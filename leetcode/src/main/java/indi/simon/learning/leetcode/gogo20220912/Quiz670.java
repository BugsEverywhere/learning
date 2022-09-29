package indi.simon.learning.leetcode.gogo20220912;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz670 {

    public static void main(String[] args) {
        Quiz670 quiz670 = new Quiz670();
        int res = quiz670.maximumSwap(98368);
        System.out.println(res);
    }

    public int maximumSwap(int num) {
        String numStr = Integer.toString(num);
        char[] cArr = numStr.toCharArray();

        for (int i = 0; i < cArr.length; i++) {
            int maxIndexAfterI = i;
            char maxAfterI = cArr[i];
            for (int j = i + 1; j < cArr.length; j++) {
                //todo: 第二个判断条件很容易忘记，例如98368，当i=1, j=4时
                if (cArr[j] >= maxAfterI && cArr[j] != cArr[i]) {
                    maxAfterI = cArr[j];
                    maxIndexAfterI = j;
                }
            }
            if (maxIndexAfterI != i) {
                char temp = cArr[i];
                cArr[i] = cArr[maxIndexAfterI];
                cArr[maxIndexAfterI] = temp;
                break;
            }
        }

        numStr = new String(cArr);
        return Integer.parseInt(numStr);
    }

}
