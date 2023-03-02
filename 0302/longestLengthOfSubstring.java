class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int maxLength = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int right = 0; right < s.length(); right ++) {
            char rightChar = s.charAt(right);
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0) + 1);
            while (freqMap.get(rightChar) > 1) {
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}


class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int maxLength = 0;
        Map<Character, Integer> freqMap = new HashMap<>(); //char -> index
        for (int right = 0; right < s.length(); right ++) {
            char rightChar = s.charAt(right);
            if (freqMap.containsKey(rightChar)) {
                left = Math.max(freqMap.get(rightChar) + 1, left); //
            }
            freqMap.put(rightChar, right);

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}