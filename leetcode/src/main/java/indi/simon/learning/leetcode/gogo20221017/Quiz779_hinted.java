package indi.simon.learning.leetcode.gogo20221017;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 一种自底向上的思想，先观察了规律，确定了最终结果可以有一个通项公式由前向后递推得到，
// 那么就假定前一项的结果已经知道，来写递归函数，前一项的结果又由前一项得出。
public class Quiz779_hinted {

    public static void main(String[] args) {
        Quiz779_hinted quiz779Hinted = new Quiz779_hinted();
        int res = quiz779Hinted.kthGrammar(3, 2);
        System.out.println(res);
    }

    public int kthGrammar(int n, int k) {
        return kthGrammarInternal(n, k) == '0' ? 0 : 1;
    }

    private char kthGrammarInternal(int n, int k) {
        if (n == 1) {
            return '0';
        }

        int halfLength = (int) Math.pow(2, n - 1) / 2;
        if (k > halfLength) {
            return kthGrammarInternal(n - 1, k - halfLength) == '0' ? '1' : '0';
        } else {
            return kthGrammarInternal(n - 1, k);
        }
    }


}
