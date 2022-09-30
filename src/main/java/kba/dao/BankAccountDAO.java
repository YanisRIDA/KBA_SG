package kba.dao;

import kba.model.BankAccount;
import kba.model.Operation;
import kba.utils.OperationType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankAccountDAO {

    @Autowired
    private ModelMapper modelMapper;

    public BankAccount updateBalance(BankAccount account, long amount) {
        account.setBalance(account.getBalance()+amount);
        return account;
    }

}
