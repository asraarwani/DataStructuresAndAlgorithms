package com.some_domain.www.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : waniasra
 * @date : 12/13/2019 9:25 PM
 * This class demonstrates how to print the nodes of a BST in min max fashion
 */
//Reference : https://www.geeksforgeeks.org/print-binary-search-tree-in-min-max-fashion/
public class PrintBSTInMinMaxFashion {

    private Node root;

    public PrintBSTInMinMaxFashion() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {


          /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */


        PrintBSTInMinMaxFashion bst = new PrintBSTInMinMaxFashion();
        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        System.out.println("Node of BST in Min-Max fashion are given as follows");
        bst.printBSTInMinMaxFashion(bst.getRoot());
        System.out.println("\nTime and space complexity is O(N)");
    }

    /*
        Approach:
        Create a list and store the in-order traversal of the given binary search tree.
        Since the in-order traversal of the binary search tree is sorted in ascending order, initialise i = 0 and j = n – 1.
        Print list.get(i) and update i = i + 1.
        Print list.get(j) and update j = j – 1.
        Repeat steps 3 and 4 until all the elements have been printed.
     */
    public void printBSTInMinMaxFashion(Node rootReference) {
        List<Node> list = new ArrayList<>();
        inOrderTraversalOfBST(rootReference, list);

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            System.out.print(list.get(i++).getData() + " " + list.get(j--).getData() + " ");
        }
        //In case we've odd number of nodes in BST
        if (i == j) {
            System.out.print(list.get(i).getData());
        }
    }

    private void inOrderTraversalOfBST(Node rootReference, List<Node> list) {
        if (rootReference != null) {
            inOrderTraversalOfBST(rootReference.getLeftChild(), list);
            list.add(rootReference);
            inOrderTraversalOfBST(rootReference.getRightChild(), list);
        }
    }

    public Node insertNodeIntoBSTRecursively(Node rootReference, int data) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursively(rootReference.getLeftChild(), data));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursively(rootReference.getRightChild(), data));
        }
        return rootReference;
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
