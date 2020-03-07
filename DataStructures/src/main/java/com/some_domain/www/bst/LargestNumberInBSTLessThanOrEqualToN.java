package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/25/2019 11:56 AM
 * This class demonstrates how to find a largest number which is less than or equal to a given number in a BST
 */
//Reference : https://www.geeksforgeeks.org/largest-number-bst-less-equal-n/
//Reference : https://www.geeksforgeeks.org/largest-number-less-equal-n-bst-iterative-approach/
public class LargestNumberInBSTLessThanOrEqualToN {

    private Node root;

    public LargestNumberInBSTLessThanOrEqualToN() {
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

        LargestNumberInBSTLessThanOrEqualToN bst = new LargestNumberInBSTLessThanOrEqualToN();
        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        int givenNumber = 26;
        Node floorNode = bst.findLargestNumberSmallerThanOrEqualToGivenNumber(bst.getRoot(), givenNumber);
        if (floorNode != null)
            System.out.println("Number is  : " + floorNode.getData());

        floorNode = bst.findFloor(bst.getRoot(), givenNumber);
        if (floorNode != null)
            System.out.println("\nNumber is  : " + floorNode.getData());

        System.out.println("Time complexity is O(H) where H is the height of the BST");
    }

    public Node findLargestNumberSmallerThanOrEqualToGivenNumber(Node rootReference, int givenNumber) {
        if (rootReference == null)
            return null;
        if (rootReference.getData() == givenNumber)
            return rootReference;
        else if (givenNumber < rootReference.getData()) {
            return findLargestNumberSmallerThanOrEqualToGivenNumber(rootReference.getLeftChild(), givenNumber);
        } else if (givenNumber > rootReference.getData()) {
            Node floorNode = findLargestNumberSmallerThanOrEqualToGivenNumber(rootReference.getRightChild(), givenNumber);
            if (floorNode == null)
                return rootReference;
            else
                return floorNode;
        }
        return null;
    }

    public Node findFloor(Node rootReference, int givenNumber) {
        Node resultNode = null;
        Node currentNode = rootReference;
        while (currentNode != null) {
            if (givenNumber < currentNode.getData()) {
                currentNode = currentNode.getLeftChild();
            } else {
                resultNode = currentNode;
                currentNode = currentNode.getRightChild();
            }
        }
        return resultNode;
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
