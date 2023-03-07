class Solution {
    public boolean judgeSquareSum(int c) {
        int max = (int)Math.sqrt(c);
        for (int i = 0; i <= max; i ++) {
            int j = (int)Math.sqrt(c - i * i);
            if (i * i + j * j == c) return true;
        }
        return false;
    }
}