package com.atm.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Custom UI components and styling for the ATM interface
 */
public class UIComponents {
    
    // Color scheme
    public static final Color PRIMARY_COLOR = new Color(25, 118, 210);      // Blue
    public static final Color SECONDARY_COLOR = new Color(33, 150, 243);    // Light Blue
    public static final Color ACCENT_COLOR = new Color(255, 193, 7);        // Amber
    public static final Color SUCCESS_COLOR = new Color(76, 175, 80);       // Green
    public static final Color ERROR_COLOR = new Color(244, 67, 54);         // Red
    public static final Color WARNING_COLOR = new Color(255, 152, 0);       // Orange
    public static final Color BACKGROUND_COLOR = new Color(245, 245, 245);  // Light Gray
    public static final Color CARD_BACKGROUND = Color.WHITE;
    public static final Color TEXT_PRIMARY = new Color(33, 33, 33);
    public static final Color TEXT_SECONDARY = new Color(117, 117, 117);
    
    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    
    /**
     * Creates a styled primary button
     */
    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(SECONDARY_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a styled secondary button
     */
    public static JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(PRIMARY_COLOR);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 22, 10, 22)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(25, 118, 210, 10));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a styled success button
     */
    public static JButton createSuccessButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(SUCCESS_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(SUCCESS_COLOR.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(SUCCESS_COLOR);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a styled danger button
     */
    public static JButton createDangerButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(ERROR_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ERROR_COLOR.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(ERROR_COLOR);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a styled tertiary button (outline style)
     */
    public static JButton createTertiaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(PRIMARY_COLOR);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 22, 10, 22)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(245, 248, 255));
                button.setForeground(PRIMARY_COLOR.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(PRIMARY_COLOR);
            }
        });
        
        return button;
    }

    /**
     * Creates a styled text field
     */
    public static JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(BODY_FONT);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        
        // Add placeholder
        if (placeholder != null && !placeholder.isEmpty()) {
            textField.setText(placeholder);
            textField.setForeground(TEXT_SECONDARY);
            
            textField.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (textField.getText().equals(placeholder)) {
                        textField.setText("");
                        textField.setForeground(TEXT_PRIMARY);
                    }
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (textField.getText().isEmpty()) {
                        textField.setText(placeholder);
                        textField.setForeground(TEXT_SECONDARY);
                    }
                }
            });
        }
        
        return textField;
    }
    
    /**
     * Creates a styled password field
     */
    public static JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(BODY_FONT);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        
        return passwordField;
    }
    
    /**
     * Creates a card panel with shadow effect
     */
    public static JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 8, 8),
                BorderFactory.createLineBorder(new Color(0, 0, 0, 20), 1)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }
    
    /**
     * Creates a title label
     */
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setForeground(TEXT_PRIMARY);
        return label;
    }
    
    /**
     * Creates a subtitle label
     */
    public static JLabel createSubtitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(SUBTITLE_FONT);
        label.setForeground(TEXT_PRIMARY);
        return label;
    }
    
    /**
     * Creates a body text label
     */
    public static JLabel createBodyLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY_FONT);
        label.setForeground(TEXT_PRIMARY);
        return label;
    }
    
    /**
     * Creates a secondary text label
     */
    public static JLabel createSecondaryLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY_FONT);
        label.setForeground(TEXT_SECONDARY);
        return label;
    }
    
    /**
     * Creates a success message label
     */
    public static JLabel createSuccessLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY_FONT);
        label.setForeground(SUCCESS_COLOR);
        return label;
    }
    
    /**
     * Creates an error message label
     */
    public static JLabel createErrorLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY_FONT);
        label.setForeground(ERROR_COLOR);
        return label;
    }
    
    /**
     * Shows a styled success message dialog
     */
    public static void showSuccessMessage(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Shows a styled error message dialog
     */
    public static void showErrorMessage(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Shows a confirmation dialog
     */
    public static int showConfirmationDialog(Component parent, String title, String message) {
        return JOptionPane.showConfirmDialog(parent, message, title, 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}