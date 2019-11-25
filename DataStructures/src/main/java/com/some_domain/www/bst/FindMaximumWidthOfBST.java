package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 11/25/2019 10:06 PM
 * This class demonstrates how to find the maximum width of the BST
 */
//Reference : https://www.geeksforgeeks.org/maximum-width-of-a-binary-tree/
public class FindMaximumWidthOfBST {

    private Node root;

    public FindMaximumWidthOfBST() {
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

        FindMaximumWidthOfBST bst = new FindMaximumWidthOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.findMaximumWidthOfBST(bst.getRoot());

        System.out.println("Time and space complexity is O(N)");

    }

    /*
        The idea is to use the level order traversal of the BST.
        We count he number of nodes at each level and keep track of the level with maximum number of nodes
     */
    public void findMaximumWidthOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            int maximumWidth = 0;
            while (!queue.isEmpty()) {
                int currentLevelWidth = queue.size();
                maximumWidth = Math.max(currentLevelWidth, maximumWidth);
                for (int i = 0; i < currentLevelWidth; i++) {
                    Node polledNode = queue.poll();

                    //If  the polled node has a left child
                    if (polledNode.getLeftChild() != null) {
                        queue.offer(polledNode.getLeftChild());
                    }

                    //If the polled node has a right child
                    if (polledNode.getRightChild() != null) {
                        queue.offer(polledNode.getRightChild());
                    }
                }
            }
            System.out.println("Maximum width of the BST is : " + maximumWidth);
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
