package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 1/7/2020 11:10 AM
 * This class demonstrates how to construct a balanced BST from given sorted array
 */
//Reference : https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
public class SortedArrayToBalancedBST {

    private Node root;

    public SortedArrayToBalancedBST() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        SortedArrayToBalancedBST bst = new SortedArrayToBalancedBST();

        int sortedArray[] = new int[]{1, 2, 3, 4, 5, 6, 7};

        Node rootReference = bst.convertSortedArrayToBalancedBST(sortedArray, 0, sortedArray.length - 1);
        bst.setRoot(rootReference);

        bst.printInOrderTraversalOfConstructedBSTIteratively(bst.getRoot());
        System.out.println("\nTime complexity is O(N) where N is the number of items in the given sorted array");
    }


    public Node convertSortedArrayToBalancedBST(int[] sortedArray, int startingIndex, int endingIndex) {
        if (startingIndex > endingIndex)
            return null;

        int middleIndex = (startingIndex + endingIndex) / 2;

        Node rootNode = new Node(sortedArray[middleIndex], null, null);

        rootNode.setLeftChild(convertSortedArrayToBalancedBST(sortedArray, startingIndex, middleIndex - 1));

        rootNode.setRightChild(convertSortedArrayToBalancedBST(sortedArray, middleIndex + 1, endingIndex));

        return rootNode;
    }

    public void printInOrderTraversalOfConstructedBSTIteratively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("Constructed BST is empty");
            return;
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
