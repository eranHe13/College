package il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T>{
    private int size = 0;
    FullBinaryTree(T k) {
        super(k);
        size = 1;
    }

    FullBinaryTree(T k, BinaryTreeI<T> l, BinaryTreeI<T> r) {
        super(k, l, r);
        if( (l == null && r != null) ||  (l != null && r == null) ){
            throw new IllegalArgumentException();
        }
        super.setLeft(l);
        super.setRight(r);
        int size_r = 0,  size_l = 0;
        if(l != null){
            size_l = l.size();
            size_r = r.size();
        }
        size = size_l +size_r + 1;
    }

    @Override
    public void setLeft(BinaryTreeI<T> l){
        if(super.getRight() == null && l == null)
            return;
        if( super.getRight() == null && l != null)
            throw new IllegalArgumentException();
        if(!(l instanceof FullBinaryTree))
            throw new IllegalArgumentException();
        super.setLeft(l);
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if(super.getLeft() == null && right == null){
            return;
        }
        if(super.getLeft() == null  && right != null|| !(right instanceof FullBinaryTree))
            throw new IllegalArgumentException();
        super.setRight(right);    }

    @Override
    public int size(){
        return size;
    }
}
