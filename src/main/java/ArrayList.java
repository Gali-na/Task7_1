import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    T[] arry;
    int elementArry = 10;
    int element;

    public ArrayList() {
        arry = (T[]) new Object[elementArry];
        element = 0;
    }

    public ArrayList(int size) {
        arry = (T[]) new Object[size];
        element = 0;
    }

    void checkingArraySize() {
        if (arry.length == element) {
            T[] tempArry = (T[]) new Object[arry.length + 5];
            System.arraycopy(arry, 0, tempArry, 0, arry.length);
            arry = (T[]) new Object[tempArry.length];
            System.arraycopy(tempArry, 0, arry, 0, tempArry.length);
        }
    }

    @Override
    public void add(T value) {
        checkingArraySize();
        arry[element] = value;
        element++;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexInBounds(index);
        checkingArraySize();
        System.arraycopy(arry, index, arry, index + 1, element - index);
        arry[index] = value;
        element++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        verifyIndexInBounds(index);
        if (index > element || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arry[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndexInBounds(index);
        checkingArraySize();
        T[] tempArry = (T[]) new Object[arry.length];
        int j = 0;
        for (int i = 0; i < arry.length; ) {
            if (i == index) {
                tempArry[j] = value;
                j++;
                i++;
            }
            tempArry[j] = arry[i];
            j++;
            i++;
        }
        arry = (T[]) new Object[tempArry.length];
        System.arraycopy(tempArry, 0, arry, 0, tempArry.length);
    }

    @Override
    public T remove(int index) {
        T valueHolder = arry[index];
        System.arraycopy(arry, index + 1, arry, index, element - index - 1);
        arry[--element] = null;
        return valueHolder;
    }

    @Override
    public T remove(T t) {

        for (int i = 0; i < element; i++) {
            if (t == arry[i] || (t != null && t.equals(arry[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element '" + t + "' not found in the array");
    }

    @Override
    public int size() {
        return element;
    }

    @Override
    public boolean isEmpty() {
        return element == 0;
    }

    private void verifyIndexInBounds(int index) {
        if (index < 0 || index >= element) {
            throw new ArrayIndexOutOfBoundsException("Illegal index: '" + index + "' .");
        }
    }
}
