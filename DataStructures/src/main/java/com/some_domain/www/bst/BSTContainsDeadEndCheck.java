package com.some_domain.www.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : waniasra
 * @date : 11/13/2019 10:26 PM
 * This class demonstrates how to check if a given BST contains a dead end or not.
 * By dead end, we mean there exists a node in BST after which a node can't be inserted
 * Note : We assume BST contains only positive integer greater than 0
 */
//Reference : https://www.geeksforgeeks.org/check-whether-bst-contains-dead-end-not/
//Reference : https://www.geeksforgeeks.org/simple-recursive-solution-check-whether-bst-contains-dead-end/
public class BSTContainsDeadEndCheck {

    private Node root;

    public BSTContainsDeadEndCheck() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        /*
                        8
                      /   \
                    7     10
                 /      /   \
                2      9     13

        Approach 2 : For lead node 9, we have both 9+1 and 9-1 present in the BST, hence there exists a dead-end
        */

        BSTContainsDeadEndCheck bst = new BSTContainsDeadEndCheck();
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(7);
        bst.insertNodeIntoBSTIteratively(10);
        bst.insertNodeIntoBSTIteratively(2);
        bst.insertNodeIntoBSTIteratively(9);
        bst.insertNodeIntoBSTIteratively(13);

       /* bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(5);
        bst.insertNodeIntoBSTIteratively(9);
        bst.insertNodeIntoBSTIteratively(2);
        bst.insertNodeIntoBSTIteratively(7);
        bst.insertNodeIntoBSTIteratively(1);*/

        boolean containsDeadEnd = bst.containsDeadEnd(bst.getRoot(), 1, Integer.MAX_VALUE);
        if (containsDeadEnd)
            System.out.println("BST contains dead end");
        else
            System.out.println("BST doesn't contain  dead end");

        containsDeadEnd = bst.containsDeadEndAlternate(bst.getRoot());
        if (containsDeadEnd)
            System.out.println("BST contains dead end");
        else
            System.out.println("BST doesn't contain  dead end");

        System.out.println("Time complexity for above two approaches is O(N) where N is the number of the nodes in BST");
    }

    /**
     * First of all, it is given that it is a BST and nodes are greater than zero,
     * root node can be in the range [1, ∞] and if root val is say, val, then left sub-tree can have the value in the range [1, val-1]
     * and right sub-tree the value in range [val+1, ∞].
     * We need to traverse recursively and when the the min and max value of range coincided it means that we cannot add any node further in the tree.
     * Hence we encounter a dead end.
     */
    public boolean containsDeadEnd(Node rootReference, int minimumRange, int maximumRange) {
        if (rootReference == null)
            return false;

        if (minimumRange == maximumRange)
            return true;

        return containsDeadEnd(rootReference.getLeftChild(), minimumRange, rootReference.getData() - 1)
                || containsDeadEnd(rootReference.getRightChild(), rootReference.getData() + 1, maximumRange);
    }

    /**
     * If we take a closer look at problem, we can notice that we basically need to check if there is leaf node with value x such that x+1 and x-1 exist in BST with exception of x = 1.
     * For x = 1, we can’t insert 0 as problem statement says BST contains positive integers only.
     * To implement above idea we first traverse whole BST and store all nodes in a hash_map.
     * We also store all leaves in a separate hash to avoid re-traversal of BST.
     * Finally we check for every leaf node x, if x-1 and x+1 are present in hash_map or not.
     */
    public boolean containsDeadEndAlternate(Node rootReference) {
        if (rootReference == null)
            return false;
        List<Integer> allNodes = new ArrayList<>();
        List<Integer> leafNodes = new ArrayList<>();

        storeNodesInHashMap(rootReference, allNodes, leafNodes);

        for (int i = 0; i < leafNodes.size(); i++) {
            int leafNode = leafNodes.get(i);
            // If BST contains 1, we need to add additional condition (leafNode - 1 != 0 ), otherwise condition is ( (allNodes.contains(leafNode + 1) && allNodes.contains(leafNode - 1)) )
            if (allNodes.contains(leafNode + 1) && (leafNode - 1 != 0 || allNodes.contains(leafNode - 1))) {
                return true;
            }
        }
        return false;
    }

    private void storeNodesInHashMap(Node rootReference, List<Integer> allNodes, List<Integer> leafNodes) {
        if (rootReference == null)
            return;

        allNodes.add(rootReference.getData());

        if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
            leafNodes.add(rootReference.getData());
        }

        storeNodesInHashMap(rootReference.getLeftChild(), allNodes, leafNodes);
        storeNodesInHashMap(rootReference.getRightChild(), allNodes, leafNodes);
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyBeingProcessedNode = null;
            Node traversingNode = root;
            while (true) {
                currentlyBeingProcessedNode = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        currentlyBeingProcessedNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        currentlyBeingProcessedNode.setRightChild(newNode);
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
