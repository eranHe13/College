package il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack <T>{
    private DLinkedList<T> stack ;
    T top ;

    DLinkedListStack(){
        stack = new DLinkedList<T>();
        top = null;
    }
    @Override
    public void push(T o) {
        top = o;
        stack.insert(o);
    }

    @Override
    public T pop() {
        if(isEmpty()){
            return null;
        }
        stack.goToEnd();
        T obj = stack.remove();
        stack.goToEnd();
        top = stack.getCursor();
        return obj;
    }

    @Override
    public T top() {
        return top;
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        if (stack.isEmpty()){
            return "[]";
        }
        String retStr = "[";

        while( stack.hasPrev()){
            retStr += stack.getCursor().toString()+", ";
            stack.getPrev();
        }
        stack.getPrev();
        retStr += stack.getCursor().toString()+ "]";
        stack.goToEnd();
        return retStr;
    }
}
