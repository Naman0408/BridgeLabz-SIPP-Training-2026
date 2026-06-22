/**
 * Custom exception for insufficient funds in payment/billing system
 */
public class InsufficientFundsException extends Exception {
    private double requiredAmount;
    private double availableAmount;
    
    public InsufficientFundsException(double required, double available) {
        super(String.format("Insufficient funds! Required: ₹%.2f, Available: ₹%.2f, Shortfall: ₹%.2f",
                required, available, (required - available)));
        this.requiredAmount = required;
        this.availableAmount = available;
    }
    
    public double getRequiredAmount() {
        return requiredAmount;
    }
    
    public double getAvailableAmount() {
        return availableAmount;
    }
    
    public double getShortfall() {
        return requiredAmount - availableAmount;
    }
}
