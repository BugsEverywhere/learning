package indi.simon.learning.leetcode.gogo20220418;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz821 {

    public static void main(String[] args) {
        Quiz821 quiz821 = new Quiz821();
        int[] res = quiz821.shortestToChar("cizokxcijwbyspcfcqws", 'c');
        System.out.println(res);
    }

    public int[] shortestToChar(String s, char c) {
        int[] res = new int[s.length()];

        char[] sArr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (sArr[i] == c) {
                res[i] = 0;
                continue;
            }

            int k = i + 1;
            int h = i - 1;

            while (true) {
                if (k < s.length() && sArr[k] == c) {
                    res[i] = Math.abs(k - i);
                    break;
                } else if (k < s.length() - 1) {
                    k++;
                }

                if (h >= 0 && sArr[h] == c) {
                    res[i] = Math.abs(h - i);
                    break;
                } else if (h >= 1) {
                    h--;
                }
            }
        }

        return res;
    }


}
