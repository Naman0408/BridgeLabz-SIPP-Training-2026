/**
 * Hospital Billing System - Handles division by zero, invalid patient index,
 * number format exceptions, and payment failures
 */
public class HospitalBillingSystem {
    
    private static class Bill {
        int patientId;
        String patientName;
        double totalAmount;
        int itemCount;
        
        Bill(int id, String name, double amount, int count) {
            this.patientId = id;
            this.patientName = name;
            this.totalAmount = amount;
            this.itemCount = count;
        }
    }
    
    private Bill[] bills = new Bill[100]; // Array to store patient bills
    private int billCount = 0;
    private static final double MAX_BALANCE = 50000; // Maximum hospital credit balance
    
    /**
     * Add a bill to the system
     */
    public void addBill(int patientId, String patientName, double totalAmount, int itemCount) {
        if (billCount < bills.length) {
            bills[billCount++] = new Bill(patientId, patientName, totalAmount, itemCount);
            System.out.println("✓ Bill added for " + patientName);
        }
    }
    
    /**
     * Calculate cost per item - handles division by zero
     */
    public double calculateCostPerItem(int billIndex) throws ArithmeticException {
        try {
            if (billIndex < 0 || billIndex >= billCount) {
                throw new ArrayIndexOutOfBoundsException("Invalid patient index: " + billIndex);
            }
            
            Bill bill = bills[billIndex];
            
            if (bill.itemCount == 0) {
                throw new ArithmeticException("Cannot calculate cost per item: Zero items in bill");
            }
            
            return bill.totalAmount / bill.itemCount;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Invalid Patient Index: " + e.getMessage());
            throw new RuntimeException("Patient record not found", e);
        }
    }
    
    /**
     * Process payment - handles number format and insufficient funds exceptions
     */
    public boolean processPayment(int billIndex, String paymentAmountStr) 
            throws InsufficientFundsException, NumberFormatException {
        try {
            // Handle invalid index
            if (billIndex < 0 || billIndex >= billCount) {
                throw new ArrayIndexOutOfBoundsException("Invalid patient index: " + billIndex);
            }
            
            Bill bill = bills[billIndex];
            
            // Parse payment amount - handles number format exception
            double paymentAmount;
            try {
                paymentAmount = Double.parseDouble(paymentAmountStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR - Invalid Payment Amount Format: '" + paymentAmountStr + "' is not a valid number");
                throw new NumberFormatException("Invalid payment amount: " + paymentAmountStr);
            }
            
            // Check if payment is sufficient
            if (paymentAmount < bill.totalAmount) {
                double shortfall = bill.totalAmount - paymentAmount;
                throw new InsufficientFundsException(bill.totalAmount, paymentAmount);
            }
            
            if (paymentAmount < 0) {
                throw new IllegalArgumentException("Payment amount cannot be negative: ₹" + paymentAmount);
            }
            
            // Process payment
            System.out.println("✓ Payment Successful! Amount: ₹" + paymentAmount + 
                             " for patient: " + bill.patientName);
            return true;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Array Out of Bounds: " + e.getMessage());
            return false;
        } catch (InsufficientFundsException e) {
            System.out.println("❌ ERROR - " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Get bill details with exception handling
     */
    public void getBillDetails(int billIndex) {
        try {
            if (billIndex < 0 || billIndex >= billCount) {
                throw new ArrayIndexOutOfBoundsException("Invalid patient index: " + billIndex);
            }
            
            Bill bill = bills[billIndex];
            System.out.println("\n--- Bill Details ---");
            System.out.println("Patient ID: " + bill.patientId);
            System.out.println("Patient Name: " + bill.patientName);
            System.out.println("Total Amount: ₹" + bill.totalAmount);
            System.out.println("Item Count: " + bill.itemCount);
            System.out.println("Cost per Item: ₹" + (bill.itemCount > 0 ? bill.totalAmount / bill.itemCount : 0));
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Cannot retrieve bill: " + e.getMessage());
        }
    }
    
    /**
     * Display all bills with comprehensive exception handling
     */
    public void displayAllBills() {
        if (billCount == 0) {
            System.out.println("No bills in the system.");
            return;
        }
        
        System.out.println("\n========== Hospital Billing System ==========");
        for (int i = 0; i < billCount; i++) {
            try {
                Bill bill = bills[i];
                System.out.printf("%d. %s - ₹%.2f (%d items)%n", 
                    (i+1), bill.patientName, bill.totalAmount, bill.itemCount);
            } catch (Exception e) {
                System.out.println("Error displaying bill #" + (i+1) + ": " + e.getMessage());
            }
        }
        System.out.println("=============================================\n");
    }
    
    // Main method to demonstrate the system
    public static void main(String[] args) {
        HospitalBillingSystem system = new HospitalBillingSystem();
        
        // Add some bills
        system.addBill(1, "Arjun Kumar", 5000, 10);
        system.addBill(2, "Priya Sharma", 8000, 8);
        system.addBill(3, "Raj Patel", 3000, 0); // Zero items for testing
        
        system.displayAllBills();
        
        // Test 1: Valid bill details
        System.out.println("Test 1: Retrieve bill details");
        system.getBillDetails(0);
        
        // Test 2: Invalid index
        System.out.println("\nTest 2: Invalid patient index");
        system.getBillDetails(10);
        
        // Test 3: Cost per item calculation (will fail for bill with 0 items)
        System.out.println("\nTest 3: Calculate cost per item");
        try {
            double costPerItem = system.calculateCostPerItem(0);
            System.out.println("✓ Cost per item: ₹" + costPerItem);
        } catch (ArithmeticException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        // Test 4: Division by zero - bill with zero items
        System.out.println("\nTest 4: Division by zero exception (0 items)");
        try {
            double costPerItem = system.calculateCostPerItem(2);
            System.out.println("Cost per item: ₹" + costPerItem);
        } catch (Exception e) {
            System.out.println("❌ Error caught: " + e.getMessage());
        }
        
        // Test 5: Valid payment
        System.out.println("\nTest 5: Valid payment");
        try {
            system.processPayment(0, "5000");
        } catch (InsufficientFundsException | NumberFormatException e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
        
        // Test 6: Invalid payment amount format
        System.out.println("\nTest 6: Invalid payment format");
        try {
            system.processPayment(1, "invalid_amount");
        } catch (InsufficientFundsException | NumberFormatException e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
        
        // Test 7: Insufficient funds
        System.out.println("\nTest 7: Insufficient funds");
        try {
            system.processPayment(1, "5000"); // Bill amount is 8000
        } catch (InsufficientFundsException e) {
            System.out.println("Shortfall: ₹" + e.getShortfall());
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Test 8: Negative payment
        System.out.println("\nTest 8: Negative payment amount");
        try {
            system.processPayment(0, "-1000");
        } catch (InsufficientFundsException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
