package org.geeks;

/**
 * Add two numbers represented by linked lists
 * Given two numbers represented by two linked lists, write a function that returns sum list. 
 * The sum list is linked list representation of addition of two input numbers. 
 * It is not allowed to modify the lists. Also, not allowed to use explicit extra space (Hint: Use Recursion).
 * 
 * Input:
 *  First List: 5->6->3  // represents number 563
 *  Second List: 8->4->2 //  represents number 842
 * Output
 *  Resultant list: 1->4->0->5  // represents number 1405
 */
public class SumLists {
    private static class Node {
        private int digit;
        private Node next;

        public Node(int digit, Node next) {
            this.digit = digit;
            this.next = next;
        }

        public Node(int digit) {
            this.digit = digit;
        }
    }
    
    public Node sum(Node root1, Node root2) {
        int numNodes1 = count(root1);
        int numNodes2 = count(root2);
        
        if (numNodes1 < numNodes2) 
            root1 = addZeroNodes(root1, numNodes2 - numNodes1);
        else if (numNodes2 < numNodes1) 
            root2 = addZeroNodes(root2, numNodes1 - numNodes2);
        
        Node result = sumInternal(root1, root2);
        int overflow = handleOverflow(result);
        
        if (overflow > 0)
            result = new Node(overflow, result);
        
        return result;
    }
    
    private Node sumInternal(Node root1, Node root2) {
        if (root1.next == null && root2.next == null)
            return new Node(root1.digit + root2.digit);
        
        Node nextNode = sumInternal(root1.next, root2.next);
        int overflow = handleOverflow(nextNode);
        
        return new Node(root1.digit + root2.digit + overflow, nextNode);
    }

    private int handleOverflow(Node node) {
        int overflow = 0;
        
        if (node.digit > 9) {
            int digit = node.digit;
            node.digit = digit % 10;
            overflow = digit / 10;
        }
        
        return overflow;
    }
    
    private Node addZeroNodes(Node node, int num) {
        Node tmp = node;
        
        while (num-- > 0)
            tmp = new Node(0, tmp);
        
        return tmp;
    }
    
    private int count(Node node) {
        if (node == null)
            return 0;
        
        int count = 1;
        
        Node tmp = node;
        while (tmp.next != null) {
            tmp = tmp.next;
            count++;
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        Node node1 = new Node(5, new Node(6, new Node(3)));
        Node node2 = new Node(1, new Node(8, new Node(4, new Node(2))));
        
        Node sum = new SumLists().sum(node1, node2);
        
        Node tmp = sum;
        while (tmp != null) {
            System.out.printf("%d", tmp.digit);
            
            tmp = tmp.next;
            
            if (tmp != null)
                System.out.print("->");
        }
    }
}
