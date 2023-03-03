class Solution {
    public String reverseVowels(String s) {
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        Set<Character> set = new HashSet<>();
        for (char c: vowels) {
            set.add(c);
        }

        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left <= right) {
            if (set.contains(chars[left]) && set.contains(chars[right])) {
                swap(chars, left, right);
                left ++;
                right --;
            } else {
                if (!set.contains(chars[left])) left ++;
                if (!set.contains(chars[right])) right --;
            }
        }

        return String.valueOf(chars);
    }

    private void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }
}