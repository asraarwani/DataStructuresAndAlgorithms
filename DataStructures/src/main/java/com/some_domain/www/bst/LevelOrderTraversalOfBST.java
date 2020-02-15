package com.some_domain.www.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 10/14/2019 10:33 PM
 * This class demonstrates how to perform a level order traversal of a BST (BFS)
 * Also, shows how to print the nodes at each level of the BST
 */
public class LevelOrderTraversalOfBST {

    private Node root;

    public LevelOrderTraversalOfBST() {
        this.root = null;
    }

    public static void main(String[] args) {

        LevelOrderTraversalOfBST bst = new LevelOrderTraversalOfBST();

          /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        Node rootReference = bst.insertNodeIntoBST(20, null);
        rootReference = bst.insertNodeIntoBST(15, rootReference);
        rootReference = bst.insertNodeIntoBST(25, rootReference);
        rootReference = bst.insertNodeIntoBST(8, rootReference);
        rootReference = bst.insertNodeIntoBST(16, rootReference);
        rootReference = bst.insertNodeIntoBST(24, rootReference);
        rootReference = bst.insertNodeIntoBST(30, rootReference);

        System.out.println("Level order traversal of BST");
        bst.printLevelOrderTraversalOfBSTIteratively(rootReference);
        System.out.println("\nTime complexity using above approach is O(N^2)");

        System.out.println("\nLevel order traversal of BST using enhanced approach (using queue)");
        bst.printLevelOrderTraversalOfBSTEnhanced(rootReference);
        System.out.println("\nTime complexity is O(N)");

        System.out.println("\nList of nodes at each level of  the BST is given as follows");
        List<List<Integer>> list = bst.getListOfNodesAtEachLevel(rootReference);
        System.out.println(list.stream().flatMap(l -> l.stream()).collect(Collectors.toList()));
    }

    public List<List<Integer>> getListOfNodesAtEachLevel(Node rootReference) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (rootReference == null) {
            return resultList;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(rootReference);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevelNodesList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node processedNode = queue.poll();
                currentLevelNodesList.add(processedNode.getData());
                if (processedNode.getLeftChild() != null) {
                    queue.add(processedNode.getLeftChild());
                }
                if (processedNode.getRightChild() != null) {
                    queue.add(processedNode.getRightChild());
                }
            }
            resultList.add(currentLevelNodesList);
        }
        return resultList;
    }

    public void printLevelOrderTraversalOfBSTEnhanced(Node rootReference) {
        Queue<Node> queue = new LinkedList<>();
        if (rootReference != null) {
            queue.add(rootReference);
        }

        while (!queue.isEmpty()) {
            Node processedNode = queue.poll();
            System.out.print(processedNode.getData() + " ");

            //If processed node has left child, add it to the queue
            if (processedNode.getLeftChild() != null) {
                queue.add(processedNode.getLeftChild());
            }

            //If processed node has right child, add it to the queue
            if (processedNode.getRightChild() != null) {
                queue.add(processedNode.getRightChild());
            }
        }
    }

    public void printLevelOrderTraversalOfBSTIteratively(Node rootReference) {
        if (rootReference != null) {
            int heightOfBST = calculateHeightOfBST(rootReference);
            for (int i = 1; i <= heightOfBST; i++) {
                printNodesAtGivenLevel(rootReference, i);
            }
        }
    }

    private void printNodesAtGivenLevel(Node rootReference, int levelNumber) {
        if (rootReference == null)
            return;
        if (levelNumber == 1) {
            System.out.print(rootReference.getData() + " ");
        }
        printNodesAtGivenLevel(rootReference.getLeftChild(), levelNumber - 1);

        printNodesAtGivenLevel(rootReference.getRightChild(), levelNumber - 1);
    }

    private int calculateHeightOfBST(Node rootReference) {
        if (rootReference == null)
            return 0;
        else {
            int leftSubtreeHeight = calculateHeightOfBST(rootReference.getLeftChild());
            int rightSubtreeHeight = calculateHeightOfBST(rootReference.getRightChild());
            return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
        }
    }

    public Node insertNodeIntoBST(int data, Node rootReference) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBST(data, rootReference.getLeftChild()));
        } else if (data > rootReference.getData()) {
            rootReference.setRightChild(insertNodeIntoBST(data, rootReference.getRightChild()));
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
