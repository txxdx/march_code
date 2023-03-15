//给你一个字符串化学式 formula ，返回 每种原子的数量 。 
//
// 原子总是以一个大写字母开始，接着跟随 0 个或任意个小写字母，表示原子的名字。 
//
// 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。 
//
// 
// 例如，"H2O" 和 "H2O2" 是可行的，但 "H1O2" 这个表达是不可行的。 
// 
//
// 两个化学式连在一起可以构成新的化学式。 
//
// 
// 例如 "H2O2He3Mg4" 也是化学式。 
// 
//
// 由括号括起的化学式并佐以数字（可选择性添加）也是化学式。 
//
// 
// 例如 "(H2O2)" 和 "(H2O2)3" 是化学式。 
// 
//
// 返回所有原子的数量，格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于
// 1），以此类推。 
//
// 
//
// 示例 1： 
//
// 
//输入：formula = "H2O"
//输出："H2O"
//解释：原子的数量是 {'H': 2, 'O': 1}。
// 
//
// 示例 2： 
//
// 
//输入：formula = "Mg(OH)2"
//输出："H2MgO2"
//解释：原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
// 
//
// 示例 3： 
//
// 
//输入：formula = "K4(ON(SO3)2)2"
//输出："K4N2O14S4"
//解释：原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= formula.length <= 1000 
// formula 由英文字母、数字、'(' 和 ')' 组成 
// formula 总是有效的化学式 
// 
//
// Related Topics 栈 哈希表 字符串 排序 👍 283 👎 0


import java.util.ArrayList;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Node {
        String atom;
        int val;

        Node(String atom, int val) {
            this.atom = atom;
            this.val = val;
        }
    }
    public String countOfAtoms(String s) {
        Map<String, Integer> map = new HashMap<>();
        Deque<String> stack = new ArrayDeque<>();
        int idx = 0;
        int i = 0;
        int n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                stack.addLast(String.valueOf(c));
                i ++;
            } else {
                if (Character.isDigit(c)) {
                    int j = i;
                    int cnt = 0;
                    while (j < n && Character.isDigit(s.charAt(j))) {
                        int d = s.charAt(j) - '0';
                        cnt = cnt * 10 + d;
                        j ++;
                    }
                    i = j;

                    if (!stack.isEmpty() && stack.peekLast().equals(")")) {
                        stack.pollLast(); // pop )

                        List<String> tmp = new ArrayList<>();
                        while (!stack.isEmpty() && !stack.peekLast().equals("(")) {
                            String atom = stack.pollLast();
                            map.put(atom, map.getOrDefault(atom, 1) * cnt);
                            tmp.add(atom);
                        }

                        stack.pollLast();

                        for (int k = tmp.size() - 1; k >= 0; k --) {
                            stack.addLast(tmp.get(k));
                        }
                    } else {
                        String atom = stack.peekLast();
                        map.put(atom, map.getOrDefault(atom, 1) * cnt);
                    }
                } else {
                    // atom
                    int j = i + 1;
                    while(j < n && Character.isLowerCase(s.charAt(j))) {
                        j ++;
                    }

                    String atom = s.substring(i, j) + "_" + idx;
                    idx ++;

                    map.put(atom, 1);
                    stack.addLast(atom);

                    i = j;
                }
            }
        }

        Map<String, Node> nodeMap = new HashMap<>();
        for (String k: map.keySet()) {
            String atom = k.split("_")[0];
            Node node = null;
            if (nodeMap.containsKey(atom)) {
                node = nodeMap.get(atom);
            } else {
                node = new Node(atom, 0);
            }

            node.val += map.get(k);
            nodeMap.put(atom, node);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.atom.compareTo(b.atom));
        for (String k: nodeMap.keySet()) {
            pq.add(nodeMap.get(k));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            sb.append(poll.atom);
            if (poll.val > 1) {
                sb.append(poll.val);
            }
        }

        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
