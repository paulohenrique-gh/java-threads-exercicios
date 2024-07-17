package bankingsystem.utils;

import bankingsystem.models.Account;
import bankingsystem.models.Transaction;

import java.time.format.DateTimeFormatter;

public class ConsoleLogger {
    public static void logInsufficientBalance(Account account){
        System.out.println(account.getAccountNumber() + ": Insufficient funds for this transaction");
    }

    public static void logSuccessfulDeposit(Account account){
        System.out.println(account.getAccountNumber() + ": Deposit successful");
    }

    public static void logSuccessfulWithdrawal(Account account){
        System.out.println(account.getAccountNumber() + ": Withdrawal successful");
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
}
