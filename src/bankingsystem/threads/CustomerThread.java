package bankingsystem.threads;

import bankingsystem.models.Account;

public class CustomerThread extends Thread {
    private Account account;

    public CustomerThread(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        super.run();

        // simular excução da transação em uma conta bancária específica
    }
}
