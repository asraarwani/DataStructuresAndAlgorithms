package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 10/26/2019 11:19 AM
 * This class demonstrates anti-clockwise boundary order traversal of BST (Generic)
 */
public class BoundaryOrderTraversalOfBSTGeneric {

    private Node root;

    public BoundaryOrderTraversalOfBSTGeneric() {
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
                                                \
                                                 36
         */

        BoundaryOrderTraversalOfBSTGeneric bst = new BoundaryOrderTraversalOfBSTGeneric();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(36);

        bst.printBoundaryOrderTraversalOfBST(bst.getRoot());

        System.out.println();
        bst.setRoot(null);
        bst.insertNodeIntoBSTIteratively(20.1);
        bst.insertNodeIntoBSTIteratively(15.1);
        bst.insertNodeIntoBSTIteratively(25.1);
        bst.insertNodeIntoBSTIteratively(8.1);
        bst.insertNodeIntoBSTIteratively(16.1);
        bst.insertNodeIntoBSTIteratively(24.1);
        bst.insertNodeIntoBSTIteratively(30.1);
        bst.insertNodeIntoBSTIteratively(36.1);

        bst.printBoundaryOrderTraversalOfBST(bst.getRoot());
    }

    public void printBoundaryOrderTraversalOfBST(Node rootReference) {

        if (rootReference == null) {
            System.out.println("BST is empty.");
        } else {
            System.out.print(rootReference.getData() + " ");
            printLeftBoundary(rootReference.getLeftChild());
            printLeafBoundary(rootReference.getLeftChild());
            printLeafBoundary(rootReference.getRightChild());
            printRightBoundary(rootReference.getRightChild());
        }
    }

    private void printLeafBoundary(Node rootReference) {
        if (rootReference == null)
            return;
        printLeafBoundary(rootReference.getLeftChild());
        if (rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
            System.out.print(rootReference.getData() + " ");
        }
        printLeafBoundary(rootReference.getRightChild());
    }

    private void printRightBoundary(Node rootReference) {
        if (rootReference == null)
            return;
        if (rootReference.getRightChild() != null) {
            printRightBoundary(rootReference.getRightChild());
            System.out.print(rootReference.getData() + " ");
        } else if (rootReference.getLeftChild() != null) {
            printRightBoundary(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
        }
    }

    private void printLeftBoundary(Node rootReference) {
        if (rootReference == null)
            return;
        if (rootReference.getLeftChild() != null) {
            System.out.print(rootReference.getData() + " ");
            printLeftBoundary(rootReference.getLeftChild());
        } else if (rootReference.getRightChild() != null) {
            System.out.print(rootReference.getData() + " ");
            printLeftBoundary(rootReference.getRightChild());
        }
    }

    public <T extends Comparable> void insertNodeIntoBSTIteratively(T data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processingNodeParent = null;
            Node traversingNode = root;
            while (true) {
                processingNodeParent = traversingNode;
                if (data.compareTo(traversingNode.getData()) <= 0) {
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

    private class Node<T extends Comparable> {
        private T data;
        private Node leftChild;
        private Node rightChild;

        public Node(T data, Node leftChild, Node rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
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
