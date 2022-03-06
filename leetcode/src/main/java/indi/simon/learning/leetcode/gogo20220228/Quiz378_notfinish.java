package indi.simon.learning.leetcode.gogo20220228;


/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz378_notfinish {

    public static void main(String[] args) {
        Quiz378_notfinish quiz378 = new Quiz378_notfinish();
        int[][] arr = new int[][]{{1, 3, 5}, {6, 7, 12}, {11, 14, 14}};
        int res = quiz378.kthSmallest(arr, 3);
        System.out.println(res);
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

    //todo: 一种变化了的二分，该种二分最终一定是right边界先到达第k大的数（矩阵中一定会有这个数），然后左边界再一步步逼近right，最终返回左边界值还是右边界值其实都一样，因为循环终止条件是right==left
    // 再说说为什么要这样做，首先我们要找的数是矩阵中第k大的数，而且矩阵中一定有这么一个第k大的数，所以反过来说，如果找到一个这么一个数x，矩阵中小于他的数个数是i，等于他的数个数是j，
    // i+j==k并且j!=0那么这个x一定属于这个矩阵。
    // 然后check方法最终就能帮我们定位到这么一个数y，他能保证i+j==k，但是不能保证j!=0（当然如果y就是x那就是最好情况了），也就是y很有可能比x稍大（如果y>x，最终状态肯定是y==x+1），而且y不在矩阵当中。
    // 注意此时right肯定会跳到y然后躺在这儿等着，就看最后一步怎么走了，此时right==y==x+1，当left逼近到x-1时，mid==x，下一步right跳到x，所以你看，right总会先到x，然后在这儿躺着等left。
    // 此时left再往前一步，left==right，循环终止，返回left或者right都行，因为他们都是x了。
    // 观察到，当x在本次mid的左边（或者x就是本次mid）时，右边界right在下一循环会跳到mid，此时如果mid就是x那当然好，但是很有可能mid还不是x，甚至很有可能right都不是矩阵中的数；
    // 反之，如果x在本次mid的右边，那么说明上一次right跳太狠了，跳过了，让温和一点的left拱一步。左边界每次循环只会前进1，这样是为了保证在我们还没找到这么个数x的时候，我们不会错过矩阵中的数，
    // 如果每次迭代让left也贸然跳到mid，很有可能这个mid也不是矩阵中的数，那这下完球了，本来right就不一定是矩阵中的数了，left也不是的话，就完全跟矩阵没关系了。所以left只能一步步小心翼翼的前进，
    // 这样保证每一次x在mid右边的时候，在下一循环时，mid也只加1或者干脆就没动，这样总有一次mid能对上x。
    // 所以最终当我们有一天找到这个mid，下一步也是right跳到这个mid，然后躺在这等着，不动了，等着left一步步走过来。
    // so, 这样的二分总结起来其实只有一半是二分，另一半是一步步在缩小窗口，原因是怕错过目标集合（本题是矩阵）中的数，实属无奈之举
    // right每次跳mid是为了保证算法的logN复杂度，如果要对称好理解一点，那就right每次也只是减一，那这样算法复杂度就上去了


}
