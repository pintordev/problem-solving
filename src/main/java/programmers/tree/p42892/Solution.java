package programmers.tree.p42892;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] nodeinfos = {
                {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}}
        };
        int[][][] answers = {
                {{7, 4, 6, 9, 1, 8, 5, 2, 3}, {9, 6, 5, 8, 1, 4, 3, 2, 7}}
        };
        for (int i = 0; i < nodeinfos.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(nodeinfos[i]), answers[i]));
        }
    }

    int n;
    Node root;

    public int[][] solution(int[][] nodeinfo) {
        n = nodeinfo.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        Arrays.sort(nodes);
        root = nodes[0];
        for (int i = 1; i < n; i++) insert(nodes[i]);
        return new int[][]{preorder(), postorder()};
    }

    public void insert(Node node) {
        Node cur = root;
        while (true) {
            if (node.x < cur.x) {
                if (cur.left == null) {
                    cur.left = node;
                    return;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = node;
                    return;
                }
                cur = cur.right;
            }
        }
    }

    public int[] preorder() {
        int[] result = new int[n];
        int idx = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            result[idx++] = cur.num;
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return result;
    }

    public int[] postorder() {
        int[] result = new int[n];
        int idx = n;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            result[--idx] = cur.num;
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return result;
    }
}

class Node implements Comparable<Node> {
    int x;
    int y;
    int num;
    Node left;
    Node right;

    public Node(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }

    @Override
    public int compareTo(Node o) {
        if (y != o.y) return o.y - y;
        return x - o.x;
    }
}
