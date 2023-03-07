class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int ans = 0;
    int i = 0;
    while (i < nums.length) {
      if (nums[i] == 1) {
        int j = i;
        while (j < nums.length && nums[j] == 1) j ++;
        ans = Math.max(ans, j - i);
        i = j;
      } else {
        i ++;
      }
    }

    return ans;
  }
}