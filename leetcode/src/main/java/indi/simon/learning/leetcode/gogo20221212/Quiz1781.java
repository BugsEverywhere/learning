package indi.simon.learning.leetcode.gogo20221212;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1781 {

    public static void main(String[] args) {
        Quiz1781 quiz1781 = new Quiz1781();
        int res = quiz1781.beautySum("aabcbaa");
        System.out.println(res);
    }

    public int beautySum(String s) {
        int res = 0;

        //遍历每一个子串
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                //记录子串中每个字符的出现频次
                int[] countArr = new int[26];
                int most = 0;
                for (int k = i; k <= j; k++) {
                    countArr[s.charAt(k) - 'a']++;
                    if (countArr[s.charAt(k) - 'a'] > most) {
                        most = countArr[s.charAt(k) - 'a'];
                    }
                }

                int least = j - i + 1;
                for (int o = 0; o < countArr.length; o++) {
                    if (countArr[o] < least && countArr[o] != 0) {
                        least = countArr[o];
                    }
                }

                if (least == most) {
                    continue;
                }

                res += most - least;
            }
        }

        return res;
    }

}
