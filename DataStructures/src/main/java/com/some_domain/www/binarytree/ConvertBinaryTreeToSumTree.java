package com.some_domain.www.binarytree;

/**
 * @author : asraar
 * @date : 09-11-2022 06:41 pm
 * <p>
 * Reference  : https://www.geeksforgeeks.org/convert-a-given-tree-to-sum-tree/
 */
public class ConvertBinaryTreeToSumTree {

    private Node root;

    public static void main(String[] args) {

        /* Binary Tree:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        ConvertBinaryTreeToSumTree tree = new ConvertBinaryTreeToSumTree();
        tree.setRoot(tree.createDummyBinaryTree());

        tree.inOrderTraversalOfBinaryTree(tree.getRoot());

        System.out.println("\nAfter converting binary tree to sum tree");
        tree.convertBinaryTreeToSumTree(tree.getRoot());
        tree.inOrderTraversalOfBinaryTree(tree.getRoot());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in the binary tree");
    }

    private int convertBinaryTreeToSumTree(Node rootReference) {
        if (rootReference == null)
            return 0;

        int rootData = rootReference.getData();

        rootReference.setData(convertBinaryTreeToSumTree(rootReference.getLeftChild()) + convertBinaryTreeToSumTree(rootReference.getRightChild()));

        return rootData + rootReference.getData();
    }

    private void inOrderTraversalOfBinaryTree(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBinaryTree(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBinaryTree(rootReference.getRightChild());
        }
    }

    private Node createDummyBinaryTree() {
        Node root = new Node(20, null, null);
        root.setLeftChild(new Node(15, null, null));
        root.setRightChild(new Node(25, null, null));
        root.getLeftChild().setLeftChild(new Node(8, null, null));
        root.getLeftChild().setRightChild(new Node(16, null, null));
        root.getRightChild().setLeftChild(new Node(24, null, null));
        root.getRightChild().setRightChild(new Node(30, null, null));
        return root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private class Node {
        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int data, Node leftChild, Node rightChilid) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChilid;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}
