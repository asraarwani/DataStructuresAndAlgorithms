package com.some_domain.www.bst;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * @author : waniasra
 * @date : 10/18/2019 11:34 AM
 * This class demonstrates the basic operations on a BST
 */
public class BST {

    private Node root;

    public BST() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        BST bst = new BST();


          /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        Node rootReference = bst.insertNodeIntoBSTRecursively(20, null);
        rootReference = bst.insertNodeIntoBSTRecursively(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursively(30, rootReference);
        bst.setRoot(rootReference);

        /* //Inserting node into BST iteratively
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        */

        System.out.println("InOrder traversal of BST");
        bst.inOrderTraversalOfBST(bst.getRoot());

        System.out.println("\nPreOrder traversal of BST");
        bst.preOrderTraversalOfBST(bst.getRoot());

        System.out.println("\nPostOrder traversal of BST");
        bst.postOrderTraversalOfBST(bst.getRoot());

        System.out.println();
        System.out.println();
        Node searchedNode = bst.searchForNodeInBST(16, bst.getRoot());
        if (searchedNode != null) {
            System.out.println("Node is present in the BST");
        } else {
            System.out.println("Node is not present in the BST");
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

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node parentNode = root;
            Node traversingNode = root;
            while (true) {
                parentNode = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        parentNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        parentNode.setRightChild(newNode);
                        break;
                    }
                }
            }
        }
    }

    public void inOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBST(rootReference.getRightChild());
        }
    }

    public void preOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            System.out.print(rootReference.getData() + " ");
            preOrderTraversalOfBST(rootReference.getLeftChild());
            preOrderTraversalOfBST(rootReference.getRightChild());
        }
    }

    public void postOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            postOrderTraversalOfBST(rootReference.getLeftChild());
            postOrderTraversalOfBST(rootReference.getRightChild());
            System.out.print(rootReference.getData() + " ");
        }
    }

    public Node searchForNodeInBST(int data, Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
            return null;
        } else {
            Node traversingNode = rootReference;
            while (traversingNode.getData() != data) {
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                } else {
                    traversingNode = traversingNode.getRightChild();
                }
                if (traversingNode == null) {
                    return null;
                }
            }
            return traversingNode;
        }
    }

    public Node deleteNodeFromBST(int data, Node rootReference) {
        //To be implemented
        return null;
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
