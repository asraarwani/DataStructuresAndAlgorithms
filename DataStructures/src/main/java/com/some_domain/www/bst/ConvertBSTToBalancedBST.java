package com.some_domain.www.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : waniasra
 * @date : 12/21/2019 11:48 AM
 * This class demonstrates how to convert a BST to balanced BST
 */
//Reference : https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/   Approach 2
public class ConvertBSTToBalancedBST {

    private Node root;

    public ConvertBSTToBalancedBST() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        ConvertBSTToBalancedBST bst = new ConvertBSTToBalancedBST();

        //Constructing a unbalanced binary search tree
        bst.constructUnbalancedBST();

        Node balancedBSTRootReference = bst.convertNormalBSTToBalancedBST(bst.getRoot());

        System.out.println("In order traversal of balanced BST is given as follows");
        bst.inOrderTraversalOfBSTIteratively(balancedBSTRootReference);

        System.out.println("\nTime complexity is O(N) + O(N) for first and second step and which is equal to O(N)");
    }

    public Node convertNormalBSTToBalancedBST(Node rootReference) {
        List<Node> list = new ArrayList<>();
        constructInOrderTraversalOfBST(rootReference, list);
        int startingIndex = 0;
        int endingIndex = list.size() - 1;
        return buildBalancedBSTFromInOrderTraversal(list, startingIndex, endingIndex);
    }

    private Node buildBalancedBSTFromInOrderTraversal(List<Node> list, int startingIndex, int endingIndex) {
        if (startingIndex > endingIndex)
            return null;
        int middleIndex = (startingIndex + endingIndex) / 2;

        Node root = list.get(middleIndex);

        root.setLeftChild(buildBalancedBSTFromInOrderTraversal(list, startingIndex, middleIndex - 1));
        root.setRightChild(buildBalancedBSTFromInOrderTraversal(list, middleIndex + 1, endingIndex));
        return root;
    }

    private void constructInOrderTraversalOfBST(Node rootReference, List<Node> list) {
        if (rootReference != null) {
            constructInOrderTraversalOfBST(rootReference.getLeftChild(), list);
            list.add(rootReference);
            constructInOrderTraversalOfBST(rootReference.getRightChild(), list);
        }
    }

    public void constructUnbalancedBST() {
        /*Input:
                         30
                        /
                      20
                     /
                   10
                  /
                 5

         */
        root = new Node(30, null, null);
        root.setLeftChild(new Node(20, null, null));
        root.getLeftChild().setLeftChild(new Node(10, null, null));
        root.getLeftChild().getLeftChild().setLeftChild(new Node(5, null, null));
    }

    private void inOrderTraversalOfBSTIteratively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
        } else {
            Stack<Node> stack = new Stack<>();
            while (true) {
                while (rootReference != null) {
                    stack.push(rootReference);
                    rootReference = rootReference.getLeftChild();
                }
                if (stack.isEmpty())
                    break;
                rootReference = stack.pop();
                System.out.print(rootReference.getData() + " ");
                rootReference = rootReference.getRightChild();
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
