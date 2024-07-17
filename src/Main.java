import bankingsystem.models.Account;
import bankingsystem.models.Bank;
import bankingsystem.threads.CustomerThread;
import bankingsystem.utils.SystemLogger;

public class Main {
    public static void main(String[] args) {
        Account account1 = new Account(123, 500);
        Account account2 = new Account(332, 800);

        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);

        CustomerThread account1Thread1 = new CustomerThread(account1, 100, 40);
        CustomerThread account1Thread2 = new CustomerThread(account1, 48, 299);
        CustomerThread account1Thread3 = new CustomerThread(account1, 192, 92);
        CustomerThread account1Thread4 = new CustomerThread(account1, 270, 43);
        account1Thread1.start();
        account1Thread2.start();
        account1Thread3.start();
        account1Thread4.start();

        CustomerThread account2Thread1 = new CustomerThread(account2, 30, 3);
        CustomerThread account2Thread2 = new CustomerThread(account2, 400, 120);
        CustomerThread account2Thread3 = new CustomerThread(account2, 544, 88);
        CustomerThread account2Thread4 = new CustomerThread(account2, 484, 110);
        CustomerThread account2Thread5 = new CustomerThread(account2, 581, 149);
        CustomerThread account2Thread6 = new CustomerThread(account2, 469, 98);
        account2Thread1.start();
        account2Thread2.start();
        account2Thread3.start();
        account2Thread4.start();
        account2Thread5.start();
        account2Thread6.start();

        try {
            account1Thread1.join();
            account1Thread2.join();
            account1Thread3.join();
            account1Thread4.join();
            account2Thread1.join();
            account2Thread2.join();
            account2Thread3.join();
            account2Thread4.join();
            account2Thread5.join();
            account2Thread6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAccount 1 transactions: ");
        SystemLogger.logAccountTransactions(account1);
        System.out.println("Account 1 final balance - Expected: 636 | Actual: " + account1.getBalance());
        System.out.println("\nAccount 2 transactions: ");
        SystemLogger.logAccountTransactions(account2);
        System.out.println("Account 2 final balance - Expected: 2740 | Actual: " + account2.getBalance());

    }
}
