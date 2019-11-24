package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 11/24/2019 10:17 PM
 * This class demonstrates how to print even and odd nodes from a BST
 */

//Reference : https://www.geeksforgeeks.org/print-all-odd-nodes-of-binary-search-tree/
//Reference : https://www.geeksforgeeks.org/print-all-even-nodes-of-binary-search-tree/
public class PrintAllEvenAndOddNodesOfBST {

    private Node root;

    public PrintAllEvenAndOddNodesOfBST() {
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

        PrintAllEvenAndOddNodesOfBST bst = new PrintAllEvenAndOddNodesOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        System.out.println("Even nodes of the BST are as follows : ");
        bst.printEvenNodesOfBST(bst.getRoot());

        System.out.println("\n");

        System.out.println("Odd nodes of the BST are as follows : ");
        bst.printOddNodesOfBST(bst.getRoot());

        System.out.println("\n\nTime complexity of the above two approaches is O(N)");
    }

    public void printOddNodesOfBST(Node rootReference) {
        if (rootReference != null) {
            printOddNodesOfBST(rootReference.getLeftChild());
            if (rootReference.getData() % 2 != 0) {
                System.out.print(rootReference.getData() + " ");
            }
            printOddNodesOfBST(rootReference.getRightChild());
        }
    }

    public void printEvenNodesOfBST(Node rootReference) {
        if (rootReference != null) {
            printEvenNodesOfBST(rootReference.getLeftChild());
            if (rootReference.getData() % 2 == 0) {
                System.out.print(rootReference.getData() + " ");
            }
            printEvenNodesOfBST(rootReference.getRightChild());
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processingNodeParent = null;
            Node traversalNode = root;
            while (true) {
                processingNodeParent = traversalNode;
                if (data <= traversalNode.getData()) {
                    traversalNode = traversalNode.getLeftChild();
                    if (traversalNode == null) {
                        processingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversalNode = traversalNode.getRightChild();
                    if (traversalNode == null) {
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
