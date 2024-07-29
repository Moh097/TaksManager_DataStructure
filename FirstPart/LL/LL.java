package LL;

public class LL<T> {

    private int size;
    private Node<T> head;

    public LL() {
        this.size = 0;
        this.head = null;
    }

    // Inserts a new node with the specified item at the end of the list
    public void insertNode(T item) {
        Node<T> newNode = new Node<>();
        newNode.item = item;
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    // Returns the head node of the list for external use
    public Node<T> getHead() {
        return this.head;
    }

    // Returns the size of the list
    public int getSize() {
        return this.size;
    }
}
