class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0 || s.length() < k) {
            return 0;
        }

        int[] counts = new int[26];
        int ans = 0;
        char[] chs = s.toCharArray();

        for (int c = 1; c <= 26; c ++) {
            Arrays.fill(counts, 0);
            int appears = 0;
            int match = 0;
            for (int i = 0, j = 0; j < chs.length; j ++) {
                int right = chs[j] - 'a';
                counts[right] ++;
                if (counts[right] == 1) {
                    appears ++;
                }
                if (counts[right] == k) {
                    match ++;
                }

                while (appears > c) {
                    int left = chs[i] - 'a';
                    counts[left] --;
                    if (counts[left] == 0) {
                        appears --;
                    }
                    if (counts[left] == k - 1) {
                        match --;
                    }
                    i ++;
                }

                if (appears == match) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
}
