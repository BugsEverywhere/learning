package indi.simon.learning.leetcode.april2020.week20to26;

public class P14 {

    public static void main(String[] args) {

        String[] testArr = new String[]{};
        System.out.println(longestCommonPrefix(testArr));

    }

    public static String longestCommonPrefix(String[] strs) {

        //todo:忘记判断空输入数组的极端情况
        if(strs.length == 0){
            return "";
        }

        char[] pubArr = null;
        int pubIndex = 0;

        int i = 0;
        while (i < strs.length) {
            if ("".equals(strs[i])) {
                return "";
            }
            if (i == 0) {
                pubArr = strs[i].toCharArray();
                pubIndex = strs[i].length() - 1;
                i++;
                continue;
            }
            char[] thisStrCharArr = strs[i].toCharArray();
            int j = 0;
            for (; j < Math.min(thisStrCharArr.length, pubIndex + 1); j++) {
                if (thisStrCharArr[j] != pubArr[j]) {
                    pubArr = thisStrCharArr;
                    break;
                }
            }
            pubIndex = j - 1;
            i++;
        }

        //todo: pubIndex等于0的情况代表也有公共前缀，之前判断==0也给返回了“”
        if (pubIndex < 0) {
            return "";
        }

        char[] targetPubArr = new char[pubIndex + 1];
        System.arraycopy(pubArr, 0, targetPubArr, 0, pubIndex + 1);

        return new String(targetPubArr);
    }


}
