package indi.simon.learning.leetcode.gogo20221003;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz921 {

    public static void main(String[] args) {
        Quiz921 quiz921 = new Quiz921();
        //todo: 此题注意下面这种坑爹用例即可
        int res = quiz921.minAddToMakeValid("()))((");
        System.out.println(res);
    }

    public int minAddToMakeValid(String s) {
        char[] cArr = s.toCharArray();

        int res = 0;

        int bracketCount = 0;
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == '(') {
                bracketCount++;
            } else {
                bracketCount--;
            }
            if (bracketCount < 0) {
                res += Math.abs(bracketCount);
                bracketCount = 0;
            }
        }

        return res + bracketCount;
    }

}
