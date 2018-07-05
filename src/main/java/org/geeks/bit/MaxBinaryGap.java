package org.geeks.bit;

/**
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros,
 * that is surrounded by ones at both ends in the binary representation of N.
 *
 * For example,
 *  number 9 has binary representation 1001 and contains a binary gap of length 2.
 *  number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3
 */
public class MaxBinaryGap {
    int solution(int N) {
	// Using method when we divided the number by two.
	//	if the rest of the division is 1 : start/end of gap.
	//	if the rest of the division is 0 : within gap.
        int result, gap, count;
        for (count = 0, result = 0, gap = 0; N > 0; N /= 2) {
            if ((N % 2) > 0) {
                if (result < gap)
                    result = gap;

                count = 1;
                gap = 0;
            }
            else if ((N % 2) == 0) {
                if (count == 1) gap++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MaxBinaryGap mbg = new MaxBinaryGap();

//        System.out.printf("Binary gap is %d\n", mbg.solution(0));    //00             0 (No gap).
//        System.out.printf("Binary gap is %d\n", mbg.solution(1));    //01             0 (No gap).
//        System.out.printf("Binary gap is %d\n", mbg.solution(2));    //11             0 (No gap).
        System.out.printf("Binary gap is %d\n", mbg.solution(5));    //101            1 (Gap is 1).
//        System.out.printf("Binary gap is %d\n", mbg.solution(9));    //1001           2 (Gap is 2).
//        System.out.printf("Binary gap is %d\n", mbg.solution(529));  //1000010001     4 (Max gap is 4).
//        System.out.printf("Binary gap is %d\n", mbg.solution(1041)); //10000010001    5 (Max gap is 5).
    }
}
