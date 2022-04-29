package indi.simon.learning.leetcode.gogo20220425;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz427 {

    public static void main(String[] args) {
        Quiz427 quiz427 = new Quiz427();
        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        Node res = quiz427.construct(grid);
        System.out.println(res);
    }


    public Node construct(int[][] grid) {
        return constructInternal(grid, 0, grid.length - 1, 0, grid[0].length - 1);
    }

    public Node constructInternal(int[][] grid, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if (rowStart == rowEnd && columnStart == columnEnd) {
            Node leaf = new Node();
            leaf.isLeaf = true;
            leaf.val = grid[rowStart][columnStart] == 1;
            return leaf;
        }

        Node node = new Node();
        Node topLeft = constructInternal(grid, rowStart, (rowStart + rowEnd) / 2, columnStart, (columnStart + columnEnd) / 2);
        Node topRight = constructInternal(grid, rowStart, (rowStart + rowEnd) / 2, (columnStart + columnEnd) / 2 + 1, columnEnd);
        Node bottomLeft = constructInternal(grid, (rowStart + rowEnd) / 2 + 1, rowEnd, columnStart, (columnStart + columnEnd) / 2);
        Node bottomRight = constructInternal(grid, (rowStart + rowEnd) / 2 + 1, rowEnd, (columnStart + columnEnd) / 2 + 1, columnEnd);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
                topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            node.isLeaf = true;
            node.val = topLeft.val;
        } else {
            node.isLeaf = false;
            node.val = false;
            node.topLeft = topLeft;
            node.topRight = topRight;
            node.bottomLeft = bottomLeft;
            node.bottomRight = bottomRight;
        }

        return node;
    }


    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

}
