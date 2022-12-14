package kba.service;

import kba.dao.ClientDAO;
import kba.domain.BankAccount;
import kba.domain.Client;
import kba.domain.Operation;
import kba.dto.ClientDTO;
import kba.exception.*;
import kba.dto.BankAccountDTO;
import kba.dto.OperationDTO;
import kba.dao.BankAccountDAO;
import kba.dao.OperationDAO;
import kba.utils.OperationType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bankAccountSvc")
public class BankAccountSvc {

    private final Logger LOG = LoggerFactory.getLogger(BankAccountSvc.class);

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BankAccountDAO bankAccountDAO;

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    OperationDAO operationDAO;

    //Creates a Bank Account and add it to the database
    public BankAccountDTO createAccount(ClientDTO clientDTO) throws EmptyParameterException {
        LOG.info("[KATA BANK] Creating a bank account for {}", clientDTO);
        if(clientDTO.getFirstName() == null || clientDTO.getFirstName().isEmpty())
            throw new EmptyParameterException("firstName");
        if(clientDTO.getLastName() == null || clientDTO.getLastName().isEmpty())
            throw new EmptyParameterException("lastName");
        Client client = clientDAO.createClient(clientDTO.getFirstName(), clientDTO.getLastName());
        BankAccount bankAccount = bankAccountDAO.createBankAccount(client);
        LOG.info("[KATA BANK] Successfully created bank account {}", bankAccount);
        return modelMapper.map(bankAccount, BankAccountDTO.class);
    }

    //Creates an operation(Depose/Withdraw) and apply it on the bank account
    public OperationDTO operation(int id, Long amount, OperationType operationType) throws InvalidAmountException, BankAccountNotFoundException, EmptyParameterException {
        LOG.info("[KATA BANK] Creating {} operation", operationType);
        if(amount<0)
            throw new NegativeAmountException(amount);

        BankAccount bankAccount = bankAccountDAO.findBankAccountById(id);
        if(bankAccount==null)
            throw new BankAccountNotFoundException(id);

        long oldBalance=bankAccount.getBalance();
        long newBalance=oldBalance;

        switch (operationType) {
            case DEPOSIT:
                newBalance+=amount;
                break;
            case WITHDRAWAL:
                newBalance-=amount;
                if(newBalance<0)
                    throw new NegativeBalanceException(amount);
                break;
        }

        Operation operation = operationDAO.createOperation(operationType, amount, oldBalance, newBalance);
        bankAccount.addOperation(operation);
        bankAccountDAO.updateBalance(bankAccount, newBalance);
        LOG.info("[KATA BANK] {} operation successful, new balance={}", operationType, newBalance);
        return modelMapper.map(operation,OperationDTO.class);
    }

    //Returns all bank account and its operation information
    public BankAccountDTO consult(int id) throws BankAccountNotFoundException {
        LOG.info("[KATA BANK] Consulting bank account");
        BankAccount bankAccount = bankAccountDAO.findBankAccountById(id);
        if(bankAccount==null)
            throw new BankAccountNotFoundException(id);

        LOG.info("[KATA BANK] Successfully consulted bank account, account={}", bankAccount);
        return modelMapper.map(bankAccount, BankAccountDTO.class);
    }

}
