package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author : asraar
 * @date : 09-10-2022 12:38 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/bottom-view-binary-tree/
 */
public class BottomViewOfBST {

    private Node root;

    public static void main(String[] args) {

         /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30

         */

        BottomViewOfBST bst = new BottomViewOfBST();

        Node rootReference = null;
        rootReference = bst.insertNodeRecursively(rootReference, 20);
        rootReference = bst.insertNodeRecursively(rootReference, 15);
        rootReference = bst.insertNodeRecursively(rootReference, 25);
        rootReference = bst.insertNodeRecursively(rootReference, 8);
        rootReference = bst.insertNodeRecursively(rootReference, 16);
        rootReference = bst.insertNodeRecursively(rootReference, 24);
        rootReference = bst.insertNodeRecursively(rootReference, 30);
        bst.setRoot(rootReference);
        //bst.inOrderTraversalRecursive(bst.getRoot());

        System.out.println("\nBottom view using level-order and vertical order traversal of BST is given as follows:");
        bst.printBottomViewOfBST(bst.getRoot());

        System.out.println();
        System.out.println("\nUsing DFS");
        Map<Integer, Node> map = new TreeMap<>();
        bst.printBottomViewUsingDepthFirstSearch(bst.getRoot(), map, 0);
        map.entrySet().stream().forEach(entry -> System.out.print(entry.getValue().getData() + " "));

        System.out.println("\nTime complexity is O(N log N) , extra logN is for accessing map, space complexity is O(N)");

    }

    private void printBottomViewOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
        } else {
            Map<Integer, Node> bottomViewMap = new TreeMap<>();
            Queue<QueueNode> queue = new LinkedList<>();
            queue.offer(new QueueNode(rootReference, 0));
            while (!queue.isEmpty()) {
                QueueNode polledQueueNode = queue.poll();

                //Every time we find a node having same horizontal distance we need to replace the data in the map
                //This will make sure that the bottom-most element for that horizontal distance is present in the map
                bottomViewMap.put(polledQueueNode.getHorizontalDistance(), polledQueueNode.getNode());

                if (polledQueueNode.getNode().getLeftChild() != null) {
                    queue.offer(new QueueNode(polledQueueNode.getNode().getLeftChild(), polledQueueNode.getHorizontalDistance() - 1));
                }

                if (polledQueueNode.getNode().getRightChild() != null) {
                    queue.offer(new QueueNode(polledQueueNode.getNode().getRightChild(), polledQueueNode.getHorizontalDistance() + 1));
                }
            }
            bottomViewMap.entrySet().stream().forEach(entry -> {
                System.out.print(entry.getValue().getData() + " ");
            });
        }
    }

    private void printBottomViewUsingDepthFirstSearch(Node rootReference, Map<Integer, Node> map, int horizontalDistance) {
        if (rootReference != null) {

            printBottomViewUsingDepthFirstSearch(rootReference.getLeftChild(), map, horizontalDistance - 1);

            map.put(horizontalDistance, rootReference);

            printBottomViewUsingDepthFirstSearch(rootReference.getRightChild(), map, horizontalDistance + 1);
        }
    }

    private Node insertNodeRecursively(Node rootReference, int data) {
        Node newNode = new Node(data, null, null);
        if (rootReference == null) {
            rootReference = newNode;
        } else {
            if (newNode.getData() <= rootReference.getData()) {
                rootReference.setLeftChild(insertNodeRecursively(rootReference.getLeftChild(), data));
            } else {
                rootReference.setRightChild(insertNodeRecursively(rootReference.getRightChild(), data));
            }
        }
        return rootReference;
    }

    private void inOrderTraversalRecursive(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalRecursive(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalRecursive(rootReference.getRightChild());
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private class QueueNode {
        private Node node;
        private int horizontalDistance;

        public QueueNode(Node node, int horizontalDistance) {
            this.node = node;
            this.horizontalDistance = horizontalDistance;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public int getHorizontalDistance() {
            return horizontalDistance;
        }

        public void setHorizontalDistance(int horizontalDistance) {
            this.horizontalDistance = horizontalDistance;
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
