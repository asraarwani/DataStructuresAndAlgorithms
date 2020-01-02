package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 12/10/2019 10:04 PM
 * This class demonstrates how to print all the nodes between two given levels
 */
//Reference : https://www.geeksforgeeks.org/print-all-nodes-between-two-given-levels-in-binary-tree/
public class PrintAllNodesBetweenTwoGivenLevelsOfBST {

    private Node root;

    public PrintAllNodesBetweenTwoGivenLevelsOfBST() {
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

        PrintAllNodesBetweenTwoGivenLevelsOfBST bst = new PrintAllNodesBetweenTwoGivenLevelsOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        int startingLevel = 1;
        int endingLevel = 3;
        System.out.println("Nodes between levels " + startingLevel + "  and " + endingLevel + "  are given as follows");
        bst.printAllNodesBetweenTwoLevels(bst.getRoot(), startingLevel, endingLevel);
        System.out.println("Time and space complexity is O(N)");

        System.out.println("\nNodes between levels " + startingLevel + "  and " + endingLevel + " using alternate approach are given as follows");
        bst.printNodesBetweenTwoGivenLevels(bst.getRoot(), startingLevel, endingLevel, 1);
        System.out.println("\nTime and complexity is O(N)");
    }

    /*
        The idea is to use in-order traversal technique of BST
     */
    public void printNodesBetweenTwoGivenLevels(Node rootReference, int lowerLevel, int higherLevel, int currentLevel) {

        if (rootReference != null) {

            if (currentLevel > lowerLevel && currentLevel < higherLevel) {
                System.out.print(rootReference.getData() + " ");
            }

            printNodesBetweenTwoGivenLevels(rootReference.getLeftChild(), lowerLevel, higherLevel, currentLevel + 1);

            printNodesBetweenTwoGivenLevels(rootReference.getRightChild(), lowerLevel, higherLevel, currentLevel + 1);
        }
    }

    public void printAllNodesBetweenTwoLevels(Node rootReference, int startingLevel, int endingLevel) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            int levelNumber = 0;
            while (!queue.isEmpty()) {
                int currentQueueSize = queue.size();
                levelNumber++;
                for (int i = 0; i < currentQueueSize; i++) {
                    Node polledNode = queue.poll();

                    //Check if the given nodes falls under the two given levels
                    if (levelNumber > startingLevel && levelNumber < endingLevel) {
                        System.out.print(polledNode.getData() + " ");
                    }

                    //If polled node has  a left child
                    if (polledNode.getLeftChild() != null) {
                        queue.offer(polledNode.getLeftChild());
                    }

                    //If polled node has a right child
                    if (polledNode.getRightChild() != null) {
                        queue.offer(polledNode.getRightChild());
                    }
                }
                System.out.println();
            }
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentNodeParent = null;
            Node currentNode = root;
            while (true) {
                currentNodeParent = currentNode;
                if (data <= currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        currentNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        currentNodeParent.setRightChild(newNode);
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
