package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 11/26/2019 11:01 PM
 * This class demonstrates how to print the full nodes of a BST. Full nodes mean nodes which have both left and right child
 */
//Reference : https://www.geeksforgeeks.org/print-full-nodes-binary-tree/
public class PrintAllFullNodesOfBST {

    private Node root;

    public PrintAllFullNodesOfBST() {
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

        PrintAllFullNodesOfBST bst = new PrintAllFullNodesOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        System.out.println("Full nodes of the BST are given as follows :");
        bst.printAllFullNodesInBST(bst.getRoot());

        System.out.println("\nTime complexity is O(N)");
    }

    /*
        The idea is to use any one of the pre/post/in order traversals of the BST, here we are using in-order traversal
     */
    public void printAllFullNodesInBST(Node rootReference) {
        if (rootReference != null) {
            printAllFullNodesInBST(rootReference.getLeftChild());
            if (rootReference.getLeftChild() != null && rootReference.getRightChild() != null) {
                System.out.print(rootReference.getData() + " ");
            }
            printAllFullNodesInBST(rootReference.getRightChild());
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
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
