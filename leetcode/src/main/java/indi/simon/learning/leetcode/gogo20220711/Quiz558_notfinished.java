package indi.simon.learning.leetcode.gogo20220711;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 自己的解法跑用例失败了，但是构造用例太复杂了，暂时不去探究原因，这里是官方解法，背下来先
public class Quiz558_notfinished {

    public static void main(String[] args) {
        Node quadTree1TopLeft = new Node(true, true, null, null, null, null);
        Node quadTree1TopRight = new Node(false, true, null, null, null, null);
        Node quadTree1BottomLeft = new Node(true, true, null, null, null, null);
        Node quadTree1BottomRight = new Node(true, true, null, null, null, null);
        Node quadTree1 = new Node(false, false, quadTree1TopLeft, quadTree1TopRight, quadTree1BottomLeft, quadTree1BottomRight);

        Node quadTree2TopLeft = new Node(true, true, null, null, null, null);
        Node quadTree2Level2TopLeft = new Node(true, true, null, null, null, null);
        Node quadTree2Level2TopRight = new Node(false, true, null, null, null, null);
        Node quadTree2Level2BottomLeft = new Node(false, true, null, null, null, null);
        Node quadTree2Level2BottomRight = new Node(true, true, null, null, null, null);
        Node quadTree2TopRight = new Node(true, false, quadTree2Level2TopLeft, quadTree2Level2TopRight, quadTree2Level2BottomLeft, quadTree2Level2BottomRight);
        Node quadTree2BottomLeft = new Node(true, true, null, null, null, null);
        Node quadTree2BottomRight = new Node(true, true, null, null, null, null);
        Node quadTree2 = new Node(false, false, quadTree2TopLeft, quadTree2TopRight, quadTree2BottomLeft, quadTree2BottomRight);

        Quiz558_notfinished quiz558Notfinished = new Quiz558_notfinished();
        Node res = quiz558Notfinished.intersect(quadTree1, quadTree2);
        System.out.println(res);

    }

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf) {
            //quadTree1是叶子节点
            if (quadTree1.val) {
                //quadTree1是true
                return new Node(true, true);
            } else {
                //quadTree1不是true
                return new Node(quadTree2.val, quadTree2.isLeaf, quadTree2.topLeft, quadTree2.topRight, quadTree2.bottomLeft, quadTree2.bottomRight);
            }
        } else if (quadTree2.isLeaf) {
            //quadTree2是叶子节点，反过来递归一下即可
            return intersect(quadTree2, quadTree1);
        } else {
            //两者都不是叶子节点，需要递归每一块
            Node o1 = intersect(quadTree1.topLeft, quadTree2.topLeft);
            Node o2 = intersect(quadTree1.topRight, quadTree2.topRight);
            Node o3 = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
            Node o4 = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
            if (o1.isLeaf && o2.isLeaf && o3.isLeaf && o4.isLeaf && o1.val == o2.val && o1.val == o3.val && o1.val == o4.val) {
                return new Node(o1.val, true);
            } else {
                return new Node(false, false, o1, o2, o3, o4);
            }
        }
    }


    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
        }

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }
}
