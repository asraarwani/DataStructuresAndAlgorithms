package com.some_domain.www.bst;


import java.util.Stack;

/**
 * @author : waniasra
 * @date : 10/19/2019 11:14 PM
 * This class demonstrates iterative post-order traversal of BST using single stack
 */
//Reference : https://www.youtube.com/watch?v=cviyUv2TFG8
public class IterativePostOrderTraversalOfBSTUsingSingleStack {

    private Node root;

    public IterativePostOrderTraversalOfBSTUsingSingleStack() {
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

        IterativePostOrderTraversalOfBSTUsingSingleStack bst = new IterativePostOrderTraversalOfBSTUsingSingleStack();

        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printPostOrderTraversalUsingSingleStack(bst.getRoot());
    }

    public void printPostOrderTraversalUsingSingleStack(Node rootReference) {
        if (rootReference != null) {
            Node traversingNode = rootReference;
            Stack<Node> stack = new Stack();
            while (true) {
                if (traversingNode != null) {
                    stack.push(traversingNode);
                    traversingNode = traversingNode.getLeftChild();
                } else {
                    if (stack.isEmpty()) {
                        break;
                    } else {
                        if (stack.peek().getRightChild() == null) {
                            traversingNode = stack.pop();
                            System.out.print(traversingNode.getData() + " ");
                            while (traversingNode == stack.peek().getRightChild()) {
                                traversingNode = stack.pop();
                                System.out.print(traversingNode.getData() + " ");
                                if (stack.isEmpty()) {
                                    break;
                                }
                            }
                        }
                        if (!stack.isEmpty()) {
                            traversingNode = stack.peek().getRightChild();
                        } else {
                            traversingNode = null;
                        }
                    }
                }
            }
        } else {
            System.out.println("Binary search tree is empty");
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
