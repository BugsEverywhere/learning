package indi.simon.learning.leetcode.gogo20231022;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/10/22.
 */
public class Quiz271_needReview {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("PJ|");
        list.add("Q.lF2 awD");
        Quiz271_needReview quiz271NeedReview = new Quiz271_needReview();
        String str = quiz271NeedReview.encode(list);
        List<String> res = quiz271NeedReview.decode(str);
    }

    // Encodes string length to bytes string
    public String intToString(String s) {
        int x = s.length();
        char[] bytes = new char[4];
        for(int i = 3; i > -1; --i) {
            bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
        }
        return new String(bytes);
    }

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s: strs) {
            sb.append(intToString(s));
            sb.append(s);
        }
        return sb.toString();
    }

    // Decodes bytes string to integer
    public int stringToInt(String bytesStr) {
        int result = 0;
        for(char b : bytesStr.toCharArray())
            result = (result << 8) + (int)b;
        return result;
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        int i = 0, n = s.length();
        List<String> output = new ArrayList();
        while (i < n) {
            int length = stringToInt(s.substring(i, i + 4));
            i += 4;
            output.add(s.substring(i, i + length));
            i += length;
        }
        return output;
    }

}
