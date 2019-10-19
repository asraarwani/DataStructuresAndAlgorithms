package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 10/19/2019 10:48 PM
 * This class demonstrates iterative post-order traversal of BST using two stacks
 */
//Reference : https://www.youtube.com/watch?v=qT65HltK2uE
public class IterativePostOrderTraversalOfBSTUsingTwoStacks {

    private Node root;

    public IterativePostOrderTraversalOfBSTUsingTwoStacks() {
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

        IterativePostOrderTraversalOfBSTUsingTwoStacks bst = new IterativePostOrderTraversalOfBSTUsingTwoStacks();

        //Inserting few nodes into BST
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printIterativePostOrderTraversalOfBST(bst.getRoot());
    }

    public void printIterativePostOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {

            Stack<Node> firstStack = new Stack<>();
            Stack<Node> secondStack = new Stack<>();
            firstStack.add(rootReference);

            while (!firstStack.isEmpty()) {
                Node processedNode = firstStack.pop();
                secondStack.add(processedNode);

                //If the processed node has a left child
                if (processedNode.getLeftChild() != null) {
                    firstStack.add(processedNode.getLeftChild());
                }

                //If the processed node has a right child
                if (processedNode.getRightChild() != null) {
                    firstStack.add(processedNode.getRightChild());
                }
            }

            System.out.println("Iterative post order traversal of BST using two stacks");
            while (!secondStack.isEmpty()) {
                System.out.print(secondStack.pop().getData() + " ");
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
