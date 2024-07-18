import bankingsystem.models.Account;
import bankingsystem.models.Bank;
import bankingsystem.threads.CustomerThread;
import bankingsystem.utils.SystemLogger;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Account account1 = new Account(123, 500);
        Account account2 = new Account(332, 800);

        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);

        List<CustomerThread> account1Threads = Arrays.asList(
            new CustomerThread(account1, 100, 40),
            new CustomerThread(account1, 48, 299),
            new CustomerThread(account1, 192, 92),
            new CustomerThread(account1, 270, 43)
        );

        List<CustomerThread> account2Threads = Arrays.asList(
            new CustomerThread(account2, 30, 3),
            new CustomerThread(account2, 400, 120),
            new CustomerThread(account2, 544, 88),
            new CustomerThread(account2, 484, 110),
            new CustomerThread(account2, 581, 149),
            new CustomerThread(account2, 469, 98)
        );

        account1Threads.forEach(Thread::start);
        account2Threads.forEach(Thread::start);

        account1Threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        account2Threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("\nAccount 1 transactions: ");
        SystemLogger.logAccountTransactions(account1);
        System.out.println("Account 1 final balance - Expected: 636 | Actual: " + account1.getBalance());
        System.out.println("\nAccount 2 transactions: ");
        SystemLogger.logAccountTransactions(account2);
        System.out.println("Account 2 final balance - Expected: 2740 | Actual: " + account2.getBalance());
    }
}
