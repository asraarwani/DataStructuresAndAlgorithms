package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/25/2019 11:58 AM
 * This class demonstrates how to find a largest number which is greater than or equal to a given number in a BST
 */
public class SmallestNumberInBSTGreaterThanOrEqualToN {

    private Node root;

    public SmallestNumberInBSTGreaterThanOrEqualToN() {
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

        SmallestNumberInBSTGreaterThanOrEqualToN bst = new SmallestNumberInBSTGreaterThanOrEqualToN();
        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        int givenNumber = 14;
        Node ceilNode = bst.findLargestNumberGreaterThanOrEqualToGivenNumber(bst.getRoot(), givenNumber);
        if (ceilNode != null)
            System.out.println("Number is  : " + ceilNode.getData());

        ceilNode = bst.findCeil(bst.getRoot(), givenNumber);
        if (ceilNode != null)
            System.out.println("Number is  : " + ceilNode.getData());

        System.out.println("Time complexity is O(H) where H is the height of the BST");
    }

    public Node findLargestNumberGreaterThanOrEqualToGivenNumber(Node rootReference, int givenNumber) {
        if (rootReference == null)
            return null;
        if (rootReference.getData() == givenNumber)
            return rootReference;
        else if (givenNumber < rootReference.getData()) {
            Node ceilNode = findLargestNumberGreaterThanOrEqualToGivenNumber(rootReference.getLeftChild(), givenNumber);
            if (ceilNode == null)
                return rootReference;
            else
                return ceilNode;
        } else if (givenNumber > rootReference.getData()) {
            return findLargestNumberGreaterThanOrEqualToGivenNumber(rootReference.getRightChild(), givenNumber);
        }
        return null;
    }

    public Node findCeil(Node rootReference, int givenNumber) {
        Node resultNode = null;
        Node currentNode = rootReference;
        while (currentNode != null) {
            if (givenNumber <= currentNode.getData()) {
                resultNode = currentNode;
                currentNode = currentNode.getLeftChild();
            } else {
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
