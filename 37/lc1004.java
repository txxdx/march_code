/**
给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。

示例 1：

输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 */

class Solution {
    public int longestOnes(int[] nums, int k) {
        int ones = 0;
        int ans = 0;
        for (int i = 0, j = 0; j < nums.length; j ++) {
            ones += nums[j];
            while (j - i + 1 > ones + k) {
                ones -= nums[i++];
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}