package com.atm.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank customer/user
 */
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDateTime accountCreatedDate;
    private List<Account> accounts;
    private List<Card> cards;
    
    public User(String userId, String firstName, String lastName, String phoneNumber, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountCreatedDate = LocalDateTime.now();
        this.accounts = new ArrayList<>();
        this.cards = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getFullName() { return firstName + " " + lastName; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public LocalDateTime getAccountCreatedDate() { return accountCreatedDate; }
    
    public List<Account> getAccounts() { return accounts; }
    public void addAccount(Account account) { this.accounts.add(account); }
    
    public List<Card> getCards() { return cards; }
    public void addCard(Card card) { this.cards.add(card); }
    
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}