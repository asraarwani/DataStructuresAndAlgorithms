package com.some_domain.www.test;

public class BST {

    private Node root;

    public BST() {
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

        BST bst = new BST();
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(7);
        bst.insertNodeIntoBSTIteratively(10);
        bst.insertNodeIntoBSTIteratively(2);
        bst.insertNodeIntoBSTIteratively(9);
        bst.insertNodeIntoBSTIteratively(13);

        BST bstTwo = new BST();
        bstTwo.insertNodeIntoBSTIteratively(20);
        bstTwo.insertNodeIntoBSTIteratively(15);
        bstTwo.insertNodeIntoBSTIteratively(25);
        bstTwo.insertNodeIntoBSTIteratively(8);
        bstTwo.insertNodeIntoBSTIteratively(16);
        bstTwo.insertNodeIntoBSTIteratively(24);
        bstTwo.insertNodeIntoBSTIteratively(30);


    }

    /**
     * 1.  BST
     * 2.  Iterative in-order traversal of BST
     * 3.  Iterative pre-order traversal of BST
     * 4.  Iterative post-order traversal of BST
     * 5.  Height of BST
     * 6.  Check if tree is a BST
     * 7.  Vertical order traversal of BST
     * 8.  Boundary order traversal of BST
     * 9.  Boundary order traversal of BST generic
     * 10. Top view of the BST
     * 11. Level order traversal of BST
     * 12. Reverse level order traversal of BST
     * 13. Diameter of a BST
     * 14. Node with minimum and maximum data in BST
     * 15. Diagonal order traversal of BST
     * 16. Diagonal sum in  BST
     * 17. Left and Right view of the BST
     * 18. Left and Right view of the BST alternate approach
     * 19. Merge two balanced BSTs
     * 20. Lowest common ancestor of a node in BST
     * 21. Leaf node count of BST
     * 22. Print path from root to any node in BST
     * 23. Print path from root to all nodes in BST
     * 24. Check if two BSTs are Identical
     * 25. BST contains dead-end check
     * 26. Print/Check if a pair equal to given sum exists in BST
     * 27. Print/Count pairs whose sum is equal to given sum in BST
     * 28. Print/Count the number of pairs from two BSTs whose sum is equal to given sum
     * 29. Print all the k sum paths starting from root node and ending at any node
     * 30. Print all the k sum paths starting from root node and ending at the leaf node in BST
     * 31. Print all the k sum paths starting  and ending at any node of the BST
     * 32. Print odd and even nodes of the BST
     * 33. Find maximum width of BST
     * 34. Find maximum sum level of BST
     * 35. Find horizontal sum of all levels of BST
     * 36. Print all full nodes of BST
     * 37. Remove all leaf nodes from BST
     * 38. Print common nodes in two BSTs
     * 39. Print all internal nodes of BST
     * 40. Print leaf nodes from right to left in BST
     * 41. Print leaf nodes from left to right in BST
     * 42. Print sum and product of all non-leaf nodes in BST
     * 43. Print all the nodes between two given levels in BST
     * 44. Print nodes of BST in min max fashion
     * 45. Flatten BST into singly linked list without using any auxiliary data structure
     * 46. Flatten BST into singly linked list using stack iterative
     * 47. Flatten BST into singly linked list using stack recursive
     * 48. Flatten BST into singly linked list using queue
     * 49. Convert a BST into doubly linked list
     * 50. Check if leaf order traversal of two BSTs is same or not
     * 51. Convert a given binary tree to binary search tree
     * 52. Convert BST to balanced BST
     * 53. Second largest node in BST
     * 54. Construct a BST from its given level order traversal
     * 55. Convert BST to sorted circular doubly linked list
     * 56. Largest number greater than or equal to a given number
     * 57. Largest number less than or equal to a given number
     * 58. In-order successor of node in BST using parent node link
     * 59. In-order successor of node in BST without parent link
     * 60. In-order successor and predecessor of node in BST
     * 61. Print BST in zig-zag level order traversal (Spiral)
     * 62. Convert sorted array to balanced BST
     * 63. Check if a BST is skewed BST
     * 64. Moris in-order traversal of BST
     * 65. Moris pre-order traversal of BST
     * 66. Check if a BST is a balanced BST
     */

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processingNodeParent = null;
            Node traversingNode = root;
            while (true) {
                processingNodeParent = traversingNode;
                if (data <= traversingNode.getData()) {
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

    private class Node {

        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int id, Node leftChild, Node rightChild) {
            this.data = id;
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

        @Override
        public String toString() {
            return "" + data;
        }
    }
}
