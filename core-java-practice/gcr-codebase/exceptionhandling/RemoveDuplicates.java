/**
 * LeetCode Problem 26: Remove Duplicates from Sorted Array
 * Given an integer array nums sorted in non-decreasing order, 
 * remove the duplicates in-place such that each unique element appears only once.
 * Return the number of unique elements.
 * 
 * Example:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * 
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class RemoveDuplicates {
    
    /**
     * Two pointer approach - optimal
     * One pointer tracks the position to insert unique element
     * Other pointer scans through the array
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int k = 1; // At least one unique element
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        
        return k;
    }
    
    /**
     * Alternative approach using different variable naming
     */
    public int removeDuplicatesAlt(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int uniqueIndex = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueIndex]) {
                uniqueIndex++;
                nums[uniqueIndex] = nums[i];
            }
        }
        
        return uniqueIndex + 1;
    }
    
    /**
     * Print the array up to k unique elements
     */
    private static void printArray(int[] nums, int k) {
        System.out.print("[");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i]);
            if (i < k - 1) System.out.print(",");
        }
        System.out.print("]");
    }
    
    // Test cases
    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();
        
        System.out.println("========== Remove Duplicates from Sorted Array ==========\n");
        
        // Test case 1
        int[] nums1 = {1, 1, 2};
        int k1 = solution.removeDuplicates(nums1);
        System.out.println("Test 1:");
        System.out.println("Input: [1,1,2]");
        System.out.print("Output: " + k1 + ", nums = ");
        printArray(nums1, k1);
        System.out.println();
        
        // Test case 2
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = solution.removeDuplicates(nums2);
        System.out.println("\nTest 2:");
        System.out.println("Input: [0,0,1,1,1,2,2,3,3,4]");
        System.out.print("Output: " + k2 + ", nums = ");
        printArray(nums2, k2);
        System.out.println();
        
        // Test case 3: No duplicates
        int[] nums3 = {1, 2, 3, 4, 5};
        int k3 = solution.removeDuplicates(nums3);
        System.out.println("\nTest 3 (No duplicates):");
        System.out.println("Input: [1,2,3,4,5]");
        System.out.print("Output: " + k3 + ", nums = ");
        printArray(nums3, k3);
        System.out.println();
        
        // Test case 4: All duplicates
        int[] nums4 = {1, 1, 1, 1};
        int k4 = solution.removeDuplicates(nums4);
        System.out.println("\nTest 4 (All duplicates):");
        System.out.println("Input: [1,1,1,1]");
        System.out.print("Output: " + k4 + ", nums = ");
        printArray(nums4, k4);
        System.out.println();
        
        // Test case 5: Single element
        int[] nums5 = {1};
        int k5 = solution.removeDuplicates(nums5);
        System.out.println("\nTest 5 (Single element):");
        System.out.println("Input: [1]");
        System.out.print("Output: " + k5 + ", nums = ");
        printArray(nums5, k5);
        System.out.println();
        
        // Test case 6: Empty array
        int[] nums6 = {};
        int k6 = solution.removeDuplicates(nums6);
        System.out.println("\nTest 6 (Empty array):");
        System.out.println("Input: []");
        System.out.print("Output: " + k6 + ", nums = ");
        printArray(nums6, k6);
        System.out.println();
        
        // Test case 7: Large array with pattern
        int[] nums7 = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 5};
        int k7 = solution.removeDuplicates(nums7);
        System.out.println("\nTest 7 (Large array):");
        System.out.println("Input: [1,1,1,2,2,3,3,3,3,4,5,5]");
        System.out.print("Output: " + k7 + ", nums = ");
        printArray(nums7, k7);
        System.out.println();
    }
}
