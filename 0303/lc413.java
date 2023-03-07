class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length < 3) {
            return 0;
        }

        int ans = 0;
        int i = 0;
        int n = nums.length;
        while (i < n - 2) {
            int d = nums[i + 1] - nums[i];
            int j = i;
            while (j + 1 < n && nums[j + 1] - nums[j] == d) {
                j ++;
            }

            int len = j - i + 1;
            if (len < 3) {
                i = j;
                continue;
            }

            int a0 = 1, an = len - 3 + 1;
            ans += (a0 + an) * an / 2;

            i = j;
        }

        return ans;
    }
}
