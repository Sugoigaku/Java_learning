package com.gaku.datastructures.LL;

public class DLinkedList {

    class DLinkedListNode {
        int val;
        DLinkedListNode next,prev;
        DLinkedListNode(int val) { this.val = val;}
    }

    DLinkedListNode head, tail;
    int length;

    public DLinkedList(int[] arr) {
        this.createDLinkedList(arr);
    }

    public DLinkedListNode createDLinkedList(int[] arr){
        if (arr == null || arr.length == 0) {
            return null;
        }

        head = new DLinkedListNode(arr[0]);
        DLinkedListNode cur = head;

        for (int i = 1; i < arr.length; i++) {
            DLinkedListNode newNode = new DLinkedListNode(arr[i]);
            cur.next = newNode;
            newNode.prev = cur;
            cur = cur.next;
            if (i == arr.length - 1) {
                tail = cur;
            }            
        }

        return head;
    }

    public void printList() {
        DLinkedListNode temp = this.head;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public void reversePrintList() {
        DLinkedListNode temp = this.tail;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.prev;
        }
    }
}
