package dataStructures;

public class OrderedSequenceClass<E extends Comparable<E>> implements OrderedSequence<E>  {

    private List<E> list;

    public OrderedSequenceClass() {
        list = new DoublyLinkedList<E>();
    }

    @Override
    public void insert(E element) {
        Iterator<E> it = list.iterator();
        if(!list.isEmpty()) {
            boolean found = false;
            for(int i = 0; it.hasNext() && found == false; i++) {
                E elm = it.next();
                if(element.compareTo(elm)<=0) {
                    found = true;
                    list.add(i, element);
                }
            }
            if (found == false){
                list.addLast(element);
            }
        }
        else{
            list.addFirst(element);
        }
    }

    @Override
    public boolean remove(E element) {
        return list.remove(element);
    }

    @Override
    public boolean contains(E element) {
        return list.find(element) != -1;
    }

    @Override
    public E get(E element) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }


}
