package com.some_domain.www.bst;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 11/25/2019 10:01 PM
 * This class demonstrates how to find the level with maximum sum in a BST
 */
//Reference : https://www.geeksforgeeks.org/find-level-maximum-sum-binary-tree/
public class FindMaximumSumLevelOfBST {

    private Node root;

    public FindMaximumSumLevelOfBST() {
        this.root = root;
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

        FindMaximumSumLevelOfBST bst = new FindMaximumSumLevelOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printMaximumSumLevelOfBST(bst.getRoot());

        System.out.println("Time and space complexity is O(N)");
    }

    /*
        The idea is to use a level order traversal and calculate the sum for each level and keep track of the level with maximum sum
     */
    public void printMaximumSumLevelOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            int maximumLevelSum = 0;
            Map<Integer, List<Node>> maxSumLevel = new LinkedHashMap<>();
            while (!queue.isEmpty()) {
                int currentSize = queue.size();
                int currentLevelSum = 0;
                List<Node> maxNodeList = new ArrayList<>();
                for (int i = 0; i < currentSize; i++) {
                    Node polledNode = queue.poll();
                    maxNodeList.add(polledNode);
                    currentLevelSum = currentLevelSum + polledNode.getData();

                    //If the polled node has a left child
                    if (polledNode.getLeftChild() != null) {
                        queue.offer(polledNode.getLeftChild());
                    }

                    //If the polled node has a right child
                    if (polledNode.getRightChild() != null) {
                        queue.offer(polledNode.getRightChild());
                    }
                }
                //Updating the maximumLevelSum
                if (maximumLevelSum < currentLevelSum) {
                    maximumLevelSum = currentLevelSum;
                    maxSumLevel.put(maximumLevelSum, maxNodeList);
                }
            }
            System.out.println("Maximum level sum is : " + maximumLevelSum);
            System.out.println("Nodes " + maxSumLevel.get(maximumLevelSum).stream().map(node->node.getData()).collect(Collectors.toList()));
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processingNodeParent = null;
            Node traversingNode = root;
            while (true) {
                processingNodeParent = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        processingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        processingNodeParent.setRightChild(newNode);
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
