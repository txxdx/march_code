class Solution {
  public int characterReplacement(String s, int k) {
    int[] cnt = new int[26];
    int ans = 0;
    int maxCount = 0;
    char[] chs = s.toCharArray();

    for (int i = 0, j = 0; j < s.length(); j ++) {
      int r = chs[j] - 'A';
      cnt[r] ++;
      maxCount = Math.max(maxCount, cnt[r]);

      while (j - i + 1 - maxCount < k) {
        int l = chs[i] - 'A';
        cnt[l] --;
        i ++;
      }

      ans = Math.max(ans, j - i + 1);
    }

    return ans;
  }
}