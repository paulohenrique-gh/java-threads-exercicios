package bankingsystem.models;

import bankingsystem.models.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime localDateTime;
    private TransactionType type;
    private double amount;

    public Transaction(LocalDateTime localDateTime, TransactionType type, double amount) {
        this.localDateTime = localDateTime;
        this.type = type;
        this.amount = amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
