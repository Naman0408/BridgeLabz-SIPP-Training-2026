/**
 * LeetCode Problem 67: Add Binary
 * Given two binary strings a and b, return their sum as a binary string.
 * 
 * Example:
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * 
 * Time Complexity: O(max(len(a), len(b)))
 * Space Complexity: O(max(len(a), len(b))) for the result
 */
public class AddBinary {
    
    /**
     * Approach 1: Two pointers from the end, process each bit and handle carry
     */
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        while (i >= 0 || j >= 0 || carry > 0) {
            int digitA = i >= 0 ? a.charAt(i) - '0' : 0;
            int digitB = j >= 0 ? b.charAt(j) - '0' : 0;
            
            int sum = digitA + digitB + carry;
            result.append(sum % 2);
            carry = sum / 2;
            
            i--;
            j--;
        }
        
        // Result is built in reverse order
        return result.reverse().toString();
    }
    
    /**
     * Approach 2: Convert to decimal, add, convert back (for educational purposes)
     * Note: This has limitations with very large numbers
     */
    public String addBinaryDecimal(String a, String b) {
        try {
            long numA = Long.parseLong(a, 2);
            long numB = Long.parseLong(b, 2);
            long sum = numA + numB;
            return Long.toBinaryString(sum);
        } catch (NumberFormatException e) {
            return "Error: Invalid binary input";
        }
    }
    
    /**
     * Approach 3: Manual implementation showing each step
     */
    public String addBinaryVerbose(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        int step = 1;
        
        System.out.println("\nStep-by-step addition:");
        System.out.println("  " + a);
        System.out.println("+ " + b);
        System.out.println("------");
        
        while (i >= 0 || j >= 0 || carry > 0) {
            int bitA = i >= 0 ? a.charAt(i) - '0' : 0;
            int bitB = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = bitA + bitB + carry;
            int resultBit = sum % 2;
            carry = sum / 2;
            
            result.append(resultBit);
            System.out.printf("Step %d: %d + %d + %d(carry) = %d (bit: %d, new carry: %d)%n",
                step, bitA, bitB, (sum > resultBit ? 1 : 0), sum, resultBit, carry);
            
            i--;
            j--;
            step++;
        }
        
        System.out.println();
        return result.reverse().toString();
    }
    
    /**
     * Validate binary string
     */
    private boolean isValidBinary(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Safe addition with validation
     */
    public String addBinarySafe(String a, String b) {
        try {
            if (!isValidBinary(a) || !isValidBinary(b)) {
                throw new IllegalArgumentException("Input must contain only 0s and 1s");
            }
            return addBinary(a, b);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    // Test cases
    public static void main(String[] args) {
        AddBinary solution = new AddBinary();
        
        System.out.println("========== Add Binary Problem ==========\n");
        
        // Test case 1
        String a1 = "11";
        String b1 = "1";
        String result1 = solution.addBinary(a1, b1);
        System.out.println("Test 1:");
        System.out.println("Input: a = \"" + a1 + "\", b = \"" + b1 + "\"");
        System.out.println("Output: \"" + result1 + "\"");
        System.out.println("Explanation: 3 + 1 = 4 (100 in binary)\n");
        
        // Test case 2
        String a2 = "1010";
        String b2 = "1011";
        String result2 = solution.addBinary(a2, b2);
        System.out.println("Test 2:");
        System.out.println("Input: a = \"" + a2 + "\", b = \"" + b2 + "\"");
        System.out.println("Output: \"" + result2 + "\"");
        System.out.println("Explanation: 10 + 11 = 21 (10101 in binary)\n");
        
        // Test case 3: Verbose output
        System.out.println("Test 3 (Verbose):");
        String a3 = "1111";
        String b3 = "1111";
        String result3 = solution.addBinaryVerbose(a3, b3);
        System.out.println("Result: " + result3);
        System.out.println("Explanation: 15 + 15 = 30 (11110 in binary)\n");
        
        // Test case 4
        String a4 = "0";
        String b4 = "0";
        String result4 = solution.addBinary(a4, b4);
        System.out.println("Test 4 (Zero):");
        System.out.println("Input: a = \"" + a4 + "\", b = \"" + b4 + "\"");
        System.out.println("Output: \"" + result4 + "\"\n");
        
        // Test case 5
        String a5 = "1";
        String b5 = "1111";
        String result5 = solution.addBinary(a5, b5);
        System.out.println("Test 5 (Different lengths):");
        System.out.println("Input: a = \"" + a5 + "\", b = \"" + b5 + "\"");
        System.out.println("Output: \"" + result5 + "\"\n");
        
        // Test case 6: Large numbers
        String a6 = "110010";
        String b6 = "10011";
        String result6 = solution.addBinary(a6, b6);
        System.out.println("Test 6 (Larger numbers):");
        System.out.println("Input: a = \"" + a6 + "\", b = \"" + b6 + "\"");
        System.out.println("Output: \"" + result6 + "\"\n");
        
        // Test case 7: All ones
        String a7 = "1111";
        String b7 = "1";
        String result7 = solution.addBinary(a7, b7);
        System.out.println("Test 7 (Carry propagation):");
        System.out.println("Input: a = \"" + a7 + "\", b = \"" + b7 + "\"");
        System.out.println("Output: \"" + result7 + "\"\n");
        
        // Verification with decimal approach
        System.out.println("========== Verification with Decimal Conversion ==========\n");
        String[][] testCases = {
            {"11", "1"},
            {"1010", "1011"},
            {"1111", "1111"},
            {"0", "0"},
            {"110010", "10011"}
        };
        
        for (String[] testCase : testCases) {
            String a = testCase[0];
            String b = testCase[1];
            String result = solution.addBinary(a, b);
            String decimalResult = solution.addBinaryDecimal(a, b);
            
            System.out.println("a = \"" + a + "\", b = \"" + b + "\"");
            System.out.println("  Binary approach: " + result);
            System.out.println("  Decimal verification: " + decimalResult);
            System.out.println("  Match: " + result.equals(decimalResult));
            System.out.println();
        }
    }
}
