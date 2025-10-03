package com.atm.gui;

import com.atm.services.AuthenticationService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login screen for the ATM interface
 */
public class LoginScreen extends JPanel {
    private JTextField cardNumberField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton clearButton;
    private JButton createAccountButton;
    private JLabel statusLabel;
    private ATMMainFrame parentFrame;
    
    public LoginScreen(ATMMainFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeComponents();
        setupLayout();
        setupEventListeners();
    }
    
    private void initializeComponents() {
        setBackground(UIComponents.BACKGROUND_COLOR);
        
        // Card number field
        cardNumberField = UIComponents.createTextField("Enter 16-digit card number");
        cardNumberField.setPreferredSize(new Dimension(300, 45));
        
        // PIN field
        pinField = UIComponents.createPasswordField("Enter 4-digit PIN");
        pinField.setPreferredSize(new Dimension(300, 45));
        
        // Buttons
        loginButton = UIComponents.createPrimaryButton("LOGIN");
        clearButton = UIComponents.createSecondaryButton("CLEAR");
        createAccountButton = UIComponents.createTertiaryButton("🆕 CREATE NEW ACCOUNT");
        
        // Status label
        statusLabel = UIComponents.createBodyLabel(" ");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main content panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Create login card with enhanced design
        JPanel loginCard = UIComponents.createCard();
        loginCard.setLayout(new GridBagLayout());
        loginCard.setPreferredSize(new Dimension(450, 550));
        
        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.insets = new Insets(10, 10, 10, 10);
        
        // Bank logo/title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        
        // Create a simple bank icon using text
        JLabel iconLabel = new JLabel("🏦");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        
        JLabel titleLabel = UIComponents.createTitleLabel("MyBanker ATM");
        titleLabel.setForeground(UIComponents.PRIMARY_COLOR);
        
        titlePanel.add(iconLabel);
        titlePanel.add(Box.createHorizontalStrut(10));
        titlePanel.add(titleLabel);
        
        cardGbc.gridx = 0;
        cardGbc.gridy = 0;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        loginCard.add(titlePanel, cardGbc);
        
        // Enhanced subtitle with security info
        JPanel subtitlePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        subtitlePanel.setBackground(Color.WHITE);
        
        JLabel subtitleLabel = UIComponents.createSecondaryLabel("Please insert your card and enter PIN");
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel securityLabel = UIComponents.createSecondaryLabel("🔒 Secured with 256-bit encryption");
        securityLabel.setHorizontalAlignment(JLabel.CENTER);
        securityLabel.setFont(UIComponents.SMALL_FONT);
        
        subtitlePanel.add(subtitleLabel);
        subtitlePanel.add(securityLabel);
        
        cardGbc.gridy = 1;
        cardGbc.insets = new Insets(5, 10, 20, 10);
        loginCard.add(subtitlePanel, cardGbc);
        
        // Card number section with icon
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        
        JLabel cardIcon = new JLabel("💳");
        cardIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        cardIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        JLabel cardLabel = UIComponents.createBodyLabel("Card Number:");
        cardLabel.setFont(UIComponents.BUTTON_FONT);
        
        JPanel cardLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        cardLabelPanel.setBackground(Color.WHITE);
        cardLabelPanel.add(cardIcon);
        cardLabelPanel.add(cardLabel);
        
        cardPanel.add(cardLabelPanel, BorderLayout.WEST);
        
        cardGbc.gridy = 2;
        cardGbc.gridwidth = 1;
        cardGbc.anchor = GridBagConstraints.WEST;
        cardGbc.insets = new Insets(10, 10, 5, 10);
        loginCard.add(cardPanel, cardGbc);
        
        // Enhanced card number field with formatting
        cardNumberField = createFormattedCardField();
        cardGbc.gridy = 3;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        cardGbc.insets = new Insets(0, 10, 15, 10);
        loginCard.add(cardNumberField, cardGbc);
        
        // PIN section with icon
        JPanel pinPanel = new JPanel(new BorderLayout());
        pinPanel.setBackground(Color.WHITE);
        
        JLabel pinIcon = new JLabel("🔐");
        pinIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        pinIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        JLabel pinLabel = UIComponents.createBodyLabel("PIN:");
        pinLabel.setFont(UIComponents.BUTTON_FONT);
        
        JPanel pinLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pinLabelPanel.setBackground(Color.WHITE);
        pinLabelPanel.add(pinIcon);
        pinLabelPanel.add(pinLabel);
        
        pinPanel.add(pinLabelPanel, BorderLayout.WEST);
        
        cardGbc.gridy = 4;
        cardGbc.gridwidth = 1;
        cardGbc.anchor = GridBagConstraints.WEST;
        cardGbc.insets = new Insets(0, 10, 5, 10);
        loginCard.add(pinPanel, cardGbc);
        
        // Enhanced PIN field
        pinField = createEnhancedPinField();
        cardGbc.gridy = 5;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        cardGbc.insets = new Insets(0, 10, 20, 10);
        loginCard.add(pinField, cardGbc);
        
        // Status label with icon support
        statusLabel = UIComponents.createBodyLabel(" ");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        cardGbc.gridy = 6;
        cardGbc.insets = new Insets(0, 10, 10, 10);
        loginCard.add(statusLabel, cardGbc);
        
        // Enhanced button panel
        JPanel buttonPanel = createEnhancedButtonPanel();
        cardGbc.gridy = 7;
        cardGbc.insets = new Insets(10, 10, 10, 10);
        loginCard.add(buttonPanel, cardGbc);
        
        // Add login card to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(loginCard, gbc);
        
        // Enhanced header panel
        JPanel headerPanel = createEnhancedHeaderPanel();
        
        // Enhanced footer panel
        JPanel footerPanel = createEnhancedFooterPanel();
        
        // Add panels to main layout
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIComponents.PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel headerLabel = new JLabel("Welcome to MyBanker ATM", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        return headerPanel;
    }
    
    private JPanel createEnhancedHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIComponents.PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 100));
        
        // Main title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(UIComponents.PRIMARY_COLOR);
        
        JLabel headerLabel = new JLabel("🏧 MyBanker ATM Network", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        
        titlePanel.add(headerLabel);
        
        // Status info
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusPanel.setBackground(UIComponents.PRIMARY_COLOR);
        
        JLabel statusLabel = new JLabel("🟢 Online | 24/7 Service Available", JLabel.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(144, 238, 144)); // Light green
        
        statusPanel.add(statusLabel);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(statusPanel, BorderLayout.SOUTH);
        
        return headerPanel;
    }
    
    private JTextField createFormattedCardField() {
        JTextField field = UIComponents.createTextField("Enter 16-digit card number");
        field.setPreferredSize(new Dimension(300, 45));
        
        // Add document listener for formatting
        field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private boolean isUpdating = false;
            
            public void insertUpdate(javax.swing.event.DocumentEvent e) { formatCardNumber(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { formatCardNumber(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { formatCardNumber(); }
            
            private void formatCardNumber() {
                if (isUpdating) return;
                
                SwingUtilities.invokeLater(() -> {
                    isUpdating = true;
                    String text = field.getText().replaceAll("\\D", ""); // Remove non-digits
                    if (text.length() > 16) text = text.substring(0, 16);
                    
                    // Format as XXXX XXXX XXXX XXXX
                    StringBuilder formatted = new StringBuilder();
                    for (int i = 0; i < text.length(); i++) {
                        if (i > 0 && i % 4 == 0) formatted.append(" ");
                        formatted.append(text.charAt(i));
                    }
                    
                    int caretPos = field.getCaretPosition();
                    field.setText(formatted.toString());
                    
                    // Adjust caret position
                    try {
                        field.setCaretPosition(Math.min(caretPos, formatted.length()));
                    } catch (Exception ex) { /* ignore */ }
                    
                    isUpdating = false;
                });
            }
        });
        
        return field;
    }
    
    private JPasswordField createEnhancedPinField() {
        JPasswordField field = UIComponents.createPasswordField("Enter 4-digit PIN");
        field.setPreferredSize(new Dimension(300, 45));
        
        // Limit to 4 digits
        field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { limitLength(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { limitLength(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { limitLength(); }
            
            private void limitLength() {
                SwingUtilities.invokeLater(() -> {
                    String text = new String(field.getPassword());
                    if (text.length() > 4) {
                        field.setText(text.substring(0, 4));
                    }
                });
            }
        });
        
        return field;
    }
    
    private JPanel createEnhancedButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Enhanced clear button
        clearButton = UIComponents.createSecondaryButton("🗑️ CLEAR");
        clearButton.setToolTipText("Clear all fields");
        
        // Enhanced login button
        loginButton = UIComponents.createPrimaryButton("🔓 LOGIN");
        loginButton.setToolTipText("Login to your account");
        
        buttonPanel.add(clearButton);
        buttonPanel.add(loginButton);
        
        return buttonPanel;
    }
    
    private JPanel createEnhancedFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(UIComponents.TEXT_SECONDARY);
        footerPanel.setPreferredSize(new Dimension(0, 100));
        
        // Main footer text
        JPanel mainFooterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainFooterPanel.setBackground(UIComponents.TEXT_SECONDARY);
        
        JLabel footerLabel = new JLabel("24/7 Banking Services | Secure & Reliable");
        footerLabel.setFont(UIComponents.SMALL_FONT);
        footerLabel.setForeground(Color.WHITE);
        
        mainFooterPanel.add(footerLabel);
        
        // New customer section
        JPanel newCustomerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        newCustomerPanel.setBackground(UIComponents.TEXT_SECONDARY);
        
        JLabel newCustomerLabel = new JLabel("New Customer?");
        newCustomerLabel.setFont(UIComponents.BODY_FONT);
        newCustomerLabel.setForeground(Color.WHITE);
        
        createAccountButton.setPreferredSize(new Dimension(200, 35));
        
        newCustomerPanel.add(newCustomerLabel);
        newCustomerPanel.add(createAccountButton);
        
        // Additional info panel
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        infoPanel.setBackground(UIComponents.TEXT_SECONDARY);
        
        JLabel infoLabel = new JLabel("📞 Customer Service: 1-800-MYBANK | 🌐 www.mybanker.com");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        infoLabel.setForeground(new Color(200, 200, 200));
        
        infoPanel.add(infoLabel);
        
        footerPanel.add(mainFooterPanel, BorderLayout.NORTH);
        footerPanel.add(newCustomerPanel, BorderLayout.CENTER);
        footerPanel.add(infoPanel, BorderLayout.SOUTH);
        
        return footerPanel;
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(UIComponents.TEXT_SECONDARY);
        footerPanel.setPreferredSize(new Dimension(0, 50));
        
        JLabel footerLabel = new JLabel("24/7 Banking Services | Secure & Reliable");
        footerLabel.setFont(UIComponents.SMALL_FONT);
        footerLabel.setForeground(Color.WHITE);
        
        footerPanel.add(footerLabel);
        return footerPanel;
    }
    
    private void setupEventListeners() {
        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        
        // Clear button action
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        
        // Enter key in PIN field
        pinField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        
        // Enter key in card number field
        cardNumberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pinField.requestFocus();
            }
        });
        
        // Create account button action
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showAccountCreationScreen(null); // null = guest/unauthenticated user
            }
        });
    }
    
    private void performLogin() {
        String cardNumber = cardNumberField.getText().trim();
        String pin = new String(pinField.getPassword());
        
        // Clear previous status
        statusLabel.setText(" ");
        
        // Validate input
        if (cardNumber.isEmpty() || cardNumber.equals("Enter 16-digit card number")) {
            setStatusMessage("Please enter your card number", false);
            cardNumberField.requestFocus();
            return;
        }
        
        if (pin.isEmpty()) {
            setStatusMessage("Please enter your PIN", false);
            pinField.requestFocus();
            return;
        }
        
        // Disable login button during authentication
        loginButton.setEnabled(false);
        loginButton.setText("AUTHENTICATING...");
        
        // Perform authentication in a separate thread to avoid blocking UI
        SwingUtilities.invokeLater(() -> {
            try {
                AuthenticationService authService = AuthenticationService.getInstance();
                AuthenticationService.AuthenticationResult result = authService.authenticate(cardNumber, pin);
                
                if (result.isSuccess()) {
                    setStatusMessage("Login successful!", true);
                    // Switch to main ATM interface
                    parentFrame.showMainInterface(result.getAuthenticatedUser());
                } else {
                    setStatusMessage(result.getMessage(), false);
                    pinField.setText("");
                }
            } catch (Exception ex) {
                setStatusMessage("System error. Please try again.", false);
            } finally {
                loginButton.setEnabled(true);
                loginButton.setText("LOGIN");
            }
        });
    }
    
    private void clearFields() {
        cardNumberField.setText("Enter 16-digit card number");
        cardNumberField.setForeground(UIComponents.TEXT_SECONDARY);
        pinField.setText("");
        statusLabel.setText(" ");
        cardNumberField.requestFocus();
    }
    
    private void setStatusMessage(String message, boolean isSuccess) {
        String icon = isSuccess ? "✅ " : "❌ ";
        statusLabel.setText(icon + message);
        statusLabel.setForeground(isSuccess ? UIComponents.SUCCESS_COLOR : UIComponents.ERROR_COLOR);
        
        // Add fade effect simulation
        if (isSuccess) {
            Timer timer = new Timer(3000, e -> {
                statusLabel.setText(" ");
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    
    // Method to reset the login screen when returning from main interface
    public void resetScreen() {
        clearFields();
    }
}