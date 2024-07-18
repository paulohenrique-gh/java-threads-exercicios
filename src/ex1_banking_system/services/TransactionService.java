package ex1_banking_system.services;

import ex1_banking_system.models.Transaction;
import ex1_banking_system.models.enums.TransactionType;

import java.time.LocalDateTime;

public class TransactionService {
    public static Transaction registerNewDeposit(double amount) {
        return new Transaction(LocalDateTime.now(), TransactionType.DEPOSIT, amount);
    }

    public static Transaction registerNewWithdrawal(double amount) {
        return new Transaction(LocalDateTime.now(), TransactionType.WITHDRAWAL, amount);
    }
}
