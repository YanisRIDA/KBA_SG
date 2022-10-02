package kba.dao;

import kba.domain.BankAccount;
import kba.domain.Client;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BankAccountDAO {

    //Simulate database with an array list
    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount createBankAccount(Client client) {
        BankAccount bankAccount = new BankAccount(bankAccounts.size(), 0,
                client, new ArrayList<>());
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount findBankAccountById(int id) {
        //Simulate a repository "findById" from database --> returns null if not found
        try {
            return bankAccounts.get(id);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void updateBalance(BankAccount account, long balance) {
        account.setBalance(balance);
    }

}
