package indi.simon.learning.leetcode.gogo20220411;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz385 {

    public static void main(String[] args) {
        Quiz385 quiz385 = new Quiz385();
        NestedInteger res = quiz385.deserialize("[123,[456,[789]]]");
        System.out.println(res);
    }

    private int index;
    private int subIndex;

    public NestedInteger deserialize(String s) {
        index = 0;
        subIndex = 0;
        String[] arr = s.split(",");
        if (s.length() == 0) {
            return null;
        }
        if (s.startsWith("[")) {
            subIndex++;
            return deserializeInternal(arr);
        } else {
            return new NestedInteger(Integer.parseInt(s));
        }
    }

    private NestedInteger deserializeInternal(String[] comaSplitArr) {
        List<NestedInteger> listOfThisLayer = new ArrayList<>();
        while (index < comaSplitArr.length) {
            if (comaSplitArr[index].charAt(subIndex) == '[') {
                //该位是个数组，往下递归，因为[肯定不是坑位的最后一个字符，所以大胆加一
                subIndex++;
                listOfThisLayer.add(deserializeInternal(comaSplitArr));
            } else if (comaSplitArr[index].charAt(subIndex) == ']') {
                //该位是本层数组的结束
                if (subIndex == comaSplitArr[index].length() - 1) {
                    subIndex = 0;
                    index++;
                } else {
                    subIndex++;
                }
                break;
            } else {
                //是数字
                StringBuilder stringBuilder = new StringBuilder();
                while ((subIndex < comaSplitArr[index].length() && comaSplitArr[index].charAt(subIndex) - '0' >= 0 && comaSplitArr[index].charAt(subIndex) - '0' <= 9)
                        || (subIndex < comaSplitArr[index].length() && comaSplitArr[index].charAt(subIndex) == '-')) {
                    //一直是个数字
                    stringBuilder.append(comaSplitArr[index].charAt(subIndex));
                    subIndex++;
                }
                if (stringBuilder.length() != 0) {
                    listOfThisLayer.add(new NestedInteger(Integer.parseInt(stringBuilder.toString())));
                }
                if (subIndex == comaSplitArr[index].length()) {
                    //整个坑位结束了,循环下一个坑位
                    subIndex = 0;
                    index++;
                } else if (comaSplitArr[index].charAt(subIndex) == ']') {
                    //本数组的结束，下标前移，为后一个数组做准备
                    if (subIndex == comaSplitArr[index].length() - 1) {
                        subIndex = 0;
                        index++;
                    } else {
                        subIndex++;
                    }
                    break;
                }
            }
        }

        if (listOfThisLayer.size() == 0) {
            return new NestedInteger();
        } else {
            NestedInteger nestedInteger = new NestedInteger();
            for (NestedInteger singleInteger : listOfThisLayer) {
                nestedInteger.add(singleInteger);
            }
            return nestedInteger;
        }
    }


    private class NestedInteger {

        public NestedInteger() {
        }

        public NestedInteger(Integer val) {
            this.val = val;
        }

        private Integer val;

        private List<NestedInteger> intList;

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return val != null && (intList == null || intList.size() == 0);
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return val;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.val = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            if (intList == null) {
                intList = new ArrayList<>();
            }
            intList.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return intList;
        }

    }
}
