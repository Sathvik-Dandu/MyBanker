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

### Run Immediately

```bash- **Data validation** and sanitization

# Clone the repository

git clone https://github.com/yourusername/MyBanker-ATM.git```

cd MyBanker-ATM

## 🚀 Quick StartMyBanker/

# Compile and run

javac -d build src/com/atm/*.java src/com/atm/**/*.java├── src/

java -cp build com.atm.ATMApplication

```### Prerequisites│   └── com/



## 📦 Installation- **Java 8** or higher installed on your system│       └── atm/



### Option 1: Download Release- Windows, macOS, or Linux operating system│           ├── ATMApplication.java          # Main application entry point

1. Go to [Releases](https://github.com/yourusername/MyBanker-ATM/releases)

2. Download `MyBanker-ATM.jar`│           ├── models/                      # Data models

3. Double-click to run or use `java -jar MyBanker-ATM.jar`

### Installation & Running│           │   ├── User.java               # User information

### Option 2: Build from Source

```bash│           │   ├── Account.java            # Bank account details

# Clone repository

git clone https://github.com/yourusername/MyBanker-ATM.git#### Option 1: Double-click to Run│           │   ├── Card.java               # ATM card management

cd MyBanker-ATM

1. Download the `MyBanker-ATM.jar` file│           │   └── Transaction.java        # Transaction records

# Create build directory

mkdir build2. Double-click the JAR file to launch the application│           ├── services/                    # Business logic



# Compile all Java files│           │   ├── AuthenticationService.java  # Login & authentication

javac -d build src/com/atm/*.java src/com/atm/models/*.java src/com/atm/services/*.java src/com/atm/gui/*.java src/com/atm/utils/*.java

#### Option 2: Command Line│           │   └── ATMService.java         # ATM operations

# Create executable JAR

jar cfe MyBanker-ATM.jar com.atm.ATMApplication -C build .```bash│           ├── gui/                        # User interface



# Run applicationjava -jar MyBanker-ATM.jar│           │   ├── ATMMainFrame.java       # Main application window

java -jar MyBanker-ATM.jar

``````│           │   ├── LoginScreen.java        # Login interface



### Option 3: IDE Setup│           │   ├── MainATMScreen.java      # Main ATM operations

1. Clone the repository

2. Import project into your IDE (IntelliJ IDEA, Eclipse, etc.)#### Option 3: Windows Launcher│           │   └── UIComponents.java       # Custom UI components

3. Set main class: `com.atm.ATMApplication`

4. Run the application1. Download `start-atm.bat`│           └── utils/                      # Utilities



## 🎯 Usage2. Double-click the batch file for guided startup│               ├── DataCache.java          # In-memory data storage



### For New Customers│               ├── SampleDataInitializer.java # Test data setup

1. **Launch Application** - Start the ATM system

2. **Create Account** - Click "Create New Account" on login screen## 🎯 How to Use│               └── ReceiptGenerator.java   # Digital receipt system

3. **Fill Registration Form**:

   - Personal information (name, phone, email)```

   - Security setup (4-digit PIN)

   - Account type selection### For New Customers

   - Initial deposit (minimum $10)

4. **Account Activation** - Receive instant account and card numbers1. **Launch the application**## How to Run

5. **Start Banking** - Login with new credentials

2. **Click "Create New Account"** on the login screen

### For Existing Customers

1. **Enter Card Number** - 16-digit card number with auto-formatting3. **Fill out the registration form**:### Prerequisites

2. **Enter PIN** - Secure 4-digit PIN

3. **Select Service**:   - Personal information (name, phone, email)- Java Development Kit (JDK) 8 or higher

   - Cash withdrawal with denomination selection

   - Fund transfer with recipient validation   - Security setup (4-digit PIN)- Java Runtime Environment (JRE)

   - Balance inquiry with transaction history

   - PIN change with security verification   - Account type and initial deposit

   - Create additional accounts

4. **Complete account creation**### Compilation and Execution

### Test Accounts

| Card Number | PIN | Account Type | Balance |5. **Login with your new card number and PIN**

|-------------|-----|--------------|---------|

| `1234567890123456` | `1234` | Checking | $2,500.75 |1. **Navigate to the project directory:**

| `1234567890123457` | `5678` | Savings | $15,000.00 |

| `1234567890123458` | `9876` | Checking | $750.25 |### For Existing Customers   ```bash

| `1234567890123459` | `4321` | Business | $50,000.00 |

1. **Enter your 16-digit card number**   cd MyBanker

## 🏗️ Architecture

2. **Enter your 4-digit PIN**   ```

### Design Pattern: MVC (Model-View-Controller)

3. **Select from available services**:

```

src/   - Withdraw cash2. **Compile the Java files:**

└── com/atm/

    ├── ATMApplication.java          # Main entry point   - Transfer money   ```bash

    ├── models/                      # Data Models

    │   ├── User.java               # User entity   - Check balance   javac -d . src/com/atm/*.java src/com/atm/*/*.java

    │   ├── Account.java            # Account entity

    │   ├── Card.java               # Card entity   - View transaction history   ```

    │   └── Transaction.java        # Transaction entity

    ├── services/                    # Business Logic   - Change PIN

    │   ├── ATMService.java         # Core ATM operations

    │   └── AuthenticationService.java # Security & auth   - Create additional accounts3. **Run the application:**

    ├── gui/                         # User Interface

    │   ├── ATMMainFrame.java       # Main window   ```bash

    │   ├── LoginScreen.java        # Authentication UI

    │   ├── MainATMScreen.java      # Main menu interface### Test Cards Available   java com.atm.ATMApplication

    │   ├── AccountCreationScreen.java # Registration UI

    │   └── UIComponents.java       # Reusable UI components| Card Number | PIN | Account Type | Balance |   ```

    └── utils/                       # Utilities

        ├── DataCache.java          # In-memory storage|-------------|-----|--------------|---------|

        ├── SampleDataInitializer.java # Test data

        └── ReceiptGenerator.java   # Receipt formatting| 1234567890123456 | 1234 | Checking | $2,500.75 |### Alternative: Using IDE

