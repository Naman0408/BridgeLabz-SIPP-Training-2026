/**
 * Custom exception for password validation failures
 */
public class PasswordValidationException extends Exception {
    private String reason;
    
    public PasswordValidationException(String reason) {
        super("❌ Password Validation Failed: " + reason);
        this.reason = reason;
    }
    
    public String getReason() {
        return reason;
    }
}
