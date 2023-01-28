package il.ac.telhai.ds.trees;

public class BinarySearchTree<T extends Comparable<T>> {

	BstNode root;
	int size;

	// Binary Search Tree Node
	class BstNode {
		T val;
		BstNode left, right;

		public BstNode(T val) {
			this.val = val;
			left = null;
			right = null;
		}
	}

	public enum Direction {
		LEFT, RIGHT
	}

	// Returns the val given a path from the root.
	// Used for testing. DO NOT DELETE.
	public T getValInPath(Direction... direction) {
		BstNode node = root;
		for (Direction d : direction) {
			if (d.equals(Direction.LEFT) && node.left != null)
				node = node.left;
			else if (d.equals(Direction.RIGHT) && node.right != null)
				node = node.right;
			else
				return null;
		}
		return node.val;
	}

	/**
	 * Constructs an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		root= null;
	}

	/**
	 * returns the number of elements in the tree
	 * 
	 * @param
	 */
	public int size() {
		return size;
		// Complete this code.
	}

	/**
	 * Adds the object value to the tree as a leaf according to the parameter.
	 * 
	 * @param val
	 * @return True, if the element was added. Otherwise false.
	 */
	public boolean add(T val) {
		try{
			root = addrec( root ,val);

			size++;
			return true;
		}
		catch (Exception e ){
			return false;
		}

	}

	public BstNode addrec(BstNode node, T InVal) {
		if(node == null){
			return new BstNode(InVal);
		}
		int resCompare = InVal.compareTo(node.val);
		if(resCompare == 0){
			throw new IllegalArgumentException();
		}
		if(resCompare == -1){
			if(node.left != null){
				node.left = addrec(node.left,InVal);
			}
			else{
				node.left = new BstNode(InVal);
			}
		} else if (resCompare == 1) {
			if(node.right != null){
				node.right = addrec(node.right,InVal);
			}
			else{
				node.right = new BstNode(InVal);
			}

		}
		return node;

	}



	/**
	 * Removes the object in the tree which is equal to the parameter. 
	 * Nothing is done if not found
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return True, if the element was removed. Otherwise false.
	 */
	public boolean remove(T val) {
		try {
			root = removeRec(root, val);
			size--;
			return true;
		}
		catch (Exception e){return false;}

	}

	public BstNode removeRec(BstNode node , T InVal){
		if(node == null){
			throw new IllegalArgumentException();
		}
		int compareVal = InVal.compareTo(node.val);
		if(compareVal == -1){
			node.left = removeRec(node.left , InVal);
		}
		else if(compareVal == 1){
			node.right  = removeRec(node.right , InVal);
		}
		else {
			if(node.left == null){
				return node.right;
			}
			else if(node.right == null){
				return node.left;
			}
			node.val = getSuccessor(node.right).val;
			node.right = removeRec(node.right, node.val);
		}
		return node;
	}

	public BstNode getSuccessor(BstNode node){
		if(node.left == null){
			return node;
		}
		return getSuccessor(node.left);
	}

	/**
	 * Looks for an object which is equal to the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return true if the tree contains this object. Otherwise, return false.
	 */
	public boolean contains(T val) {
		BstNode tmp = root;
		while (tmp != null){
			int resCompare = val.compareTo(tmp.val);
			if(resCompare == 0){
				return true;
			}
			if(resCompare == -1){
				tmp = tmp.left;
				continue;
			}
			else if(resCompare == 1){
				tmp = tmp.right;
				continue;
			}
		}
		return false;
	}
	public boolean isLeaf(){
		return root.left==null && root.right ==null;
	}

	/**
	 * Looks for the minimal object in the tree, which is greater than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findGe(T val) {
		BstNode node = root;
		BstNode res = null;
		while (node != null){
			int resCompare = node.val.compareTo(val);
			if(resCompare == 1){
				res = node;
				node = node.left;
			}
			else if(resCompare == 0){
				res = node;
				break;
			}
			else{ node = node.right;}
		}
		if(res != null){
			return res.val;
		}
		return null;

	}

	public BstNode findGeRec(BstNode node , T InVal){
		if(node == null){
			return null;
		}
		int resCompare = node.val.compareTo(InVal);
		if(resCompare == 1){
			BstNode resL = findGeRec(node.left , InVal);
			if(resL != null){
				return resL;
			}
		}
		BstNode resR = findGeRec(node.right , InVal);
		if(resR != null){
			return resR;
		}
		if(resCompare == 0 || resCompare == 1 ){
			return node;
		}
		return null;
	}

}
