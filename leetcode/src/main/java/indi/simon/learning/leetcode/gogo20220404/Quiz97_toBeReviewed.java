package indi.simon.learning.leetcode.gogo20220404;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz97_toBeReviewed {

    public static void main(String[] args) {
        Quiz97_toBeReviewed quiz97 = new Quiz97_toBeReviewed();
        boolean res = quiz97.isInterleave("aabcc", "dbbca", "aadbbcbcac");
        System.out.println(res);
    }

    private int[][][][] mem;

    public boolean isInterleave(String s1, String s2, String s3) {
        //第一个维度长度为2，下标0代表轮到s1走，下标1代表轮到s2走，后面三个维度分别为各个，数组值为0代表还未有结果，为1代表已经递归过这种情况，结果为true，为2代表false
        mem = new int[2][s1.length() + 1][s2.length() + 1][s3.length() + 1];
        //之所以mem在后三个维度上采取+1是为了兼容后续每一次更新mem的时候的边界情况

        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        char[] s3Arr = s3.toCharArray();

        return isInterleaveInternal(true, false, s1Arr, s2Arr, s3Arr, 0, 0, 0);
    }

    private boolean isInterleaveInternal(boolean strSwitch, boolean handed, char[] s1Arr, char[] s2Arr, char[] s3Arr, int s1Index, int s2Index, int s3Index) {
        if (s3Index >= s3Arr.length && s1Index >= s1Arr.length && s2Index >= s2Arr.length) {
            return true;
        } else if (s3Index >= s3Arr.length) {
            return false;
        }

        if (strSwitch) {
            //查看备忘录
            if (s1Index < s1Arr.length && s2Index < s2Arr.length && mem[0][s1Index][s2Index][s3Index] != 0) {
                return mem[0][s1Index][s2Index][s3Index] == 1;
            }
            if (s1Index < s1Arr.length && s3Arr[s3Index] != s1Arr[s1Index] && !handed) {
                //与s3元素不相等，并且不是被s2交接过来的，掰一下开关，交给s2处理
                boolean res = isInterleaveInternal(false, true, s1Arr, s2Arr, s3Arr, s1Index, s2Index, s3Index);
                mem[0][s1Index][s2Index][s3Index] = (res ? 1 : 2);
                return res;
            } else if (s1Index < s1Arr.length && s3Arr[s3Index] == s1Arr[s1Index]) {
                //与s3元素元素相等，下一步留在自己手上或者交给s2
                boolean keep = isInterleaveInternal(true, false, s1Arr, s2Arr, s3Arr, s1Index + 1, s2Index, s3Index + 1);
                if (!handed) {
                    //如果不是被交接而来的，交接出去试试
                    boolean handover = isInterleaveInternal(false, true, s1Arr, s2Arr, s3Arr, s1Index, s2Index, s3Index);
                    boolean res = keep || handover;
                    mem[0][s1Index][s2Index][s3Index] = (res ? 1 : 2);
                    return res;
                } else {
                    mem[0][s1Index][s2Index][s3Index] = (keep ? 1 : 2);
                    return keep;
                }
            } else if (s1Index == s1Arr.length && !handed) {
                //S1弹尽粮绝，并且不是被转交过来的，交接给s2处理
                boolean res = isInterleaveInternal(false, true, s1Arr, s2Arr, s3Arr, s1Index, s2Index, s3Index);
                mem[0][s1Index][s2Index][s3Index] = (res ? 1 : 2);
                return res;
            } else {
                //不满足上述三个条件的任何一个，就不合法
                mem[0][s1Index][s2Index][s3Index] = 2;
                return false;
            }
        } else {
            //查看备忘录
            if (s1Index < s1Arr.length && s2Index < s2Arr.length && mem[0][s1Index][s2Index][s3Index] != 0) {
                return mem[1][s1Index][s2Index][s3Index] == 1;
            }
            if (s2Index < s2Arr.length && s3Arr[s3Index] != s2Arr[s2Index] && !handed) {
                //与s3元素不相等，并且不是被s1交接过来的，掰一下开关，交给s1处理
                boolean res = isInterleaveInternal(true, true, s1Arr, s2Arr, s3Arr, s1Index, s2Index, s3Index);
                mem[1][s1Index][s2Index][s3Index] = (res ? 1 : 2);
                return res;
            } else if (s2Index < s2Arr.length && s3Arr[s3Index] == s2Arr[s2Index]) {
                //与s3元素元素相等，下一步留在自己手上,或者交给s1
                boolean keep = isInterleaveInternal(false, false, s1Arr, s2Arr, s3Arr, s1Index, s2Index + 1, s3Index + 1);
                if (!handed) {
                    boolean handover = isInterleaveInternal(true, true, s1Arr, s2Arr, s3Arr, s1Index, s2Index, s3Index);
                    boolean res = keep || handover;
                    mem[1][s1Index][s2Index][s3Index] = (res ? 1 : 2);
                    return res;
                } else {
                    mem[1][s1Index][s2Index][s3Index] = (keep ? 1 : 2);
                    return keep;
                }
            } else if (s2Index == s2Arr.length && !handed) {
                //S2弹尽粮绝，并且不是被转交过来的，交接给s1处理
                boolean res = isInterleaveInternal(true, true, s1Arr, s2Arr, s3Arr, s1Index, s2Index, s3Index);
                mem[1][s1Index][s2Index][s3Index] = (res ? 1 : 2);
                return res;
            } else {
                //不满足上述三个条件的任何一个，就不合法
                mem[1][s1Index][s2Index][s3Index] = 2;
                return false;
            }
        }
    }


}
