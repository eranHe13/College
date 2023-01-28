package il.ac.telhai.ds.trees;
// Eran Helvitz

public class AVLTree <T extends Comparable<T>>{
    AVLTree<T> left; // left son
    AVLTree<T> right; // right son
    int size; // counter of the amount of node under and include him
    int height; // counter of the node height - the longest rail from him.
    T val; // object

    public AVLTree(T value){
        val = value;
        left = right = null;
        size = 1;
        height = 0;
    }
    // check if the node doesn't have sons
    public boolean isLeaf(){
        return left==null && right ==null;
    }

    // update the size of the node  = left_son.size + right_son.size + 1
    // if its leaf --> size = 1
    private int updateSize(){
        int size_l = 0, size_r = 0;
        if(isLeaf()){
            size = 1;
            return size;
        }
        if(left != null){
            size_l = left.size;
        }if(right != null){
            size_r = right.size;
        }
        size = size_l + size_r + 1;
        return size;
    }

    // update the node height = MAX(left_son.height , right_son.height) + 1
    // if its leaf --> height = 0
    private  int updateHeight(){
        int L_height = -1;
        int R_height = -1;
        if( isLeaf()){
            this.height = 0;
            return 0;
        }
        if(left != null){
            L_height = left.height;

        }
        if(right != null){
            R_height = right.height;
        }
        height = Math.max(L_height , R_height) + 1;
        return height;
    }

    // return the object
    public T getValue(){
        return val;
    }

    // return the left son
    public AVLTree<T> getLeft(){
        return left;
    }

    // return the right son
    public AVLTree<T> getRight() {
        return right;
    }

    // insert object to the tree
    public AVLTree<T> add(T value) {
        // if the object all ready exist -- > not legal
        if (value.compareTo(getValue()) == 0) {
            throw new IllegalArgumentException();
        }
        // find object his place

        if (value.compareTo(getValue()) > 0) {//--> if the object is bigger than the current node -- >  go right
            if (right != null) {// if the current has right son
                right = right.add(value); // - refunc with the right subtree
                // the new node has added in the right subtree
                updateSize();
                updateHeight();
                // if right subtree his higher than the left subtree
                if(checkBalance() == -2){
                    // if right son right-subtree his higher than the right son left-subtree
                    if(right.checkBalance() < 0 ){
                        RIGHT_RIGHT();  /// -- > right right case
                    }
                    // if right son left-subtree his higher than the right son right-subtree
                    else if(right.checkBalance() == 1){
                        RIGHT_LEFT();/// -- > right left case
                    }
                }
                // the node is balance
                return this;
            }
            right = new AVLTree<T>(value); //  if the right son his null --> create new node.
            updateSize();
            updateHeight();
            return this;
        }

        // the new object his smaller than the current node --> go left
        if (left != null) {// if the current has left son
            left = left.add(value); // refunc with the left subtree
            updateSize();
            updateHeight();
            // if the left subtree is higher than the right subtree
            if(checkBalance() == 2){
                // if the left son subtree its balance or the left-subtree is higher than the right-subtree
                if(left.checkBalance() >= 0){
                    LEFT_LEFT(); /// -- > left left case
                }else if(left.checkBalance() == -1){
                    LEFT_RIGHT(); /// -- > left right case
                }
            }
            return this;
        }
        left = new AVLTree<T>(value); ////  if the left son his null --> create new node.
        updateSize();
        updateHeight();
        return this;
    }

    // first doing right-right case on the left son then left-left case on the current node
    private void LEFT_RIGHT() {
        left.RIGHT_RIGHT();
        LEFT_LEFT();
    }
    // first doing left-left case on the right son then right-right case on the current node
    private void RIGHT_LEFT() {
        right.LEFT_LEFT();
        RIGHT_RIGHT();
    }
    // left left case
    private void LEFT_LEFT() {
        AVLTree<T> temp = right; // put right-subtree in  tmp-node
        T tempVal = val;         // copy the object of the current-node
        val = left.val;          // put left-son-val on the current-node-val
        left.val = tempVal;      // put current-node-val in left-son-val
        this.right = left;       // put left-subtree of the current-node in the right-son
        left = right.left;       // put right-left-subtree of the current-node in the left-son
        right.left = right.right;// put right-right-subtree of the current-node in the right-left-son
        right.right = temp;      // put tmp-node(the right-son before the swaps) of the current-node in the right-right-son
        right.updateHeight();
        updateHeight();
        right.updateSize();
        updateSize();
    }
    // right right case
    private void RIGHT_RIGHT() {
        AVLTree<T> temp = left; // put left-subtree in tmp-node
        T tempVal = val;        // copy the object of the current-node
        val = right.val;        // put right-son-val on the current-node-val
        right.val = tempVal;    // put current-node-val in right-son-val
        this.left = right;      // put right-subtree in left-son
        right = left.right;     // put the left-right-subtree in the right son
        left.right = left.left; // put the left-left-subtree in the left-right-subtree
        left.left = temp;       // put the tmp-node(the left-son before the swaps) in the left-left-son
        left.updateHeight();
        updateHeight();
        left.updateSize();
        updateSize();
    }

    // check balance in node --> balance should be between -1 - 1
    // balance  = left_height - right_height
    // if its leaf --> height = 0
    private int checkBalance() {
        int left_height = -1;
        int right_height = -1;

        if(left != null){
            left_height = left.height;
        }
        if(right != null){
            right_height = right.height;
        }
        return left_height - right_height;
    }
}

