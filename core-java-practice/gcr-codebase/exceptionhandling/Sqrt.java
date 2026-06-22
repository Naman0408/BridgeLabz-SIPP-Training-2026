/**
 * LeetCode Problem 69: Sqrt(x)
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 * 
 * Example:
 * Input: x = 4
 * Output: 2
 * 
 * Input: x = 8
 * Output: 2 (Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.)
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class Sqrt {
    
    /**
     * Binary search approach - most efficient O(log n)
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        
        int left = 1, right = x / 2;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if ((long)mid * mid == x) {
                return mid;
            } else if ((long)mid * mid < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // Return right as it's the largest number whose square is <= x
        return right;
    }
    
    /**
     * Newton's method approach (Alternative)
     */
    public int mySqrtNewton(int x) {
        if (x < 2) {
            return x;
        }
        
        long n = x;
        while (n * n > x) {
            n = (n + x / n) / 2;
        }
        
        return (int)n;
    }
    
    /**
     * Simple linear search O(n) - less efficient but demonstrates basic approach
     */
    public int mySqrtLinear(int x) {
        if (x < 2) {
            return x;
        }
        
        long i = 1;
        while (i * i <= x) {
            if (i * i == x) {
                return (int)i;
            }
            i++;
        }
        
        return (int)(i - 1);
    }
    
    // Test cases
    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        
        System.out.println("========== Sqrt(x) Problem ==========\n");
        
        int[] testCases = {0, 1, 2, 3, 4, 8, 9, 15, 16, 100, 2147395600};
        
        for (int x : testCases) {
            System.out.printf("sqrt(%d) = %d%n", x, sqrt.mySqrt(x));
        }
        
        System.out.println("\n--- Verification ---");
        for (int x : testCases) {
            int result = sqrt.mySqrt(x);
            System.out.printf("sqrt(%d) = %d, verification: %d^2 = %d%n", 
                x, result, result, result * result);
        }
    }
}
