package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/16/2019 10:22 PM
 * This class demonstrates how to convert a given BST into doubly linked list
 */
// Reference : https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
public class ConvertBSTToDoublyLinkedList {

    private Node root;

    public ConvertBSTToDoublyLinkedList() {
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

        ConvertBSTToDoublyLinkedList bst = new ConvertBSTToDoublyLinkedList();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        System.out.println("In order traversal of BST is given as follows");
        bst.inOrderTraversalOfBST(bst.getRoot());

        //In order to avoid the use of static fields for previous, head and tail, type DLL is used
        DLL list = new DLL();
        bst.convertBSTToDoublyLinkedList(bst.getRoot(), list);

        System.out.println("\n\nContents of converted doubly linked list using forward traversal are given as follows");
        bst.printDLLUsingForwardTraversal(list.getHead());

        System.out.println("\n\nContents of converted doubly linked list using backward traversal are given as follows");
        bst.printDLLUsingBackwardTraversal(list.getTail());

        System.out.println("\n\nTime complexity is O(N) as we are using in-order traversal of BST for conversion");
    }

    public void convertBSTToDoublyLinkedList(Node rootReference, DLL list) {
        if (rootReference == null)
            return;
        convertBSTToDoublyLinkedList(rootReference.getLeftChild(), list);

        if (list.getPrevious() == null) {
            list.setHead(rootReference);
        } else {
            Node previousNode = list.getPrevious();
            rootReference.setLeftChild(previousNode);
            previousNode.setRightChild(rootReference);
        }
        list.setPrevious(rootReference);
        list.setTail(rootReference);

        convertBSTToDoublyLinkedList(rootReference.getRightChild(), list);
    }

    public void printDLLUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Converted doubly linked list is empty.");
        } else {
            Node traversalNode = headReference;
            while (traversalNode != null) {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getRightChild();
            }
        }
    }

    public void printDLLUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Converted doubly linked list is empty.");
        } else {
            Node traversalNode = tailReference;
            while (traversalNode != null) {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getLeftChild();
            }
        }
    }

    public void inOrderTraversalOfBST(Node rootReference) {
        if (rootReference != null) {
            inOrderTraversalOfBST(rootReference.getLeftChild());
            System.out.print(rootReference.getData() + " ");
            inOrderTraversalOfBST(rootReference.getRightChild());
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

    private static class DLL {

        private Node head;
        private Node previous;
        private Node tail;

        public DLL() {
            this.head = null;
            this.previous = null;
            this.tail = null;
        }

        public Node getHead() {
            return head;
        }

        public void setHead(Node head) {
            this.head = head;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getTail() {
            return tail;
        }

        public void setTail(Node tail) {
            this.tail = tail;
        }
    }
}
