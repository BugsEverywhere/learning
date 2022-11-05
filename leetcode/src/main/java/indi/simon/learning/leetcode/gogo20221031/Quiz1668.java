package indi.simon.learning.leetcode.gogo20221031;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1668 {

    public static void main(String[] args) {
        Quiz1668 quiz1668 = new Quiz1668();
        int res = quiz1668.maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba");
        System.out.println(res);
    }

    public int maxRepeating(String sequence, String word) {
        char[] cArr = sequence.toCharArray();
        char[] cWordArr = word.toCharArray();
        int k = 0;
        for (int i = 0; i < cArr.length; ) {
            if (cArr[i] == cWordArr[0]) {
                int originI = i;
                int subK = 0;
                int j = 0;
                while (i < cArr.length) {
                    if (cWordArr[j] != cArr[i]) {
                        //todo: 这里当遇到不相符的字符的时候要回到母串本次比较的原点重新开始
                        i = originI;
                        break;
                    }
                    i++;
                    if (j == cWordArr.length - 1) {
                        //到word的结尾了
                        j = 0;
                        subK++;
                    } else {
                        j++;
                    }
                }
                k = Math.max(k, subK);
            }
            i++;
        }
        return k;
    }

}
