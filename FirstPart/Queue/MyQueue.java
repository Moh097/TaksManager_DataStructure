package Queue;

import java.util.NoSuchElementException;
import Code.Task;

public class MyQueue<T extends Task> {
    private NodeQ<T> front;
    private NodeQ<T> rear;
    private int size;

    public MyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T item) {
        NodeQ<T> newNode = new NodeQ<>(item);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = front.item;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return item;
    }

    public T peek() {
        if (front == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.item;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void remove(T item) {
        if (front == null) {
            return;
        }
        if (front.item.equals(item)) {
            dequeue();
            return;
        }
        NodeQ<T> current = front;
        while (current.next != null && !current.next.item.equals(item)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            if (current.next == null) {
                rear = current;
            }
            size--;
        }
    }

    // Method to add an element in a sorted manner according to the due date
    public void enqueueByDate(T data) {
        NodeQ<T> newNode = new NodeQ<>(data);
        if (front == null || front.item.getDueDate().isAfter(data.getDueDate())) {
            newNode.next = front;
            front = newNode;
            if (rear == null) { // If the list was empty, new node is also the rear
                rear = newNode;
            }
        } else {
            NodeQ<T> current = front;
            while (current.next != null && current.next.item.getDueDate().isBefore(data.getDueDate())) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (current.next == null) { // If the new node is inserted at the end
                rear = newNode;
            }
        }
        size++;  // Increment the size every time a new node is added
    }
}
