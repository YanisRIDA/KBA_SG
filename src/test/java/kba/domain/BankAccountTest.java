package kba.domain;

import kba.utils.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankAccountTest {

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
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(0);
        bankAccount.setFirstName("Yanis");
        bankAccount.setLastName("RIDA");
        bankAccount.setBalance(1000);
        bankAccount.setOperations(new ArrayList<>());
        bankAccount.getOperations().add(operation);
        assertEquals(0, bankAccount.getId());
        assertEquals("Yanis", bankAccount.getFirstName());
        assertEquals("RIDA", bankAccount.getLastName());
        assertEquals(1000, bankAccount.getBalance());
        assertEquals(100, bankAccount.getOperations().get(0).getAmount());
    }

}
