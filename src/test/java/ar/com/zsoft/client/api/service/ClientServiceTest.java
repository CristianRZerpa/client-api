package ar.com.zsoft.client.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import ar.com.zsoft.client.api.entity.Client;
import ar.com.zsoft.client.api.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository repository;

    private List<Client> clients;

    @BeforeEach
    public void before() {
        clients = Arrays.asList(
                new Client(1, "Cristian", "Zerpa", "3511111111", "cristian@zsoft.com.ar", 20),
                new Client(2, "Rodrigo", "Zerpa", "3512222222", "rodrigo@zsoft.com.ar", 25));
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(clients);
        List<Client> clients = (List<Client>) clientService.findAll();
        assertThat(clients).hasSize(2)
                .noneMatch(x -> x.getId() < 1)
                .allMatch(x -> x.getAge() >= 20);
    }

    @Test
    public void findOneClient() {
        when(repository.findById(1l)).thenReturn(Optional.ofNullable(clients.get(0)));
        Client client = clientService.findOneClient(1l);
        assertEquals(1,client.getId());
        assertEquals("Cristian",client.getFirstName());
    }

    @Test
    public void create() {
        when(repository.save(clients.get(0))).thenReturn(clients.get(0));
        Client client = clientService.create(clients.get(0));
        assertEquals(1,client.getId());
        assertEquals("Cristian",client.getFirstName());
    }

    @Test
    public void delete() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(clients.get(0)));
        clientService.delete(0l);
        verify(repository, times(1)).findById(0l);
        verify(repository, times(1)).deleteById(0l);
    }

    @Test
    public void updateClient() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(clients.get(0)));
        when(repository.save(clients.get(0))).thenReturn(clients.get(0));
        Client client = clientService.updateClient(clients.get(0),1l);
        assertEquals(1,client.getId());
        assertEquals("Cristian",client.getFirstName());
    }

    @Test
    public void getClientsByFirstNameAndLastname() {
        when(repository.findByFirstNameAndLastName("Cristian", "Zerpa"))
                .thenReturn(Arrays.asList(clients.get(0)));
        List<Client> clients = clientService.getClientsByFirstNameAndLastname("Cristian", "Zerpa");
        assertThat(clients).hasSize(1)
                .allMatch(x -> x.getId() == 1)
                .allMatch(x -> "Cristian".equals(x.getFirstName()))
                .allMatch(x -> "Zerpa".equals(x.getLastName()));
    }

}