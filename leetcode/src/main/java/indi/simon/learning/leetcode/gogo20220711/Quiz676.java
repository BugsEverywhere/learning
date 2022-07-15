package indi.simon.learning.leetcode.gogo20220711;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 这一题有太多需要注意的地方了，详见下面
public class Quiz676 {

    public static void main(String[] args) {
        Quiz676 quiz676 = new Quiz676();
        quiz676.buildDict(new String[]{"azpslftgmqnbnqsjqhgsatcgazqjppixmjjjphdjnbwfdaxqnrw", "laiowmacuhfnhmlhsmnengwutjkvqjpxrgeopbrvhhsdgyzirsdftzrqbevup", "vkgi", "ayqpsnekvoejwvxatmgdddlzdznkfkyuyuipwbprqhanwqleuitgiigvgsgiipuvsqxtylaysezqgldrjosabuu", "dq", "jlndbuksixwvkcpdfyvtuhoskpbdbjjtbfxrnwmnuavjokezfnolwsuxcofugx", "qocgfmthfhqydkemjqgk", "rtbjlhpuajveurkucqfslypimzorxirqwzwoltfhvhtmptarzxxiweipacdfirmwmapxgxeqpcvwikpydebjipvb", "coewzurapczyocogyzypnkdbzavvnkgatlballccyledguukhs", "dmpxidzqbtdxvyreeowfshccmqiwhgzwiktecphuvhtmtplnyfxyjvuhaorenunhxiktsgtp", "tuhnukjoydirchmvfzjmcjysitzalapkugswzlupfawyvabxjpvlgvpirvomkurolrlnenhscferffttty", "ofbpgjaltkrelroyqozrzfltnxynclmvvsjikoemgsiwrnoiddkkyyorxbqrvowaixcma", "okznrgxsdsfll", "cwrhnakxdzjcrxvgxtahzgflxokodzwoxcjdpdkmwwwjzbtofvhphq", "jvebsmizgfjulylq", "xegcnzjnhqkwqpamuilubfhbhftwuxxyqxgravpcry", "jujimrbueeuzpdkym", "tgptndcg", "mlobmgfgauvrmvhogvudjjnnnzpajubzpgodekjwixrgzzgdpstmdplg", "x", "rjprciazfmjoeqhiuwvinlaxznykiuhpcceyrkpjmkumcujoljsrcxzqodqqawdvrzdurfijsqddekyl", "jwsotoivsxtjyevasbpptibmpskgqiiohwovxtysrdwpqqsrhrgxasfspjrwjqswjprznam", "uprwbpbyeobwngvnkkajhpfrigbtgxzvhjl", "wpkdnjhdfzvrmhcehzyzlpekvq", "jwdhwmozrjq", "vsyxiybtkftvrhrwaavvbolhxpubityuycmodh", "pzdaump", "fxggxctbzdtfgwvuradoijazilsyr", "ndpoauipnspshbdavkixkhvorekkemggegweuabxmsxhqhvgxbmggekzbcergabazrqlwkbnkupjyebarrrnotkgq", "xlmmubveoinkjmmkwvdgcfmegxnyvxat", "nhxeoympunllntxaoodehlbbyrfckhjrosoepheqykezaowikooabyuqyoowgvoxsoqcwknutjslpsvupfdpvntxvybxwvje", "dgduuznzzlshqscktj", "qzgqxioiwjyptwfoabniyuijlgwjrmwxhmqxjyxlhbjloeowndoojwlknpqcdonbndbmqzobbtqetaypnfjxvmeyvnclzjtyfl", "pibpmnbnwrnpmaqhwgkhuajmyytlgmrmiunhnfokamyyauaqlcjdjae", "uwlvfxhxqlnhgegzmohggribrrtostm", "gkwjneiif", "mkrippwsmgqwlqhhn", "juyypcdsvproodjkysmkdtnubsbrakabslmxjtinldmzr", "yqbicdgzcu", "rmbgyrqnujfmudiraqbrabriunxvljupofethbbefsehpio", "jkrnatgmjoqdhaeovjqhkysewnwvgntvbxcdiwxiszalzalykenicvhxburrwsgnlsargcczoqfgqycgyjg", "lssfjvccpvwepplrezcxxqzojcnvelsdkqnooijgbbenelyopmvokcdrxgqqzfjnnfiozfaeynumqcqxfwihzl", "aawjasokfqmdninyvohvjjgtagcfazxbgunlfzwwoanzginnaczxqvjluysylgxevuxkxgpjndcd", "gysjkpljmywnubcgxwoepzaplliijfambgrhullrnibcriqzvsupnpxdtxxqifczlqesgbrwuwuyomf", "loeocrvplvnsfbbrewiordaxpctivjjyqaadqbyymewytaytrixvpey", "svvmalqihxkovsogytheccxhcswcphqtbpnudujgcjmpbtcmihpkjeocdptacztwhdpmc", "ynjvhfvbeedghroxvsfgihsavoydvejylsxsgmuyain", "xxcerwceajmsshtfushlkjisv", "cbwqhvzxztmfoplharhlwqkcdeqmjjuchbqstxqwvjsjcuaeuoxycatmxqvplqgnbiaxwrfxrlogkseawmvgioehlnghrk", "ondxttmqnrihtqtcccsysfbpxoqrzptwzxddxywqkmjsonsjvbrzqmqen", "rskpoihepyiyhltwuanpzzjscnrijbgatwbzhddumnjwxffoxiehhjpt", "ythtnbgrtotgxqhpntxszunrtbbajxpsmifwienrnwbmramkoifpkjvcuysztlhyhoxbyqiqmswikwsqjnnpnybveif", "uapvxevzxcjrvwxirefhefjqbgbindnkohnvjmyrzlitzchqmalqxkcjvnxvcfnzgpsxwairylfuvjdtwrs", "lrrnzgahkijxhhdradnap", "nbjccbcjhootwmjjwszwih", "iuxupmowxfnqwqxntbqtticeocfbtiuujewglnmmrysyqvlzabnuptwhotghsolrghiaxuzscrro", "zgqamiijkqrxahrsblhdfkzqushwaldtenjq", "bjstlfmfldqtejarqyhrfkwgqqvmawjmutcpsvwvoftbnuxdgnhfhdmzystf", "ddhyfqpptflwibfyuwivszprlwgwkkimhjqhgviqwwekulelgjelsjmdpdwwpyyxeqxw", "nkbvbvgsdomirwkjbyrklcgulkhbutongivzwwyxbfnrobwzdxnvijtzhkvz", "ffkljiduldygsihezdizddzspuvnozxwudplocgpjhxlpoobjrwrgxgvxitsbkakkctscmoqspunvrgunylapvqnjai", "gum", "j", "kqtzstsnlnvrtnsrajivsipbrmrovjfktvvfvrnbctsbynckeluqqncdu", "vlmqltdb", "yicpvlsbdbyfveeujzfaaketfjdblglppjssbvqhmialzr", "qvbjvrizhyslhpvjaewmkkwtzxggagpnzwsnthynkrdrprbqapoyybqnpf"});
//        quiz676.buildDict(new String[]{"m"});
        boolean res = quiz676.search("m");
        System.out.println(res);
    }

