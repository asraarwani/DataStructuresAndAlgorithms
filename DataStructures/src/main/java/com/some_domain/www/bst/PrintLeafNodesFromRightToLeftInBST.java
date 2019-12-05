package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/5/2019 9:32 PM
 * This class demonstrates how to print the leaf nodes from right to left in a BST
 */
//Reference : https://www.geeksforgeeks.org/print-all-leaf-nodes-of-a-binary-tree-from-right-to-left/
public class PrintLeafNodesFromRightToLeftInBST {

    private Node root;

    public PrintLeafNodesFromRightToLeftInBST() {
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
                                                 \
                                                  100

          Leaf nodes from right to left : 100 -> 24 -> 16 -> 8
         */

        PrintLeafNodesFromRightToLeftInBST bst = new PrintLeafNodesFromRightToLeftInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(100);

        System.out.println("Leaf nodes from right to left are given as follows");
        bst.printLeafNodesFromRightToLeft(bst.getRoot());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in BST");
    }

    /*
        The idea is to use a pre-order traversal of BST. We first process the root node, then right child and finally left child
     */
    public void printLeafNodesFromRightToLeft(Node rootReference) {
        if (rootReference != null) {
            //If node being processed is a leaf node
            if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
                System.out.print(rootReference.getData() + " ");
            }

            //Process the right sub tree
            printLeafNodesFromRightToLeft(rootReference.getRightChild());

            //Process the left sub tree
            printLeafNodesFromRightToLeft(rootReference.getLeftChild());
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
