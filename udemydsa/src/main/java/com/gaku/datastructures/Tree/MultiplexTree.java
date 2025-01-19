package com.gaku.datastructures.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MultiplexTree<V> {

    private TreeNode<V> root;

    private static class TreeNode<V> {
        V val;
        List<TreeNode<V>> children;

        TreeNode(V val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    private static class TreeNodeWithState<V> {
        TreeNode<V> node;
        int depth;

        TreeNodeWithState(TreeNode<V> node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // Method to build the tree from a HashMap iteratively
    public void buildTreeFromMap(HashMap<V, List<V>> map, V rootKey) {
        if (!map.containsKey(rootKey)) {
            return; // If rootKey is not in the map, return null
        }

        // Create the root node
        root = new TreeNode<V>(rootKey);

        // A map to track all created nodes by their values
        Map<V, TreeNode<V>> nodeMap = new HashMap<>();
        nodeMap.put(rootKey, root);

        // A queue for iterative tree building
        Queue<V> queue = new LinkedList<>();
        queue.add(rootKey);

        while (!queue.isEmpty()) {
            V currentKey = queue.poll();
            TreeNode<V> currentNode = nodeMap.get(currentKey);

            // Process the children of the current node
            List<V> children = map.getOrDefault(currentKey, new ArrayList<>());
            for (V childKey : children) {
                // Create the child node if it doesn't already exist
                TreeNode<V> childNode = nodeMap.computeIfAbsent(childKey, TreeNode::new);
                currentNode.children.add(childNode);

                // Add the child key to the queue for processing its children
                queue.add(childKey);
            }
        }
    }

    public void dfs() {
        traverse(root);
    }

    private void traverse(TreeNode<V> node) {
        // if null , return
        if (node == null) {
            return;
        }

        // // preorder
        // System.out.println(node.val);

        for (TreeNode<V> child : node.children) {
            traverse(child);
        }

        // postorder
        System.out.println(node.val);
    }

    public void bfs() {
        // levelOrderTraverse0(root);
        // levelOrderTraverse1(root);
        levelOrderTraverse2(root);
    }

    private void levelOrderTraverse0(TreeNode<V> node) {
        if (node == null) {
            return;
        }

        Queue<TreeNode<V>> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            TreeNode<V> cur = queue.poll();
            System.out.println(cur.val);

            for (TreeNode<V> child : cur.children) {
                queue.offer(child);
            }
        }
    }

    private void levelOrderTraverse1(TreeNode<V> node) {
        if (node == null) {
            return;
        }

        Queue<TreeNode<V>> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 1;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {

                TreeNode<V> cur = queue.poll();
                System.out.println("depth = " + depth + ", val = " + cur.val);

                for (TreeNode<V> child : cur.children) {
                    queue.offer(child);
                }
            }

            depth++;
        }
    }

    private void levelOrderTraverse2(TreeNode<V> node) {
        if (node == null) {
            return;
        }

        Queue<TreeNodeWithState<V>> queue = new LinkedList<>();
        queue.offer(new TreeNodeWithState<>(node, 1));

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {

                TreeNodeWithState<V> cur = queue.poll();
                TreeNode<V> treeNodeode = cur.node;
                int depth = cur.depth;
                System.out.println("depth = " + depth + ", val = " + treeNodeode.val);

                for (TreeNode<V> child : treeNodeode.children) {
                    queue.offer(new TreeNodeWithState<>(child, depth + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        MultiplexTree<String> mTree = new MultiplexTree<>();

        // Example HashMap representing the tree
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("A", Arrays.asList("B", "C", "D")); // A -> B, C, D
        map.put("B", Arrays.asList("E", "F")); // B -> E, F
        map.put("C", Arrays.asList("G")); // C -> G
        map.put("D", Arrays.asList()); // D has no children
        map.put("E", Arrays.asList("H", "I")); // E -> H, I

        // Build the tree with "A" as the root
        mTree.buildTreeFromMap(map, "A");

        // Print the tree structure
        // System.out.println("Tree Structure:");
        // mTree.dfs();
        mTree.bfs();
    }
}