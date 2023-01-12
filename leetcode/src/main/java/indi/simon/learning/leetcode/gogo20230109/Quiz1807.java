package indi.simon.learning.leetcode.gogo20230109;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1807 {

    public static void main(String[] args) {
        Quiz1807 quiz1807 = new Quiz1807();
        List<List<String>> knowledge = new ArrayList<>();
        List<String> pair1 = new ArrayList<>();
        pair1.add("name");
        pair1.add("bob");
        knowledge.add(pair1);

        String res = quiz1807.evaluate("(name)is(age)yearsold", knowledge);
        System.out.println(res);
    }

    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> kvMap = new HashMap<>(knowledge.size());

        for (List<String> pair : knowledge) {
            kvMap.put(pair.get(0), pair.get(1));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) == '(') {
                int j = i + 1;
                while (j < s.length()) {
                    if (s.charAt(j) == ')') {
                        break;
                    } else {
                        j++;
                    }
                }
                String key = s.substring(i + 1, j);
                stringBuilder.append(kvMap.getOrDefault(key, "?"));
                i = j + 1;
            } else {
                stringBuilder.append(s.charAt(i));
                i++;
            }
        }

        return stringBuilder.toString();
    }


}
