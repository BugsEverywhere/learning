package indi.simon.learning.leetcode.gogo20230417;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz306 {

    public static void main(String[] args) {
        Quiz306 quiz306 = new Quiz306();
        boolean res = quiz306.isAdditiveNumber("11111111111011111111111");
        System.out.println(res);
    }

    public boolean isAdditiveNumber(String num) {
        return isAdditiveNumberInternal(0, -1, -1, -1, -1, num);
    }

    private boolean isAdditiveNumberInternal(int num1Start, int num1End, int num2Start, int num2End, int num3Start, String num) {
        if (num1End == -1) {
            //第一个数就没定
            for (int i = num1Start; i < num.length() - 2; i++) {
                if (num.charAt(num1Start) == '0' && i > num1Start) {
                    //避免前导0
                    continue;
                }
                if (isAdditiveNumberInternal(num1Start, i + 1, i + 1, -1, -1, num)) {
                    return true;
                }
            }
        } else if (num2End == -1) {
            //第一个数定了，第二个数没定
            for (int i = num2Start; i < num.length() - 1; i++) {
                if (num.charAt(num2Start) == '0' && i > num2Start) {
                    //避免前导0
                    continue;
                }
                if (isAdditiveNumberInternal(num1Start, num1End, num2Start, i + 1, i + 1, num)) {
                    return true;
                }
            }
        } else {
            //只有第三个数没定了
            for (int i = num3Start; i < num.length(); i++) {
                if (num.charAt(num3Start) == '0' && i > num3Start) {
                    //避免前导0
                    continue;
                }
                String num1Str = num.substring(num1Start, num1End);
                String num2Str = num.substring(num2Start, num2End);
                String num3Str = num.substring(num3Start, i + 1);
                if (num3Str.equals()) {
                    if (i == num.length() - 1) {
                        return true;
                    }
                    //本层满足，继续探索下一层
                    if (isAdditiveNumberInternal(num2Start, num2End, num3Start, i + 1, i + 1, num)) {
                        return true;
                    }
                }
            }
        }
        //never reach here
        return false;
    }

    public String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuilder third = new StringBuilder();
        int carry = 0, cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            carry = cur / 10;
            cur %= 10;
            third.append((char) (cur + '0'));
        }
        third.reverse();
        return third.toString();
    }


}
