package com.some_domain.www.bst;

import java.util.*;

/**
 * @author : waniasra
 * @date : 11/25/2019 9:58 PM
 * This class demonstrates how to print the horizontal/level sum for each level of BST
 */
//Reference : https://www.geeksforgeeks.org/sum-of-all-the-levels-in-a-binary-search-tree/
public class PrintHorizontalSumOfAllLevelsOfBST {


    private Node root;

    public PrintHorizontalSumOfAllLevelsOfBST() {
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

        PrintHorizontalSumOfAllLevelsOfBST bst = new PrintHorizontalSumOfAllLevelsOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printHorizontalSumOfEachLevelOfBST(bst.getRoot());

        System.out.println();

        System.out.println("Alternate approach using a map");
        bst.printHorizontalSumOfEachLevelOfBSTAlternate(bst.getRoot());

        System.out.println("Time and space complexity for above two approaches is O(N).");

        System.out.println();
        bst.printHorizontalSumOfAllLevelsAlternateApproach(bst.getRoot());
        System.out.println("\nTime and space complexity for this approach is O(N).");
    }


    public void printHorizontalSumOfEachLevelOfBST(Node rootReference) {
        int numberOfLevels = calculateHeightOfBST(rootReference);
        int[] levelSum = new int[numberOfLevels];
        printHorizontalSumOfEachLevelOfBSTHelper(rootReference, 0, levelSum);
        Arrays.stream(levelSum).forEach(sum -> System.out.print(sum + " "));
    }

    private void printHorizontalSumOfEachLevelOfBSTHelper(Node rootReference, int levelNumber, int[] levelSum) {
        if (rootReference == null)
            return;

        levelSum[levelNumber] = levelSum[levelNumber] + rootReference.getData();

        printHorizontalSumOfEachLevelOfBSTHelper(rootReference.getLeftChild(), levelNumber + 1, levelSum);

        printHorizontalSumOfEachLevelOfBSTHelper(rootReference.getRightChild(), levelNumber + 1, levelSum);
    }

    public void printHorizontalSumOfEachLevelOfBSTAlternate(Node rootReference) {
        Map<Integer, Integer> sumMap = new LinkedHashMap<>();
        printHorizontalSumOfEachLevelOfBSTAlternateHelper(rootReference, 1, sumMap);
        sumMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
    }

    private void printHorizontalSumOfEachLevelOfBSTAlternateHelper(Node rootReference, int levelNumber, Map<Integer, Integer> sumMap) {
        if (rootReference == null)
            return;

        Integer levelSum = sumMap.get(levelNumber);
        if (levelSum == null) {
            sumMap.put(levelNumber, rootReference.getData());
        } else {
            levelSum = levelSum + rootReference.getData();
            sumMap.put(levelNumber, levelSum);
        }

        printHorizontalSumOfEachLevelOfBSTAlternateHelper(rootReference.getLeftChild(), levelNumber + 1, sumMap);

        printHorizontalSumOfEachLevelOfBSTAlternateHelper(rootReference.getRightChild(), levelNumber + 1, sumMap);
    }

    private int calculateHeightOfBST(Node rootReference) {
        if (rootReference == null)
            return 0;
        else {
            int leftSubTreeHeight = calculateHeightOfBST(rootReference.getLeftChild());
            int rightSubTreeHeight = calculateHeightOfBST(rootReference.getRightChild());
            return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
        }
    }

    /*
        The idea is to use level order traversal of BST
     */
    public void printHorizontalSumOfAllLevelsAlternateApproach(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            while (!queue.isEmpty()) {
                int currentLevelSize = queue.size();
                int currentLevelSum = 0;
                for (int i = 0; i < currentLevelSize; i++) {
                    rootReference = queue.poll();
                    currentLevelSum = currentLevelSum + rootReference.getData();

                    //If polled node has a left child
                    if (rootReference.getLeftChild() != null) {
                        queue.offer(rootReference.getLeftChild());
                    }

                    //If polled node has a right child
                    if (rootReference.getRightChild() != null) {
                        queue.offer(rootReference.getRightChild());
                    }
                }
                System.out.print(currentLevelSum + "  ");
            }
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
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
