package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 11/10/2019 9:03 PM
 * This class demonstrates how to count the number of leaf nodes in BST
 */
//Reference : https://www.geeksforgeeks.org/write-a-c-program-to-get-count-of-leaf-nodes-in-a-binary-tree/
//Reference : https://www.geeksforgeeks.org/iterative-program-count-leaf-nodes-binary-tree/
public class LeafNodeCountInBST {

    private Node root;

    public LeafNodeCountInBST() {
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

        LeafNodeCountInBST bst = new LeafNodeCountInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        int numberOfLeafNodes = bst.countNumberOfLeafNodesInBSTRecursively(bst.getRoot());
        System.out.println("Number of leaf nodes in BST : " + numberOfLeafNodes);

        numberOfLeafNodes = bst.countNumberOfLeafNodesInBSTIteratively(bst.getRoot());
        System.out.println("Number of leaf nodes in BST : " + numberOfLeafNodes);

        NodeCountDetails nodeCountDetails = new NodeCountDetails();
        bst.countNumberOfLeafNodesRecursivelyAlternate(bst.getRoot(), nodeCountDetails);
        System.out.println("Number of leaf nodes in BST : " + nodeCountDetails.getNodeCount());

        System.out.println("Time complexity for above three approaches is O(N)");
    }


    /*
        The idea is to use in-order traversal technique of BST
     */
    public void countNumberOfLeafNodesRecursivelyAlternate(Node rootReference, NodeCountDetails nodeCountDetails) {
        if (rootReference != null) {
            countNumberOfLeafNodesRecursivelyAlternate(rootReference.getLeftChild(), nodeCountDetails);

            //Increment the leaf node count if currently being processed node is a leaf node
            if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null)
                nodeCountDetails.setNodeCount(nodeCountDetails.getNodeCount() + 1);

            countNumberOfLeafNodesRecursivelyAlternate(rootReference.getRightChild(), nodeCountDetails);
        }
    }

    /*
        The idea is to use a level order traversal of BST
     */
    public int countNumberOfLeafNodesInBSTIteratively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return 0;
        } else {
            Node traversingNode = rootReference;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(traversingNode);
            int leafNodeCount = 0;
            while (!queue.isEmpty()) {
                traversingNode = queue.poll();

                //If the polled node has a left child
                if (traversingNode.getLeftChild() != null) {
                    queue.offer(traversingNode.getLeftChild());
                }

                //If the polled node has a right child
                if (traversingNode.getRightChild() != null) {
                    queue.offer(traversingNode.getRightChild());
                }

                //Check if the polled node is a leaf node, increment the count
                if (traversingNode.getLeftChild() == null && traversingNode.getRightChild() == null) {
                    leafNodeCount++;
                }
            }
            return leafNodeCount;
        }
    }

    public int countNumberOfLeafNodesInBSTRecursively(Node rootReference) {
        if (rootReference == null)
            return 0;
        else if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
            return 1;
        } else {
            return countNumberOfLeafNodesInBSTRecursively(rootReference.getLeftChild()) + countNumberOfLeafNodesInBSTRecursively(rootReference.getRightChild());
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNodeParent = null;
            Node traversalNode = root;
            while (true) {
                currentlyProcessingNodeParent = traversalNode;
                if (data <= traversalNode.getData()) {
                    traversalNode = traversalNode.getLeftChild();
                    if (traversalNode == null) {
                        currentlyProcessingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversalNode = traversalNode.getRightChild();
                    if (traversalNode == null) {
                        currentlyProcessingNodeParent.setRightChild(newNode);
                        break;
                    }
                }
            }
        }
    }

    private static class NodeCountDetails {
        private int nodeCount;

        public int getNodeCount() {
            return nodeCount;
        }

        public void setNodeCount(int nodeCount) {
            this.nodeCount = nodeCount;
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
