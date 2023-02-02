public class StacksAndQueues {


    public static void main(String[] args) {

        Queue queue = new Queue();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.print();
        queue.pop();
        queue.print();
    }

    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static class Queue {
        int size;
        Node first;
        Node last;

        Queue() {
            this.size = 0;
            this.first = null;
            this.last = null;
        }

        private void push(int val) {
            Node newNode = new Node(val);

            if (this.size == 0) {
                this.first = newNode;
                this.last = newNode;
            }
            else {
                this.last.next = newNode;
                this.last = newNode;
            }

            this.size++;
            System.out.println("Pushed " + val);
        }

        private void pop(){
            if (this.size == 0) return;

            int popped = this.first.val;

            if (this.size == 1) {
                this.first = null;
                this.last = null;
            }
            else {
                Node oldFirst = this.first;
                this.first = this.first.next;
                oldFirst.next = null;
            }

            this.size--;
            System.out.println("Popped " + popped);
        }

        private void print() {
            Node tmp = this.first;

            while (tmp != null) {
                System.out.println(tmp.val);
                tmp = tmp.next;
            }

            System.out.println("#elements " + this.size);
        }
    }

    private static class Stack {

        int size;
        Node first;
        Node last;

        Stack() {
            this.size = 0;
            this.first = null;
            this.last = null;
        }

        private void push(int val) {
            Node newNode = new Node(val);

            if (this.size == 0) {
                this.first = newNode;
                this.last = newNode;
            }
            else {
                newNode.next = this.first;
                this.first = newNode;
            }

            this.size++;

            System.out.println("Pushed " + val);
        }

        private void pop() {
            if (this.size == 0) return;

            int popped = this.first.val;

            if (this.size == 1) {
                this.first = null;
                this.last = null;
            }
            else {
                Node poppedNode = this.first;
                this.first = this.first.next;
                poppedNode.next = null;
            }

            this.size--;
            System.out.println("Popped " + popped);

        }

        private void print() {
            Node tmp = this.first;

            while (tmp != null) {
                System.out.println(tmp.val);
                tmp = tmp.next;
            }

            System.out.println("#elements " + this.size);
        }
    }
}
