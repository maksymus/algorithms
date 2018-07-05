package org.rust.string;

/**
 * Given a sentence, reverse the order of words.
 */
public class ReverseOrder {
	public static void main(String[] args) {
//		reverse("test hello world");
		reverse1("test hello world");
	}

	public static void reverse(String sentence) {
		for (int start = sentence.length() - 1, end = sentence.length(); start >= 0; start--) {
			if (sentence.charAt(start) == ' ') {
				String substring = sentence.substring(start + 1, end);
				end = start;

				System.out.println(substring);
			} else  if (start == 0) {
				String substring = sentence.substring(start, end);
				System.out.println(substring);
			}
		}
	}

	public static void reverse1(String sentence) {
		char[] chars = sentence.toCharArray();

		for (int end = 0, start = 0; end < chars.length; end++) {
			if (sentence.charAt(end) == ' ') {
				reverse(chars, start, end - 1);
				start = end + 1;
			} else  if (end == chars.length - 1) {
				reverse(chars, start, end);
			}
		}

		reverse(chars, 0, chars.length - 1);

		System.out.println(String.valueOf(chars));
	}

	private static void reverse(char[] chars, int start, int end) {
		while (start < end){
			char tmp = chars[start];
			chars[start] = chars[end];
			chars[end] = tmp;
			start++; end--;
		}
	}
}