```

| 1234567890123457 | 5678 | Savings | $15,000.00 |1. Import the project into your favorite Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Key Components

| 1234567890123458 | 9876 | Checking | $750.25 |2. Set the main class as `com.atm.ATMApplication`

#### Models

- **User**: Customer information and authentication data| 1234567890123459 | 4321 | Business | $50,000.00 |3. Run the application

- **Account**: Banking account details and balance management

- **Card**: Debit card information and validation

- **Transaction**: Financial transaction records and history

## 🛠️ Recent Improvements## Test Credentials

#### Services

- **ATMService**: Core banking operations (withdraw, transfer, balance)

- **AuthenticationService**: Security, login, and session management

### **Enhanced Registration Interface**The system comes with pre-loaded test data for demonstration:

#### GUI Components

- **ATMMainFrame**: Main application window with maximization support- **Professional side-by-side layout** with form and information panels

- **LoginScreen**: Authentication interface with card formatting

- **MainATMScreen**: Banking operations menu and navigation- **Comprehensive form validation** with real-time feedback### Primary Test Account

- **AccountCreationScreen**: Customer registration and onboarding

- **UIComponents**: Reusable styling and component library- **Benefits and security information** displayed prominently- **Card Number**: `1234567890123456`



#### Utilities- **Responsive design** that adapts to window size- **PIN**: `1234`

- **DataCache**: Thread-safe in-memory data storage

- **SampleDataInitializer**: Test data generation and setup- **Account Holder**: John Doe

- **ReceiptGenerator**: Transaction receipt formatting and printing

### **Window Management**- **Account Type**: Checking

### Technology Stack

- **Language**: Java 8+- **Maximization support** - Users can now maximize the window- **Balance**: $2,500.75

- **GUI Framework**: Java Swing

- **Architecture**: Model-View-Controller (MVC)- **Minimum size constraints** to maintain usability

- **Storage**: In-memory with ConcurrentHashMap

- **Security**: PIN-based authentication with session management- **Responsive layouts** that adapt to window size### Additional Test Accounts



## 🔧 Development- **Scroll support** for content that exceeds screen size- **Card**: `1234567890123457`, **PIN**: `5678` (John's Savings - $15,000.00)



### Prerequisites- **Card**: `1234567890123458`, **PIN**: `9876` (Jane Smith - $750.25)

- Java Development Kit (JDK) 8 or higher

- IDE (IntelliJ IDEA, Eclipse, VS Code)### **User Experience Enhancements**- **Card**: `1234567890123459`, **PIN**: `4321` (Bob Johnson - $50,000.00)

- Git for version control

- **Intuitive navigation** between all screens

### Setup Development Environment

```bash- **Clear visual feedback** for all user actions### Test Scenarios

# Clone repository

git clone https://github.com/yourusername/MyBanker-ATM.git- **Professional styling** with consistent design language- **Blocked Card**: `1111111111111111`, **PIN**: `0000` (for testing blocked card functionality)

cd MyBanker-ATM

- **Improved accessibility** with proper focus management

# Create development build

javac -d build src/com/atm/**/*.java## Usage Instructions



# Run in development mode## 📱 Application Screens

java -cp build com.atm.ATMApplication

