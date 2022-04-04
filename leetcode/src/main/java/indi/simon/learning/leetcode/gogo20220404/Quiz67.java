package indi.simon.learning.leetcode.gogo20220404;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz67 {

    public static void main(String[] args) {
        String a = "1111";
        String b = "1111";
        Quiz67 quiz67 = new Quiz67();
        String res = quiz67.addBinary(a, b);
        System.out.println(res);
    }

    public String addBinary(String a, String b) {
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();

        Deque<Character> queue = new ArrayDeque<>();

        int carry = 0;
        int aIndex = aArr.length - 1;
        int bIndex = bArr.length - 1;
        while (aIndex >= 0 || bIndex >= 0) {
            int aInt = aIndex >= 0 ? aArr[aIndex] - '0' : 0;
            int bInt = bIndex >= 0 ? bArr[bIndex] - '0' : 0;
            if (aInt + bInt + carry == 3) {
                carry = 1;
                queue.addFirst('1');
            } else if (aInt + bInt + carry == 2) {
                carry = 1;
                queue.addFirst('0');
            } else if (aInt + bInt + carry == 1) {
                carry = 0;
                queue.addFirst('1');
            } else if (aInt + bInt + carry == 0) {
                carry = 0;
                queue.addFirst('0');
            }
            aIndex--;
            bIndex--;
        }

        if (carry > 0) {
            queue.addFirst('1');
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (char singleChar : queue) {
            stringBuilder.append(singleChar);
        }
        return stringBuilder.toString();

    }


}
