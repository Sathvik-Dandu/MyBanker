package com.atm.gui;

import com.atm.models.Account;
import com.atm.models.Card;
import com.atm.services.ATMService;
import com.atm.services.AuthenticationService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 * Enhanced account creation screen for new bank accounts
 */
public class AccountCreationScreen extends JPanel {
    private ATMMainFrame parentFrame;
    private String currentSessionId; // null for guest users
    
    // Form components
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField pinField;
    private JPasswordField confirmPinField;
    private JComboBox<AccountTypeOption> accountTypeCombo;
    private JTextField initialDepositField;
    private JLabel statusLabel;
    private JButton createAccountButton;
    private JButton backButton;
    
    public AccountCreationScreen(ATMMainFrame parentFrame, String sessionId) {
        this.parentFrame = parentFrame;
        this.currentSessionId = sessionId;
        initializeComponents();
        setupLayout();
        setupEventListeners();
    }
    
    private void initializeComponents() {
        setBackground(UIComponents.BACKGROUND_COLOR);
        
        // Personal information fields
        firstNameField = UIComponents.createTextField("Enter your first name");
        firstNameField.setPreferredSize(new Dimension(320, 45));
        
        lastNameField = UIComponents.createTextField("Enter your last name");
        lastNameField.setPreferredSize(new Dimension(320, 45));
        
        phoneField = UIComponents.createTextField("Enter phone number (10 digits)");
        phoneField.setPreferredSize(new Dimension(320, 45));
        
        emailField = UIComponents.createTextField("Enter email address");
        emailField.setPreferredSize(new Dimension(320, 45));
        
        // Security fields
        pinField = UIComponents.createPasswordField("Enter 4-digit PIN");
        pinField.setPreferredSize(new Dimension(320, 45));
        
        confirmPinField = UIComponents.createPasswordField("Confirm your PIN");
        confirmPinField.setPreferredSize(new Dimension(320, 45));
        
        // Account type selection
        AccountTypeOption[] accountTypes = {
            new AccountTypeOption(Account.AccountType.CHECKING, "Checking Account", "Everyday banking with unlimited transactions"),
            new AccountTypeOption(Account.AccountType.SAVINGS, "Savings Account", "Earn interest on your deposits"),
            new AccountTypeOption(Account.AccountType.BUSINESS, "Business Account", "For business banking needs")
        };
        accountTypeCombo = new JComboBox<>(accountTypes);
        accountTypeCombo.setPreferredSize(new Dimension(320, 45));
        accountTypeCombo.setFont(UIComponents.BODY_FONT);
        
        // Initial deposit
        initialDepositField = UIComponents.createTextField("Enter initial deposit (minimum $10)");
        initialDepositField.setPreferredSize(new Dimension(320, 45));
        
        // Buttons
        createAccountButton = UIComponents.createPrimaryButton("🏦 CREATE ACCOUNT");
        createAccountButton.setPreferredSize(new Dimension(200, 50));
        backButton = UIComponents.createSecondaryButton("🔙 BACK");
        backButton.setPreferredSize(new Dimension(120, 50));
        
        // Status label
        statusLabel = UIComponents.createBodyLabel(" ");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = createHeaderPanel();
        
        // Main content with scroll pane for better handling
        JScrollPane mainScrollPane = new JScrollPane(createMainContentPanel());
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Footer
        JPanel footerPanel = createFooterPanel();
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainScrollPane, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIComponents.PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 100));
        
        JLabel titleLabel = new JLabel("🏦 Open New Bank Account", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Join MyBanker today and start your banking journey", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(200, 230, 255));
        
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(UIComponents.PRIMARY_COLOR);
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        return headerPanel;
    }
    
    private JPanel createMainContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        
        // Create side-by-side layout
        JPanel contentWrapper = new JPanel(new GridBagLayout());
        contentWrapper.setBackground(UIComponents.BACKGROUND_COLOR);
        
        GridBagConstraints wrapperGbc = new GridBagConstraints();
        wrapperGbc.insets = new Insets(40, 40, 40, 40);
        wrapperGbc.fill = GridBagConstraints.BOTH;
        wrapperGbc.weightx = 1.0;
        wrapperGbc.weighty = 1.0;
        
        // Left side - Form
        JPanel formPanel = createEnhancedFormPanel();
        wrapperGbc.gridx = 0;
        wrapperGbc.gridy = 0;
        contentWrapper.add(formPanel, wrapperGbc);
        
        // Spacer
        wrapperGbc.gridx = 1;
        wrapperGbc.weightx = 0.1;
        wrapperGbc.fill = GridBagConstraints.NONE;
        contentWrapper.add(Box.createHorizontalStrut(30), wrapperGbc);
        
        // Right side - Information panel
        JPanel infoPanel = createInformationPanel();
        wrapperGbc.gridx = 2;
        wrapperGbc.weightx = 0.8;
        wrapperGbc.fill = GridBagConstraints.BOTH;
        contentWrapper.add(infoPanel, wrapperGbc);
        
        mainPanel.add(contentWrapper, BorderLayout.CENTER);
        return mainPanel;
    }
    
    private JPanel createEnhancedFormPanel() {
        JPanel formPanel = UIComponents.createCard();
        formPanel.setLayout(new BorderLayout());
        formPanel.setPreferredSize(new Dimension(520, 800));
        formPanel.setMaximumSize(new Dimension(520, 1000));
        
        // Form title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 15, 25));
        
        JLabel formTitle = UIComponents.createTitleLabel("Create Your Account");
        formTitle.setForeground(UIComponents.PRIMARY_COLOR);
        titlePanel.add(formTitle);
        
        // Form content
        JPanel formContent = new JPanel(new GridBagLayout());
        formContent.setBackground(Color.WHITE);
        formContent.setBorder(BorderFactory.createEmptyBorder(10, 35, 25, 35));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int row = 0;
        
        // Personal Information Section
        addStyledSectionHeader(formContent, "👤 Personal Information", gbc, row++);
        addStyledFormField(formContent, "First Name *", firstNameField, gbc, row++);
        addStyledFormField(formContent, "Last Name *", lastNameField, gbc, row++);
        addStyledFormField(formContent, "Phone Number *", phoneField, gbc, row++);
        addStyledFormField(formContent, "Email Address *", emailField, gbc, row++);
        
        // Security Section
        addStyledSectionHeader(formContent, "🔒 Security Setup", gbc, row++);
        addStyledFormField(formContent, "PIN (4 digits) *", pinField, gbc, row++);
        addStyledFormField(formContent, "Confirm PIN *", confirmPinField, gbc, row++);
        
        // Account Details Section
        addStyledSectionHeader(formContent, "💳 Account Setup", gbc, row++);
        addStyledFormField(formContent, "Account Type *", accountTypeCombo, gbc, row++);
        addStyledFormField(formContent, "Initial Deposit *", initialDepositField, gbc, row++);
        
        // Status and buttons
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 0, 15, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        formContent.add(statusLabel, gbc);
        
        JPanel buttonPanel = createStyledButtonPanel();
        gbc.gridy = row++;
        gbc.insets = new Insets(15, 0, 25, 0);
        formContent.add(buttonPanel, gbc);
        
        formPanel.add(titlePanel, BorderLayout.NORTH);
        formPanel.add(formContent, BorderLayout.CENTER);
        
        return formPanel;
    }
    
    private JPanel createInformationPanel() {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(UIComponents.BACKGROUND_COLOR);
        infoPanel.setPreferredSize(new Dimension(450, 800));
        
        // Welcome section
        JPanel welcomeCard = UIComponents.createCard();
        welcomeCard.setLayout(new BorderLayout());
        welcomeCard.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel welcomeTitle = UIComponents.createSubtitleLabel("Welcome to MyBanker!");
        welcomeTitle.setHorizontalAlignment(JLabel.CENTER);
        welcomeTitle.setForeground(UIComponents.SUCCESS_COLOR);
        
        JTextArea welcomeText = new JTextArea(
            "Join thousands of satisfied customers who trust MyBanker " +
            "for their banking needs. Open your account today and start " +
            "enjoying our premium banking services with competitive rates " +
            "and exceptional customer support."
        );
        welcomeText.setFont(UIComponents.BODY_FONT);
        welcomeText.setForeground(UIComponents.TEXT_PRIMARY);
        welcomeText.setBackground(Color.WHITE);
        welcomeText.setEditable(false);
        welcomeText.setOpaque(false);
        welcomeText.setLineWrap(true);
        welcomeText.setWrapStyleWord(true);
        
        welcomeCard.add(welcomeTitle, BorderLayout.NORTH);
        welcomeCard.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
        
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(Color.WHITE);
        textPanel.add(welcomeText, BorderLayout.NORTH);
        welcomeCard.add(textPanel, BorderLayout.SOUTH);
        
        // Benefits section
        JPanel benefitsCard = createBenefitsCard();
        
        // Security section
        JPanel securityCard = createSecurityCard();
        
        // Main info panel layout
        JPanel infoContent = new JPanel(new GridBagLayout());
        infoContent.setBackground(UIComponents.BACKGROUND_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 25, 0);
        
        gbc.gridy = 0;
        infoContent.add(welcomeCard, gbc);
        gbc.gridy = 1;
        infoContent.add(benefitsCard, gbc);
        gbc.gridy = 2;
        infoContent.add(securityCard, gbc);
        
        infoPanel.add(infoContent, BorderLayout.NORTH);
        return infoPanel;
    }
    
    private JPanel createBenefitsCard() {
        JPanel benefitsCard = UIComponents.createCard();
        benefitsCard.setLayout(new BorderLayout());
        benefitsCard.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        JLabel benefitsTitle = UIComponents.createSubtitleLabel("✨ Your Benefits");
        benefitsTitle.setForeground(UIComponents.PRIMARY_COLOR);
        
        String[] benefits = {
            "🏧 Free ATM access at 50,000+ locations",
            "📱 Mobile banking and online account management", 
            "🛡️ 24/7 fraud protection and monitoring",
            "💰 No monthly fees for the first year",
            "💳 Instant contactless debit card",
            "📞 24/7 customer support",
            "🎯 Competitive interest rates",
            "💸 Free money transfers between accounts"
        };
        
        JPanel benefitsContent = new JPanel(new GridLayout(benefits.length, 1, 0, 10));
        benefitsContent.setBackground(Color.WHITE);
        
        for (String benefit : benefits) {
            JLabel benefitLabel = new JLabel(benefit);
            benefitLabel.setFont(UIComponents.SMALL_FONT);
            benefitLabel.setForeground(UIComponents.TEXT_PRIMARY);
            benefitsContent.add(benefitLabel);
        }
        
        benefitsCard.add(benefitsTitle, BorderLayout.NORTH);
        benefitsCard.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
        benefitsCard.add(benefitsContent, BorderLayout.SOUTH);
        
        return benefitsCard;
    }
    
    private JPanel createSecurityCard() {
        JPanel securityCard = UIComponents.createCard();
        securityCard.setLayout(new BorderLayout());
        securityCard.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        JLabel securityTitle = UIComponents.createSubtitleLabel("🔒 Security & Privacy");
        securityTitle.setForeground(UIComponents.SECONDARY_COLOR);
        
        JTextArea securityText = new JTextArea(
            "Your personal information is protected with:\n\n" +
            "• 256-bit SSL encryption\n" +
            "• Multi-factor authentication\n" +
            "• Real-time fraud detection\n" +
            "• FDIC insurance up to $250,000\n" +
            "• SOC 2 compliance certification\n\n" +
            "We never share your data with third parties " +
            "without your explicit consent."
        );
        securityText.setFont(UIComponents.SMALL_FONT);
        securityText.setForeground(UIComponents.TEXT_PRIMARY);
        securityText.setBackground(Color.WHITE);
        securityText.setEditable(false);
        securityText.setOpaque(false);
        securityText.setLineWrap(true);
        securityText.setWrapStyleWord(true);
        
        securityCard.add(securityTitle, BorderLayout.NORTH);
        securityCard.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
        securityCard.add(securityText, BorderLayout.SOUTH);
        
        return securityCard;
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(UIComponents.TEXT_SECONDARY);
        footerPanel.setPreferredSize(new Dimension(0, 60));
        
        JLabel footerLabel = new JLabel("🔒 Your information is protected with bank-level security");
        footerLabel.setFont(UIComponents.SMALL_FONT);
        footerLabel.setForeground(Color.WHITE);
        
        footerPanel.add(footerLabel);
        return footerPanel;
    }
    
    private void addStyledSectionHeader(JPanel parent, String title, GridBagConstraints gbc, int row) {
        JLabel sectionLabel = new JLabel(title);
        sectionLabel.setFont(UIComponents.BUTTON_FONT);
        sectionLabel.setForeground(UIComponents.PRIMARY_COLOR);
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 20, 0);
        gbc.anchor = GridBagConstraints.WEST;
        parent.add(sectionLabel, gbc);
        
        // Reset for next components
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 0, 15, 0);
    }
    
    private void addStyledFormField(JPanel parent, String labelText, JComponent field, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(UIComponents.BODY_FONT);
        label.setForeground(UIComponents.TEXT_PRIMARY);
        
        // Style the field
        if (field instanceof JTextField || field instanceof JPasswordField) {
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
            ));
        }
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        parent.add(label, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = new Insets(8, 0, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        parent.add(field, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(15, 0, 15, 0);
    }
    
    private JPanel createStyledButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        buttonPanel.add(backButton);
        buttonPanel.add(createAccountButton);
        
        return buttonPanel;
    }
    
    private void setupEventListeners() {
        createAccountButton.addActionListener(e -> createAccount());
        backButton.addActionListener(e -> goBack());
        
        // Account type selection listener
        accountTypeCombo.addActionListener(e -> updateAccountTypeInfo());
        
        // Add input formatters
        setupInputFormatters();
    }
    
    private void setupInputFormatters() {
        // Phone number formatter
        phoneField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { formatPhone(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { formatPhone(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { formatPhone(); }
            
            private void formatPhone() {
                SwingUtilities.invokeLater(() -> {
                    String text = phoneField.getText().replaceAll("\\D", "");
                    if (text.length() > 10) text = text.substring(0, 10);
                    
                    if (text.length() >= 6) {
                        phoneField.setText(text.substring(0, 3) + "-" + text.substring(3, 6) + "-" + text.substring(6));
                    } else if (text.length() >= 3) {
                        phoneField.setText(text.substring(0, 3) + "-" + text.substring(3));
                    } else {
                        phoneField.setText(text);
                    }
                });
            }
        });
        
        // PIN length limiters
        setupPinLimiter(pinField);
        setupPinLimiter(confirmPinField);
    }
    
    private void setupPinLimiter(JPasswordField field) {
        field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { limitPin(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { limitPin(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { limitPin(); }
            
            private void limitPin() {
                SwingUtilities.invokeLater(() -> {
                    String text = new String(field.getPassword()).replaceAll("\\D", "");
                    if (text.length() > 4) {
                        field.setText(text.substring(0, 4));
                    }
                });
            }
        });
    }
    
    private void updateAccountTypeInfo() {
        // This could show dynamic information about the selected account type
        AccountTypeOption selected = (AccountTypeOption) accountTypeCombo.getSelectedItem();
        if (selected != null) {
            setStatusMessage("ℹ️ " + selected.description, true);
        }
    }
    
    private void createAccount() {
        // Validate all fields
        if (!validateForm()) {
            return;
        }
        
        // Disable button during creation
        createAccountButton.setEnabled(false);
        createAccountButton.setText("CREATING ACCOUNT...");
        
        SwingUtilities.invokeLater(() -> {
            try {
                // Get form data
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String phone = phoneField.getText().replaceAll("-", "");
                String email = emailField.getText().trim();
                String pin = new String(pinField.getPassword());
                AccountTypeOption selectedType = (AccountTypeOption) accountTypeCombo.getSelectedItem();
                BigDecimal initialDeposit = new BigDecimal(initialDepositField.getText().trim());
                
                // Create account
                ATMService atmService = ATMService.getInstance();
                ATMService.AccountCreationResult result = atmService.createAccount(
                    currentSessionId, firstName, lastName, phone, email, 
                    selectedType.accountType, initialDeposit, pin);
                
                if (result.isSuccess()) {
                    showSuccessDialog(result.getAccount(), result.getCard());
                } else {
                    setStatusMessage("❌ " + result.getMessage(), false);
                }
                
            } catch (Exception ex) {
                setStatusMessage("❌ Error: " + ex.getMessage(), false);
            } finally {
                createAccountButton.setEnabled(true);
                createAccountButton.setText("🏦 CREATE ACCOUNT");
            }
        });
    }
    
    private boolean validateForm() {
        // Validate first name
        if (firstNameField.getText().trim().isEmpty() || firstNameField.getText().contains("Enter your first name")) {
            setStatusMessage("❌ Please enter your first name", false);
            firstNameField.requestFocus();
            return false;
        }
        
        // Validate last name
        if (lastNameField.getText().trim().isEmpty() || lastNameField.getText().contains("Enter your last name")) {
            setStatusMessage("❌ Please enter your last name", false);
            lastNameField.requestFocus();
            return false;
        }
        
        // Validate phone
        String phone = phoneField.getText().replaceAll("-", "");
        if (phone.length() != 10 || !phone.matches("\\d{10}")) {
            setStatusMessage("❌ Please enter a valid 10-digit phone number", false);
            phoneField.requestFocus();
            return false;
        }
        
        // Validate email
        String email = emailField.getText().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            setStatusMessage("❌ Please enter a valid email address", false);
            emailField.requestFocus();
            return false;
        }
        
        // Validate PIN
        String pin = new String(pinField.getPassword());
        if (pin.length() != 4 || !pin.matches("\\d{4}")) {
            setStatusMessage("❌ PIN must be exactly 4 digits", false);
            pinField.requestFocus();
            return false;
        }
        
        // Validate PIN confirmation
        String confirmPin = new String(confirmPinField.getPassword());
        if (!pin.equals(confirmPin)) {
            setStatusMessage("❌ PIN confirmation does not match", false);
            confirmPinField.requestFocus();
            return false;
        }
        
        // Validate initial deposit
        try {
            BigDecimal deposit = new BigDecimal(initialDepositField.getText().trim());
            if (deposit.compareTo(new BigDecimal("10.00")) < 0) {
                setStatusMessage("❌ Minimum initial deposit is $10.00", false);
                initialDepositField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            setStatusMessage("❌ Please enter a valid initial deposit amount", false);
            initialDepositField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void showSuccessDialog(Account account, Card card) {
        JDialog successDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
                                          "Account Created Successfully!", true);
        successDialog.setSize(550, 450);
        successDialog.setLocationRelativeTo(this);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        // Success message
        JLabel successIcon = new JLabel("🎉", JLabel.CENTER);
        successIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        
        JLabel successTitle = UIComponents.createTitleLabel("Welcome to MyBanker!");
        successTitle.setHorizontalAlignment(JLabel.CENTER);
        successTitle.setForeground(UIComponents.SUCCESS_COLOR);
        
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.add(successIcon);
        headerPanel.add(successTitle);
        
        // Account details
        JTextArea detailsText = new JTextArea(
            "Your new account has been created successfully!\n\n" +
            "📧 Account Number: " + account.getAccountNumber() + "\n" +
            "💳 Card Number: " + card.getMaskedCardNumber() + "\n" +
            "🏦 Account Type: " + account.getAccountType() + "\n" +
            "💰 Initial Balance: $" + account.getBalance() + "\n" +
            "📅 Account Created: " + java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy")) + "\n\n" +
            "Your debit card is ready to use! You can now:\n" +
            "• Make withdrawals at any ATM\n" +
            "• Transfer funds to other accounts\n" +
            "• Check your balance anytime\n" +
            "• Access online banking services\n\n" +
            "Keep your card number and PIN secure!"
        );
        detailsText.setFont(UIComponents.BODY_FONT);
        detailsText.setEditable(false);
        detailsText.setOpaque(false);
        
        JScrollPane scrollPane = new JScrollPane(detailsText);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton continueButton = UIComponents.createPrimaryButton("🏧 LOGIN TO ATM");
        JButton printButton = UIComponents.createSecondaryButton("🖨️ PRINT DETAILS");
        
        continueButton.addActionListener(e -> {
            successDialog.dispose();
            parentFrame.showLoginScreen();
        });
        
        printButton.addActionListener(e -> {
            UIComponents.showSuccessMessage(successDialog, "Account Details", 
                "Account details have been printed.\nPlease collect your printout and keep it safe.");
        });
        
        buttonPanel.add(printButton);
        buttonPanel.add(continueButton);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        successDialog.add(mainPanel);
        successDialog.setVisible(true);
    }
    
    private void setStatusMessage(String message, boolean isSuccess) {
        statusLabel.setText(message);
        statusLabel.setForeground(isSuccess ? UIComponents.SUCCESS_COLOR : UIComponents.ERROR_COLOR);
        
        if (isSuccess && !message.contains("ℹ️")) {
            Timer timer = new Timer(5000, e -> statusLabel.setText(" "));
            timer.setRepeats(false);
            timer.start();
        }
    }
    
    private void goBack() {
        if (currentSessionId != null) {
            // Return to main ATM interface
            parentFrame.showMainInterface(AuthenticationService.getInstance().validateSession(currentSessionId));
        } else {
            // Return to login screen
            parentFrame.showLoginScreen();
        }
    }
    
    public void resetForm() {
        firstNameField.setText("Enter your first name");
        firstNameField.setForeground(UIComponents.TEXT_SECONDARY);
        lastNameField.setText("Enter your last name");
        lastNameField.setForeground(UIComponents.TEXT_SECONDARY);
        phoneField.setText("Enter phone number (10 digits)");
        phoneField.setForeground(UIComponents.TEXT_SECONDARY);
        emailField.setText("Enter email address");
        emailField.setForeground(UIComponents.TEXT_SECONDARY);
        pinField.setText("");
        confirmPinField.setText("");
        accountTypeCombo.setSelectedIndex(0);
        initialDepositField.setText("Enter initial deposit (minimum $10)");
        initialDepositField.setForeground(UIComponents.TEXT_SECONDARY);
        statusLabel.setText(" ");
    }
    
    // Helper class for account type combo box
    private static class AccountTypeOption {
        public final Account.AccountType accountType;
        public final String displayName;
        public final String description;
        
        public AccountTypeOption(Account.AccountType accountType, String displayName, String description) {
            this.accountType = accountType;
            this.displayName = displayName;
            this.description = description;
        }
        
        @Override
        public String toString() {
            return displayName;
        }
    }
}