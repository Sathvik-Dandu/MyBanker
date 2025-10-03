package com.atm.models;

import java.time.LocalDate;

/**
 * Represents an ATM card
 */
public class Card {
    public enum CardType {
        DEBIT, CREDIT
    }
    
    public enum CardStatus {
        ACTIVE, BLOCKED, EXPIRED, CANCELLED
    }
    
    private String cardNumber;
    private String pin;
    private String userId;
    private String accountNumber;
    private CardType cardType;
    private CardStatus cardStatus;
    private LocalDate expiryDate;
    private LocalDate issuedDate;
    private int failedPinAttempts;
    private static final int MAX_PIN_ATTEMPTS = 3;
    
    public Card(String cardNumber, String pin, String userId, String accountNumber, CardType cardType) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.cardType = cardType;
        this.cardStatus = CardStatus.ACTIVE;
        this.issuedDate = LocalDate.now();
        this.expiryDate = LocalDate.now().plusYears(3); // Card valid for 3 years
        this.failedPinAttempts = 0;
    }
    
    // Business logic methods
    public boolean validatePin(String inputPin) {
        if (cardStatus != CardStatus.ACTIVE) {
            return false;
        }
        
        if (pin.equals(inputPin)) {
            failedPinAttempts = 0; // Reset attempts on successful validation
            return true;
        } else {
            failedPinAttempts++;
            if (failedPinAttempts >= MAX_PIN_ATTEMPTS) {
                cardStatus = CardStatus.BLOCKED;
            }
            return false;
        }
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    
    public boolean isUsable() {
        return cardStatus == CardStatus.ACTIVE && !isExpired();
    }
    
    public void resetFailedAttempts() {
        this.failedPinAttempts = 0;
    }
    
    public void blockCard() {
        this.cardStatus = CardStatus.BLOCKED;
    }
    
    public void unblockCard() {
        if (cardStatus == CardStatus.BLOCKED) {
            this.cardStatus = CardStatus.ACTIVE;
            this.failedPinAttempts = 0;
        }
    }
    
    // Getters and Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    
    public CardType getCardType() { return cardType; }
    public void setCardType(CardType cardType) { this.cardType = cardType; }
    
    public CardStatus getCardStatus() { return cardStatus; }
    public void setCardStatus(CardStatus cardStatus) { this.cardStatus = cardStatus; }
    
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
    public LocalDate getIssuedDate() { return issuedDate; }
    
    public int getFailedPinAttempts() { return failedPinAttempts; }
    
    // Get masked card number for display (e.g., **** **** **** 1234)
    public String getMaskedCardNumber() {
        if (cardNumber.length() < 4) return cardNumber;
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
    
    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + getMaskedCardNumber() + '\'' +
                ", cardType=" + cardType +
                ", cardStatus=" + cardStatus +
                ", expiryDate=" + expiryDate +
                '}';
    }
}