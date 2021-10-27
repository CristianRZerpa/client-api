package ar.com.zsoft.client.api.controller;

import java.util.List;

import ar.com.zsoft.client.api.entity.Client;
import ar.com.zsoft.client.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepo;

    @GetMapping
    public Iterable findAll() {
        return clientRepo.findAll();
    }

    @GetMapping("/{id}")
    public Client findOneClient(@PathVariable Long id) {
        return clientRepo.findById(id).orElseThrow(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientRepo.save(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientRepo.findById(id).orElseGet(null);
        clientRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@RequestBody Client client, @PathVariable Long id) throws Exception {
        if (client.getId() != id) {
            throw new Exception("Error en actualizar el cliente");
        }
        clientRepo.findById(id).get();
        return clientRepo.save(client);
    }

    @GetMapping(path = "/find")
    public List<Client> getClientsByFirstNameAndLastname(@RequestParam String firstName, @RequestParam String lastName) {
        return clientRepo.findByFirstNameAndLastName(firstName, lastName);
    }
}

