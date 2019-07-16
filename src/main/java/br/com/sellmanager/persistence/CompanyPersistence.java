package br.com.sellmanager.persistence;

import br.com.sellmanager.model.company.Company;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CompanyPersistence extends CrudRepository<Company, UUID> {
}
