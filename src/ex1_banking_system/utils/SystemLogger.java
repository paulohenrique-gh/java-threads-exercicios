package ex1_banking_system.utils;

import ex1_banking_system.models.Account;

import java.time.format.DateTimeFormatter;

public class SystemLogger {
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
