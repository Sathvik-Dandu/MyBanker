@echo off
echo ================================
echo    MyBanker ATM System v1.0
echo ================================
echo.
echo Starting ATM Application...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 8 or higher and try again
    echo Download Java from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

REM Run the ATM application
java -jar MyBanker-ATM.jar

echo.
echo ATM Application has closed.
pause