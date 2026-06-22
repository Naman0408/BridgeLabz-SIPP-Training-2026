/**
 * Custom exception for insufficient balance in ATM withdrawal
 */
public class InsufficientBalanceException extends Exception {
    private double accountBalance;
    private double withdrawalAmount;
    
    public InsufficientBalanceException(double balance, double withdrawal) {
        super(String.format("❌ Withdrawal Failed! Account Balance: ₹%.2f, Requested Amount: ₹%.2f, Shortfall: ₹%.2f",
                balance, withdrawal, (withdrawal - balance)));
        this.accountBalance = balance;
        this.withdrawalAmount = withdrawal;
    }
    
    public double getAccountBalance() {
        return accountBalance;
    }
    
    public double getWithdrawalAmount() {
        return withdrawalAmount;
    }
    
    public double getShortfall() {
        return withdrawalAmount - accountBalance;
    }
}
