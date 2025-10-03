package com.atm.services;

import com.atm.models.Card;
import com.atm.models.Account;
import com.atm.models.User;
import com.atm.utils.DataCache;

/**
 * Service for handling authentication and authorization
 */
public class AuthenticationService {
    private DataCache cache;
    private static AuthenticationService instance;
    
    private AuthenticationService() {
        this.cache = DataCache.getInstance();
    }
    
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }
    
    /**
     * Authenticates a user with card number and PIN
     * @param cardNumber The card number
     * @param pin The PIN
     * @return AuthenticationResult containing success status and card info
     */
    public AuthenticationResult authenticate(String cardNumber, String pin) {
        // Clean up expired sessions first
        cache.cleanExpiredSessions();
        
        // Validate input
        if (cardNumber == null || pin == null || cardNumber.trim().isEmpty() || pin.trim().isEmpty()) {
            return new AuthenticationResult(false, "Invalid card number or PIN", null);
        }
        
        // Remove spaces and validate card number format
        cardNumber = cardNumber.replaceAll("\\s+", "");
        if (!isValidCardNumber(cardNumber)) {
            return new AuthenticationResult(false, "Invalid card number format", null);
        }
        
        // Validate PIN format
        if (!isValidPin(pin)) {
            return new AuthenticationResult(false, "PIN must be 4 digits", null);
        }
        
        // Get card from cache
        Card card = cache.getCard(cardNumber);
        if (card == null) {
            return new AuthenticationResult(false, "Card not found", null);
        }
        
        // Check if card is usable
        if (!card.isUsable()) {
            if (card.isExpired()) {
                return new AuthenticationResult(false, "Card has expired", null);
            }
            if (card.getCardStatus() == Card.CardStatus.BLOCKED) {
                return new AuthenticationResult(false, "Card is blocked due to multiple failed attempts", null);
            }
            return new AuthenticationResult(false, "Card is not active", null);
        }
        
        // Validate PIN
        if (!card.validatePin(pin)) {
            int remainingAttempts = 3 - card.getFailedPinAttempts();
            if (remainingAttempts <= 0) {
                return new AuthenticationResult(false, "Card has been blocked due to multiple failed attempts", null);
            }
            return new AuthenticationResult(false, 
                "Incorrect PIN. " + remainingAttempts + " attempts remaining", null);
        }
        
        // Get associated account
        Account account = cache.getAccount(card.getAccountNumber());
        if (account == null || !account.isActive()) {
            return new AuthenticationResult(false, "Account not found or inactive", null);
        }
        
        // Get user information
        User user = cache.getUser(card.getUserId());
        if (user == null) {
            return new AuthenticationResult(false, "User not found", null);
        }
        
        // Create session
        String sessionId = generateSessionId();
        cache.createSession(sessionId, cardNumber);
        
        return new AuthenticationResult(true, "Authentication successful", 
            new AuthenticatedUser(card, account, user, sessionId));
    }
    
    /**
     * Validates session and returns authenticated user info
     */
    public AuthenticatedUser validateSession(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        
        DataCache.UserSession session = cache.getSession(sessionId);
        if (session == null || session.isExpired()) {
            if (session != null) {
                cache.removeSession(sessionId);
            }
            return null;
        }
        
        // Update last activity
        session.updateLastActivity();
        
        // Get user data
        Card card = cache.getCard(session.getCardNumber());
        Account account = cache.getAccount(card.getAccountNumber());
        User user = cache.getUser(card.getUserId());
        
        return new AuthenticatedUser(card, account, user, sessionId);
    }
    
    /**
     * Logs out user by removing session
     */
    public void logout(String sessionId) {
        if (sessionId != null) {
            cache.removeSession(sessionId);
        }
    }
    
    private boolean isValidCardNumber(String cardNumber) {
        // Card number should be 16 digits
        return cardNumber.matches("\\d{16}");
    }
    
    private boolean isValidPin(String pin) {
        // PIN should be exactly 4 digits
        return pin.matches("\\d{4}");
    }
    
    private String generateSessionId() {
        return "SESSION_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 10000);
    }
    
    // Result classes
    public static class AuthenticationResult {
        private boolean success;
        private String message;
        private AuthenticatedUser authenticatedUser;
        
        public AuthenticationResult(boolean success, String message, AuthenticatedUser authenticatedUser) {
            this.success = success;
            this.message = message;
            this.authenticatedUser = authenticatedUser;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public AuthenticatedUser getAuthenticatedUser() { return authenticatedUser; }
    }
    
    public static class AuthenticatedUser {
        private Card card;
        private Account account;
        private User user;
        private String sessionId;
        
        public AuthenticatedUser(Card card, Account account, User user, String sessionId) {
            this.card = card;
            this.account = account;
            this.user = user;
            this.sessionId = sessionId;
        }
        
        public Card getCard() { return card; }
        public Account getAccount() { return account; }
        public User getUser() { return user; }
        public String getSessionId() { return sessionId; }
    }
}