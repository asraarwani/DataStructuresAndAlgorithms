package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/21/2019 9:59 PM
 * This class demonstrates how to find the second largest node in a given BST
 */
//Reference : https://www.geeksforgeeks.org/second-largest-element-in-binary-search-tree-bst/
public class SecondLargestElementInBST {

    private Node root;

    public SecondLargestElementInBST() {
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

        SecondLargestElementInBST bst = new SecondLargestElementInBST();
        Node rootReference = bst.insertNodeIntoBSTRecursively(20, null);
        rootReference = bst.insertNodeIntoBSTRecursively(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(30, rootReference);
        bst.setRoot(rootReference);

        Counter counter = new Counter();
        bst.findSecondLargestNodeInBST(bst.getRoot(), counter);
        System.out.println("Time complexity is O(H) where H is the height of BST");
    }

    /*
        The idea is to use a reverse in-order traversal of BST.
        Second largest node is going to be the second node in reverse in-order traversal
     */
    public void findSecondLargestNodeInBST(Node rootReference, Counter counter) {

        if (rootReference == null || counter.getCount() >= 2)
            return;

        findSecondLargestNodeInBST(rootReference.getRightChild(), counter);

        int count = counter.getCount();
        counter.setCount(++count);
        if (counter.getCount() == 2) {
            System.out.println("Second largest node in BST : " + rootReference.getData() + " ");
            return;
        }

        findSecondLargestNodeInBST(rootReference.getLeftChild(), counter);
    }

    public Node insertNodeIntoBSTRecursively(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursively(data, rootReference.getLeftChild()));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursively(data, rootReference.getRightChild()));
        }
        return rootReference;
    }

    private static class Counter {

        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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
