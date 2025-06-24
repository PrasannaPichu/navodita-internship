package data;

import model.Account;
import util.SecurityUtil;

import java.util.HashMap;
import java.util.Map;

public class BankDatabase {
    private static Map<String, Account> accounts = new HashMap<>();

    public static void initDemoUsers() {
        Account acc1 = new Account("1001", "alice", SecurityUtil.hashPassword("pass123"), 5000);
        Account acc2 = new Account("1002", "bob", SecurityUtil.hashPassword("bob@123"), 3000);
        accounts.put(acc1.getUsername(), acc1);
        accounts.put(acc2.getUsername(), acc2);
    }

    public static Account getAccountByUsername(String username) {
        return accounts.get(username);
    }

    public static Account getAccountByNumber(String number) {
        return accounts.values().stream()
                .filter(acc -> acc.getAccountNumber().equals(number))
                .findFirst().orElse(null);
    }
}
