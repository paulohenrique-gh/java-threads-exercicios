package ex1_banking_system.models.enums;

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    private final String TransactionTypeName;

    TransactionType(String TransactionTypeName) {
        this.TransactionTypeName = TransactionTypeName;
    }

    @Override
    public String toString() {
        return this.TransactionTypeName;
    }
}
