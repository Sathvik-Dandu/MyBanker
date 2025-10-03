package com.atm;

import com.atm.gui.ATMMainFrame;
import com.atm.utils.SampleDataInitializer;

import javax.swing.*;

/**
 * Main application class for the MyBanker ATM System
 */
public class ATMApplication {
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If system look and feel is not available, use default
            System.out.println("Could not set system look and feel: " + e.getMessage());
        }
        
        // Initialize sample data
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize sample data for testing
                SampleDataInitializer.initializeSampleData();
                
                // Create and show the main ATM frame
                ATMMainFrame mainFrame = new ATMMainFrame();
                mainFrame.setVisible(true);
                
                // Show welcome message with test credentials
                showWelcomeMessage();
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Error starting ATM application: " + e.getMessage(), 
                    "Startup Error", 
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
    
    /**
     * Shows a welcome message with test credentials
     */
    private static void showWelcomeMessage() {
        String message = "Welcome to MyBanker ATM System!\n\n" +
                        "Test Credentials:\n" +
                        "==================\n" +
                        "Card: 1234567890123456\n" +
                        "PIN:  1234\n" +
                        "User: John Doe\n" +
                        "Balance: $2,500.75\n\n" +
                        "Other Test Cards:\n" +
                        "- Card: 1234567890123457, PIN: 5678 (John's Savings)\n" +
                        "- Card: 1234567890123458, PIN: 9876 (Jane Smith)\n" +
                        "- Card: 1234567890123459, PIN: 4321 (Bob Johnson)\n\n" +
                        "Features Available:\n" +
                        "- Cash Withdrawal\n" +
                        "- Fund Transfer\n" +
                        "- Balance Inquiry\n" +
                        "- Transaction History\n" +
                        "- PIN Change\n" +
                        "- Digital Receipts\n" +
                        "- Session Management\n" +
                        "- Security Features";
        
        JOptionPane.showMessageDialog(null, message, "MyBanker ATM - Welcome", JOptionPane.INFORMATION_MESSAGE);
    }
}