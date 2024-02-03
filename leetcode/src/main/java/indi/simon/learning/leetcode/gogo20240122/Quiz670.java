package indi.simon.learning.leetcode.gogo20240122;


/**
 * Created by Chen Zhuo on 2024/1/23.
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
        //postArr[i]存放的是i以后的最大值下标
        int[] postArr = new int[numStr.length()];
        char max = cArr[numStr.length() - 1];
        int maxIndex = numStr.length() - 1;
        for (int i = numStr.length() - 1; i >= 0; i--) {
            if (i == numStr.length() - 1) {
                postArr[i] = maxIndex;
            } else {
                if (cArr[i] > max) {
                    max = cArr[i];
                    maxIndex = i;
                }
                postArr[i] = maxIndex;
            }
        }

        for (int i = 0; i < cArr.length; i++) {
            if (cArr[postArr[i]] > cArr[i] && postArr[i] > i) {
                char tmp = cArr[i];
                cArr[i] = cArr[postArr[i]];
                cArr[postArr[i]] = tmp;
                break;
            }
        }

        StringBuilder builder = new StringBuilder();
        for(char c : cArr){
            builder.append(c);
        }

        return Integer.parseInt(builder.toString());
    }

}
