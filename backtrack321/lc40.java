//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
//
// Related Topics 数组 回溯 👍 1284 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ans;
        }

        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        dfs(candidates, 0, target, path, ans);
        return ans;
    }

    private void dfs(int[] candidates, int index, int target, List<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (index == candidates.length || target < 0) return;

        for (int i = index; i < candidates.length; i ++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], path, ans);
            path.remove(path.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
