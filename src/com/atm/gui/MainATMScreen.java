package com.atm.gui;

import com.atm.services.AuthenticationService;
import com.atm.services.ATMService;
import com.atm.models.Transaction;
import com.atm.utils.ReceiptGenerator;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

/**
 * Main ATM interface screen
 */
public class MainATMScreen extends JPanel {
    private AuthenticationService.AuthenticatedUser authenticatedUser;
    private ATMMainFrame parentFrame;
    private JLabel welcomeLabel;
    private JLabel balanceLabel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Panels for different screens
    private JPanel menuPanel;
    private JPanel withdrawalPanel;
    private JPanel transferPanel;
    private JPanel historyPanel;
    private JPanel changePinPanel;
    
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    
    public MainATMScreen(ATMMainFrame parentFrame, AuthenticationService.AuthenticatedUser authenticatedUser) {
        this.parentFrame = parentFrame;
        this.authenticatedUser = authenticatedUser;
        initializeComponents();
        setupLayout();
        loadBalance();
    }
    
    private void initializeComponents() {
        setBackground(UIComponents.BACKGROUND_COLOR);
        
        // Welcome label
        welcomeLabel = UIComponents.createSubtitleLabel("Welcome, " + authenticatedUser.getUser().getFirstName());
        
        // Balance label
        balanceLabel = UIComponents.createBodyLabel("Balance: Loading...");
        
        // Content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        
        // Initialize different panels
        initializeMenuPanel();
        initializeWithdrawalPanel();
        initializeTransferPanel();
        initializeHistoryPanel();
        initializeChangePinPanel();
        
        // Add panels to content panel
        contentPanel.add(menuPanel, "MENU");
        contentPanel.add(withdrawalPanel, "WITHDRAWAL");
        contentPanel.add(transferPanel, "TRANSFER");
        contentPanel.add(historyPanel, "HISTORY");
        contentPanel.add(changePinPanel, "CHANGE_PIN");
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        
        // Main content
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        
        // Show menu by default
        cardLayout.show(contentPanel, "MENU");
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIComponents.PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 120));
        
        // Left side - Welcome and account info
        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        leftPanel.setBackground(UIComponents.PRIMARY_COLOR);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        welcomeLabel.setForeground(Color.WHITE);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(UIComponents.SUBTITLE_FONT);
        
        // Add account type and card info
        String cardNumber = authenticatedUser.getCard().getMaskedCardNumber();
        String accountType = authenticatedUser.getAccount().getAccountType().toString();
        JLabel accountInfoLabel = UIComponents.createBodyLabel("💳 " + cardNumber + " | " + accountType + " Account");
        accountInfoLabel.setForeground(new Color(200, 230, 255)); // Light blue
        
        leftPanel.add(welcomeLabel);
        leftPanel.add(balanceLabel);
        leftPanel.add(accountInfoLabel);
        
        // Center - Current time and date
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBackground(UIComponents.PRIMARY_COLOR);
        
        JLabel timeLabel = new JLabel("🕐 " + getCurrentTime(), JLabel.CENTER);
        timeLabel.setFont(UIComponents.BODY_FONT);
        timeLabel.setForeground(Color.WHITE);
        
        JLabel dateLabel = new JLabel("📅 " + getCurrentDate(), JLabel.CENTER);
        dateLabel.setFont(UIComponents.SMALL_FONT);
        dateLabel.setForeground(new Color(200, 230, 255));
        
        centerPanel.add(timeLabel);
        centerPanel.add(dateLabel);
        
        // Right side - Session info and logout
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.setBackground(UIComponents.PRIMARY_COLOR);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        // Session timeout indicator
        JLabel sessionLabel = new JLabel("⏱️ Session: 5:00", JLabel.RIGHT);
        sessionLabel.setFont(UIComponents.SMALL_FONT);
        sessionLabel.setForeground(new Color(255, 255, 140)); // Light yellow
        
        JButton logoutButton = UIComponents.createDangerButton("🚪 LOGOUT");
        logoutButton.addActionListener(e -> logout());
        
        rightPanel.add(sessionLabel);
        rightPanel.add(logoutButton);
        
        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(centerPanel, BorderLayout.CENTER);
        headerPanel.add(rightPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private void initializeMenuPanel() {
        menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        
        // Create enhanced menu card
        JPanel menuCard = UIComponents.createCard();
        menuCard.setLayout(new BorderLayout());
        menuCard.setPreferredSize(new Dimension(700, 500));
        
        // Menu title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel titleLabel = UIComponents.createSubtitleLabel("🏧 Select Transaction");
        titleLabel.setForeground(UIComponents.PRIMARY_COLOR);
        titlePanel.add(titleLabel);
        
        // Enhanced menu grid - changed to accommodate 7 buttons
        JPanel menuGrid = new JPanel(new GridLayout(4, 2, 25, 25));
        menuGrid.setBackground(Color.WHITE);
        menuGrid.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        
        // Enhanced menu buttons with descriptions
        JButton withdrawButton = createMenuButton("💰 CASH WITHDRAWAL", "Withdraw money from your account", UIComponents.PRIMARY_COLOR);
        withdrawButton.addActionListener(e -> showWithdrawalPanel());
        
        JButton transferButton = createMenuButton("💸 FUND TRANSFER", "Transfer money to another account", UIComponents.PRIMARY_COLOR);
        transferButton.addActionListener(e -> showTransferPanel());
        
        JButton balanceButton = createMenuButton("💳 BALANCE INQUIRY", "Check your account balance", UIComponents.SECONDARY_COLOR);
        balanceButton.addActionListener(e -> showBalance());
        
        JButton historyButton = createMenuButton("📋 MINI STATEMENT", "View recent transactions", UIComponents.SECONDARY_COLOR);
        historyButton.addActionListener(e -> showHistoryPanel());
        
        JButton changePinButton = createMenuButton("🔐 CHANGE PIN", "Update your security PIN", UIComponents.WARNING_COLOR);
        changePinButton.addActionListener(e -> showChangePinPanel());
        
        JButton createAccountButton = createMenuButton("🏦 CREATE ACCOUNT", "Open a new bank account", UIComponents.SUCCESS_COLOR);
        createAccountButton.addActionListener(e -> openNewAccount());
        
        JButton exitButton = createMenuButton("🚪 EXIT", "End session and logout", UIComponents.ERROR_COLOR);
        exitButton.addActionListener(e -> logout());
        
        menuGrid.add(withdrawButton);
        menuGrid.add(transferButton);
        menuGrid.add(balanceButton);
        menuGrid.add(historyButton);
        menuGrid.add(changePinButton);
        menuGrid.add(createAccountButton);
        menuGrid.add(exitButton);
        
        // Add empty panel for spacing
        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(Color.WHITE);
        menuGrid.add(spacerPanel);
        
        // Quick access panel
        JPanel quickAccessPanel = createQuickAccessPanel();
        
        menuCard.add(titlePanel, BorderLayout.NORTH);
        menuCard.add(menuGrid, BorderLayout.CENTER);
        menuCard.add(quickAccessPanel, BorderLayout.SOUTH);
        
        menuPanel.add(menuCard, gbc);
    }
    
    private JButton createMenuButton(String title, String description, Color backgroundColor) {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(backgroundColor.darker(), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        buttonPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(UIComponents.BUTTON_FONT);
        titleLabel.setForeground(Color.WHITE);
        
        JLabel descLabel = new JLabel(description, JLabel.CENTER);
        descLabel.setFont(UIComponents.SMALL_FONT);
        descLabel.setForeground(new Color(255, 255, 255, 180));
        
        buttonPanel.add(titleLabel, BorderLayout.CENTER);
        buttonPanel.add(descLabel, BorderLayout.SOUTH);
        
        // Convert panel to button
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.add(buttonPanel, BorderLayout.CENTER);
        button.setPreferredSize(new Dimension(300, 100));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        
        // Add hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonPanel.setBackground(backgroundColor.brighter());
                buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(backgroundColor, 3),
                    BorderFactory.createEmptyBorder(14, 14, 14, 14)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonPanel.setBackground(backgroundColor);
                buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(backgroundColor.darker(), 2),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
        });
        
        return button;
    }
    
    private JPanel createQuickAccessPanel() {
        JPanel quickPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quickPanel.setBackground(Color.WHITE);
        quickPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel quickLabel = UIComponents.createSecondaryLabel("Quick Access:");
        
        JButton quickBalance = UIComponents.createSecondaryButton("💰 $" + getCurrentBalance());
        quickBalance.addActionListener(e -> showBalance());
        quickBalance.setToolTipText("Click for detailed balance");
        
        JButton helpButton = UIComponents.createSecondaryButton("❓ Help");
        helpButton.addActionListener(e -> showHelp());
        helpButton.setToolTipText("Get help and assistance");
        
        quickPanel.add(quickLabel);
        quickPanel.add(Box.createHorizontalStrut(15));
        quickPanel.add(quickBalance);
        quickPanel.add(Box.createHorizontalStrut(10));
        quickPanel.add(helpButton);
        
        return quickPanel;
    }
    
    private void initializeWithdrawalPanel() {
        withdrawalPanel = new JPanel(new GridBagLayout());
        withdrawalPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JPanel withdrawCard = UIComponents.createCard();
        withdrawCard.setLayout(new GridBagLayout());
        withdrawCard.setPreferredSize(new Dimension(600, 550));
        
        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.insets = new Insets(10, 10, 10, 10);
        
        // Enhanced title with account info
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = UIComponents.createSubtitleLabel("💰 Cash Withdrawal");
        titleLabel.setForeground(UIComponents.PRIMARY_COLOR);
        
        JLabel accountLabel = UIComponents.createSecondaryLabel("From: " + authenticatedUser.getAccount().getAccountType() + " Account");
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(accountLabel, BorderLayout.SOUTH);
        
        cardGbc.gridx = 0;
        cardGbc.gridy = 0;
        cardGbc.gridwidth = 2;
        cardGbc.anchor = GridBagConstraints.CENTER;
        withdrawCard.add(titlePanel, cardGbc);
        
        // Current balance display
        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        balancePanel.setBackground(new Color(245, 255, 245));
        balancePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIComponents.SUCCESS_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel currentBalanceLabel = UIComponents.createBodyLabel("💳 Available Balance: " + balanceLabel.getText().substring(9));
        currentBalanceLabel.setForeground(UIComponents.SUCCESS_COLOR.darker());
        balancePanel.add(currentBalanceLabel);
        
        cardGbc.gridy = 1;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        withdrawCard.add(balancePanel, cardGbc);
        
        // Quick amount buttons with enhanced design
        JLabel quickLabel = UIComponents.createBodyLabel("Select Amount:");
        quickLabel.setFont(UIComponents.BUTTON_FONT);
        cardGbc.gridy = 2;
        cardGbc.fill = GridBagConstraints.NONE;
        cardGbc.anchor = GridBagConstraints.WEST;
        withdrawCard.add(quickLabel, cardGbc);
        
        JPanel quickAmountPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        quickAmountPanel.setBackground(Color.WHITE);
        quickAmountPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        String[] amounts = {"$20", "$50", "$100", "$200", "$500", "Other"};
        String[] descriptions = {"Quick Cash", "Small Amount", "Medium Amount", "Large Amount", "Maximum", "Custom"};
        
        for (int i = 0; i < amounts.length; i++) {
            final String amount = amounts[i]; // Make effectively final
            JButton amountButton = createWithdrawalButton(amount, descriptions[i]);
            amountButton.addActionListener(e -> handleQuickWithdrawal(amount));
            quickAmountPanel.add(amountButton);
        }
        
        cardGbc.gridy = 3;
        cardGbc.fill = GridBagConstraints.BOTH;
        withdrawCard.add(quickAmountPanel, cardGbc);
        
        // Custom amount section
        JPanel customPanel = new JPanel(new BorderLayout());
        customPanel.setBackground(Color.WHITE);
        customPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(UIComponents.SECONDARY_COLOR),
            "Custom Amount", 0, 0, UIComponents.BODY_FONT, UIComponents.SECONDARY_COLOR
        ));
        
        JTextField customAmountField = UIComponents.createTextField("Enter amount (Min: $10, Max: $1000)");
        customAmountField.setPreferredSize(new Dimension(250, 35));
        
        JButton withdrawCustomButton = UIComponents.createPrimaryButton("💸 WITHDRAW");
        withdrawCustomButton.addActionListener(e -> {
            try {
                String text = customAmountField.getText();
                if (!text.isEmpty() && !text.contains("Enter amount")) {
                    BigDecimal amount = new BigDecimal(text);
                    showDenominationDialog(amount);
                }
            } catch (NumberFormatException ex) {
                UIComponents.showErrorMessage(this, "Invalid Amount", "Please enter a valid amount");
            }
        });
        
        JPanel customInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        customInputPanel.setBackground(Color.WHITE);
        customInputPanel.add(customAmountField);
        customInputPanel.add(withdrawCustomButton);
        
        customPanel.add(customInputPanel, BorderLayout.CENTER);
        
        cardGbc.gridy = 4;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        withdrawCard.add(customPanel, cardGbc);
        
        // Enhanced buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton backButton = UIComponents.createSecondaryButton("🔙 BACK TO MENU");
        backButton.addActionListener(e -> showMenuPanel());
        
        buttonPanel.add(backButton);
        
        cardGbc.gridy = 5;
        cardGbc.fill = GridBagConstraints.NONE;
        cardGbc.anchor = GridBagConstraints.CENTER;
        withdrawCard.add(buttonPanel, cardGbc);
        
        withdrawalPanel.add(withdrawCard, gbc);
    }
    
    private JButton createWithdrawalButton(String amount, String description) {
        JPanel buttonContent = new JPanel(new BorderLayout());
        buttonContent.setBackground(UIComponents.PRIMARY_COLOR);
        buttonContent.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        
        JLabel amountLabel = new JLabel(amount, JLabel.CENTER);
        amountLabel.setFont(UIComponents.SUBTITLE_FONT);
        amountLabel.setForeground(Color.WHITE);
        
        JLabel descLabel = new JLabel(description, JLabel.CENTER);
        descLabel.setFont(UIComponents.SMALL_FONT);
        descLabel.setForeground(new Color(255, 255, 255, 180));
        
        buttonContent.add(amountLabel, BorderLayout.CENTER);
        buttonContent.add(descLabel, BorderLayout.SOUTH);
        
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.add(buttonContent, BorderLayout.CENTER);
        button.setPreferredSize(new Dimension(150, 80));
        button.setBorder(BorderFactory.createLineBorder(UIComponents.PRIMARY_COLOR.darker(), 2));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonContent.setBackground(UIComponents.SECONDARY_COLOR);
                button.setBorder(BorderFactory.createLineBorder(UIComponents.SECONDARY_COLOR, 3));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonContent.setBackground(UIComponents.PRIMARY_COLOR);
                button.setBorder(BorderFactory.createLineBorder(UIComponents.PRIMARY_COLOR.darker(), 2));
            }
        });
        
        return button;
    }
    
    private void initializeTransferPanel() {
        transferPanel = new JPanel(new GridBagLayout());
        transferPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JPanel transferCard = UIComponents.createCard();
        transferCard.setLayout(new GridBagLayout());
        transferCard.setPreferredSize(new Dimension(500, 400));
        
        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = UIComponents.createSubtitleLabel("Fund Transfer");
        cardGbc.gridx = 0;
        cardGbc.gridy = 0;
        cardGbc.gridwidth = 2;
        cardGbc.anchor = GridBagConstraints.CENTER;
        transferCard.add(titleLabel, cardGbc);
        
        // Destination account
        JLabel destLabel = UIComponents.createBodyLabel("Destination Account:");
        cardGbc.gridy = 1;
        cardGbc.gridwidth = 1;
        cardGbc.anchor = GridBagConstraints.WEST;
        transferCard.add(destLabel, cardGbc);
        
        JTextField destAccountField = UIComponents.createTextField("Enter account number");
        destAccountField.setPreferredSize(new Dimension(300, 35));
        cardGbc.gridy = 2;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        transferCard.add(destAccountField, cardGbc);
        
        // Amount
        JLabel amountLabel = UIComponents.createBodyLabel("Amount:");
        cardGbc.gridy = 3;
        cardGbc.gridwidth = 1;
        cardGbc.fill = GridBagConstraints.NONE;
        cardGbc.anchor = GridBagConstraints.WEST;
        transferCard.add(amountLabel, cardGbc);
        
        JTextField amountField = UIComponents.createTextField("Enter amount");
        amountField.setPreferredSize(new Dimension(300, 35));
        cardGbc.gridy = 4;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        transferCard.add(amountField, cardGbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton transferButton = UIComponents.createPrimaryButton("TRANSFER");
        transferButton.addActionListener(e -> {
            try {
                String destAccount = destAccountField.getText();
                String amountText = amountField.getText();
                
                if (destAccount.isEmpty() || destAccount.equals("Enter account number")) {
                    UIComponents.showErrorMessage(this, "Invalid Input", "Please enter destination account");
                    return;
                }
                
                if (amountText.isEmpty() || amountText.equals("Enter amount")) {
                    UIComponents.showErrorMessage(this, "Invalid Input", "Please enter amount");
                    return;
                }
                
                BigDecimal amount = new BigDecimal(amountText);
                performTransfer(destAccount, amount);
                
            } catch (NumberFormatException ex) {
                UIComponents.showErrorMessage(this, "Invalid Amount", "Please enter a valid amount");
            }
        });
        
        JButton backButton = UIComponents.createSecondaryButton("BACK");
        backButton.addActionListener(e -> showMenuPanel());
        
        buttonPanel.add(backButton);
        buttonPanel.add(transferButton);
        
        cardGbc.gridy = 5;
        cardGbc.fill = GridBagConstraints.NONE;
        cardGbc.anchor = GridBagConstraints.CENTER;
        transferCard.add(buttonPanel, cardGbc);
        
        transferPanel.add(transferCard, gbc);
    }
    
    private void initializeHistoryPanel() {
        historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(UIComponents.BACKGROUND_COLOR);
    }
    
    private void initializeChangePinPanel() {
        changePinPanel = new JPanel(new GridBagLayout());
        changePinPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JPanel pinCard = UIComponents.createCard();
        pinCard.setLayout(new GridBagLayout());
        pinCard.setPreferredSize(new Dimension(400, 350));
        
        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = UIComponents.createSubtitleLabel("Change PIN");
        cardGbc.gridx = 0;
        cardGbc.gridy = 0;
        cardGbc.gridwidth = 2;
        cardGbc.anchor = GridBagConstraints.CENTER;
        pinCard.add(titleLabel, cardGbc);
        
        // Current PIN
        JLabel currentLabel = UIComponents.createBodyLabel("Current PIN:");
        cardGbc.gridy = 1;
        cardGbc.gridwidth = 1;
        cardGbc.anchor = GridBagConstraints.WEST;
        pinCard.add(currentLabel, cardGbc);
        
        JPasswordField currentPinField = UIComponents.createPasswordField("Current PIN");
        currentPinField.setPreferredSize(new Dimension(200, 35));
        cardGbc.gridy = 2;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        pinCard.add(currentPinField, cardGbc);
        
        // New PIN
        JLabel newLabel = UIComponents.createBodyLabel("New PIN:");
        cardGbc.gridy = 3;
        cardGbc.gridwidth = 1;
        cardGbc.fill = GridBagConstraints.NONE;
        cardGbc.anchor = GridBagConstraints.WEST;
        pinCard.add(newLabel, cardGbc);
        
        JPasswordField newPinField = UIComponents.createPasswordField("New PIN");
        newPinField.setPreferredSize(new Dimension(200, 35));
        cardGbc.gridy = 4;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        pinCard.add(newPinField, cardGbc);
        
        // Confirm PIN
        JLabel confirmLabel = UIComponents.createBodyLabel("Confirm New PIN:");
        cardGbc.gridy = 5;
        cardGbc.gridwidth = 1;
        cardGbc.fill = GridBagConstraints.NONE;
        cardGbc.anchor = GridBagConstraints.WEST;
        pinCard.add(confirmLabel, cardGbc);
        
        JPasswordField confirmPinField = UIComponents.createPasswordField("Confirm PIN");
        confirmPinField.setPreferredSize(new Dimension(200, 35));
        cardGbc.gridy = 6;
        cardGbc.gridwidth = 2;
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        pinCard.add(confirmPinField, cardGbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton changePinButton = UIComponents.createPrimaryButton("CHANGE PIN");
        changePinButton.addActionListener(e -> {
            String currentPin = new String(currentPinField.getPassword());
            String newPin = new String(newPinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());
            
            if (currentPin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
                UIComponents.showErrorMessage(this, "Invalid Input", "Please fill all fields");
                return;
            }
            
            if (!newPin.equals(confirmPin)) {
                UIComponents.showErrorMessage(this, "PIN Mismatch", "New PIN and confirmation do not match");
                return;
            }
            
            performPinChange(currentPin, newPin);
        });
        
        JButton backButton = UIComponents.createSecondaryButton("BACK");
        backButton.addActionListener(e -> showMenuPanel());
        
        buttonPanel.add(backButton);
        buttonPanel.add(changePinButton);
        
        cardGbc.gridy = 7;
        cardGbc.fill = GridBagConstraints.NONE;
        cardGbc.anchor = GridBagConstraints.CENTER;
        pinCard.add(buttonPanel, cardGbc);
        
        changePinPanel.add(pinCard, gbc);
    }
    
    // Navigation methods
    private void showMenuPanel() {
        cardLayout.show(contentPanel, "MENU");
        loadBalance(); // Refresh balance when returning to menu
    }
    
    private void showWithdrawalPanel() {
        cardLayout.show(contentPanel, "WITHDRAWAL");
    }
    
    private void showTransferPanel() {
        cardLayout.show(contentPanel, "TRANSFER");
    }
    
    private void showHistoryPanel() {
        loadTransactionHistory();
        cardLayout.show(contentPanel, "HISTORY");
    }
    
    private void showChangePinPanel() {
        cardLayout.show(contentPanel, "CHANGE_PIN");
    }
    
    // Business logic methods
    private void loadBalance() {
        ATMService atmService = ATMService.getInstance();
        ATMService.BalanceResult result = atmService.getBalance(authenticatedUser.getSessionId());
        
        if (result.isSuccess()) {
            balanceLabel.setText("Balance: " + currencyFormat.format(result.getBalance()));
        } else {
            balanceLabel.setText("Balance: Error loading");
        }
    }
    
    private void handleQuickWithdrawal(String amountStr) {
        if (amountStr.equals("Other")) {
            // Focus stays on custom amount field
            return;
        }
        
        try {
            // Parse amount (remove $ symbol)
            BigDecimal amount = new BigDecimal(amountStr.substring(1));
            showDenominationDialog(amount);
        } catch (NumberFormatException e) {
            UIComponents.showErrorMessage(this, "Error", "Invalid amount format");
        }
    }
    
    private void showDenominationDialog(BigDecimal amount) {
        JDialog denomDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
                                        "Select Cash Denomination", true);
        denomDialog.setSize(500, 400);
        denomDialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = UIComponents.createSubtitleLabel("💵 Select Cash Denomination");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(UIComponents.PRIMARY_COLOR);
        
        // Amount info
        JLabel amountLabel = UIComponents.createBodyLabel("Withdrawal Amount: " + currencyFormat.format(amount));
        amountLabel.setHorizontalAlignment(JLabel.CENTER);
        amountLabel.setFont(UIComponents.SUBTITLE_FONT);
        
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        headerPanel.add(titleLabel);
        headerPanel.add(amountLabel);
        
        // Denomination options
        JPanel denomPanel = new JPanel(new GridLayout(3, 1, 0, 15));
        denomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Calculate possible denominations
        String[] denominations = calculateDenominations(amount);
        
        for (String denom : denominations) {
            JButton denomButton = UIComponents.createSecondaryButton(denom);
            denomButton.setPreferredSize(new Dimension(400, 50));
            denomButton.addActionListener(e -> {
                denomDialog.dispose();
                performWithdrawal(amount);
            });
            denomPanel.add(denomButton);
        }
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton cancelButton = UIComponents.createDangerButton("❌ CANCEL");
        cancelButton.addActionListener(e -> denomDialog.dispose());
        buttonPanel.add(cancelButton);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(denomPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        denomDialog.add(mainPanel);
        denomDialog.setVisible(true);
    }
    
    private String[] calculateDenominations(BigDecimal amount) {
        int amt = amount.intValue();
        
        // Calculate different denomination combinations
        String[] options = new String[3];
        
        if (amt >= 100) {
            int hundreds = amt / 100;
            int fifties = (amt % 100) / 50;
            int twenties = ((amt % 100) % 50) / 20;
            int tens = (((amt % 100) % 50) % 20) / 10;
            options[0] = String.format("💵 Mix: %d×$100, %d×$50, %d×$20, %d×$10", hundreds, fifties, twenties, tens);
        } else {
            options[0] = "💵 Standard Mix: Optimal combination of bills";
        }
        
        // All twenties if possible
        if (amt % 20 == 0) {
            options[1] = String.format("💵 All Twenties: %d×$20 bills", amt / 20);
        } else {
            int twenties = amt / 20;
            int tens = (amt % 20) / 10;
            options[1] = String.format("💵 Mostly Twenties: %d×$20, %d×$10", twenties, tens);
        }
        
        // Small bills
        if (amt <= 200) {
            int tens = amt / 10;
            options[2] = String.format("💵 Small Bills: %d×$10 bills", tens);
        } else {
            options[2] = "💵 Compact: Minimum number of bills";
        }
        
        return options;
    }
    
    private void performWithdrawal(BigDecimal amount) {
        ATMService atmService = ATMService.getInstance();
        ATMService.TransactionResult result = atmService.withdraw(authenticatedUser.getSessionId(), amount);
        
        if (result.isSuccess()) {
            String message = "Amount withdrawn: " + currencyFormat.format(amount) + 
                           "\nTransaction ID: " + result.getTransaction().getReferenceNumber();
            UIComponents.showSuccessMessage(this, "Withdrawal Successful", message);
            
            // Ask if user wants a receipt
            int receiptChoice = UIComponents.showConfirmationDialog(this, "Receipt", 
                "Would you like to print a receipt?");
            if (receiptChoice == JOptionPane.YES_OPTION) {
                ReceiptGenerator.showTransactionReceipt(this, result.getTransaction(), authenticatedUser);
            }
            
            loadBalance();
            showMenuPanel();
        } else {
            UIComponents.showErrorMessage(this, "Withdrawal Failed", result.getMessage());
        }
    }
    
    private void performTransfer(String destinationAccount, BigDecimal amount) {
        int confirm = UIComponents.showConfirmationDialog(this, "Confirm Transfer",
            "Transfer " + currencyFormat.format(amount) + " to account " + destinationAccount + "?");
        
        if (confirm == JOptionPane.YES_OPTION) {
            ATMService atmService = ATMService.getInstance();
            ATMService.TransactionResult result = atmService.transfer(
                authenticatedUser.getSessionId(), destinationAccount, amount);
            
            if (result.isSuccess()) {
                String message = "Amount transferred: " + currencyFormat.format(amount) + 
                               "\nTo: " + destinationAccount +
                               "\nTransaction ID: " + result.getTransaction().getReferenceNumber();
                UIComponents.showSuccessMessage(this, "Transfer Successful", message);
                
                // Ask if user wants a receipt
                int receiptChoice = UIComponents.showConfirmationDialog(this, "Receipt", 
                    "Would you like to print a receipt?");
                if (receiptChoice == JOptionPane.YES_OPTION) {
                    ReceiptGenerator.showTransactionReceipt(this, result.getTransaction(), authenticatedUser);
                }
                
                loadBalance();
                showMenuPanel();
            } else {
                UIComponents.showErrorMessage(this, "Transfer Failed", result.getMessage());
            }
        }
    }
    
    private void showBalance() {
        loadBalance();
        String balanceText = balanceLabel.getText().substring(9); // Remove "Balance: " prefix
        UIComponents.showSuccessMessage(this, "Account Balance", 
            "Your current balance is: " + balanceText);
        
        // Ask if user wants a receipt
        int receiptChoice = UIComponents.showConfirmationDialog(this, "Receipt", 
            "Would you like to print a receipt?");
        if (receiptChoice == JOptionPane.YES_OPTION) {
            ATMService atmService = ATMService.getInstance();
            ATMService.BalanceResult result = atmService.getBalance(authenticatedUser.getSessionId());
            if (result.isSuccess()) {
                ReceiptGenerator.showBalanceReceipt(this, result.getBalance(), authenticatedUser);
            }
        }
    }
    
    private void loadTransactionHistory() {
        historyPanel.removeAll();
        
        ATMService atmService = ATMService.getInstance();
        ATMService.TransactionHistoryResult result = atmService.getTransactionHistory(
            authenticatedUser.getSessionId(), 10);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        JLabel titleLabel = UIComponents.createSubtitleLabel("Transaction History");
        JButton backButton = UIComponents.createSecondaryButton("BACK");
        backButton.addActionListener(e -> showMenuPanel());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(backButton, BorderLayout.EAST);
        
        if (result.isSuccess() && !result.getTransactions().isEmpty()) {
            JPanel transactionListPanel = new JPanel();
            transactionListPanel.setLayout(new BoxLayout(transactionListPanel, BoxLayout.Y_AXIS));
            transactionListPanel.setBackground(UIComponents.BACKGROUND_COLOR);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
            
            for (Transaction transaction : result.getTransactions()) {
                JPanel transactionCard = UIComponents.createCard();
                transactionCard.setLayout(new BorderLayout());
                transactionCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
                
                JPanel leftPanel = new JPanel(new GridLayout(2, 1));
                leftPanel.setBackground(Color.WHITE);
                
                JLabel typeLabel = UIComponents.createBodyLabel(transaction.getType().toString());
                JLabel dateLabel = UIComponents.createSecondaryLabel(transaction.getTimestamp().format(formatter));
                
                leftPanel.add(typeLabel);
                leftPanel.add(dateLabel);
                
                JLabel amountLabel = UIComponents.createBodyLabel(currencyFormat.format(transaction.getAmount()));
                if (transaction.getType() == Transaction.TransactionType.WITHDRAWAL) {
                    amountLabel.setForeground(UIComponents.ERROR_COLOR);
                    amountLabel.setText("-" + amountLabel.getText());
                } else if (transaction.getType() == Transaction.TransactionType.DEPOSIT) {
                    amountLabel.setForeground(UIComponents.SUCCESS_COLOR);
                    amountLabel.setText("+" + amountLabel.getText());
                }
                
                transactionCard.add(leftPanel, BorderLayout.WEST);
                transactionCard.add(amountLabel, BorderLayout.EAST);
                
                transactionListPanel.add(transactionCard);
                transactionListPanel.add(Box.createVerticalStrut(10));
            }
            
            JScrollPane scrollPane = new JScrollPane(transactionListPanel);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            
            historyPanel.add(headerPanel, BorderLayout.NORTH);
            historyPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
            JPanel emptyPanel = new JPanel(new GridBagLayout());
            emptyPanel.setBackground(UIComponents.BACKGROUND_COLOR);
            
            JLabel emptyLabel = UIComponents.createSecondaryLabel("No transactions found");
            emptyPanel.add(emptyLabel);
            
            historyPanel.add(headerPanel, BorderLayout.NORTH);
            historyPanel.add(emptyPanel, BorderLayout.CENTER);
        }
        
        historyPanel.revalidate();
        historyPanel.repaint();
    }
    
    private void performPinChange(String currentPin, String newPin) {
        ATMService atmService = ATMService.getInstance();
        ATMService.OperationResult result = atmService.changePin(
            authenticatedUser.getSessionId(), currentPin, newPin);
        
        if (result.isSuccess()) {
            UIComponents.showSuccessMessage(this, "PIN Changed", "Your PIN has been successfully changed");
            showMenuPanel();
        } else {
            UIComponents.showErrorMessage(this, "PIN Change Failed", result.getMessage());
        }
    }
    
    private void logout() {
        AuthenticationService.getInstance().logout(authenticatedUser.getSessionId());
        parentFrame.showLoginScreen();
    }
    
    private String getCurrentTime() {
        return java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    
    private String getCurrentDate() {
        return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }
    
    private String getCurrentBalance() {
        ATMService atmService = ATMService.getInstance();
        ATMService.BalanceResult result = atmService.getBalance(authenticatedUser.getSessionId());
        if (result.isSuccess()) {
            return currencyFormat.format(result.getBalance()).replace("$", "");
        }
        return "0.00";
    }
    
    private void showHelp() {
        String helpText = "🏧 MyBanker ATM Help\n\n" +
                         "Available Services:\n" +
                         "💰 Cash Withdrawal - Withdraw money from your account\n" +
                         "💸 Fund Transfer - Send money to another account\n" +
                         "💳 Balance Inquiry - Check your current balance\n" +
                         "📋 Mini Statement - View recent transactions\n" +
                         "🔐 Change PIN - Update your security PIN\n" +
                         "🏦 Create Account - Open a new bank account\n\n" +
                         "Security Features:\n" +
                         "• Session automatically times out after 5 minutes\n" +
                         "• Maximum 3 PIN attempts before card is blocked\n" +
                         "• All transactions are encrypted and secure\n" +
                         "• Multi-account management for existing customers\n\n" +
                         "Need Assistance?\n" +
                         "📞 Customer Service: 1-800-MYBANK\n" +
                         "🌐 Online: www.mybanker.com\n" +
                         "📧 Email: support@mybanker.com";
        
        UIComponents.showSuccessMessage(this, "ATM Help & Information", helpText);
    }
    
    private void openNewAccount() {
        int response = JOptionPane.showConfirmDialog(
            this,
            "🏦 Open New Bank Account\n\n" +
            "Would you like to open an additional bank account?\n" +
            "You will be guided through the account creation process.\n\n" +
            "✓ Multiple account types available\n" +
            "✓ Instant account activation\n" +
            "✓ New debit card issued immediately",
            "Create New Account",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            // Navigate to account creation with current session ID
            parentFrame.showAccountCreationScreen(authenticatedUser.getSessionId());
        }
    }
}