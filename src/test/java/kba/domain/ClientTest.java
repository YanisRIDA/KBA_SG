package kba.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @Test
    public void testGetterSetter() {
        Client client = new Client();
        client.setFirstName("Yanis");
        client.setLastName("RIDA");
        assertEquals("Yanis", client.getFirstName());
        assertEquals("RIDA", client.getLastName());
    }

}
