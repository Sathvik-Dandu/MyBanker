package com.atm.gui;

import com.atm.services.AuthenticationService;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Main frame for the ATM application
 */
public class ATMMainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginScreen loginScreen;
    private MainATMScreen mainATMScreen;
    private AccountCreationScreen accountCreationScreen;
    
    public ATMMainFrame() {
        initializeFrame();
        initializeComponents();
        setupLayout();
        
        // Show login screen initially
        showLoginScreen();
    }
    
    private void initializeFrame() {
        setTitle("MyBanker ATM System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Start maximized
        
        // Set application icon (you can add an icon file if available)
        try {
            setIconImage(createATMIcon());
        } catch (Exception e) {
            // Icon creation failed, continue without icon
        }
    }
    
    private void initializeComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Initialize screens
        loginScreen = new LoginScreen(this);
        
        // Add screens to main panel
        mainPanel.add(loginScreen, "LOGIN");
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }
    
    /**
     * Shows the login screen
     */
    public void showLoginScreen() {
        if (mainATMScreen != null) {
            mainPanel.remove(mainATMScreen);
            mainATMScreen = null;
        }
        
        loginScreen.resetScreen();
        cardLayout.show(mainPanel, "LOGIN");
        setTitle("MyBanker ATM System - Login");
    }
    
    /**
     * Shows the main ATM interface
     */
    public void showMainInterface(AuthenticationService.AuthenticatedUser authenticatedUser) {
        // Create new main ATM screen
        mainATMScreen = new MainATMScreen(this, authenticatedUser);
        mainPanel.add(mainATMScreen, "MAIN");
        
        // Switch to main interface
        cardLayout.show(mainPanel, "MAIN");
        setTitle("MyBanker ATM System - " + authenticatedUser.getUser().getFullName());
    }
    
    /**
     * Shows the account creation screen
     */
    public void showAccountCreationScreen(String sessionId) {
        // Create new account creation screen
        accountCreationScreen = new AccountCreationScreen(this, sessionId);
        mainPanel.add(accountCreationScreen, "ACCOUNT_CREATION");
        
        // Switch to account creation screen
        cardLayout.show(mainPanel, "ACCOUNT_CREATION");
        setTitle("MyBanker ATM System - Create Account");
    }
    
    /**
     * Creates a simple ATM icon for the application
     */
    private Image createATMIcon() {
        // Create a simple 32x32 icon
        Image icon = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) icon.getGraphics();
        
        // Enable antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw ATM machine representation
        g2d.setColor(UIComponents.PRIMARY_COLOR);
        g2d.fillRoundRect(4, 2, 24, 28, 4, 4);
        
        // Screen
        g2d.setColor(Color.BLACK);
        g2d.fillRect(8, 6, 16, 10);
        
        // Screen content
        g2d.setColor(UIComponents.ACCENT_COLOR);
        g2d.fillRect(9, 7, 14, 8);
        
        // Keypad
        g2d.setColor(Color.LIGHT_GRAY);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                g2d.fillRoundRect(8 + col * 5, 18 + row * 4, 3, 3, 1, 1);
            }
        }
        
        // Card slot
        g2d.setColor(Color.BLACK);
        g2d.fillRect(6, 26, 20, 2);
        
        g2d.dispose();
        return icon;
    }
}