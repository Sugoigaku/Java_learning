package com.gaku.datastructures.LL;

public class Main {
    public static void main(String[] args) {

        // LinkedList myLinkedList = new LinkedList(0);
        // myLinkedList.append(1);
        // myLinkedList.append(2);
        // myLinkedList.append(3);


        // System.out.println(myLinkedList.get(3).value);

        // LinkedList myLinkedList = new LinkedList(1);
        // myLinkedList.append(3);

        // System.out.println("LL before insert():");
        // myLinkedList.printList();

        // myLinkedList.insert(1, 2);

        // System.out.println("\nLL after insert(2) in middle:");
        // myLinkedList.printList();

        // myLinkedList.insert(0, 0);

        // System.out.println("\nLL after insert(0) at beginning:");
        // myLinkedList.printList();

        // myLinkedList.insert(4, 4);

        // System.out.println("\nLL after insert(4) at end:");
        // myLinkedList.printList();

        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        myLinkedList.append(5);

        System.out.println("LL before remove():");
        myLinkedList.printList();

        System.out.println("\nRemoved node:");
        System.out.println(myLinkedList.remove(2).value);
        System.out.println("LL after remove() in middle:");
        myLinkedList.printList();

        System.out.println("\nRemoved node:");
        System.out.println(myLinkedList.remove(0).value);
        System.out.println("LL after remove() of first node:");
        myLinkedList.printList();

        System.out.println("\nRemoved node:");
        System.out.println(myLinkedList.remove(2).value);
        System.out.println("LL after remove() of last node:");
        myLinkedList.printList();

    }
}
