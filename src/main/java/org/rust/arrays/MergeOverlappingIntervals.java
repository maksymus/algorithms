package org.rust.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of time intervals in any order, merge all overlapping intervals into one and output the result which
 * should have only mutually exclusive intervals. Let the intervals be represented as pairs of integers for simplicity.
 * For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }.
 * The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}.
 * Similarly {5, 7} and {6, 8} should be merged and become {5, 8}
 */
public class MergeOverlappingIntervals {
	public static void main(String[] args) {
		List<Interval> merged = merge(Arrays
				.asList(new Interval(5, 7), new Interval(2, 4), new Interval(6, 8), new Interval(1, 3)));
		System.out.println(merged);
	}

	public static List<Interval> merge(List<Interval> intervals) {
		Collections.sort(intervals, (i1, i2) -> {
			if (i1.from < i2.from)
				return -1;

			if (i1.from == i2.from)
				return Integer.compare(i1.to, i2.to);

			return 1;
		});

		Interval current = null;
		for (Interval interval : intervals) {
			if (current == null) {
				current = interval;
			}

			if (current.to >= interval.from) {
				current.to = interval.to;
			}

			// TODO finish me
		}

		return intervals;
	}

	private static class Interval {
		private int from;
		private int to;

		public Interval(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public String toString() {
			return "{" + from + "," + to + '}';
		}
	}
}

/**
 	---------------
 		-------------
      -----------------
 */