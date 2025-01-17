package com.gaku.datastructures.HT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

// this data structure is for randomly getting a key
public class MyArrayHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // create a mapping relationship to Key and Index of arr, for implement O(1) get 
    private final HashMap<K, Integer> map = new HashMap<>();
    // 真正存储 key-value 的数组
    private final ArrayList<Node<K, V>> arr = new ArrayList<>();

    private final Random r = new Random();

    // get
    public V get(K key) {
        // if it doesn't contain the key, return null
        if (!map.containsKey(key)) {
            return null;
        }
        int index = map.get(key);
        return arr.get(index).val;
    }

    // add/update
    public void put(K key, V val){
        // if it doesn't contain the key, add it
        if (!map.containsKey(key)) {
            // always add to the last place of array
            arr.add(new Node<K,V>(key, val));
            map.put(key, arr.size() - 1);
            return;
        }

        // if the key exsit, update
        int index = map.get(key);
        Node<K, V> node = arr.get(index);
        node.val = val;
    }

    // remove
    public void remove(K key) {
        // if the is not exsist, return
        if (!map.containsKey(key)) {
            return;
        }

        // find the node index
        int index = map.get(key);
        // remove it by moving the last node to that place
        Node<K, V> node = arr.get(arr.size()-1);
        arr.set(index, node);
        // modify map
        map.put(node.key, index);
        // relase the last node (because it has been copied)
        arr.remove(arr.size()-1);
        // remove from map
        map.remove(key);
    }

    // randomKey
    public K randomKey() {
        int n = arr.size();
        int randomIndex = r.nextInt(n);
        return arr.get(randomIndex).key;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }

    public static void main(String[] args) {
        MyArrayHashMap<Integer, Integer> map = new MyArrayHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);

        System.out.println(map.get(1)); // 1
        System.out.println(map.randomKey());

        map.remove(4);
        System.out.println(map.randomKey());
        System.out.println(map.randomKey());
    }
}
