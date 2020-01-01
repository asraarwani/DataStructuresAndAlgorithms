package com.some_domain.www.bst;

import java.util.Stack;
import java.util.stream.IntStream;

/**
 * @author : waniasra
 * @date : 11/1/2019 12:00 PM
 * This class demonstrates how to find all the paths with sum equal to K in a BST
 * Note : We are looking for paths starting from any node
 */

//Reference : https://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
public class PrintAllKSumPathsInBST {

    private Node root;

    public PrintAllKSumPathsInBST() {
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

        PrintAllKSumPathsInBST bst = new PrintAllKSumPathsInBST();

        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 10);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        int kSum = 45;
        System.out.println("We are looking for kSum [ " + kSum + " ]");
        bst.printAllKSumPathsFromBST(bst.getRoot(), new Stack<>(), kSum);
        System.out.println("\nTime complexity is O(NlogN) for balanced BST and O(N^2) for BST/skewed BST");
    }

    public void printAllKSumPathsFromBST(Node rootReference, Stack<Node> stack, int kSum) {
        if (rootReference == null)
            return;

        stack.push(rootReference);

        //Check if there exists any k-sum path that ends at this node
        int sumSoFar = 0;
        for (int i = stack.size() - 1; i >= 0; i--) {
            sumSoFar = sumSoFar + stack.get(i).getData();
            if (sumSoFar == kSum) {
              /*  for (int j = i; j < stack.size(); j++) {
                    System.out.print(stack.get(j).getData() + " ");
                }*/
                IntStream.range(i, stack.size()).forEach(index -> {
                    System.out.print(stack.get(index).getData() + " ");
                });
                System.out.println();
            }
        }
        printAllKSumPathsFromBST(rootReference.getLeftChild(), stack, kSum);

        printAllKSumPathsFromBST(rootReference.getRightChild(), stack, kSum);

        stack.pop();
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

        public void setRightChild(Node righChild) {
            this.rightChild = righChild;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }
}
