package indi.simon.learning.leetcode.gogo20220221;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1189 {

    public static void main(String[] args) {
        Quiz1189 quiz1189 = new Quiz1189();
        int res = quiz1189.maxNumberOfBalloons("nlaebolko");
        System.out.println(res);
    }

    public int maxNumberOfBalloons(String text) {
        int[] count = new int[5];
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'b') {
                count[0]++;
            } else if (text.charAt(i) == 'a') {
                count[1]++;
            } else if (text.charAt(i) == 'l') {
                count[2]++;
            } else if (text.charAt(i) == 'o') {
                count[3]++;
            } else if (text.charAt(i) == 'n') {
                count[4]++;
            }
        }

        return Math.min(count[0], Math.min(count[1], Math.min(count[2] / 2, Math.min(count[3] / 2 , count[4]))));
        //todo: 粗心哦。。。。
    }


}
