class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n]; //
        int ans = 0;
        for (int i = 1; i < n; i ++) {
            char c = s.charAt(i);
            if (c == ')') {
                char pre = s.charAt(i-1);
                if (pre == '(') {
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                } else {
                    // ([-----)])
                    int k = i - dp[i-1] - 1;
                    if (k >= 0 && s.charAt(k) == '(') {
                        dp[i] = dp[i-1] + 2 + (k > 0 ? dp[k-1] : 0);
                    }
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}