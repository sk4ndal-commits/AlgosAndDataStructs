
public class DoublyLinkedList {

  public static void main(String[] args) {

    LinkedList linkedList = new LinkedList();
    linkedList.print();
    linkedList.push(1);
    linkedList.print();
    linkedList.push(2);
    linkedList.print();
    linkedList.push(3);
    linkedList.print();
    linkedList.shift();
    linkedList.print();
  }

  private static class Node {
    int val;
    Node prev, next;

    Node(int val) {
      this.val = val;
      this.prev = null;
      this.next = null;
    }
  }

  private static class LinkedList {
    int length;
    Node head, tail;

    LinkedList() {
      this.length = 0;
      this.head = null;
      this.tail = null;
    }

    public void push(int i) {
      Node newNode = new Node(i);

      if (this.head==null) {
        this.head = newNode;
        this.tail = newNode;
      }
      else {
        this.tail.next = newNode;
        newNode.prev = this.tail;
        this.tail = newNode;
      }

      this.length++;
    }

    public void pop() {

      if (this.length == 0) return;

      int popped = this.tail.val;

      if (this.length == 1) {
        this.head = null;
        this.tail = null;
      }
      else {
        Node newTail = this.tail.prev;
        newTail.next = null;
        this.tail.prev = null;
        this.tail = newTail;
      }

      this.length--;
      System.out.println("Popped " + popped);
    }


    public void shift() {
      if (this.length == 0) return;
      if (this.length == 1) {
        this.head = null;
        this.tail = null;
      }
      else {
        Node newHead = this.head.next;
        newHead.prev = null;
        this.head.next = null;
        this.head = newHead;
      }

      this.length--;
      System.out.println("Shifted");
    }

    public void print() {
      Node tmp = this.head;
      while (tmp!=null) {
        System.out.println(tmp.val);
        tmp = tmp.next;
      }
      System.out.println("Total number of elements " + this.length);
    }
  }
}
