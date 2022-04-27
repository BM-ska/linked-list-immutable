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

    public LinkedListImpl(T i) {
        this.head = new Node<>(i);
    }

    public LinkedListImpl(ListNode<T> node) {

        if (hasCycle(node))
            throw new LinkedListCycleException("Cycle detected");

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

        LinkedList<T> newlinkedList = new LinkedListImpl<>(copyList(head, newNode));

        if (newlinkedList.hasCycle()) {
            throw new LinkedListCycleException("Cycle detected");
        }

        return newlinkedList;
    }

    public ListNode<T> copyList(ListNode<T> node, ListNode<T> last) {
        if (node == null) {
            return last;
        }

        ListNode<T> newNode = new Node<>(node.data());
        newNode.setNext(copyList(node.next(), last));

        return newNode;
    }

    @Override
    public int size(){
        int size = 1;

        ListNode<T> tail = head;

        if ( head == null || head.isEmpty() ) {
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
        StringBuilder lista = new StringBuilder();
        lista.append("LinkedList{");

        if ( head == null || head.isEmpty() ) {
            lista.append('}');
            return lista.toString();
        }

        lista.append('[');
        lista.append(head.data());

        ListNode<T> nextNode = head;
        while (nextNode.next() != null) {
            lista.append(", ");
            lista.append(nextNode.next().data());
            nextNode = nextNode.next();
        }

        lista.append("]}");
        return lista.toString();
    }

    public boolean hasCycle(ListNode<T> node) {
        if (node == null || node.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        Set<ListNode<T>> treeSet = new HashSet<>();

        ListNode<T> nextNode = node;
        while (nextNode.next() != null) {
            if (treeSet.contains(nextNode))
                return true;

            treeSet.add(nextNode);
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
