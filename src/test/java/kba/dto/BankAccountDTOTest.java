package kba.dto;

import kba.domain.Client;
import kba.utils.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankAccountDTOTest {

    @Test
    public void testGetterSetter() {
        Date now = new Date();
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setOperationType(OperationType.DEPOSIT);
        operationDTO.setDate(now);
        operationDTO.setOldBalance(200);
        operationDTO.setAmount(100);
        operationDTO.setNewBalance(300);
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(0);
        bankAccountDTO.setClient(new ClientDTO("Yanis", "RIDA"));
        bankAccountDTO.setBalance(1000);
        bankAccountDTO.setOperations(new ArrayList<>());
        bankAccountDTO.getOperations().add(operationDTO);
        assertEquals(0, bankAccountDTO.getId());
        assertEquals("Yanis", bankAccountDTO.getClient().getFirstName());
        assertEquals("RIDA", bankAccountDTO.getClient().getLastName());
        assertEquals(1000, bankAccountDTO.getBalance());
        assertEquals(100, bankAccountDTO.getOperations().get(0).getAmount());
    }
}