    private Map<Character, TreeNode> rootNodeMap;

    public Quiz676() {
        rootNodeMap = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for (String singleStr : dictionary) {
            char firstC = singleStr.charAt(0);
            TreeNode rootOfThisC = rootNodeMap.get(firstC);
            if (rootOfThisC == null) {
                rootOfThisC = new TreeNode(singleStr.length() == 1, firstC, new HashMap<>());
                rootNodeMap.put(firstC, rootOfThisC);
            }
            //todo: 如果单词只有一个字符，需要注意给该字符节点置为结束节点
            if (singleStr.length() == 1) {
                rootOfThisC.setEnd(true);
            }
            buildInternal(singleStr, 1, rootOfThisC.getChildren());
        }
    }

    private void buildInternal(String str, int cIndex, Map<Character, TreeNode> brotherMap) {
        if (cIndex >= str.length()) {
            return;
        }
        char c = str.charAt(cIndex);
        if (!brotherMap.containsKey(c)) {
            //之前本层没有本字符
            brotherMap.put(c, new TreeNode(cIndex == str.length() - 1, c, new HashMap<>()));
        } else if (!brotherMap.get(c).isEnd()) {
            //之前就有本字符，且本字符不是结束节点，需要校验一下
            brotherMap.get(c).setEnd(cIndex == str.length() - 1);
        }
        buildInternal(str, cIndex + 1, brotherMap.get(c).getChildren());
    }

    public boolean search(String searchWord) {
        return searchInternal(searchWord, 0, new TreeNode(false, ' ', rootNodeMap), 0);
    }

    private boolean searchInternal(String searchWord, int index, TreeNode node, int changeCount) {
        if (changeCount > 1) {
            return false;
        }
        if (node.isEnd() && index == searchWord.length() && changeCount == 1) {
            return true;
        } else if (node.getChildren().size() == 0 || index == searchWord.length()) {
            return false;
        }

        //无论字典树本层包含不包含当前字符，都要递归下去
        boolean containRes = false;
        for (TreeNode singleChild : node.getChildren().values()) {
            containRes = containRes || searchInternal(searchWord, index + 1, singleChild, singleChild.getC() == searchWord.charAt(index) ? changeCount : changeCount + 1);
        }
        return containRes;
    }


    private class TreeNode {
        public TreeNode(boolean end, char c, Map<Character, TreeNode> children) {
            this.end = end;
            this.c = c;
            this.children = children;
        }

        private boolean end;
        private char c;
        private Map<Character, TreeNode> children;

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public char getC() {
            return c;
        }

        public void setC(char c) {
            this.c = c;
        }

        public Map<Character, TreeNode> getChildren() {
            return children;
        }

        public void setChildren(Map<Character, TreeNode> children) {
            this.children = children;
        }
    }
}
