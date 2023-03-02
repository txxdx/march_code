class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("nums should have at least 3 elements.");
        }
        
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[0] + nums[1] + nums[2]; //
        for (int i = 0; i < n - 2; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }

                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    j ++;
                } else {
                    k --;
                }
            }
        }

        return ans;
    }
}