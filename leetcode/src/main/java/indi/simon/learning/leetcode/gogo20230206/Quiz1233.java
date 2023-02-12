package indi.simon.learning.leetcode.gogo20230206;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1233 {

    public static void main(String[] args) {
        Quiz1233 quiz1233 = new Quiz1233();
        List<String> res = quiz1233.removeSubfolders(new String[]{"/a/b/c", "/a/b/ca", "/a/b/d"});
        System.out.println(res);
    }

    public List<String> removeSubfolders(String[] folder) {
        Map<String, TrieNode> map = new HashMap<>();

        for (String s : folder) {
            fillTree(map, s);
        }

        for (String s : folder) {
            cutTree(map, s);
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, TrieNode> nodeEntry : map.entrySet()) {
            traversTree(nodeEntry.getValue(), new StringBuilder(), res);
        }
        return res;
    }

    private void fillTree(Map<String, TrieNode> map, String singlePath) {
        String[] levels = singlePath.split("/");
        TrieNode curr;
        for (int i = 1; i < levels.length; i++) {
            if (map.containsKey(levels[i])) {
                curr = map.get(levels[i]);
            } else {
                curr = new TrieNode(levels[i]);
                map.put(levels[i], curr);
            }

            map = curr.children;
        }
    }

    private void cutTree(Map<String, TrieNode> map, String singlePath) {
        String[] levels = singlePath.split("/");
        TrieNode curr = null;

        for (int i = 1; i < levels.length; i++) {
            if (map.size() == 0) {
                break;
            }
            curr = map.get(levels[i]);
            map = curr.children;
        }
        curr.children = new HashMap<>();
    }


    private void traversTree(TrieNode node, StringBuilder str, List<String> res) {
        str.append("/");
        str.append(node.data);

        if (node.children.size() > 0) {
            for (Map.Entry<String, TrieNode> singleEntry : node.children.entrySet()) {
                traversTree(singleEntry.getValue(), new StringBuilder(str), res);
            }
        } else {
            res.add(str.toString());
        }

    }

    public class TrieNode {
        public String data;
        public Map<String, TrieNode> children = new HashMap<>();

        public TrieNode(String data) {
            this.data = data;
        }
    }


}
