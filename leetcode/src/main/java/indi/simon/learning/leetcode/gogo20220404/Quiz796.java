package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz796 {

    public static void main(String[] args) {
        Quiz796 quiz796 = new Quiz796();
        boolean res = quiz796.rotateString("bbbacddceeb", "ceebbbbacdd");
        System.out.println(res);
    }

    public boolean rotateString(String s, String goal) {
        char[] sArr = s.toCharArray();
        char[] goalArr = goal.toCharArray();
        if (sArr.length != goalArr.length) {
            return false;
        }
        int i = 0;
        for (; i < sArr.length; i++) {
            if (sArr[i] == goalArr[0]) {
                if (rotateStrInternal(i, sArr, goalArr)) {
                    return true;
                }
                //todo: 第二次提交没考虑到main方法里面的情形，也就是由多个"head"的情况
            }
        }
        return false;
    }


    private boolean rotateStrInternal(int sHead, char[] sArr, char[] goalArr) {
        //找到了头儿
        int j = sHead + 1;
        int k = 1;
        while (j != sHead) {
            if (j == sArr.length) {
                j = 0;
            }
            if (sArr[j] == goalArr[k]) {
                j++;
                k++;
            } else {
                return false;
            }
        }

        return true;
    }

}
