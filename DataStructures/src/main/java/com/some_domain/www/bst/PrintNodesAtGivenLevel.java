package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : asraar
 * @date : 09-10-2022 02:39 pm
 */
public class PrintNodesAtGivenLevel {

    private Node root;

    public static void main(String[] args) {

           /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        PrintNodesAtGivenLevel bst = new PrintNodesAtGivenLevel();
        Node rootReference = bst.insertNodeIntoBSTRecursivelyBST(20, null);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(30, rootReference);
        bst.setRoot(rootReference);

        System.out.println();
        int levelNumber = 3;
        System.out.println("\nNodes at level " + levelNumber + " are given as follows");
        bst.printNodesAtGivenLevel(bst.getRoot(), levelNumber);

        System.out.println();
        bst.printNodesAtGivenLevelUsingLevelOrderTraversal(bst.getRoot(), levelNumber);

        System.out.println();
        bst.printNodesAtGivenLevelAlternate(bst.getRoot(), levelNumber, 1);

        System.out.println("\nTime complexity is O(N) for above three approaches");
    }

    private void printNodesAtGivenLevel(Node rootReference, int levelNumber) {
        if (rootReference == null)
            return;

        if (levelNumber == 1) {
            System.out.print(rootReference.getData() + " ");
        }

        printNodesAtGivenLevel(rootReference.getLeftChild(), levelNumber - 1);
        printNodesAtGivenLevel(rootReference.getRightChild(), levelNumber - 1);
    }

    private void printNodesAtGivenLevelUsingLevelOrderTraversal(Node rootReference, int levelNumber) {
        if (rootReference == null) {
            System.out.println("BST is empty");
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            int currentLevel = 0;
            while (!queue.isEmpty()) {
                int currentQueueSize = queue.size();
                currentLevel++;
                for (int i = 0; i < currentQueueSize; i++) {
                    Node polledNode = queue.poll();
                    if (currentLevel == levelNumber) {
                        System.out.print(polledNode.getData() + " ");
                    }

                    if (polledNode.getLeftChild() != null) {
                        queue.offer(polledNode.getLeftChild());
                    }

                    if (polledNode.getRightChild() != null) {
                        queue.offer(polledNode.getRightChild());
                    }
                }
            }
        }
    }

    private void printNodesAtGivenLevelAlternate(Node rootReference, int maxLevel, int levelNumber) {

        if (rootReference != null) {

            printNodesAtGivenLevelAlternate(rootReference.getLeftChild(), maxLevel, levelNumber + 1);

            if (levelNumber == maxLevel) {
                System.out.print(rootReference.getData() + " ");
            }
            printNodesAtGivenLevelAlternate(rootReference.getRightChild(), maxLevel, levelNumber + 1);
        }
    }

    private void printInorderTraversalRecursive(Node rootReference) {
        if (rootReference != null) {
            printInorderTraversalRecursive(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            printInorderTraversalRecursive(rootReference.getRightChild());
        }
    }

    public Node insertNodeIntoBSTRecursivelyBST(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getLeftChild()));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getRightChild()));
        }
        return rootReference;
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
