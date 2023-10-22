package indi.simon.learning.leetcode.gogo20231022;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/10/22.
 */
public class Quiz320 {

    public static void main(String[] args) {
        Quiz320 quiz320 = new Quiz320();
        List<String> res = quiz320.generateAbbreviations("word");
        System.out.println(res);
    }

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        generateAbbreviationsInternal(res, word, 0, "");
        return res;
    }

    private void generateAbbreviationsInternal(List<String> res, String word, int i, String prefix) {
        if (i >= word.length()) {
            res.add(prefix);
            return;
        }

        //从i开始的某一段转成长度
        for (int j = i; j < word.length(); j++) {
            //从i开始，j结束，这一段转成长度
            String newPrefix = prefix + (j - i + 1);
            //todo:这里要记得把跳过的间隔字符给加上
            if (j + 1 < word.length()) {
                newPrefix += word.substring(j + 1, j + 2);
            }
            generateAbbreviationsInternal(res, word, j + 2, newPrefix);
        }

        //跳过i，继续往下递归
        //todo: 这里也要记得在往下递归的时候加上跳过的字符
        generateAbbreviationsInternal(res, word, i + 1, prefix + word.charAt(i));
    }

}
