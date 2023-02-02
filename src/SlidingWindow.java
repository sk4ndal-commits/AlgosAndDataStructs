import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    public static void main(String[] args) {
        System.out.println(findLongestSubstring("helllo"));
    }

    private static int findLongestSubstring(String a) {
        int longest = 0, start = 0;
        Map<Character, Integer> seen = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {

            Character c = a.charAt(i);

            if (seen.containsKey(c)) {
                start = Math.max(start, seen.get(c));
            }

            longest = Math.max(longest, i-start+1);

            seen.put(c, i+1);
        }

        return longest;
    }

    private static int minSubArrayLen(int[] a, int sum) {
        int startPointer = 0, endPointer = 0;
        int minLen = (int) 1e9;

        int currentSum = 0;

        while (startPointer < a.length) {
            if (endPointer < a.length-1 && currentSum < sum) {
                currentSum+=a[endPointer];
                endPointer++;
            }
            else if (currentSum>=sum) {
                minLen = Math.min(minLen, endPointer-startPointer);
                currentSum-=a[startPointer];
                startPointer++;
            }
            else {
                break;
            }
        }

        return (minLen==(int)1e9 ? 0 : minLen);
    }

    private static void maxSubarraySumFast(int[] array, int n) {
        if (array.length < n) return;
        if (array.length == 0) return;

        int maxSum = Arrays.stream(Arrays.copyOfRange(array, 0, n)).sum();

        int p1 = 0, p2 = n-1, tmp = maxSum;

        while (p2 < array.length-1) {
            tmp -= array[p1];
            p1++; p2++;
            tmp += array[p2];

            maxSum = Math.max(maxSum, tmp);
        }

        System.out.println(maxSum);
    }

    private static void maxSubarraySum(int[] array, int n) {
        if (array.length < n) return;
        if (array.length == 0) return;

        int maxSum = Arrays.stream(Arrays.copyOfRange(array, 0, n)).sum();

        for (int i=1; i<=array.length-n; i++) {
            int[] copyRange = Arrays.copyOfRange(array, i, i+n);
            maxSum = Math.max(maxSum, Arrays.stream(copyRange).sum());
        }

        System.out.println(maxSum);
    }
}
