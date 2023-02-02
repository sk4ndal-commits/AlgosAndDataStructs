
public class Recursion {

    public static void main(String[] args) {

    }

    private static void capitalize(String[] a, int pos) {

        if (pos == a.length) return;

        a[pos] = a[pos].toUpperCase();

        capitalize(a, pos+1);
    }


    private static class SomeRecursion {
        int[] a;
        int pos = 0;
        boolean ans;
        Callback callback;

        SomeRecursion(int[] a, Callback callback) {
            this.a = a;
            this.callback = callback;
            this.ans = false;
        }

        public void recurse() {
            if (pos == this.a.length-1) return;
            this.ans |= this.callback.call(this.a[pos]);
            pos++;
            recurse();
        }

        public void printAns() {
            System.out.println(this.ans);
        }
    }

    private static int power(int a, int n) {
        if (n<0) throw new IllegalArgumentException();
        return (n==0 ? 1 : a * power(a, n-1));
    }

    private static int factorial(int n) {
        if (n<1) throw new IllegalArgumentException();
        return (n>2 ? n * factorial(n-1) : n);
    }

    private static int productOfArray(int[] a, int currArrPos) {
        if (a.length == 0) throw new IllegalArgumentException();
        if (currArrPos==a.length-1) return a[currArrPos];
        return a[currArrPos] * productOfArray(a, currArrPos+1);
    }

    private static int recursiveRange(int n) {
        if (n<0) throw new IllegalArgumentException();
        if (n==0) return 0;
        return n + recursiveRange(n-1);
    }

    private static int fib(int n) {
        if (n<0) throw new IllegalArgumentException();
        if (n<=2) return 1;
        return fib(n-1) + fib(n-2);
    }

    private static String reverse(String a, int pos) {
        if (pos==0) return String.valueOf(a.charAt(pos));

        return String.valueOf(a.charAt(pos)) + reverse(a, pos-1);
    }

    private static boolean isPalindrome(String s, int left, int right) {
        if (left==s.length()/2 || left==right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindrome(s, left + 1, right - 1);
    }

    @FunctionalInterface
    private static interface Callback {
        boolean call(int x);
    }
}
