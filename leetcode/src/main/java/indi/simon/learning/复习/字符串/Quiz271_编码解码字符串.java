package indi.simon.learning.复习.字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/10/22.
 *
 *请你设计一个算法，可以将一个 字符串列表 编码成为一个 字符串。这个编码后的字符串是可以通过网络进行高效传送的，并且可以在接收端被解码回原来的字符串列表。
 *
 * 1 号机（发送方）有如下函数：
 *
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * 2 号机（接收方）有如下函数：
 *
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * 1 号机（发送方）执行：
 *
 * string encoded_string = encode(strs);
 * 2 号机（接收方）执行：
 *
 * vector<string> strs2 = decode(encoded_string);
 * 此时，2 号机（接收方）的 strs2 需要和 1 号机（发送方）的 strs 相同。
 *
 * 请你来实现这个 encode 和 decode 方法。
 *
 * 不允许使用任何序列化方法解决这个问题（例如 eval）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：dummy_input = ["Hello","World"]
 * 输出：["Hello","World"]
 * 解释：
 * 1 号机：
 * Codec encoder = new Codec();
 * String msg = encoder.encode(strs);
 * Machine 1 ---msg---> Machine 2
 *
 * 2 号机：
 * Codec decoder = new Codec();
 * String[] strs = decoder.decode(msg);
 * 示例 2：
 *
 * 输入：dummy_input = [""]
 * 输出：[""]
 *
 */
//todo: 本解法是有字符集限制的解法，题给的字符串必须是ASCII字符集字符组成，这个解法就是以
// Character.toString((char) 257) 作为分隔符来搞
public class Quiz271_编码解码字符串 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("PJ|");
        list.add("Q.lF2 awD");
        Quiz271_编码解码字符串 quiz271NeedReview = new Quiz271_编码解码字符串();
        String str = quiz271NeedReview.encode(list);
        List<String> res = quiz271NeedReview.decode(str);
        System.out.println(res);
    }

    public String encode(List<String> strs) {
        if (strs.isEmpty()) {
            return Character.toString((char) 258);
        }

        String d = Character.toString((char) 257);
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
            sb.append(d);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public List<String> decode(String s) {
        String d = Character.toString((char) 258);
        if (s.equals(d)) return new ArrayList();

        d = Character.toString((char) 257);
        return Arrays.asList(s.split(d, -1));
    }


}
