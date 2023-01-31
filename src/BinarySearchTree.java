public class BinarySearchTree {

  public static void main(String[] args) {

    BST binarySearchTree = new BST();
    binarySearchTree.insert(2);
    binarySearchTree.insert(1);
    binarySearchTree.insert(3);
    binarySearchTree.insert(4);
    binarySearchTree.insert(4);
    binarySearchTree.insert(5);
    binarySearchTree.search(5);
    System.out.println();
  }


  private static class Node {
    int val;
    Node left, right;

    Node(int val) {
      this.val = val;
      this.left = null;
      this.right = null;
    }
  }

  private static class BST {
    Node root;

    BST() {
      this.root = null;
    }

    private void insert(int val) {
      Node newNode = new Node(val);

      Node currentNode = this.root, parentCurrentNode = null;

      while (currentNode != null) {
        parentCurrentNode = currentNode;

        if (currentNode.val == val) return;
        else if (val < currentNode.val) currentNode = currentNode.left;
        else currentNode = currentNode.right;
      }

      if (parentCurrentNode == null) this.root = newNode;
      else if (val < parentCurrentNode.val) parentCurrentNode.left = newNode;
      else parentCurrentNode.right = newNode;
    }

    private void search(int val) {
      Node currentNode = this.root;

      while (currentNode != null && currentNode.val != val) {
        if (val < currentNode.val) currentNode = currentNode.left;
        else currentNode = currentNode.right;
      }

      System.out.println((currentNode==null ? "Value not found" : "Found " + val));
    }
  }
}
