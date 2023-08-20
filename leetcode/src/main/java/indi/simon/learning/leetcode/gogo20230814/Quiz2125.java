package indi.simon.learning.leetcode.gogo20230814;

/**
 * Created by Chen Zhuo on 2023/8/20.
 */
public class Quiz2125 {

    public static void main(String[] args) {

    }

    public int numberOfBeams(String[] bank) {
        int[] deviceCnt = new int[bank.length];

        for (int i = 0; i < bank.length; i++) {
            if (!bank[i].contains("1")) {
                continue;
            }
            for (int j = 0; j < bank[i].length(); j++) {
                if (bank[i].charAt(j) == '1') {
                    deviceCnt[i]++;
                }
            }
        }
        int res = 0;
        Integer lastNoneZeroLine = null;
        for (int i = 0; i < deviceCnt.length; i++) {
            if (lastNoneZeroLine == null) {
                if(deviceCnt[i] > 0){
                    lastNoneZeroLine = deviceCnt[i];
                }
            } else if(deviceCnt[i] != 0) {
                res += lastNoneZeroLine * deviceCnt[i];
                lastNoneZeroLine = deviceCnt[i];
            }
        }

        return res;
    }
}
