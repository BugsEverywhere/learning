package indi.simon.learning.leetcode.gogo20221010;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1790 {

    public static void main(String[] args) {
        Quiz1790 quiz1790 = new Quiz1790();
        boolean res = quiz1790.areAlmostEqual("aa", "ac");
        System.out.println(res);
    }

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        int difCount = 0;
        int diffIndex1 = -1;
        int diffIndex2 = -1;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                difCount++;
                //todo: 这里要记得判断一下总差异个数，如果比2还大了就没必要往下遍历了
                if (difCount > 2) {
                    return false;
                }
                if (difCount == 2) {
                    diffIndex2 = i;
                    break;
                } else if (difCount == 1) {
                    diffIndex1 = i;
                }
            }
        }

        //todo: 如果遍历下来总差异个数小于2也可以直接返回了
        if (difCount < 2) {
            return false;
        }

        char[] chars2 = s2.toCharArray();
        char tmp = chars2[diffIndex1];
        chars2[diffIndex1] = chars2[diffIndex2];
        chars2[diffIndex2] = tmp;

        String s3 = new String(chars2);

        return s3.equals(s1);
    }

}
