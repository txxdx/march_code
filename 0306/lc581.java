class Solution {
  public int findUnsortedSubarray(int[] nums) {
    if (nums == null || nums.length < 2) {
      return 0;
    }

    int n = nums.length;
    int maxLeft = Integer.MIN_VALUE;
    int right = 0;
    for (int i = 0; i < n; i ++) {
      if (nums[i] < maxLeft) {
        right = i;
      }
      maxLeft = Math.max(maxLeft, nums[i]);
    }

    int minRight = Integer.MAX_VALUE;
    int left = 0;
    for (int i = n - 1; i >= 0; i --) {
      if (nums[i] > minRight) {
        left = i;
      }
      minRight = Math.min(minRight, nums[i]);
    }

    return right > left ? right - left + 1 : 0;
  }
}