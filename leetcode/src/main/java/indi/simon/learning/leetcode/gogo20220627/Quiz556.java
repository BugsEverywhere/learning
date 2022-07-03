package indi.simon.learning.leetcode.gogo20220627;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 第一次提交的时候审题审错了。。。
public class Quiz556 {

    public static void main(String[] args) {
        Quiz556 quiz556 = new Quiz556();
        int res = quiz556.nextGreaterElement(2147483486);
        System.out.println(res);
    }

    public int nextGreaterElement(int n) {
        if (n >= Integer.MAX_VALUE) {
            return -1;
        }
        String nStr = Integer.toString(n);
        char[] nStrCharArr = nStr.toCharArray();

        int i = nStrCharArr.length - 1;
        int j = i - 1;

        while (j >= 0) {
            if (nStrCharArr[j] < nStrCharArr[i]) {
                //发现一个减小的数
                int rightBiggerNum = Integer.MAX_VALUE;
                int finalIndex = -1;
                for (int index = nStrCharArr.length - 1; index > j; index--) {
                    if (nStrCharArr[index] > nStrCharArr[j] && nStrCharArr[index] < rightBiggerNum) {
                        rightBiggerNum = nStrCharArr[index];
                        finalIndex = index;
                    }
                }
                swap(nStrCharArr, finalIndex, j);
                //交换完之后，后面的数应该取最小
                Arrays.sort(nStrCharArr, j + 1, nStrCharArr.length);
                //todo: 这里注意，new String(nStrCharArr)的结果很可能是一个大于32位整形的数，所以这里第二次提交使用的Integer.parseInt就会报错
                long res = Long.parseLong(new String(nStrCharArr));
                if (res > Integer.MAX_VALUE) {
                    return -1;
                } else {
                    return (int)res;
                }
            } else if (nStrCharArr[j] == nStrCharArr[i]) {
                j--;
                i--;
            } else {
                i = j;
                j = i - 1;
            }
        }
        return -1;
    }

    private void swap(char[] charArr, int i, int j) {
        char temp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = temp;
    }
}
