/**
 * ATM Withdrawal System - Demonstrates custom InsufficientBalanceException
 * Scenario: Account balance = ₹5,000, Withdrawal request = ₹8,000
 */
public class ATMWithdrawalSystem {
    
    private static class Account {
        String accountHolder;
        String accountNumber;
        double balance;
        
        Account(String holder, String number, double initialBalance) {
            this.accountHolder = holder;
            this.accountNumber = number;
            this.balance = initialBalance;
        }
    }
    
    private Account[] accounts = new Account[100];
    private int accountCount = 0;
    
    /**
     * Add an account to the system
     */
    public void addAccount(String holder, String accountNumber, double initialBalance) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = new Account(holder, accountNumber, initialBalance);
            System.out.println("✓ Account created for " + holder + " with balance: ₹" + initialBalance);
        }
    }
    
    /**
     * Withdraw money from account - handles insufficient balance
     * Exception Information Should Contain:
     * 1. Current account balance
     * 2. Requested withdrawal amount
     * 3. Shortfall amount
     * 4. Account holder name
     * 5. Account number (last 4 digits for security)
     */
    public boolean withdrawMoney(int accountIndex, double withdrawalAmount) 
            throws InsufficientBalanceException {
        try {
            // Validate account index
            if (accountIndex < 0 || accountIndex >= accountCount) {
                throw new ArrayIndexOutOfBoundsException("Invalid account index: " + accountIndex);
            }
            
            Account account = accounts[accountIndex];
            
            // Validate withdrawal amount
            if (withdrawalAmount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive: ₹" + withdrawalAmount);
            }
            
            // Check balance
            if (withdrawalAmount > account.balance) {
                // Throw custom exception with detailed information
                throw new InsufficientBalanceException(account.balance, withdrawalAmount);
            }
            
            // Process withdrawal
            account.balance -= withdrawalAmount;
            System.out.println("✓ Withdrawal Successful!");
            System.out.println("  Account: " + account.accountHolder + " (" + 
                             maskAccountNumber(account.accountNumber) + ")");
            System.out.println("  Amount Withdrawn: ₹" + withdrawalAmount);
            System.out.println("  Remaining Balance: ₹" + account.balance);
            return true;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Deposit money into account
     */
    public boolean depositMoney(int accountIndex, double depositAmount) {
        try {
            if (accountIndex < 0 || accountIndex >= accountCount) {
                throw new ArrayIndexOutOfBoundsException("Invalid account index: " + accountIndex);
            }
            
            Account account = accounts[accountIndex];
            
            if (depositAmount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive: ₹" + depositAmount);
            }
            
            account.balance += depositAmount;
            System.out.println("✓ Deposit Successful!");
            System.out.println("  Amount Deposited: ₹" + depositAmount);
            System.out.println("  New Balance: ₹" + account.balance);
            return true;
            
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("❌ ERROR - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Check account balance
     */
    public void checkBalance(int accountIndex) {
        try {
            if (accountIndex < 0 || accountIndex >= accountCount) {
                throw new ArrayIndexOutOfBoundsException("Invalid account index: " + accountIndex);
            }
            
            Account account = accounts[accountIndex];
            System.out.println("\n--- Account Balance ---");
            System.out.println("Account Holder: " + account.accountHolder);
            System.out.println("Account Number: " + maskAccountNumber(account.accountNumber));
            System.out.println("Current Balance: ₹" + account.balance);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - " + e.getMessage());
        }
    }
    
    /**
     * Request withdrawal with where the exception should be handled
     * Exception Handling Location: In the calling method (withdrawMoney)
     * This demonstrates proper exception propagation
     */
    public void initiateWithdrawal(int accountIndex, double amount) {
        try {
            System.out.println("\n--- Withdrawal Request ---");
            checkBalance(accountIndex);
            System.out.println("Requested Amount: ₹" + amount);
            
            withdrawMoney(accountIndex, amount);
            
        } catch (InsufficientBalanceException e) {
            // Exception is handled here - display comprehensive error info
            System.out.println("\n" + e.getMessage());
            System.out.println("Available Options:");
            System.out.println("1. Withdraw a smaller amount");
            System.out.println("2. Make a deposit");
            System.out.println("3. Request for overdraft facility (if eligible)");
        }
    }
    
    /**
     * Mask account number for security (show only last 4 digits)
     */
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber.length() <= 4) {
            return "****";
        }
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
    
    /**
     * Display all accounts
     */
    public void displayAllAccounts() {
        System.out.println("\n========== ATM System - All Accounts ==========");
        for (int i = 0; i < accountCount; i++) {
            Account account = accounts[i];
            System.out.printf("%d. %s - Balance: ₹%.2f%n", 
                (i+1), account.accountHolder, account.balance);
        }
        System.out.println("==============================================\n");
    }
    
    // Main method to demonstrate the system
    public static void main(String[] args) {
        ATMWithdrawalSystem atm = new ATMWithdrawalSystem();
        
        // Create accounts
        atm.addAccount("Arjun Kumar", "SBI1234567890", 5000);
        atm.addAccount("Priya Sharma", "ICICI9876543210", 15000);
        atm.addAccount("Raj Patel", "HDFC1111111111", 2000);
        
        atm.displayAllAccounts();
        
        // Test 1: Valid withdrawal
        System.out.println("Test 1: Valid withdrawal");
        atm.initiateWithdrawal(0, 2000);
        
        // Test 2: Insufficient balance (Scenario from problem)
        System.out.println("\nTest 2: Insufficient balance (₹5,000 balance, ₹8,000 request)");
        // Reset balance for demonstration
        atm.addAccount("Rajesh", "BANK5678901234", 5000);
        atm.initiateWithdrawal(3, 8000);
        
        // Test 3: Another insufficient balance scenario
        System.out.println("\nTest 3: Another insufficient balance");
        atm.initiateWithdrawal(2, 5000);
        
        // Test 4: Deposit and then withdraw
        System.out.println("\nTest 4: Deposit money then withdraw");
        System.out.println("--- Deposit ₹3000 ---");
        atm.depositMoney(2, 3000);
        System.out.println("--- Now trying to withdraw ₹4000 ---");
        atm.initiateWithdrawal(2, 4000);
        
        // Test 5: Check balance
        System.out.println("\nTest 5: Check balance");
        atm.checkBalance(0);
        
        // Test 6: Invalid account index
        System.out.println("\nTest 6: Invalid account index");
        atm.checkBalance(10);
        
        // Test 7: Negative withdrawal amount
        System.out.println("\nTest 7: Negative withdrawal amount");
        try {
            atm.withdrawMoney(0, -1000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Final display
        atm.displayAllAccounts();
    }
}
