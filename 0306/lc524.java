class Solution {
  public String findLongestWord(String s, List<String> dictionary) {
    Collections.sort(dictionary, (a, b) -> {
      if (a.length() != b.length()) {
        return b.length() - a.length();
      }
      return a.compareTo(b);
    });

    for (String word: dictionary) {
      int i = 0, j = 0;
      while (i < s.length() && j < word.length()) {
        if (s.charAt(i) == word.charAt(j)) {
          j ++;
        }
        i ++;
      }

      if (j == word.length()) {
        return word;
      }
    }

    return "";
  }
}