package indi.simon.learning.复习.排序;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Quiz49_字母异位词分组 {

    public static void main(String[] args) {

        String[] test = new String[]{"hos", "boo", "nay", "deb", "wow", "bop", "bob", "brr", "hey", "rye", "eve", "elf", "pup", "bum", "iva", "lyx", "yap", "ugh", "hem", "rod", "aha", "nam", "gap", "yea", "doc", "pen", "job", "dis", "max", "oho", "jed", "lye", "ram", "pup", "qua", "ugh", "mir", "nap", "deb", "hog", "let", "gym", "bye", "lon", "aft", "eel", "sol", "jab"};

        List<List<String>> result = groupAnagrams(test);

        System.out.println(result);

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> listRes = new HashMap<>();

        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            String sortedStr = new String(charArr);
            List<String> list = listRes.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);
            listRes.put(sortedStr, list);
        }
        List<List<String>> res = new ArrayList<>(listRes.values());
        return res;
    }


}
