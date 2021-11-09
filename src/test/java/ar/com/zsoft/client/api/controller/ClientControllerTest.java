package ar.com.zsoft.client.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import ar.com.zsoft.client.api.entity.Client;
import ar.com.zsoft.client.api.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    private List<Client> clients;

    @BeforeEach
    public void before() {
        clients = Arrays.asList(
                new Client(1, "Cristian", "Zerpa", "3511111111", "cristian@zsoft.com.ar", 20),
                new Client(2, "Rodrigo", "Zerpa", "3512222222", "rodrigo@zsoft.com.ar", 25));
    }

    @Test
    public void findAllTest() {
        when(clientService.findAll()).thenReturn(clients);
        List<Client> clients = (List<Client>) clientController.findAll();
        assertThat(clients).hasSize(2)
                .noneMatch(x -> x.getId() < 1)
                .allMatch(x -> x.getAge() >= 20);
    }

    @Test
    public void findOneClientTest() {
        when(clientService.findOneClient(0l)).thenReturn(clients.get(0));
        Client client = clientController.findOneClient(0l);
        assertEquals(1,client.getId());
        assertEquals("Cristian",client.getFirstName());
    }

    @Test
    public void createTest() {
        when(clientService.create(clients.get(0))).thenReturn(clients.get(0));
        Client client = clientController.create(clients.get(0));
        assertEquals(1,client.getId());
        assertEquals("Cristian",client.getFirstName());
    }

    @Test
    public void deleteTest() {
        clientController.delete(0l);
        verify(clientService, times(1)).delete(0l);
    }

    @Test
    public void updateClientTest() throws Exception {
        when(clientService.updateClient(clients.get(0),1l)).thenReturn(clients.get(0));
        Client client = clientController.updateClient(clients.get(0),1l);
        assertEquals(1,client.getId());
        assertEquals("Cristian",client.getFirstName());
    }

    @Test
    public void getClientsByFirstNameAndLastnameTest() {
        when(clientService.getClientsByFirstNameAndLastname("Cristian", "Zerpa"))
                .thenReturn(Arrays.asList(clients.get(0)));
        List<Client> clients = clientController.getClientsByFirstNameAndLastname("Cristian", "Zerpa");
        assertThat(clients).hasSize(1)
                .allMatch(x -> x.getId() == 1)
                .allMatch(x -> "Cristian".equals(x.getFirstName()))
                .allMatch(x -> "Zerpa".equals(x.getLastName()));
    }

}