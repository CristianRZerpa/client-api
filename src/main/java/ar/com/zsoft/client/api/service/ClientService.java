package ar.com.zsoft.client.api.service;

import ar.com.zsoft.client.api.entity.Client;
import ar.com.zsoft.client.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public Iterable findAll() {
        return repository.findAll();
    }

    public Client findOneClient(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    public Client create(Client client) {
        return repository.save(client);
    }

    public void delete(Long id) {
        repository.findById(id).orElseGet(null);
        repository.deleteById(id);
    }

    public Client updateClient(Client client, Long id) throws Exception {
        if (client.getId() != id) {
            throw new Exception("Error en actualizar el cliente");
        }
        repository.findById(id).get();
        return repository.save(client);
    }

    public List<Client> getClientsByFirstNameAndLastname(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }
}
