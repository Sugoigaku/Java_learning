package com.gaku.datastructures.Array;

public class CycleArray<T> {
    private T[] arr;
    // Assume range is [start,end)
    // start points to the index of the first valid data
    // end points to the index+1 of the last valid data
    private int start;
    private int end;
    // the array size;
    private int size;
    // how many data in the array
    private int count;

    public CycleArray() {
        this(1);
    }

    @SuppressWarnings("unchecked")
    public CycleArray(int size) {
        this.size = size;
        this.arr = (T[]) new Object[size];
        this.start = 0;
        this.end = 0;
        this.count = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        // Create a new array of the specified size.
        T[] newArr = (T[]) new Object[newSize];
        // You don't need copy all array, just copy the valid data
        for (int i = 0; i < count; i++) {
            newArr[i] = arr[(start + i) % size];
        }
        arr = newArr;
        start = 0;
        end = count;
        size = newSize;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public void addFirst(T val) {
        // Resize the array to twice its current size when it is full.
        if (isFull()) {
            resize(size * 2);
        }

        // Because the start points to the first valid data, we need to move it first.
        // Don't forget to add +size, because when start == 0, start - 1 = -1, which is not the desired index.
        start = (start - 1 + size) % size;
        arr[start] = val;
        count++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty");
        }

        arr[start] = null;
        start = (start + 1) % size;
        count--;

        if (count > 0 && count == size / 4) {
            resize(size / 2);
        }
    }

    public void addLast(T val) {
        if (isFull()) {
            resize(size * 2);
        }

        arr[end] = val;
        end = (end + 1) % size;
        count++;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty");
        }

        // Don't forget to add +size, because when end == 0, end - 1 = -1, which is not the desired index.
        end = (end - 1 + size) % size;
        arr[end] = null;
        count--;

        if (count > 0 && count == size / 4) {
            resize(size / 2);
        }
    }

    // 获取数组头部元素，时间复杂度 O(1)
    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty");
        }
        return arr[start];
    }

    // 获取数组尾部元素，时间复杂度 O(1)
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty");
        }
        // end 是开区间，指向的是下一个元素的位置，所以要减 1
        return arr[(end - 1 + size) % size];
    }

    public int size(){
        return count;
    }

    public static void main(String[] args) {
        CycleArray<Integer> cycleArray = new CycleArray<Integer>();

        cycleArray.addLast(1);
        cycleArray.addLast(2);

        cycleArray.addFirst(3);
        cycleArray.addFirst(4);

        System.out.println(cycleArray.getFirst());
        System.out.println(cycleArray.getLast());
        System.out.println(cycleArray.size);

        cycleArray.addFirst(5);

        cycleArray.removeLast();
        cycleArray.removeFirst();
        cycleArray.removeLast();
        cycleArray.removeLast();

        System.out.println(cycleArray.getFirst());
    }

}
