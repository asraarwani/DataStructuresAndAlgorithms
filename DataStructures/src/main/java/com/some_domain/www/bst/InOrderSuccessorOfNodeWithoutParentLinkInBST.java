package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/29/2019 11:31 AM
 * This class demonstrates how to find an in-order successor a given node from BST without using parent node link
 */
//Reference : https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
//Reference : https://www.techiedelight.com/find-inorder-successor-given-key-bst/
//Reference : https://algorithms.tutorialhorizon.com/inorder-successor-in-binary-search-tree-without-using-parent-link/
public class InOrderSuccessorOfNodeWithoutParentLinkInBST {

    private Node root;

    public InOrderSuccessorOfNodeWithoutParentLinkInBST() {
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

        InOrderSuccessorOfNodeWithoutParentLinkInBST bst = new InOrderSuccessorOfNodeWithoutParentLinkInBST();
        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 10);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 5);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 1);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 7);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        bst.setRoot(rootReference);

        Node givenNode = bst.getRoot();//.getLeftChild().getLeftChild().getRightChild().getRightChild();
        System.out.println("Given node is " + givenNode.getData());

        Node inOrderSuccessorNode = bst.findInOrderSuccessorOfNode(bst.getRoot(), givenNode);
        if (inOrderSuccessorNode != null) {
            System.out.println("In-Order successor node of given node is : " + inOrderSuccessorNode.getData());
            System.out.println("Time complexity is O(H) where H is the height of the BST");
        } else {
            System.out.println("In-Order successor of given node doesn't exist");
        }

        System.out.println();

        inOrderSuccessorNode = bst.findInOrderSuccessorOfNodeRecursively(bst.getRoot(), null, givenNode);
        if (inOrderSuccessorNode != null) {
            System.out.println("In-Order successor node of given node is : " + inOrderSuccessorNode.getData());
            System.out.println("Time complexity is O(H) where H is the height of the BST");
        } else {
            System.out.println("In-Order successor of given node doesn't exist");
        }
    }


    public Node findInOrderSuccessorOfNodeRecursively(Node rootReference, Node successorNode, Node givenNode) {
        if (rootReference == null)
            return null;
        if (rootReference.getData() == givenNode.getData()) {
            if (rootReference.getRightChild() != null) {
                return findMinimumNodeOnLeftOfNode(rootReference.getRightChild());
            }
        } else if (givenNode.getData() < rootReference.getData()) {
            successorNode = rootReference;
            return findInOrderSuccessorOfNodeRecursively(rootReference.getLeftChild(), successorNode, givenNode);
        } else {
            return findInOrderSuccessorOfNodeRecursively(rootReference.getRightChild(), successorNode, givenNode);
        }
        return successorNode;
    }

    public Node findInOrderSuccessorOfNode(Node rootReference, Node givenNode) {

        //Case 1 : If the NODE has a right child then its in-order successor will the left most element in the right sub tree of NODE.
        if (givenNode.getRightChild() != null) {
            return findMinimumNodeOnLeftOfNode(givenNode.getRightChild());
        }

        //Case 2: If the NODE doesn’t have a right child then its in-order successor will the one of its ancestors,
        //use the binary search technique to find the NODE, start from the root, if root is bigger than the NODE then go left,
        // if root is less than NODE, go right. while traveling whenever you go left , store the node and call it successor.
        Node successorNode = null;
        while (rootReference != null) {
            if (rootReference.getData() > givenNode.getData()) {
                successorNode = rootReference;
                rootReference = rootReference.getLeftChild();
            } else if (rootReference.getData() < givenNode.getData()) {
                rootReference = rootReference.getRightChild();
            } else {
                return successorNode;
            }
        }
        return null;
    }

    private Node findMinimumNodeOnLeftOfNode(Node nodeReference) {
        while (nodeReference.getLeftChild() != null) {
            nodeReference = nodeReference.getLeftChild();
        }
        return nodeReference;
    }

    public Node insertNodeIntoBSTRecursively(Node rootReference, int data) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursively(rootReference.getLeftChild(), data));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursively(rootReference.getRightChild(), data));
        }
        return rootReference;
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
