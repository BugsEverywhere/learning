package indi.simon.learning.leetcode.gogo20220801;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1374 {

    public static void main(String[] args) {

    }

    public String generateTheString(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        if (n % 2 == 0) {
            for (int i = 0; i < n - 1; i++) {
                stringBuilder.append('a');
            }
            stringBuilder.append('b');
            return stringBuilder.toString();
        } else {
            for (int i = 0; i < n; i++) {
                stringBuilder.append('a');
            }
            return stringBuilder.toString();
        }
    }

}
