package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : waniasra
 * @date : 2/22/2020 1:18 PM
 */
//Reference : https://www.geeksforgeeks.org/delete-the-last-leaf-node-in-a-binary-tree/
public class DeleteLastLeafNodeInBST {

    private Node root;

    public DeleteLastLeafNodeInBST() {
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

        DeleteLastLeafNodeInBST bst = new DeleteLastLeafNodeInBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        System.out.println("Before deleting the last leaf node from the BST");
        bst.printInOrderTraversalOfBSTIteratively(bst.getRoot());

        bst.deleteLastLeafNodeRecursively(bst.getRoot());

        System.out.println("\nAfter deleting the last leaf node from the BST using recursive approach");
        bst.printInOrderTraversalOfBSTIteratively(bst.getRoot());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in BST");

        System.out.println("\n\n\n");

        System.out.println("Before deleting the last leaf node from the BST");
        bst.printInOrderTraversalOfBSTIteratively(bst.getRoot());

        bst.deleteLastLeafNodeIteratively_UsingLevelOrderTraversal(bst.getRoot());

        System.out.println("\nAfter deleting the last leaf node from the BST using recursive approach");
        bst.printInOrderTraversalOfBSTIteratively(bst.getRoot());
        System.out.println("\nTime  and space complexity is O(N) where N is the number of nodes in BST");

    }

    public void deleteLastLeafNodeIteratively_UsingLevelOrderTraversal(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            NodeDetails nodeDetails = new NodeDetails();
            while (!queue.isEmpty()) {
                rootReference = queue.poll();

                //If polled node has a left child
                if (rootReference.getLeftChild() != null) {
                    queue.offer(rootReference.getLeftChild());
                    //Store the reference to the last leaf node and its parent
                    if (rootReference.getLeftChild().getLeftChild() == null && rootReference.getLeftChild().getRightChild() == null) {
                        nodeDetails.setLastLeafNodeParent(rootReference);
                        nodeDetails.setLastLeafNode(rootReference.getLeftChild());
                    }
                }

                //If polled node has a right child
                if (rootReference.getRightChild() != null) {
                    queue.offer(rootReference.getRightChild());
                    //Store the reference to the last leaf node and its parent
                    if (rootReference.getRightChild().getLeftChild() == null && rootReference.getRightChild().getRightChild() == null) {
                        nodeDetails.setLastLeafNodeParent(rootReference);
                        nodeDetails.setLastLeafNode(rootReference.getRightChild());
                    }
                }
            }

            //Deleting the last leaf node from the BST
            if (nodeDetails.getLastLeafNodeParent() != null && nodeDetails.getLastLeafNode() != null) {
                //If there is a right child which is going to be the last leaf node , delete it otherwise delete left child
                if (nodeDetails.getLastLeafNodeParent().getRightChild() != null) {
                    nodeDetails.getLastLeafNodeParent().setRightChild(null);
                } else {
                    nodeDetails.getLastLeafNodeParent().setLeftChild(null);
                }
            }
        }
    }

    public void deleteLastLeafNodeRecursively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        }
        int height = calculateHeightOfBST(rootReference);
        NodeDetails nodeDetails = new NodeDetails();
        deleteLastLeafNodeRecursivelyHelper(rootReference, nodeDetails, null, height);

        //Deleting the last leaf node from the BST
        if (nodeDetails.getLastLeafNodeParent() != null && nodeDetails.getLastLeafNode() != null) {
            //If there is a right child which is going to be the last leaf node , delete it otherwise delete left child
            if (nodeDetails.getLastLeafNodeParent().getRightChild() != null)
                nodeDetails.getLastLeafNodeParent().setRightChild(null);
            else
                nodeDetails.getLastLeafNodeParent().setLeftChild(null);
        }
    }

    private void deleteLastLeafNodeRecursivelyHelper(Node rootReference, NodeDetails nodeDetails, Node parentNode, int levelNumber) {
        if (rootReference == null)
            return;
        if (levelNumber == 1) {
            nodeDetails.setLastLeafNode(rootReference);
            nodeDetails.setLastLeafNodeParent(parentNode);
        }
        deleteLastLeafNodeRecursivelyHelper(rootReference.getLeftChild(), nodeDetails, rootReference, levelNumber - 1);
        deleteLastLeafNodeRecursivelyHelper(rootReference.getRightChild(), nodeDetails, rootReference, levelNumber - 1);
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

    public void printInOrderTraversalOfBSTIteratively(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Stack<Node> stack = new Stack<>();
            while (true) {

                while (rootReference != null) {
                    stack.push(rootReference);
                    rootReference = rootReference.getLeftChild();
                }

                if (stack.isEmpty())
                    break;

                rootReference = stack.pop();
                System.out.print(rootReference.getData() + " ");
                rootReference = rootReference.getRightChild();
            }
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node parentOfCurrentNode = null;
            Node currentNode = root;
            while (true) {
                parentOfCurrentNode = currentNode;
                if (data <= currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        parentOfCurrentNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentOfCurrentNode.setRightChild(newNode);
                        break;
                    }
                }
            }
        }
    }

    private static class NodeDetails {
        private Node lastLeafNode;
        private Node lastLeafNodeParent;

        public Node getLastLeafNode() {
            return lastLeafNode;
        }

        public void setLastLeafNode(Node lastLeafNode) {
            this.lastLeafNode = lastLeafNode;
        }

        public Node getLastLeafNodeParent() {
            return lastLeafNodeParent;
        }

        public void setLastLeafNodeParent(Node lastLeafNodeParent) {
            this.lastLeafNodeParent = lastLeafNodeParent;
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
