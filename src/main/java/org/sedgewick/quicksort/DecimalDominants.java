package org.sedgewick.quicksort;

import java.util.Arrays;

///**
// * Decimal dominants.
// * Given an array with N keys, design an algorithm to find all values that occur more than N/10 times.
// * The expected running time of your algorithm should be linear.
// *
// * Heavy Hitters
// * Another problem I want to mention is called Decimal Dominants, also known as “heavy hitters”.
// * In an unordered list, find all the elements that dominate, i, e, that occur > N / m percent, where N is the length of the
// * list and m = 10. If m = 2, then we are looking for the item that is a majority, if there is one. This problem (for any m)
// * can be solved by the Boyer-Moore Majority Voting algorithm (which is really cool). By only using m - 1 counters
// * (no matter how large N is), the algorithm generates candidates that could dominate. A second pass is needed per
// * candidate to count occurrences to determine if they in fact do dominate.
// *
// * BUT, a completely different algorithm can be used to solve this problem based on partitioning.
// * (quicksort partitions a list to get 1 item in place). Since you know that a dominant item is > N/10, you know that its
// * final position in a sorted list must be on some N/10 boundary. (But you don’t need to sort the list!) For example,
// * if 5 dominates a list of N = 100, then it must occur at least 11 times and a 5 must be in one of the
// * positions 9, 19, 29, …, 99. Use partitioning to find the items that sit on N/10 boundaries.
// * (This use of partitioning is called quickselect.)  The result from quickselect is the
// * lower and upper indices of the item so the difference between upper index and lower
// * index indicates whether the item dominates. In general, for any N and any m, the number of
// * boundary positions on which to call quickselect is at most m - 1.
// */
class DecimalDominants {
    private int[] arr;
    private int m;

    public DecimalDominants(int[] arr, int m) {
        this.m = m;
        this.arr = Arrays.copyOf(arr, arr.length);
    }

    private void quickselect() {

    }

    private void partition(int pos) {
        int pivot = arr[pos];

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            while (arr[left] < pivot)
                left++;
            while ((right >= left) && (arr[right] >= pivot))
                right--;
            if (right > left)
                swap(left, right);
        }
    }

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
//        int[] arr = IntStream.generate(() -> new Random().nextInt(10)).limit(20).toArray();
        int[] arr = new int[] { 1, 3, 9, 5, 1, 2, 7, 8, 2, 6 };
        DecimalDominants dd = new DecimalDominants(arr, 10);
        dd.partition(2);

        System.out.println(Arrays.toString(dd.arr));
    }
}


//class DecimalDominants {
//  class DecimalDominantsSolve[T <% Ordered[T]](arr: Array[T], m: Int) {
//    println(s"Searching for dominant appearing more than $m times in array of ${arr.length} elements")
//
//    for (i <- 1 to arr.length / m)
//      quickselect(i * m - 1)
//
//    def quickselect(n: Int) {
//      def swap(i: Int, j: Int) {
//        val tmp = arr(i)
//        arr(i) = arr(j)
//        arr(j) = tmp
//      }
//
//      def partition(i: Int, j: Int): Int = {
//        val pivot = arr(i)
//
//        var left = i
//        var right = j
//
//        while (left <= right) {
//          while (arr(left) < pivot)
//            left += 1;
//          while ((right >= left) && (arr(right) >= pivot))
//            right -= 1;
//
//          if (right > left)
//            swap(left, right);
//        }
//
//        left;
//      }
//
//      @tailrec
//      def quickselect(i: Int, j: Int): T = {
//        val pos = partition(i, j)
//
//        if (pos == n)
//          arr(pos)
//        else if (pos < n)
//          quickselect(pos + 1, j)
//        else
//          quickselect(i, pos)
//      }
//
//      quickselect(0, arr.length - 1)
//    }
//
//    def dominants: List[T] = {
//      if (arr.isEmpty)
//        List.empty
//      else {
//        var count = 0
//        var current: T = arr(0)
//
//        var domList = Set.empty[T]
//
//        for (i <- 0 until arr.length) {
//          if (arr(i) == current) {
//            count += 1;
//            if (count > m)
//              domList = domList + current
//          } else {
//            count = 0
//            current = arr(i)
//          }
//        }
//
//        domList.toList
//      }
//    }
//  }
//
//  def main(args: Array[String]) {
////    val arr = Array.fill(100){ Random.nextInt(100) }
//    val arr = Array(2,4,4,2,4,2,4,3,7,5)
//    arr.zip(arr.tail)
//
//    val dds = new DecimalDominantsSolve[Int](arr, arr.length / 10)
//
//    println(dds.dominants)
//  }
//}
