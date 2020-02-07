package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : waniasra
 * @date : 12/22/2019 12:48 PM
 * This class demonstrates how to construct a BST from a its given level order traversal
 */
//Reference : https://www.geeksforgeeks.org/construct-bst-from-its-given-level-order-traversal-set-2/
public class ConstructBSTFromItsGivenLevelOrderTraversal {

    private Node root;

    public ConstructBSTFromItsGivenLevelOrderTraversal() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        ConstructBSTFromItsGivenLevelOrderTraversal bst = new ConstructBSTFromItsGivenLevelOrderTraversal();
        int n = 9;
        int array[] = {7, 4, 12, 3, 6, 8, 1, 5, 10};

        Node rootReference = bst.constructBSTFromGivenLevelOrderTraversal(array, n);
        bst.setRoot(rootReference);

        System.out.println("In-order traversal of BST constructed from given array is given as follows");
        bst.printInOrderTraversalOfConstructedBSTIteratively(bst.getRoot());
        System.out.println("\nTime complexity is O(N)");
    }

    public Node constructBSTFromGivenLevelOrderTraversal(int[] array, int nodeCount) {
        if (nodeCount == 0)
            return null;

        int i = 0;
        Queue<NodeDetails> queue = new LinkedList<>();
        NodeDetails nodeDetails = new NodeDetails(new Node(array[i++], null, null), Integer.MIN_VALUE, Integer.MAX_VALUE);
        queue.offer(nodeDetails);

        Node root = nodeDetails.getNode();

        while (i != nodeCount) {
            NodeDetails poppedNodeDetails = queue.poll();

            //Check if there are more elements in array which can be left child of the poppedNode
            if (i < nodeCount && array[i] < poppedNodeDetails.getNode().getData() && array[i] > poppedNodeDetails.getMinValue()) {

                //Create a new NodeDetails object and add to the left of the poppedNode's node and queue
                NodeDetails newNodeDetails = new NodeDetails(new Node(array[i++], null, null),
                        poppedNodeDetails.getMinValue(), poppedNodeDetails.getNode().getData());
                queue.offer(newNodeDetails);
                poppedNodeDetails.getNode().setLeftChild(newNodeDetails.getNode());
            }

            //Check if there are more elements in array which can be right child of the poppedNode
            if (i < nodeCount && array[i] > poppedNodeDetails.getNode().getData() && array[i] < poppedNodeDetails.getMaxValue()) {

                //Create a new NodeDetails object and add it to the right of the poppedNode's node and queue
                NodeDetails newNodeDetails = new NodeDetails(new Node(array[i++], null, null),
                        poppedNodeDetails.getNode().getData(), poppedNodeDetails.getMaxValue());
                queue.offer(newNodeDetails);
                poppedNodeDetails.getNode().setRightChild(newNodeDetails.getNode());
            }
        }
        return root;
    }

    public void printInOrderTraversalOfConstructedBSTIteratively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST constructed from given array is empty.");
            return;
        } else {
            Stack<Node> stack = new Stack<>();
            while (true) {
                while (rootReference != null) {
                    stack.push(rootReference);
                    rootReference = rootReference.getLeftChild();
                }
                if (stack.isEmpty()) {
                    break;
                }
                rootReference = stack.pop();
                System.out.print(rootReference.getData() + " ");
                rootReference = rootReference.getRightChild();
            }
        }
    }

    private class NodeDetails {
        private Node node;
        private int minValue;
        private int maxValue;

        public NodeDetails(Node node, int minValue, int maxValue) {
            this.node = node;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public int getMinValue() {
            return minValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public int getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
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
