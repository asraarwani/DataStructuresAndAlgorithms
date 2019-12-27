package com.some_domain.www.binarytree;

/**
 * @author : waniasra
 * @date : 10/31/2019 12:22 PM
 * This class demonstrates how to check if two binary trees are isomorphic
 */

/**
 * Two trees are called isomorphic if one of them can be obtained from other by a series of flips,
 * i.e. by swapping left and right children of a number of nodes.
 * Any number of nodes at any level can have their children swapped.
 * Two empty trees are isomorphic.
 * <p>
 * There are following two conditions for subtrees rooted with n1 and n2 to be isomorphic.
 * 1) Data of n1 and n2 is same.
 * 2) One of the following two is true for children of n1 and n2:
 * a) Left child of n1 is isomorphic to left child of n2 and right child of n1 is isomorphic to right child of n2.
 * b) Left child of n1 is isomorphic to right child of n2 and right child of n1 is isomorphic to left child of n2.
 */

//Reference : https://www.youtube.com/watch?v=9Eo42meRcrY
//Reference : https://www.geeksforgeeks.org/tree-isomorphism-problem/
public class IsomorphicCheckOnBinaryTrees {

    private Node root;

    public IsomorphicCheckOnBinaryTrees() {
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

        //Note : For simplicity, I  am going to create two BSTs
        IsomorphicCheckOnBinaryTrees firstBinaryTree = new IsomorphicCheckOnBinaryTrees();
        firstBinaryTree.insertNodeIntoBinaryTreeIteratively(20);
        firstBinaryTree.insertNodeIntoBinaryTreeIteratively(15);
        firstBinaryTree.insertNodeIntoBinaryTreeIteratively(25);
        firstBinaryTree.insertNodeIntoBinaryTreeIteratively(8);
        firstBinaryTree.insertNodeIntoBinaryTreeIteratively(16);
        firstBinaryTree.insertNodeIntoBinaryTreeIteratively(24);
        firstBinaryTree.insertNodeIntoBinaryTreeIteratively(30);

        IsomorphicCheckOnBinaryTrees secondBinaryTree = new IsomorphicCheckOnBinaryTrees();
        secondBinaryTree.insertNodeIntoBinaryTreeIteratively(20);
        secondBinaryTree.insertNodeIntoBinaryTreeIteratively(15);
        secondBinaryTree.insertNodeIntoBinaryTreeIteratively(25);
        secondBinaryTree.insertNodeIntoBinaryTreeIteratively(8);
        secondBinaryTree.insertNodeIntoBinaryTreeIteratively(16);
        secondBinaryTree.insertNodeIntoBinaryTreeIteratively(24);
        secondBinaryTree.insertNodeIntoBinaryTreeIteratively(30);

        boolean areTwoBTsIsomorphic = new IsomorphicCheckOnBinaryTrees().areTwoBinaryTreesIsomorphic(firstBinaryTree.getRoot(), secondBinaryTree.getRoot());
        if (areTwoBTsIsomorphic) {
            System.out.println("Two Binary trees are isomorphic");
        } else {
            System.out.println("Two Binary trees aren't isomorphic");
        }

        System.out.println("\nTime complexity is O(M + N) where N and M are the number of nodes in two Binary trees");
        System.out.println("Since every node is visited at most two times, complexity is going to be O(M + N)");
    }

    public boolean areTwoBinaryTreesIsomorphic(Node firstBTRootReference, Node secondBTRootReference) {
        if (firstBTRootReference == null && secondBTRootReference == null)
            return true;
        if (firstBTRootReference == null || secondBTRootReference == null)
            return false;
        if (firstBTRootReference.getData() != secondBTRootReference.getData())
            return false;
        if ((areTwoBinaryTreesIsomorphic(firstBTRootReference.getLeftChild(), secondBTRootReference.getLeftChild())
                && areTwoBinaryTreesIsomorphic(firstBTRootReference.getRightChild(), secondBTRootReference.getRightChild()))
                || (areTwoBinaryTreesIsomorphic(firstBTRootReference.getLeftChild(), secondBTRootReference.getRightChild())
                && areTwoBinaryTreesIsomorphic(firstBTRootReference.getRightChild(), secondBTRootReference.getLeftChild())))
            return true;
        return false;
    }

    /*
        Creating a BST
     */
    public void insertNodeIntoBinaryTreeIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNode = root;
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
