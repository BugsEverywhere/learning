package indi.simon.learning.leetcode.gogo20221003;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz811 {

    public static void main(String[] args) {
        Quiz811 quiz811 = new Quiz811();
        List<String> res = quiz811.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});
        System.out.println(res);
    }

    public List<String> subdomainVisits(String[] cpdomains) {

        Map<String, Integer> domainCountMap = new HashMap<>();

        for (String singleDomainCount : cpdomains) {
            String[] arr = singleDomainCount.split(" ");
            String singleDomain = arr[1];
            Integer originCount = Integer.valueOf(arr[0]);
            while (true) {
                if (domainCountMap.containsKey(singleDomain)) {
                    domainCountMap.put(singleDomain, domainCountMap.get(singleDomain) + originCount);
                } else {
                    domainCountMap.put(singleDomain, originCount);
                }
                //判断余下的域名是否还有点
                if (singleDomain.contains(".")) {
                    singleDomain = singleDomain.substring(singleDomain.indexOf('.') + 1);
                } else {
                    break;
                }
            }
        }

        List<String> res = new ArrayList<>();

        for (Map.Entry<String, Integer> singleEntry : domainCountMap.entrySet()) {
            res.add(singleEntry.getValue() + " " + singleEntry.getKey());
        }

        return res;
    }


}
