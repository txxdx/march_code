//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 1427 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        int n = s.length();
        char[] chars = s.toCharArray();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = i; j >= 0; j --) {
                if (j == i) {
                    isPalindrome[j][i] = true;
                } else {
                    if (i - j == 1) {
                        isPalindrome[j][i] = (chars[j] == chars[i]);
                    } else {
                        isPalindrome[j][i] = isPalindrome[j+1][i-1] && (chars[j] == chars[i]);
                    }
                }
            }
        }

        List<String> path = new ArrayList<>();
        dfs(s, 0, ans, path, isPalindrome);
        return ans;
    }

    private void dfs(String s, int index, List<List<String>> ans, List<String> path, boolean[][] isPalindrome) {
        if (index == s.length()) {
            ans.add(new ArrayList(path));
        }

        for (int i = index; i < s.length(); i ++) {
            if (isPalindrome[index][i]) {
                path.add(s.substring(index, i + 1));
                dfs(s, i + 1, ans, path, isPalindrome);
                path.remove(path.size() - 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
