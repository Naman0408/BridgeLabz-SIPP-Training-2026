/**
 * LeetCode Problem 121: Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing 
 * a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve 
 * any profit, return 0.
 * 
 * Example:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5 (Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5)
 * 
 * Input: prices = [7,6,4,3,1]
 * Output: 0 (No transaction, as prices only decrease)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class BestTimeToBuySellStock {
    
    /**
     * Single pass approach - track minimum price and maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // Calculate profit if we sell at current price
            int profit = prices[i] - minPrice;
            
            // Update max profit if current profit is better
            maxProfit = Math.max(maxProfit, profit);
            
            // Update minimum price if current price is lower
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }
    
    /**
     * Alternative approach tracking buy and sell separately
     */
    public int maxProfitV2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        
        for (int price : prices) {
            // Maximum profit if we buy at this price
            buy = Math.max(buy, -price);
            
            // Maximum profit if we sell at this price
            sell = Math.max(sell, buy + price);
        }
        
        return sell;
    }
    
    /**
     * Brute force approach O(n^2) - for demonstration
     */
    public int maxProfitBruteForce(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        
        return maxProfit;
    }
    
    // Test cases
    public static void main(String[] args) {
        BestTimeToBuySellStock solution = new BestTimeToBuySellStock();
        
        System.out.println("========== Best Time to Buy and Sell Stock ==========\n");
        
        // Test case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int profit1 = solution.maxProfit(prices1);
        System.out.println("Test 1:");
        System.out.println("Input: [7,1,5,3,6,4]");
        System.out.println("Output: " + profit1);
        System.out.println("Explanation: Buy on day 2 (price=1), sell on day 5 (price=6), profit=5\n");
        
        // Test case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        int profit2 = solution.maxProfit(prices2);
        System.out.println("Test 2:");
        System.out.println("Input: [7,6,4,3,1]");
        System.out.println("Output: " + profit2);
        System.out.println("Explanation: No profit possible, prices only decrease\n");
        
        // Test case 3
        int[] prices3 = {2, 4, 1, 7, 5, 11};
        int profit3 = solution.maxProfit(prices3);
        System.out.println("Test 3:");
        System.out.println("Input: [2,4,1,7,5,11]");
        System.out.println("Output: " + profit3);
        System.out.println("Explanation: Buy at 1, sell at 11, profit=10\n");
        
        // Test case 4
        int[] prices4 = {2};
        int profit4 = solution.maxProfit(prices4);
        System.out.println("Test 4 (Single price):");
        System.out.println("Input: [2]");
        System.out.println("Output: " + profit4);
        System.out.println("Explanation: Cannot buy and sell on same day\n");
        
        // Test case 5
        int[] prices5 = {1, 2, 3, 4, 5};
        int profit5 = solution.maxProfit(prices5);
        System.out.println("Test 5 (Increasing prices):");
        System.out.println("Input: [1,2,3,4,5]");
        System.out.println("Output: " + profit5);
        System.out.println("Explanation: Buy at 1, sell at 5, profit=4\n");
        
        // Test case 6
        int[] prices6 = {3, 3, 3, 3, 3};
        int profit6 = solution.maxProfit(prices6);
        System.out.println("Test 6 (All same prices):");
        System.out.println("Input: [3,3,3,3,3]");
        System.out.println("Output: " + profit6);
        System.out.println("Explanation: No profit possible\n");
        
        // Verification with all approaches
        System.out.println("========== Verification with different approaches ==========\n");
        int[][] testCases = {
            {7, 1, 5, 3, 6, 4},
            {7, 6, 4, 3, 1},
            {2, 4, 1, 7, 5, 11},
            {1, 2, 3, 4, 5}
        };
        
        for (int[] prices : testCases) {
            int result1 = solution.maxProfit(prices);
            int result2 = solution.maxProfitV2(prices);
            int result3 = solution.maxProfitBruteForce(prices);
            
            System.out.print("[");
            for (int i = 0; i < prices.length; i++) {
                System.out.print(prices[i]);
                if (i < prices.length - 1) System.out.print(",");
            }
            System.out.println("]");
            System.out.println("  Approach 1 (Optimal): " + result1);
            System.out.println("  Approach 2 (DP): " + result2);
            System.out.println("  Approach 3 (Brute Force): " + result3);
            System.out.println();
        }
    }
}
