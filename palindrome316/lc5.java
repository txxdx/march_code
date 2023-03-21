//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 6275 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int n = s.length();
        int maxLength = 0;
        int startIndex = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < 2; j ++) {
                int left = i, right = i + j;
                while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                    left --;
                    right ++;
                }
                //[left+1, right-1]
                if (right - left - 1 > maxLength) {
                    startIndex = left + 1;
                    maxLength = right - left - 1;
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLength);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
