package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 10/22/2019 12:15 PM
 * This class demonstrates how to find the node with minimum and maximum value in BST
 */
public class NodeWithMinimumAndMaximumDataInBST {

    private Node root;

    public NodeWithMinimumAndMaximumDataInBST() {
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

        NodeWithMinimumAndMaximumDataInBST bst = new NodeWithMinimumAndMaximumDataInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printInOrderTraversalOfBSTIteratively(bst.getRoot());

        System.out.println("\n");
        Node nodeWithMinimumData = bst.findNodeWithMinimumData(bst.getRoot());
        if (nodeWithMinimumData != null)
            System.out.println("Node with minimum data in BST is : " + nodeWithMinimumData.getData());
        else
            System.out.println("BST is empty");

        Node nodeWithMaximumData = bst.findNodeWithMaximumData(bst.getRoot());
        if (nodeWithMaximumData != null)
            System.out.println("Node with maximum data in BST is : " + nodeWithMaximumData.getData());
        else
            System.out.println("BST is empty");

        System.out.println("Time complexity for the above approach is going to be O(N) for left and right skewed tree.");
    }

    public Node findNodeWithMaximumData(Node rootReference) {
        if (rootReference == null) {
            return null;
        } else {
            Node traversingNode = rootReference;
            while (traversingNode.getRightChild() != null) {
                traversingNode = traversingNode.getRightChild();
            }
            return traversingNode;
        }
    }

    public Node findNodeWithMinimumData(Node rootReference) {
        if (rootReference == null) {
            return null;
        } else {
            Node traversingNode = rootReference;
            while ((traversingNode.getLeftChild() != null)) {
                traversingNode = traversingNode.getLeftChild();
            }
            return traversingNode;
        }
    }

    public void printInOrderTraversalOfBSTIteratively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
        } else {
            Node traversingNode = rootReference;
            Stack<Node> stack = new Stack<>();
            while (true) {
                while (traversingNode != null) {
                    stack.push(traversingNode);
                    traversingNode = traversingNode.getLeftChild();
                }
                if (stack.isEmpty()) {
                    break;
                }
                traversingNode = stack.pop();
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getRightChild();
            }
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
}
