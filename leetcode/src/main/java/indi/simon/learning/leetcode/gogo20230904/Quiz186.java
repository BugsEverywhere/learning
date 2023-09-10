package indi.simon.learning.leetcode.gogo20230904;

/**
 * Created by Chen Zhuo on 2023/9/10.
 */
public class Quiz186 {

    public static void main(String[] args) {
        char[] s = new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        Quiz186 quiz186 = new Quiz186();
        quiz186.reverseWords(s);
        System.out.println(s);
    }

    public void reverseWords(char[] s) {
        if (s.length == 1) {
            return;
        }
        //先将整个数组掉头
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            swap(s, i, j);
            i++;
            j--;
        }

        int m = 0;
        int n = 0;

        while (n < s.length) {
            //从头开始寻找每一个单词
            int nextSpace;
            while (n < s.length && s[n] != ' ') {
                n++;
            }
            nextSpace = n;
            n--;

            //找到一个单词了，倒转他的顺序
            while (m < n) {
                swap(s, m, n);
                m++;
                n--;
            }

            //寻找下一个单词
            m = nextSpace + 1;
            n = nextSpace + 1;
        }


    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

}
