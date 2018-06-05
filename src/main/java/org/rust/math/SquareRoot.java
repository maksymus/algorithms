package org.rust.math;

/**
 * Given a double number, write a function to calculate its square root.
 */
public class SquareRoot {
	public static void main(String[] args) {
		System.out.println(sqrt(9));
	}

	public static double sqrt(double x) {
		double estimate = x / 2.;

		while (Math.abs(x - estimate * estimate) > 0.00000001) {
			estimate = (estimate + x / estimate) / 2.0;
		}

		return estimate;
	}
}