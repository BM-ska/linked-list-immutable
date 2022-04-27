import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableList;

/**
 * @author Barbara Moczulska
 */
public final class LinkedListImpl<T> implements LinkedList<T> {

    private final ListNode<T> head;

    public LinkedListImpl(T i) {
        this.head = new Node<>(i);
    }

    public LinkedListImpl(ListNode<T> node) {

        if(hasCycle(node))
            throw new LinkedListCycleException("Cycle detected");

        this.head = node;

    }

    @Override
    public ListNode<T> add(T i) {
        return add(new Node<>(i));
    }

    @Override
    public ListNode<T> add(ListNode<T> newNode) {
        if (newNode == null || newNode.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        if(hasCycle(newNode))
            throw new LinkedListCycleException("Cycle detected");


        if (head.isEmpty()) {
            head = newNode;
        } else {
            ListNode<T> tail1 = head;
            while (tail1.next() != null) {
                tail1 = tail1.next();
            }
            tail1.setNext(newNode);
        }

        if(hasCycle(newNode))
            throw new LinkedListCycleException("Cycle detected");

        return copyList(head).setNext(newNode);
    }

    public ListNode<T> copyList(ListNode<T> node)
    {
        if (node == null ) {
            return null;
        }

        ListNode<T> newNode = new Node<>(node.data());
        newNode.setNext(copyList(node.next()));

        return newNode;
    }

    @Override
    public boolean isEmpty() {
        return head.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder lista = new StringBuilder();
        lista.append("LinkedList{");

        if (head.isEmpty()) {
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

    boolean hasCycle ( ListNode<T> node )
    {
        if (node == null || node.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        Set<ListNode<T>> treeSet = new HashSet<>();

        ListNode<T> nextNode = node;
        while (nextNode.next() != null) {
            if(treeSet.contains(nextNode))
                return true;

            treeSet.add(nextNode);
            nextNode = nextNode.next();

        }
        return  false;
    }

    public static final class Node<T> implements ListNode<T> {

        private T value;
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
            return this.next;
        }

        @Override
        public boolean isEmpty() {
            return this.value == null;
        }

    }



}
