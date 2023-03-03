package indi.simon.learning.leetcode.gogo20230227;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1487 {

    public static void main(String[] args) {
        Quiz1487 quiz1487 = new Quiz1487();
        String[] res = quiz1487.getFolderNames(new String[]{"kaido", "kaido(1)", "kaido", "kaido(1)", "kaido(2)"});
        System.out.println(res);
    }

//    private Pattern pattern = Pattern.compile(".*\\(\\d+\\)$");


    public String[] getFolderNames(String[] names) {
        String[] res = new String[names.length];
        Map<String, Integer> register = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            if (register.containsKey(names[i])) {
                //当前序号
                int suffix = register.get(names[i]);
                res[i] = names[i] + "(" + suffix + ")";
                while (register.containsKey(res[i])) {
                    ++suffix;
                    res[i] = names[i] + "(" + suffix + ")";
                }
                //更新当前序号
                register.put(names[i], suffix);
                register.put(res[i], 1);
            } else {
                res[i] = names[i];
                register.put(names[i], 1);
            }
        }

        return res;
    }


}
