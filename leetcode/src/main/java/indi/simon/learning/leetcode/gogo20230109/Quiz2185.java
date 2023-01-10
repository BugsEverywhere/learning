package indi.simon.learning.leetcode.gogo20230109;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2185 {

    public static void main(String[] args) {

    }

    public int prefixCount(String[] words, String pref) {

        int res = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                res++;
            }
        }

        return res;
    }

}
