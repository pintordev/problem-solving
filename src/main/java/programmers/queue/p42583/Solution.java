package programmers.queue.p42583;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] bridgeLengths = {2, 100, 100};
        int[] weights = {10, 100, 100};
        int[][] truckWeights = {{7, 4, 5, 6}, {10}, {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}};
        int[] answers = {8, 101, 110};
        for (int i = 0; i < answers.length; i++) {
            System.out.println(s.solution(bridgeLengths[i], weights[i], truckWeights[i]) == answers[i]);
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int[] bridge = new int[bridge_length];
        int time = 0, bridgeWeight = 0, idx = 0;
        while (idx < truck_weights.length || bridgeWeight > 0) {
            bridgeWeight -= bridge[time % bridge_length];
            bridge[time % bridge_length] = 0;
            if (idx < truck_weights.length && bridgeWeight + truck_weights[idx] <= weight) {
                bridge[time % bridge_length] = truck_weights[idx++];
                bridgeWeight += bridge[time % bridge_length];
            }
            time++;
        }
        return time;
    }
}
