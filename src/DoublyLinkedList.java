
public class DoublyLinkedList {

  public static void main(String[] args) {

    LinkedList linkedList = new LinkedList();
    linkedList.push(1);
    linkedList.push(2);
    linkedList.push(3);
    linkedList.push(4);
    linkedList.print();
    linkedList.insert(3, 69);
    linkedList.print();
    linkedList.remove(4);
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

    public void insert(int atPosition, int val) {
      Node beforeInsertNode = this.get(atPosition-1);

      // at Position within boundaries
      if (beforeInsertNode != null) {
        Node toInsert = new Node(val), afterInsertNode = beforeInsertNode.next;

        beforeInsertNode.next = toInsert;
        toInsert.prev = beforeInsertNode;
        afterInsertNode.prev = toInsert;
        toInsert.next = afterInsertNode;
      }
      // insert the element right next to the boundaries
      else {
        if (atPosition == 0) {
          this.unshift(val);
        }
        else if (atPosition == this.length-1) {
          this.push(val);
        }
        else return;
      }



      this.length++;
      System.out.println("Inserted");
    }

    public void remove(int atPosition) {
      Node toRemove = this.get(atPosition);


      if (atPosition == 0) this.shift();
      else if (atPosition == this.length-1) this.pop();
      else if (toRemove != null) {

        Node before = toRemove.prev , after = toRemove.next;
        toRemove.prev = null;
        toRemove.next = null;

        before.next = after;
        after.prev = before;
      }
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

    public void set(int atPosition, int val) {
      Node toSet = this.get(atPosition);
      if (toSet == null) return;
      toSet.val = val;
      System.out.println("Set at index " + atPosition + " val " + toSet.val);
    }

    /**
     *
     * @param atPosition 0-indexed
     */
    public Node get(int atPosition) {
      if (atPosition < 0 || atPosition >= this.length) return null;

      // used for small optimization
      int listMiddle = this.length / 2;
      int stepsToGo;
      Node tmp;

      // if <atPosition> comes after the middle, start from the tail
      if (atPosition >= listMiddle) {
        stepsToGo = this.length-1 - atPosition;
        tmp = this.tail;

        while (stepsToGo-- > 0) {
          tmp = tmp.prev;
        }
      }
      // else start from the head
      else {
        stepsToGo = atPosition;
        tmp = this.head;

        while (stepsToGo-- > 0) {
          tmp = tmp.next;
        }
      }

      System.out.println("Got " + tmp.val);
      return tmp;
    }

    public void unshift(int i) {
      Node newNode = new Node(i);

      if (this.length == 0) {
        this.head = newNode;
        this.tail = newNode;
      }
      else {
        this.head.prev = newNode;
        newNode.next = this.head;
        this.head = newNode;
      }

      this.length++;
      System.out.println("Unshifted " + i);
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
