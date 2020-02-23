package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 12/16/2019 10:27 PM
 * This class demonstrates how to convert a given BST into Singly Linked List using level order traversal
 */
//Reference : https://www.geeksforgeeks.org/flatten-a-binary-tree-into-linked-list/  -> Simple approach using level order traversal of BST
public class FlattenBSTToSinglyLL_UsingQueue {

    private Node root;

    public FlattenBSTToSinglyLL_UsingQueue() {
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

        FlattenBSTToSinglyLL_UsingQueue bst = new FlattenBSTToSinglyLL_UsingQueue();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.flattenBSTToSinglyLinkedListUsingQueue(bst.getRoot());
        bst.inOrderTraversalOfBST(bst.getRoot());
        System.out.println("\nTime and space complexity is O(N)");
    }

    public void flattenBSTToSinglyLinkedListUsingQueue(Node rootReference) {
        if (rootReference == null) {
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            Node previousNode = rootReference;
            queue.offer(rootReference);
            while (!queue.isEmpty()) {
                Node polledNode = queue.poll();

                if (polledNode.getLeftChild() != null) {
                    queue.offer(polledNode.getLeftChild());
                }

                if (polledNode.getRightChild() != null) {
                    queue.offer(polledNode.getRightChild());
                }

                polledNode.setLeftChild(null);
                previousNode.setRightChild(polledNode);
                previousNode = polledNode;
            }
        }
    }

    public void inOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBST(rootReference.getRightChild());
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
            return;
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
