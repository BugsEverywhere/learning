package indi.simon.learning.leetcode.gogo2020april.week20to26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P28 {

    public static void main(String[] args) {

        System.out.println(strStr("babbbbbabb" ,"bbab"));

    }

    public static int strStr(String haystack, String needle) {

        if ("".equals(needle)) {
            return 0;
        }

        //用于每一次碰上坏字符的时候，快速定位模式串中下标小于案发现场并且最近的一个与坏字符相等的字符
        Map<Character, List<Integer>> needleCharPosMap = new HashMap<>(needle.length());
        char[] modeArr = needle.toCharArray();
        for (int i = 0; i < needle.length(); i++) {
            List<Integer> listOfThisChar = needleCharPosMap.get(modeArr[i]);
            if (listOfThisChar == null) {
                listOfThisChar = new ArrayList<>();
                listOfThisChar.add(i);
                needleCharPosMap.put(modeArr[i], listOfThisChar);
            } else {
                listOfThisChar.add(i);
            }
        }

        //坏字符比较
        char[] mainArr = haystack.toCharArray();
        int currentTail = modeArr.length - 1;
        while (currentTail < mainArr.length) {
            int i = currentTail;
            for (int j = modeArr.length - 1; ; j--, i--) {
                if (j < 0) {
                    return i + 1;
                }
                if (modeArr[j] != mainArr[i]) {
                    List<Integer> badCharPosList = needleCharPosMap.get(mainArr[i]);
                    int lastBadCharPosInModeArr = binaryBlurSearch(badCharPosList, j);
                    if (lastBadCharPosInModeArr == -1) {
                        //在案发现场j之前的模式串中没有找到坏字符，需要移动j+1位
                        currentTail = currentTail + j + 1;
                    } else {
                        //找着了坏字符
                        currentTail = currentTail + (j - lastBadCharPosInModeArr);
                    }
                    break;
                }
            }
        }

        return -1;
    }


    private static int binaryBlurSearch(List<Integer> arr, int num) {
        if (arr == null) {
            return -1;
        }
        int midIndex = arr.size() / 2;
        if (num > arr.get(midIndex)) {
            if (num > arr.get(arr.size() - 1)) {
                return arr.get(arr.size() - 1);
            } else {
                //todo:在这里栽了跟头，因为这里是要做模糊左查询，也就是找比目标刚好小的那一个，因此在mid的右侧递归的时候，要判断结果是否为-1，因为此时代表num比mid右侧所有的都小，这样就直接返回Mid的元素即可
                int resultOnRight = binaryBlurSearch(arr.subList(midIndex + 1, arr.size()), num);
                if(resultOnRight == -1){
                    return arr.get(midIndex);
                } else {
                    return resultOnRight;
                }
            }
        } else if (num == arr.get(midIndex)) {
            return arr.get(midIndex);
        } else {
            if (num < arr.get(0)) {
                //因为是查找仅比num小的一个值，如果num比arr中最小的还小，则返回-1，因为没有比num更小的了
                return -1;
            } else {
                return binaryBlurSearch(arr.subList(0, midIndex), num);
            }
        }
    }
}
