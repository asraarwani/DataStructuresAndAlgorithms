package com.some_domain.www.bst;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author : asraar
 * @date : 07-10-2022 10:55 am
 * <p>
 * Reference : https://www.youtube.com/watch?v=C6r1fDKAW_o&ab_channel=KevinNaughtonJr.
 */
public class FindKthSmallestNodeInBST {

    private Node root;


    public static void main(String[] args) {

        /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        FindKthSmallestNodeInBST bst = new FindKthSmallestNodeInBST();
        bst.insertNodeIteratively(20);
        bst.insertNodeIteratively(15);
        bst.insertNodeIteratively(25);
        bst.insertNodeIteratively(8);
        bst.insertNodeIteratively(16);
        bst.insertNodeIteratively(24);
        bst.insertNodeIteratively(30);
        bst.inOrderTraversal(bst.getRoot());

        int k = 3;
        int kthSmallestElement = bst.findKthSmallestNodeFromBST(bst.getRoot(), k);
        System.out.println();
        System.out.println(k + "-th smallest element from BST is " + kthSmallestElement);
        System.out.println("Time and space complexity is O(N)");

        kthSmallestElement = bst.findKthSmallestNodeFromBSTUsingHeap(bst.getRoot(), k);
        System.out.println();
        System.out.println(k + "-th smallest element using Heap from BST is " + kthSmallestElement);
        System.out.println("Time and space complexity is O(N)");

        System.out.println();
        bst.findKthSmallestNodeAlternateApproach(bst.getRoot(), k, new Counter());
        System.out.println("Time and space complexity is O(N)");
    }

    private int findKthSmallestNodeFromBSTUsingHeap(Node rootReference, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        findKthSmallestNodeFromBSTUsingHeapHelper(rootReference, k, maxHeap);
        return maxHeap.remove();
    }

    private void findKthSmallestNodeFromBSTUsingHeapHelper(Node rootReference, int k, PriorityQueue<Integer> maxHeap) {
        if (rootReference != null) {
            findKthSmallestNodeFromBSTUsingHeapHelper(rootReference.getLeftChild(), k, maxHeap);
            maxHeap.add(rootReference.getData());
            if (maxHeap.size() > k) {
                maxHeap.remove();
            }
            findKthSmallestNodeFromBSTUsingHeapHelper(rootReference.getRightChild(), k, maxHeap);
        }
    }

    private int findKthSmallestNodeFromBST(Node rootReference, int k) {
        int[] array = new int[2];
        findKthSmallestNodeFromBSTHelper(rootReference, array, k);
        return array[1];
    }

    private void findKthSmallestNodeFromBSTHelper(Node rootReference, int[] array, int k) {
        if (rootReference != null) {
            findKthSmallestNodeFromBSTHelper(rootReference.getLeftChild(), array, k);
            if (++array[0] == k) {
                array[1] = rootReference.getData();
                return;
            }
            findKthSmallestNodeFromBSTHelper(rootReference.getRightChild(), array, k);
        }
    }

    private void findKthSmallestNodeAlternateApproach(Node rootReference, int k, Counter counter) {
        if (rootReference != null) {
            findKthSmallestNodeAlternateApproach(rootReference.getLeftChild(), k, counter);
            counter.setCount(counter.getCount() + 1);
            if (counter.getCount() == k) {
                System.out.println(k + "th smallest node in BST is : " + rootReference.getData());
            }
            findKthSmallestNodeAlternateApproach(rootReference.getRightChild(), k, counter);
        }
    }

    private static class Counter {
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    private void insertNodeIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = null;
            Node currentNode = root;
            while (true) {
                parent = currentNode;
                if (newNode.getData() <= currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        parent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parent.setRightChild(newNode);
                        break;
                    }
                }

            }
        }
    }

    private void inOrderTraversal(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversal(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversal(rootReference.getRightChild());
        }
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
