package programmers.bruteforce.p67257;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"100-200*300-500+20", "50*6-3*2"};
        long[] answers = {60420, 300};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public long solution(String expression) {
        expression = expression.replace("-", "m");
        char[] operator = getOperator(expression);
        List<char[]> orders = new ArrayList<>();
        getOrder(operator, orders, "", 0);

        String[] baseEq = expression.split("(?<=[*+m])|(?=[*+m])");
        long answer = 0;
        for (char[] order : orders) {
            String[] eq = baseEq.clone();
            for (char o : order) {
                String opStr = String.valueOf(o);
                List<String> stk = new ArrayList<>();
                for (int i = 0; i < eq.length; i++) {
                    if (eq[i].equals(opStr)) {
                        String a = stk.remove(stk.size() - 1);
                        stk.add(calculate(a, o, eq[++i]));
                    } else {
                        stk.add(eq[i]);
                    }
                }
                eq = stk.toArray(new String[0]);
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(eq[0])));
        }
        return answer;
    }

    char[] getOperator(String expression) {
        char[] operator = new char[3];
        int i = 0;
        if (expression.contains("*")) operator[i++] = '*';
        if (expression.contains("+")) operator[i++] = '+';
        if (expression.contains("m")) operator[i++] = 'm';
        return Arrays.copyOf(operator, i);
    }

    void getOrder(char[] operator, List<char[]> orders, String order, int mask) {
        if (order.length() == operator.length) {
            orders.add(order.toCharArray());
            return;
        }
        for (int i = 0; i < operator.length; i++) {
            if ((mask & (1 << i)) == 0) getOrder(operator, orders, order + operator[i], mask | (1 << i));
        }
    }

    String calculate(String a, char o, String b) {
        long la = Long.parseLong(a), lb = Long.parseLong(b);
        if (o == '*') return String.valueOf(la * lb);
        if (o == '+') return String.valueOf(la + lb);
        return String.valueOf(la - lb);
    }
}