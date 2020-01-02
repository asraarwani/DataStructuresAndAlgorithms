package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 12/4/2019 8:37 PM
 * This class demonstrates how to print the internal nodes of a BST (node with at least one child is called an internal node or we can say node which is not a leaf node)
 */
//Reference : https://www.geeksforgeeks.org/print-all-internal-nodes-of-a-binary-tree/
public class PrintAllInternalNodesOfBST {


    private Node root;

    public PrintAllInternalNodesOfBST() {
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
                              /
                             4
         */

        PrintAllInternalNodesOfBST bst = new PrintAllInternalNodesOfBST();
        bst.insertNodeIntoIteratively(20);
        bst.insertNodeIntoIteratively(15);
        bst.insertNodeIntoIteratively(25);
        bst.insertNodeIntoIteratively(8);
        bst.insertNodeIntoIteratively(16);
        bst.insertNodeIntoIteratively(24);
        bst.insertNodeIntoIteratively(30);
        bst.insertNodeIntoIteratively(4);

        System.out.println("Internal nodes of the BST are given as follows");
        bst.printInternalNodesOfBST(bst.getRoot());
        System.out.println("\nTime and space complexity is O(N) where N is the number of nodes in the BST");

        System.out.println();
        bst.printAllInternalNodesOfBST(bst.getRoot());
        System.out.println("\nTime complexity for this approach is O(N) where N is the number of nodes in the BST");
    }

    /*
        The idea is to use the level order traversal of the BST. If a node has either left or right child or both, we print it
     */
    public void printInternalNodesOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            boolean isInternalNode = false;
            while (!queue.isEmpty()) {
                Node polledNode = queue.poll();
                isInternalNode = false;

                //If polled node has a left child
                if (polledNode.getLeftChild() != null) {
                    isInternalNode = true;
                    queue.offer(polledNode.getLeftChild());
                }

                //If polled node has a right child
                if (polledNode.getRightChild() != null) {
                    isInternalNode = true;
                    queue.offer(polledNode.getRightChild());
                }

                if (isInternalNode) {
                    System.out.print(polledNode.getData() + " ");
                }
            }
        }
    }

    /*
        The idea is to use the in-order traversal technique of BST
     */
    public void printAllInternalNodesOfBST(Node rootReference) {
        if (rootReference != null) {

            printAllInternalNodesOfBST(rootReference.getLeftChild());

            //If node has either left or right or both child nodes, we print it
            if (rootReference.getLeftChild() != null || rootReference.getRightChild() != null) {
                System.out.print(rootReference.getData() + " ");
            }

            printAllInternalNodesOfBST(rootReference.getRightChild());
        }
    }

    public void insertNodeIntoIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processingNodeParent = null;
            Node currentNode = root;
            while (true) {
                processingNodeParent = currentNode;
                if (data <= currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        processingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
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
