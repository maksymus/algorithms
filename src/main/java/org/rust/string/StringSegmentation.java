package org.rust.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a dictionary of words and an input string tell whether the input string can be completely segmented into dictionary words.
 */
public class StringSegmentation {
	private static final List<String> dictionary = Arrays.asList("word", "test", "world", "hello");

	public static void main(String[] args) {
		check("hello world INVALID");
	}

	public static void check(String input) {
		Trie trie = new Trie();

		dictionary.forEach(word -> trie.add(word.toLowerCase()));

		String[] split = input.split("\\s+");
		for (int i = 0; i < split.length; i++) {
			if (!trie.contains(split[i].toLowerCase())) {
				System.out.println(String.format("Word '%s' is not in dictionary", split[i]));
			}
		}
	}

	private static class Trie {
		private Node root = new Node();

		public void add(String word) {
			root.add(word, 0);
		}

		public boolean contains(String word) {
			return root.contains(word, 0);
		}

		private static class Node {
			private Map<Character, Node> map = new HashMap<>();

			private void add(String word, int index) {
				if (index >= word.length())
					return;

				char ch = word.charAt(index);

				if (!map.containsKey(ch)) {
					map.put(ch, new Node());
				}

				map.get(ch).add(word, index + 1);
			}

			private boolean contains(String word, int index) {
				if (index >= word.length())
					return true;

				char ch = word.charAt(index);

				if (!map.containsKey(ch))
					return false;

				return map.get(ch).contains(word, index + 1);
			}
		}
	}
}

