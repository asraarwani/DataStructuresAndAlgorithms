package com.some_domain.www.bst;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 10/15/2019 11:08 PM
 * This class demonstrates vertical order traversal of a BST
 */
public class VerticalOrderTraversalOfBST {


    private Node root;

    public VerticalOrderTraversalOfBST() {
        this.root = null;
    }

    public static void main(String[] args) {

        VerticalOrderTraversalOfBST bst = new VerticalOrderTraversalOfBST();

        /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        Node rootReference = bst.insertNodeIntoBSTRecursivelyBST(20, null);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(15, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(25, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(8, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(16, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(24, rootReference);
        rootReference = bst.insertNodeIntoBSTRecursivelyBST(30, rootReference);

        bst.printVerticalOrderTraversalOfBST(rootReference);
    }


    public void printVerticalOrderTraversalOfBST(Node rootReference) {
        Map<Integer, List<Node>> verticalOrderTraversalMap = new LinkedHashMap<>();
        Map<Node, Integer> parentNodeHorizontalDistanceMap = new LinkedHashMap<>(); //Stores the parent's horizontal distance
        Queue<Node> queue = new LinkedList<>();
        if (rootReference != null) {
            queue.add(rootReference);
            parentNodeHorizontalDistanceMap.put(rootReference, 0);  // Initially, setting the root node's horizontal distance to 0

        }
        //Assign the horizontal distance to the root node
        int rootReferenceData = rootReference.getData();
        List<Node> verticalLine = new ArrayList<>();
        verticalLine.add(rootReference);
        verticalOrderTraversalMap.put(0, verticalLine);

        while (!queue.isEmpty()) {
            Node processedNode = queue.poll();

            //Assign the horizontal distance to the left child (parent_node_horizontal_distance - 1)
            if (processedNode.getLeftChild() != null) {
                int leftChildHorizontalDistance = parentNodeHorizontalDistanceMap.get(processedNode) - 1;
                Node leftChild = processedNode.getLeftChild();
                if (verticalOrderTraversalMap.containsKey(leftChildHorizontalDistance)) {  // If we've already encountered the distance (vertical line)
                    List<Node> temporaryList = verticalOrderTraversalMap.get(leftChildHorizontalDistance);
                    temporaryList.add(leftChild);
                    verticalOrderTraversalMap.put(leftChildHorizontalDistance, temporaryList);
                    parentNodeHorizontalDistanceMap.put(leftChild, leftChildHorizontalDistance);
                } else {
                    List<Node> newVerticalLine = new ArrayList<>();
                    newVerticalLine.add(leftChild);
                    verticalOrderTraversalMap.put(leftChildHorizontalDistance, newVerticalLine);
                    parentNodeHorizontalDistanceMap.put(leftChild, leftChildHorizontalDistance);
                }
            }

            //Assign the horizontal distance to the right child (parent_node_horizontal_distance + 1)
            if (processedNode.getRightChild() != null) {
                int rightChildHorizontalDistance = parentNodeHorizontalDistanceMap.get(processedNode) + 1;
                Node rightChild = processedNode.getRightChild();
                if (verticalOrderTraversalMap.containsKey(rightChildHorizontalDistance)) {  // If we've already encountered the distance (vertical line)
                    List<Node> temporaryList = verticalOrderTraversalMap.get(rightChildHorizontalDistance);
                    temporaryList.add(rightChild);
                    verticalOrderTraversalMap.put(rightChildHorizontalDistance, temporaryList);
                    parentNodeHorizontalDistanceMap.put(rightChild, rightChildHorizontalDistance);
                } else {
                    List<Node> newVerticalLine = new ArrayList<>();
                    newVerticalLine.add(rightChild);
                    verticalOrderTraversalMap.put(rightChildHorizontalDistance, newVerticalLine);
                    parentNodeHorizontalDistanceMap.put(rightChild, rightChildHorizontalDistance);
                }
            }

            //Check if the node has left child
            if (processedNode.getLeftChild() != null) {
                queue.add(processedNode.getLeftChild());
            }

            //Check if the node has right child
            if (processedNode.getRightChild() != null) {
                queue.add(processedNode.getRightChild());
            }
        }
        verticalOrderTraversalMap.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " -> " + entry.getValue().stream().map(node -> node.getData()).collect(Collectors.toList()));
        });
    }

    public Node insertNodeIntoBSTRecursivelyBST(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getLeftChild()));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursivelyBST(data, rootReference.getRightChild()));
        }
        return rootReference;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
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
