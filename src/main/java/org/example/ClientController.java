package org.example;

import org.example.exceptions.ClientNotFoundException;
import org.example.exceptions.TableIsEmptyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/clients", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Client> getClients() throws TableIsEmptyException {
        return clientService.findAll();
    }

    @GetMapping(value = "clients/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Optional<Client>> findClient(@PathVariable int id) throws ClientNotFoundException, TableIsEmptyException {
        return clientService.findOneById(id);
    }

    @PostMapping(value = "clients/add/{name}/{email}/{phone}")
    public String addClient(@PathVariable String name, @PathVariable String email, @PathVariable String phone) {
        Client client = new Client(name, email, phone);
        return clientService.addClient(client);
    }

    @PostMapping(value = "clients/delete/{id}")
    public String deleteClient(@PathVariable int id) throws ClientNotFoundException, TableIsEmptyException {
        return clientService.deleteClient(id);
    }
}