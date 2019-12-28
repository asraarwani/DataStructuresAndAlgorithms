package com.some_domain.www.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : waniasra
 * @date : 12/21/2019 12:47 PM
 * This class demonstrates how to merge two balanced BSTs
 */
//Reference : https://www.geeksforgeeks.org/merge-two-balanced-binary-search-trees/
public class MergeTwoBalancedBSTs {

    private Node root;

    public MergeTwoBalancedBSTs() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        // Step 1: Store in-order traversal of first balanced BST in a list or an array                O(N)
        // Step 2: Store in-order traversal of second balanced BST in a list or an array               O(N)
        // Step 3: Merge two in-order traversals created in step 1 and 2                               O(M+N)
        // Step 4: Construct a balanced BST from the the merged list or array                          O(M+N)
        //                                                                                           = O(M+N)

          /* Balanced BST 1:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   22   30
         */
        MergeTwoBalancedBSTs firstBalancedBST = new MergeTwoBalancedBSTs();
        firstBalancedBST.insertNodeIntoBSTIteratively(20);
        firstBalancedBST.insertNodeIntoBSTIteratively(15);
        firstBalancedBST.insertNodeIntoBSTIteratively(25);
        firstBalancedBST.insertNodeIntoBSTIteratively(8);
        firstBalancedBST.insertNodeIntoBSTIteratively(16);
        firstBalancedBST.insertNodeIntoBSTIteratively(22);
        firstBalancedBST.insertNodeIntoBSTIteratively(30);

           /* Balanced BST 2:-
                                      19
                                    /    \
                                   13      24
                                 /  \      /  \
                                7    14   23   29
         */

        MergeTwoBalancedBSTs secondBalancedBST = new MergeTwoBalancedBSTs();
        secondBalancedBST.insertNodeIntoBSTIteratively(19);
        secondBalancedBST.insertNodeIntoBSTIteratively(13);
        secondBalancedBST.insertNodeIntoBSTIteratively(24);
        secondBalancedBST.insertNodeIntoBSTIteratively(7);
        secondBalancedBST.insertNodeIntoBSTIteratively(14);
        secondBalancedBST.insertNodeIntoBSTIteratively(23);
        secondBalancedBST.insertNodeIntoBSTIteratively(29);

        new MergeTwoBalancedBSTs().mergeTwoBalancedBSTs(firstBalancedBST.getRoot(), secondBalancedBST.getRoot());
        System.out.println("\nTime complexity is O(M + N) where M and N are the number of the nodes in two balanced BSTs");

    }

    public void mergeTwoBalancedBSTs(Node firstBSTRootRef, Node secondBSTRootRef) {

        // Step 1
        List<Node> firstBSTInOrderTraversal = new ArrayList<>();
        constructInOrderTraversalOfBalancedBST(firstBSTRootRef, firstBSTInOrderTraversal);

        // Step 2
        List<Node> secondBSTInOrderTraversal = new ArrayList<>();
        constructInOrderTraversalOfBalancedBST(secondBSTRootRef, secondBSTInOrderTraversal);

        // Step 3
        List<Node> mergedInOrderTraversal = mergeTwoInOrderTraversalOfTwoBalancedBSTs(firstBSTInOrderTraversal, secondBSTInOrderTraversal);

        // Step 4
        int startingIndex = 0;
        int endingIndex = mergedInOrderTraversal.size() - 1;
        Node rootReference = constructBalancedBSTFromMergedInOrderTraversalOfTwoBalancedBSTs(mergedInOrderTraversal, startingIndex, endingIndex);

        inOrderTraversalOfBalancedBST(rootReference);
    }

    private Node constructBalancedBSTFromMergedInOrderTraversalOfTwoBalancedBSTs(List<Node> list, int staringIndex, int endingIndex) {
        if (staringIndex > endingIndex)
            return null;

        int middleIndex = (staringIndex + endingIndex) / 2;

        Node root = list.get(middleIndex);

        root.setLeftChild(constructBalancedBSTFromMergedInOrderTraversalOfTwoBalancedBSTs(list, staringIndex, middleIndex - 1));
        root.setRightChild(constructBalancedBSTFromMergedInOrderTraversalOfTwoBalancedBSTs(list, middleIndex + 1, endingIndex));

        return root;
    }

    private List<Node> mergeTwoInOrderTraversalOfTwoBalancedBSTs(List<Node> firstList, List<Node> secondList) {
        List<Node> resultNodeList = new ArrayList<>();
        int firstListSize = firstList.size();
        int secondListSize = secondList.size();
        int i = 0;
        int j = 0;
        while (i < firstListSize && j < secondListSize) {

            if (firstList.get(i).getData() < secondList.get(j).getData()) {
                resultNodeList.add(firstList.get(i));
                i++;
            } else {
                resultNodeList.add(secondList.get(j));
                j++;
            }
        }

        //If firstList exhausts, we directly add the remaining nodes of secondList to the final list
        while (j < secondListSize) {
            resultNodeList.add(secondList.get(j));
            j++;
        }

        //If secondList exhausts, we directly add the remaining nodes of the firstList to the final list
        while (i < firstListSize) {
            resultNodeList.add(firstList.get(i));
            i++;
        }
        return resultNodeList;
    }

    private void constructInOrderTraversalOfBalancedBST(Node rootReference, List<Node> list) {
        if (rootReference != null) {
            constructInOrderTraversalOfBalancedBST(rootReference.getLeftChild(), list);
            list.add(rootReference);
            constructInOrderTraversalOfBalancedBST(rootReference.getRightChild(), list);
        }
    }

    public void inOrderTraversalOfBalancedBST(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBalancedBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBalancedBST(rootReference.getRightChild());
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node parentNodeOfCurrent = null;
            Node currentNode = root;
            while (true) {
                parentNodeOfCurrent = currentNode;
                if (data <= currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        parentNodeOfCurrent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNodeOfCurrent.setRightChild(newNode);
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
