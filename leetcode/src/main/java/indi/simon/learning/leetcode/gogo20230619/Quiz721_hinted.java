package indi.simon.learning.leetcode.gogo20230619;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 需要注意的点很多，首先，List和Set的特性生疏了，List.add是在列表尾部add，Set没有顺序性，因此使用Set去重可以，但是简单的塞进去去重一把，就失去了原有List中的顺序性了。
// 然后，在List遍历时删除元素的技巧需要掌握。再然后ArrayList.subList返回的是基于父亲列表的一个子列表，只不过把下标什么的调整一下而已，对齐进行排序操作，同样会影响到父亲列表。
public class Quiz721_hinted {

    public static void main(String[] args) {
        List<String> account1 = new ArrayList<>();
        account1.add("John");
        account1.add("johnsmith@mail.com");
        account1.add("johnsmith@mail.com");
        account1.add("johnsmith@mail.com");
        account1.add("john_newyork@mail.com");

        List<String> account2 = new ArrayList<>();
        account2.add("John");
        account2.add("johnsmith@mail.com");
        account2.add("john00@mail.com");

        List<String> account3 = new ArrayList<>();
        account3.add("Mary");
        account3.add("mary@mail.com");

        List<String> account4 = new ArrayList<>();
        account4.add("John");
        account4.add("johnnybravo@mail.com");

//        List<String> account5 = new ArrayList<>();
//        account5.add("David");
//        account5.add("David1@m.co");
//        account5.add("David2@m.co");


        List<List<String>> arrList = new ArrayList<>();
        arrList.add(account1);
        arrList.add(account2);
        arrList.add(account3);
        arrList.add(account4);
//        arrList.add(account5);

        Quiz721_hinted quiz721Hinted = new Quiz721_hinted();
        List<List<String>> res = quiz721Hinted.accountsMerge(arrList);
        System.out.println(res);
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            //账户内去重一把，用于记录account内部已经处理过的mail
            Set<String> mailSet = new HashSet<>();
            //检查每一个邮箱在其余账户中是否存在，存在则合并账户
            for (int m = 1; m < account.size(); m++) {
                String mail = account.get(m);
                if (mailSet.contains(mail)) {
                    //之前已经有过该mail，account内需要删掉他
                    account.remove(m);
                    m--;
                    continue;
                }
                mailSet.add(mail);
                //遍历其余账户，检查该mail
                for (int j = 0; j < accounts.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    List<String> anotherAccount = accounts.get(j);
                    if (anotherAccount.contains(mail)) {
                        //需要合并账户，将anotherAccount的邮箱并入account里面，然后删除anotherAccount
                        for (int n = 1; n < anotherAccount.size(); n++) {
                            if (!account.contains(anotherAccount.get(n))) {
                                account.add(anotherAccount.get(n));
                            }
                        }
                        accounts.remove(j);
                        j--;
                        if (j < i) {
                            i--;
                        }
                    }
                }
            }
            res.add(account);
        }

        for (List<String> account : res) {
            Collections.sort(account.subList(1, account.size()), String::compareTo);
        }

        return res;
    }

}
