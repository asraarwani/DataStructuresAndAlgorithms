package com.some_domain.www.bst;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : waniasra
 * @date : 11/1/2019 10:54 AM
 * This class demonstrates how to print the path from root node to any node in BST
 */
//Reference : https://www.geeksforgeeks.org/print-path-root-given-node-binary-tree/
//Reference : https://algorithms.tutorialhorizon.com/print-a-path-from-root-to-node-in-binary-tree/    -> Alternate approach
public class PrintPathFromRootToGivenNode {

    private Node root;

    public PrintPathFromRootToGivenNode() {
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


        PrintPathFromRootToGivenNode bst = new PrintPathFromRootToGivenNode();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        int node = 16;
        bst.printPathFromRootToGivenNode(bst.getRoot(), node);

        System.out.println();
        Node givenNode = bst.getRoot().getLeftChild().getRightChild();
        bst.printPathFromRootToGivenNodeAlternate(bst.getRoot(), givenNode);
    }

    public void printPathFromRootToGivenNodeAlternate(Node rootReference, Node givenNode) {
        List<Node> path = new ArrayList<>();
        System.out.println("You are looking for node [" + givenNode.getData() + " ]");
        if (printPathFromRootToGivenNodeAlternateHelper(rootReference, givenNode, path)) {
            Collections.reverse(path);
            path.stream().forEach(node -> System.out.print(node.getData() + " "));
            System.out.println("\nTime complexity is O(N) where N is the number of nodes in BST");
        } else {
            System.out.println("Path doesn't exist, make sure you are looking for the node which is actually present in the BST");
        }
    }

    private boolean printPathFromRootToGivenNodeAlternateHelper(Node rootReference, Node givenNode, List<Node> path) {
        if (rootReference == null)
            return false;
        if (rootReference == givenNode || printPathFromRootToGivenNodeAlternateHelper(rootReference.getLeftChild(), givenNode, path)
                || printPathFromRootToGivenNodeAlternateHelper(rootReference.getRightChild(), givenNode, path)) {
            path.add(rootReference);
            return true;
        }
        return false;
    }

    public void printPathFromRootToGivenNode(Node rootReference, int node) {
        List<Integer> path = new ArrayList<>();
        System.out.println("You are looking for node [" + node + " ]");
        if (printPathFromRootToGivenNodeHelper(rootReference, node, path)) {
            path.stream().forEach(item -> System.out.print(item + " "));
            System.out.println("\nTime complexity is O(N) where N is the number of nodes in BST");
        } else {
            System.out.println("Path doesn't exist, make sure you are looking for the node which is actually present in the BST");
        }
    }

    private boolean printPathFromRootToGivenNodeHelper(Node rootReference, int node, List<Integer> list) {

        if (rootReference == null)
            return false;

        list.add(rootReference.getData());

        if (rootReference.getData() == node)
            return true;

        if (printPathFromRootToGivenNodeHelper(rootReference.getLeftChild(), node, list) ||
                printPathFromRootToGivenNodeHelper(rootReference.getRightChild(), node, list))
            return true;
        else {
            list.remove(list.size() - 1);
            return false;
        }
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
    }
}
