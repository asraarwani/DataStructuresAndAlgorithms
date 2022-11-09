package com.some_domain.www.cache;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : asraar
 * @date : 09-11-2022 02:59 pm
 */
public class LRUCacheUsingDequeue implements Cache {

    private static final int maxSize = 3;

    private Map<Integer, CacheElement> cache = new ConcurrentHashMap<>();
    private Deque<CacheElement> deque = new LinkedList();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        LRUCacheUsingDequeue lruCacheUsingDequeue = new LRUCacheUsingDequeue();
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
                    lruCacheUsingDequeue.set(random.nextInt(1000), random.nextInt(1000));
                    lruCacheUsingDequeue.currentHeadAndTail();
                    break;
                case 2:
                    System.out.println("Enter the key");
                    int key = scanner.nextInt();
                    int value = lruCacheUsingDequeue.get(key);
                    System.out.println("Value for given key is " + value);
                    lruCacheUsingDequeue.currentHeadAndTail();
                    break;
                case 3:
                    System.out.println("Current size of cache is " + lruCacheUsingDequeue.size());
                    break;
                case 4:
                    lruCacheUsingDequeue.clear();
                    System.out.println("Cache cleared successfully");
                    break;
                case 5:
                    lruCacheUsingDequeue.displayContents();
                    lruCacheUsingDequeue.currentHeadAndTail();
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
                CacheElement cacheElement = deque.removeLast();
                deque.offerFirst(cacheElement);
                cache.put(key, cacheElement);
            } else {
                if (cache.size() == maxSize) {
                    CacheElement removedNode = deque.removeLast();
                    System.out.println("Removed node " + removedNode.toString());
                    cache.remove(removedNode.getKey());
                    CacheElement cacheElement = new CacheElement(key, value);
                    deque.offerFirst(cacheElement);
                    cache.put(key, cacheElement);
                } else {
                    CacheElement cacheElement = new CacheElement(key, value);
                    deque.offerFirst(cacheElement);
                    cache.put(key, cacheElement);
                    System.out.println("New entry inserted " + cacheElement);
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
            CacheElement cacheElement = cache.get(key);
            if (cacheElement != null) {
                boolean removed = deque.remove(cacheElement);
                if (removed) {
                    deque.offerFirst(cacheElement);
                    cache.put(key, cacheElement);
                }
                return cacheElement.getValue();
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
        this.deque = new LinkedList<>();
    }

    public void displayContents() {
        cache.entrySet().stream().forEach(entry -> {
            System.out.println("Key " + entry.getKey() + " Value : " + entry.getValue());
        });
    }

    private void currentHeadAndTail() {
        if (deque.peekFirst() != null && deque.peekLast() != null)
            System.out.println("Head " + deque.peekFirst().getKey() + " Tail : " + deque.peekLast().getKey());
        else {
            System.out.println("Cache is empty");
        }
    }
}
