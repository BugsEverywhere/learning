package indi.simon.learning.leetcode.gogo20221031;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1662 {

    public static void main(String[] args) {

    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        int indexW1 = 0;
        int indexW2 = 0;

        int indexW1Sub = 0;
        int indexW2Sub = 0;

        while (indexW1 < word1.length && indexW2 < word2.length && indexW1Sub < word1[indexW1].length() && indexW2Sub < word2[indexW2].length()) {
            if (word1[indexW1].charAt(indexW1Sub) == word2[indexW2].charAt(indexW2Sub)) {
                indexW1Sub++;
                if (indexW1Sub >= word1[indexW1].length()) {
                    indexW1Sub = 0;
                    indexW1++;
                }

                indexW2Sub++;
                if (indexW2Sub >= word2[indexW2].length()) {
                    indexW2Sub = 0;
                    indexW2++;
                }
            } else {
                return false;
            }
        }

        if (indexW1 == word1.length && indexW2 == word2.length && indexW1Sub == 0 && indexW2Sub == 0) {
            return true;
        }

        return false;
    }

}
