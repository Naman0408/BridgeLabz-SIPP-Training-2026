/**
 * Password Strength Checker - Comprehensive exception handling for password validation
 * Password Rules:
 * 1. The first character must be uppercase
 * 2. The last character must be a digit
 * 3. Password length must be at least 8 characters
 * 4. Password must contain at least one special character (@, #, $, %, &, *)
 * 
 * Handles:
 * - Empty string ("")
 * - Null password
 * - Password shorter than 8 characters
 * - No special characters
 * - First character not uppercase
 * - Last character not a digit
 */
public class PasswordStrengthChecker {
    
    private static final int MIN_LENGTH = 8;
    private static final String SPECIAL_CHARS = "@#$%&*";
    
    /**
     * Validate password strength - throws custom exception for violations
     * Checks all rules and throws exception with specific reason
     */
    public void checkPassword(String password) throws PasswordValidationException {
        try {
            // Check 1: Null password
            if (password == null) {
                throw new PasswordValidationException("Password cannot be null (empty input)");
            }
            
            // Check 2: Empty password
            if (password.isEmpty() || password.trim().isEmpty()) {
                throw new PasswordValidationException("Password cannot be empty");
            }
            
            // Check 3: Length validation
            if (password.length() < MIN_LENGTH) {
                throw new PasswordValidationException(
                    String.format("Password must be at least %d characters long. Current length: %d", 
                    MIN_LENGTH, password.length()));
            }
            
            // Check 4: First character must be uppercase
            if (!Character.isUpperCase(password.charAt(0))) {
                throw new PasswordValidationException(
                    String.format("First character must be uppercase. Found: '%c'", password.charAt(0)));
            }
            
            // Check 5: Last character must be a digit
            if (!Character.isDigit(password.charAt(password.length() - 1))) {
                throw new PasswordValidationException(
                    String.format("Last character must be a digit. Found: '%c'", password.charAt(password.length() - 1)));
            }
            
            // Check 6: At least one special character
            boolean hasSpecialChar = false;
            for (char c : password.toCharArray()) {
                if (SPECIAL_CHARS.indexOf(c) >= 0) {
                    hasSpecialChar = true;
                    break;
                }
            }
            
            if (!hasSpecialChar) {
                throw new PasswordValidationException(
                    "Password must contain at least one special character: @, #, $, %, &, *");
            }
            
            // If all checks pass
            System.out.println("✓ PASSWORD VALID - All rules satisfied!");
            System.out.println("  Length: " + password.length() + " characters");
            System.out.println("  First character: Uppercase '" + password.charAt(0) + "'");
            System.out.println("  Last character: Digit '" + password.charAt(password.length() - 1) + "'");
            System.out.println("  Contains special character: Yes");
            
        } catch (PasswordValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new PasswordValidationException("Unexpected error during validation: " + e.getMessage());
        }
    }
    
