package com.some_domain.www.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 11/28/2019 10:02 PM
 * This class demonstrates how to convert a binary tree to a mirror binary tree
 */
//Reference : https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
public class ConvertBinaryTreeToMirrorTree {

    private Node root;

    public ConvertBinaryTreeToMirrorTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

          /* BST:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

            /* Mirror BST:-
                                      20
                                    /    \
                                   25      15
                                 /  \      /  \
                                30   24  16    8
         */

        ConvertBinaryTreeToMirrorTree bst = new ConvertBinaryTreeToMirrorTree();
        bst.setRoot(bst.createDummyBinaryTree());

        System.out.println("In order traversal of binary tree");
        bst.inOrderTraversalOfBinaryTree(bst.getRoot());

        bst.convertBinaryTreeToMirrorTreeRecursively(bst.getRoot());
        System.out.println("\nTime complexity is O(N) and space complexity is O(H) where H is the height of the binary tree.");

        System.out.println("\nMirror of binary tree using recrusive approach");
        bst.inOrderTraversalOfBinaryTree(bst.getRoot());
        System.out.println("\n");

        bst.convertBinaryTreeToMirrorTreeIteratively(bst.getRoot());
        System.out.println("\nMirror of binary tree using iterative approach");
        bst.inOrderTraversalOfBinaryTree(bst.getRoot());
        System.out.println("\nTime and space complexity is O(N).");

    }

    public void convertBinaryTreeToMirrorTreeIteratively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("Binary tree is empty.");
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(rootReference);
            while (!queue.isEmpty()) {
                Node polledNode = queue.poll();

                Node temporaryNode = polledNode.getLeftChild();
                polledNode.setLeftChild(polledNode.getRightChild());
                polledNode.setRightChild(temporaryNode);

                //If polled node has a left child
                if (polledNode.getLeftChild() != null) {
                    queue.offer(polledNode.getLeftChild());
                }

                //If polled node has a right child
                if (polledNode.getRightChild() != null) {
                    queue.offer(polledNode.getRightChild());
                }
            }
        }
    }

    public Node convertBinaryTreeToMirrorTreeRecursively(Node rootReference) {
        if (rootReference == null)
            return null;

        Node leftNode = convertBinaryTreeToMirrorTreeRecursively(rootReference.getLeftChild());
        Node rightNode = convertBinaryTreeToMirrorTreeRecursively(rootReference.getRightChild());

        rootReference.setLeftChild(rightNode);
        rootReference.setRightChild(leftNode);

        return rootReference;
    }

    private void inOrderTraversalOfBinaryTree(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBinaryTree(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBinaryTree(rootReference.getRightChild());
        }
    }

    private Node createDummyBinaryTree() {
        Node root = new Node(20, null, null);
        root.setLeftChild(new Node(15, null, null));
        root.setRightChild(new Node(25, null, null));
        root.getLeftChild().setLeftChild(new Node(8, null, null));
        root.getLeftChild().setRightChild(new Node(16, null, null));
        root.getRightChild().setLeftChild(new Node(24, null, null));
        root.getRightChild().setRightChild(new Node(30, null, null));
        return root;
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
