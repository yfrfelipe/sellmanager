package br.com.sellmanager.persistence;

import br.com.sellmanager.model.offer.Offer;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OfferPersistence extends CrudRepository<Offer, UUID> {

    @Query(value = "SELECT offer FROM tb_offer WHERE offer.companyid = ?1 AND offer.requestid= ?2", nativeQuery = true)
    Offer queryOfferByCompanyAndRequestId(UUID companyID, UUID requestId);

    @Query(value = "SELECT offer FROM tb_offer WHERE offer.companyid LIKE %?1 AND requeststatus = OPEN", nativeQuery = true)
    Set<Offer> queryOpenOffersByCompany(UUID companyID);
}