    /**
     * Detailed password validation - returns detailed feedback without throwing
     */
    public PasswordValidationResult validatePasswordDetailed(String password) {
        PasswordValidationResult result = new PasswordValidationResult();
        
        try {
            // Check: Null password
            if (password == null) {
                result.isValid = false;
                result.errorMessages.add("Password is null (empty input)");
                return result;
            }
            
            // Check: Empty password
            if (password.isEmpty()) {
                result.isValid = false;
                result.errorMessages.add("Password is empty");
                return result;
            }
            
            result.password = password;
            result.length = password.length();
            
            // Check: Length
            if (password.length() < MIN_LENGTH) {
                result.isValid = false;
                result.errorMessages.add("Length must be at least " + MIN_LENGTH + " (current: " + password.length() + ")");
            } else {
                result.passedChecks.add("✓ Length: " + password.length());
            }
            
            // Check: First character uppercase
            if (!Character.isUpperCase(password.charAt(0))) {
                result.isValid = false;
                result.errorMessages.add("First character must be uppercase (found: '" + password.charAt(0) + "')");
            } else {
                result.passedChecks.add("✓ First character is uppercase: '" + password.charAt(0) + "'");
            }
            
            // Check: Last character digit
            if (!Character.isDigit(password.charAt(password.length() - 1))) {
                result.isValid = false;
                result.errorMessages.add("Last character must be a digit (found: '" + password.charAt(password.length() - 1) + "')");
            } else {
                result.passedChecks.add("✓ Last character is a digit: '" + password.charAt(password.length() - 1) + "'");
            }
            
            // Check: Special character
            boolean hasSpecialChar = false;
            char foundSpecialChar = '\0';
            for (char c : password.toCharArray()) {
                if (SPECIAL_CHARS.indexOf(c) >= 0) {
                    hasSpecialChar = true;
                    foundSpecialChar = c;
                    break;
                }
            }
            
            if (!hasSpecialChar) {
                result.isValid = false;
                result.errorMessages.add("Must contain at least one special character: @, #, $, %, &, *");
            } else {
                result.passedChecks.add("✓ Contains special character: '" + foundSpecialChar + "'");
            }
            
            // Mark as valid only if no errors
            if (result.errorMessages.isEmpty()) {
                result.isValid = true;
            }
            
        } catch (Exception e) {
            result.isValid = false;
            result.errorMessages.add("Unexpected error: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Result class to hold validation feedback
     */
    public static class PasswordValidationResult {
        public boolean isValid = false;
        public String password = "";
        public int length = 0;
        public java.util.List<String> passedChecks = new java.util.ArrayList<>();
        public java.util.List<String> errorMessages = new java.util.ArrayList<>();
        
        public void displayResult() {
            System.out.println("\n========== Password Validation Result ==========");
            System.out.println("Password: " + (password.isEmpty() ? "[Empty]" : "*".repeat(password.length())));
            System.out.println("Status: " + (isValid ? "✓ VALID" : "✗ INVALID"));
            System.out.println("Length: " + length + "/" + MIN_LENGTH);
            
            if (!passedChecks.isEmpty()) {
                System.out.println("\nPassed Checks:");
                for (String check : passedChecks) {
                    System.out.println("  " + check);
                }
            }
            
            if (!errorMessages.isEmpty()) {
                System.out.println("\nViolations:");
                for (String error : errorMessages) {
                    System.out.println("  ❌ " + error);
                }
            }
            
            System.out.println("================================================\n");
        }
    }
    
    /**
     * Interactive password checker
     */
    public void interactiveCheck(String password) {
        System.out.println("\n--- Checking Password ---");
        System.out.println("Input: " + (password == null ? "null" : password.isEmpty() ? "\"\"" : password));
        
        try {
            checkPassword(password);
        } catch (PasswordValidationException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Main method to demonstrate the system
    public static void main(String[] args) {
        PasswordStrengthChecker checker = new PasswordStrengthChecker();
        
        System.out.println("========== Password Strength Checker ==========\n");
        System.out.println("Password Rules:");
        System.out.println("1. First character must be UPPERCASE");
        System.out.println("2. Last character must be a DIGIT");
        System.out.println("3. Minimum length: 8 characters");
        System.out.println("4. Must contain special character: @, #, $, %, &, *");
        System.out.println("==============================================\n");
        
        // Test cases
        String[] testPasswords = {
            "SecurePass@2024",    // Valid
            "Pass123",             // Too short
            "securePass@2024",     // First char not uppercase
            "SecurePass@Pass",     // Last char not digit
            "SecurePass2024",      // No special character
            "",                    // Empty string
            "Short@1",             // Too short
            "UPPERCASE@2024",      // Valid
            "Password#99",         // Valid
            "Test$Pass1"           // Valid
        };
        
        System.out.println("Testing Various Passwords:\n");
        
        for (int i = 0; i < testPasswords.length; i++) {
            System.out.println("Test " + (i + 1) + ":");
            checker.interactiveCheck(testPasswords[i]);
        }
        
        // Test with null
        System.out.println("Test " + (testPasswords.length + 1) + ":");
        System.out.println("--- Checking Password ---");
        System.out.println("Input: null");
        try {
            checker.checkPassword(null);
        } catch (PasswordValidationException e) {
            System.out.println(e.getMessage());
        }
        
        // Detailed validation results
        System.out.println("\n========== Detailed Validation Results ==========\n");
        
        String[] detailedTests = {
            "SecurePass@2024",
            "weak1",
            "NoSpecial1",
            "test@pass99"
        };
        
        for (String pwd : detailedTests) {
            PasswordValidationResult result = checker.validatePasswordDetailed(pwd);
            result.displayResult();
        }
        
        // Edge cases
        System.out.println("========== Edge Cases ==========\n");
        
        System.out.println("Test: Whitespace only");
        try {
            checker.checkPassword("   ");
        } catch (PasswordValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nTest: Exactly 8 characters, valid");
        try {
            checker.checkPassword("Test@ab1");
        } catch (PasswordValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nTest: 7 characters (one too short)");
        try {
            checker.checkPassword("Test@ab");
        } catch (PasswordValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nTest: Multiple special characters");
        try {
            checker.checkPassword("Test@#$%2024");
        } catch (PasswordValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
