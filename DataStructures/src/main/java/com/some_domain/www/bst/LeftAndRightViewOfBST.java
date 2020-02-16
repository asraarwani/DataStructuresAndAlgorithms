package com.some_domain.www.bst;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 10/21/2019 10:04 PM
 * This class demonstrates left and right view of a BST both iteratively and recursively
 */
//Reference : https://www.youtube.com/watch?v=eBdKNoW3VJg
//Reference : https://www.geeksforgeeks.org/print-left-view-binary-tree/
//Reference : https://www.geeksforgeeks.org/print-right-view-binary-tree-2/
public class LeftAndRightViewOfBST {

    private Node root;
    private static int maxLevel;

    public LeftAndRightViewOfBST() {
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

        LeftAndRightViewOfBST bst = new LeftAndRightViewOfBST();
        Node rootReference = bst.insertNodeIntoBSTIteratively(20, null);
        rootReference = bst.insertNodeIntoBSTIteratively(15, rootReference);
        rootReference = bst.insertNodeIntoBSTIteratively(25, rootReference);
        rootReference = bst.insertNodeIntoBSTIteratively(8, rootReference);
        rootReference = bst.insertNodeIntoBSTIteratively(16, rootReference);
        rootReference = bst.insertNodeIntoBSTIteratively(24, rootReference);
        rootReference = bst.insertNodeIntoBSTIteratively(30, rootReference);

        bst.setRoot(rootReference);

        System.out.println("Left view of the BST using iterative approach is given as follows");
        List<List<Node>> nodeListAtEachLevel = bst.printLeftAndRightViewOfBSTIteratively(bst.getRoot());
        nodeListAtEachLevel.stream().forEach(nodeList -> {
            List<Integer> list = nodeList.stream().map(node -> node.getData()).collect(Collectors.toList());
            System.out.print(list.get(0) + " "); //Printing only first node at each level (Left view of the BST)
        });

        System.out.println("\nRight view of the BST using iterative approach is given as follows");
        nodeListAtEachLevel.stream().forEach(nodeList -> {
            List<Integer> list = nodeList.stream().map(node -> node.getData()).collect(Collectors.toList());
            System.out.print(list.get(list.size() - 1) + " "); //Printing only last node at each level (Right view of the BST)
        });
        System.out.println("\nTime complexity for above approach is going to be O(N)");

        System.out.println("\nLeft view of the BST using recursive approach is given as follows");
        bst.printLeftViewRecursively(bst.getRoot(), 1);
        System.out.println("\nTime complexity for this approach is O(N)");

        //Resetting the global/class-level variable
        maxLevel = 0;

        System.out.println("\nRight view of the BST using recursive approach is given as follows");
        bst.printRightViewOfBSTRecursively(bst.getRoot(), 1);
        System.out.println("\nTime complexity for this approach is O(N)");

        System.out.println("\nLeft view");
        bst.printLeftViewOfBSTAlternateApproach(bst.getRoot(), new NodeDetails(), 1);
        System.out.println("\nRight view");
        bst.printRightViewOfBSTAlternateApproach(bst.getRoot(), new NodeDetails(), 1);
        System.out.println("\nTime complexity for above two approaches is also O(N)");
    }

