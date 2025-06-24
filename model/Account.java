package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String username;
    private String hashedPassword;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, String username, String hashedPassword, double initialBalance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() { return accountNumber; }
    public String getUsername() { return username; }
    public String getHashedPassword() { return hashedPassword; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction tx) {
        transactions.add(tx);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
