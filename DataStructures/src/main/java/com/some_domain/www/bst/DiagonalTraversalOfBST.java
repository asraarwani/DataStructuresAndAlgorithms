package com.some_domain.www.bst;

import java.util.*;

/**
 * @author : waniasra
 * @date : 10/30/2019 10:09 PM
 * This class demonstrates how to do a diagonal traversal of a BST
 */

/**
 * For approach 2, idea is to assign the slope distance to each node. For left child node of a given node slope distance will be slopeDistanceOfParent + 1
 * and for right child node, it will be same as parent node.
 */

//Reference : https://www.youtube.com/watch?v=e9ZGxH1y_PE
//Reference : https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
public class DiagonalTraversalOfBST {

    private Node root;

    public DiagonalTraversalOfBST() {
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

        // 20 25 30 -> 15 16 24 -> 8 -> 4

        DiagonalTraversalOfBST bst = new DiagonalTraversalOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(4);

        System.out.println("Diagonal traversal of BST is given as follows:");
        bst.printDiagonalTraversalOfBST(bst.getRoot());

        System.out.println("\nDiagonal traversal of BST using alternate approach is given as follows:");
        bst.printDiagonalTraversalOfBSTAlternate(bst.getRoot());

        System.out.println("\nTime complexity for the above two approaches is going to be O(N)");
    }

    public void printDiagonalTraversalOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
            return;
        } else {
            Node traversingNode = rootReference;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(traversingNode);
            queue.offer(null);
            while (!queue.isEmpty()) {
                traversingNode = queue.poll();
                if (traversingNode == null) {
                    queue.offer(null);
                    traversingNode = queue.poll();
                    if (traversingNode == null) {
                        break;
                    }
                }
                while (traversingNode != null) {
                    System.out.print(traversingNode.getData() + " ");
                    //If node has a left child, enqueue it and then move to the right child
                    if (traversingNode.getLeftChild() != null) {
                        queue.offer(traversingNode.getLeftChild());
                    }
                    traversingNode = traversingNode.getRightChild();
                }
            }
        }
    }

    public void printDiagonalTraversalOfBSTAlternate(Node rootReference) {
        Map<Integer, List<Node>> diagonalMap = new LinkedHashMap<>();
        printDiagonalTraversalOfBSTAlternateHelper(rootReference, 0, diagonalMap);

        diagonalMap.entrySet().stream().forEach(entry -> {
            entry.getValue().stream().forEach(node -> System.out.print(node.getData() + " "));
        });
    }

    private void printDiagonalTraversalOfBSTAlternateHelper(Node rootReference, int diagonalNumber, Map<Integer, List<Node>> diagonalMap) {
        if (rootReference == null)
            return;
        List<Node> nodeList = diagonalMap.get(diagonalNumber);
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        nodeList.add(rootReference);

        //Store nodes with same diagonalDistance as a list
        diagonalMap.put(diagonalNumber, nodeList);

        //For left sub-tree, increase the diagonalDistance/slopeDistance by 1
        printDiagonalTraversalOfBSTAlternateHelper(rootReference.getLeftChild(), diagonalNumber + 1, diagonalMap);

        //For right sub-tree, diagonalDistance/slopeDistance will remain the same
        printDiagonalTraversalOfBSTAlternateHelper(rootReference.getRightChild(), diagonalNumber, diagonalMap);
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNode = null;
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
