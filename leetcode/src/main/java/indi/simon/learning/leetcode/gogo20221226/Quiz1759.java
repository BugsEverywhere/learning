package indi.simon.learning.leetcode.gogo20221226;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1759 {

    public static void main(String[] args) {
        Quiz1759 quiz1759 = new Quiz1759();
        int res = quiz1759.countHomogenous("zzzzz");
        System.out.println(res);
    }

    public int countHomogenous(String s) {
        int index = 0;
        long res = 0;

        while (index < s.length()) {
            char curr = s.charAt(index);
            long currLength = 1;
            index++;
            while (index < s.length() && s.charAt(index) == curr) {
                index++;
                currLength++;
            }
            //将所有的同字符子串的个数累加起来
            long sum = currLength;
            while (currLength > 0) {
                currLength--;
                sum += currLength;
            }
            res += sum;
        }

        return (int) (res % ((int) Math.pow(10, 9) + 7));
    }

}
