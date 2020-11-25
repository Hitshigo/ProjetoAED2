package dataStructures;

/**
 * Doubly linked list Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class DoublyLinkedList<E> implements List<E>  {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	static class DListNode<E>{
		// Element stored in the node.
		protected E element;
		// (Pointer to) the next node.
		protected DListNode<E> next;
		// (Pointer to) the previous node.
		protected DListNode<E> previous;

		public DListNode( E elem, DListNode<E> thePrev, DListNode<E> theNext ){
			element = elem;
			previous = thePrev;
			next = theNext;
		}
		
		public DListNode( E theElement ){
			this(theElement, null, null);
		}

		public E getElement( ){
			return element;
		}

		public DListNode<E> getNext( ){
			return next;
		}

		public DListNode<E> getPrevious( ){
			return previous;
		}

		public void setElement( E newElement ){
			element = newElement;
		}

		public void setNext( DListNode<E> newNext ){
			next = newNext;
		}

		public void setPrevious( DListNode<E> newPrevious ){
			previous = newPrevious;
		}

	}


	// Node at the head of the list.
	protected DListNode<E> head;

	// Node at the tail of the list.
	protected DListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	public DoublyLinkedList(){
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		boolean found = false;
		if (head == null){
			found = true;
		}
		return found;
	}

	@Override
	public int size() {
		return currentSize;
	}


	@Override
	public int find(E element) {
		int pos=-1;
		DListNode<E> auxNo = head;
		boolean found=false;
		for(int i = 0; i < currentSize && found == false; i++){
			if (auxNo.getElement().equals(element)){
				pos = i;
				found = true;
			}
			auxNo = auxNo.getNext();
		}
		return pos;
	}


	@Override
	public E getFirst() throws NoElementException {
		if (isEmpty()) throw new NoElementException();
		return get(0);
	}

	@Override
	public E getLast() throws NoElementException {
		if (isEmpty()) throw new NoElementException();
		return get(currentSize);
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position<0 || position>=currentSize) 
			throw new InvalidPositionException();
		return getNode(position).getElement();
	}
	
	private DListNode<E> getNode(int position) {
		DListNode<E> auxNode = head;
		for (int i = 1; i <= position; i++){
			auxNode = auxNode.getNext();
		}
		return auxNode;
	}
	

	@Override
	public void addFirst(E element) {
		DListNode<E> node = new DListNode<E>(element,null,null);
		if (isEmpty()){
			head = node;
			tail = node;
		}
		else{
			head.setPrevious(node);
			node.setNext(head);
			head = node;
		}
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		DListNode<E> node = new DListNode<E>(element, null,null);
		if (isEmpty()){
			head = node;
			tail = node;
		}
		else{
			tail.setNext(node);
			node.setPrevious(tail);
			tail = node;
		}
		currentSize++;
	}

	private void addMiddle(int position, E element) {
		DListNode<E> auxNode=getNode(position);
		DListNode<E> auxNodeP= auxNode.getPrevious();
		DListNode<E> node = new DListNode<E>(element,auxNodeP,auxNode);
		auxNode.setPrevious(node);
		auxNodeP.setNext(node);
		currentSize++;
	}
	
	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position<0 || position >currentSize) 
			throw new InvalidPositionException();
		if (position==0) 
			addFirst(element);
		else if (position==currentSize) 
			addLast(element);
		else {
			addMiddle(position,element);
		}

	}

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    private void removeFirstNode( ) {
		if(currentSize == 1){
			head = null;
			tail = null;
		}
		else{
			DListNode<E> auxNode = head.getNext();
			head  = auxNode;
			head.setPrevious(null);
		}
		currentSize--;
    }


    @Override
    public E removeFirst( ) throws NoElementException {
        if (isEmpty())
            throw new NoElementException();

        E element = head.getElement();
        this.removeFirstNode();
        return element;
    }
	
    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    private void removeLastNode( ) {
		if (currentSize == 1){
			head = null;
			tail = null;
		}
		else{
			DListNode<E> auxNode = tail.getPrevious();
			tail  = auxNode;
			tail.setNext(null);
		}
		currentSize--;
    }


    @Override 
    public E removeLast( ) throws NoElementException {
        if (isEmpty())
            throw new NoElementException();

        E element = tail.getElement();
        this.removeLastNode();
        return element;
    }
    
    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     * @param node - middle node to be removed
     */
    private void removeMiddleNode(DListNode<E> node) {
      	DListNode<E> prev = node.getPrevious();
      	DListNode<E> next = node.getNext();
      	prev.setNext(next);
      	next.setPrevious(prev);
      	currentSize--;
    }
    
	private E removeMiddle(int position) {
		DListNode<E> nodeToRemove = this.getNode(position);
		this.removeMiddleNode(nodeToRemove);
		return nodeToRemove.getElement();
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if(position<0 || position>=currentSize)
			throw new InvalidPositionException();
		if (position==0)
			return removeFirst();
		if (position==currentSize-1)
			return removeLast();
		return removeMiddle(position);
	}

	private DListNode<E> findNode(E element) {
		int i = find(element);
		return getNode(i);
	}

	@Override
	public boolean remove(E element)
	{
		DListNode<E> node = this.findNode(element);
		if ( node == null )
			return false;
		else
		{
			if ( node == head )
				this.removeFirstNode();
			else if ( node == tail )
				this.removeLastNode();
			else
				this.removeMiddleNode(node);
			return true;
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return new DoublyLLIterator<E>(head,tail);
	}

	@Override
	public void append(DoublyLinkedList<E> list){
    	if (head == null){
    		head = list.head;
    		tail = list.tail;
		}
    	else if (list.head != null){
			tail.setNext(list.head);
			list.head.setPrevious(tail);
			tail = list.tail;
		}

	}
}
