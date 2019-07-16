package br.com.sellmanager.service;

import br.com.sellmanager.dto.sell.ItemDTO;
import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.dto.sell.SellInProgressDTO;
import br.com.sellmanager.dto.sell.SellPageDTO;
import br.com.sellmanager.exception.northbound.SellFinalizeException;
import br.com.sellmanager.exception.southbound.sell.SellCreateException;
import br.com.sellmanager.exception.southbound.sell.SellDeleteException;
import br.com.sellmanager.exception.southbound.sell.SellNotFoundException;
import br.com.sellmanager.exception.southbound.sell.SellUpdateException;
import br.com.sellmanager.external.product.ProductClientService;
import br.com.sellmanager.model.reservation.Reservation;
import br.com.sellmanager.model.sell.Item;
import br.com.sellmanager.model.sell.ItemsBySell;
import br.com.sellmanager.model.sell.Sell;
import br.com.sellmanager.model.sell.SellByReservation;
import br.com.sellmanager.model.sell.SellStatus;
import br.com.sellmanager.persistence.ItemsBySellPersistence;
import br.com.sellmanager.persistence.ItemsPersistence;
import br.com.sellmanager.persistence.SellByReservationPersistence;
import br.com.sellmanager.persistence.SellPersistence;
import br.com.sellmanager.service.converter.ItemConverter;
import br.com.sellmanager.service.converter.SellConverter;
import br.com.sellmanager.service.converter.SellInProgressConverter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SellServiceImpl implements SellService {
    final static Logger LOG = LogManager.getLogger(SellServiceImpl.class);

    private final Map<UUID, SellResultAction> resultActionListener = Maps.newConcurrentMap();

    @Autowired
    private SellPersistence sellPersistence;

    @Autowired
    private SellByReservationPersistence sellByReservationPersistence;

    @Autowired
    private ItemsBySellPersistence itemsBySellPersistence;

    @Autowired
    private ItemsPersistence itemsPersistence;

    @Autowired
    private ProductClientService productClientService;

    @Autowired
    private SellInProgressConverter sellInProgressConverter;

    @Autowired
    private SellConverter sellConverter;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private ReservationService reservationService;

    private static final String SELL_NOT_FOUND_MESSAGE = "There's no sell with ID %s";

    @Override
    public SellDTO create(final SellDTO entityDTO) throws SellCreateException {
        final Sell sell = sellConverter.fromDto(entityDTO);
        final UUID reservationId = UUID.randomUUID();
        final Reservation reservation = new Reservation();
        final ItemsBySell itemsBySell = new ItemsBySell();
        final Set<Item> itemsToPersist = Sets.newHashSet();

        itemsBySell.setItemId(Sets.newHashSet());

        for(ItemDTO itemDTO : entityDTO.getItemsDTO()) {
            itemsToPersist.add(itemConverter.fromDto(itemDTO));
        }

        final Set<Item> persistedItems = persistItems(itemsToPersist);

        for (Item item : persistedItems) {
            itemsBySell.getItemId().add(item.getId());
        }

        reservation.setTimestamp(System.currentTimeMillis());
        reservation.setTransactionID(reservationId);

        sell.setTimestamp(System.currentTimeMillis());
        sell.setReservation(reservation);
        sell.setSellStatus(SellStatus.IN_PROGRESS);
        sell.setItemsBySell(itemsBySell);

        reservationService.reserveItems(reservationId, extractProductByQuantity(entityDTO.getItemsDTO()));
        final Sell persistedSell = sellPersistence.save(sell);

        final SellByReservation sellByReservation = new SellByReservation(reservationId, persistedSell.getId());
        sellByReservationPersistence.save(sellByReservation);
        return sellConverter.toDto(persistedSell);
    }

    @Override
    public SellInProgressDTO retrieve(final Integer id) throws SellNotFoundException {
        if (!sellPersistence.existsById(id)) {
            throw new SellNotFoundException(String.format(SELL_NOT_FOUND_MESSAGE, id));
        }

        final Optional<Sell> optional = sellPersistence.findById(id);
        final Sell sell = optional.get();
        final SellInProgressDTO sellDTO = sellInProgressConverter.toDto(sell);

        final ItemsBySell itemsBySell = sell.getItemsBySell();
        sellDTO.setItemsDTO(extractItemsBy(itemsBySell));

        return sellDTO;
    }

    public void update(final Integer id, final SellInProgressDTO entityDTO) throws SellUpdateException {
        final Optional<Sell> optional = sellPersistence.findById(id);
        if (!optional.isPresent()) {
            throw new SellUpdateException(String.format(SELL_NOT_FOUND_MESSAGE, id));
        }

        final SellInProgressDTO updatedDTO = new SellInProgressDTO(entityDTO);
        final Sell updated = sellInProgressConverter.fromDto(updatedDTO);

        updated.setId(id);
        reservationService.reserveItems(
                updated.getReservation().getTransactionID(),
                extractProductByQuantity(updatedDTO.getItemsDTO()));
        sellPersistence.save(updated);
    }

    @Override
    public void delete(final Integer id) throws SellDeleteException {
        sellPersistence.deleteById(id);
    }

    @Override
    public void finalizeSell(final Integer id, final Consumer<String> consumer) throws SellFinalizeException {
        if (sellPersistence.existsById(id)) {
            final Optional<Sell> optional = sellPersistence.findById(id);
            final UUID reservationID = optional.get().getReservation().getTransactionID();
            productClientService.finalizeReservation(reservationID, consumer);

            resultActionListener.put(reservationID, new SellResultAction() {
                @Override
                public void onSuccess(final UUID reservationID) {
                    final Optional<SellByReservation> sellByReservationOpt = sellByReservationPersistence
                            .findById(reservationID);
                    if (sellByReservationOpt.isPresent()) {
                        final SellByReservation sellByReservation = sellByReservationOpt.get();
                        final Sell sell = sellPersistence.findById(sellByReservation.getSellID()).get();
                        sell.setSellStatus(SellStatus.FINALIZED);
                        sellPersistence.save(sell);
                    }
                }

                @Override
                public void onFail(final String message, UUID reservationID) throws SellFinalizeException {
                    LOG.info("Handling ReservationFinalizeException");
                    final StringBuilder builder = new StringBuilder();
                    builder.append("Sell with ID ");
                    builder.append(reservationID);
                    builder.append(" could not be finalized ");
                    builder.append("reason: ");
                    builder.append(message);
//                    throw new SellFinalizeException(builder.toString());
                    LOG.info(builder.toString());
                }
            });
        }
    }


    @Override
    public void cancelSell(final Integer id, final Consumer<String> consumer) throws SellUpdateException {
        if (sellPersistence.existsById(id)) {
            throw new SellUpdateException(String.format(SELL_NOT_FOUND_MESSAGE, id));
        }

        final Sell sell = sellPersistence.findById(id).get();
        productClientService.cancelReservation(sell.getReservation().getTransactionID(), consumer);
        sell.setTimestamp(System.currentTimeMillis());
        sell.setSellStatus(SellStatus.CANCELED);
        sellPersistence.save(sell);
    }

    @Override
    public SellPageDTO listByQuantity(final Integer quantity, final Integer pageNum) {
        final Pageable pageable = buildTimestampPageable(quantity, pageNum);
        final Page<Sell> page = sellPersistence.findAll(pageable);

        return buildSellPageDTO(page);
    }

    @Override
    public SellPageDTO listBy(final String timestamp, final Integer quantity, final Integer pageNum) {
        final Pageable pageable = buildTimestampPageable(quantity, pageNum);
        final Page<Sell> page = sellPersistence.findSellByTimestamp(Long.valueOf(timestamp), pageable);
        return buildSellPageDTO(page);
    }

    private Set<Item> persistItems(final Set<Item> items) {
        return (Set) itemsPersistence.saveAll(items);
    }

    private Pageable buildTimestampPageable(final Integer quantity, final Integer pagegNum) {
        final Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "timestamp"));
        final Pageable pageable = PageRequest.of(pagegNum, quantity, sort);

        return pageable;
    }

    private SellPageDTO buildSellPageDTO(final Page<Sell> page) {
        final List<SellInProgressDTO> sellInProgressDTOS = Lists.newArrayList();

        for (Sell sell : page.getContent()) {
            final SellInProgressDTO sellInProgressDTO = sellInProgressConverter.toDto(sell);
            sellInProgressDTO.setItemsDTO(extractItemsBy(sell.getItemsBySell()));
            sellInProgressDTOS.add(sellInProgressDTO);
        }

        final SellPageDTO sellPageDTO = new SellPageDTO();
        sellPageDTO.setContent(sellInProgressDTOS);
        sellPageDTO.setTotalElements(page.getTotalElements());
        sellPageDTO.setTotalPages(page.getTotalPages());
        sellPageDTO.setCurrentPage(page.getPageable().getPageNumber());

        return sellPageDTO;
    }

    private Set<ItemDTO> extractItemsBy(final ItemsBySell itemsBySell) {
        final Set<Item> items = (Set) itemsPersistence.findAllById(itemsBySell.getItemId());
        final Set<ItemDTO> itemsDTO = Sets.newHashSet();

        for (Item item : items) {
            itemsDTO.add(itemConverter.toDto(item));
        }
        return itemsDTO;
    }

    private Map<Integer, Integer> extractProductByQuantity(final Set<ItemDTO> itens) {
        final Map<Integer, Integer> productByQuantity = Maps.newHashMap();
        for (ItemDTO item : itens) {
            productByQuantity.put(item.getProductID(), item.getQuantity());
        }
        return productByQuantity;
    }

    public synchronized SellResultAction getResultAction(final UUID reservationID) {
        return resultActionListener.get(reservationID);
    }

    @Override
    public void close() throws Exception {
    }
}
