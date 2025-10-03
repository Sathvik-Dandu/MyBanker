package com.atm.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account
 */
public class Account {
    public enum AccountType {
        CHECKING, SAVINGS, BUSINESS
    }
    
    private String accountNumber;
    private String userId;
    private AccountType accountType;
    private BigDecimal balance;
    private BigDecimal dailyLimit;
    private LocalDateTime createdDate;
    private boolean isActive;
    private List<Transaction> transactions;
    
    public Account(String accountNumber, String userId, AccountType accountType, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.dailyLimit = new BigDecimal("1000.00"); // Default daily limit
        this.createdDate = LocalDateTime.now();
        this.isActive = true;
        this.transactions = new ArrayList<>();
    }
    
    // Business logic methods
    public boolean canWithdraw(BigDecimal amount) {
        return isActive && balance.compareTo(amount) >= 0;
    }
    
    public void withdraw(BigDecimal amount) {
        if (canWithdraw(amount)) {
            balance = balance.subtract(amount);
        } else {
            throw new IllegalArgumentException("Insufficient funds or inactive account");
        }
    }
    
    public void deposit(BigDecimal amount) {
        if (!isActive) {
            throw new IllegalArgumentException("Account is inactive");
        }
        balance = balance.add(amount);
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    
    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
    
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    
    public BigDecimal getDailyLimit() { return dailyLimit; }
    public void setDailyLimit(BigDecimal dailyLimit) { this.dailyLimit = dailyLimit; }
    
    public LocalDateTime getCreatedDate() { return createdDate; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    public List<Transaction> getTransactions() { return new ArrayList<>(transactions); }
    
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", isActive=" + isActive +
                '}';
    }
}