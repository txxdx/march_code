/**
给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。

子数组 是数组的一段连续部分。

示例 1：

输入：nums = [1,0,1,0,1], goal = 2
输出：4
解释：
有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
 */

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] sums = new int[n+1];
        for (int i = 1; i <= n; i ++) sums[i] = sums[i-1] + nums[i-1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ans = 0;
        for (int i = 0; i < n; i ++) {
            int find = sums[i+1] - goal;
            ans += map.getOrDefault(find, 0);
            map.put(sums[i+1], map.getOrDefault(sums[i+1], 0) + 1);
        }

        return ans;
    }
}

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            if (map.containsKey(sum - goal)) {
                ans += map.get(sum - goal);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}