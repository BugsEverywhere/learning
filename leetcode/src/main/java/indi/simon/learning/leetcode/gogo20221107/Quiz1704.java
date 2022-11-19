package indi.simon.learning.leetcode.gogo20221107;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1704 {

    public static void main(String[] args) {
        Quiz1704 quiz1704 = new Quiz1704();
        boolean res = quiz1704.halvesAreAlike("AbCdEfGh");
        System.out.println(res);
    }

    public boolean halvesAreAlike(String s) {
        char[] cArr = s.toCharArray();
        Set<Character> yuanyinSet = new HashSet<Character>() {
            {
                add('a');
                add('e');
                add('i');
                add('o');
                add('u');
                add('A');
                add('E');
                add('I');
                add('O');
                add('U');
            }
        };

        int firstHalfCount = 0;
        for (int i = 0; i < cArr.length / 2; i++) {
            if (yuanyinSet.contains(cArr[i])) {
                firstHalfCount++;
            }
        }

        for (int i = cArr.length / 2; i < cArr.length; i++) {
            if (yuanyinSet.contains(cArr[i])) {
                firstHalfCount--;
            }
        }

        return firstHalfCount == 0;
    }

}
