import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class BinaryHeap {

    public static void main(String[] args) {
        PriorityQueue.meh();
    }


    public static class MaxBinaryHeap {

        private final List<Integer> values;
        private int lastIdx;
        MaxBinaryHeap() {
            values = new ArrayList<>();
            lastIdx = -1;
        }
        public void insert(Integer newValue) {
            values.add(newValue);
            lastIdx++;

            if (values.size() > 2) bubbleUp(lastIdx);
        }
        public void extractMax() {

            swap(values, 0, lastIdx);
            int removedValue = values.remove(lastIdx);
            lastIdx--;

            System.out.println("Removed " + removedValue);

            if (values.size() > 0) sinkDown(0);
        }
        private void sinkDown(int sinkingNodeIdx) {


            // we need to bound these indexes with the lastIdx since it may happen
            // that we step out of array bounds otherwise
            int leftChildIdx = Math.min(getLeftChildIdxOf(sinkingNodeIdx), lastIdx);
            int rightChildIdx = Math.min(getRightChildIdxOf(sinkingNodeIdx), lastIdx);

            int leftChildValue = values.get(leftChildIdx);
            int rightChildValue = values.get(rightChildIdx);
            int sinkingNodeValue = values.get(sinkingNodeIdx);

            int swappingNodeIdx;


            while (sinkingNodeValue < leftChildValue || sinkingNodeValue < rightChildValue) {

                swappingNodeIdx = leftChildValue > rightChildValue ? leftChildIdx : rightChildIdx;
                swap(values, sinkingNodeIdx, swappingNodeIdx);

                sinkingNodeIdx = swappingNodeIdx;
                leftChildIdx = Math.min(getLeftChildIdxOf(sinkingNodeIdx), lastIdx);
                rightChildIdx = Math.min(getRightChildIdxOf(sinkingNodeIdx), lastIdx);

                sinkingNodeValue = values.get(sinkingNodeIdx);
                leftChildValue = values.get(leftChildIdx);
                rightChildValue = values.get(rightChildIdx);
            }
        }
        private int getLeftChildIdxOf(int nodeIdx) { return 2*nodeIdx+1; }
        private int getRightChildIdxOf(int nodeIdx) { return 2*nodeIdx+2; }
        private int getParentIdxOf(int index) {
            return (int) Math.floor((index - 1) >> 1);
        }
        private void bubbleUp(int childIndex) {
            int parentIndex = getParentIdxOf(childIndex);

            while (parentIndex >= 0 && values.get(parentIndex) < values.get(childIndex)) {
                swap(values, parentIndex, childIndex);

                childIndex = parentIndex;
                parentIndex = getParentIdxOf(childIndex);
            }
        }
        public void print() {
            values.forEach(System.out::println);
        }
    }

    public static class PriorityQueue {
        public static void meh(){
            System.out.println("NOOO");
        }
    }
}
