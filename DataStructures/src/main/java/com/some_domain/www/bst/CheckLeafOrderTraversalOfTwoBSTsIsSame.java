package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 12/18/2019 9:17 PM
 * This class demonstrates how to check whether leaf order traversal of two BSTs is same or not
 */
//Reference : https://www.geeksforgeeks.org/check-if-leaf-traversal-of-two-binary-trees-is-same/
public class CheckLeafOrderTraversalOfTwoBSTsIsSame {

    private Node root;

    public CheckLeafOrderTraversalOfTwoBSTsIsSame() {
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

        CheckLeafOrderTraversalOfTwoBSTsIsSame firstBST = new CheckLeafOrderTraversalOfTwoBSTsIsSame();
        firstBST.insertNodeIntoBSTIteratively(20);
        firstBST.insertNodeIntoBSTIteratively(15);
        firstBST.insertNodeIntoBSTIteratively(25);
        firstBST.insertNodeIntoBSTIteratively(8);
        firstBST.insertNodeIntoBSTIteratively(16);
        firstBST.insertNodeIntoBSTIteratively(24);
        firstBST.insertNodeIntoBSTIteratively(30);

          /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        CheckLeafOrderTraversalOfTwoBSTsIsSame secondBST = new CheckLeafOrderTraversalOfTwoBSTsIsSame();
        secondBST.insertNodeIntoBSTIteratively(20);
        secondBST.insertNodeIntoBSTIteratively(15);
        secondBST.insertNodeIntoBSTIteratively(25);
        secondBST.insertNodeIntoBSTIteratively(8);
        secondBST.insertNodeIntoBSTIteratively(16);
        secondBST.insertNodeIntoBSTIteratively(24);
        secondBST.insertNodeIntoBSTIteratively(30);

        boolean isLeafOrderTraversalSame = new CheckLeafOrderTraversalOfTwoBSTsIsSame().isLeafOrderTraversalOfTwoBSTsSame(firstBST.getRoot(), secondBST.getRoot());
        System.out.println("Leaf order traversal of two BSTs is same : " + isLeafOrderTraversalSame);
        System.out.println("Time and space complexity is O(N) and O(H1 + H2) where H1 and H2 are the heights of the two BSTs");
    }

    public boolean isLeafOrderTraversalOfTwoBSTsSame(Node firstBSTRootRef, Node seconBSTRootRef) {
        Stack<Node> firstStack = new Stack<>();
        Stack<Node> secondStack = new Stack<>();

        firstStack.push(firstBSTRootRef);
        secondStack.push(seconBSTRootRef);

        while (!firstStack.isEmpty() && !secondStack.isEmpty()) {

            //If either firstStack or secondStack is empty
            if (firstStack.isEmpty() || secondStack.isEmpty()) {
                return false;
            }

            //Process firstStack
            Node poppedNodeFromFirstStack = firstStack.pop();
            while (poppedNodeFromFirstStack != null && !isLeafNode(poppedNodeFromFirstStack)) {

                //Push the right and left child nodes to the corresponding stack
                if (poppedNodeFromFirstStack.getRightChild() != null)
                    firstStack.push(poppedNodeFromFirstStack.getRightChild());
                if (poppedNodeFromFirstStack.getLeftChild() != null)
                    firstStack.push(poppedNodeFromFirstStack.getLeftChild());
                poppedNodeFromFirstStack = firstStack.pop();
            }


            //Process secondStack
            Node poppedNodeFromSecondStack = secondStack.pop();
            while (poppedNodeFromSecondStack != null && !isLeafNode(poppedNodeFromSecondStack)) {

                //Push the right and left child nodes to the corresponding stack
                if (poppedNodeFromSecondStack.getRightChild() != null)
                    secondStack.push(poppedNodeFromSecondStack.getRightChild());
                if (poppedNodeFromSecondStack.getLeftChild() != null)
                    secondStack.push(poppedNodeFromSecondStack.getLeftChild());
                poppedNodeFromSecondStack = secondStack.pop();
            }

            //If one of the two leaf nodes from the two BSTs is null and other is not
            if (poppedNodeFromFirstStack == null && poppedNodeFromSecondStack != null)
                return false;
            if (poppedNodeFromFirstStack != null && poppedNodeFromSecondStack == null)
                return false;

            //If both the leaf nodes from the two BSTs aren't null and data isn't same return false
            if (poppedNodeFromFirstStack != null && poppedNodeFromSecondStack != null) {
                if (poppedNodeFromFirstStack.getData() != poppedNodeFromSecondStack.getData()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isLeafNode(Node nodeReference) {
        return (nodeReference.getLeftChild() == null) && (nodeReference.getRightChild() == null);
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
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
