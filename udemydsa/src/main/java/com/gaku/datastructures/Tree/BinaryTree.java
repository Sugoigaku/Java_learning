package com.gaku.datastructures.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<V> {

    private static class TreeNode<V> {
        V val;
        TreeNode<V> left;
        TreeNode<V> right;
        TreeNode(V val) {
            this.val = val;
        }
    }

    private TreeNode<V> root;
    private ArrayList<V> preorderResult = new ArrayList<>();
    private ArrayList<V> inorderResult = new ArrayList<>();
    private ArrayList<V> postorderResult = new ArrayList<>();

    public void dfs(String order) {
        traverse(root);
        switch (order) {
            case "preorder":
                System.out.println(preorderResult);
                break;
            case "inorder":
                System.out.println(inorderResult);
                break;
            case "postorder":
                System.out.println(postorderResult);
                break;
            default:
                break;
        }
        preorderResult.clear();
        inorderResult.clear();
        postorderResult.clear();
    }

    private void traverse(TreeNode<V> node){
        if (node == null) {
            return;
        }

        preorderResult.add(node.val);

        traverse(node.left);
        
        inorderResult.add(node.val);

        traverse(node.right);

        postorderResult.add(node.val);
    }

    public void buildTree(V[] array){
        if (array == null || array.length == 0) {
            return; // Return for an empty array
        }

        if (array[0] == null) {
            throw new IllegalStateException("the root node can't be null");
        }

        root = new TreeNode<V>(array[0]); // Root of the tree
        Queue<TreeNode<V>> queue = new LinkedList<>();
        queue.add(root); // Add the root to the queue

        int i = 1;
        while (i < array.length) {
            TreeNode<V> current = queue.poll();

            // Add the left child
            if (i < array.length && array[i] != null) {
                current.left = new TreeNode<V>(array[i]);
                queue.add(current.left);
            }
            i++;

            // Add the right child
            if (i < array.length && array[i] != null) {
                current.right = new TreeNode<V>(array[i]);
                queue.add(current.right);
            }
            i++;
        }
    }

    public static void main(String[] args) {
        Integer[] array = { 1, 2, 3, null, 4, 5, null };
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.buildTree(array);
        tree.dfs("preorder");
        tree.dfs("inorder");
        tree.dfs("postorder");
    }
}
