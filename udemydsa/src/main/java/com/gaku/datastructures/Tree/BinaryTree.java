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

    private static class TreeNodeWithState<V> {
        TreeNode<V> node;
        int depth;
    
        TreeNodeWithState(TreeNode<V> node, int depth) {
            this.node = node;
            this.depth = depth;
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

        // need to optimization
        preorderResult.clear();
        inorderResult.clear();
        postorderResult.clear();
    }

    private void traverse(TreeNode<V> node) {
        if (node == null) {
            return;
        }

        preorderResult.add(node.val);

        traverse(node.left);

        inorderResult.add(node.val);

        traverse(node.right);

        postorderResult.add(node.val);
    }

    public void bfs() {
        // ArrayList<V> bfsResult = levelOrderTraverse0(root);
        // System.out.println(bfsResult);
        // levelOrderTraverse1(root);
        levelOrderTraverse2(root);
    }

    private ArrayList<V> levelOrderTraverse0(TreeNode<V> node) {
        // if node is null, return
        if (root == null) {
            return null;
        }

        ArrayList<V> result = new ArrayList<>();

        Queue<TreeNode<V>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<V> cur = queue.poll();
            result.add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
            }

            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        return result;
    }

    // Knowing the level of a node
    public void levelOrderTraverse1(TreeNode<V> node) {
        // if node is null, return
        if (root == null) {
            return;
        }

        Queue<TreeNode<V>> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            // size == how many node on current layer
            int size = queue.size();
            // i == i th node of this level
            for (int i = 0; i < size; i++) {                
                TreeNode<V> cur = queue.poll();
                System.out.println("depth = " + depth + ", val = " + cur.val);
    
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
    
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            // retrive all node on current level, depth++
            depth++;
        }
    }

    // Assume that the weight and of each branch can be any value. (levelOrderTraverse2's weight is 1)
    public void levelOrderTraverse2(TreeNode<V> node) {
        // if node is null, return
        if (root == null) {
            return;
        }

        Queue<TreeNodeWithState<V>> queue = new LinkedList<>();
        queue.offer(new TreeNodeWithState(root, 1));
        int weight = 2;

        while (!queue.isEmpty()) {
            TreeNodeWithState<V> cur = queue.poll();
            // 访问 cur 节点，同时知道它的路径权重和
            System.out.println("depth = " + cur.depth + ", val = " + cur.node.val);
    
            // 把 cur 的左右子节点加入队列
            if (cur.node.left != null) {
                queue.offer(new TreeNodeWithState(cur.node.left, cur.depth + weight));
            }
            if (cur.node.right != null) {
                queue.offer(new TreeNodeWithState(cur.node.right, cur.depth + weight));
            }
        }
    }

    public void buildTree(V[] array) {
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
        // tree.dfs("preorder");
        // tree.dfs("inorder");
        // tree.dfs("postorder");
        tree.bfs();
    }
}
