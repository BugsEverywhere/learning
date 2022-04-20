package indi.simon.learning.leetcode.gogo20220418;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz388 {

    public static void main(String[] args) {
        Quiz388 quiz388 = new Quiz388();
        int res = quiz388.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
        System.out.println(res);
    }

    private int longestLength = 0;

    public int lengthLongestPath(String input) {
        List<List<Integer>> dirLengthList = new ArrayList<>();
        String[] rowStrArr = input.split("\n");
        for (int i = 0; i < rowStrArr.length; i++) {
            lengthLongestPathInternal(rowStrArr, i, dirLengthList);
        }
        return longestLength;
    }

    private void lengthLongestPathInternal(String[] rows, int index, List<List<Integer>> dirLengthList) {
        String row = rows[index];
        int lastTabIndex = row.lastIndexOf("\t");
        if (lastTabIndex == -1) {
            //是根目录或者根目录下的文件
            if (row.contains(".")) {
                //是文件
                if (row.length() > longestLength) {
                    longestLength = row.length();
                }
            } else {
                //是目录
                List<Integer> rootDirs;
                if (dirLengthList.size() == 0) {
                    rootDirs = new ArrayList<>();
                    rootDirs.add(row.length());
                    dirLengthList.add(rootDirs);
                } else {
                    rootDirs = dirLengthList.get(0);
                    rootDirs.add(row.length());
                }
            }
            return;
        }

        int tabCount = lastTabIndex + 1;
        String pureRow = row.substring(tabCount);
        List<Integer> belongedDirLengthList = dirLengthList.get(tabCount - 1);
        int belongedDirLength = belongedDirLengthList.get(belongedDirLengthList.size() - 1);
        int lengthOfThis = belongedDirLength + 1 + pureRow.length();
        if (pureRow.contains(".")) {
            //是文件
            if (lengthOfThis > longestLength) {
                longestLength = lengthOfThis;
            }
        } else {
            //是目录
            List<Integer> dirListOfThisLevel;
            if (tabCount > dirLengthList.size() - 1) {
                dirListOfThisLevel = new ArrayList<>();
                dirLengthList.add(dirListOfThisLevel);
            } else {
                dirListOfThisLevel = dirLengthList.get(tabCount);
            }
            dirListOfThisLevel.add(belongedDirLength + 1 + pureRow.length());
        }

    }
}
