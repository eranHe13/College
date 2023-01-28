package il.ac.telhai.ds.hash;

import il.ac.telhai.ds.linkedlist.DLinkedList;

public class HashTable<V> {
	public static final int DEF_MAX_HASH_SIZE = 10;
	private DLinkedList<V>[] map ;
	private int size;
	int sellsConter;

	/**
	 * c'tor
	 * construct a hash-table of max-size "DEF_MAX_HASH_SIZE".
	 */
	public HashTable() {
		this(DEF_MAX_HASH_SIZE);
	}
	
	/**
	 * construct a hash-table of size 'hashSize'.
	 * @param hashSize, the size of the constructed hash-table.
	 * O(size)
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public HashTable (int hashSize) {
		map = new DLinkedList[hashSize];
		size = hashSize;
		sellsConter = 0;
		for(int i = 0 ; i < size ; i++){
			map[i] = new DLinkedList<V>();
		}
	}
	
	/**
	 * @param val
	 * @return true if the hash-table contains val, otherwise return false
	 *O(1) - average because the hash table
	 */
	public boolean contains(V val) {
		int location = Math.abs(val.hashCode()%size);
		DLinkedList tmp = map[location];
		tmp.goToBeginning();
		while(tmp.hasNext()){
			if (val.equals(tmp.getCursor())){
				return true;
			}
			tmp.getNext();
		}
		if (val.equals(tmp.getCursor())){
			return true;
		}
		return false;
	}

	/**
	 * Add val to the hash-table.
	 * 
	 * @param val
	 * @return If the val has already exist in the the hash-table, it will not be
	 *         added again. Return true if the val was added successfully. Otherwise
	 *         return false.
	 *O(1) - average because the hash table
	 */
	public boolean add(V val) {
		int location = Math.abs(val.hashCode()%size);
		DLinkedList<V> tmp = map[location];
		if(map[location].getCursor() == null){
			sellsConter++;
		}
		tmp.goToBeginning();
		while(tmp.hasNext() ){
			if (val.equals(tmp.getCursor())){
				return false;
			}
			tmp.getNext();
		}
		if (val.equals(tmp.getCursor())){
			return false;
		}
		map[location].insert(val);
		return true;
	}

	/**
	 * Remove val from the hash-table.
	 * 
	 * @param val
	 * @return Return true if the val was removed successfully. Otherwise return
	 *         false.
	 */
	public boolean remove(V val) {
		int location = Math.abs(val.hashCode()%size);
		DLinkedList tmp = map[location];
		tmp.goToBeginning();
		while(tmp.hasNext() ){
			if (val.equals(tmp.getCursor())){
				map[location].remove(val);
				map[location].goToBeginning();
				if(map[location].getCursor() == null){sellsConter--;}
				return true;
			}
			tmp.getNext();
		}
		if (val.equals(tmp.getCursor())){
			map[location].remove(val);
			map[location].goToBeginning();
			if(map[location].getCursor() == null){sellsConter--;}
			return true;
		}
		return false;
		// TODO add your implementation.
	}

	/**
	 * clear al the data in the hash-table
	 */
	public void clear() {
		for(int i = 0 ; i< size ; i ++){
			map[i].clear();
		}
		sellsConter=0;
		// TODO add your implementation.
	}

	/**
	 * @return true if the hase-table is empty, otherwise return false.
	 */
	public boolean isEmpty() {
		return sellsConter == 0 ;
		// TODO add your implementation.
	}
}