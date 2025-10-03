package com.atm.services;

import com.atm.models.*;
import com.atm.utils.DataCache;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Service for handling ATM operations like withdrawal, transfer, balance inquiry
 */
public class ATMService {
    private DataCache cache;
    private static ATMService instance;
    
    private ATMService() {
        this.cache = DataCache.getInstance();
    }
    
    public static synchronized ATMService getInstance() {
        if (instance == null) {
            instance = new ATMService();
        }
        return instance;
    }
    
    /**
     * Perform withdrawal operation
     */
    public TransactionResult withdraw(String sessionId, BigDecimal amount) {
        // Validate session
        AuthenticationService.AuthenticatedUser authUser = 
            AuthenticationService.getInstance().validateSession(sessionId);
        if (authUser == null) {
            return new TransactionResult(false, "Session expired. Please login again.", null);
        }
        
        // Validate amount
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionResult(false, "Invalid withdrawal amount", null);
        }
        
        // Check minimum withdrawal amount
        if (amount.compareTo(new BigDecimal("10")) < 0) {
            return new TransactionResult(false, "Minimum withdrawal amount is $10", null);
        }
        
        // Check maximum withdrawal amount
        if (amount.compareTo(new BigDecimal("1000")) > 0) {
            return new TransactionResult(false, "Maximum withdrawal amount is $1000 per transaction", null);
        }
        
        Account account = authUser.getAccount();
        
        // Check if account can perform withdrawal
        if (!account.canWithdraw(amount)) {
            return new TransactionResult(false, "Insufficient funds", null);
        }
        
        // Check daily limit (simplified - in real world, would check daily total)
        if (amount.compareTo(account.getDailyLimit()) > 0) {
            return new TransactionResult(false, 
                "Amount exceeds daily limit of $" + account.getDailyLimit(), null);
        }
        
