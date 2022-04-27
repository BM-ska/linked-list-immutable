/**
 * @author Barbara Moczulska
 */
public interface LinkedList<T> {

    LinkedList<T> add(T i);

    LinkedList<T> add(ListNode<T> newNode);

    boolean isEmpty();

    int size();

    boolean hasCycle();

    boolean hasCycle(ListNode<T> node);
}
