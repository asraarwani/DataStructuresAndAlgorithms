package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/25/2019 11:58 AM
 * This class demonstrates how to find a largest number which is greater than or equal to a given number in a BST
 */
public class LargestNumberInBSTGreaterThanOrEqualToN {

    private Node root;

    public LargestNumberInBSTGreaterThanOrEqualToN() {
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

        LargestNumberInBSTGreaterThanOrEqualToN bst = new LargestNumberInBSTGreaterThanOrEqualToN();
        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        int givenNumber = 19;
        int largestNumberGreaterThanOrEqualToGivenNumber = bst.findLargestNumberGreaterThanOrEqualToGivenNumber(bst.getRoot(), givenNumber);
        System.out.println("Number is  : " + largestNumberGreaterThanOrEqualToGivenNumber);
        System.out.println("Time complexity is O(H) where H is the height of the BST");
    }

    public int findLargestNumberGreaterThanOrEqualToGivenNumber(Node rootReference, int givenNumber) {
        if (rootReference == null)
            return -1;
        if (rootReference.getData() == givenNumber)
            return rootReference.getData();
        else if (givenNumber < rootReference.getData()) {
            int number = findLargestNumberGreaterThanOrEqualToGivenNumber(rootReference.getLeftChild(), givenNumber);
            if (number == -1)
                return rootReference.getData();
            else
                return number;
        } else if (givenNumber > rootReference.getData()) {
            return findLargestNumberGreaterThanOrEqualToGivenNumber(rootReference.getRightChild(), givenNumber);
        }
        return -1;
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