        try {
            // Create transaction
            String transactionId = UUID.randomUUID().toString();
            Transaction transaction = new Transaction(
                transactionId,
                account.getAccountNumber(),
                Transaction.TransactionType.WITHDRAWAL,
                amount,
                "ATM Withdrawal"
            );
            
            // Perform withdrawal
            account.withdraw(amount);
            transaction.setBalanceAfter(account.getBalance());
            transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
            
            // Save transaction
            cache.addTransaction(transaction);
            
            return new TransactionResult(true, 
                "Withdrawal successful. Amount: $" + amount, transaction);
                
        } catch (Exception e) {
            return new TransactionResult(false, 
                "Transaction failed: " + e.getMessage(), null);
        }
    }
    
    /**
     * Perform transfer operation
     */
    public TransactionResult transfer(String sessionId, String destinationAccount, BigDecimal amount) {
        // Validate session
        AuthenticationService.AuthenticatedUser authUser = 
            AuthenticationService.getInstance().validateSession(sessionId);
        if (authUser == null) {
            return new TransactionResult(false, "Session expired. Please login again.", null);
        }
        
        // Validate amount
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionResult(false, "Invalid transfer amount", null);
        }
        
        // Check minimum transfer amount
        if (amount.compareTo(new BigDecimal("1")) < 0) {
            return new TransactionResult(false, "Minimum transfer amount is $1", null);
        }
        
        Account sourceAccount = authUser.getAccount();
        
        // Check if source account can perform transfer
        if (!sourceAccount.canWithdraw(amount)) {
            return new TransactionResult(false, "Insufficient funds", null);
        }
        
        // Get destination account
        Account destAccount = cache.getAccount(destinationAccount);
        if (destAccount == null || !destAccount.isActive()) {
            return new TransactionResult(false, "Destination account not found or inactive", null);
        }
        
        // Can't transfer to same account
        if (sourceAccount.getAccountNumber().equals(destinationAccount)) {
            return new TransactionResult(false, "Cannot transfer to the same account", null);
        }
        
        try {
            // Create transaction
            String transactionId = UUID.randomUUID().toString();
            Transaction transaction = new Transaction(
                transactionId,
                sourceAccount.getAccountNumber(),
                Transaction.TransactionType.TRANSFER,
                amount,
                "Transfer to " + destinationAccount
            );
            transaction.setDestinationAccount(destinationAccount);
            
            // Perform transfer
            sourceAccount.withdraw(amount);
            destAccount.deposit(amount);
            
            transaction.setBalanceAfter(sourceAccount.getBalance());
            transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
            
            // Save transaction
            cache.addTransaction(transaction);
            
            // Create corresponding deposit transaction for destination account
            String depositTransactionId = UUID.randomUUID().toString();
            Transaction depositTransaction = new Transaction(
                depositTransactionId,
                destinationAccount,
                Transaction.TransactionType.DEPOSIT,
                amount,
                "Transfer from " + sourceAccount.getAccountNumber()
            );
            depositTransaction.setBalanceAfter(destAccount.getBalance());
            depositTransaction.setStatus(Transaction.TransactionStatus.COMPLETED);
            cache.addTransaction(depositTransaction);
            
            return new TransactionResult(true, 
                "Transfer successful. Amount: $" + amount + " transferred to " + destinationAccount, 
                transaction);
                
        } catch (Exception e) {
            return new TransactionResult(false, 
                "Transfer failed: " + e.getMessage(), null);
        }
    }
    
    /**
     * Get account balance
     */
    public BalanceResult getBalance(String sessionId) {
        // Validate session
        AuthenticationService.AuthenticatedUser authUser = 
            AuthenticationService.getInstance().validateSession(sessionId);
        if (authUser == null) {
            return new BalanceResult(false, "Session expired. Please login again.", null);
        }
        
        Account account = authUser.getAccount();
        
        // Create balance inquiry transaction
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(
            transactionId,
            account.getAccountNumber(),
            Transaction.TransactionType.BALANCE_INQUIRY,
            BigDecimal.ZERO,
            "Balance Inquiry"
        );
        transaction.setBalanceAfter(account.getBalance());
        transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
        cache.addTransaction(transaction);
        
        return new BalanceResult(true, "Balance retrieved successfully", account.getBalance());
    }
    
    /**
     * Get transaction history
     */
    public TransactionHistoryResult getTransactionHistory(String sessionId, int limit) {
        // Validate session
        AuthenticationService.AuthenticatedUser authUser = 
            AuthenticationService.getInstance().validateSession(sessionId);
        if (authUser == null) {
            return new TransactionHistoryResult(false, "Session expired. Please login again.", null);
        }
        
        Account account = authUser.getAccount();
        List<Transaction> transactions = cache.getRecentTransactions(account.getAccountNumber(), limit);
        
        return new TransactionHistoryResult(true, "Transaction history retrieved", transactions);
    }
    
    /**
     * Change PIN
     */
    public OperationResult changePin(String sessionId, String currentPin, String newPin) {
        // Validate session
        AuthenticationService.AuthenticatedUser authUser = 
            AuthenticationService.getInstance().validateSession(sessionId);
        if (authUser == null) {
            return new OperationResult(false, "Session expired. Please login again.");
        }
        
        Card card = authUser.getCard();
        
        // Validate current PIN
        if (!card.getPin().equals(currentPin)) {
            return new OperationResult(false, "Current PIN is incorrect");
        }
        
        // Validate new PIN format
        if (newPin == null || !newPin.matches("\\d{4}")) {
            return new OperationResult(false, "New PIN must be 4 digits");
        }
        
        // Can't use same PIN
        if (currentPin.equals(newPin)) {
            return new OperationResult(false, "New PIN must be different from current PIN");
        }
        
        // Change PIN
        card.setPin(newPin);
        
        return new OperationResult(true, "PIN changed successfully");
    }
    
    // Result classes
    public static class TransactionResult {
        private boolean success;
        private String message;
        private Transaction transaction;
        
        public TransactionResult(boolean success, String message, Transaction transaction) {
            this.success = success;
            this.message = message;
            this.transaction = transaction;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public Transaction getTransaction() { return transaction; }
    }
    
    public static class BalanceResult {
        private boolean success;
        private String message;
        private BigDecimal balance;
        
        public BalanceResult(boolean success, String message, BigDecimal balance) {
            this.success = success;
            this.message = message;
            this.balance = balance;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public BigDecimal getBalance() { return balance; }
    }
    
    public static class TransactionHistoryResult {
        private boolean success;
        private String message;
        private List<Transaction> transactions;
        
        public TransactionHistoryResult(boolean success, String message, List<Transaction> transactions) {
            this.success = success;
            this.message = message;
            this.transactions = transactions;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public List<Transaction> getTransactions() { return transactions; }
    }
    
    /**
     * Create a new bank account
     */
    public AccountCreationResult createAccount(String sessionId, String firstName, String lastName, 
                                             String phoneNumber, String email, Account.AccountType accountType, 
                                             BigDecimal initialDeposit, String pin) {
        
        // Validate session for existing users, or allow guest creation
        boolean isLoggedInUser = false;
        AuthenticationService.AuthenticatedUser authUser = null;
        
        if (sessionId != null) {
            authUser = AuthenticationService.getInstance().validateSession(sessionId);
            isLoggedInUser = (authUser != null);
        }
        
        // Validate input
        if (firstName == null || firstName.trim().isEmpty()) {
            return new AccountCreationResult(false, "First name is required", null, null);
        }
        
        if (lastName == null || lastName.trim().isEmpty()) {
            return new AccountCreationResult(false, "Last name is required", null, null);
        }
        
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}|\\d{3}-\\d{3}-\\d{4}")) {
            return new AccountCreationResult(false, "Valid phone number is required (10 digits)", null, null);
        }
        
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return new AccountCreationResult(false, "Valid email address is required", null, null);
        }
        
        if (pin == null || !pin.matches("\\d{4}")) {
            return new AccountCreationResult(false, "PIN must be exactly 4 digits", null, null);
        }
        
        if (initialDeposit == null || initialDeposit.compareTo(new BigDecimal("10.00")) < 0) {
            return new AccountCreationResult(false, "Minimum initial deposit is $10.00", null, null);
        }
        
        if (accountType == null) {
            return new AccountCreationResult(false, "Account type must be selected", null, null);
        }
        
        try {
            User user;
            String userId;
            
            if (isLoggedInUser) {
                // Adding account to existing user
                user = authUser.getUser();
                userId = user.getUserId();
            } else {
                // Creating new user
                userId = "USER" + String.format("%06d", (int)(Math.random() * 1000000));
                
                // Check if email already exists
                for (User existingUser : cache.getAllUsers()) {
                    if (existingUser.getEmail().equalsIgnoreCase(email.trim())) {
                        return new AccountCreationResult(false, "An account with this email already exists", null, null);
                    }
                }
                
                user = new User(userId, firstName.trim(), lastName.trim(), 
                              phoneNumber.replaceAll("-", ""), email.trim());
                cache.addUser(user);
            }
            
            // Generate unique account number
            String accountNumber;
            do {
                accountNumber = generateAccountNumber();
            } while (cache.getAccount(accountNumber) != null);
            
            // Create new account
            Account newAccount = new Account(accountNumber, userId, accountType, initialDeposit);
            
            // Set appropriate daily limits based on account type
            switch (accountType) {
                case CHECKING:
                    newAccount.setDailyLimit(new BigDecimal("1000.00"));
                    break;
                case SAVINGS:
                    newAccount.setDailyLimit(new BigDecimal("500.00"));
                    break;
                case BUSINESS:
                    newAccount.setDailyLimit(new BigDecimal("5000.00"));
                    break;
            }
            
            cache.addAccount(newAccount);
            user.addAccount(newAccount);
            
            // Generate unique card number
            String cardNumber;
            do {
                cardNumber = generateCardNumber();
            } while (cache.getCard(cardNumber) != null);
            
            // Create card for the account
            Card newCard = new Card(cardNumber, pin, userId, accountNumber, Card.CardType.DEBIT);
            cache.addCard(newCard);
            user.addCard(newCard);
            
            // Create initial deposit transaction
            String transactionId = UUID.randomUUID().toString();
            Transaction initialTransaction = new Transaction(
                transactionId,
                accountNumber,
                Transaction.TransactionType.DEPOSIT,
                initialDeposit,
                "Initial Account Opening Deposit"
            );
            initialTransaction.setStatus(Transaction.TransactionStatus.COMPLETED);
            initialTransaction.setBalanceAfter(initialDeposit);
            cache.addTransaction(initialTransaction);
            
            return new AccountCreationResult(true, 
                "Account created successfully! Welcome to MyBanker!", 
                newAccount, newCard);
                
        } catch (Exception e) {
            return new AccountCreationResult(false, 
                "Account creation failed: " + e.getMessage(), null, null);
        }
    }
    
    private String generateAccountNumber() {
        // Generate 16-digit account number starting with 1234
        StringBuilder sb = new StringBuilder("1234");
        for (int i = 0; i < 12; i++) {
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }
    
    private String generateCardNumber() {
        // Generate 16-digit card number starting with 4
        StringBuilder sb = new StringBuilder("4");
        for (int i = 0; i < 15; i++) {
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }
    
    // Result class for account creation
    public static class AccountCreationResult {
        private boolean success;
        private String message;
        private Account account;
        private Card card;
        
        public AccountCreationResult(boolean success, String message, Account account, Card card) {
            this.success = success;
            this.message = message;
            this.account = account;
            this.card = card;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public Account getAccount() { return account; }
        public Card getCard() { return card; }
    }
    
    public static class OperationResult {
        private boolean success;
        private String message;
        
        public OperationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
    }
}