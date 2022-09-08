package indi.simon.learning.leetcode.gogo20220905;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1592 {

    public static void main(String[] args) {
        String str = "  this   is  a sentence ";
        Quiz1592 quiz1592 = new Quiz1592();
        String res = quiz1592.reorderSpaces(str);
        System.out.println(res);
    }

    public String reorderSpaces(String text) {
        String[] rawWords = text.split("\\s+");
        String[] words;
        if ("".equals(rawWords[0])) {
            words = new String[rawWords.length - 1];
            System.arraycopy(rawWords, 1, words, 0, words.length);
        } else {
            words = rawWords;
        }

        if (words.length == 1) {
            String blankTail = "";
            for (int i = 0; i < text.length() - words[0].length(); i++) {
                blankTail = blankTail + " ";
            }
            return words[0] + blankTail;
        }

        int sumLength = 0;
        for (String singleWord : words) {
            sumLength += singleWord.length();
        }

        //剩余的总空格数
        int leftBlankCount = text.length() - sumLength;
        //单词间的空格个数
        int interval = leftBlankCount / (words.length - 1);
        String intervalBlankStr = "";
        for (int i = 0; i < interval; i++) {
            intervalBlankStr = intervalBlankStr + " ";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == words.length - 1) {
                sb.append(words[i]);
            } else {
                sb.append(words[i]).append(intervalBlankStr);
                leftBlankCount -= interval;
            }
        }

        for (int i = 0; i < leftBlankCount; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

}
