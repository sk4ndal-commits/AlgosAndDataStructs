import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming {

    public static void main(String[] args) {
        System.out.println(RecursiveFibonacci.run(45));

        MemoizedFibonacci memoizedFibonacci = new MemoizedFibonacci();

        System.out.println(memoizedFibonacci.run(45L));
    }

    private static class RecursiveFibonacci {

        public static int run(int n) {
            if (n <= 2) return 1;
            return run(n-1) + run(n-2);
        }
    }

    private static class MemoizedFibonacci {
        Map<Long, Long> memory;

        MemoizedFibonacci() {
            this.memory = new HashMap<>();
        }

        public Long run(Long n) {
            if (n <= 2) return 1L;
            if (!this.memory.containsKey(n)) {
                this.memory.put(n, run(n-1) + run(n-2));
            }
            return this.memory.get(n);
        }
    }
}
