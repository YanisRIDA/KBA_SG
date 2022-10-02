package kba.controller;

import kba.dto.BankAccountDTO;
import kba.dto.ClientDTO;
import kba.dto.OperationDTO;
import kba.exception.*;
import kba.service.BankAccountSvc;
import kba.utils.OperationType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
public class BankAccountCtlTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    BankAccountSvc bankAccountSvc;

    @InjectMocks
    BankAccountCtl bankAccountCtl;

    @Before
    public void setUp() throws EmptyParameterException, BankAccountNotFoundException, InvalidAmountException {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(0);
        bankAccountDTO.setClient(new ClientDTO("Yanis","RIDA"));
        bankAccountDTO.setBalance(0);
        bankAccountDTO.setOperations(new ArrayList<>());

        Date now = new Date();
        OperationDTO operationDTO1 = new OperationDTO();
        operationDTO1.setOldBalance(0);
        operationDTO1.setAmount(100);
        operationDTO1.setNewBalance(100);
        operationDTO1.setDate(now);
        operationDTO1.setOperationType(OperationType.DEPOSIT);

        OperationDTO operationDTO2 = new OperationDTO();
        operationDTO2.setOldBalance(100);
        operationDTO2.setAmount(100);
        operationDTO2.setNewBalance(0);
        operationDTO2.setDate(now);
        operationDTO2.setOperationType(OperationType.WITHDRAWAL);

        ArrayList<OperationDTO> operations = new ArrayList<>();
        operations.add(operationDTO1);
        operations.add(operationDTO2);

        bankAccountDTO.setOperations(operations);



        this.mockMvc = MockMvcBuilders.standaloneSetup(this.bankAccountCtl).build();
        Mockito.when(this.bankAccountSvc.createAccount(new ClientDTO("Yanis","RIDA"))).thenReturn(bankAccountDTO);
        Mockito.when(this.bankAccountSvc.createAccount(new ClientDTO("Yanis",""))).thenThrow(new EmptyParameterException("lastName"));
        Mockito.when(this.bankAccountSvc.createAccount(new ClientDTO("","RIDA"))).thenThrow(new EmptyParameterException("firstName"));
        Mockito.when(this.bankAccountSvc.operation(0, null, OperationType.DEPOSIT)).thenThrow(new EmptyParameterException("amount"));
        Mockito.when(this.bankAccountSvc.operation(0, null, OperationType.WITHDRAWAL)).thenThrow(new EmptyParameterException("amount"));
        Mockito.when(this.bankAccountSvc.operation(10, 100L, OperationType.DEPOSIT)).thenThrow(new BankAccountNotFoundException(10));
        Mockito.when(this.bankAccountSvc.operation(10, 100L, OperationType.WITHDRAWAL)).thenThrow(new BankAccountNotFoundException(10));
        Mockito.when(this.bankAccountSvc.operation(0, -100L, OperationType.DEPOSIT)).thenThrow(new NegativeAmountException(0));
        Mockito.when(this.bankAccountSvc.operation(0, -100L, OperationType.WITHDRAWAL)).thenThrow(new NegativeAmountException(0));
        Mockito.when(this.bankAccountSvc.operation(0, 1000L, OperationType.WITHDRAWAL)).thenThrow(new NegativeBalanceException(0));
        Mockito.when(this.bankAccountSvc.operation(0, 100L, OperationType.DEPOSIT)).thenReturn(operationDTO1);
        Mockito.when(this.bankAccountSvc.operation(0, 100L, OperationType.WITHDRAWAL)).thenReturn(operationDTO2);
        Mockito.when(this.bankAccountSvc.consult(0)).thenReturn(bankAccountDTO);
    }

    @Test
    public void testCreateBankAccountOK() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Yanis\", \"lastName\": \"RIDA\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("0"));
        assertTrue(response.getContentAsString().contains("Yanis"));
        assertTrue(response.getContentAsString().contains("RIDA"));
    }

    @Test
    public void testCreateBankAccountKOMissingFirstName() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{ \"lastName\": \"RIDA\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        assertTrue(response.getContentAsString().contains("Empty parameter: firstName"));
    }

    @Test
    public void testCreateBankAccountKOMissingLastName() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Yanis\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        assertTrue(response.getContentAsString().contains("Empty parameter: lastName"));
    }

    @Test
    public void testCreateBankAccountKOEmptyFirstName() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"\", \"lastName\": \"RIDA\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        assertTrue(response.getContentAsString().contains("Empty parameter: firstName"));
    }

    @Test
    public void testCreateBankAccountKOEmptyLastName() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Yanis\", \"lastName\": \"\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        assertTrue(response.getContentAsString().contains("Empty parameter: lastName"));
    }

    @Test
    public void testCreateBankAccountKOJSONError() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Yanis\", }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        assertTrue(response.getContentAsString().contains("Unable to parse json data, please check the format"));
    }

    @Test
    public void testDeposeOK() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/0/depose")
                        .param("amount", "100"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("\"newBalance\":100"));
    }

    @Test
    public void testDeposeKOAccountNotFound() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/10/depose")
                        .param("amount", "100"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Bank account not found"));
    }

    @Test
    public void testDeposeKONegativeAmount() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/0/depose")
                        .param("amount", "-100"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Amount value cannot be less than 0"));
    }

    @Test
    public void testDeposeKOMissingAmount() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/10/depose"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        assertTrue(response.getContentAsString().contains("Required request parameter 'amount' " +
                "for method parameter type Long is not present"));
    }

    @Test
    public void testDeposeKOEmptyAmount() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/0/depose")
                        .param("amount", ""))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Required request parameter 'amount' " +
                "for method parameter type Long is present but converted to null"));
    }

    @Test
    public void testWithdrawOK() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/0/withdraw")
                        .param("amount", "100"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("\"newBalance\":0"));
    }

    @Test
    public void testWithdrawKOAccountNotFound() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/10/withdraw")
                        .param("amount", "100"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Bank account not found"));
    }

    @Test
    public void testWithdrawKONegativeAmount() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/0/withdraw")
                        .param("amount", "-100"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Amount value cannot be less than 0"));
    }

    @Test
    public void testWithdrawKOMissingAmount() throws Exception {
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/api/v1/bankaccounts/10/withdraw"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        assertTrue(response.getContentAsString().contains("Required request parameter 'amount'" +
                " for method parameter type Long is not present"));
    }

}
