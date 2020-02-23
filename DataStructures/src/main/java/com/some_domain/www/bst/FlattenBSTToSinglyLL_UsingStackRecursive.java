package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 12/16/2019 10:27 PM
 * This class demonstrates how to convert a given BST into Singly Linked List using stack (Recursive)
 */
//Reference : https://www.youtube.com/watch?v=J6hitEo42Q0
public class FlattenBSTToSinglyLL_UsingStackRecursive {

    private Node root;

    public FlattenBSTToSinglyLL_UsingStackRecursive() {
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

        FlattenBSTToSinglyLL_UsingStackRecursive bst = new FlattenBSTToSinglyLL_UsingStackRecursive();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.flattenBSTToSinglyLinkedListUsingStackRecursive(bst.getRoot());
        bst.inOrderTraversalOfBST(bst.getRoot());
        System.out.println("\nTime and space complexity is O(N)");

    }


    public void flattenBSTToSinglyLinkedListUsingStackRecursive(Node rootReference) {
        flattenBSTToSinglyLinkedListUsingStackRecursiveHelper(rootReference, new NodeDetails());
    }

    private void flattenBSTToSinglyLinkedListUsingStackRecursiveHelper(Node rootReference, NodeDetails nodeDetails) {

        if (rootReference != null) {

            flattenBSTToSinglyLinkedListUsingStackRecursiveHelper(rootReference.getRightChild(), nodeDetails);

            flattenBSTToSinglyLinkedListUsingStackRecursiveHelper(rootReference.getLeftChild(), nodeDetails);

            rootReference.setLeftChild(null);
            rootReference.setRightChild(nodeDetails.getPrevious());
            nodeDetails.setPrevious(rootReference);
        }
    }

    private class NodeDetails {
        private Node previous;

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }

    public void inOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBST(rootReference.getRightChild());
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
            return;
        } else {
            Node parentOfCurrentNode = null;
            Node currentNode = root;
            while (true) {
                parentOfCurrentNode = currentNode;
                if (data <= currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        parentOfCurrentNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentOfCurrentNode.setRightChild(newNode);
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
