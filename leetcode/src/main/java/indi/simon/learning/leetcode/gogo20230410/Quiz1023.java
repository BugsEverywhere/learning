package indi.simon.learning.leetcode.gogo20230410;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1023 {

    public static void main(String[] args) {
        Quiz1023 quiz1023 = new Quiz1023();
        List<Boolean> res = quiz1023.camelMatch(new String[]{"aksvbjLiknuTzqon","ksvjLimflkpnTzqn","mmkasvjLiknTxzqn","ksvjLiurknTzzqbn","ksvsjLctikgnTzqn","knzsvzjLiknTszqn"}, "ksvjLiknTzqn");
        System.out.println(res);
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<UpperCase> upperCasePattern = new ArrayList<>();
        UpperCase nullUpperCase = new UpperCase(null, new ArrayList<>());
        upperCasePattern.add(nullUpperCase);
        for (char c : pattern.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                UpperCase newUpperCase = new UpperCase(c, new ArrayList<>());
                upperCasePattern.add(newUpperCase);
            } else {
                UpperCase upperCase = upperCasePattern.get(upperCasePattern.size() - 1);
                upperCase.getLowerCase().add(c);
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (String query : queries) {
            res.add(checkQuery(query, upperCasePattern));
        }
        return res;
    }

    private boolean checkQuery(String query, List<UpperCase> upperCaseList) {

        int i = 0;
        int j = 0;

        for (char c : query.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                //当前目标串是一个大写字母，需要校验一下前面大写字母区间是否用完
                if (j != upperCaseList.get(i).getLowerCase().size()) {
                    return false;
                }
                //前一个区间合格
                i++;
                //j还原
                j = 0;
                if (i >= upperCaseList.size() //模式串已经没有大写字母了
                        || c != upperCaseList.get(i).getC()) { //当前大写字母与模式串不匹配
                    return false;
                }
            } else {
                //目标串当前是一个小写字母
                if (j < upperCaseList.get(i).getLowerCase().size()) {
                    if (upperCaseList.get(i).getLowerCase().get(j) == c) {
                        //模式串小写字母前移一位
                        j++;
                    }
                }
            }
        }

        return i == upperCaseList.size() - 1 && (j == upperCaseList.get(i).getLowerCase().size());
    }

    private class UpperCase {

        private Character c;
        private List<Character> lowerCase;

        public UpperCase(Character c, List<Character> lowerCase) {
            this.c = c;
            this.lowerCase = lowerCase;
        }

        public Character getC() {
            return c;
        }

        public void setC(Character c) {
            this.c = c;
        }

        public List<Character> getLowerCase() {
            return lowerCase;
        }

        public void setLowerCase(List<Character> lowerCase) {
            this.lowerCase = lowerCase;
        }
    }


}
