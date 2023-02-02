import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FrequencyCounter {

    public static void main(String[] args) {


        boolean test_182_281 = sameFrequency(182, 281);
        boolean test_34_14 = sameFrequency(34, 14);
        boolean test_3589578_5879385 = sameFrequency(3589578, 5879385);
        boolean test_22_222 = sameFrequency(22, 222);

        System.out.println(test_182_281);
        System.out.println(test_34_14);
        System.out.println(test_3589578_5879385);
        System.out.println(test_22_222);

        System.out.println(sameFrequency(0, 10));

    }

    private static boolean sameFrequency(int a, int b) {
        boolean haveSameFrequency = true;

        Map<Integer, Integer> fMapA = new HashMap<>(), fMapB = new HashMap<>();
        int numFactorsA = 0, numFactorsB = 0;

        if (a == 0) fMapA.put(0, 1);
        if (b == 0) fMapB.put(0, 1);

        while (a!=0) {
            fMapA.put(a%10, (fMapA.containsKey(a%10) ? fMapA.get(a%10)+1 : 1));
            a/=10;
            numFactorsA++;
        }
        while (b!=0) {
            fMapB.put(b%10, (fMapB.containsKey(b%10) ? fMapB.get(b%10)+1 : 1));
            b/=10;
            numFactorsB++;
        }

        if (numFactorsA != numFactorsB) return false;

        for (Map.Entry<Integer, Integer> mp : fMapA.entrySet()) {
            if (!Objects.equals(mp.getValue(), fMapB.get(mp.getKey()))) {
                haveSameFrequency = false;
                return haveSameFrequency;
            }
        }

        return haveSameFrequency;
    }

    private static boolean areAnagrams(String a, String b) {
        boolean isAnagram = true;

        if (a.length() != b.length()) { isAnagram = false; return isAnagram;}

        Map<Character, Integer> charFreqA = frequencies(a), charFreqB = frequencies(b);


        for (Map.Entry<Character, Integer> mp: charFreqA.entrySet()) {
            if (!Objects.equals(mp.getValue(), charFreqB.get(mp.getKey()))) {
                isAnagram = false;
                return isAnagram;
            }
        }

        return isAnagram;
    }

    private static boolean areSame(int[] a, int[] b) {
        boolean isSame = true;

        if (a.length != b.length) { isSame = false; return isSame; }

        Map<Integer, Integer> numFreqA = frequencies(a), numFreqB = frequencies(b);

        for (Map.Entry<Integer, Integer> mp : numFreqA.entrySet()) {
            int squaredKey = mp.getKey()*mp.getKey();

            if (!Objects.equals(numFreqB.get(squaredKey), mp.getValue())) {
                isSame = false;
                break;
            }
        }

        return isSame;
    }

    private static Map<Integer, Integer> frequencies(int[] a) {
        Map<Integer, Integer> result = new HashMap<>();

        for (int i : a) {
            result.put(i, (result.containsKey(i) ? result.get(i) +1 : 1));
        }

        return result;
    }

    private static Map<Character, Integer> frequencies(String a) {
        Map<Character, Integer> result = new HashMap<>();

        for (char i : a.toCharArray()) {
            result.put(i, (result.containsKey(i) ? result.get(i) +1 : 1));
        }

        return result;
    }
}
