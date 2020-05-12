package indi.simon.learning.leetcode.may2020.week11to17;

public class P155 {

    private static class MinStack {

        private Node minNode;
        private Node head;

        /** initialize your data structure here. */
        public MinStack() {
            minNode = null;
            head = null;
        }

        public void push(int x) {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
            if(minNode==null||x <= minNode.val){
                Node newMinNode = new Node(x);
                newMinNode.next = minNode;
                minNode = newMinNode;
            }
        }

        public void pop() {
            if(head == null){
                return;
            }
            if(head.val==minNode.val){
                Node tmp = minNode.next;
                minNode.next = null;
                minNode = tmp;
            }
            Node tmp = head.next;
            head.next = null;
            head = tmp;
        }

        public int top() {
            if(head == null){
                return -1;
            }
            return head.val;
        }

        public int getMin() {
            return minNode.val;
        }

    }


    private static class Node{
        public int val;
        public Node next;

        Node(int val){
            this.val = val;
        }
    }
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

}
