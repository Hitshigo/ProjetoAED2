package dataStructures;

public class SearchabledDoublyLL<E> extends DoublyLinkedList<E> {

    private DListNode node;

    public SearchabledDoublyLL() {
        super();
    }

    public DListNode<E> findNodeRec(E element) {


        return findNodeRec(element);
    }

    public boolean aux(E element, DListNode node) {

        if(node == null) {
            return false;
        }
    }
}
