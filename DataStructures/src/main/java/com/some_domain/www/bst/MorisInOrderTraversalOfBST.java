package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 1/11/2020 12:02 PM
 * This class demonstrates how to print in-order traversal of a given BST using Moris Algorithm
 */
//Reference : https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
public class MorisInOrderTraversalOfBST {

    private Node root;

    public MorisInOrderTraversalOfBST() {
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


        MorisInOrderTraversalOfBST bst = new MorisInOrderTraversalOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printInOrderTraversalUsingMorisAlgorithm(bst.getRoot());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in the BST");
    }

    public void printInOrderTraversalUsingMorisAlgorithm(Node rootReference) {
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
                    //Find the in-order predecessor of the current node, which is going to be the right most node in the left sub-tree
                    Node predecessorNode = currentNode.getLeftChild();
                    while (predecessorNode.getRightChild() != null && predecessorNode.getRightChild() != currentNode) {
                        predecessorNode = predecessorNode.getRightChild();
                    }

                    if (predecessorNode.getRightChild() == null) {  //Make current as the right child
                        predecessorNode.setRightChild(currentNode);
                        currentNode = currentNode.getLeftChild();
                    } else {                                         // Revert the changes made in if block to restore the tree
                        predecessorNode.setRightChild(null);
                        System.out.print(currentNode.getData() + " ");
                        currentNode = currentNode.getRightChild();
                    }
                }
            }
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
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
