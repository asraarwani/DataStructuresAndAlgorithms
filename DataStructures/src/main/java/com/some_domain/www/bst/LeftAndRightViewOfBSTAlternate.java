package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 10/21/2019 11:17 PM
 * This class demonstrates right and left view of a BST using Queue
 */
public class LeftAndRightViewOfBSTAlternate {

    private Node root;

    public LeftAndRightViewOfBSTAlternate() {
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

        LeftAndRightViewOfBSTAlternate bst = new LeftAndRightViewOfBSTAlternate();

        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        System.out.println("Right view of the BST is given as follows...");
        bst.printRightViewOfBST(bst.getRoot());

        System.out.println("\nLeft view of the BST is given as follows...");
        bst.printLeftViewOfBST(bst.getRoot());
        System.out.println("\nTime complexity of the above approaches is O(N) where N is the number of the nodes in the BST");

    }


    public void printLeftViewOfBST(Node rootReference) {
        if (rootReference == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(rootReference);
        while (!queue.isEmpty()) {
            int currentSizeOfQueue = queue.size();
            for (int i = 1; i <= currentSizeOfQueue; i++) {
                Node processedNode = queue.poll();
                //Print the left most node at current level of BST
                if (i == 1) {
                    System.out.print(processedNode.getData() + " ");
                }

                //If the processed node has a left child
                if (processedNode.getLeftChild() != null) {
                    queue.offer(processedNode.getLeftChild());
                }

                //If the processed node has a right child
                if (processedNode.getRightChild() != null) {
                    queue.offer(processedNode.getRightChild());
                }
            }
        }
    }

    public void printRightViewOfBST(Node rootReference) {
        if (rootReference == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(rootReference);
        while (!queue.isEmpty()) {
            int currentSizeOfQueue = queue.size();
            for (int i = 1; i <= currentSizeOfQueue; i++) {
                Node polledNode = queue.poll();

                //Print the right most node at current level
                if (i == currentSizeOfQueue) {
                    System.out.print(polledNode.getData() + " ");
                }

                //If the processed node has a left child
                if (polledNode.getLeftChild() != null) {
                    queue.offer(polledNode.getLeftChild());
                }

                //if the processed node has a right child
                if (polledNode.getRightChild() != null) {
                    queue.offer(polledNode.getRightChild());
                }
            }
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
