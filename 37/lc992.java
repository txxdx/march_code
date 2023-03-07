/**
给定一个正整数数组 nums和一个整数 k ，返回 num 中 「好子数组」 的数目。

如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。

例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
子数组 是数组的 连续 部分。

示例 1：

输入：nums = [1,2,1,2,3], k = 2
输出：7
解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

 */

class Solution {
  public int subarraysWithKDistinct(int[] nums, int k) {
    if (nums == null || nums.length < k) {
      return 0;
    }

    int n = nums.length;
    int[] lower = new int[n];
    int[] upper = new int[n];

    findPosition(nums, k, lower);
    findPosition(nums, k - 1, upper);

    int ans = 0;
    for (int i = 0; i < n; i ++) {
      ans += upper[i] - lower[i];
    }

    return ans;
  }

  private void findPosition(int[] nums, int k, int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
    int cnt = 0;

    for (int i = 0, j = 0; j < nums.length; j ++) {
      int right = nums[j];
      map.put(right, map.getOrDefault(right, 0) + 1);
      if (map.get(right) == 1) {
        cnt ++;
      }

      while (cnt > k) {
        int left = nums[i++];
        map.put(left, map.get(left) - 1);
        if (map.get(left) == 0) {
          cnt --;
        }
      }

      arr[j] = i;
    }
  }
}