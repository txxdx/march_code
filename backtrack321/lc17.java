class Solution {
    Map<Character, Character[]> map = new HashMap<>() {{
        put('2', new Character[]{'a', 'b', 'c'});
        put('3', new Character[]{'d', 'e', 'f'});
        put('4', new Character[]{'g', 'h', 'i'});
        put('5', new Character[]{'j', 'k', 'l'});
        put('6', new Character[]{'m', 'n', 'o'});
        put('7', new Character[]{'p', 'q', 'r', 's'});
        put('8', new Character[]{'t', 'u', 'v'});
        put('9', new Character[]{'w', 'x', 'y', 'z'});
    }};

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }

        StringBuilder path = new StringBuilder();
        dfs(digits, 0, path, ans);
        return ans;
    }

    private void dfs(String digits, int index, StringBuilder path, List<String> ans) {
        if (index == digits.length()) {
            ans.add(path.toString());
            return;
        }

        Character key = digits.charAt(index);
        Character[] chars = map.get(key);
        for (Character c: chars) {
            path.append(c);
            dfs(digits, index + 1, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
    }
}