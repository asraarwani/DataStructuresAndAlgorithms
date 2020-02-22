package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 2/22/2020 9:34 PM
 * This class demonstrates how to serialize and de-serialize a given BST
 */
public class SerializeDeserializeBST {

    private Node root;

    public SerializeDeserializeBST() {
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

        SerializeDeserializeBST bst = new SerializeDeserializeBST();
        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        System.out.println("BST before serialization");
        bst.printInOrderTraversalOfBST(bst.getRoot());

        System.out.println("\n\nSerialized BST");
        String serializedBST = bst.serializeBST(bst.getRoot());
        System.out.println(serializedBST);

        System.out.println("\nDe-serialized BST");
        Node rootNode = bst.deserializeBST(serializedBST);
        bst.printInOrderTraversalOfBST(rootNode);
    }

    public String serializeBST(Node rootReference) {
        if (rootReference == null)
            return "null,";
        //Process root node
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rootReference.getData());
        stringBuilder.append(",");

        //Recursive calls for left and right sub-tree
        stringBuilder.append(serializeBST(rootReference.getLeftChild()));
        stringBuilder.append(serializeBST(rootReference.getRightChild()));

        return stringBuilder.toString();
    }

    public Node deserializeBST(String serializedBST) {
        String[] array = serializedBST.split(",");
        return deserializeBSTHelper(array, new NodeDetails());
    }

    private Node deserializeBSTHelper(String[] array, NodeDetails nodeDetails) {
        if (nodeDetails.getIndex() > array.length || array[nodeDetails.getIndex()].equals("null")) {
            nodeDetails.setIndex(nodeDetails.getIndex() + 1);
            return null;
        }

        //Recreate node from the serialized string
        int nodeIndex = nodeDetails.getIndex();
        Node rootNode = new Node(Integer.parseInt(array[nodeIndex]), null, null);
        nodeDetails.setIndex(nodeIndex + 1);

        //Recreate left child node and set on root created above
        rootNode.setLeftChild(deserializeBSTHelper(array, nodeDetails));

        //Recreate right child node and set on root created above
        rootNode.setRightChild(deserializeBSTHelper(array, nodeDetails));

        return rootNode;
    }

    public void printInOrderTraversalOfBST(Node rootReference) {
        if (rootReference == null)
            return;
        printInOrderTraversalOfBST(rootReference.getLeftChild());
        System.out.print(rootReference.getData() + " ");
        printInOrderTraversalOfBST(rootReference.getRightChild());
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

    private static class NodeDetails {
        private int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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
