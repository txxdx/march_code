//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。 
//
// 返回符合要求的 最少分割次数 。 
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
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：s = "ab"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 653 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCut(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = i; j >= 0; j --) {
                if (i == j) {
                    isPalindrome[j][i] = true;
                } else if (chs[j] == chs[i] && (i - j == 1 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                }
            }
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i ++) {
            if (isPalindrome[0][i]) {
                dp[i] = 0;
            } else {
                dp[i] = i;
                for (int j = 0; j <= i; j ++) {
                    if (isPalindrome[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j-1] + 1);
                    }
                }
            }
        }

        return dp[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
