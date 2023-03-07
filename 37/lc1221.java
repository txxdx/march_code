/**
平衡字符串 中，'L' 和 'R' 字符的数量是相同的。

给你一个平衡字符串 s，请你将它分割成尽可能多的子字符串，并满足：

每个子字符串都是平衡字符串。
返回可以通过分割得到的平衡字符串的 最大数量 。

示例 1：

输入：s = "RLRRLLRLRL"
输出：4
解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 */

class Solution {
  public int balancedStringSplit(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] chars = s.toCharArray();
    int ans = 0;
    int n = s.length();
    int i = 0;
    while (i < n) {
      int j = i + 1;
      int sum = chars[i] == 'L' ? 1 : -1;
      while (j < n && sum != 0) {
        sum += chars[j++] == 'L' ? 1 : -1;
      }
      ans ++;
      i = j;
    }

    return ans;
  }
}