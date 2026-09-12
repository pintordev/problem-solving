package programmers.implementation.p150366;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] commands = {
                {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"},
                {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"}
        };
        String[][] answers = {
                {"EMPTY", "group"},
                {"d", "EMPTY"}
        };

        for (int i = 0; i < commands.length; i++) {
            System.out.println(Arrays.equals(s.solution(commands[i]), answers[i]));
        }
    }

    int size = 50;
    int[] parent;
    String[] value;

    public String[] solution(String[] commands) {
        int n = size * size;
        parent = new int[n];
        value = new String[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        List<String> prints = new ArrayList<>();
        for (String command : commands) {
            String[] tokens = command.split(" ");
            switch (tokens[0]) {
                case "UPDATE":
                    if (tokens.length == 4) update(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
                    else update(tokens[1], tokens[2]);
                    break;
                case "MERGE":
                    merge(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                    break;
                case "UNMERGE":
                    unmerge(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;
                case "PRINT":
                    prints.add(print(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                    break;
            }
        }
        return prints.toArray(new String[0]);
    }

    public void update(int r, int c, String newValue) {
        value[find(index(r, c))] = newValue;
    }

    public void update(String oldValue, String newValue) {
        for (int i = 0; i < size * size; i++) {
            if (oldValue.equals(value[i])) value[i] = newValue;
        }
    }

    public void merge(int r1, int c1, int r2, int c2) {
        union(index(r1, c1), index(r2, c2));
    }

    public void unmerge(int r, int c) {
        int target = index(r, c);
        int root = find(target);
        String savedValue = value[root];
        List<Integer> members = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            if (find(i) == root) members.add(i);
        }
        for (int member : members) {
            parent[member] = member;
            value[member] = null;
        }
        value[target] = savedValue;
    }

    public String print(int r, int c) {
        String v = value[find(index(r, c))];
        if (v == null) return "EMPTY";
        return v;
    }

    public int index(int r, int c) {
        return (r - 1) * size + (c - 1);
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;
        String mergedValue = value[rootA] != null ? value[rootA] : value[rootB];
        parent[rootA] = rootB;
        value[rootB] = mergedValue;
        value[rootA] = null;
    }
}
