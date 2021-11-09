package ar.com.zsoft.client.api.controller;

import java.util.List;

import ar.com.zsoft.client.api.entity.Client;
import ar.com.zsoft.client.api.repository.ClientRepository;
import ar.com.zsoft.client.api.service.ClientService;
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
    private ClientService clientService;

    @GetMapping
    public Iterable findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client findOneClient(@PathVariable Long id) {
        return clientService.findOneClient(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@RequestBody Client client, @PathVariable Long id) throws Exception {
        return clientService.updateClient(client, id);
    }

    @GetMapping(path = "/find")
    public List<Client> getClientsByFirstNameAndLastname(@RequestParam String firstName, @RequestParam String lastName) {
        return clientService.getClientsByFirstNameAndLastname(firstName, lastName);
    }
}

