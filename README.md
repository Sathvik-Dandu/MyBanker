# 🏧 MyBanker ATM System# 🏧 MyBanker ATM System# MyBanker ATM System



[![Java](https://img.shields.io/badge/Java-8%2B-orange?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)

[![Swing](https://img.shields.io/badge/GUI-Swing-blue?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)

[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)A comprehensive **Java Swing ATM application** with a beautiful user interface, complete banking functionality, and account creation capabilities.A comprehensive ATM (Automated Teller Machine) interface built in Java with a modern, user-friendly GUI. This system simulates real-world ATM functionality with security features, transaction management, and beautiful interface design.

[![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen?style=for-the-badge)](https://github.com/yourusername/MyBanker-ATM)



> A comprehensive Java Swing ATM application featuring modern UI design, complete banking operations, and advanced account management capabilities.

![ATM System](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java)## Features

## 📋 Table of Contents

![GUI Framework](https://img.shields.io/badge/GUI-Swing-blue?style=for-the-badge)

- [Features](#-features)

- [Demo](#-demo)![Status](https://img.shields.io/badge/Status-Production%20Ready-green?style=for-the-badge)### 🔐 Security Features

- [Quick Start](#-quick-start)

- [Installation](#-installation)- **Card Number & PIN Authentication**: 16-digit card number with 4-digit PIN validation

- [Usage](#-usage)

- [Architecture](#-architecture)## ✨ Features- **PIN Attempt Limits**: Cards get blocked after 3 failed PIN attempts

- [Contributing](#-contributing)

- [License](#-license)- **Session Management**: Automatic session timeout after 5 minutes of inactivity



## ✨ Features### 🏦 **Core Banking Operations**- **Card Status Management**: Support for active, blocked, expired, and cancelled cards



### 🏦 Core Banking Operations- **Cash Withdrawal** with denomination selection

- **💰 Cash Withdrawal** - Multiple denomination options with receipt generation

- **💸 Fund Transfer** - Secure money transfers between accounts- **Fund Transfer** between accounts### 💳 ATM Operations

- **💳 Balance Inquiry** - Real-time account balance checking

- **📋 Transaction History** - Detailed mini-statements with transaction logs- **Balance Inquiry** with real-time updates- **Cash Withdrawal**: Quick amounts ($20, $50, $100, $200, $500) or custom amounts

- **🔐 PIN Management** - Secure PIN change functionality

- **🧾 Receipt System** - Professional transaction receipts- **Transaction History** with detailed mini-statements- **Fund Transfer**: Transfer money between accounts with confirmation



### 👤 Advanced Account Management- **PIN Change** functionality- **Balance Inquiry**: Check account balance with optional receipt

- **🆕 Account Creation** - Complete new customer onboarding

- **🏢 Multi-Account Support** - Manage multiple accounts per user- **Receipt Generation** for all transactions- **Transaction History**: View recent transactions with detailed information

- **📊 Account Types** - Checking, Savings, and Business accounts

- **⚡ Real-time Validation** - Instant form validation and feedback- **PIN Change**: Secure PIN change functionality

- **🔒 Session Management** - Secure session handling with timeout

### 👤 **Account Management**

### 🎨 Modern User Interface

- **📱 Responsive Design** - Adapts to different screen sizes- **Account Creation** for new customers### 🎨 Beautiful User Interface

- **🖥️ Maximization Support** - Full-screen capability

- **🎯 Professional Styling** - Consistent modern design language- **Multi-account support** for existing users- **Modern Design**: Clean, professional interface using Java Swing

- **✅ Form Validation** - Real-time input validation and formatting

- **🧭 Intuitive Navigation** - User-friendly interface flow- **Multiple Account Types**: Checking, Savings, Business- **Responsive Layout**: Well-organized screens with intuitive navigation



### 🔐 Security Features- **Real-time account validation**- **Custom Components**: Styled buttons, input fields, and cards

- **🛡️ PIN Authentication** - Secure 4-digit PIN system

- **⏰ Session Timeout** - Automatic logout after 5 minutes- **Session management** with security timeout- **Color-coded Elements**: Success/error messages with appropriate colors

- **🚫 Failed Attempt Protection** - Account lockout after 3 failed attempts

- **🔒 Data Validation** - Comprehensive input sanitization- **ATM-like Experience**: Simulates real ATM interface flow

- **📝 Audit Trail** - Complete transaction logging

### 🎨 **Enhanced User Interface**

## 🎥 Demo

- **Modern Design** with professional styling### 📄 Digital Receipts

## 🎥 Demo & Screenshots

### 1. Welcome & Login Screen

![ATM Login Interface](docs/login-screen.png)

**Screenshot Title: "ATM Login Interface - Card Number Entry and Authentication"**

*Modern login interface featuring automatic card number formatting, secure PIN entry, and prominent "Create New Account" option for new customers. Shows the professional banking aesthetic with clear security indicators.*

### 2. Account Creation Wizard

![Account Registration Form](docs/account-creation.png)

**Screenshot Title: "Account Registration Form - New Customer Onboarding"**

*Enhanced two-panel registration interface with organized form sections on the left and welcome information on the right. Demonstrates real-time validation, account type selection, and security setup for new customers.*

### 3. Main ATM Operations Menu

![Main ATM Menu](docs/main-interface.png)

**Screenshot Title: "Main ATM Operations Menu - Banking Services Dashboard"**

*Comprehensive ATM operations interface showing all available banking services including withdrawal, transfer, balance inquiry, transaction history, PIN change, and account creation. Features professional button styling and account information display.*

### 4. Transaction Processing

![Transaction Processing](docs/transaction.png)

**Screenshot Title: "Transaction Processing - Real-time Banking Operations"**

*Smooth transaction processing interface with real-time feedback, confirmation dialogs, and progress indicators for all banking operations.*

### 5. Cash Withdrawal Interface

![Cash Withdrawal](docs/withdrawal-screen.png)

**Screenshot Title: "Cash Withdrawal Interface - Amount Selection and Processing"**

*Withdrawal screen showing quick amount buttons ($20, $50, $100, $200, $500) and custom amount entry option. Includes balance validation and denomination selection for realistic ATM experience.*

### 6. Fund Transfer System

![Money Transfer](docs/transfer-screen.png)

**Screenshot Title: "Fund Transfer Interface - Secure Money Transfer System"**

*Fund transfer screen with destination account entry, amount validation, and transfer confirmation. Shows the step-by-step transfer process with security checks and real-time balance updates.*

### 7. Balance Inquiry & Account Information

![Balance Inquiry](docs/balance-screen.png)

**Screenshot Title: "Balance Inquiry - Account Summary and Information"**

*Balance inquiry screen displaying current account balance, available balance, account type, and recent transaction summary with clear financial information.*

### 8. Transaction History & Mini Statement

![Transaction History](docs/transaction-history.png)

**Screenshot Title: "Transaction History - Mini Statement and Account Activity"**

*Detailed transaction history interface displaying recent account activity with transaction types, amounts, dates, and reference numbers. Includes filtering options and balance information.*

### 9. Digital Receipt System

![Transaction Receipt](docs/receipt-screen.png)

**Screenshot Title: "Digital Receipt - Transaction Confirmation and Details"**

*Professional transaction receipt showing complete transaction details including reference number, timestamp, account information, transaction amount, and remaining balance. Simulates real ATM receipt printing.*

### 10. PIN Change Security Interface

![PIN Change](docs/pin-change-screen.png)

**Screenshot Title: "PIN Change Interface - Secure Password Management"**

*Secure PIN change interface with current PIN verification, new PIN entry with confirmation, and security validation. Shows the multi-step security process for PIN updates.*

### 11. Error Handling & Security Alerts

![Security Alert](docs/security-alert.png)

**Screenshot Title: "Security Alert - Failed Authentication and Error Handling"**

*Security alert interface showing failed login attempts, card blocking warnings, and system error messages with appropriate user guidance and security information.*

### 12. Session Timeout Warning

![Session Timeout](docs/session-timeout.png)

**Screenshot Title: "Session Timeout Warning - Security and Session Management"**

*Session timeout warning dialog with countdown timer, session extension option, and automatic logout functionality for enhanced security.*

- **PIN-based authentication**- **Transaction Logging**: Complete transaction history with status tracking

## 🚀 Quick Start

- **Session timeout** (5 minutes)- **User Account Management**: Multiple accounts per user support

### Prerequisites

- **Java 8** or higher- **Failed attempt protection** (max 3 attempts)

- Any operating system (Windows, macOS, Linux)

- **Secure transaction processing**## Project Structure



## License

This project is created for educational and demonstration purposes. Feel free to use, modify, and learn from the code.

---

**MyBanker ATM System** - Providing secure, reliable, and user-friendly banking services 24/7.
