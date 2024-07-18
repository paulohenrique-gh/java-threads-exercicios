package ex1_banking_system.models;

import ex1_banking_system.utils.SystemLogger;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<Integer, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account) {

    }

    public Map<Integer, Account> getAccounts() {
        return this.accounts;
    }

    public void printAllTransactions() {
        this.accounts.values().forEach(SystemLogger::logAccountTransactions);
    }
}
