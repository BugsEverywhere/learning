package indi.simon.learning.leetcode.gogo20221212;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1832 {

    public static void main(String[] args) {

    }

    public boolean checkIfPangram(String sentence) {
        int[] arr = new int[26];

        for (char c : sentence.toCharArray()) {
            arr[c - 'a']++;
        }

        for (int i : arr) {
            if (i == 0) {
                return false;
            }
        }

        return true;
    }


}
