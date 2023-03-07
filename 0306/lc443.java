//压缩字符串
class Solution {
  public int compress(char[] chars) {
    int n = chars.length;
    int i = 0, j = 0;
    while (i < n) {
      int idx = i;
      while (idx < n && chars[idx] == chars[i]) cnt ++;
      int cnt = idx - i;
      chars[j++] = chars[i];
      if (cnt > 1) {
        int left = j, right = j;
        while (cnt > 0) {
          chars[right++] = (char)(cnt % 10 + '0');
          cnt /= 10;
        }

        reverse(chars, left, right - 1);
        j = right;
      }
      i = idx;
    }

    return j;
  }

  private void reverse(char[] chars, int i, int j) {
    while (i < j) {
      char tmp = chars[i];
      chars[i] = chars[j];
      chars[j] = tmp;
      i ++;
      j --;
    }
  }
}