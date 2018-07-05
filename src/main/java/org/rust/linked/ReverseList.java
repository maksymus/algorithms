package org.rust.linked;

/**
 * Given the pointer/reference to the head of a singly linked list, reverse it and return the pointer/reference to the
 * head of reversed linked list.
 */
public class ReverseList {
	public static void main(String[] args) {
		Node root = Node.build(1, 2, 3, 4, 5, 6);
		System.out.println(reverse(root));
	}

	public static Node reverse(Node root) {
		Node prev = null;
		Node curr = root;

		while (curr != null) {
			Node tmp = curr.next;
			curr.next = prev;

			prev = curr;
			curr = tmp;
		}

		return prev;
	}
}
