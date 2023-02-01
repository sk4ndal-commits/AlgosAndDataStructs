import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTraversal {

    public static void main(String[] args) {

        BinarySearchTree.BST tree = new BinarySearchTree.BST();

        tree.insert(10);
        tree.insert(6);
        tree.insert(15);
        tree.insert(3);
        tree.insert(8);
        tree.insert(20);

        DFS dfs = new DFS(tree);

        dfs.runPreOrder(tree.root);
        dfs.printAndClear();

        System.out.println("--------------------------------");

        dfs.runInOrder(tree.root);
        dfs.printAndClear();

        System.out.println("--------------------------------");

        dfs.runPostOrder(tree.root);
        dfs.printAndClear();
    }


    public static class BFS {

        public static void run(BinarySearchTree.BST tree) {
            if (tree == null || tree.root == null) return;

            List<Integer> visited = new ArrayList<>();
            Queue<BinarySearchTree.Node> queue = new LinkedList<>();

            BinarySearchTree.Node currentNode = tree.root;
            queue.add(currentNode);

            while (!queue.isEmpty()) {
                currentNode = queue.remove();
                visited.add(currentNode.val);

                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }

            visited.forEach(System.out::println);
        }
    }

    public static class DFS {
        List<Integer> visited;

        DFS(BinarySearchTree.BST tree) {

            visited = new ArrayList<>();
        }

        public void runPreOrder(BinarySearchTree.Node currentNode) {
            visited.add(currentNode.val);

            if (currentNode.left != null) runPreOrder(currentNode.left);
            if (currentNode.right != null) runPreOrder(currentNode.right);
        }

        public void runInOrder(BinarySearchTree.Node currentNode) {
            if (currentNode.left != null) runInOrder(currentNode.left);

            visited.add(currentNode.val);

            if (currentNode.right != null) runInOrder(currentNode.right);
        }

        public void runPostOrder(BinarySearchTree.Node currentNode) {
            if (currentNode.left != null) runPostOrder(currentNode.left);
            if (currentNode.right != null) runPostOrder(currentNode.right);

            visited.add(currentNode.val);

        }
        public void printAndClear() {
            visited.forEach(System.out::println);
            visited.clear();

        }
    }
}
