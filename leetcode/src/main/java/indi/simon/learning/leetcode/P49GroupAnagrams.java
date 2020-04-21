package indi.simon.learning.leetcode;

import java.math.BigInteger;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P49GroupAnagrams {

    public static void main(String[] args) {

        String[] test = new String[]{"hos", "boo", "nay", "deb", "wow", "bop", "bob", "brr", "hey", "rye", "eve", "elf", "pup", "bum", "iva", "lyx", "yap", "ugh", "hem", "rod", "aha", "nam", "gap", "yea", "doc", "pen", "job", "dis", "max", "oho", "jed", "lye", "ram", "pup", "qua", "ugh", "mir", "nap", "deb", "hog", "let", "gym", "bye", "lon", "aft", "eel", "sol", "jab"};

        List<List<String>> result = groupAnagrams(test);

        System.out.println(result);

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<BigInteger, List<String>> result = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            byte[] byteArr = new byte[26];
            char[] charArr = str.toCharArray();
            for (char singleChar : charArr) {
                byteArr[singleChar - 'a'] = (byte) (byteArr[singleChar - 'a'] + 1);
            }
            BigInteger bigInteger = new BigInteger(byteArr);
            if (result.get(bigInteger) == null) {
                result.put(bigInteger, new ArrayList<>());
            }
            result.get(bigInteger).add(str);
        }
        List<List<String>> finalResult = new ArrayList<>();
        Iterator<Map.Entry<BigInteger, List<String>>> it = result.entrySet().iterator();
        while (it.hasNext()) {
            finalResult.add(it.next().getValue());
        }
        return finalResult;
    }


}

//todo: 思路，每个字符串对应一个大小为26的byte类型数组，数组中下标为字符串各个字符的ASCII值的位置置为1，
// 则每个byte数组可以对应到一个BigInteger，全局维护一个Map<BigInteger, List<String>>，遍历所有字符串
// 填充这个Map，然后遍历Map打印结果
