package com.some_domain.www.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : waniasra
 * @date : 12/19/2019 9:27 PM
 * This class demonstrates how to convert a binary tree to binary search tree
 */

//Reference : https://www.geeksforgeeks.org/binary-tree-to-binary-search-tree-conversion/
//Reference : https://www.youtube.com/watch?v=wBFttOncVUc
public class BinaryTreeToBSTConversion {

    private Node root;

    public BinaryTreeToBSTConversion() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        BinaryTreeToBSTConversion tree = new BinaryTreeToBSTConversion();

        //Creating a binary sample binary tree
        tree.createBinaryTree();

        tree.convertBinaryTreeToBST(tree.getRoot());

        System.out.println("In-order traversal of converted BST is given as follows");
        tree.inOrderTraversalOfBST(tree.getRoot());
    }

    private void convertBinaryTreeToBST(Node rootReference) {

        //Step 1  : Store the in-order traversal of binary tree in, say a list or an array
        List<Integer> list = new ArrayList<>();
        constructInOrderTraversalOfBST(rootReference, list); // TC O(N)

        //Step 2 : Sort the list/array
        Collections.sort(list);       // TC O(NlogN), internally merge sort or Tim sort is being used

        //Step 3 : Traverse the binary tree and sorted list/array simultaneously and replace the values in the binary tree
        //         with the values stored in sorted list/array
        ReplacementIndex replacementIndex = new ReplacementIndex(); // Just stores the index for replacement
        replaceBinaryTreeNodeDataWithSortedInOrderTraversalFormed(rootReference, list, replacementIndex); // TC O(N)

        System.out.println("Time complexity is O(N) + O(NlogN) + O(N) for steps 1, 2 and 3 respectively and which is equal to O(NlogN)");
    }

    private void replaceBinaryTreeNodeDataWithSortedInOrderTraversalFormed(Node rootReference, List<Integer> list, ReplacementIndex replacementIndex) {
        if (rootReference != null) {
            replaceBinaryTreeNodeDataWithSortedInOrderTraversalFormed(rootReference.getLeftChild(), list, replacementIndex);

            int index = replacementIndex.getIndex();
            rootReference.setData(list.get(index));
            replacementIndex.setIndex(++index);

            replaceBinaryTreeNodeDataWithSortedInOrderTraversalFormed(rootReference.getRightChild(), list, replacementIndex);
        }
    }

    private void constructInOrderTraversalOfBST(Node rootReference, List<Integer> list) {
        if (rootReference != null) {
            constructInOrderTraversalOfBST(rootReference.getLeftChild(), list);
            list.add(rootReference.getData());
            constructInOrderTraversalOfBST(rootReference.getRightChild(), list);
        }
    }

    public void createBinaryTree() {

        /* Constructing following tree
                10
               /  \
              30   15
             /      \
            20       5
        */
        Node rootReference = new Node(10, null, null);
        rootReference.setLeftChild(new Node(30, null, null));
        rootReference.setRightChild(new Node(15, null, null));
        rootReference.getLeftChild().setLeftChild(new Node(20, null, null));
        rootReference.getRightChild().setRightChild(new Node(5, null, null));
        root = rootReference;
    }

    public void inOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBST(rootReference.getRightChild());
        }
    }

    private static class ReplacementIndex {
        private int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    private class Node {

        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int data, Node leftChild, Node rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
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
