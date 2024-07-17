package bankingsystem.services;

import bankingsystem.models.Transaction;
import bankingsystem.models.enums.TransactionType;

import java.time.LocalDateTime;

public class TransactionService {
    public static Transaction registerNewDeposit(double amount) {
        return new Transaction(LocalDateTime.now(), TransactionType.DEPOSIT, amount);
    }

    public static Transaction registerNewWithdrawal(double amount) {
        return new Transaction(LocalDateTime.now(), TransactionType.WITHDRAWAL, amount);
    }
}
