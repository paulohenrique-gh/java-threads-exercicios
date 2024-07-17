package bankingsystem.models;

import bankingsystem.utils.ConsoleLogger;
import bankingsystem.services.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
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

    public void setBalance(double balance) {
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
    public void deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
        this.addTransaction(TransactionService.registerNewDeposit(amount));
        ConsoleLogger.logSuccessfulDeposit(this);
    }

    public void withdraw(double amount) {
        double previousBalance = this.getBalance();
        this.setBalance(this.getBalance() - amount);
        if (this.getBalance() < 0) {
            this.setBalance(previousBalance);
            ConsoleLogger.logInsufficientBalance(this);
            return;
        }

        this.addTransaction(TransactionService.registerNewWithdrawal(amount));
        ConsoleLogger.logSuccessfulWithdrawal(this);
    }
}
