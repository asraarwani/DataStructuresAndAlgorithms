package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/29/2019 12:12 PM
 * This class demonstrates how to find the in-order successor and predecessor of a given node in BST
 */
//Reference : https://algorithms.tutorialhorizon.com/inorder-predecessor-and-successor-in-binary-search-tree/
public class InOrderSuccessorAndPredecessorOfGivenNodeInBST {

    private Node root;

    public InOrderSuccessorAndPredecessorOfGivenNodeInBST() {
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

        InOrderSuccessorAndPredecessorOfGivenNodeInBST bst = new InOrderSuccessorAndPredecessorOfGivenNodeInBST();
        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        Node givenNode = rootReference.getRightChild().getLeftChild();
        System.out.println("Given node " + givenNode.getData());
        PredecessorSuccessorNode predecessorSuccessorNode = new PredecessorSuccessorNode();
        bst.findInOrderSuccessorAndPredecessorOfGivenNodeInBST(bst.getRoot(), predecessorSuccessorNode, givenNode);
        System.out.println("Predecessor Node : " + predecessorSuccessorNode.getPredecessorNode().getData() + " Successor node : " + predecessorSuccessorNode.getSuccessorNode().getData());
        System.out.println("Time complexity is O(LogN) where N is the number of nodes in BST");

        System.out.println();
        predecessorSuccessorNode = new PredecessorSuccessorNode();
        bst.findInOrderSuccessorAndPredecessorOfGivenNodeInBSTAlternate(bst.getRoot(), predecessorSuccessorNode, givenNode);
        System.out.println("Predecessor Node : " + predecessorSuccessorNode.getPredecessorNode().getData() + " Successor node : " + predecessorSuccessorNode.getSuccessorNode().getData());
        System.out.println("Time complexity is O(N) where N is the number of nodes in BST");

    }

    //https://ide.geeksforgeeks.org/lwKIva3xm7
    public void findInOrderSuccessorAndPredecessorOfGivenNodeInBSTAlternate(Node rootReference, PredecessorSuccessorNode predecessorSuccessorNode, Node givenNode) {
        if (rootReference != null) {

            findInOrderSuccessorAndPredecessorOfGivenNodeInBSTAlternate(rootReference.getLeftChild(), predecessorSuccessorNode, givenNode);

            //Store the first largest node greater than  the givenNode and that is going to be the successor node
            if (rootReference.getData() > givenNode.getData() && predecessorSuccessorNode.getSuccessorNode() == null) {
                predecessorSuccessorNode.setSuccessorNode(rootReference);
            }

            //Store the last smallest node smaller than the givenNode and that is going to be the predecessor node
            if (rootReference.getData() < givenNode.getData()) {
                predecessorSuccessorNode.setPredecessorNode(rootReference);
            }

            findInOrderSuccessorAndPredecessorOfGivenNodeInBSTAlternate(rootReference.getRightChild(), predecessorSuccessorNode, givenNode);
        }
    }

    public void findInOrderSuccessorAndPredecessorOfGivenNodeInBST(Node rootReference, PredecessorSuccessorNode predecessorSuccessorNode, Node givenNode) {

        if (rootReference != null) {

            if (givenNode.getData() == rootReference.getData()) {

                //Go to the right most node in left subtree, it is going to be the predecessor
                if (rootReference.getLeftChild() != null) {
                    Node largestNodeInLeftSubTree = rootReference.getLeftChild();
                    while (largestNodeInLeftSubTree != null) {
                        largestNodeInLeftSubTree = rootReference.getRightChild();
                    }
                    predecessorSuccessorNode.setPredecessorNode(largestNodeInLeftSubTree);
                }

                //Go the the left most node in right subtree, it is going to be the successor
                if (rootReference.getRightChild() != null) {
                    Node smallestNodeInRightSubTree = rootReference.getRightChild();
                    while (smallestNodeInRightSubTree != null) {
                        smallestNodeInRightSubTree = smallestNodeInRightSubTree.getLeftChild();
                    }
                    predecessorSuccessorNode.setSuccessorNode(smallestNodeInRightSubTree);
                }

            } else if (givenNode.getData() < rootReference.getData()) {
                predecessorSuccessorNode.setSuccessorNode(rootReference);
                findInOrderSuccessorAndPredecessorOfGivenNodeInBST(rootReference.getLeftChild(), predecessorSuccessorNode, givenNode);
            } else {
                predecessorSuccessorNode.setPredecessorNode(rootReference);
                findInOrderSuccessorAndPredecessorOfGivenNodeInBST(rootReference.getRightChild(), predecessorSuccessorNode, givenNode);
            }
        }
    }

    public Node insertNodeIntoBSTRecursively(Node rootReference, int data) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursively(rootReference.getLeftChild(), data));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursively(rootReference.getRightChild(), data));
        }
        return rootReference;
    }

    private static class PredecessorSuccessorNode {
        private Node predecessorNode;
        private Node successorNode;

        public Node getPredecessorNode() {
            return predecessorNode;
        }

        public void setPredecessorNode(Node predecessorNode) {
            this.predecessorNode = predecessorNode;
        }

        public Node getSuccessorNode() {
            return successorNode;
        }

        public void setSuccessorNode(Node successorNode) {
            this.successorNode = successorNode;
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
