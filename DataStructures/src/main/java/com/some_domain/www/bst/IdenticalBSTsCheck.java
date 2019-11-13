package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 11/11/2019 9:46 PM
 * This class demonstrates how to check if two BST are identical
 * Two BSTs are said to be identical if both BSTs have same data and arrangement of data is also same
 */
//Reference : https://www.geeksforgeeks.org/iterative-function-check-two-trees-identical/
//Reference : https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
public class IdenticalBSTsCheck {

    private Node root;

    public IdenticalBSTsCheck() {
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


        IdenticalBSTsCheck bstOne = new IdenticalBSTsCheck();
        bstOne.insertNodeIntoBSTIteratively(20);
        bstOne.insertNodeIntoBSTIteratively(15);
        bstOne.insertNodeIntoBSTIteratively(25);
        bstOne.insertNodeIntoBSTIteratively(8);
        bstOne.insertNodeIntoBSTIteratively(16);
        bstOne.insertNodeIntoBSTIteratively(24);
        bstOne.insertNodeIntoBSTIteratively(30);

        IdenticalBSTsCheck bstTwo = new IdenticalBSTsCheck();
        bstTwo.insertNodeIntoBSTIteratively(20);
        bstTwo.insertNodeIntoBSTIteratively(15);
        bstTwo.insertNodeIntoBSTIteratively(25);
        bstTwo.insertNodeIntoBSTIteratively(8);
        bstTwo.insertNodeIntoBSTIteratively(16);
        bstTwo.insertNodeIntoBSTIteratively(24);
        bstTwo.insertNodeIntoBSTIteratively(30);

        boolean identical = new IdenticalBSTsCheck().areTwoBSTsIdenticalRecursive(bstOne.getRoot(), bstTwo.getRoot());
        if (identical) {
            System.out.println("Two BSTs are identical");
        } else {
            System.out.println("Two BSTs aren't identical");
        }
        System.out.println("Time complexity is O(min(M, N)) where M and N are the number of nodes in two BSTs");

        System.out.println();
        identical = new IdenticalBSTsCheck().areTwoBSTsIdenticalIteratively(bstOne.getRoot(), bstTwo.getRoot());
        if (identical) {
            System.out.println("Two BSTs are identical");
        } else {
            System.out.println("Two BSTs aren't identical");
        }
        System.out.println("Time complexity is O(min(M, N)) where M and N are the number of nodes in two BSTs");
    }

    public boolean areTwoBSTsIdenticalIteratively(Node firstBSTRootReference, Node secondBSTRootReference) {
        if (firstBSTRootReference == null && secondBSTRootReference == null)
            return true;
        if (firstBSTRootReference == null || secondBSTRootReference == null)
            return false;

        Queue<Node> firstQueue = new LinkedList<>();
        Queue<Node> secondQueue = new LinkedList<>();
        firstQueue.add(firstBSTRootReference);
        secondQueue.add(secondBSTRootReference);

        while (!firstQueue.isEmpty() && !secondQueue.isEmpty()) {

            Node polledNodeFromFirstQueue = firstQueue.poll();
            Node polledNodeFromSecondQueue = secondQueue.poll();

            if (polledNodeFromFirstQueue.getData() != polledNodeFromSecondQueue.getData())
                return false;

            //Process the left children of the polled nodes
            if (polledNodeFromFirstQueue.getLeftChild() != null && polledNodeFromSecondQueue.getLeftChild() != null) {
                firstQueue.offer(polledNodeFromFirstQueue.getLeftChild());
                secondQueue.offer(polledNodeFromSecondQueue.getLeftChild());
            } else if (polledNodeFromFirstQueue.getLeftChild() != null || polledNodeFromSecondQueue.getLeftChild() != null) {
                return false;
            }

            //Process the right children of the polled nodes
            if (polledNodeFromFirstQueue.getRightChild() != null && polledNodeFromSecondQueue.getRightChild() != null) {
                firstQueue.offer(polledNodeFromFirstQueue.getRightChild());
                secondQueue.offer(polledNodeFromSecondQueue.getRightChild());
            } else if (polledNodeFromFirstQueue.getRightChild() != null || polledNodeFromSecondQueue.getRightChild() != null) {
                return false;
            }
        }
        return true;
    }

    public boolean areTwoBSTsIdenticalRecursive(Node firstBSTRootReference, Node secondBSTRootReference) {
        if (firstBSTRootReference == null && secondBSTRootReference == null)
            return true;
        if (firstBSTRootReference != null && secondBSTRootReference != null) {
            return firstBSTRootReference.getData() == secondBSTRootReference.getData()
                    && areTwoBSTsIdenticalRecursive(firstBSTRootReference.getLeftChild(), secondBSTRootReference.getLeftChild())
                    && areTwoBSTsIdenticalRecursive(firstBSTRootReference.getRightChild(), secondBSTRootReference.getRightChild());
        }
        return false;
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNode = null;
            Node traversingNode = root;
            while (true) {
                currentlyProcessingNode = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        currentlyProcessingNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        currentlyProcessingNode.setRightChild(newNode);
                        break;
                    }
                }
            }
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
