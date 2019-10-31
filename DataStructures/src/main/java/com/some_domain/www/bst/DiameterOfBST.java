package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 10/30/2019 11:25 AM
 * This class demonstrates how to find the diameter of a BST
 */

/**
 * The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two end nodes.
 * The diameter of a tree T is the largest of the following quantities:
 * the diameter of T’s left subtree
 * the diameter of T’s right subtree
 * the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)
 */

//Reference : https://www.youtube.com/watch?v=ey7DYc9OANo
//Reference : https://www.geeksforgeeks.org/diameter-of-a-binary-tree/
public class DiameterOfBST {

    private Node root;

    public DiameterOfBST() {
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
        //Diameter : 4-> 8 -> 15 -> 20 -> 25 -> 30

        DiameterOfBST bst = new DiameterOfBST();

        //Inserting nodes into BST iteratively
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(4);


        System.out.println();
        int diameterOfBST = bst.findDiameterOfBST(bst.getRoot());
        System.out.println("Diameter of BST is : " + diameterOfBST + " . Time complexity is O(N^2)");

        diameterOfBST = bst.findDiameterOFBSTOptimized(bst.getRoot(), new Height());
        System.out.println("Diameter of BST is : " + diameterOfBST + " . Time complexity is O(N) as height is being calculated in the same recursive function.");
    }

    /**
     * Calculates height in the same recursion rather than having a separate height calculating function
     *
     * @param rootReference
     * @return
     */
    public int findDiameterOFBSTOptimized(Node rootReference, Height height) {
        Height leftSubTreeHeight = new Height();
        Height rightSubTreeHeight = new Height();

        if (rootReference == null) {
            height.setHeight(0);
            return 0;
        }

        int leftSubTreeDiameter = findDiameterOFBSTOptimized(rootReference.getLeftChild(), leftSubTreeHeight);
        int rightSubTreeDiameter = findDiameterOFBSTOptimized(rootReference.getRightChild(), rightSubTreeHeight);

        height.setHeight(Math.max(leftSubTreeHeight.getHeight(), rightSubTreeHeight.getHeight()) + 1);

        int resultDiameterOfBST = Math.max((leftSubTreeHeight.getHeight() + rightSubTreeHeight.getHeight() + 1),
                Math.max(leftSubTreeDiameter, rightSubTreeDiameter));
        return resultDiameterOfBST;
    }

    public int findDiameterOfBST(Node rootReference) {
        if (rootReference == null)
            return 0;
        else {
            int leftSubTreeHeight = calculateHeightOfST(rootReference.getLeftChild());
            int rightSubTreeHeight = calculateHeightOfST(rootReference.getRightChild());
            int leftSubTreeDiameter = findDiameterOfBST(rootReference.getLeftChild());
            int rightSubTreeDiameter = findDiameterOfBST(rootReference.getRightChild());
            int resultDiameterOfBST = Math.max((leftSubTreeHeight + rightSubTreeHeight + 1), Math.max(leftSubTreeDiameter, rightSubTreeDiameter));
            return resultDiameterOfBST;
        }
    }

    private int calculateHeightOfST(Node rootReference) {
        if (rootReference == null)
            return 0;
        else {
            int leftSubTreeHeight = calculateHeightOfST(rootReference.getLeftChild());
            int rightSubTreeHeight = calculateHeightOfST(rootReference.getRightChild());
            return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processingNodeParent = null;
            Node traversingNode = root;
            while (true) {
                processingNodeParent = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        processingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        processingNodeParent.setRightChild(newNode);
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

    private static class Height {

        private int height;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
