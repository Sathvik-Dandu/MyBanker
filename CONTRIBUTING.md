# Contributing to MyBanker ATM System

Thank you for your interest in contributing to the MyBanker ATM System! This document provides guidelines and information for contributors.

## 🤝 How to Contribute

### Reporting Issues
- **Search existing issues** before creating a new one
- **Use clear, descriptive titles** for issues
- **Provide detailed reproduction steps** for bugs
- **Include system information** (OS, Java version)
- **Add screenshots** for UI-related issues

### Suggesting Features
- **Check existing feature requests** first
- **Clearly describe the feature** and its benefits
- **Explain the use case** and target users
- **Consider implementation complexity**

### Code Contributions

#### Getting Started
1. **Fork** the repository
2. **Clone** your fork locally
3. **Create** a new branch for your feature/fix
4. **Make** your changes
5. **Test** thoroughly
6. **Submit** a pull request

#### Development Setup
```bash
# Clone your fork
git clone https://github.com/yourusername/MyBanker-ATM.git
cd MyBanker-ATM

# Create feature branch
git checkout -b feature/your-feature-name

# Compile and test
javac -d build src/com/atm/**/*.java
java -cp build com.atm.ATMApplication
```

## 📝 Code Style Guidelines

### Java Conventions
- **Package names**: lowercase (com.atm.services)
- **Class names**: PascalCase (ATMService)
- **Method names**: camelCase (createAccount)
- **Variable names**: camelCase (accountNumber)
- **Constants**: UPPER_SNAKE_CASE (MAX_PIN_ATTEMPTS)

### Code Quality
- **Meaningful names** for variables and methods
- **Single responsibility** principle for methods
- **Proper error handling** with try-catch blocks
- **Comments** for complex business logic
- **Consistent indentation** (4 spaces)

### Swing UI Guidelines
- **Consistent styling** using UIComponents class
- **Responsive layouts** that work at different sizes
- **Proper event handling** with ActionListeners
- **User-friendly error messages**
- **Accessibility** considerations

## 🧪 Testing Guidelines

### Manual Testing Checklist
- [ ] Login with valid/invalid credentials
- [ ] Account creation flow
- [ ] All transaction types (withdraw, transfer, balance)
- [ ] PIN change functionality
- [ ] Session timeout behavior
- [ ] UI responsiveness at different screen sizes
- [ ] Error handling and validation

### Test Accounts
Use these test accounts for development:
- Card: 1234567890123456, PIN: 1234 (Checking: $2,500.75)
- Card: 1234567890123457, PIN: 5678 (Savings: $15,000.00)
- Card: 1234567890123458, PIN: 9876 (Checking: $750.25)
- Card: 1234567890123459, PIN: 4321 (Business: $50,000.00)

## 📋 Pull Request Process

### Before Submitting
- [ ] **Code compiles** without warnings
- [ ] **All functionality tested** manually
- [ ] **Code follows** style guidelines
- [ ] **Documentation updated** if needed
- [ ] **No build artifacts** included

### PR Description Template
```markdown
## Description
Brief description of changes made

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Manual testing completed
- [ ] Test cases added/updated
- [ ] No regressions found

## Screenshots (if UI changes)
Add screenshots here

## Additional Notes
Any additional information
```

### Review Process
1. **Automated checks** must pass
2. **Code review** by maintainers
3. **Testing** by reviewers
4. **Merge** after approval

## 🏗️ Architecture Guidelines

### Adding New Features
- **Follow MVC pattern** (Model-View-Controller)
- **Use existing services** when possible
- **Create reusable components** in UIComponents
- **Handle errors gracefully**
- **Update help documentation**

### Database Integration (Future)
- **Prepare for database migration** from in-memory storage
- **Use abstraction layers** for data access
- **Consider transaction management**
- **Plan for data migration**

### UI Enhancements
- **Maintain consistent styling**
- **Support window resizing**
- **Add proper focus management**
- **Include hover effects**
- **Provide user feedback**

## 🚀 Release Process

### Version Numbering
- **Major.Minor.Patch** (e.g., 1.2.3)
- **Major**: Breaking changes
- **Minor**: New features (backward compatible)
- **Patch**: Bug fixes

### Release Checklist
- [ ] All tests pass
- [ ] Documentation updated
- [ ] Version number incremented
- [ ] Release notes created
- [ ] JAR file built and tested
- [ ] GitHub release created

## 💡 Ideas for Contributions

### Beginner-Friendly
- **UI improvements** (colors, fonts, layouts)
- **Input validation** enhancements
- **Error message** improvements
- **Help documentation** updates
- **Code comments** and documentation

### Intermediate
- **New transaction types** (check deposit, bill pay)
- **Advanced reporting** features
- **Export functionality** (PDF, CSV)
- **Enhanced security** features
- **Performance optimizations**

### Advanced
- **Database integration** (MySQL, PostgreSQL)
- **Web interface** using Spring Boot
- **REST API** development
- **Multi-language support**
- **Plugin architecture**

## 📞 Getting Help

### Community Support
- **GitHub Discussions**: Ask questions and share ideas
- **Issues**: Report bugs and request features
- **Wiki**: Find detailed documentation
- **Code Reviews**: Learn from feedback

### Contact Maintainers
- **@yourusername**: Project lead
- **Email**: your.email@example.com

## 🙏 Recognition

Contributors will be:
- **Listed** in the README
- **Credited** in release notes
- **Invited** to be maintainers (for significant contributions)

Thank you for contributing to MyBanker ATM System! 🏧✨