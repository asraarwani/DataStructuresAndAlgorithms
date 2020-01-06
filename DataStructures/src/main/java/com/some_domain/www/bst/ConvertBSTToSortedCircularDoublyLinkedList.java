package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/24/2019 1:13 PM
 * This class demonstrates how to convert a given BST into a sorted circular doubly linked list in-place
 */
//Reference : http://tech-queries.blogspot.com/2011/08/convert-binary-tree-in-circular-doubly.html
public class ConvertBSTToSortedCircularDoublyLinkedList {

    private Node root;

    public ConvertBSTToSortedCircularDoublyLinkedList() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

        ConvertBSTToSortedCircularDoublyLinkedList bst = new ConvertBSTToSortedCircularDoublyLinkedList();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        NodeDetails nodeDetails = new NodeDetails();
        bst.convertBSTToSortedCircularDoublyLinkedList(bst.getRoot(), nodeDetails);

        System.out.println("Forward traversal of converted circular sorted doubly linked list ");
        bst.printCircularDoublyLinkedListUsingForwardTraversal(nodeDetails.getHead());

        System.out.println("\n\nBackward traversal of converted circular sorted doubly linked list");
        bst.printCircularDoublyLinkedListUsingBackwardTraversal(nodeDetails.getTail());

        System.out.println("\nTime complexity is O(N)");
    }

    public void convertBSTToSortedCircularDoublyLinkedList(Node rootReference, NodeDetails nodeDetails) {
        if (rootReference != null) {

            convertBSTToSortedCircularDoublyLinkedList(rootReference.getLeftChild(), nodeDetails);

            rootReference.setLeftChild(nodeDetails.getPrevious()); //Setting the backward link (left)

            if (nodeDetails.getPrevious() == null) {
                nodeDetails.setHead(rootReference);
            } else {
                nodeDetails.getPrevious().setRightChild(rootReference); // Setting the forward link (right)
            }

            //Store the reference to the right subtree for further processing
            Node rightChildRef = rootReference.getRightChild();

            //Setting the circular links from head to currently processing node and back to head
            nodeDetails.getHead().setLeftChild(rootReference);
            rootReference.setRightChild(nodeDetails.getHead());

            //Updating the previous and tail node reference
            nodeDetails.setPrevious(rootReference);
            nodeDetails.setTail(rootReference);

            convertBSTToSortedCircularDoublyLinkedList(rightChildRef, nodeDetails);
        }
    }

    public void printCircularDoublyLinkedListUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Converted sorted circular doubly linked list is empty");
            return;
        } else {
            Node traversalNode = tailReference;
            do {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getLeftChild();
            } while (traversalNode != tailReference);
        }
    }

    public void printCircularDoublyLinkedListUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Converted sorted circular doubly linked list is empty");
            return;
        } else {
            Node traversalNode = headReference;
            do {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getRightChild();
            } while (traversalNode != headReference);
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
        private Node head;
        private Node tail;
        private Node previous;

        public Node getHead() {
            return head;
        }

        public void setHead(Node head) {
            this.head = head;
        }

        public Node getTail() {
            return tail;
        }

        public void setTail(Node tail) {
            this.tail = tail;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
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
