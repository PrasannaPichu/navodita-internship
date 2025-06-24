package service;

import data.BankDatabase;
import model.Account;
import util.SecurityUtil;

public class BankService {
    private Account loggedInUser;

    public boolean login(String username, String password) {
        Account acc = BankDatabase.getAccountByUsername(username);
        if (acc != null && acc.getHashedPassword().equals(SecurityUtil.hashPassword(password))) {
            loggedInUser = acc;
            return true;
        }
        return false;
    }

    public Account getLoggedInAccount() {
        return loggedInUser;
    }

    public boolean transferFunds(String toAccountNumber, double amount) {
        Account toAccount = BankDatabase.getAccountByNumber(toAccountNumber);
        if (toAccount != null && loggedInUser.withdraw(amount)) {
            toAccount.deposit(amount);
            loggedInUser.addTransaction(new model.Transaction("Transfer to " + toAccountNumber, amount));
            toAccount.addTransaction(new model.Transaction("Transfer from " + loggedInUser.getAccountNumber(), amount));
            return true;
        }
        return false;
    }
}
