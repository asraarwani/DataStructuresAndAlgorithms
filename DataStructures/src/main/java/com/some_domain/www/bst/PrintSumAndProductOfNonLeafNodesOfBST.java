package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 12/9/2019 9:49 PM
 * This class demonstrates how to print the sum and product of non-leaf nodes of a BST
 */
//Reference : https://www.geeksforgeeks.org/print-sum-and-product-of-all-non-leaf-nodes-in-binary-tree/
public class PrintSumAndProductOfNonLeafNodesOfBST {
    private Node root;

    public PrintSumAndProductOfNonLeafNodesOfBST() {
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
                               /
                              3

           Node leaf nodes : 20 , 15 , 25 and 8
         */

        PrintSumAndProductOfNonLeafNodesOfBST bst = new PrintSumAndProductOfNonLeafNodesOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);
        bst.insertNodeIntoBSTIteratively(3);

        ProductAndSum productAndSum = new ProductAndSum();
        bst.printProductAndSumOfNonLeafNodes(bst.getRoot(), productAndSum);
        System.out.println("Product and sum of non-leaf nodes is given as follows");
        System.out.println("Product : " + productAndSum.getProduct());
        System.out.println("Sum : " + productAndSum.getSum());
        System.out.println("Time complexity is O(N)");
    }

    public void printProductAndSumOfNonLeafNodes(Node rootReference, ProductAndSum productAndSum) {
        if (rootReference != null) {

            printProductAndSumOfNonLeafNodes(rootReference.getLeftChild(), productAndSum);

            if (rootReference.getLeftChild() != null || rootReference.getRightChild() != null) {
                productAndSum.setProduct(productAndSum.getProduct() * rootReference.getData());
                productAndSum.setSum(productAndSum.getSum() + rootReference.getData());
            }

            printProductAndSumOfNonLeafNodes(rootReference.getRightChild(), productAndSum);
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node traversingNodeParent = null;
            Node traversingNode = root;
            while (true) {
                traversingNodeParent = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        traversingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        traversingNodeParent.setRightChild(newNode);
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

    private static class ProductAndSum {

        private int product;
        private int sum;

        public ProductAndSum() {
            this.product = 1;
            this.sum = 0;
        }

        public int getProduct() {
            return product;
        }

        public void setProduct(int product) {
            this.product = product;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }
}
