package com.some_domain.www.cache;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : asraar
 * @date : 09-11-2022 02:31 pm
 *
 * Reference : https://www.baeldung.com/java-lru-cache
 */
public class LRUCache implements Cache {

    private static final int maxSize = 3;

    private Map<Integer, LinkedListNode> cache = new ConcurrentHashMap<>();
    private DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache();
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        do {
            System.out.println("Enter 1 for inserting item into cache");
            System.out.println("Enter 2 for getting item from cache");
            System.out.println("Enter 3 for getting the current size of the cache");
            System.out.println("Enter 4 for clearing the cache");
            System.out.println("Enter 5 for displaying the contents of cache");
            System.out.println("Enter 0 to quit");
            System.out.println("Enter your choice now");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Random random = new Random();
                    lruCache.set(random.nextInt(1000), random.nextInt(1000));
                    lruCache.currentHeadAndTail();
                    break;
                case 2:
                    System.out.println("Enter the key");
                    int key = scanner.nextInt();
                    int value = lruCache.get(key);
                    System.out.println("Value for given key is " + value);
                    lruCache.currentHeadAndTail();
                    break;
                case 3:
                    System.out.println("Current size of cache is " + lruCache.size());
                    break;
                case 4:
                    lruCache.clear();
                    System.out.println("Cache cleared successfully");
                    break;
                case 5:
                    lruCache.displayContents();
                    lruCache.currentHeadAndTail();
                    break;
                case 0:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option has been selected");
            }
        } while (keepRunning);
    }

    @Override
    public boolean set(int key, int value) {
        try {
            lock.writeLock().lock();
            if (cache.containsKey(key)) {
                LinkedListNode removedNode = doublyLinkedList.removeAndMoveToFront(cache.get(key));
                doublyLinkedList.insertNodeAtFront(removedNode);
                cache.put(key, removedNode);
            } else {
                if (cache.size() == maxSize) {
                    LinkedListNode removedNode = doublyLinkedList.removeNodeFromRear();
                    System.out.println("Removed node " + removedNode.toString());
                    cache.remove(removedNode.getKey());
                    LinkedListNode newNode = new LinkedListNode(key, value);
                    doublyLinkedList.insertNodeAtFront(newNode);
                    cache.put(key, newNode);
                } else {
                    LinkedListNode newNode = new LinkedListNode(key, value);
                    doublyLinkedList.insertNodeAtFront(newNode);
                    cache.put(key, newNode);
                    System.out.println("New entry inserted " + newNode.toString());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
        return true;
    }

    @Override
    public Integer get(int key) {
        try {
            lock.readLock().lock();
            LinkedListNode linkedListNode = cache.get(key);
            if (linkedListNode != null) {
                LinkedListNode accessedNode = doublyLinkedList.removeAndMoveToFront(linkedListNode);
                doublyLinkedList.insertNodeAtFront(accessedNode);
                cache.put(key, accessedNode);
                return accessedNode.getValue();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return -1;
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.size() == 0 ? true : false;
    }

    @Override
    public void clear() {
        this.cache = new ConcurrentHashMap<>();
        this.doublyLinkedList = new DoublyLinkedList();
    }


    public void displayContents() {
        cache.entrySet().stream().forEach(entry -> {
            System.out.println("Key " + entry.getKey() + " Value : " + entry.getValue());
        });
    }

    private void currentHeadAndTail() {
        if (doublyLinkedList.getHead() != null && doublyLinkedList.getTail() != null)
            System.out.println("Head " + doublyLinkedList.getHead().getKey() + " Tail : " + doublyLinkedList.getTail().getKey());
        else {
            System.out.println("Cache is empty");
        }
    }
}
