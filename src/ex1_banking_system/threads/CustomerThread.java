package ex1_banking_system.threads;

import ex1_banking_system.models.Account;

public class CustomerThread extends Thread {
    private Account account;
    private double amountToDeposit;
    private double amountToWithdraw;

    public CustomerThread(Account account, double amountToDeposit, double amountToWithdraw) {
        this.account = account;
        this.amountToDeposit = amountToDeposit;
        this.amountToWithdraw = amountToWithdraw;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmountToDeposit() {
        return this.amountToDeposit;
    }

    public double getAmountToWithdraw() {
        return this.amountToWithdraw;
    }

    @Override
    public void run() {
        super.run();

        int accNumber = this.getAccount().getAccountNumber();

        System.out.println(accNumber + " initial balance: " + account.getBalance());

        this.getAccount().deposit(amountToDeposit);
        this.getAccount().withdraw(amountToWithdraw);
    }
}
