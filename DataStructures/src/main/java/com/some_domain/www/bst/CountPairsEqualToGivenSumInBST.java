package com.some_domain.www.bst;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : waniasra
 * @date : 11/16/2019 1:02 PM
 */
//Reference : https://www.geeksforgeeks.org/count-pairs-in-a-binary-tree-whose-sum-is-equal-to-a-given-value-x/
public class CountPairsEqualToGivenSumInBST {

    private Node root;
    private static int pairCount;

    public CountPairsEqualToGivenSumInBST() {
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

        CountPairsEqualToGivenSumInBST bst = new CountPairsEqualToGivenSumInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        int givenSum = 40;
        bst.countNumberOfPairsEqualToGivenSum(bst.getRoot(), givenSum);
        System.out.println("Number of pairs whose sum is equal to " + givenSum + " are : " + pairCount);
        System.out.println("Time and space complexity is O(N)");
    }


    public void countNumberOfPairsEqualToGivenSum(Node rootReference, int givenSum) {
        countNumberOfPairsEqualToGivenSumHelper(rootReference, givenSum, new HashSet<>());
    }

    /*
    Traverse the tree in any order (pre / post / in) .
    Create an empty hash and keep checking if difference between node's data and given sum exists in hash, if yes then increment the pairCount
    else add the node's data  to the hash.
    */
    private int countNumberOfPairsEqualToGivenSumHelper(Node rootReference, int givenSum, Set<Integer> set) {
        if (rootReference == null)
            return 0;

        countNumberOfPairsEqualToGivenSumHelper(rootReference.getLeftChild(), givenSum, set);

        if (set.contains(givenSum - rootReference.getData())) {
            System.out.println(givenSum - rootReference.getData() + " , " + rootReference.getData());
            pairCount++;
        } else {
            set.add(rootReference.getData());
        }

        countNumberOfPairsEqualToGivenSumHelper(rootReference.getRightChild(), givenSum, set);
        return pairCount;
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNode = null;
            Node traversalNode = root;
            while (true) {
                currentlyProcessingNode = traversalNode;
                if (data <= traversalNode.getData()) {
                    traversalNode = traversalNode.getLeftChild();
                    if (traversalNode == null) {
                        currentlyProcessingNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversalNode = traversalNode.getRightChild();
                    if (traversalNode == null) {
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