    public List<List<Node>> printLeftAndRightViewOfBSTIteratively(Node rootReference) {
        List<List<Node>> resultList = new ArrayList<>();
        if (rootReference == null) {
            return resultList;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(rootReference);
        while (!queue.isEmpty()) {
            int currentSizeOfQueue = queue.size();
            List<Node> nodesAtCurrentLevelList = new ArrayList<>();
            for (int i = 0; i < currentSizeOfQueue; i++) {
                Node polledNode = queue.poll();
                nodesAtCurrentLevelList.add(polledNode);

                //If polledNode has a left child
                if (polledNode.getLeftChild() != null) {
                    queue.offer(polledNode.getLeftChild());
                }

                //If polledNode has a right child
                if (polledNode.getRightChild() != null) {
                    queue.offer(polledNode.getRightChild());
                }
            }
            resultList.add(nodesAtCurrentLevelList);
        }
        return resultList;
    }

    /**
     * We can keep track of the level of a node by passing a parameter to all recursive calls.
     * The idea is to keep track of the maximum level also.
     * Whenever we see a node whose level is more than maximum level so far,
     * we print the node because this is the first node in its level.
     * Note that we traverse the left subtree before right subtree.
     *
     * @param rootReference reference to the root node of the BST
     */
    public void printLeftViewRecursively(Node rootReference, int level) {
        if (rootReference == null) {
            return;
        }

        //If this is the first of node of its level
        if (maxLevel < level) {
            System.out.print(rootReference.getData() + " ");
            maxLevel = level;
        }

        printLeftViewRecursively(rootReference.getLeftChild(), level + 1);
        printLeftViewRecursively(rootReference.getRightChild(), level + 1);
    }

    /**
     * We can keep track of level of a node by passing a parameter to all recursive calls.
     * The idea is to keep track of maximum level also.
     * And traverse the tree in a manner that right subtree is visited before left subtree.
     * Whenever we see a node whose level is more than maximum level so far,
     * we print the node because this is the last node in its level.
     * Note that we traverse the right subtree before left subtree.
     *
     * @param rootReference reference to the root node of the BST
     * @param level
     */
    public void printRightViewOfBSTRecursively(Node rootReference, int level) {
        if (rootReference == null) {
            return;
        }

        //If this is the first node of its level
        if (maxLevel < level) {
            System.out.print(rootReference.getData() + " ");
            maxLevel = level;
        }

        printRightViewOfBSTRecursively(rootReference.getRightChild(), level + 1);
        printRightViewOfBSTRecursively(rootReference.getLeftChild(), level + 1);
    }

    public Node insertNodeIntoBSTIteratively(int dataOfNodeToBeInserted, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(dataOfNodeToBeInserted, null, null);
        } else if (dataOfNodeToBeInserted <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTIteratively(dataOfNodeToBeInserted, rootReference.getLeftChild()));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTIteratively(dataOfNodeToBeInserted, rootReference.getRightChild()));
        }
        return rootReference;
    }

    /*
        For left view,left sub tree is being processed before right sub tree
     */
    public void printLeftViewOfBSTAlternateApproach(Node rootReference, NodeDetails nodeDetails, int currentLevelNumber) {
        if (rootReference == null)
            return;

        if (nodeDetails.getGlobalMaxLevel() < currentLevelNumber) {
            System.out.print(rootReference.getData() + " ");
            nodeDetails.setGlobalMaxLevel(currentLevelNumber);
        }

        printLeftViewOfBSTAlternateApproach(rootReference.getLeftChild(), nodeDetails, currentLevelNumber + 1);

        printLeftViewOfBSTAlternateApproach(rootReference.getRightChild(), nodeDetails, currentLevelNumber + 1);
    }

    /*
       For right view, right sub tree is being processed before left sub tree
    */
    public void printRightViewOfBSTAlternateApproach(Node rootReference, NodeDetails nodeDetails, int currentLevelNumber) {
        if (rootReference == null)
            return;

        if (nodeDetails.getGlobalMaxLevel() < currentLevelNumber) {
            System.out.print(rootReference.getData() + " ");
            nodeDetails.setGlobalMaxLevel(currentLevelNumber);
        }

        printRightViewOfBSTAlternateApproach(rootReference.getRightChild(), nodeDetails, currentLevelNumber + 1);

        printRightViewOfBSTAlternateApproach(rootReference.getLeftChild(), nodeDetails, currentLevelNumber + 1);
    }

    private static class NodeDetails {
        private int globalMaxLevel;

        public int getGlobalMaxLevel() {
            return globalMaxLevel;
        }

        public void setGlobalMaxLevel(int globalMaxLevel) {
            this.globalMaxLevel = globalMaxLevel;
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
