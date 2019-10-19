package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : waniasra
 * @date : 10/19/2019 10:12 PM
 * This class demonstrates iterative in-order traversal of a BST
 */
//Reference : https://www.youtube.com/watch?v=SiyEwLrPpyQ&list=PLeIMaH7i8JDj7DnmO7lll97P1yZjMCpgY
public class IterativeInOrderTraversalOfBST {

    private Node root;

    public IterativeInOrderTraversalOfBST() {
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

        IterativeInOrderTraversalOfBST bst = new IterativeInOrderTraversalOfBST();

        //Inserting few nodes into BST
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printIterativeInOrderTraversalOfBST(bst.getRoot());
    }

    public void printIterativeInOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            Stack<Node> stack = new Stack<>();
            Node traversingNode = rootReference;
            while (true) {
                while (traversingNode != null) {
                    stack.push(traversingNode);
                    traversingNode = traversingNode.getLeftChild();
                }
                //If stack is empty, means there are no further nodes to process
                if (stack.isEmpty()) {
                    break;
                }
                traversingNode = stack.pop();
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getRightChild();
            }
        } else {
            System.out.println("Binary search tree is empty.");
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processNodeParent = null;
            Node traversalNode = root;
            while (true) {
                processNodeParent = traversalNode;
                if (data <= traversalNode.getData()) {
                    traversalNode = traversalNode.getLeftChild();
                    if (traversalNode == null) {
                        processNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversalNode = traversalNode.getRightChild();
                    if (traversalNode == null) {
                        processNodeParent.setRightChild(newNode);
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
