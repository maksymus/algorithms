package org.cracking.list;

import java.util.Objects;

/**
 * Palindrome: Implement a function to check if a linked list is a palindrome.
 * Palindrome: 1 2 3 2 1
 */
public class Palindrome {
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();

        System.out.println("Is palindrome: " + palindrome.isPalindrome(Node.build(1, 2, 3, 2, 1)));
        System.out.println("Is palindrome: " + palindrome.isPalindrome(Node.build(1, 2, 3, 2, 2)));
    }

    public boolean isPalindrome(Node root) {
        Node node = root;
        Node copy = null;

        while (node != null) {
            Node tmp = new Node(node.data);
            tmp.next = copy;
            copy = tmp;

            node = node.next;
        }

        node = root;
        while (node != null) {
            if (!Objects.equals(node.data, copy.data))
                return false;

            node = node.next;
            copy = copy.next;
        }

        return true;
    }
}
