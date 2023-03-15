class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>(){{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.addLast(c);
            } else {
                if (!stack.isEmpty() && stack.peekLast() == map.get(c)) {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}