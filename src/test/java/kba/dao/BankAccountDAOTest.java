package kba.dao;

import kba.domain.BankAccount;
import kba.domain.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankAccountDAOTest {

    @InjectMocks
    BankAccountDAO bankAccountDAO;

    @Test
    public void TestCreateBankAccountAndFindById() {
        assertEquals(0, bankAccountDAO.createBankAccount(new Client("Yanis", "RIDA")).getId());
        assertEquals("Yanis", bankAccountDAO.findBankAccountById(0).getClient().getFirstName());
    }

    @Test
    public void TestUpdateBalance() {
        BankAccount bankAccount = bankAccountDAO.createBankAccount(new Client("Yanis", "RIDA"));
        assertEquals(0, bankAccountDAO.findBankAccountById(0).getBalance());
        bankAccountDAO.updateBalance(bankAccount, 200);
        assertEquals(200, bankAccountDAO.findBankAccountById(0).getBalance());
    }

}
