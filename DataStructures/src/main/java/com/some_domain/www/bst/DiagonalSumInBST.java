package com.some_domain.www.bst;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author : waniasra
 * @date : 10/31/2019 5:11 PM
 * This class demonstrates hwo to print the diagonal sum of a BST
 */

/**
 * Diagonal sum of a BST is the sum of all the nodes' data lying between diagonal lines or slope lines
 */
//Reference : https://www.youtube.com/watch?v=I3BC8nEKYm8
//Reference : https://www.geeksforgeeks.org/diagonal-sum-binary-tree/
public class DiagonalSumInBST {

    private Node root;

    public DiagonalSumInBST() {
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
                               /       \
                              4         17
         */

        // 20 25 30 = 75
        // 15 16 24 17 = 72
        // 8 = 8
        // 4 = 4

        DiagonalSumInBST bst = new DiagonalSumInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(4);
        bst.insertNodeIntoBSTIteratively(17);

        System.out.println("Diagonal sum for the BST is given as follows");
        bst.printDiagonalSumInBST(bst.getRoot());
        System.out.println("\nTime complexity is O(N)");

        System.out.println();
        System.out.println("Diagonal sum for the BST using alternate approach is given as follows");
        bst.printDiagonalSumInBSTAlternate(bst.getRoot());
        System.out.println("\nTime complexity is O(N)");

        System.out.println();
        System.out.println("Diagonal sum for the BST using alternate approach where we need to modify the Node class by adding one more filed 'diagonalDistance'");
        bst.printDiagonalSumInBSTAlternate2(bst.getRoot());
        System.out.println("\nTime complexity is O(N)");
    }

    public void printDiagonalSumInBSTAlternate(Node rootReference) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        printDiagonalSumInBSTAlternateHelper(rootReference, map, 0);
        map.entrySet().stream().forEach(entry -> {
            System.out.print(entry.getValue() + " ");
        });
    }

    private void printDiagonalSumInBSTAlternateHelper(Node rootReference, Map<Integer, Integer> map, int diagonalDistance) {
        if (rootReference == null)
            return;
        Integer sum = map.get(diagonalDistance);
        if (sum == null) {
            sum = 0;
        }

        sum = sum + rootReference.getData();
        map.put(diagonalDistance, sum);

        printDiagonalSumInBSTAlternateHelper(rootReference.getLeftChild(), map, diagonalDistance + 1);

        printDiagonalSumInBSTAlternateHelper(rootReference.getRightChild(), map, diagonalDistance);
    }

    /**
     * For this approach, we need to modify the Node class by adding additional field "diagonalDistance"
     *
     * @param rootReference
     */
    public void printDiagonalSumInBSTAlternate2(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
            return;
        } else {
            Map<Integer, Integer> map = new LinkedHashMap<>();
            Node traversingNode = rootReference;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(traversingNode);
            while (!queue.isEmpty()) {
                traversingNode = queue.poll();
                int diagonalDistance = traversingNode.getDiagonalDistance();
                while (traversingNode != null) {
                    int previousSum = (map.get(diagonalDistance) == null) ? 0 : map.get(diagonalDistance);
                    map.put(diagonalDistance, previousSum + traversingNode.getData());

                    //If currently processing node which in our case is traversingNode has a left child, we do two things:
                    //1. Assign a diagonal distance to it (parent node's diagonalDistance + 1)
                    //2. Add it to the queue
                    if (traversingNode.getLeftChild() != null) {
                        traversingNode.getLeftChild().setDiagonalDistance(diagonalDistance + 1);
                        queue.add(traversingNode.getLeftChild());
                    }

                    traversingNode = traversingNode.getRightChild();
                }
            }
            map.entrySet().stream().forEach(entry -> {
                System.out.print(entry.getValue() + " ");
            });
        }
    }

    public void printDiagonalSumInBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty.");
            return;
        } else {
            int sum = 0;
            Node traversingNode = rootReference;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(traversingNode);
            queue.offer(null);
            while (!queue.isEmpty()) {
                traversingNode = queue.poll();
                if (traversingNode == null) {
                    System.out.print(sum + " ");
                    sum = 0;
                    queue.offer(null);
                    traversingNode = queue.poll();
                    if (traversingNode == null) {
                        break;
                    }
                }
                while (traversingNode != null) {
                    sum = sum + traversingNode.getData();
                    if (traversingNode.getLeftChild() != null) {
                        queue.offer(traversingNode.getLeftChild());
                    }
                    traversingNode = traversingNode.getRightChild();
                }
            }
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, 0, null, null);
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
        private int diagonalDistance;
        private Node leftChild;
        private Node rightChild;

        public Node(int data, int diagonalDistance, Node leftChild, Node rightChild) {
            this.data = data;
            this.diagonalDistance = diagonalDistance;
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

        public int getDiagonalDistance() {
            return diagonalDistance;
        }

        public void setDiagonalDistance(int diagonalDistance) {
            this.diagonalDistance = diagonalDistance;
        }
    }
}
