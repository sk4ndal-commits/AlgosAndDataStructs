import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {
    public static void main(String[] args) {

        int[] xs = {1,23,456,7890};

        System.out.println(mostDigits(xs));
    }

    private static void radixSort(int[] nums) {
        int maxDigits = mostDigits(nums);

        for (int k = 0; k < maxDigits; k++) {

            // create the bucket each time we progress in the number positions
            ArrayList<Integer>[] buckets = new ArrayList[10];
            for (int i = 0; i < 10; i++) {
                buckets[i] = new ArrayList();
            }

            for (int num : nums) {
                int numberBucket = getDigit(num, k);
                buckets[numberBucket].add(num);
            }

            // here we need to do sth like: nums = [].concat(...buckets)
            // to put the bucket elements back into nums and redo processing
        }
    }

    private static int mostDigits(int[] nums) {
        return Arrays.stream(nums).map(Sorting::digitCount).max().getAsInt();
    }

    private static int getDigit(int num, int pos) {
        if (num == 0) return 1;
        return (int) (Math.abs(num) / Math.pow(10, pos)) % 10;
    }

    private static int digitCount(int num) {
        return (int) Math.log10(Math.abs(num))+1;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int[] quicksort(int[] a, int left, int right) {
        if (left<right) {
            int pivIdx = pivot(a, left, right);
            quicksort(a, left, pivIdx-1);
            quicksort(a, pivIdx+1, right);
        }
        return a;
    }
    private static int pivot(int[] a, int start, int end) {
        int pivot = a[start];
        int swapIdx = start;

        for (int i = start+1; i < end; i++) {
            if (pivot > a[i]) {
                swapIdx++;
                swap(a, swapIdx, i);
            }
        }

        swap(a, swapIdx, start);
        return swapIdx;
    }

    private static int[] mergeSort(int[] a) {
        if (a.length<=1) return a;
        int m = a.length >> 1;
        int[] left = Arrays.copyOfRange(a, 0, m);
        int[] right = Arrays.copyOfRange(a, m, a.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] a, int[] b) {
        int sizeA = a.length, sizeB = b.length;
        int indexA = 0, indexB = 0;
        int[] result = new int[sizeA+sizeB];

        List<Integer> tempList = new ArrayList<>();

        while (indexA < sizeA && indexB <sizeB) {
            if (b[indexB] > a[indexA]) {
                tempList.add(a[indexA]);
                indexA++;
            }
            else {
                tempList.add(b[indexB]);
                indexB++;
            }
        }

        while (indexA<sizeA) {
            tempList.add(a[indexA]);
            indexA++;
        }

        while (indexB<sizeB) {
            tempList.add(b[indexB]);
            indexB++;
        }

        if (tempList.size() != sizeA+sizeB) throw new NullPointerException();

        for (int i = 0; i < tempList.size(); i++) {
            result[i] = tempList.get(i);
        }

        return result;
    }

    private static void insertionSort(int[] a) {
        int j, currVal;

        for (int i=1; i<a.length; i++) {
            currVal = a[i];

            for (j = i-1; j >= 0 && a[j] > currVal; j++) {
                a[j+1] = a[j];
            }


            a[j+1] = currVal;
        }

        System.out.println(Arrays.toString(a));
    }

    private static void selectionSort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            int lowest = i;

            for (int j = i+1; j < a.length; j++) {
                if (a[lowest] > a[j]) lowest = j;
            }

            if (i!=lowest) {
                int t = a[i];
                a[i] = a[lowest];
                a[lowest] = t;
            }
        }

        System.out.println(Arrays.toString(a));
    }

    private static void bubbleSort(int[] a) {
        int size = a.length;
        boolean hasChanged;

        for (int i = size-1; i >= 0; i--) {
            hasChanged = false;

            for (int j = 0; j < i; j++) {
                if (a[j] > a[j+1]) {
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                    hasChanged = true;
                }
            }

            if (!hasChanged) {
                break;
            }
        }

        System.out.println(Arrays.toString(a));
    }
}
