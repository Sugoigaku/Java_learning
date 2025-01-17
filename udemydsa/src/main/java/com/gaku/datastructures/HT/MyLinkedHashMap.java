package com.gaku.datastructures.HT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyLinkedHashMap<K, V> {
    // create Node
    private static class Node<K, V> {
        K key;
        V val;
        Node<K, V> prev, next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private final Node<K, V> head, tail;

    // store nodes in the map
    private final HashMap<K, Node<K, V>> map = new HashMap<>();

    public MyLinkedHashMap() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    // add/udate
    public void put(K key, V val) {

        if (!map.containsKey(key)) {
            Node<K, V> node = new Node<K, V>(key, val);
            addLastNode(node);
            map.put(key, node);
            return;
        }

        // if the key exsists, update to new value
        map.get(key).val = val;
    }

    // remove
    public boolean remove(K key) {
        if (!map.containsKey(key)) {
            return false;
        }
        Node<K, V> node = map.get(key);
        removeNode(node);
        map.remove(key);

        return true;
    }

    // get
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            return node.val;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            keyList.add(p.key);
        }
        return keyList;
    }

    public int size() {
        return map.size();
    }

    // tool method
    private void addLastNode(Node<K, V> newNode) {
        Node<K, V> temp = tail.prev;

        newNode.next = tail;
        newNode.prev = temp;
        // temp <- newNode -> tail

        tail.prev = newNode;
        temp.next = newNode;
        // temp <-> newNode <-> tail
    }

    private void removeNode(Node<K, V> xNode) {
        Node<K, V> prev = xNode.prev;
        Node<K, V> next = xNode.next;
        // prev <-> xNode <-> next

        prev.next = next;
        next.prev = prev;

        xNode.prev = xNode.next = null;
    }

    public static void main(String[] args) {
        MyLinkedHashMap<String, Integer> map = new MyLinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);

        System.out.println(map.keys()); // [a, b, c, d, e]
        map.remove("c");
        System.out.println(map.keys()); // [a, b, d, e]
    }
}
