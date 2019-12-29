package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 10/31/2019 12:43 PM
 * This class demonstrates how to print all the paths from root to the leaf nodes
 */
//Reference : https://www.youtube.com/watch?v=zIkDfgFAg60
//Reference : https://ide.geeksforgeeks.org/RhEpoc
public class PrintPathFromRootToAllLeafNodesOfBST {

    private Node root;

    public PrintPathFromRootToAllLeafNodesOfBST() {
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
                                    /     \
                                   15       25
                                  /  \     /  \
                                 8   16  24   30
                               /                \
                              4                  35
         */

        PrintPathFromRootToAllLeafNodesOfBST bst = new PrintPathFromRootToAllLeafNodesOfBST();

        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(4);
        bst.insertNodeIntoBSTIteratively(35);

        System.out.println("Paths from root to every leaf node are given as follows");
        bst.printAllPathsFromRootToLeafNodes(bst.getRoot(), new Stack<>());
        System.out.println("Time complexity is O(N), as we are using in-order traversal of BST approach");

        System.out.println();
        bst.printAllPathsFromRootToLeafNodesAlternate(bst.getRoot(), "");
        System.out.println("Time complexity is O(N)");

    }

    public void printAllPathsFromRootToLeafNodesAlternate(Node rootReference, String path) {
        if (rootReference == null)
            return;

        path = path + " " + rootReference.getData();

        printAllPathsFromRootToLeafNodesAlternate(rootReference.getLeftChild(), path);

        if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
            System.out.println(path);
        }

        printAllPathsFromRootToLeafNodesAlternate(rootReference.getRightChild(), path);
    }

    public void printAllPathsFromRootToLeafNodes(Node rootReference, Stack<Node> stack) {
        if (rootReference == null)
            return;

        stack.push(rootReference);

        printAllPathsFromRootToLeafNodes(rootReference.getLeftChild(), stack);

        //Print the stack as soon as we reach to the leaf node
        if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
            System.out.println(stack);
        }

        printAllPathsFromRootToLeafNodes(rootReference.getRightChild(), stack);

        stack.pop();
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNode = null;
            Node traversingNode = root;
            while (true) {
                currentlyProcessingNode = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        currentlyProcessingNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        currentlyProcessingNode.setRightChild(newNode);
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

        @Override
        public String toString() {
            return "" + data;
        }
    }
}
