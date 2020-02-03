package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 2/3/2020 4:02 PM
 * This class demonstrates how to check if a given BST is a balanced BST
 */
//Reference : https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
public class BalancedBSTCheck {

    private Node root;

    public BalancedBSTCheck() {
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

        BalancedBSTCheck bst = new BalancedBSTCheck();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        boolean isBalancedBST = bst.isBSTBalancedOptimized(bst.getRoot());
        if (isBalancedBST) {
            System.out.println("BST is balanced BST");
            System.out.println("Time complexity is O(N^2)");
        } else {
            System.out.println("BST is not a balanced BST");
        }

        System.out.println();


        isBalancedBST = bst.isBSTBalancedOptimized(bst.getRoot(), new Height());
        if (isBalancedBST) {
            System.out.println("BST is balanced BST");
            System.out.println("Time complexity is O(N) because height is being calculated in same recursion");
        } else {
            System.out.println("BST is not a balanced BST");
        }
    }

    public boolean isBSTBalancedOptimized(Node rootReference, Height height) {
        if (rootReference == null) {
            height.setHeight(0);
            return true;
        }

        Height leftSubTreeHeight = new Height();
        Height rightSubTreeHeight = new Height();

        boolean isLeftSubTreeBalanced = isBSTBalancedOptimized(rootReference.getLeftChild(), leftSubTreeHeight);
        boolean isRightSubTreeBalanced = isBSTBalancedOptimized(rootReference.getRightChild(), rightSubTreeHeight);
        height.setHeight(Math.max(leftSubTreeHeight.getHeight(), rightSubTreeHeight.getHeight()) + 1);

        if (Math.abs(leftSubTreeHeight.getHeight() - rightSubTreeHeight.getHeight()) >= 2)
            return false;
        else
            return isLeftSubTreeBalanced && isRightSubTreeBalanced;
    }

    public boolean isBSTBalancedOptimized(Node rootReference) {
        if (rootReference == null) {
            return true;
        } else {
            int leftSubTreeHeight = calculateHeightOfBST(rootReference.getLeftChild());
            int rightSubTreeHeight = calculateHeightOfBST(rootReference.getRightChild());
            if (Math.abs(leftSubTreeHeight - rightSubTreeHeight) <= 1 && isBSTBalancedOptimized(rootReference.getLeftChild())
                    && isBSTBalancedOptimized(rootReference.getRightChild())) {
                return true;
            } else {
                return false;
            }
        }
    }

    private int calculateHeightOfBST(Node rootReference) {
        if (rootReference == null)
            return 0;
        else {
            int leftSubTreeHeight = calculateHeightOfBST(rootReference.getLeftChild());
            int rightSubTreeHeight = calculateHeightOfBST(rootReference.getRightChild());
            return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
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

    private static class Height {
        private int height;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
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
