package programmers.implementation.p17680;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[] cacheSizes = {3, 3, 2, 5, 2, 0};
        String[][] cities = {
            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"},
            {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"},
            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
            {"Jeju", "Pangyo", "NewYork", "newyork"},
            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}
        };
        int[] answers = {50, 21, 60, 52, 16, 25};

        for (int i = 0; i < cacheSizes.length; i++) {
            System.out.println(s.solution(cacheSizes[i], cities[i]) == answers[i]);
        }
    }

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return 5 * cities.length;
        Queue<String> cache = new ArrayDeque<>();
        int runningTime = 0;
        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.contains(city)) {
                cache.remove(city);
                runningTime++;
                cache.add(city);
            } else if (cache.size() < cacheSize) {
                runningTime += 5;
                cache.add(city);
            } else {
                cache.poll();
                runningTime += 5;
                cache.add(city);
            }
        }
        return runningTime;
    }
}