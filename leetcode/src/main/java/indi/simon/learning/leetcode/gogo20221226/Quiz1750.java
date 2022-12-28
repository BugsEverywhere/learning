package indi.simon.learning.leetcode.gogo20221226;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1750 {

    public static void main(String[] args) {
        Quiz1750 quiz1750 = new Quiz1750();
        int res = quiz1750.minimumLength("bbbbbbbbbbbbbbbbbbb");
        System.out.println(res);
    }

    public int minimumLength(String s) {

        if (s == null || "".equals(s) || s.length() == 0) {
            return 0;
        }

        int head = 0;
        int tail = s.length() - 1;

        while (head < tail) {
            if (s.charAt(head) != s.charAt(tail)) {
                break;
            }
            char c = s.charAt(head);
            while (head < s.length() && s.charAt(head) == c) {
                head++;
            }
            while (tail >= 0 && s.charAt(tail) == c) {
                tail--;
            }
        }

        if (tail - head == 1) {
            return s.charAt(head) == s.charAt(tail) ? 0 : 2;
        } else if (tail < head) {
            return 0;
        } else {
            return tail - head + 1;
        }

    }


}
