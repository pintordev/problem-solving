package programmers.hash.p42888;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] inputs = {
            {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"}
        };
        String[][] answers = {
            {"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public String[] solution(String[] record) {
        Map<String, String> id2Name = new HashMap<>();
        List<String[]> commands = new ArrayList<>();
        for (String r : record) {
            String[] bits = r.split(" ");
            commands.add(bits);
            if (bits[0].equals("Leave")) continue;
            id2Name.put(bits[1], bits[2]);
        }
        List<String> resList = new ArrayList<>();
        for (String[] bits : commands) {
            if (bits[0].equals("Change")) continue;
            if (bits[0].equals("Enter")) resList.add(id2Name.get(bits[1]) + "님이 들어왔습니다.");
            else resList.add(id2Name.get(bits[1]) + "님이 나갔습니다.");
        }
        return resList.toArray(new String[0]);
    }
}