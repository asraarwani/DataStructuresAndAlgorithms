package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 11/16/2019 1:02 PM
 * This class demonstrates how to print/count the number of pairs of nodes from two BSTs whose sum is equal to given sum
 */
//Reference : https://www.geeksforgeeks.org/count-pairs-from-two-bsts-whose-sum-is-equal-to-a-given-value-x/
public class CountPairsEqualToGivenSumFromTwoBSTs {

    private Node root;

    public CountPairsEqualToGivenSumFromTwoBSTs() {
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


          /*

         BST 1:
                  5
                /   \
               3     7
              / \   / \
             2  4  6   8

        BST 2:
                 10
                /   \
               6     15
              / \   /  \
             3  8  11  18

        x = 16

        */

        CountPairsEqualToGivenSumFromTwoBSTs firstBST = new CountPairsEqualToGivenSumFromTwoBSTs();
        Node rootReference = firstBST.insertNodeIntoBSTRecursively(null, 20);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 30);
        firstBST.setRoot(rootReference);

        CountPairsEqualToGivenSumFromTwoBSTs secondBST = new CountPairsEqualToGivenSumFromTwoBSTs();
        Node root = secondBST.insertNodeIntoBSTRecursively(null, 20);
        root = secondBST.insertNodeIntoBSTRecursively(root, 15);
        root = secondBST.insertNodeIntoBSTRecursively(root, 25);
        root = secondBST.insertNodeIntoBSTRecursively(root, 8);
        root = secondBST.insertNodeIntoBSTRecursively(root, 16);
        root = secondBST.insertNodeIntoBSTRecursively(root, 24);
        root = secondBST.insertNodeIntoBSTRecursively(root, 30);
        secondBST.setRoot(root);

     /*   Node rootReference = firstBST.insertNodeIntoBSTRecursively(null, 5);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 3);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 7);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 2);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 4);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 6);
        rootReference = firstBST.insertNodeIntoBSTRecursively(rootReference, 8);
        firstBST.setRoot(rootReference);

        CountPairsEqualToGivenSumFromTwoBSTs secondBST = new CountPairsEqualToGivenSumFromTwoBSTs();
        Node root = secondBST.insertNodeIntoBSTRecursively(null, 10);
        root = secondBST.insertNodeIntoBSTRecursively(root, 6);
        root = secondBST.insertNodeIntoBSTRecursively(root, 15);
        root = secondBST.insertNodeIntoBSTRecursively(root, 3);
        root = secondBST.insertNodeIntoBSTRecursively(root, 8);
        root = secondBST.insertNodeIntoBSTRecursively(root, 11);
        root = secondBST.insertNodeIntoBSTRecursively(root, 18);
        secondBST.setRoot(root);*/

        int givenSum = 40;
        int pairCount = new CountPairsEqualToGivenSumFromTwoBSTs().countNumberOfPairsEqualToGivenSum(firstBST.getRoot(), secondBST.getRoot(), givenSum);
        System.out.println("Number of pairs is " + pairCount);
        System.out.println("Space and time complexity is O(N1 + N2) where N1 and N2 are the number of nodes in two BSTs");
    }

    public int countNumberOfPairsEqualToGivenSum(Node firstRoot, Node secondRoot, int givenSum) {
        if (firstRoot == null || secondRoot == null)
            return 0;

        Stack<Node> firstStack = new Stack<>();
        Stack<Node> secondStack = new Stack<>();

        int pairCount = 0;
        Node firstStackTraversalNode = firstRoot;
        Node secondStackTraversalNode = secondRoot;
        while (true) {

            while (firstStackTraversalNode != null) {
                firstStack.push(firstStackTraversalNode);
                firstStackTraversalNode = firstStackTraversalNode.getLeftChild();
            }

            while (secondStackTraversalNode != null) {
                secondStack.push(secondStackTraversalNode);
                secondStackTraversalNode = secondStackTraversalNode.getRightChild();
            }

            if (firstStack.isEmpty() || secondStack.isEmpty())
                break;

            firstStackTraversalNode = firstStack.peek();
            secondStackTraversalNode = secondStack.peek();
            if (firstStackTraversalNode.getData() + secondStackTraversalNode.getData() == givenSum) {
                pairCount++;
                System.out.println(firstStackTraversalNode.getData() + " , " + secondStackTraversalNode.getData());
                // Moving to the right child in case of in-order traversal for picking the next smallest node
                firstStackTraversalNode = firstStackTraversalNode.getRightChild();
                // Moving to the left child in case of reverse in-order traversal for picking the next largest node
                secondStackTraversalNode = secondStackTraversalNode.getLeftChild();
                firstStack.pop();
                secondStack.pop();
            } else if (firstStackTraversalNode.getData() + secondStackTraversalNode.getData() < givenSum) {
                // Moving to the right child in case of in-order traversal for picking the next smallest node
                firstStackTraversalNode = firstStackTraversalNode.getRightChild();
                secondStackTraversalNode = null;
                firstStack.pop();
            } else {
                // Moving to the left child in case of reverse in-order traversal for picking the next largest node
                secondStackTraversalNode = secondStackTraversalNode.getLeftChild();
                firstStackTraversalNode = null;
                secondStack.pop();
            }
        }
        return pairCount;
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
