//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 位运算 数组 回溯 👍 1053 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        dfs(nums, 0, path, ans);
        return ans;
    }

    private void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> ans) {
        if (index > nums.length) return;
        ans.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i ++) {
            if (i > index && nums[i] == nums[i - 1]) continue;

            path.add(nums[i]);
            dfs(nums, i + 1, path, ans);
            path.remove(path.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        dfs(nums, 0, path, ans);
        return new ArrayList<>(ans);
    }

    private void dfs(int[] nums, int index, List<Integer> path, Set<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        path.add(nums[index]);
        dfs(nums, index + 1, path, ans);

        path.remove(path.size() - 1);
        dfs(nums, index + 1, path, ans);
    }
}