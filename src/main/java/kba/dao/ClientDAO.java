package kba.dao;

import kba.model.BankAccount;
import kba.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO {

    private final List<Client> clients = new ArrayList<>();

    public void createClientAccount(Client clientToAdd) {
        for(Client client : clients) {
            if(client.getMail().equals(clientToAdd.getMail())) {
                //TODO throw already exist
            }
        }
        clientToAdd.setId(clients.size());
        clientToAdd.setToken("token"+clientToAdd.getId());
        clientToAdd.setBankAccount(new BankAccount(clientToAdd.getId(), 0, new ArrayList<>()));
        clients.add(clientToAdd);
    }

    //Simulates oAuth token generation
    public String getToken(String mail, String password) {
        for(Client client : clients) {
            if(client.getMail().equals(mail)) {
                if (client.getPassword().toString().equals(password))
                    return client.getToken();
                else {
                    //TODO THROW bad password;
                    return null;
                }
            }
        }
        //TODO THROW client doesn't exist;
        return null;
    }

    public Client findClientByToken(String token) {
        for(Client client : clients) {
            if(client.getToken().equals(token))
                return client;
        }
        return null;
    }

}
