package com.gaku.datastructures.LL;

public class LinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node node = new Node(value);
        this.head = node;
        this.tail = node;
        this.length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0)
            return null;

        Node temp = head;
        Node pre = head;

        // using two pointers pre always in front of temp
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }

        tail = pre;
        tail.next = null;
        length--;

        // if length == 0, then set head and tail to null
        if (length == 0) {
            head = null;
            tail = null;
        }

        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);

        newNode.next = head;
        head = newNode;
        length++;

        if (length == 1) {
            tail = newNode;
        }
    }

    public Node removeFirst() {
        if (length == 0)
            return null;

        Node temp = head;
        head = head.next;
        // the returned node.next should bue null
        temp.next = null;
        length--;

        if (length == 0)
            tail = null;

        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length)
            return null;
        Node temp = head;

        // int count = 0;

        // while (count < index) {
        // temp = temp.next;
        // count++;
        // }
        for (int count = 0; count < index; count++) {
            temp = temp.next;
        }

        return temp;
    }

    public boolean set(int index, int value) {
        // 1. find the node -> using get method
        // 2. set value
        Node temp = this.get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }

        return false;
    }

    public boolean insert(int index, int value) {

        if (index < 0 || index > length)
            return false;

        if (index == 0) {
            this.prepend(value);
            return true;
        }

        if (index == length) {
            this.append(value);
            return true;
        }

        Node newNode = new Node(value);
        // Node pre = head;

        // for (int i = 0; i < index - 1; i++) {
        //     pre = pre.next;
        // }

        // newNode.next = pre.next;
        // pre.next = newNode;
        // length++;
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;

        return true;
    }

    public Node remove(int index) {
        // index should not equal with length
        if (index < 0 || index >= length) return null;

        if (index == 0) return removeFirst();
        if (index == length-1) return removeLast();

        Node pre = get(index-1);
        Node temp = pre.next;
        pre.next = pre.next.next;
        length--;
        return temp;
    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;

        Node after = temp.next;
        Node before = null;

        for (int i = 0; i < length; i++) {
            // temp.next = before;
            // before = temp;
            // temp = after;
            // after = after.next;
            temp.next = after;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }
}
