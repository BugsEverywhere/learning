package indi.simon.learning.leetcode.gogo20240108;

/**
 * Created by Chen Zhuo on 2024/1/13.
 */
public class Quiz2061 {

    public static void main(String[] args) {
        int[][] room = new int[][]{
                {0, 0, 0, 1},
                {0, 1, 0, 1},
                {1, 0, 0, 0}};
        Quiz2061 quiz2061 = new Quiz2061();
        int res = quiz2061.numberOfCleanRooms(room);
        System.out.println(res);
    }

    //用于记录机器人在room中每一个位置出现过的状态的数组，
    // status[i][j]==1表示来过此处并且方向向↑
    // status[i][j]==2表示来过此处并且方向向→
    // status[i][j]==3表示来过此处并且方向向↓
    // status[i][j]==4表示来过此处并且方向向←
    private int[][] status;

    public int numberOfCleanRooms(int[][] room) {
        status = new int[room.length][room[0].length];
        dfs(room, 0, 0, 2);
        //数一下房间内等于2的块
        int cnt = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[i][j] == 2) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private void dfs(int[][] room, int i, int j, int dir) {
        if (status[i][j] == dir) {
            return;
        }
        //将房间标记为已清理
        room[i][j] = 2;
        status[i][j] = dir;
        //继续走
        if (dir == 1) {
            if (i - 1 >= 0 && room[i - 1][j] != 1) {
                dfs(room, i - 1, j, dir);
            } else {
                //转向
                if (j + 1 < room[0].length && room[i][j + 1] != 1) {
                    dfs(room, i, j + 1, 2);
                } else if (i + 1 < room.length && room[i + 1][j] != 1) {
                    dfs(room, i + 1, j, 3);
                } else if (j - 1 >= 0 && room[i][j - 1] != 1) {
                    dfs(room, i, j - 1, 4);
                }
            }
        } else if (dir == 2) {
            if (j + 1 < room[0].length && room[i][j + 1] != 1) {
                dfs(room, i, j + 1, dir);
            } else {
                //转向
                if (i + 1 < room.length && room[i + 1][j] != 1) {
                    dfs(room, i + 1, j, 3);
                } else if (j - 1 >= 0 && room[i][j - 1] != 1) {
                    dfs(room, i, j - 1, 4);
                } else if (i - 1 >= 0 && room[i - 1][j] != 1) {
                    dfs(room, i - 1, j, 1);
                }
            }
        } else if (dir == 3) {
            if (i + 1 < room.length && room[i + 1][j] != 1) {
                dfs(room, i + 1, j, dir);
            } else {
                //转向
                if (j - 1 >= 0 && room[i][j - 1] != 1) {
                    dfs(room, i, j - 1, 4);
                } else if (i - 1 >= 0 && room[i - 1][j] != 1) {
                    dfs(room, i - 1, j, 1);
                } else if (j + 1 < room[0].length && room[i][j + 1] != 1) {
                    dfs(room, i, j + 1, 2);
                }
            }
        } else if (dir == 4) {
            if (j - 1 >= 0 && room[i][j - 1] != 1) {
                dfs(room, i, j - 1, dir);
            } else {
                //转向
                if (i - 1 >= 0 && room[i - 1][j] != 1) {
                    dfs(room, i - 1, j, 1);
                } else if (j + 1 < room[0].length && room[i][j + 1] != 1) {
                    dfs(room, i, j + 1, 2);
                } else if (i + 1 < room.length && room[i + 1][j] != 1) {
                    dfs(room, i + 1, j, 3);
                }
            }
        }
    }

}
