package kba.dto;

import kba.utils.OperationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OperationDTOTest {

    @Test
    public void testGetterSetter() {
        Date now = new Date();
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setOperationType(OperationType.DEPOSIT);
        operationDTO.setDate(now);
        operationDTO.setOldBalance(200);
        operationDTO.setAmount(100);
        operationDTO.setNewBalance(300);
        assertEquals(OperationType.DEPOSIT, operationDTO.getOperationType());
        assertEquals(now, operationDTO.getDate());
        assertEquals(200, operationDTO.getOldBalance());
        assertEquals(100, operationDTO.getAmount());
        assertEquals(300, operationDTO.getNewBalance());
    }

}
