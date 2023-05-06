package indi.simon.learning.leetcode.gogo20230501;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1419 {

    public static void main(String[] args) {
        Quiz1419 quiz1419 = new Quiz1419();
        int res = quiz1419.minNumberOfFrogs("crcoakroak");
        System.out.println(res);
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int[] arr = new int[4];

        int maxGrogCnt = 0;
        int frogCnt = 0;

        for (char c : croakOfFrogs.toCharArray()) {
            if (c == 'c') {
                frogCnt++;
                arr[0]++;
            } else if (c == 'r') {
                arr[0]--;
                if (arr[0] < 0) {
                    return -1;
                }
                arr[1]++;
            } else if (c == 'o') {
                arr[1]--;
                if (arr[1] < 0) {
                    return -1;
                }
                arr[2]++;
            } else if (c == 'a') {
                arr[2]--;
                if (arr[2] < 0) {
                    return -1;
                }
                arr[3]++;
            } else if (c == 'k') {
                arr[3]--;
                if (arr[3] < 0) {
                    return -1;
                }
                maxGrogCnt = Math.max(maxGrogCnt, frogCnt);
                frogCnt--;
            }
        }

        for (int cnt : arr) {
            if (cnt != 0) {
                return -1;
            }
        }

        return maxGrogCnt;
    }


}
