package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 31-10-2022 02:56 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-a-triplet-from-three-linked-lists-with-sum-equal-to-a-given-number/
 */
public class FindTripletWithGivenSumFrom3Lists {

    private Node head;

    public static void main(String[] args) {

        FindTripletWithGivenSumFrom3Lists firstList = new FindTripletWithGivenSumFrom3Lists();
        firstList.insertNodeIntoSLL(2);
        firstList.insertNodeIntoSLL(3);
        firstList.insertNodeIntoSLL(1);
        firstList.insertNodeIntoSLL(5);
        firstList.insertNodeIntoSLL(4);
        firstList.displayContents(firstList.getHead());

        System.out.println();
        FindTripletWithGivenSumFrom3Lists secondList = new FindTripletWithGivenSumFrom3Lists();
        secondList.insertNodeIntoSLL(10);
        secondList.insertNodeIntoSLL(8);
        secondList.insertNodeIntoSLL(9);
        secondList.insertNodeIntoSLL(7);
        secondList.insertNodeIntoSLL(6);
        secondList.displayContents(secondList.getHead());

        System.out.println();
        FindTripletWithGivenSumFrom3Lists thirdList = new FindTripletWithGivenSumFrom3Lists();
        thirdList.insertNodeIntoSLL(12);
        thirdList.insertNodeIntoSLL(13);
        thirdList.insertNodeIntoSLL(11);
        thirdList.insertNodeIntoSLL(15);
        thirdList.insertNodeIntoSLL(14);
        thirdList.displayContents(thirdList.getHead());


        System.out.println();
        System.out.println();
        FindTripletWithGivenSumFrom3Lists tripletFinder = new FindTripletWithGivenSumFrom3Lists();
        int givenSum = 30;
        tripletFinder.findTripletEqualToGivenSum(firstList.getHead(), secondList.getHead(), thirdList.getHead(), givenSum);
        System.out.println("\nTime complexity is O(N^2)");

    }

    private boolean findTripletEqualToGivenSum(Node firstListHead, Node secondListHead, Node thirdListHead, int givenSum) {

        //Sort second list in ascending order
        Node secondListSortedAscHead = mergeSortList(secondListHead, true);
        displayContents(secondListSortedAscHead);


        //Sort third list in descending order
        System.out.println();
        Node thirdListSortedDescHead = mergeSortList(thirdListHead, false);
        displayContents(thirdListSortedDescHead);

        //Process first list - pick nodes one by one and find a pair with 2nd and 3rd list
        Node currentNode = firstListHead;
        boolean containsTriplet = false;
        while (currentNode != null) {
            Node secondListHeadRef = secondListSortedAscHead;
            Node thirdListHeadRef = thirdListSortedDescHead;
            while (secondListHeadRef != null && thirdListHeadRef != null) {
                if (currentNode.getData() + secondListHeadRef.getData() + thirdListHeadRef.getData() == givenSum) {
                    System.out.println("\nTriplet found with given sum " + givenSum + " is : (" +
                            currentNode.getData() + "," + secondListHeadRef.getData() + "," + thirdListHeadRef.getData() + ")");
                    containsTriplet = true;
                    secondListHeadRef = secondListHeadRef.getNext();
                    thirdListHeadRef = thirdListHeadRef.getNext();

                } else if (currentNode.getData() + secondListHeadRef.getData() + thirdListHeadRef.getData() > givenSum) {
                    thirdListHeadRef = thirdListHeadRef.getNext();
                } else {
                    secondListHeadRef = secondListHeadRef.getNext();
                }
            }
            currentNode = currentNode.getNext();
        }
        if (!containsTriplet) {
            System.out.println("\nGiven singly linked lists don't contain a triplet whose sum is equal to " + givenSum);
        }
        return false;
    }

    private Node mergeSortList(Node headReference, boolean isAsc) {
        if (headReference == null || headReference.getNext() == null)
            return headReference;
        else {
            Node middleNode = getMiddleNode(headReference);
            Node middleNodeRef = middleNode.getNext();
            middleNode.setNext(null);
            Node firstPartHead = mergeSortList(headReference, isAsc);
            Node secondPartHead = mergeSortList(middleNodeRef, isAsc);
            Node mergedListHead = merge(firstPartHead, secondPartHead, isAsc);
            return mergedListHead;
        }
    }

    private Node merge(Node firstListHead, Node secondListHead, boolean isAsc) {
        Node resultNode = null;
        if (firstListHead == null)
            return secondListHead;
        if (secondListHead == null)
            return firstListHead;
        if (isAsc) {
            if (firstListHead.getData() <= secondListHead.getData()) {
                resultNode = firstListHead;
                resultNode.setNext(merge(firstListHead.getNext(), secondListHead, isAsc));
            } else {
                resultNode = secondListHead;
                resultNode.setNext(merge(firstListHead, secondListHead.getNext(), isAsc));
            }
        } else {
            if (firstListHead.getData() >= secondListHead.getData()) {
                resultNode = firstListHead;
                resultNode.setNext(merge(firstListHead.getNext(), secondListHead, isAsc));
            } else {
                resultNode = secondListHead;
                resultNode.setNext(merge(firstListHead, secondListHead.getNext(), isAsc));
            }
        }
        return resultNode;
    }

    private Node getMiddleNode(Node headRerference) {
        if (headRerference == null)
            return null;
        else {
            Node slowPointer = headRerference;
            Node fastPointer = headRerference.getNext();
            while (fastPointer != null && fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
            }
            return slowPointer;
        }
    }

    private void insertNodeIntoSLL(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversalNode = head;
            while (traversalNode.getNext() != null) {
                traversalNode = traversalNode.getNext();
            }
            traversalNode.setNext(newNode);
        }
    }

    private void displayContents(Node headReference) {
        if (headReference == null) {
            System.out.println("SLL is empty");
        } else {
            Node currentNode = headReference;
            while (currentNode != null) {
                System.out.print(currentNode.getData() + " ");
                currentNode = currentNode.getNext();
            }
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    private class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
