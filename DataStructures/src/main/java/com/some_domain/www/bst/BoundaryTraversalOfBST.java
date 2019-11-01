package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 10/16/2019 10:37 PM
 * This class demonstrates anti-clockwise boundary order traversal of BST
 */
//Reference : https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
public class BoundaryTraversalOfBST {

    private Node root;

    public BoundaryTraversalOfBST() {
        this.root = null;
    }


    public static void main(String[] args) {


        /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
                                                 \
                                                  40
         */

        // 20 -> 15 ->  8 -> 16  -> 24  -> 40  -> 30  -> 25
        BoundaryTraversalOfBST bst = new BoundaryTraversalOfBST();

        //Inserting some nodes into BST
        Node rootReference = bst.insertNodeIntoBSTRecursivelyBST(20, null);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(30, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(40, rootReference);

        bst.printBoundaryOfBST(rootReference);

        System.out.println("\nTime complexity is O(N)");
    }

    public void printBoundaryOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            System.out.print(rootReference.getData() + " ");
            printLeftBoundaryOfBST(rootReference.getLeftChild());   // Top to bottom for the left boundary
            printLeafBoundaryOfBST(rootReference.getLeftChild());   // Left to right for leaf node of the left sub-tree
            printLeafBoundaryOfBST(rootReference.getRightChild());  // Left to right for leaf node of the right sub-tree
            printRightBoundaryOfBST(rootReference.getRightChild()); // Bottom to top for the right boundary
        }
    }

    private void printLeftBoundaryOfBST(Node rootReference) {
        if (rootReference == null)
            return;
        if (rootReference.getLeftChild() != null) {
            System.out.print(rootReference.getData() + " ");
            printLeftBoundaryOfBST(rootReference.getLeftChild());
        } else if (rootReference.getRightChild() != null) {
            System.out.print(rootReference.getData() + " ");
            printLeftBoundaryOfBST(rootReference.getRightChild());
        }
    }

    private void printRightBoundaryOfBST(Node rootReference) {
        if (rootReference == null)
            return;
        if (rootReference.getRightChild() != null) {
            printRightBoundaryOfBST(rootReference.getRightChild());
            System.out.print(rootReference.getData() + " ");
        } else if (rootReference.getLeftChild() != null) {
            printRightBoundaryOfBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
        }
    }

    private void printLeafBoundaryOfBST(Node rootReference) {
        if (rootReference == null)
            return;
        printLeafBoundaryOfBST(rootReference.getLeftChild());
        if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
            System.out.print(rootReference.getData() + " ");
        }
        printLeafBoundaryOfBST(rootReference.getRightChild());
    }

    public Node insertNodeIntoBSTRecursivelyBST(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getLeftChild()));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getRightChild()));
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

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
