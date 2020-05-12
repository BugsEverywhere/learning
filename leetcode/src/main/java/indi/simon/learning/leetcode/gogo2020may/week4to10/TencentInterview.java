package indi.simon.learning.leetcode.gogo2020may.week4to10;

public class TencentInterview {

    public static void main(String[] args) {

        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);


        one.next = two;
        two.next = three;
        three.next = one
        ;

        //Node result = reverse(one);

        boolean result = isCycle(one);

        System.out.println(result);

    }

    private static Node reverse(Node node) {
        if (node == null) {
            return null;
        }

        Node tmp = null;
        Node last = null;
        while (node != null) {
            tmp = node.next;
            node.next = last;
            last = node;
            node = tmp;
        }

        return last;
    }

    private static boolean isCycle(Node node) {
        Node start = node;
        Node last = null;
        while (node != null) {
            if(node == start ){
                return true;
            }

            if (last == null) {
                last = node;
                node = node.next;
                continue;
            }

            Node node1 = start;
            Node node1Last = null;
            while (node1 != null) {
                if (node1 == node && node1Last != last) {
                    return true;
                }
                node1Last = node1;
                node1 = node1.next;
            }

            last = node;
            node = node.next;
        }

        return false;
    }


    static class Node {

        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

}
