package il.ac.telhai.ds.heap;

public class MinHeap<T extends Comparable<T>> {
	private T heap[];
	private int size ;
	private int elemCount;


	// create new heap by size;
	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(int length) {
		heap = (T[]) new Comparable[length];
		size = length;
		elemCount = 0;
	}

	// create new heap by array
	public MinHeap(T[] arr) {
		heap = arr;
		size = heap.length;
		elemCount = size;
		for (int i = size-1 ; i >= 0 ; i--){
			heapifyDown(i); // try to sift up each element  - insure heap
		}
	}

	public boolean isFull() {
		return elemCount == size;
	}

	public boolean isEmpty() {
		return elemCount == 0;
	}

	// add new element to the heap;
	public void insert(T element) {
		if(isFull()){
			throw new RuntimeException();
		}
		heap[elemCount] = element; //  put in the last location
		elemCount++; // increase +1
		heapifyUp(elemCount-1); // try to sift up if possible
	}

	public T getMin() {
	if(isEmpty()){
		throw new RuntimeException();
	}
	return heap[0];
	}

	private int left(int i){
		return (i+1)*2 - 1;
	}

	private int right(int i){
		return (i+1)*2;
	}

	private int parent(int i){
		int res = (i + 1)/2;
		return res - 1;
	}

	// the min is in the root - put the last elem in the root sift down him
	public T deleteMin() {
		if(isEmpty()){
			return null;
		}
		T min = heap[0]; // minimum
		heap[0] = heap[--elemCount]; //put the last elem in the root
		heapifyDown(0);// try to siftdown
		return min;
	}

	private void heapifyDown(int i ){
		int s = 0 ; // tmp index to the smallest elem;
		T tmp; // tmp element;
		// if the left son is not the last elem and the left son is smaller than the parent
		if(left(i) < elemCount && heap[left(i)].compareTo(heap[i]) < 0){
			s = left(i); // save the index
		}
		else { //
			s = i; // the current elem is smaller than left child
		}
		// if the right son is not the last elem and the right son is smaller than the parent
		if(right(i) < elemCount && heap[s].compareTo(heap[right(i)]) > 0){
			s = right(i);
		}
		if(s != i ){ // if need a swap
			tmp = heap[i];
			heap[i] = heap [s];
			heap[s] = tmp;
			heapifyDown(s); // keep try to sift down
		}
	}



	private void heapifyUp(int i){
		if(i <= 0) // if is the root cant go up
			return;
		int smallest = 0;
		T temp;
		if(parent(i) >= 0 && heap[parent(i)].compareTo(heap[i]) > 0){
			smallest = i;
		}else {
			smallest = parent(i);
		}
		if(smallest == i){
			temp = heap[parent(i)];
			heap[parent(i)] = heap[i];
			heap[i] = temp;
			heapifyUp(parent(i));
		}
	}

	/**
	 * return a string represents the heap. The order of the elements are according
	 * to The string starts with "[", ends with "]", and the values are seperated
	 * with a comma
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < elemCount; i++){
			if(i < elemCount - 1)
				sb.append(heap[i].toString() + ",");
			else
				sb.append(heap[i].toString());
		}
		sb.append("]");
		return sb.toString();	}
}
