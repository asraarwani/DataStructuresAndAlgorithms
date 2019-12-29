package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/29/2019 11:14 AM
 * This class demonstrates how to find the in-order successor of a given node in BST using parent link
 */
//Reference : https://algorithms.tutorialhorizon.com/inorder-successor-in-binary-search-tree-using-parent-link/
//Reference : https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
public class InOrderSuccessorOfNodeWithParentInBST {

    private Node root;

    public InOrderSuccessorOfNodeWithParentInBST() {
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
                                      15
                                     /
                                   10
                                  /
                                5
                               /  \
                             1     7
                                     \
                                       8
         */

        InOrderSuccessorOfNodeWithParentInBST bst = new InOrderSuccessorOfNodeWithParentInBST();
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(10);
        bst.insertNodeIntoBSTIteratively(5);
        bst.insertNodeIntoBSTIteratively(1);
        bst.insertNodeIntoBSTIteratively(7);
        bst.insertNodeIntoBSTIteratively(8);

        Node givenNode = bst.getRoot().getLeftChild().getLeftChild().getRightChild().getRightChild();
        System.out.println("Given node is " + givenNode.getData());

        Node inOrderSuccessorNode = bst.findInOrderSuccessorOfNode(bst.getRoot(), givenNode);
        if (inOrderSuccessorNode != null) {
            System.out.println("In-Order successor node of given node is : " + inOrderSuccessorNode.getData());
            System.out.println("\nTime complexity is O(H) where H is the height of the BST");
        } else {
            System.out.println("In-Order successor of given node doesn't exist");
        }
    }

    public Node findInOrderSuccessorOfNode(Node rootReference, Node givenNode) {

        //Case 1 : If the NODE has a right child then its in-order successor will the left most element in the right sub tree of NODE.
        if (givenNode.getRightChild() != null) {
            return findMinimumNodeOnLeftOfNode(givenNode.getRightChild());
        }

        //Case 2: If the NODE doesn’t have a right child then its in-order successor will the one of its ancestors,
        //using parent link keep traveling up till you get the node which is the left child of its parent.
        //Then this parent node will be the in-order successor.
        Node parentNode = givenNode.getParentNode();
        while (parentNode != null && givenNode == parentNode.getRightChild()) {
            givenNode = parentNode;
            parentNode = parentNode.getParentNode();
        }
        return parentNode;
    }

    private Node findMinimumNodeOnLeftOfNode(Node nodeReference) {
        while (nodeReference.getLeftChild() != null) {
            nodeReference = nodeReference.getLeftChild();
        }
        return nodeReference;
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null, null);
        if (root == null) {
            newNode.setParentNode(null); //Setting the parent node link
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
                        newNode.setParentNode(parentOfCurrentNode); //Setting the parent node link
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentOfCurrentNode.setRightChild(newNode);
                        newNode.setParentNode(parentOfCurrentNode);//Setting the parent node link
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
        private Node parentNode;

        public Node(int data, Node leftChild, Node rightChild, Node parentNode) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parentNode = parentNode;
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

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }
    }
}
