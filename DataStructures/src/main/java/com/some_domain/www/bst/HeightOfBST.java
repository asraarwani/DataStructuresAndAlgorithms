package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 10/20/2019 9:32 PM
 * This class demonstrates how to find the height of a BST both iteratively and recursively
 */
public class HeightOfBST {

    private Node root;

    public HeightOfBST() {
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

        HeightOfBST bst = new HeightOfBST();

        Node rootReference = bst.insertNodeIntoBSTRecursively(20, null);
        rootReference = bst.insertNodeIntoBSTRecursively(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(30, rootReference);
        bst.setRoot(rootReference);

        int heightOfBST = bst.findHeightOfBSTIteratively(bst.getRoot());
        System.out.println("Height of the BST (using iterative approach) : " + heightOfBST);
        System.out.println("Time complexity is O(N), where N is the number of nodes int the BST");

        heightOfBST = bst.findHeightOfBSTRecursively(bst.getRoot());
        System.out.println("\nHeight of the BST (using recursive approach) : " + heightOfBST);
        System.out.println("Time complexity is O(N), where N is the number of nodes int the BST");
    }

    public int findHeightOfBSTRecursively(Node rootReference) {
        if (rootReference == null) {
            return 0;
        } else {
            int leftSubTreeHeight = findHeightOfBSTRecursively(rootReference.getLeftChild());
            int rightSubTreeHeight = findHeightOfBSTRecursively(rootReference.getRightChild());
            return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
        }
    }

    public int findHeightOfBSTIteratively(Node rootReference) {
        if (rootReference == null)
            return 0;
        else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            int height = 0;
            while (!queue.isEmpty()) {
                int currentSizeOfQueue = queue.size();
                while (currentSizeOfQueue > 0) {
                    Node polledNode = queue.poll();

                    //If polled node has a left child
                    if (polledNode.getLeftChild() != null) {
                        queue.offer(polledNode.getLeftChild());
                    }

                    //If polled node has a right child
                    if (polledNode.getRightChild() != null) {
                        queue.offer(polledNode.getRightChild());
                    }
                    currentSizeOfQueue--;
                }
                height++;
            }
            return height;
        }
    }

    public Node insertNodeIntoBSTRecursively(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursively(data, rootReference.getLeftChild()));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursively(data, rootReference.getRightChild()));
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
