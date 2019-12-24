package com.some_domain.www.bst;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 10/15/2019 11:08 PM
 * This class demonstrates vertical order traversal of a BST
 */
//Reference : https://www.youtube.com/watch?v=PQKkr036wRc
//Reference : https://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/   (For alternate approach)
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
        bst.setRoot(rootReference);

        bst.printVerticalOrderTraversalOfBST(bst.getRoot());

        System.out.println("\nVertical order traversal of BST using alternate approach");
        bst.printVerticalOrderTraversalOfBSTAlternate(bst.getRoot());
        System.out.println("Time complexity is O(N) under the assumption that we have a good hash function that allows insertion and retrieval operations in O(1)");
    }

    public void printVerticalOrderTraversalOfBSTAlternate(Node rootReference) {
        Map<Integer, List<Node>> map = new LinkedHashMap<>();
        printVerticalOrderTraversalOfBSTAlternateHelper(rootReference, map, 0);
        map.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue().stream().map(item -> item.getData()).collect(Collectors.toList()));
        });
    }

    public void printVerticalOrderTraversalOfBSTAlternateHelper(Node rootReference, Map<Integer, List<Node>> map, int horizontalDistance) {
        if (rootReference == null)
            return;

        List<Node> nodeList = map.get(horizontalDistance);
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        nodeList.add(rootReference);
        map.put(horizontalDistance, nodeList);

        printVerticalOrderTraversalOfBSTAlternateHelper(rootReference.getLeftChild(), map, horizontalDistance - 1);

        printVerticalOrderTraversalOfBSTAlternateHelper(rootReference.getRightChild(), map, horizontalDistance + 1);
    }

    public void printVerticalOrderTraversalOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Map<Integer, List<Node>> map = printVerticalOrderTraversalOfBSTHelper(rootReference);
            map.entrySet().stream().forEach(entry -> {
                System.out.println(entry.getKey() + " " + entry.getValue().stream().map(node -> node.getData()).collect(Collectors.toList()));
            });
        }
    }

    private Map<Integer, List<Node>> printVerticalOrderTraversalOfBSTHelper(Node rootReference) {
        Map<Integer, List<Node>> resultMap = new LinkedHashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> parentHorizontalDistance = new LinkedHashMap<>();
        Node traversalNode = rootReference;
        queue.offer(traversalNode);
        parentHorizontalDistance.put(traversalNode, 0);
        resultMap.put(0, new ArrayList<>(Arrays.asList(traversalNode)));
        while (!queue.isEmpty()) {
            Node polledNode = queue.poll();
            //If the polledNode has a left child
            if (polledNode.getLeftChild() != null) {
                Node leftChildNode = polledNode.getLeftChild();
                int horizontalDistance = parentHorizontalDistance.get(polledNode) - 1;
                if (!resultMap.containsKey(horizontalDistance)) {
                    resultMap.put(horizontalDistance, new ArrayList<>());
                }
                resultMap.get(horizontalDistance).add(leftChildNode);
                parentHorizontalDistance.put(leftChildNode, horizontalDistance);
                queue.offer(leftChildNode);
            }

            //If the polledNode has a right child
            if (polledNode.getRightChild() != null) {
                Node rightChildNode = polledNode.getRightChild();
                int horizontalDistance = parentHorizontalDistance.get(polledNode) + 1;
                if (!resultMap.containsKey(horizontalDistance)) {
                    resultMap.put(horizontalDistance, new ArrayList<>());
                }
                resultMap.get(horizontalDistance).add(rightChildNode);
                parentHorizontalDistance.put(rightChildNode, horizontalDistance);
                queue.offer(rightChildNode);
            }
        }
        return resultMap;
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
