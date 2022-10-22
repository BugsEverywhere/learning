package indi.simon.learning.leetcode.gogo20221010;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz886_reviewed {

    public static void main(String[] args) {
        Quiz886_reviewed quiz886TimeExceed = new Quiz886_reviewed();
        boolean res = quiz886TimeExceed.possibleBipartitionOfficialDfs(9, new int[][]{{1, 2}, {3, 4}, {5, 6}, {6, 7}, {8, 9}, {7, 8}});
        System.out.println(res);
    }


    //todo: 官方染色dfs法，遍历每一个人，然后对他所不喜欢的人进行dfs，相当于挖出一条关系链，比如假设当前编号的人是i，
    // i讨厌j，j讨厌k，k讨厌m，m讨厌i，....这条关系链是否有冲突，与最开始的i放在哪一个阵营没有关系，dfs完之后没有问题，那么这条关系链就是ok的，
    // 关系链上的其他所有人都不用看了，都可以跳过，然后考察下一个关系链
    public boolean possibleBipartitionOfficialDfs(int n, int[][] dislikes) {
        //用于记录每个人在当前所处的阵营，0代表还未被划分阵营，1代表在第一阵营，2代表在第二阵营
        int[] color = new int[n + 1];

        //用于登记每一个人所讨厌的角色的登记本
        List<Integer>[] dislikeRegisterBook = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            dislikeRegisterBook[i] = new ArrayList<>();
        }
        //给登记本中每个人的那一页填充一下，注意这里的不喜欢是互相的，如果a不喜欢b，那么在a和b的登记簿上都应该记上对方
        for (int[] p : dislikes) {
            dislikeRegisterBook[p[0]].add(p[1]);
            dislikeRegisterBook[p[1]].add(p[0]);
        }

        //遍历每一个人，如果他已经在前面人的dfs中被划分到了某个阵营，那么就跳过，
        // 如果还未划分阵营，则将其分入第一阵营（或者第2阵营，无所谓其实，因为最终因他而起的冲突跟最开始他放在哪一个阵营没关系），然后dfs合法性
        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0 && !dfs(i, 1, color, dislikeRegisterBook)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param curnode
     * @param nowcolor
     * @param color
     * @param dislikeRegisterBook
     * @return
     */
    public boolean dfs(int curnode, int nowcolor, int[] color, List<Integer>[] dislikeRegisterBook) {
        //当前编号的人划分到第nowcolor阵营
        color[curnode] = nowcolor;
        //遍历他所不喜欢的人
        for (int nextnode : dislikeRegisterBook[curnode]) {
            //如果该不喜欢的人已经被划分阵营，且跟他自己在同一个阵营，不合法，返回false
            if (color[nextnode] != 0 && color[nextnode] == color[curnode]) {
                return false;
            }
            //如果该不喜欢的人没有被划分阵营，将他划分到另一个阵营，与3的异或运算就是取另一个阵营
            if (color[nextnode] == 0 && !dfs(nextnode, 3 ^ nowcolor, color, dislikeRegisterBook)) {
                return false;
            }
        }
        return true;
    }


    //todo: 官方染色bfs法，与dfs同样的思路，也就是依次处理关系链
    public boolean possibleBipartitionOfficialBfs(int n, int[][] dislikes) {
        int[] color = new int[n + 1];
        List<Integer>[] dislikeRegisterBook = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            dislikeRegisterBook[i] = new ArrayList<>();
        }
        for (int[] p : dislikes) {
            dislikeRegisterBook[p[0]].add(p[1]);
            dislikeRegisterBook[p[1]].add(p[0]);
        }
        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0) {
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                color[i] = 1;
                //只要这条关系链没遍历完，那么queue就不会为空，while循环就一直继续
                while (!queue.isEmpty()) {
                    int currNode = queue.poll();
                    for (int next : dislikeRegisterBook[currNode]) {
                        if (color[next] > 0 && color[next] == color[currNode]) {
                            return false;
                        }
                        if (color[next] == 0) {
                            color[next] = 3 ^ color[currNode];
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return true;
    }


    //todo: 超时，自己的傻逼做法，人为去创造多分支的遍历，没有将不同关系链切割为岛屿来处理
    public boolean possibleBipartition(int n, int[][] dislikes) {
        return possibleBipartitionInternal(dislikes, 0, new HashSet<>(), new HashSet<>());
    }

    private boolean possibleBipartitionInternal(int[][] dislikes, int i, Set<Integer> set1, Set<Integer> set2) {
        if (i >= dislikes.length) {
            return true;
        }

        int[] singlePair = dislikes[i];

        boolean res;

        if (set1.contains(singlePair[0])) {
            //第一个集合有singlePair[0]
            if (set1.contains(singlePair[1])) {
                //第一个集合也有singlePair[1]，直接返回
                return false;
            } else {
                //第一个集合没有singlePair[1]，将其加入第二个集合，继续递归
                if (set2.contains(singlePair[1])) {
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                } else {
                    set2.add(singlePair[1]);
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                    set2.remove(singlePair[1]);
                }
            }
        } else if (set2.contains(singlePair[0])) {
            //第二个集合有singlePair[0]
            if (set2.contains(singlePair[1])) {
                //第二个集合也有singlePair[1]
                return false;
            } else {
                //第二个集合没有singlePair[1]，将其加入第一个集合，继续递归
                if (set1.contains(singlePair[1])) {
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                } else {
                    set1.add(singlePair[1]);
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                    set1.remove(singlePair[1]);
                }
            }
        } else {
            //两个集合都没有singlePair[0]
            if (set1.contains(singlePair[1])) {
                if (set2.contains(singlePair[0])) {
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                } else {
                    set2.add(singlePair[0]);
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                    set2.remove(singlePair[0]);
                }
            } else if (set2.contains(singlePair[1])) {
                if (set1.contains(singlePair[0])) {
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                } else {
                    set1.add(singlePair[0]);
                    res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                    set1.remove(singlePair[0]);
                }
            } else {
                //两个集合也都没有singlePair[1]
                //先递归一种情况
                set1.add(singlePair[0]);
                set2.add(singlePair[1]);
                res = possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                set1.remove(singlePair[0]);
                set2.remove(singlePair[1]);
                //再递归另一种
                set2.add(singlePair[0]);
                set1.add(singlePair[1]);
                res = res || possibleBipartitionInternal(dislikes, i + 1, set1, set2);
                set2.remove(singlePair[0]);
                set1.remove(singlePair[1]);
            }
        }
        return res;
    }

}
