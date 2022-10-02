package kba.domain;

import kba.utils.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OperationTest {

    @Test
    public void testGetterSetter() {
        Date now = new Date();
        Operation operation = new Operation();
        operation.setId(0);
        operation.setOperationType(OperationType.DEPOSIT);
        operation.setDate(now);
        operation.setOldBalance(200);
        operation.setAmount(100);
        operation.setNewBalance(300);
        assertEquals(0, operation.getId());
        assertEquals(OperationType.DEPOSIT, operation.getOperationType());
        assertEquals(now, operation.getDate());
        assertEquals(200, operation.getOldBalance());
        assertEquals(100, operation.getAmount());
        assertEquals(300, operation.getNewBalance());
    }

}
