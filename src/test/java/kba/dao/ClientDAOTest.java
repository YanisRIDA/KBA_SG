package kba.dao;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientDAOTest {

    @Test
    public void testCreateClient() {
        ClientDAO clientDAO = new ClientDAO();
        assertEquals("Yanis", clientDAO.createClient("Yanis", "RIDA").getFirstName());
        assertEquals("RIDA", clientDAO.createClient("Yanis", "RIDA").getLastName());
    }

}