```### 1. Login



### Code Style### Login Screen- Enter your 16-digit card number

- Follow Java naming conventions

- Use meaningful variable and method names- Modern login interface with automatic card number formatting- Enter your 4-digit PIN

- Comment complex business logic

- Maintain consistent indentation (4 spaces)- "Create New Account" option prominently displayed for new customers- Click "LOGIN" or press Enter

- Keep methods focused and small

- Security indicators and banking information

### Testing

- Test with provided sample accounts### 2. Main Menu Operations

- Verify all transaction types

- Test error handling and validation### Enhanced Account Creation- **WITHDRAWAL**: Select quick amounts or enter custom amount

- Check UI responsiveness at different screen sizes

- Validate security features (PIN attempts, session timeout)- **Professional two-panel layout**- **TRANSFER**: Enter destination account and amount



## 🤝 Contributing- **Left Panel**: Registration form with organized sections- **BALANCE INQUIRY**: View current account balance



We welcome contributions! Please follow these steps:- **Right Panel**: Welcome message, benefits list, and security information- **TRANSACTION HISTORY**: Review recent transactions



1. **Fork** the repository- **Real-time validation** with helpful error messages- **CHANGE PIN**: Update your PIN securely

2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)

3. **Commit** your changes (`git commit -m 'Add amazing feature'`)- **Account type selection** with descriptions- **EXIT**: Logout and return to login screen

4. **Push** to the branch (`git push origin feature/amazing-feature`)

5. **Open** a Pull Request



### Contribution Guidelines### Main ATM Interface### 3. Security Features

- Ensure your code follows the existing style

- Add comments for complex functionality- **Expanded menu grid** with "Create Account" option- Sessions automatically timeout after 5 minutes of inactivity

- Test thoroughly before submitting

- Update documentation if needed- **Real-time balance display** and account information- Cards are blocked after 3 failed PIN attempts

- Include screenshots for UI changes

- **Professional button styling** with hover effects- All transactions require confirmation for transfers

## 📝 License

- **Help system** and quick access features

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### 4. Receipt Generation

## 🙏 Acknowledgments

---- After each transaction, you'll be asked if you want a receipt

- Java Swing documentation and community

- Banking industry standards for UI/UX inspiration- Receipts show complete transaction details including reference numbers

- Open source community for best practices

**🎉 Your ATM System is Now Live and Ready to Use!**- Print simulation provides formatted receipt output

## 📞 Support



- **Issues**: [GitHub Issues](https://github.com/yourusername/MyBanker-ATM/issues)

- **Discussions**: [GitHub Discussions](https://github.com/yourusername/MyBanker-ATM/discussions)The application starts **maximized** by default and includes a completely redesigned registration interface that provides an excellent user experience for new customer onboarding.## Technical Features

- **Documentation**: [Wiki](https://github.com/yourusername/MyBanker-ATM/wiki)



## 🗺️ Roadmap

**Made with ❤️ using Java Swing**### Architecture

- [ ] Database integration (MySQL/PostgreSQL)- **MVC Pattern**: Clean separation of models, views, and controllers

- [ ] Web-based interface using Spring Boot- **Service Layer**: Business logic encapsulated in service classes

- [ ] Mobile app development- **Singleton Pattern**: Used for services and data cache

- [ ] Advanced reporting and analytics- **Observer Pattern**: UI updates based on data changes

- [ ] Multi-language support

- [ ] Biometric authentication### Security Implementation

- PIN validation with attempt limiting

---- Session management with timeout

- Card status validation (active, blocked, expired)

<div align="center">- Transaction amount limits and validation



**⭐ Star this repository if you found it helpful!**### Data Management

- Thread-safe in-memory cache using ConcurrentHashMap

Made with ❤️ by [Your Name](https://github.com/yourusername)- Comprehensive transaction logging

- Session cleanup and management

[⬆ Back to Top](#-mybanker-atm-system)- Sample data initialization for testing



</div>### UI/UX Features
- Modern color scheme and typography
- Responsive layout design
- Hover effects and visual feedback
- Error handling with user-friendly messages
- Loading states for better user experience

## Extensibility

The system is designed for easy extension:

### Adding New Features
- **New Transaction Types**: Extend the Transaction.TransactionType enum
- **Additional Services**: Create new service classes following the existing pattern
- **Enhanced Security**: Add biometric authentication or two-factor authentication
- **Database Integration**: Replace DataCache with database connectivity
- **Network Operations**: Add support for real banking system integration

### Customization Options
- **UI Themes**: Modify UIComponents.java for different color schemes
- **Business Rules**: Update validation logic in service classes
- **Transaction Limits**: Customize limits in Account and ATMService classes
- **Session Configuration**: Modify timeout values in DataCache.UserSession

## Future Enhancements

Potential improvements for the system:

1. **Database Integration**: Replace in-memory storage with persistent database
2. **Network Support**: Add support for distributed ATM network
3. **Enhanced Security**: Implement encryption for sensitive data
4. **Mobile App**: Create companion mobile application
5. **Admin Interface**: Add administrative functions for bank staff
6. **Multi-language Support**: Internationalization and localization
7. **Accessibility Features**: Enhanced support for users with disabilities
8. **Real Printer Integration**: Interface with actual receipt printers
9. **Card Reader Simulation**: Simulate card reader hardware
10. **Cash Dispenser Simulation**: Simulate physical cash dispensing

## Contributing

This project demonstrates professional Java development practices including:
- Clean code architecture
- Comprehensive documentation
- Error handling and validation
- Security considerations
- User experience design
- Extensible design patterns

## License

This project is created for educational and demonstration purposes. Feel free to use, modify, and learn from the code.

---

**MyBanker ATM System** - Providing secure, reliable, and user-friendly banking services 24/7.