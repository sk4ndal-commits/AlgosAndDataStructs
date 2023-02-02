import java.util.ArrayList;
import java.util.Objects;

public class HashTable {
    public static void main(String[] args) {

        HashTableImpl hashTable = new HashTableImpl(13);
        hashTable.set("kek", 12);
        hashTable.set("hello", 69);
        hashTable.set("myworld", 42);
        hashTable.set("keky", 37);
        hashTable.printTable();
        System.out.println();
        System.out.println("Value for key: keky");
        System.out.println(hashTable.get("keky"));

        System.out.println();

        hashTable.getKeys().forEach(System.out::println);
        hashTable.getValues().forEach(System.out::println);
    }


    private static class HashTableImpl {
        ArrayList<Pair>[] keyMap;
        int size;

        HashTableImpl(int size) {
            keyMap = new ArrayList[size];
            this.size = size;
        }

        public ArrayList<String> getKeys() {

            ArrayList<String> keys = new ArrayList<>();

            for (int i = 0; i < this.size; i++) if (this.keyMap[i] != null) {
                this.keyMap[i].forEach(pair -> {
                    if (!keys.contains(pair.key)) keys.add(pair.key);
                });
            }

            return keys;
        }

        public ArrayList<Integer> getValues() {
            ArrayList<Integer> values = new ArrayList<>();

            for (int i = 0; i < this.size; i++) if (this.keyMap[i] != null) {
                this.keyMap[i].forEach(pair -> {
                    if (!values.contains(pair.value)) values.add(pair.value);
                });
            }

            return values;
        }

        public Integer get(String key) {
            int getIdx = _hash(key);

            if (this.keyMap[getIdx] == null) return null;

            for (Pair p : this.keyMap[getIdx]) {
                if (Objects.equals(p.key, key)) return p.value;
            }

            return null;
        }

        public void set(String key, int value) {

            int setIdx = _hash(key);

            if (this.keyMap[setIdx] == null) this.keyMap[setIdx] = new ArrayList<>();
            this.keyMap[setIdx].add(new Pair(key, value));
        }

        public void printTable() {
            for (int i = 0; i < size; i++) if (this.keyMap[i] != null) {

                System.out.println("-------------");
                System.out.println("At index: " + i);
                System.out.println();

                ArrayList<Pair> listAtIndex = this.keyMap[i];
                listAtIndex.forEach(System.out::println);
            }
        }

        private int _hash(String s) {
            int resultIdx = 0, charValue, PRIME =31;

            for (int i = 0; i < Math.min(s.length(), 100); i++) {
                charValue = ((int) s.charAt(i)) - 96;
                resultIdx = (resultIdx * PRIME + charValue) % this.size;
            }

            return  resultIdx;
        }

    }

    private static class Pair {
        String key;
        Integer value;

        Pair(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + ": " + this.value;
        }
    }
}
