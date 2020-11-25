package dataStructures;

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	//The array of dictionaries.
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new CollisionList<K,V>();
        maxSize = capacity;
        currentSize = 0;

    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key ) {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key ) {
        int hashNumber = hash(key);
        return table[hashNumber].find(key);
    }

    @Override
    public V insert( K key, V value ) {
        if ( this.isFull() )
            this.rehash();
        int hashNumber = hash(key);
        if(table[hashNumber].size() == 0) {currentSize++;}
        return table[hashNumber].insert(key, value);
        //TODO: inserir novo elemento com key e value (ver comentarios
        //na interface de dicionario para saber o que deve ser devolvido 
        //no return
    }

    @Override
    public V remove( K key ){
        int hashNumber = hash(key);
        V val = table[hashNumber].remove(key);
        if(table[hashNumber].size() == 0) {currentSize--;}
        return val;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( ) {
        DoublyLinkedList<Entry<K,V>> auxList = new DoublyLinkedList<Entry<K,V>>();
        for(int i = 0; i < maxSize; i ++){
            auxList.append((DoublyLinkedList<Entry<K, V>>) table[i]);
        }
        return auxList.iterator();
    }

    private void rehash(){

        Dictionary<K,V>[] auxTable;
        int auxArraySize = HashTable.nextPrime((int) (1.1 * 2*maxSize));
        auxTable = (Dictionary<K,V>[]) new Dictionary[auxArraySize];
        for ( int i = 0; i < auxArraySize; i++ ) {
            auxTable[i] = new CollisionList<K,V>();
        }
        Iterator<Entry<K,V>> it = iterator();
        int newCurrentSize = 0;
        while(it.hasNext()){
            Entry<K, V> entry = it.next();
            int hashNumber = hash(entry.getKey());
            if(auxTable[hashNumber].size() == 0) {newCurrentSize++;}
            auxTable[hashNumber].insert(entry.getKey(), entry.getValue());
        }
        currentSize = newCurrentSize;
    }
}
































