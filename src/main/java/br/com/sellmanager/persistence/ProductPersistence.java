package br.com.sellmanager.persistence;

import br.com.sellmanager.model.company.CompanyProduct;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductPersistence extends CrudRepository<CompanyProduct, UUID> {

    @Query(value = "SELECT * FROM tb_product WHERE modelid = ?1", nativeQuery = true)
    Set<UUID> queryCompaniesWithProductModel(UUID modelID);
}
