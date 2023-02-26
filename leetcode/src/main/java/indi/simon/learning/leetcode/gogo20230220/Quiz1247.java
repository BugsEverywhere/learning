package indi.simon.learning.leetcode.gogo20230220;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1247 {

    public static void main(String[] args) {
        Quiz1247 quiz1247 = new Quiz1247();
        int res = quiz1247.minimumSwap("xx", "xy");
        System.out.println(res);
    }

    //规律就是，如果s1中的x所在下标在s2中为y，那么只要这种错位对还有另一对，那么这两对通过一次交换就能上下相等，比如xxy和yyx，在下标为0和1的位置都是上x下y，进行一次交换就都变成yxy
    // 那么只需要统计s1中为x并且s2中为y的下标个数，记为cx，如果这个个数是双数，除以2就能得到这些字符相等的交换次数，同理算一遍s1中为y并且s2中为x的下标个数，记为cy。
    // 那么剩下的就只需要考虑cx和cy单出来的那两对（如果cx为双数则cy肯定为双数，否则都为单数），这种情况就好比xy与yx，他们肯定是需要交换2次才行的，所以结果再加上cx（或者cy）
    // 对2取余再乘以2即可
    public int minimumSwap(String s1, String s2) {
        //s1中为x，并且s2为y字的下标数
        int xDiffCount = 0;
        //s1中为y，并且s2为x字的下标数
        int yDiffCount = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == 'x' && s1.charAt(i) != s2.charAt(i)) {
                xDiffCount++;
            } else if (s1.charAt(i) == 'y' && s1.charAt(i) != s2.charAt(i)) {
                yDiffCount++;
            }
        }

        if ((xDiffCount + yDiffCount) % 2 == 1) {
            return -1;
        }

        return xDiffCount / 2 + yDiffCount / 2 + (xDiffCount % 2) * 2;
    }

}
