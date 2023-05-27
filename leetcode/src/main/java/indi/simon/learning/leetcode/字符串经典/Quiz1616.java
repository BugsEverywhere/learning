package indi.simon.learning.leetcode.字符串经典;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 这道题还是很有意思，虽然是自己摸索出来的，但是过程比较曲折，需要注意一点，那就是a和b一个取前缀，一个取后缀，来组成回文串的话，需要分别对两者进行两次遍历，
// 也就是4次遍历，即两者分别作为主串遍历一次，以及在各自作为主串和从串时，又作为前缀串和后缀串遍历一次，也就是说这四种情况是
// 1. a作为主串前缀串
// 2. a作为主串后缀串
// 3. b作为主串前缀串
// 4. b作为主串后缀串
public class Quiz1616 {

    public static void main(String[] args) {
        Quiz1616 quiz1616 = new Quiz1616();
//boolean res = quiz1616.checkPalindromeFormation(a, b);
//        System.out.println(res);
    }
    //=========================================================official

//    public boolean checkPalindromeFormationOfficial(String a, String b) {
//        return checkConcatenation(a, b) || checkConcatenation(b, a);
//    }
//
//    public boolean checkConcatenation(String a, String b) {
//        int n = a.length();
//        int left = 0, right = n - 1;
//        while (left < right && a.charAt(left) == b.charAt(right)) {
//            left++;
//            right--;
//        }
//        if (left >= right) {
//            return true;
//        }
//        return checkSelfPalindrome(a, left, right) || checkSelfPalindrome(b, left, right);
//    }
//
//    public boolean checkSelfPalindrome(String a, int left, int right) {
//        while (left < right && a.charAt(left) == a.charAt(right)) {
//            left++;
//            right--;
//        }
//        return left >= right;
//    }

    //=========================================================

    public boolean checkPalindromeFormation(String a, String b) {
        int i;
        int j;
        if (a.length() % 2 == 0) {
            i = a.length() / 2 - 1;
            j = a.length() / 2;
        } else {
            i = a.length() / 2;
            j = a.length() / 2;
        }
        if (checkPrePost(a, b, i, j)) {
            return true;
        }
        if (checkPrePost(b, a, i, j)) {
            return true;
        }
        return false;
    }

    /**
     * 将a, b分别作为前缀和后缀字符串进行校验
     * @param pre
     * @param post
     * @param i
     * @param j
     * @return
     */
    private boolean checkPrePost(String pre, String post, int i, int j) {
        //前缀字符串作为主串，以及不作为主串，分别遍历一次
        return checkMainBackup(pre, post, i, j, true) || checkMainBackup(pre, post, i, j, false);
    }

    /**
     * 将checkPrePost中的前缀字符串和后缀字符串分别作为主串进行校验
     * @param main
     * @param backup
     * @param i
     * @param j
     * @param mainPre
     * @return
     */
    private boolean checkMainBackup(String main, String backup, int i, int j, boolean mainPre) {
        boolean switched = false;
        //一开始前后缀字符串都是main
        String preStr = main;
        String postStr = main;
        while (i >= 0 && j < main.length()) {
            if (preStr.charAt(i) != postStr.charAt(j)) {
                //当发生前后缀字符串在某个下标不相等时
                if (!switched && mainPre && main.charAt(i) == backup.charAt(j)) {
                    //指定了main作为前缀字符串，比较main在i以及backup在j是否相等，因为此后需要backup作为后缀字符串
                    postStr = backup;
                    switched = true;
                } else if (!switched && !mainPre && backup.charAt(i) == main.charAt(j)) {
                    //指定了main作为后缀字符串，比较main在j以及backup在i是否相等，因为此后需要backup作为前缀字符串
                    preStr = backup;
                    switched = true;
                } else {
                    //都不相等返回false
                    return false;
                }
            }
            i--;
            j++;
        }
        return true;
    }

}
