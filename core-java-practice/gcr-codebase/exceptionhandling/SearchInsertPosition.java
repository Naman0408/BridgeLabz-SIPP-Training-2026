/**
 * LeetCode Problem 35: Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * 
 * Example:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * 
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * 
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 * 
 * Input: nums = [1,3,5,6], target = 0
 * Output: 0
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class SearchInsertPosition {
    
    /**
     * Binary search approach - optimal
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // At this point, left is the insertion position
        return left;
    }
    
    /**
     * Linear search approach O(n) - for demonstration
     */
    public int searchInsertLinear(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
    
    /**
     * Java built-in binary search (for reference)
     */
    public int searchInsertBuiltIn(int[] nums, int target) {
        int index = java.util.Arrays.binarySearch(nums, target);
        // If found, return the index
        if (index >= 0) {
            return index;
        }
        // If not found, Arrays.binarySearch returns (-(insertion_point) - 1)
        // So we need to convert back: insertion_point = -result - 1
        return -index - 1;
    }
    
    /**
     * Verbose version for understanding
     */
    public int searchInsertVerbose(int[] nums, int target) {
        System.out.println("\nSearching for " + target + " in array: " + 
                         java.util.Arrays.toString(nums));
        
        int left = 0;
        int right = nums.length - 1;
        int step = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            System.out.println("Step " + step + ": left=" + left + ", right=" + right + 
                             ", mid=" + mid + ", nums[" + mid + "]=" + nums[mid]);
            
            if (nums[mid] == target) {
                System.out.println("Target found at index " + mid);
                return mid;
            } else if (nums[mid] < target) {
                System.out.println("  " + nums[mid] + " < " + target + ", move left");
                left = mid + 1;
            } else {
                System.out.println("  " + nums[mid] + " > " + target + ", move right");
                right = mid - 1;
            }
            
            step++;
        }
        
        System.out.println("Target not found. Insert at index " + left);
        return left;
    }
    
    // Test cases
    public static void main(String[] args) {
        SearchInsertPosition solution = new SearchInsertPosition();
        
        System.out.println("========== Search Insert Position Problem ==========\n");
        
        // Test case 1: Target found
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        int result1 = solution.searchInsert(nums1, target1);
        System.out.println("Test 1 (Target Found):");
        System.out.println("Input: nums = [1,3,5,6], target = 5");
        System.out.println("Output: " + result1);
        System.out.println("Explanation: 5 exists at index 2\n");
        
        // Test case 2: Insert at end
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 7;
        int result2 = solution.searchInsert(nums2, target2);
        System.out.println("Test 2 (Insert at End):");
        System.out.println("Input: nums = [1,3,5,6], target = 7");
        System.out.println("Output: " + result2);
        System.out.println("Explanation: 7 should be inserted at index 4\n");
        
        // Test case 3: Insert at beginning
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 0;
        int result3 = solution.searchInsert(nums3, target3);
        System.out.println("Test 3 (Insert at Beginning):");
        System.out.println("Input: nums = [1,3,5,6], target = 0");
        System.out.println("Output: " + result3);
        System.out.println("Explanation: 0 should be inserted at index 0\n");
        
        // Test case 4: Insert in middle
        int[] nums4 = {1, 3, 5, 6};
        int target4 = 4;
        int result4 = solution.searchInsert(nums4, target4);
        System.out.println("Test 4 (Insert in Middle):");
        System.out.println("Input: nums = [1,3,5,6], target = 4");
        System.out.println("Output: " + result4);
        System.out.println("Explanation: 4 should be inserted at index 2\n");
        
        // Test case 5: Single element - found
        int[] nums5 = {1};
        int target5 = 1;
        int result5 = solution.searchInsert(nums5, target5);
        System.out.println("Test 5 (Single Element - Found):");
        System.out.println("Input: nums = [1], target = 1");
        System.out.println("Output: " + result5 + "\n");
        
        // Test case 6: Single element - not found
        int[] nums6 = {1};
        int target6 = 2;
        int result6 = solution.searchInsert(nums6, target6);
        System.out.println("Test 6 (Single Element - Not Found):");
        System.out.println("Input: nums = [1], target = 2");
        System.out.println("Output: " + result6 + "\n");
        
        // Test case 7: Verbose demonstration
        System.out.println("Test 7 (Verbose - Insert in Middle):");
        solution.searchInsertVerbose(new int[]{1, 3, 5, 6}, 4);
        
        // Verification with all approaches
        System.out.println("\n========== Verification with different approaches ==========\n");
        
        int[][] testCases = {
            {1, 3, 5, 6},
            {1, 3, 5, 6},
            {1, 3, 5, 6},
            {1, 3, 5, 6},
            {1},
            {1}
        };
        
        int[] targets = {5, 7, 0, 4, 1, 2};
        
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int target = targets[i];
            
            int result = solution.searchInsert(nums, target);
            int resultLinear = solution.searchInsertLinear(nums, target);
            int resultBuiltIn = solution.searchInsertBuiltIn(nums, target);
            
            System.out.println("nums = " + java.util.Arrays.toString(nums) + 
                             ", target = " + target);
            System.out.println("  Binary Search: " + result);
            System.out.println("  Linear Search: " + resultLinear);
            System.out.println("  Built-in: " + resultBuiltIn);
            System.out.println("  All match: " + (result == resultLinear && result == resultBuiltIn));
            System.out.println();
        }
    }
}
