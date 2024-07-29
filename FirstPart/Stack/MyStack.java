package Stack;

import java.util.EmptyStackException;

public class MyStack<T> {
    private NodeS<T> top;

    public MyStack() {
        top = null;
    }

    // Method to add an element to the top of the stack
    public void push(T data) {
        NodeS<T> newNode = new NodeS<>(data); 
        newNode.next = top;
        top = newNode;
    }

    // Method to remove and return the top element of the stack
    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    // Method to look at the top element of the stack without removing it
    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }
}
