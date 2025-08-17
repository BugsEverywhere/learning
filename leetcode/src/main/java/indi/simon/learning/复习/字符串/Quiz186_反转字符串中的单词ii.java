package indi.simon.learning.复习.字符串;

/**
 * Created by Chen Zhuo on 2023/9/10.
 *
 * 给你一个字符数组 s ，反转其中 单词 的顺序。
 *
 * 单词 的定义为：单词是一个由非空格字符组成的序列。s 中的单词将会由单个空格分隔。
 *
 * 必须设计并实现 原地 解法来解决此问题，即不分配额外的空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出：["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 示例 2：
 *
 * 输入：s = ["a"]
 * 输出：["a"]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 可以是一个英文字母（大写或小写）、数字、或是空格 ' ' 。
 * s 中至少存在一个单词
 * s 不含前导或尾随空格
 * 题目数据保证：s 中的每个单词都由单个空格分隔
 */
public class Quiz186_反转字符串中的单词ii {

    public static void main(String[] args) {
        char[] s = new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        Quiz186_反转字符串中的单词ii quiz186 = new Quiz186_反转字符串中的单词ii();
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
