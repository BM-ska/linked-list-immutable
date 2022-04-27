import java.util.HashSet;
import java.util.Set;

/**
 * @author Barbara Moczulska
 */
public final class LinkedListImpl<T> implements LinkedList<T> {

    private final ListNode<T> head;

    public LinkedListImpl() {
        this.head = null;
    }

    public LinkedListImpl(T data) {
        this.head = new Node<>(data);
    }

    public LinkedListImpl(ListNode<T> node) {

        if (hasCycle(node)) {
            throw new LinkedListCycleException("Cycle detected");
        }

        this.head = node;

    }

    @Override
    public LinkedList<T> add(T i) {
        return add(new Node<>(i));
    }

    @Override
    public LinkedList<T> add(ListNode<T> newNode) {
        if (newNode == null || newNode.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        if (hasCycle(newNode)) {
            throw new LinkedListCycleException("Cycle detected");
        }

        if (head == null) {
            return new LinkedListImpl<>(newNode);
        }

        LinkedList<T> newlinkedList = new LinkedListImpl<>(copyWithTail(head, newNode));

        if (newlinkedList.hasCycle()) {
            throw new LinkedListCycleException("Cycle detected");
        }

        return newlinkedList;
    }

    public ListNode<T> copyWithTail(ListNode<T> node, ListNode<T> last) {
        if (node == null) {
            return last;
        }

        ListNode<T> newNode = new Node<>(node.data());
        newNode.setNext(copyWithTail(node.next(), last));

        return newNode;
    }

    @Override
    public int size() {
        int size = 1;

        ListNode<T> tail = head;

        if (head == null || head.isEmpty()) {
            return 0;
        }

        while (tail.next() != null) {
            size++;
            tail = tail.next();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null || head.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList{");

        if (head == null || head.isEmpty()) {
            sb.append('}');
            return sb.toString();
        }

        sb.append('[');
        sb.append(head.data());

        ListNode<T> nextNode = head;
        while (nextNode.next() != null) {
            sb.append(", ");
            sb.append(nextNode.next().data());
            nextNode = nextNode.next();
        }

        sb.append("]}");
        return sb.toString();
    }

    public boolean hasCycle(ListNode<T> node) {
        if (node == null || node.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        Set<ListNode<T>> visitedNodes = new HashSet<>();

        ListNode<T> nextNode = node;
        while (nextNode.next() != null) {
            if (visitedNodes.contains(nextNode)) {
                return true;
            }

            visitedNodes.add(nextNode);
            nextNode = nextNode.next();

        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle(head);
    }

    public static final class Node<T> implements ListNode<T> {

        private final T value;
        private ListNode<T> next;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public T data() {
            return this.value;
        }

        @Override
        public ListNode<T> next() {
            return this.next;
        }

        @Override
        public ListNode<T> setNext(ListNode<T> next) {
            this.next = next;
            return this;
        }

        @Override
        public boolean isEmpty() {
            return this.value == null;
        }

    }

}
