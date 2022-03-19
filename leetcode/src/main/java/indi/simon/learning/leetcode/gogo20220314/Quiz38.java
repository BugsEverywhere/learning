package indi.simon.learning.leetcode.gogo20220314;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz38 {

    public static void main(String[] args) {
        Quiz38 quiz38 = new Quiz38();
        String res = quiz38.countAndSay(4);
        System.out.println(res);

    }

    public String countAndSay(int n) {
        int index = 1;
        String currentStr = null;
        while (index <= n) {
            if (currentStr == null) {
                currentStr = "1";
            } else {
                StringBuilder sb = new StringBuilder();
                char[] charArr = currentStr.toCharArray();
                Character lastInteger = null;
                int countForNow = 0;
                for (int i = 0; i < charArr.length; i++) {
                    if (lastInteger == null) {
                        lastInteger = charArr[0];
                        countForNow++;
                    } else {
                        if (charArr[i] == lastInteger) {
                            countForNow++;
                        } else {
                            sb.append(countForNow).append(lastInteger);
                            countForNow = 1;
                            lastInteger = charArr[i];
                        }
                    }
                }
                sb.append(countForNow).append(lastInteger);
                currentStr = sb.toString();
            }
            index++;
        }

        return currentStr;
    }

}
