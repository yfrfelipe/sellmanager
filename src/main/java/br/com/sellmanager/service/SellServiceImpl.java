package br.com.sellmanager.service;

import br.com.sellmanager.dto.sell.ItemDTO;
import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.dto.sell.SellPageDTO;
import br.com.sellmanager.exception.southbound.sell.SellCreateException;
import br.com.sellmanager.exception.southbound.sell.SellDeleteException;
import br.com.sellmanager.exception.southbound.sell.SellNotFoundException;
import br.com.sellmanager.exception.southbound.sell.SellUpdateException;
import br.com.sellmanager.external.product.ProductClientService;
import br.com.sellmanager.model.reservation.Reservation;
import br.com.sellmanager.model.sell.Sell;
import br.com.sellmanager.model.sell.SellStatus;
import br.com.sellmanager.persistence.SellPersistence;
import br.com.sellmanager.service.converter.SellConverter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SellServiceImpl implements SellService {

    @Autowired
    private SellPersistence sellPersistence;

    @Autowired
    private ProductClientService productClientService;

    @Autowired
    private SellConverter sellConverter;

    @Autowired
    private ReservationService reservationService;

    private static final String SELL_NOT_FOUND_MESSAGE = "There's no sell with ID %s";

    @Override
    public SellDTO create(final SellDTO entityDTO) throws SellCreateException {
        final Sell sell = sellConverter.fromDto(entityDTO);
        final UUID reservationId = UUID.randomUUID();
        final Reservation reservation = new Reservation();

        reservation.setTimestamp(System.currentTimeMillis());
        reservation.setTransactionID(reservationId);

        sell.setTimestamp(System.currentTimeMillis());
        sell.setReservation(reservation);
        sell.setSellStatus(SellStatus.IN_PROGRESS);

        reservationService.reserveItems(reservationId, extractProductByQuantity(entityDTO.getItemsDTO()));
        return sellConverter.toDto(sellPersistence.save(sell));
    }

    @Override
    public SellDTO retrieve(final Integer id) throws SellNotFoundException {
        if (!sellPersistence.existsById(id)) {
            throw new SellNotFoundException(String.format(SELL_NOT_FOUND_MESSAGE, id));
        }

        final Optional<Sell> optional = sellPersistence.findById(id);
        return sellConverter.toDto(optional.get());
    }

    @Override
    public void update(final Integer id, final SellDTO entityDTO) throws SellUpdateException {
        final Optional<Sell> optional = sellPersistence.findById(id);
        if (!optional.isPresent()) {
            throw new SellUpdateException(String.format(SELL_NOT_FOUND_MESSAGE, id));
        }

        final SellDTO updatedDTO = new SellDTO(entityDTO);
        final Sell updated = sellConverter.fromDto(updatedDTO);

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
    public void finalizeSell(final Integer id) throws SellUpdateException {
        if (sellPersistence.existsById(id)) {
            final Optional<Sell> optional = sellPersistence.findById(id);
            final UUID reservationID = optional.get().getReservation().getTransactionID();
            productClientService.finalizeReservation(reservationID);
        }
    }

    @Override
    public void cancelSell(final Integer id) throws SellUpdateException {
        if (sellPersistence.existsById(id)) {
            throw new SellUpdateException(String.format(SELL_NOT_FOUND_MESSAGE, id));
        }

        final Sell sell = sellPersistence.findById(id).get();
        productClientService.cancelReservation(sell.getReservation().getTransactionID());
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

    private Pageable buildTimestampPageable(final Integer quantity, final Integer pagegNum) {
        final Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "timestamp"));
        final Pageable pageable = PageRequest.of(pagegNum, quantity, sort);

        return pageable;
    }

    private SellPageDTO buildSellPageDTO(final Page<Sell> page) {
        final List<SellDTO> sellDTOS = Lists.newArrayList();

        for (Sell sell : page.getContent()) {
            sellDTOS.add(sellConverter.toDto(sell));
        }

        final SellPageDTO sellPageDTO = new SellPageDTO();
        sellPageDTO.setContent(sellDTOS);
        sellPageDTO.setTotalElements(page.getTotalElements());
        sellPageDTO.setTotalPages(page.getTotalPages());
        sellPageDTO.setCurrentPage(page.getPageable().getPageNumber());

        return sellPageDTO;
    }

    private Map<Integer, Integer> extractProductByQuantity(final Set<ItemDTO> itens) {
        final Map<Integer, Integer> productByQuantity = Maps.newHashMap();
        for (ItemDTO item : itens) {
            productByQuantity.put(item.getProductID(), item.getQuantity());
        }
        return productByQuantity;
    }

    @Override
    public void close() throws Exception {
    }
}
