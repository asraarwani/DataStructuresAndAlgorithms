package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 11/27/2019 9:37 PM
 * This class demonstrates how to remove all the leaf nodes from a BST
 */
//Reference : https://www.geeksforgeeks.org/remove-leaf-nodes-binary-search-tree/
public class RemoveAllLeafNodesFromBST {

    private Node root;

    public RemoveAllLeafNodesFromBST() {
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


        RemoveAllLeafNodesFromBST bst = new RemoveAllLeafNodesFromBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(4);

        System.out.println("In order traversal of BST before leaf nodes are deleted");
        bst.printInOrderTraversalOfBST(bst.getRoot());

        System.out.println();
        Node updatedRoot = bst.deleteLeafNodes(bst.getRoot());
        bst.setRoot(updatedRoot);
        System.out.println("\nIn order traversal of BST after leaf nodes are deleted");
        bst.printInOrderTraversalOfBST(bst.getRoot());

        System.out.println("\nTime complexity is O(N) where N is the number of the nodes in the BST");
    }

    /*
        The idea is to use pre-order traversal of  BST.
        During traversal we check if a node is leaf node, we delete it else we recur for left and right sub trees
     */
    public Node deleteLeafNodes(Node rootReference) {
        if (rootReference == null)
            return null;

        if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null)
            return null;

        rootReference.setLeftChild(deleteLeafNodes(rootReference.getLeftChild()));

        rootReference.setRightChild(deleteLeafNodes(rootReference.getRightChild()));

        return rootReference;
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node traversingNodeParent = null;
            Node traversingNode = root;
            while (true) {
                traversingNodeParent = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        traversingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        traversingNodeParent.setRightChild(newNode);
                        break;
                    }
                }
            }
        }
    }

    public void printInOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            printInOrderTraversalOfBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            printInOrderTraversalOfBST(rootReference.getRightChild());
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
