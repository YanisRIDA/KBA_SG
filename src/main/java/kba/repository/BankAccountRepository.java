package kba.repository;

import kba.model.BankAccount;
import kba.model.Operation;
import kba.utils.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BankAccountRepository {

    @Autowired
    OperationRepository operationRepository;

    public BankAccount depose(BankAccount account, long amount) {
        if(amount<0) {
            //TODO THROW EXCEPTION <0
        }
        Operation operation = operationRepository.createOperation(OperationType.DEPOSIT, amount);
        account.addOperation(operation);
        account.setBalance(account.getBalance()+amount);
        return account;
    }

    public BankAccount withdraw(BankAccount account, long amount) {
        if(amount<0) {
            //TODO THROW EXCEPTION <0
        }
        Operation operation = operationRepository.createOperation(OperationType.WITHDRAWAL, amount);
        account.addOperation(operation);
        account.setBalance(account.getBalance()-amount);
        return account;
    }

    public List<Operation> getOperationsForAccountId(BankAccount account) {
        return account.getOperations();
    }

}
