package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : waniasra
 * @date : 10/17/2019 10:21 PM
 * This class demonstrates the reverse level order traversal of BST
 * <p>
 * The idea is to print last level first, then second last level, and so on.
 * Like Level order traversal, every level is printed from left to right.
 */
//Reference : https://www.geeksforgeeks.org/reverse-level-order-traversal/
public class ReverseLevelOrderTraversalOfBST {

    private Node root;

    public ReverseLevelOrderTraversalOfBST() {
        this.root = null;
    }

    public static void main(String[] args) {

        ReverseLevelOrderTraversalOfBST bst = new ReverseLevelOrderTraversalOfBST();

         /* Example:-
                                      20           ------------- Level 1
                                    /    \
                                   15      25      ------------- Level 2
                                 /  \      /  \
                                8    16   24   30  ------------- Level 3
         */

        // Left to right starting from the last level then second last and so on
        //Level 3 : 8 16 24 30
        //Level 2 : 15 25
        //Level 1: 20
        //Result : 8 16 24 30 -> 15 25 -> 20

        //Inserting few nodes into BST
        Node rootReference = bst.insertNodeIntoBSTRecursivelyBST(20, null);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(30, rootReference);

        System.out.println("Reverse level order traversal of BST");
        bst.printReverseLevelOrderTraversalOfBST(rootReference);
        System.out.println("\nTime complexity using above approach is O(N^2)");

        System.out.println("\nReverse level order traversal of BST using enhanced approach (using queue + stack)");
        bst.printReverseLevelOrderTraversalEnhanced(rootReference);
        System.out.println("\nTime complexity is O(N)");
    }


    public void printReverseLevelOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            int heightOfBST = calculateHeightOfBST(rootReference);
            for (int i = heightOfBST; i > 0; i--) {
                printNodesOfAGivenLevelNumber(i, rootReference);
            }
        }
    }

    private void printNodesOfAGivenLevelNumber(int levelNumber, Node rootReference) {
        if (rootReference == null)
            return;
        if (levelNumber == 1) {
            System.out.print(rootReference.getData() + " ");
        }
        printNodesOfAGivenLevelNumber(levelNumber - 1, rootReference.getLeftChild());
        printNodesOfAGivenLevelNumber(levelNumber - 1, rootReference.getRightChild());
    }

    /**
     * Approach is similar to the level order traversal with following two differences:
     * 1) Instead of printing a node, we push the node to stack
     * 2) Right subtree is visited before left subtree
     *
     * @param rootReference reference to the root node
     */
    private void printReverseLevelOrderTraversalEnhanced(Node rootReference) {
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();

        queue.add(rootReference);
        while (!queue.isEmpty()) {
            Node processedNode = queue.poll();
            stack.push(processedNode);

            //If the processing node has a right child          (Right subtree is processed first)
            if (processedNode.getRightChild() != null) {
                queue.add(processedNode.getRightChild());
            }

            //If the processing node has a left child
            if (processedNode.getLeftChild() != null) {
                queue.add(processedNode.getLeftChild());
            }
        }

        //Print the stack
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getData() + " ");
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

    public Node insertNodeIntoBSTRecursivelyBST(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getLeftChild()));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getRightChild()));
        }
        return rootReference;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
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
