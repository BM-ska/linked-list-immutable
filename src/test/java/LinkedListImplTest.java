import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListImplTest {
    LinkedList<Integer> linkedList;

    @BeforeEach
    void initializeTest(){
        linkedList = new LinkedListImpl<>();
    }

    @Test
    void testAddElements() {

        linkedList = linkedList.add(1);
        linkedList = linkedList.add(2);
        linkedList = linkedList.add(3);

        int expectedValue = 3;

        assertEquals(expectedValue, linkedList.size());
    }

    @Test
    void testIsEmptyWhenElementPassed() {
        linkedList = linkedList.add(1);

        assertFalse(linkedList.isEmpty());
    }

    @Test
    void testIsEmptyWhenNoElementPassed() {
        assertTrue(linkedList.isEmpty());
    }


    @Test
    void testThrowingExceptionWhenNullPassed() {

        assertThrows(NullPointerException.class, () -> linkedList.add((ListNode<Integer>) null));

    }

    @Test
    void testAddNodeAsElement(){
        linkedList = linkedList.add(1);

        ListNode<Integer> node = new LinkedListImpl.Node<>(2);
        node.setNext(new LinkedListImpl.Node<>(10));
        linkedList = linkedList.add(node);

        linkedList = linkedList.add(3);
        linkedList = linkedList.add(4);

        int expectedValue = 5;

        assertEquals(expectedValue, linkedList.size());
    }

    @Test
    void testThrowingExceptionWhenCycleDetected(){
        linkedList = linkedList.add(1);

        ListNode<Integer> node = new LinkedListImpl.Node<>(2);
        node.setNext(new LinkedListImpl.Node<>(10));
        linkedList = linkedList.add(node);

        linkedList = linkedList.add(3);
        linkedList = linkedList.add(4);

        assertDoesNotThrow( () -> linkedList.add(node));
    }
}