package com.company.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * Double linked list
 *
 * https://leetcode.com/problems/lru-cache/solution/
 *
 * One advantage of double linked list is that the node can remove itself without other reference. In addition,
 * it takes constant time to add and remove nodes from the head or tail.
 *
 * Time complexity : \mathcal{O}(1)O(1) both for put and get.
 *
 * Space complexity : \mathcal{O}(capacity)O(capacity) since the space
 * is used only for a hashmap and double linked list with at most capacity + 1 elements.
 *
 *
 *  [head] -> [a] -> [b] - [tail]     , b will be removed when size reaches the max capacity
 *
 */

public class LRUCache2 {

    private Map<Integer, DLinkedNode> cache = new HashMap<>();

    private int size;

    private int capacity;

    private DLinkedNode head, tail;

    public LRUCache2(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        // head.prev = null;

        tail = new DLinkedNode();
        // tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    private void addNode(DLinkedNode node) {
        /**
         * Always add the new node right after head.
         */
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        /**
         * Remove an existing node from the linked list.
         */
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLinkedNode node) {
        /**
         * Move certain node in between to the head.
         */
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        /**
         * Pop the current tail.
         */
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }


    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // move the accessed node to the head;
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);

            ++size;

            if (size > capacity) {
                // pop the tail
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    }

}
