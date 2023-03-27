//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。 
//
// 返回所有可能的结果。答案可以按 任意顺序 返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()())()"
//输出：["(())()","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：s = "(a)())()"
//输出：["(a())()","(a)()()"]
// 
//
// 示例 3： 
//
// 
//输入：s = ")("
//输出：[""]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 25 
// s 由小写英文字母以及括号 '(' 和 ')' 组成 
// s 中至多含 20 个括号 
// 
//
// Related Topics 广度优先搜索 字符串 回溯 👍 829 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int maxPairs = 0;
    int maxLength = 0;
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();

        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(') l ++;
            if (c == ')') r ++;
        }
        maxPairs = Math.max(l, r);

        Set<String> set = new HashSet<>();
        dfs(s, 0, 0, "", set);

        for (String str: set) {
            if (str.length() == maxLength) {
                ans.add(str);
            }
        }
        return ans;
    }

    private void dfs(String s, int index, int score, String curPath, Set<String> set) {
        if (index == s.length()) {
            if (score == 0 && curPath.length() >= maxLength) {
                maxLength = curPath.length();
                set.add(curPath);
            }
            return;
        }

        char c = s.charAt(index);
        if (c == '(') {
            if (score + 1 <= maxPairs) {
                dfs(s, index + 1, score + 1, curPath + "(", set);
            }
            dfs(s, index + 1, score, curPath, set);
        } else if (c == ')') {
            if (score > 0) {
                dfs(s, index + 1, score - 1, curPath + ")", set);
            }
            dfs(s, index + 1, score, curPath, set);
        } else {
            dfs(s, index + 1, score, curPath + String.valueOf(c), set);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            ans.add("");
            return ans;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (isValid(cur)) {
                ans.add(cur);
                found = true;
            }
            if (found) {
                continue;
            }

            for (int i = 0; i < cur.length(); i ++) {
                char c = cur.charAt(i);
                if (c == '(' || c == ')') {
                    String next = cur.substring(0, i) + cur.substring(i + 1);
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }

        return ans;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(') {
                count ++;
            } else if (c == ')') {
                count --;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}