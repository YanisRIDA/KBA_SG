package kba.dao;

import kba.domain.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO {

    private List<Client> clients = new ArrayList<>();

    public Client createClient(String firstName, String lastName) {
        Client client = new Client(firstName, lastName);
        clients.add(client);
        return client;
    }
}
