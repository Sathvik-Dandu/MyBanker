package com.atm.utils;

import com.atm.models.Transaction;
import com.atm.models.Account;
import com.atm.models.User;
import com.atm.services.AuthenticationService;
import com.atm.gui.UIComponents;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for generating digital receipts
 */
public class ReceiptGenerator {
    
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");
    
    /**
     * Shows a digital receipt for a transaction
     */
    public static void showTransactionReceipt(Component parent, Transaction transaction, 
                                            AuthenticationService.AuthenticatedUser authUser) {
        
        JDialog receiptDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), 
                                          "Transaction Receipt", true);
        receiptDialog.setSize(400, 500);
        receiptDialog.setLocationRelativeTo(parent);
        
        JPanel receiptPanel = createReceiptPanel(transaction, authUser);
        
        JScrollPane scrollPane = new JScrollPane(receiptPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton printButton = UIComponents.createSecondaryButton("PRINT");
        JButton closeButton = UIComponents.createPrimaryButton("CLOSE");
        
        printButton.addActionListener(e -> printReceipt(receiptPanel));
        closeButton.addActionListener(e -> receiptDialog.dispose());
        
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);
        
        receiptDialog.setLayout(new BorderLayout());
        receiptDialog.add(scrollPane, BorderLayout.CENTER);
        receiptDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        receiptDialog.setVisible(true);
    }
    
    /**
     * Creates the receipt panel with transaction details
     */
    private static JPanel createReceiptPanel(Transaction transaction, 
                                           AuthenticationService.AuthenticatedUser authUser) {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        addReceiptHeader(panel);
        
        // Transaction details
        addTransactionDetails(panel, transaction, authUser);
        
        // Footer
        addReceiptFooter(panel);
        
        return panel;
    }
    
    private static void addReceiptHeader(JPanel panel) {
        // Bank name
        JLabel bankLabel = new JLabel("MyBanker ATM", JLabel.CENTER);
        bankLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        bankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(bankLabel);
        
        // Separator
        panel.add(Box.createVerticalStrut(5));
        JLabel separatorLabel = new JLabel("================================", JLabel.CENTER);
        separatorLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
        separatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(separatorLabel);
        
        panel.add(Box.createVerticalStrut(10));
        
        // Receipt title
        JLabel titleLabel = new JLabel("TRANSACTION RECEIPT", JLabel.CENTER);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        panel.add(Box.createVerticalStrut(15));
    }
    
    private static void addTransactionDetails(JPanel panel, Transaction transaction, 
                                            AuthenticationService.AuthenticatedUser authUser) {
        
        Font labelFont = new Font("Courier New", Font.PLAIN, 12);
        
        // Transaction information
        addReceiptLine(panel, "Transaction ID:", transaction.getReferenceNumber(), labelFont);
        addReceiptLine(panel, "Date/Time:", transaction.getTimestamp().format(dateTimeFormatter), labelFont);
        addReceiptLine(panel, "Transaction Type:", formatTransactionType(transaction.getType()), labelFont);
        
        panel.add(Box.createVerticalStrut(10));
        
        // Account information
        Account account = authUser.getAccount();
        User user = authUser.getUser();
        
        addReceiptLine(panel, "Account Holder:", user.getFullName(), labelFont);
        addReceiptLine(panel, "Account Number:", maskAccountNumber(account.getAccountNumber()), labelFont);
        addReceiptLine(panel, "Account Type:", account.getAccountType().toString(), labelFont);
        
        panel.add(Box.createVerticalStrut(10));
        
        // Transaction amount
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            addReceiptLine(panel, "Amount:", currencyFormat.format(transaction.getAmount()), labelFont);
        }
        
        // Destination account for transfers
        if (transaction.getDestinationAccount() != null) {
            addReceiptLine(panel, "To Account:", maskAccountNumber(transaction.getDestinationAccount()), labelFont);
        }
        
        // Balance after transaction
        if (transaction.getBalanceAfter() != null) {
            addReceiptLine(panel, "Available Balance:", currencyFormat.format(transaction.getBalanceAfter()), labelFont);
        }
        
        panel.add(Box.createVerticalStrut(10));
        
        // Transaction status
        addReceiptLine(panel, "Status:", transaction.getStatus().toString(), labelFont);
        
        // Description
        if (transaction.getDescription() != null && !transaction.getDescription().isEmpty()) {
            addReceiptLine(panel, "Description:", transaction.getDescription(), labelFont);
        }
    }
    
    private static void addReceiptFooter(JPanel panel) {
        panel.add(Box.createVerticalStrut(15));
        
        Font footerFont = new Font("Courier New", Font.PLAIN, 10);
        
        // Separator
        JLabel separatorLabel = new JLabel("================================", JLabel.CENTER);
        separatorLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
        separatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(separatorLabel);
        
        panel.add(Box.createVerticalStrut(10));
        
        // Footer messages
        String[] footerMessages = {
            "Thank you for banking with MyBanker",
            "Keep this receipt for your records",
            "24/7 Customer Service: 1-800-MYBANK",
            "",
            "This is a computer generated receipt"
        };
        
        for (String message : footerMessages) {
            JLabel footerLabel = new JLabel(message, JLabel.CENTER);
            footerLabel.setFont(footerFont);
            footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(footerLabel);
            panel.add(Box.createVerticalStrut(2));
        }
    }
    
    private static void addReceiptLine(JPanel panel, String label, String value, Font font) {
        JPanel linePanel = new JPanel(new BorderLayout());
        linePanel.setBackground(Color.WHITE);
        linePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        
        JLabel labelLabel = new JLabel(label);
        labelLabel.setFont(font);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(font);
        valueLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        linePanel.add(labelLabel, BorderLayout.WEST);
        linePanel.add(valueLabel, BorderLayout.EAST);
        
        panel.add(linePanel);
        panel.add(Box.createVerticalStrut(2));
    }
    
    private static String formatTransactionType(Transaction.TransactionType type) {
        switch (type) {
            case WITHDRAWAL:
                return "Cash Withdrawal";
            case DEPOSIT:
                return "Cash Deposit";
            case TRANSFER:
                return "Fund Transfer";
            case BALANCE_INQUIRY:
                return "Balance Inquiry";
            default:
                return type.toString();
        }
    }
    
    private static String maskAccountNumber(String accountNumber) {
        if (accountNumber.length() < 4) {
            return accountNumber;
        }
        return "****-****-****-" + accountNumber.substring(accountNumber.length() - 4);
    }
    
    /**
     * Simulates printing the receipt (in a real application, this would interface with a printer)
     */
    private static void printReceipt(JPanel receiptPanel) {
        // In a real application, you would interface with a printer here
        // For this demo, we'll show a message dialog
        UIComponents.showSuccessMessage(receiptPanel, "Print Receipt", 
            "Receipt has been sent to printer.\n" +
            "Please collect your receipt from the printer slot.\n\n" +
            "Note: This is a simulation - no actual printing performed.");
    }
    
    /**
     * Shows a balance inquiry receipt
     */
    public static void showBalanceReceipt(Component parent, BigDecimal balance, 
                                        AuthenticationService.AuthenticatedUser authUser) {
        
        // Create a balance inquiry transaction for the receipt
        Transaction balanceTransaction = new Transaction(
            "BAL-" + System.currentTimeMillis(),
            authUser.getAccount().getAccountNumber(),
            Transaction.TransactionType.BALANCE_INQUIRY,
            BigDecimal.ZERO,
            "Balance Inquiry"
        );
        balanceTransaction.setStatus(Transaction.TransactionStatus.COMPLETED);
        balanceTransaction.setBalanceAfter(balance);
        
        showTransactionReceipt(parent, balanceTransaction, authUser);
    }
}