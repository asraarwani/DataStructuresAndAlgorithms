package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 10/16/2019 10:37 PM
 * This class demonstrates boundary traversal of BST
 */
//Reference : https://www.youtube.com/watch?v=uemjIijtu2I
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
         */

        BoundaryTraversalOfBST bst = new BoundaryTraversalOfBST();

        //Inserting some nodes into BST
        Node rootReference = bst.insertNodeIntoBSTRecursivelyBST(20, null);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(30, rootReference);

        bst.printBoundaryOfBST(rootReference);
    }

    public void printBoundaryOfBST(Node rootReference) {

        //Print the left boundary of the BST
        printLeftBoundaryOfBST(rootReference);

        //Print  the right boundary of the BST
        printRightBoundaryOfBST(rootReference.getRightChild());

        //Print the leaf boundary of the BST
        printLeafBoundaryOfBST(rootReference);
    }

    /**
     * Prints the left boundary of the BST
     *
     * @param rootReference reference to the root node of the BST
     */
    private void printLeftBoundaryOfBST(Node rootReference) {
        if (rootReference != null) {
            if (rootReference.getLeftChild() != null) {        //If the processing node has left child
                System.out.print(rootReference.getData() + " ");
                printLeftBoundaryOfBST(rootReference.getLeftChild());
            } else if (rootReference.getRightChild() != null) { //If the processing node has right child
                printLeftBoundaryOfBST(rootReference.getRightChild());
            }
        }
    }

    /**
     * Prints the right boundary of the BST
     *
     * @param rootReference reference to the immediate right child of the root node of BST (immediate right child to avoid printing root node twice)
     */
    private void printRightBoundaryOfBST(Node rootReference) {
        if (rootReference != null) {
            if (rootReference.getRightChild() != null) {       //If the processing node has right child
                System.out.print(rootReference.getData() + " ");
                printRightBoundaryOfBST(rootReference.getRightChild());
            } else if (rootReference.getLeftChild() != null) {  //If the processing node has left child
                printRightBoundaryOfBST(rootReference.getLeftChild());
            }
        }
    }

    /**
     * Prints the leaf boundary of the BST
     *
     * @param rootReference reference to the root node of the BST
     */
    private void printLeafBoundaryOfBST(Node rootReference) {
        if (rootReference != null) {
            printLeafBoundaryOfBST(rootReference.getLeftChild());
            //If processing node doesn't have left and right child, meaning it is a leaf node and we need to print it
            if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
                System.out.print(rootReference.getData() + " ");
            }
            printLeafBoundaryOfBST(rootReference.getRightChild());
        }
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
