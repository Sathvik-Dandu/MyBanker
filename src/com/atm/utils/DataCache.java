package com.atm.utils;

import com.atm.models.Account;
import com.atm.models.Card;
import com.atm.models.User;
import com.atm.models.Transaction;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * In-memory cache for storing temporary data and managing user sessions
 */
public class DataCache {
    private static DataCache instance;
    
    // Data storage
    private Map<String, User> users;
    private Map<String, Account> accounts;
    private Map<String, Card> cards;
    private Map<String, Transaction> transactions;
    
    // Session management
    private Map<String, UserSession> activeSessions;
    
    private DataCache() {
        users = new ConcurrentHashMap<>();
        accounts = new ConcurrentHashMap<>();
        cards = new ConcurrentHashMap<>();
        transactions = new ConcurrentHashMap<>();
        activeSessions = new ConcurrentHashMap<>();
    }
    
    public static synchronized DataCache getInstance() {
        if (instance == null) {
            instance = new DataCache();
        }
        return instance;
    }
    
    // User operations
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }
    
    public User getUser(String userId) {
        return users.get(userId);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    // Account operations
    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
    
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    public List<Account> getAccountsByUserId(String userId) {
        return accounts.values().stream()
                .filter(account -> account.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    
    // Card operations
    public void addCard(Card card) {
        cards.put(card.getCardNumber(), card);
    }
    
    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }
    
    public List<Card> getCardsByUserId(String userId) {
        return cards.values().stream()
                .filter(card -> card.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    
    // Transaction operations
    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
        
        // Also add to account's transaction history
        Account account = getAccount(transaction.getAccountNumber());
        if (account != null) {
            account.addTransaction(transaction);
        }
    }
    
    public Transaction getTransaction(String transactionId) {
        return transactions.get(transactionId);
    }
    
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return transactions.values().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList());
    }
    
    public List<Transaction> getRecentTransactions(String accountNumber, int limit) {
        return transactions.values().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    // Session management
    public void createSession(String sessionId, String cardNumber) {
        UserSession session = new UserSession(sessionId, cardNumber, LocalDateTime.now());
        activeSessions.put(sessionId, session);
    }
    
    public UserSession getSession(String sessionId) {
        return activeSessions.get(sessionId);
    }
    
    public void removeSession(String sessionId) {
        activeSessions.remove(sessionId);
    }
    
    public void cleanExpiredSessions() {
        LocalDateTime now = LocalDateTime.now();
        activeSessions.entrySet().removeIf(entry -> 
            entry.getValue().isExpired(now));
    }
    
    // Clear all data (for testing purposes)
    public void clearAll() {
        users.clear();
        accounts.clear();
        cards.clear();
        transactions.clear();
        activeSessions.clear();
    }
    
    // Inner class for session management
    public static class UserSession {
        private String sessionId;
        private String cardNumber;
        private LocalDateTime loginTime;
        private LocalDateTime lastActivity;
        private static final int SESSION_TIMEOUT_MINUTES = 5;
        
        public UserSession(String sessionId, String cardNumber, LocalDateTime loginTime) {
            this.sessionId = sessionId;
            this.cardNumber = cardNumber;
            this.loginTime = loginTime;
            this.lastActivity = loginTime;
        }
        
        public void updateLastActivity() {
            this.lastActivity = LocalDateTime.now();
        }
        
        public boolean isExpired(LocalDateTime currentTime) {
            return lastActivity.plusMinutes(SESSION_TIMEOUT_MINUTES).isBefore(currentTime);
        }
        
        public boolean isExpired() {
            return isExpired(LocalDateTime.now());
        }
        
        // Getters
        public String getSessionId() { return sessionId; }
        public String getCardNumber() { return cardNumber; }
        public LocalDateTime getLoginTime() { return loginTime; }
        public LocalDateTime getLastActivity() { return lastActivity; }
        public int getSessionTimeoutMinutes() { return SESSION_TIMEOUT_MINUTES; }
    }
}