package indi.simon.learning.leetcode.gogo20230116;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1813 {

    public static void main(String[] args) {
        Quiz1813 quiz1813 = new Quiz1813();
        boolean res = quiz1813.areSentencesSimilar("A B C D B B", "A B B");
        System.out.println(res);
    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {

        if (sentence1.equals(sentence2)) {
            return true;
        }

        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");

        if (words1.length == words2.length) {
            return false;
        }

        String[] wordsL;
        String[] wordsS;
        if (words1.length > words2.length) {
            wordsL = words1;
            wordsS = words2;
        } else {
            wordsL = words2;
            wordsS = words1;
        }

        int iL = 0;
        int jL = wordsL.length - 1;

        int iS = 0;
        int jS = wordsS.length - 1;

        //看前缀
        while (iL < wordsL.length && iS < wordsS.length) {
            if (wordsL[iL].equals(wordsS[iS])) {
                iL++;
                iS++;
                if (iS == wordsS.length) {
                    return true;
                }
            } else {
                iL--;
                iS--;
                break;
            }
        }

        //看后缀
        while (jL >= 0 && jS >= 0) {
            if (wordsL[jL].equals(wordsS[jS])) {
                jS--;
                jL--;
                if (jS < 0) {
                    return true;
                }
            } else {
                jS++;
                jL++;
                break;
            }
        }

        if ((iL < 0 || iS < 0) && (jS >= wordsS.length || jL >= wordsL.length)) {
            return false;
        }
        //todo: 此处注意，只需要iS>=jS-1即可，没有必要完全等于，参考用例："A B C D B B", "A B B"
        return iS + 1 >= jS && iL < jL;

    }

}
