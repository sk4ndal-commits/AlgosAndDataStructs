
public class SinglyLinkedList {

  public static void main(String[] args) {
    LinkedList linkedList = new LinkedList();
    linkedList.push(1);
    linkedList.push(3);
    linkedList.push(2);
    linkedList.print();
    linkedList.reverse();
    linkedList.print();
    linkedList.reverse();
    linkedList.print();
  }

  private static class Node {
    int val;
    Node next;

    Node(int val) {
      this.val = val;
      this.next = null;
    }
  }

  private static class LinkedList {
    Node head, tail;
    int length;

    LinkedList() {
      this.head = null;
      this.tail = null;
      this.length = 0;
    }

    // O(n)
    public void reverse() {

      Node node = this.head;
      this.head = this.tail;
      this.tail = node;
      Node prev = null, next;

      for (int i = 0; i < this.length; i++) {
        next = node.next;
        node.next = prev;

        prev = node;
        node = next;
      }
    }

    // O(1)
    public void push(int i) {
      Node newNode = new Node(i);

      if (this.head==null) {
        this.head = newNode;
        this.tail = this.head;
      }
      else {
        this.tail.next = newNode;
        this.tail = this.tail.next;
      }

      this.length++;
    }

    // O(n)
    public void insert(int atPosition, int i) {
      if (atPosition>this.length || atPosition<0) return;

      if (atPosition==this.length) {
        this.push(i);
      }
      else if (atPosition==0) {
        this.unshift(i);
      }
      else {
        atPosition--;
        Node tmp = this.head, newNode = new Node(i);
        while(atPosition-- > 0) tmp = tmp.next;

        Node afterNewNode = tmp.next;
        tmp.next = newNode;
        newNode.next = afterNewNode;
        this.length++;
      }
      System.out.println("Inserted " + i + " at position " + atPosition);
    }

    // O(1)
    public void pop() {
      int popped;

      if (this.tail==null) return;
      else if (this.head==this.tail) {
        popped = this.head.val;
        this.head = null;
        this.tail = this.head;
      }
      else {
        Node tmp = this.head;

        while (tmp.next != this.tail) tmp = tmp.next;

        popped = this.tail.val;
        this.tail = tmp;
        this.tail.next = null;
      }

      System.out.println("popped " + popped);
      this.length--;
    }

    // O(n)
    public Node get(int atPosition) {
      if (atPosition>=this.length || atPosition<0) return null;

      Node tmp = this.head;
      int currentPosition = 0;

      while (currentPosition != atPosition) {
        tmp = tmp.next;
        currentPosition++;
      }

      System.out.println("Got " + tmp.val);
      return tmp;
    }

    // O(n)
    public void set(int atPosition, int i) {
      if (atPosition>=this.length || atPosition<0) return;

      Node tmp = get(atPosition);
      tmp.val = i;
      System.out.println("Set " + i + " at position " + atPosition);
    }

    // O(n)
    public void remove(int atPosition) {
      if (atPosition>=this.length || atPosition<0) return;
      if (atPosition == 0) this.shift();
      else if (atPosition==this.length-1) this.pop();
      else {
        atPosition--;
        Node tmp = this.head;
        while(atPosition-- > 0) tmp = tmp.next;

        tmp.next = tmp.next.next;

        this.length--;
      }
    }

    // O(1)
    public void unshift(int i) {
      Node newNode = new Node(i);

      if (this.head==null) {
        this.head = newNode;
        this.tail = this.head;
      }
      else {
        newNode.next = this.head;
        this.head = newNode;
      }

      this.length++;
      System.out.println("Unshifted " + i);
    }


    // O(1)
    public void shift() {
      if (this.head==null) return;
      int shifted = this.head.val;

      if (this.head.next==null) {
        this.head = null;
        this.tail = this.head;
      }
      else {
        Node newHead = this.head.next;
        this.head = newHead;
      }

      this.length--;
      System.out.println("shifted " + shifted);
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
