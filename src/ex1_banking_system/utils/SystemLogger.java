package ex1_banking_system.utils;

import ex1_banking_system.models.Account;

import java.time.format.DateTimeFormatter;

public class SystemLogger {
    public static void logInsufficientBalance(int accountNumber){
        System.out.println("Account " + accountNumber + ": Insufficient funds for this transaction");
    }

    // https://www.w3schools.com/java/java_date.asp
    public static void logAccountTransactions(Account account){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Account number: ")
                .append(account.getAccountNumber())
                .append(System.lineSeparator());

        account.getTransactions().forEach(transaction -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = dateTimeFormatter.format(transaction.getLocalDateTime());

            stringBuilder.append("Date and time of transaction: ")
                    .append(formattedDateTime)
                    .append(System.lineSeparator());
            stringBuilder.append("Transaction type: ")
                    .append(transaction.getType())
                    .append(System.lineSeparator());
            stringBuilder.append("Amount: ")
                    .append(transaction.getAmount())
                    .append(System.lineSeparator());
        });

        System.out.println(stringBuilder);
    }

    public static void logCurrentBalance(Account account) {
        System.out.println("Account " + account.getAccountNumber() + " current balance: " + account.getBalance());
    }

    public static void logNewDepositHeader(int accountNumber, double amount) {
        System.out.println("-> Account " + accountNumber + " New deposit of " + amount + " <-");
    }

    public static void logNewWithdrawalHeader(int accountNumber, double amount) {
        System.out.println("-> Account " + accountNumber + " New withdrawal of " + amount + " <-");
    }

    public static void logExpectedBalance(double expectedBalance) {
        System.out.println("Expected balance after transaction: " + expectedBalance);
    }
}
