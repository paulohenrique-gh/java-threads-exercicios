package ex1_banking_system.models;

import ex1_banking_system.utils.SystemLogger;
import ex1_banking_system.services.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    // https://www.baeldung.com/java-synchronized
    // https://www.youtube.com/watch?v=HQh0Omi7k7s
    public synchronized void deposit(double amount) {
        double prevBalance = this.getBalance();

        SystemLogger.logNewDepositHeader(this.getAccountNumber(), amount);
        SystemLogger.logCurrentBalance(this);

        this.setBalance(this.getBalance() + amount);
        this.addTransaction(TransactionService.registerNewDeposit(amount));

        SystemLogger.logExpectedBalance(prevBalance + amount);
        SystemLogger.logCurrentBalance(this);
    }

    public synchronized void withdraw(double amount) {
        double prevBalance = this.getBalance();

        SystemLogger.logNewWithdrawalHeader(this.getAccountNumber(), amount);
        SystemLogger.logCurrentBalance(this);

        this.setBalance(this.getBalance() - amount);
        if (this.getBalance() < 0) {
            this.setBalance(prevBalance);
            SystemLogger.logInsufficientBalance(this.getAccountNumber());
            return;
        }

        this.addTransaction(TransactionService.registerNewWithdrawal(amount));

        SystemLogger.logExpectedBalance(prevBalance - amount);
        SystemLogger.logCurrentBalance(this);
    }
}
