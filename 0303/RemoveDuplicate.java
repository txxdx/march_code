class Solution {
  public int removeDuplicates(int[] nums) {
    int j = 0;
    for (int i = 0; i < nums.length; i ++) {
      if (nums[i] != nums[j]) {
        nums[++j] = nums[i];
      }
    }

    return j + 1;
  }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        return process(nums, 1);
    }

    private int process(int[] nums, int k) {
        int idx = 0;
        for (int x : nums) {
            if (idx < k || nums[idx - k] != x) {
                nums[idx++] = x;
            }
        }

        return idx;
    }
}