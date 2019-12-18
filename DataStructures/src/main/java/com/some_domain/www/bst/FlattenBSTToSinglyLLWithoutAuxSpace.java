package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/16/2019 9:37 PM
 * This class demonstrates how to convert a given BST into Singly Linked List without using any auxiliary data structure
 */
//Reference : https://www.youtube.com/watch?v=J6hitEo42Q0
public class FlattenBSTToSinglyLLWithoutAuxSpace {

    private Node root;

    public FlattenBSTToSinglyLLWithoutAuxSpace() {
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


        FlattenBSTToSinglyLLWithoutAuxSpace bst = new FlattenBSTToSinglyLLWithoutAuxSpace();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        System.out.println("Flattened BST is given as follows");
        SLL list = new SLL();
        bst.flattenBSTIntoSinglyLinkedList(bst.getRoot(), list);
        bst.inOrderTraversalOfBST(bst.getRoot());
        System.out.println("\nTime complexity is O(N)");
    }

    /*
        The idea is not to use any auxiliary data structure
     */
    public void flattenBSTIntoSinglyLinkedList(Node rootReference, SLL list) {
        if (rootReference == null)
            return;
        flattenBSTIntoSinglyLinkedList(rootReference.getRightChild(), list);
        flattenBSTIntoSinglyLinkedList(rootReference.getLeftChild(), list);
        rootReference.setLeftChild(null);
        rootReference.setRightChild(list.getPrevious());
        list.setPrevious(rootReference);
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

    private static class SLL {

        private Node previous;

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
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
