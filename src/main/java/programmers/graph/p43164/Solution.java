package programmers.graph.p43164;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][][] tickets = {
                {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}},
                {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}
        };
        String[][] answers = {
                {"ICN", "JFK", "HND", "IAD"},
                {"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"}
        };
        for (int i = 0; i < tickets.length; i++) {
            System.out.println(Arrays.equals(s.solution(tickets[i]), answers[i]));
        }
    }

    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            PriorityQueue<String> destinations = graph.get(ticket[0]);
            if (destinations == null) {
                destinations = new PriorityQueue<>();
                graph.put(ticket[0], destinations);
            }
            destinations.add(ticket[1]);
        }

        Deque<String> stack = new ArrayDeque<>();
        Deque<String> route = new ArrayDeque<>();
        stack.push("ICN");
        while (!stack.isEmpty()) {
            String airport = stack.peek();
            PriorityQueue<String> destinations = graph.get(airport);
            if (destinations == null || destinations.isEmpty()) route.push(stack.pop());
            else stack.push(destinations.poll());
        }
        return route.toArray(new String[0]);
    }
}