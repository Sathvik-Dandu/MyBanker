package com.atm.utils;

import com.atm.models.*;
import java.math.BigDecimal;

/**
 * Utility class to initialize sample data for testing
 */
public class SampleDataInitializer {
    
    public static void initializeSampleData() {
        DataCache cache = DataCache.getInstance();
        
        // Clear existing data
        cache.clearAll();
        
        // Create sample users
        User user1 = new User("USER001", "John", "Doe", "555-0123", "john.doe@email.com");
        User user2 = new User("USER002", "Jane", "Smith", "555-0456", "jane.smith@email.com");
        User user3 = new User("USER003", "Bob", "Johnson", "555-0789", "bob.johnson@email.com");
        
        cache.addUser(user1);
        cache.addUser(user2);
        cache.addUser(user3);
        
        // Create sample accounts
        Account account1 = new Account("1234567890123456", "USER001", Account.AccountType.CHECKING, new BigDecimal("2500.75"));
        Account account2 = new Account("1234567890123457", "USER001", Account.AccountType.SAVINGS, new BigDecimal("15000.00"));
        Account account3 = new Account("1234567890123458", "USER002", Account.AccountType.CHECKING, new BigDecimal("750.25"));
        Account account4 = new Account("1234567890123459", "USER003", Account.AccountType.BUSINESS, new BigDecimal("50000.00"));
        
        cache.addAccount(account1);
        cache.addAccount(account2);
        cache.addAccount(account3);
        cache.addAccount(account4);
        
        // Add accounts to users
        user1.addAccount(account1);
        user1.addAccount(account2);
        user2.addAccount(account3);
        user3.addAccount(account4);
        
        // Create sample cards
        Card card1 = new Card("1234567890123456", "1234", "USER001", "1234567890123456", Card.CardType.DEBIT);
        Card card2 = new Card("1234567890123457", "5678", "USER001", "1234567890123457", Card.CardType.DEBIT);
        Card card3 = new Card("1234567890123458", "9876", "USER002", "1234567890123458", Card.CardType.DEBIT);
        Card card4 = new Card("1234567890123459", "4321", "USER003", "1234567890123459", Card.CardType.DEBIT);
        
        // Test card with blocked status for testing
        Card blockedCard = new Card("1111111111111111", "0000", "USER001", "1234567890123456", Card.CardType.DEBIT);
        blockedCard.blockCard();
        
        cache.addCard(card1);
        cache.addCard(card2);
        cache.addCard(card3);
        cache.addCard(card4);
        cache.addCard(blockedCard);
        
        // Add cards to users
        user1.addCard(card1);
        user1.addCard(card2);
        user1.addCard(blockedCard);
        user2.addCard(card3);
        user3.addCard(card4);
        
        // Create some sample transactions for history
        createSampleTransactions(cache);
        
        System.out.println("Sample data initialized successfully!");
        System.out.println("Test Cards Available:");
        System.out.println("1. Card: 1234567890123456, PIN: 1234 (John Doe - Checking: $2,500.75)");
        System.out.println("2. Card: 1234567890123457, PIN: 5678 (John Doe - Savings: $15,000.00)");
        System.out.println("3. Card: 1234567890123458, PIN: 9876 (Jane Smith - Checking: $750.25)");
        System.out.println("4. Card: 1234567890123459, PIN: 4321 (Bob Johnson - Business: $50,000.00)");
        System.out.println("5. Card: 1111111111111111, PIN: 0000 (BLOCKED CARD - for testing)");
    }
    
    private static void createSampleTransactions(DataCache cache) {
        // Create some historical transactions for account 1234567890123456
        Transaction tx1 = new Transaction("TXN001", "1234567890123456", 
            Transaction.TransactionType.DEPOSIT, new BigDecimal("1000.00"), "Initial Deposit");
        tx1.setStatus(Transaction.TransactionStatus.COMPLETED);
        tx1.setBalanceAfter(new BigDecimal("1000.00"));
        
        Transaction tx2 = new Transaction("TXN002", "1234567890123456", 
            Transaction.TransactionType.WITHDRAWAL, new BigDecimal("200.00"), "ATM Withdrawal");
        tx2.setStatus(Transaction.TransactionStatus.COMPLETED);
        tx2.setBalanceAfter(new BigDecimal("800.00"));
        
        Transaction tx3 = new Transaction("TXN003", "1234567890123456", 
            Transaction.TransactionType.DEPOSIT, new BigDecimal("1700.75"), "Salary Deposit");
        tx3.setStatus(Transaction.TransactionStatus.COMPLETED);
        tx3.setBalanceAfter(new BigDecimal("2500.75"));
        
        cache.addTransaction(tx1);
        cache.addTransaction(tx2);
        cache.addTransaction(tx3);
        
        // Create some transactions for other accounts
        Transaction tx4 = new Transaction("TXN004", "1234567890123458", 
            Transaction.TransactionType.WITHDRAWAL, new BigDecimal("50.00"), "ATM Withdrawal");
        tx4.setStatus(Transaction.TransactionStatus.COMPLETED);
        tx4.setBalanceAfter(new BigDecimal("700.25"));
        
        Transaction tx5 = new Transaction("TXN005", "1234567890123458", 
            Transaction.TransactionType.DEPOSIT, new BigDecimal("100.00"), "Cash Deposit");
        tx5.setStatus(Transaction.TransactionStatus.COMPLETED);
        tx5.setBalanceAfter(new BigDecimal("750.25"));
        
        cache.addTransaction(tx4);
        cache.addTransaction(tx5);
    }
}