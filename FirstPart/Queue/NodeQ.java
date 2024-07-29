package Queue;

public class NodeQ<T> {
    T item;
    NodeQ<T> next;

    public NodeQ(T item) {
        this.item = item;
        this.next = null;
    }
}
