package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 10/14/2019 10:33 PM
 * This class demonstrates how to perform a level order traversal of a BST
 */
public class LevelOrderTraversalOfBST {

    private Node root;

    public LevelOrderTraversalOfBST() {
        this.root = null;
    }

    public static void main(String[] args) {

        LevelOrderTraversalOfBST bst = new LevelOrderTraversalOfBST();

        Node rootReference = bst.insertNodeIntoBST(4, null);
        rootReference = bst.insertNodeIntoBST(1, rootReference);
        rootReference = bst.insertNodeIntoBST(2, rootReference);
        rootReference = bst.insertNodeIntoBST(5, rootReference);
        rootReference = bst.insertNodeIntoBST(3, rootReference);
        rootReference = bst.insertNodeIntoBST(6, rootReference);
        rootReference = bst.insertNodeIntoBST(7, rootReference);

        System.out.println("Level order traversal of BST");
        bst.printLevelOrderTraversalOfBSTIteratively(rootReference);
        System.out.println("\nTime complexity using above approach is O(N^2)");

        System.out.println("\nLevel order traversal of BST using enhanced approach (using queue)");
        bst.printLevelOrderTraversalOfBSTEnhanced(rootReference);
        System.out.println("\nTime complexity is O(N)");
    }

    public void printLevelOrderTraversalOfBSTEnhanced(Node rootReference) {
        Queue<Node> queue = new LinkedList<>();
        if (rootReference != null) {
            queue.add(rootReference);
        }

        while (!queue.isEmpty()) {
            Node processedNode = queue.poll();
            System.out.print(processedNode.getData() + " ");

            //If processed node has left child, add it to the queue
            if (processedNode.getLeftChild() != null) {
                queue.add(processedNode.getLeftChild());
            }

            //If processed node has right child, add it to the queue
            if (processedNode.getRightChild() != null) {
                queue.add(processedNode.getRightChild());
            }
        }
    }

    public void printLevelOrderTraversalOfBSTIteratively(Node rootReference) {
        if (rootReference != null) {
            int heightOfBST = calculateHeightOfBST(rootReference);
            for (int i = 1; i <= heightOfBST; i++) {
                printNodesAtGivenLevel(rootReference, i);
            }
        }
    }

    private void printNodesAtGivenLevel(Node rootReference, int levelNumber) {
        if (rootReference == null)
            return;
        if (levelNumber == 1) {
            System.out.print(rootReference.getData() + " ");
        } else {
            printNodesAtGivenLevel(rootReference.getLeftChild(), levelNumber - 1);
            printNodesAtGivenLevel(rootReference.getRightChild(), levelNumber - 1);
        }
    }

    private int calculateHeightOfBST(Node rootReference) {
        if (rootReference == null)
            return 0;
        else {
            int leftSubtreeHeight = calculateHeightOfBST(rootReference.getLeftChild());
            int rightSubtreeHeight = calculateHeightOfBST(rootReference.getRightChild());
            if (leftSubtreeHeight > rightSubtreeHeight)
                return leftSubtreeHeight + 1;
            else
                return rightSubtreeHeight + 1;
        }
    }

    public Node insertNodeIntoBST(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBST(data, rootReference.getLeftChild()));
        } else if (data > rootReference.getData()) {
            rootReference.setRightChild(insertNodeIntoBST(data, rootReference.getRightChild()));
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
