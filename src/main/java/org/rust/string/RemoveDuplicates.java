package org.rust.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Remove duplicate characters from a string.
 */
public class RemoveDuplicates {
	public static void main(String[] args) {
		System.out.println(remove("geeks for geeks"));
	}

	public static String remove(String str) {
		char[] chars = str.toCharArray();

		Set<Object> set = new HashSet<>();
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {
			if (!set.contains(chars[i])) {
				result.append(chars[i]);
				set.add(chars[i]);
			}
		}

		return result.toString();
	}
}
