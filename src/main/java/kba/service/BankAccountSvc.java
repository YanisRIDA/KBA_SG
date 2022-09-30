package kba.service;

import kba.model.BankAccount;
import kba.model.Operation;
import kba.model.dto.BankAccountDTO;
import kba.model.dto.OperationDTO;
import kba.dao.BankAccountDAO;
import kba.dao.ClientDAO;
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
    private ModelMapper modelMapper;

    @Autowired
    BankAccountDAO bankAccountDAO;

    @Autowired
    OperationDAO operationDAO;

    @Autowired
    ClientDAO clientDAO;

    public OperationDTO depose(long amount) {
        LOG.info("[KATA BANK] Creating deposit operation");
        if(amount<0) {
            //TODO THROW EXCEPTION <0
        }
        //TODO TOKEN
        BankAccount clientBankAccount = clientDAO.findClientByToken("token").getBankAccount();
        Operation operation = operationDAO.createOperation(OperationType.DEPOSIT, amount,
                clientBankAccount.getBalance(), clientBankAccount.getBalance()+amount);
        clientBankAccount.addOperation(operation);
        bankAccountDAO.updateBalance(clientBankAccount, amount);
        return modelMapper.map(operation,OperationDTO.class);
    }

    public OperationDTO withdraw(long amount) {
        LOG.info("[KATA BANK] Creating withdrawal operation");
        if(amount<0) {
            //TODO THROW EXCEPTION <0
        }
        //TODO TOKEN
        BankAccount clientBankAccount = clientDAO.findClientByToken("token").getBankAccount();
        Operation operation = operationDAO.createOperation(OperationType.WITHDRAWAL, amount,
                clientBankAccount.getBalance(), clientBankAccount.getBalance()-amount);
        clientBankAccount.addOperation(operation);
        bankAccountDAO.updateBalance(clientBankAccount, -amount);
        return modelMapper.map(operation,OperationDTO.class);
    }

    public BankAccountDTO consult() {
        LOG.info("[KATA BANK] Consulting bank account");
        return modelMapper.map(clientDAO.findClientByToken("token").getBankAccount(), BankAccountDTO.class);
    }

}
