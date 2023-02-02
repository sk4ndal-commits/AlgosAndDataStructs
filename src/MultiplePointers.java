import java.util.Arrays;

public class MultiplePointers {

    public static void main(String[] args) {

        double[] a = {};
        double d = 4.1;
        System.out.println(averagePair(a, d));

    }


    private static boolean isSubsequence(String a, String b) {
        int sizeA = a.length(), sizeB = b.length();

        if (sizeA == 0 || sizeB>sizeA) return false;

        int pointerA = 0, pointerB = 0;

        while (pointerB<sizeB) {
            if (a.charAt(pointerA) == b.charAt(pointerB)) pointerA++;
            if (pointerA==sizeA-1) return true;
            pointerB++;
        }

        return false;
    }

    private static boolean averagePair(double[] doubleArray, double avrg) {
        if (doubleArray.length==0) return false;

        double arrayMax = Arrays.stream(doubleArray).max().getAsDouble();
        double arrayMin = Arrays.stream(doubleArray).min().getAsDouble();

        if (arrayMax < avrg || arrayMin > avrg)
            return false;

        Arrays.sort(doubleArray);
        int leftPointer = 0, rightPointer = doubleArray.length-1;

        while (leftPointer<rightPointer) {
            double currentAvrg = (doubleArray[leftPointer]+doubleArray[rightPointer])/2.;

            if (currentAvrg == avrg) return true;
            if (currentAvrg<avrg) leftPointer++;
            else rightPointer--;
        }

        return false;
    }

    private static boolean areThereDuplicates(int[] a) {

        // next time check : Set(a).length == a.length

        Arrays.sort(a);
        int p1 = 0, p2 = 1;

        while (p2 < a.length) {

            if (a[p1] == a[p2]) return true;

            p1++; p2++;
        }

        return false;
    }

    private static void findTwoElementsEqualToSum(int sum, int[] array) {
        Arrays.sort(array);
        int leftPointer = 0, rightPointer = array.length-1;
        int currentSum;

        while (leftPointer<rightPointer) {
            currentSum = array[leftPointer]+array[rightPointer];

            if (currentSum==sum) {
                System.out.println("Result: " + array[leftPointer] + " " + array[rightPointer]);
                return;
            }
            else if (currentSum<sum) leftPointer++;
            else rightPointer--;
        }

        System.out.println("No elements found");
    }

    private static void countUniqueValues(int[] array) {
        if (array.length==0) {
            System.out.println(0);
            return;
        }

        int p1 = 0, p2 = 1;
        int result = 1;

        while (p2<array.length) {
            if (array[p1] != array[p2]) {
                result++;
                p1 = p2;
            }
            p2++;
        }

        System.out.println(result);
    }
}
