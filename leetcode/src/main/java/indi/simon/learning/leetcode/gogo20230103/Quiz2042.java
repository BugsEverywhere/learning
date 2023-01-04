package indi.simon.learning.leetcode.gogo20230103;

import java.util.ArrayDeque;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2042 {

    public static void main(String[] args) {
        Quiz2042 quiz2042 = new Quiz2042();
        boolean res = quiz2042.areNumbersAscending("208 900 1000");
        System.out.println(res);
    }

    public boolean areNumbersAscending(String s) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        String[] strArr = s.split(" ");
        for (String str : strArr) {
            try {
                Integer num = Integer.parseInt(str);
                if (queue.size() == 0 || queue.peekLast() < num) {
                    queue.add(num);
                } else {
                    return false;
                }
            } catch (Exception ignored) {
            }
        }
        return true;
    }


}
