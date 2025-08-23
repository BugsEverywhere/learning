package indi.simon.learning.复习.逻辑推理;

/**
 * 假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。
 * <p>
 * 现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。
 * <p>
 * 给定整数 n 和一个辅助函数 bool knows(a, b) 用来获取 a 是否认识 b。实现一个函数 int findCelebrity(n)。派对最多只会有一个 “名人” 参加。
 * <p>
 * 若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。
 * <p>
 * 注意 n x n 的二维数组 graph 给定的输入并不是 直接 提供给你的，而是 只能 通过辅助函数 knows 获取。graph[i][j] == 1 表示 i 认识 j，而 graph[i][j] == 0 表示 j 不认识 i。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * 输出: 1
 * 解释: 有编号分别为 0、1 和 2 的三个人。graph[i][j] = 1 代表编号为 i 的人认识编号为 j 的人，而 graph[i][j] = 0 则代表编号为 i 的人不认识编号为 j 的人。“名人” 是编号 1 的人，因为 0 和 2 均认识他/她，但 1 不认识任何人。
 * 示例 2:
 * <p>
 * <p>
 * 输入: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * 输出: -1
 * 解释: 没有 “名人”
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == graph.length == graph[i].length
 * 2 <= n <= 100
 * graph[i][j] 是 0 或 1
 * graph[i][i] == 1
 */
public class Quiz277_搜寻名人 {

    public static void main(String[] args) {

    }

    private int numberOfPeople;

    public int findCelebrity(int n) {
        numberOfPeople = n;

        //初始化名人候选人为0号
        int possibleCelebrity = 0;
        //遍历所有的人，因为候选人不认识任何人，所以如果候选人认识当前人，那么就把候选人更新为当前人
        for (int i = 0; i < n; i++) {
            if (knows(possibleCelebrity, i)) {
                possibleCelebrity = i;
            }
        }
        //结束时再判断一下候选人是否为真的名人，如果不是，那么肯定就没有名人了
        if (isCelebrity(possibleCelebrity)) {
            return possibleCelebrity;
        }
        return -1;
    }

    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) {
                continue;
            }
            // i必须不认j，并且j必须认识i，否则返回false
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean knows(int i, int j) {
        return false;
    }

}
