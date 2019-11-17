package com.some_domain.www.bst;

import java.util.*;

/**
 * @author : waniasra
 * @date : 11/16/2019 11:18 AM
 * This class demonstrates how to find a pair of nodes whose sum is equal to a given sum
 */

//Reference : https://www.geeksforgeeks.org/find-a-pair-with-given-sum-in-bst/
//Reference : https://www.geeksforgeeks.org/find-pair-given-sum-bst/
public class PairWithGivenSumExistsInBST {

    private Node root;

    public PairWithGivenSumExistsInBST() {
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

        PairWithGivenSumExistsInBST bst = new PairWithGivenSumExistsInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        int givenSum = 28;
        boolean pairExists = bst.pairWithGivenSumExistsInBST(bst.getRoot(), givenSum);
        if (pairExists)
            System.out.println("Pair exists");
        else
            System.out.println("Pair doesn't exist");

        pairExists = bst.pairWithGivenSumExistsInBSTAlternate(bst.getRoot(), givenSum);
        if (pairExists)
            System.out.println("Pair exists");
        else
            System.out.println("Pair doesn't exist");

        System.out.println("Time and space complexity for above two approaches is O(N)");

        System.out.println();
        pairExists = bst.pairWithGivenSumExistsInBSTEnhanced(bst.getRoot(), givenSum);
        if (pairExists)
            System.out.println("Pair exists");
        else
            System.out.println("Pair doesn't exist");
        System.out.println("Time and space complexity for this approach is O(N) and O(LogN) respectively");
        System.out.println("Space complexity : 2 * O(LogN) which is equal to O(LogN), constants are ignored. Means at any point of time, maximum there will be logN elements in stack");
    }

    /**
     * We traverse BST in Normal in-order and reverse in-order simultaneously.
     * In reverse in-order, we start from the rightmost node which is the maximum value node.
     * In normal in-order, we start from the left most node which is minimum value node.
     * We add sum of current nodes in both traversals and compare this sum with given target sum.
     * If the sum is same as target sum, we return true.
     * If the sum is more than target sum, we move to next node in reverse in-order traversal,
     * otherwise we move to next node in normal in-order traversal.
     * If any of the traversals is finished without finding a pair, we return false.
     */
    //Reference : https://ide.geeksforgeeks.org/oZ0MCUYkqH
    public boolean pairWithGivenSumExistsInBSTEnhanced(Node rootReference, int givenSum) {
        if (rootReference == null)
            return false;
        Stack<Node> leftStack = new Stack<>();
        Stack<Node> rightStack = new Stack<>();
        Node leftStackReference = rootReference;
        Node rightStackReference = rootReference;
        while (true) {

            while (leftStackReference != null) {
                leftStack.push(leftStackReference);
                leftStackReference = leftStackReference.getLeftChild();
            }

            while (rightStackReference != null) {
                rightStack.push(rightStackReference);
                rightStackReference = rightStackReference.getRightChild();
            }

            if (leftStack.isEmpty() || rightStack.isEmpty())
                return false;

            leftStackReference = leftStack.peek();
            rightStackReference = rightStack.peek();
            if ((leftStackReference.getData() + rightStackReference.getData() == givenSum)) {
                if (leftStackReference == rightStackReference)
                    return false;
                System.out.println("There exists a pair ( " + leftStackReference.getData() + " , " + rightStackReference.getData() + " )" +
                        " whose sum is equal to : " + givenSum);
                return true;
            } else if ((leftStackReference.getData() + rightStackReference.getData() < givenSum)) {
                leftStack.pop();
                leftStackReference = leftStackReference.getRightChild();
                rightStackReference = null;
            } else {
                rightStack.pop();
                rightStackReference = rightStackReference.getLeftChild();
                leftStackReference = null;
            }
        }
    }

    /**
     * This solution is based on hashing.
     * We traverse binary search tree by in-order way and insert node’s value into a set.
     * Also check for any node, difference between given sum and node’s value in set,
     * if it is found then pair exists otherwise not
     */
    public boolean pairWithGivenSumExistsInBSTAlternate(Node rootReference, int givenSum) {
        return pairWithGivenSumExistsInBSTAlternateHelper(rootReference, givenSum, new HashSet<>());
    }

    private boolean pairWithGivenSumExistsInBSTAlternateHelper(Node rootReference, int givenSum, Set<Integer> set) {
        if (rootReference == null)
            return false;
        if (pairWithGivenSumExistsInBSTAlternateHelper(rootReference.getLeftChild(), givenSum, set))
            return true;
        if (set.contains(givenSum - rootReference.getData())) {
            System.out.println("There exists a pair ( " + rootReference.getData() + " , " + (givenSum - rootReference.getData()) + " )" +
                    " whose sum is equal to : " + givenSum);
            return true;
        } else {
            set.add(rootReference.getData());
        }
        if (pairWithGivenSumExistsInBSTAlternateHelper(rootReference.getRightChild(), givenSum, set))
            return true;
        return false;
    }

    /**
     * Solution is to create an auxiliary array/list and store in-order traversal of BST in the array/list.
     * The array will be sorted as in-order traversal of BST always produces sorted data.
     * Once we have the in-order traversal, we can find pair in O(n) time.
     * This solution works in O(n) time, but requires O(n) auxiliary space.
     */
    public boolean pairWithGivenSumExistsInBST(Node rootReference, int givenSum) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return false;
        } else {
            List<Integer> nodeList = new ArrayList<>();
            convertBSTtoList(rootReference, nodeList);
            int startingIndex = 0;
            int endingIndex = nodeList.size() - 1;
            while (startingIndex < endingIndex) {
                if (nodeList.get(startingIndex) + nodeList.get(endingIndex) == givenSum) {
                    System.out.println("There exists a pair ( " + nodeList.get(startingIndex) + " , " + nodeList.get(endingIndex) + " )" +
                            " whose sum is equal to : " + givenSum);
                    return true;
                } else if (nodeList.get(startingIndex) + nodeList.get(endingIndex) > givenSum) {
                    endingIndex--;
                } else if (nodeList.get(startingIndex) + nodeList.get(endingIndex) < givenSum) {
                    startingIndex++;
                }
            }
            return false;
        }
    }

    private List<Integer> convertBSTtoList(Node rootReference, List<Integer> nodeList) {
        if (rootReference != null) {
            convertBSTtoList(rootReference.getLeftChild(), nodeList);
            nodeList.add(rootReference.getData());
            convertBSTtoList(rootReference.getRightChild(), nodeList);
        }
        return nodeList;
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node currentlyProcessingNode = null;
            Node traversalNode = root;
            while (true) {
                currentlyProcessingNode = traversalNode;
                if (data <= traversalNode.getData()) {
                    traversalNode = traversalNode.getLeftChild();
                    if (traversalNode == null) {
                        currentlyProcessingNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversalNode = traversalNode.getRightChild();
                    if (traversalNode == null) {
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
