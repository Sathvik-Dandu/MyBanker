package com.atm.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a transaction
 */
public class Transaction {
    public enum TransactionType {
        WITHDRAWAL, DEPOSIT, TRANSFER, BALANCE_INQUIRY
    }
    
    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }
    
    private String transactionId;
    private String accountNumber;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private LocalDateTime timestamp;
    private TransactionStatus status;
    private String destinationAccount; // For transfers
    private BigDecimal balanceAfter;
    private String referenceNumber;
    
    public Transaction(String transactionId, String accountNumber, TransactionType type, 
                      BigDecimal amount, String description) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        this.referenceNumber = generateReferenceNumber();
    }
    
    // Generate a reference number for the transaction
    private String generateReferenceNumber() {
        return "REF" + System.currentTimeMillis();
    }
    
    // Getters and Setters
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    
    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    
    public String getDestinationAccount() { return destinationAccount; }
    public void setDestinationAccount(String destinationAccount) { this.destinationAccount = destinationAccount; }
    
    public BigDecimal getBalanceAfter() { return balanceAfter; }
    public void setBalanceAfter(BigDecimal balanceAfter) { this.balanceAfter = balanceAfter; }
    
    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}