package indi.simon.learning.leetcode.gogo20220509;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz01_05 {

    public static void main(String[] args) {
        Quiz01_05 quiz01_05 = new Quiz01_05();
        String str1 = "ab";
        String str2 = "bc";
        boolean res = quiz01_05.oneEditAway(str1, str2);
        System.out.println(res);
    }

    public boolean oneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }

        if (first.length() == second.length()) {
            //检查是否可通过替换达到目的
            int notEqualCharCount = 0;
            for (int i = 0; i < first.length(); i++) {
                if (notEqualCharCount > 1) {
                    return false;
                }
                if (first.charAt(i) != second.charAt(i)) {
                    notEqualCharCount++;
                }
            }
            if(notEqualCharCount > 1){
                return false;
            }
        } else {
            if (Math.abs(first.length() - second.length()) > 1) {
                return false;
            }
            //检查是否可通过插入、删除
            int i = 0;
            int j = 0;
            while (i < first.length() && j < second.length()) {
                if (Math.abs(i - j) > 1) {
                    return false;
                }
                if (first.charAt(i) == second.charAt(j)) {
                    i++;
                    j++;
                } else {
                    if (first.length() > second.length() && i + 1 < first.length() && first.charAt(i + 1) == second.charAt(j)) {
                        i = i + 2;
                        j++;
                    } else if (second.length() > first.length() && j + 1 < second.length() && first.charAt(i) == second.charAt(j + 1)) {
                        j = j + 2;
                        i++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
