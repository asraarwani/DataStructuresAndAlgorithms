package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 1/11/2020 12:23 PM
 * This class demonstrates how to print the pre-order traversal of BST using Moris algorithm
 */
//Reference : https://www.geeksforgeeks.org/morris-traversal-for-preorder/
public class MorisPreOrderTraversalOfBST {

    private Node root;

    public MorisPreOrderTraversalOfBST() {
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

        MorisPreOrderTraversalOfBST bst = new MorisPreOrderTraversalOfBST();

        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        bst.printPreOrderTraversalOfBST(bst.getRoot());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in BST");
    }

    public void printPreOrderTraversalOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Node currentNode = rootReference;
            while (currentNode != null) {

                if (currentNode.getLeftChild() == null) {
                    System.out.print(currentNode.getData() + " ");
                    currentNode = currentNode.getRightChild();
                } else {
                    Node predecessorNode = currentNode.getLeftChild();
                    while (predecessorNode.getRightChild() != null && predecessorNode.getRightChild() != currentNode)
                        predecessorNode = predecessorNode.getRightChild();

                    if (predecessorNode.getRightChild() == currentNode) {
                        predecessorNode.setRightChild(null);
                        currentNode = currentNode.getRightChild();
                    } else {
                        System.out.print(currentNode.getData() + " ");
                        predecessorNode.setRightChild(currentNode);
                        currentNode = currentNode.getLeftChild();
                    }
                }
            }
        }
    }

    public Node insertNodeIntoBSTRecursively(Node rootReference, int data) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursively(rootReference.getLeftChild(), data));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursively(rootReference.getRightChild(), data));
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
