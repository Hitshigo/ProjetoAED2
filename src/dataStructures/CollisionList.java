package dataStructures;

public class CollisionList<K,V> implements Dictionary<K,V>{

    DoublyLinkedList<Entry<K,V>> list;

    public CollisionList(){
        list = new DoublyLinkedList<Entry<K,V>>();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public V find(K key) {
        Iterator<Entry<K, V>> it = iterator();
        while(it.hasNext()){
            Entry<K, V> entry = it.next();
            if (key.equals(entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V insert(K key, V value) {
        int pos = searchIndex(key);
        V oldValue = null;
        if (pos == -1){
            list.addLast(new EntryClass<K,V>(key,value));
        }
        else{
            oldValue = list.get(pos).getValue();
            list.get(pos).setValue(value);
        }
        return oldValue;
    }

    @Override
    public V remove(K key) {
        int pos = searchIndex(key);
        V oldValue = null;
        if (pos == -1){
            return oldValue;
        }
        else{
            oldValue = list.get(pos).getValue();
            list.remove(pos);
        }
        return oldValue;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return list.iterator();
    }

    private int searchIndex(K key) {
        Iterator<Entry<K, V>> it = iterator();
        for (int i = 0; it.hasNext(); i++) {
            Entry<K, V> entry = it.next();
            if (key.equals(entry.getKey())){
                return i;
            }
        }
        return -1;
    }

}
