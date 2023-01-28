package il.ac.telhai.ds.trees;

public class BinaryTree<T> implements  BinaryTreeI<T>{
    private T key;
    private BinaryTreeI<T> left;
    private BinaryTreeI<T> right;
    private int size;


    BinaryTree(T k){
        key = k;
        left = null;
        right = null;
        size = 1;
    }

    BinaryTree(T k , BinaryTreeI<T> l  , BinaryTreeI<T> r ){
        key = k;
        right = r;
        left = l;
        int size_r = 0;
        int size_l = 0 ;
        if(left != null){
            size_l = left.size();
        }
        if(right != null){
            size_r = right.size();
        }
        size = size_l + size_r + 1;
    }

    /**
     * @return the left subtree
     */
    @Override
    public BinaryTreeI<T> getLeft() {
        return left;
    }

    /**
     * @return the right subtree
     */
    @Override
    public BinaryTreeI<T> getRight() {
        return right;
    }

    /**
     * @return the value in the root
     */
    @Override
    public T getValue() {
        return key;
    }

    /**
     * @param value set the value in the root
     */
    @Override
    public void setValue(T value) {
        key = value;
    }

    /**
     * @param left set the left subtree
     */
    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.left = left;
    }

    /**
     * @param right set the right subtree
     */
    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.right = right;
    }

    /**
     * @return if it is a leaf or not.
     */
    @Override
    public boolean isLeaf() {
        return (right == null) && (left == null);
    }

    /**
     * @return the height of the tree, i.e. the length of a longest path starting
     * from the root.
     */
    @Override
    public int height() {
       if(isLeaf()){
           return 0;
       }
       int rightHeight = 0;
       int leftHeight = 0;
       if(left!= null){
           leftHeight = left.height();
       }
       if(right!= null){
           rightHeight = right.height();
       }
       int max = Math.max(rightHeight,leftHeight);
       return (max+1);
    }

    /**
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * clears all the tree except its root.
     */
    @Override
    public void clear() {
        right = null;
        left = null;
        size = 1;

    }

    /**
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in preorder fashion, where adding a " " before and
     * a " " after each value in the tree.
     */
    @Override
    public String preOrder() {
        return preOrder(" " , " ");
    }

    /**
     * @param separationBeforeVal
     * @param separationAfterVal
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in preorder fashion, where adding a
     * "separationBeforeVal" before each value and a
     * "separationAfterVal" after each value in the tree.
     */
    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        str.append(separationBeforeVal+key.toString()+separationAfterVal);
        if(isLeaf())
            return separationBeforeVal+key.toString()+separationAfterVal;
        if(left != null) {
            str.append(left.preOrder(separationBeforeVal,separationAfterVal));
        }
        if(right != null) {
            str.append(right.preOrder(separationBeforeVal,separationAfterVal));
        }
        return str.toString();


    }

    /**
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in inorder fashion, where adding a " " before and
     * a " " after each value in the tree.
     */
    @Override
    public String inOrder() {
        return inOrder(" ", " " );
    }

    /**
     * @param separationBeforeVal
     * @param separationAfterVal
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in inorder fashion, where adding a
     * "separationBeforeVal" before each value and a
     * "separationAfterVal" after each value in the tree.
     */
    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        if(isLeaf())
            return separationBeforeVal +key.toString() + separationAfterVal;
        if( left != null) {
            str.append(left.inOrder(separationBeforeVal,separationAfterVal));
        }
        str.append(separationBeforeVal+key.toString()+separationAfterVal);
        if(right != null) {
            str.append(right.inOrder(separationBeforeVal,separationAfterVal));
        }
        return str.toString();
    }

    /**
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in postorder fashion, where adding a " " before
     * and a " " after each value in the tree.
     */
    @Override
    public String postOrder() {
        return postOrder(" ", " ");
    }

    /**
     * @param separationBeforeVal
     * @param separationAfterVal
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in postorder fashion, where adding a
     * "separationBeforeVal" before each value and a
     * "separationAfterVal" after each value in the tree.
     */
    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        if(isLeaf())
            return separationBeforeVal+key.toString()+separationAfterVal;
        if(left != null) {
            str.append(left.postOrder(separationBeforeVal,separationAfterVal));
        }
        if( right != null) {
            str.append(right.postOrder(separationBeforeVal,separationAfterVal));
        }
        str.append(separationBeforeVal+key.toString()+separationAfterVal);
        return str.toString();
    }
}
