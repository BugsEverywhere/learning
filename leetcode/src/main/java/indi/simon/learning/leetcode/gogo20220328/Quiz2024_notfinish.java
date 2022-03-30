package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2024_notfinish {

    public static void main(String[] args) {
        Quiz2024_notfinish quiz2024 = new Quiz2024_notfinish();
        int res = quiz2024.maxConsecutiveAnswers("TTTFTTTTTTFTFTTTTTTFTT", 2);
        System.out.println(res);
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        int n = answerKey.length();
        int maxSeriesCountSoFar = 0;
        for (int left = 0, right = 0, needReduceSum = 0; right < n; right++) {
            needReduceSum += answerKey.charAt(right) != ch ? 1 : 0;
            while (needReduceSum > k) {
                needReduceSum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            maxSeriesCountSoFar = Math.max(maxSeriesCountSoFar, right - left + 1);
        }
        return maxSeriesCountSoFar;
    }

    //todo: 分两次（针对T和F）做滑动窗口，左右两个指针，首先左指针不动，右指针往前走，中间统计窗口内异类（对T来说是F，对F来说是T）的个数，一旦异类个数超过了额度k，右指针停住
    // 左指针就往前走减小窗口内的异类，直到窗口内的异类个数小于等于k。此间统计满足要求的窗口（也就是异类个数小于等于k的窗口）长度，拿最大长度返回即为所需结果

}
