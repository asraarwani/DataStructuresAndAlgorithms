package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 10/31/2019 10:36 PM
 * This class demonstrates how to find the lowest common ancestor of the two nodes in a BST
 */

/**
 * Let T be a rooted tree. The lowest common ancestor between two nodes n1 and n2 is defined as the lowest node
 * in T that has both n1 and n2 as descendants (where we allow a node to be a descendant of itself).
 * Note : We assume both the nodes are present in the BST
 */

//Reference : https://www.youtube.com/watch?v=13m9ZCB8gjw
//Reference : https://www.youtube.com/watch?v=F-_1sbnPbWQ
public class LowestCommonAncestorOfNodeInBST {

    private Node root;

    public LowestCommonAncestorOfNodeInBST() {
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

        LowestCommonAncestorOfNodeInBST bst = new LowestCommonAncestorOfNodeInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        //Getting two nodes from the BST (8 and 16)
        Node firstNode = bst.getRoot().getLeftChild().getLeftChild();
        Node secondNode = bst.getRoot().getRightChild().getLeftChild();

        Node lowestCommonAncestor = bst.getLowestCommonAncestor(bst.getRoot(), firstNode, secondNode);
        if (lowestCommonAncestor != null) {
            System.out.println("Lowest common ancestor of the two nodes [ " + firstNode.getData() + " , " + secondNode.getData() + " ] is : " + lowestCommonAncestor.getData());
            System.out.println("Time complexity of this approach is O(N) where N is the number of the nodes in the BST");
        }

        System.out.println();
        lowestCommonAncestor = bst.getLowestCommonAncestorAlternate(bst.getRoot(), firstNode, secondNode);
        if (lowestCommonAncestor != null) {
            System.out.println("Lowest common ancestor of the two nodes [ " + firstNode.getData() + " , " + secondNode.getData() + " ] is : " + lowestCommonAncestor.getData());
            System.out.println("Time complexity of this approach is O(H) where H is the height of the BST");
        }

        System.out.println();
        lowestCommonAncestor = bst.getLowestCommonAncestorOptimized(bst.getRoot(), firstNode, secondNode);
        if (lowestCommonAncestor != null) {
            System.out.println("Lowest common ancestor of the two nodes [ " + firstNode.getData() + " , " + secondNode.getData() + " ] is : " + lowestCommonAncestor.getData());
            System.out.println("Time complexity of this approach is O(H) where H is the height of the BST");
        }
    }

    public Node getLowestCommonAncestorOptimized(Node rootReference, Node firstNode, Node secondNode) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return null;
        } else {
            while (rootReference != null) {
                if (rootReference.getData() < firstNode.getData() && rootReference.getData() < secondNode.getData()) {
                    rootReference = rootReference.getRightChild();
                } else if (rootReference.getData() > firstNode.getData() && rootReference.getData() > secondNode.getData()) {
                    rootReference = rootReference.getLeftChild();
                } else
                    break;
            }
            return rootReference;
        }
    }

    //Reference : https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
    public Node getLowestCommonAncestorAlternate(Node rootReference, Node firstNode, Node secondNode) {
        if (rootReference == null)
            return null;

        if (rootReference.getData() > firstNode.getData() && rootReference.getData() > secondNode.getData()) {
            return getLowestCommonAncestorAlternate(rootReference.getLeftChild(), firstNode, secondNode);
        }

        if (rootReference.getData() < firstNode.getData() && rootReference.getData() < secondNode.getData()) {
            return getLowestCommonAncestorAlternate(rootReference.getRightChild(), firstNode, secondNode);
        }
        return rootReference;
    }

    public Node getLowestCommonAncestor(Node rootReference, Node firstNode, Node secondNode) {
        if (rootReference == null)
            return null;

        if (firstNode == rootReference || secondNode == rootReference)
            return rootReference;

        Node leftNode = getLowestCommonAncestor(rootReference.getLeftChild(), firstNode, secondNode);
        Node rightNode = getLowestCommonAncestor(rootReference.getRightChild(), firstNode, secondNode);

        if (leftNode == null && rightNode == null)
            return null;

        if (leftNode != null && rightNode != null)
            return rootReference;

        return (leftNode != null) ? leftNode : rightNode;
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNode = root;
            Node traversingNode = root;
            while (true) {
                currentlyProcessingNode = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        currentlyProcessingNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        currentlyProcessingNode.setRightChild(newNode);
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
