package Stack;

public class NodeS<T> {
    T data;
    NodeS<T> next;

    public NodeS(T data) {
        this.data = data;
        this.next = null;
    }
}
