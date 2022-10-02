package kba.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientDTOTest {

    @Test
    public void testGetterSetter() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("Yanis");
        clientDTO.setLastName("RIDA");
        assertEquals("Yanis", clientDTO.getFirstName());
        assertEquals("RIDA", clientDTO.getLastName());
    }

}
