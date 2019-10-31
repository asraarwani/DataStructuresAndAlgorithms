package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author : waniasra
 * @date : 10/30/2019 5:34 PM
 */

/**
 * Top view of a binary search tree is the set of nodes visible when the tree is viewed from the top.
 * The idea is to do vertical order traversal in order to put together nodes with same horizontal distance and then
 * using level order traversal, top most node from each list of nodes, put together with same horizontal distance, is picked.
 * Hashing is used to check if a node is already processed or not.
 */

//Reference : https://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
//Reference : https://www.youtube.com/watch?v=c3SAvcjWb1E
public class TopViewOfBST {

    private Node root;

    public TopViewOfBST() {
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
                               /
                              4
         */

        TopViewOfBST bst = new TopViewOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(4);

        System.out.println("Top view of the BST is given as follows..");
        bst.printTopViewOfBST(bst.getRoot());
        System.out.println("\nTime complexity is O(N)");
    }

    public void printTopViewOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
            return;
        } else {
            Map<Integer, Node> map = new TreeMap<>();
            Queue<QueueObject> queue = new LinkedList<>();
            queue.offer(new QueueObject(rootReference, 0));
            while (!queue.isEmpty()) {
                QueueObject polledQueueObject = queue.poll();
                if (!map.containsKey(polledQueueObject.getHorizontalDistance())) {
                    map.put(polledQueueObject.getHorizontalDistance(), polledQueueObject.getNode());
                }
                //If the processed QueueObject's node has a left child
                if (polledQueueObject.getNode().getLeftChild() != null) {
                    queue.offer(new QueueObject(polledQueueObject.getNode().getLeftChild(), polledQueueObject.getHorizontalDistance() - 1));
                }

                //If the processed QueueObject's node has a right child
                if (polledQueueObject.getNode().getRightChild() != null) {
                    queue.offer(new QueueObject(polledQueueObject.getNode().getRightChild(), polledQueueObject.getHorizontalDistance() + 1));
                }
            }
            map.entrySet().stream().forEach(entry -> {
                System.out.print(entry.getValue().getData() + " ");
            });
        }
    }

    private void insertNodeIntoBSTIteratively(int data) {
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

    private class QueueObject {

        private Node node;
        private int horizontalDistance;

        public QueueObject(Node node, int horizontalDistance) {
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
