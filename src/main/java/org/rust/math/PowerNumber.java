package org.rust.math;

/**
 * Given a double 'x' and an integer 'n'. Write a function to calculate 'x' raised to the power 'n'.
 */
public class PowerNumber {
	public static void main(String[] args) {
		System.out.println(pow(3, 1));
		System.out.println(pow(3, 2));
		System.out.println(pow(3, 3));
		System.out.println(pow(3, 4));
		System.out.println(pow(3, 5));
	}

	public static double pow(double x, int n) {
		double result = 1;

		while (n > 0) {
			if (n % 2 == 1) {
				result = result * x;
				n = n - 1;
			}

			x = x * x;
			n = n >> 1;
		}

		return result;
	}
}
