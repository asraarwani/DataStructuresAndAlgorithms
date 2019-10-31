package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 10/31/2019 12:22 PM
 * This class demonstrates how to check if two BSTs are isomorphic
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
public class IsomorphicCheckOnBST {

    private Node root;

    public IsomorphicCheckOnBST() {
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

        IsomorphicCheckOnBST firstBST = new IsomorphicCheckOnBST();
        firstBST.insertNodeIntoBSTIteratively(20);
        firstBST.insertNodeIntoBSTIteratively(15);
        firstBST.insertNodeIntoBSTIteratively(25);
        firstBST.insertNodeIntoBSTIteratively(8);
        firstBST.insertNodeIntoBSTIteratively(16);
        firstBST.insertNodeIntoBSTIteratively(24);
        firstBST.insertNodeIntoBSTIteratively(30);

        IsomorphicCheckOnBST secondBST = new IsomorphicCheckOnBST();
        secondBST.insertNodeIntoBSTIteratively(20);
        secondBST.insertNodeIntoBSTIteratively(15);
        secondBST.insertNodeIntoBSTIteratively(25);
        secondBST.insertNodeIntoBSTIteratively(8);
        secondBST.insertNodeIntoBSTIteratively(16);
        secondBST.insertNodeIntoBSTIteratively(24);
        secondBST.insertNodeIntoBSTIteratively(30);

        boolean areTwoBSTsIsomorphic = new IsomorphicCheckOnBST().areTwoBSTsIsomorphic(firstBST.getRoot(), secondBST.getRoot());
        if (areTwoBSTsIsomorphic) {
            System.out.println("Two BSTs are isomorphic");
        } else {
            System.out.println("Two BSTs aren't isomorphic");
        }

        System.out.println("\nTime complexity is O(M + N) where N and M are the number of nodes in two BSTs");
        System.out.println("Since every node is visited at most two times, complexity is going to be O(M + N)");
    }

    public boolean areTwoBSTsIsomorphic(Node firstBSTRootReference, Node secondBSTRootReference) {
        if (firstBSTRootReference == null && secondBSTRootReference == null)
            return true;
        if (firstBSTRootReference == null || secondBSTRootReference == null)
            return false;
        if (firstBSTRootReference.getData() != secondBSTRootReference.getData())
            return false;
        if ((areTwoBSTsIsomorphic(firstBSTRootReference.getLeftChild(), secondBSTRootReference.getLeftChild())
                && areTwoBSTsIsomorphic(firstBSTRootReference.getRightChild(), secondBSTRootReference.getRightChild()))
                || (areTwoBSTsIsomorphic(firstBSTRootReference.getLeftChild(), secondBSTRootReference.getRightChild())
                && areTwoBSTsIsomorphic(firstBSTRootReference.getRightChild(), secondBSTRootReference.getLeftChild())))
            return true;
        return false;
    }

    public void insertNodeIntoBSTIteratively(int data) {
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
