package org.example;

import org.example.exceptions.ClientNotFoundException;
import org.example.exceptions.TableIsEmptyException;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> findOneById(int id) throws ClientNotFoundException, TableIsEmptyException {
        isTableEmpty();
        if (clientRepository.existsById(id)) {
            return clientRepository.findById(id);
        } else {
            throw new ClientNotFoundException(); //обработка в классе ClientExceptionHandler
        }
    }

    public List<Client> findAll() throws TableIsEmptyException {
        isTableEmpty();
        return clientRepository.findAll();
    }

    public String addClient(Client client) {
        clientRepository.save(client);
        return "Пользователь успешно добавлен.";
    }

    public String deleteClient(int id) throws ClientNotFoundException, TableIsEmptyException {
        isTableEmpty();
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return "Пользователь успешно удален.";
        } else {
            throw new ClientNotFoundException(); //обработка в классе ClientExceptionHandler
        }
    }

    /**
     * Проверяет, пустая ли таблица у Базы Данных
     *
     * @throws TableIsEmptyException созданный экспешн, перехватывается в ClientExceptionHandler
     */
    private void isTableEmpty() throws TableIsEmptyException {
        if (clientRepository.findAll().isEmpty()) {
            throw new TableIsEmptyException(); //обработка в классе ClientExceptionHandler
        }
    }
}