package br.com.sellmanager.persistence;

import br.com.sellmanager.model.request.ProductModel;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProductModelPersistence extends CrudRepository<ProductModel, UUID> {
}
