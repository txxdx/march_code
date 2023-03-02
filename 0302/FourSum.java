class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long)nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) {
                break;
            }

            for (int j = i + 1; j < n - 2; j ++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                if ((long)nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) {
                    break;
                }

                int k = j + 1, p = n - 1;
                while (k < p) {
                    long sum = (long)(nums[i] + nums[j] + nums[k] + nums[p]);
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[p]));
                        k ++;
                        while (k < p && nums[k] == nums[k - 1]) k ++;
                    } else if (sum > target) {
                        p --;
                    } else {
                        k ++;
                    }
                }
            } // end for j
        } // end for i

        return ans;
    }
}