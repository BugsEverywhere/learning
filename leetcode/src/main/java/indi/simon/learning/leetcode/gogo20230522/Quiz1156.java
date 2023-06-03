package indi.simon.learning.leetcode.gogo20230522;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1156 {

    public static void main(String[] args) {
        Quiz1156 quiz1156 = new Quiz1156();
        int res = quiz1156.maxRepOpt1("aaabaaa");
        System.out.println(res);
    }

    public int maxRepOpt1(String text) {
        //key为字符，val代表该字符该次连续出现的长度
        Map<Character, List<int[]>> startEndMap = new HashMap<>();

        for (int i = 0; i < text.length(); ) {
            int j = i;
            while (j < text.length() && text.charAt(j) == text.charAt(i)) {
                j++;
            }
            int[] thisRange = new int[2];
            thisRange[0] = i;
            thisRange[1] = j - 1;
            List<int[]> list = startEndMap.getOrDefault(text.charAt(i), new ArrayList<>());
            list.add(thisRange);
            startEndMap.put(text.charAt(i), list);
            i = j;
        }

        int maxLength = 0;
        for (Map.Entry<Character, List<int[]>> entry : startEndMap.entrySet()) {
            List<int[]> ranges = entry.getValue();
            int thisCharMaxLength = 0;
            for (int i = 0; i < ranges.size(); i++) {
                int[] thisRange = ranges.get(i);
                if (i + 1 < ranges.size() && ranges.get(i + 1)[0] - thisRange[1] == 2) {
                    //下一段跟本段差一个，可以接上
                    if (ranges.size() == 2) {
                        //就这两段
                        thisCharMaxLength = Math.max(ranges.get(i + 1)[1] - thisRange[0], thisCharMaxLength);
                    } else {
                        //可以从别处借调
                        thisCharMaxLength = Math.max(ranges.get(i + 1)[1] - thisRange[0] + 1, thisCharMaxLength);
                    }
                } else {
                    //接不上
                    if (ranges.size() > 1) {
                        //有多段，可以借调一个
                        thisCharMaxLength = Math.max(thisRange[1] - thisRange[0] + 2, thisCharMaxLength);
                    } else {
                        //就这一段
                        thisCharMaxLength = Math.max(thisRange[1] - thisRange[0] + 1, thisCharMaxLength);
                    }
                }
            }
            maxLength = Math.max(maxLength, thisCharMaxLength);
        }

        return maxLength;
    }

}
