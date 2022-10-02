package kba.service;

import kba.dao.BankAccountDAO;
import kba.dao.OperationDAO;
import kba.domain.BankAccount;
import kba.dto.BankAccountDTO;
import kba.dto.OperationDTO;
import kba.exception.*;
import kba.utils.OperationType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankAccountSvcTest {

    @InjectMocks
    BankAccountSvc bankAccountSvc;

    @Spy
    BankAccountDAO bankAccountDAO;

    @Spy
    OperationDAO operationDAO;

    @Spy
    ModelMapper modelMapper;

    @Test
    public void testCreateAccountOK() throws EmptyParameterException {
        assertEquals(0, bankAccountSvc.createAccount("Yanis", "RIDA").getId());
    }

    @Test
    public void testCreateAccountKOFirstNameEmpty() {
        assertThrows(EmptyParameterException.class, () -> bankAccountSvc.createAccount("", "RIDA"));
    }

    @Test
    public void testCreateAccountKOLastNameEmpty() {
        assertThrows(EmptyParameterException.class, () -> bankAccountSvc.createAccount("Yanis", ""));
    }

    @Test
    public void testDeposeOperationOK() throws EmptyParameterException, BankAccountNotFoundException, InvalidAmountException {
        bankAccountSvc.createAccount("Yanis", "RIDA");
        assertEquals(100, bankAccountSvc.operation(0, 100L, OperationType.DEPOSIT).getNewBalance());
    }

    @Test
    public void testWithdrawOperationOK() throws EmptyParameterException, BankAccountNotFoundException, InvalidAmountException {
        bankAccountSvc.createAccount("Yanis", "RIDA");
        bankAccountSvc.operation(0, 100L, OperationType.DEPOSIT);
        assertEquals(0, bankAccountSvc.operation(0, 100L, OperationType.WITHDRAWAL).getNewBalance());
    }

    @Test
    public void testOperationKOAccountNotFound() {
        assertThrows(BankAccountNotFoundException.class, () -> bankAccountSvc.operation(10, 100L, OperationType.DEPOSIT));
    }

    @Test
    public void testOperationKONegativeAmount() {
        assertThrows(NegativeAmountException.class, () -> bankAccountSvc.operation(0, -100L, OperationType.DEPOSIT));
    }

    @Test
    public void testOperationKONegativeBalance() throws EmptyParameterException {
        bankAccountSvc.createAccount("Yanis", "RIDA");
        assertThrows(NegativeBalanceException.class, () -> bankAccountSvc.operation(0, 1000L, OperationType.WITHDRAWAL));
    }

    @Test
    public void testConsultOK() throws BankAccountNotFoundException, EmptyParameterException, InvalidAmountException {
        bankAccountSvc.createAccount("Yanis", "RIDA");
        bankAccountSvc.operation(0, 100L, OperationType.DEPOSIT);
        bankAccountSvc.operation(0, 50L, OperationType.WITHDRAWAL);
        assertEquals(0, bankAccountSvc.consult(0).getId());
        assertEquals(50, bankAccountSvc.consult(0).getBalance());
        assertEquals(2, bankAccountSvc.consult(0).getOperations().size());
    }

    @Test
    public void testConsultKOAccountNotFound() {
        assertThrows(BankAccountNotFoundException.class, () -> bankAccountSvc.consult(10));
    }

}
