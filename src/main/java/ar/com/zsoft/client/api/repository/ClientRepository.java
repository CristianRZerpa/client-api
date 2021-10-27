package ar.com.zsoft.client.api.repository;

import java.io.Serializable;
import java.util.List;

import ar.com.zsoft.client.api.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Serializable> {

    public List<Client> findByFirstNameAndLastName(String firstName, String lastName);
}
