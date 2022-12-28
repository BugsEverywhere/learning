package indi.simon.learning.leetcode.gogo20221226;


/**
 * @author chenzhuo(zhiyue)
 */
//todo: 做的时候总觉得subString这种操作太耗时，但是此处也没啥其他的办法，该subString的时候还是应该subString，然后就是String是覆写了compareTo的，比较就是按照字典序来比较，这个要牢记
public class Quiz1754_reviewed {

    public static void main(String[] args) {
        Quiz1754_reviewed quiz1754NeedReview = new Quiz1754_reviewed();
        String res = quiz1754NeedReview.largestMerge("cabaa", "bcaaa");
        System.out.println(res);
    }

    public String largestMerge(String word1, String word2) {
        StringBuilder merge = new StringBuilder();
        int i = 0, j = 0;
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length() && word1.substring(i).compareTo(word2.substring(j)) > 0) {
                merge.append(word1.charAt(i));
                i++;
            } else {
                merge.append(word2.charAt(j));
                j++;
            }
        }
        return merge.toString();
    }


}
