class Solution {
  class Node {
    int key, val;
    Node prev, next;

    Node (int k, int v) {
      key = k;
      val = v;
    }
  }

  class Bucket {
    int idx;
    Bucket prev, next;
    Map<Integer, Integer> map = new HashMap<>();
    Bucket head, tail;

    Bucket(int idx) {
      this.idx = idx;
      head = new Bucket(-1);
      tail = new Bucket(-1);
      head.next = tail;
      tail.prev = head;
    }

    void put(int key, int val) {
      if (map.containsKey(key)) {
        Node node = map.get(key);
        node.val = val;

        delete(node);
        addToHead(node);
      } else {
        Node node = new Node(key, val);
        map.put(key, node);
        addToHead(node);
      }
    }

    Node remove(int key) {
      if (map.containsKey(key)) {
        Node delNode = map.get(key);
        delete(delNode);
        map.remove(delNode.key);
        return delNode;
      }
      return null;
    }

    Node clear() {
      Node delNode = tail.prev;
      delete(delNode);
      map.remove(delNode.key);
      return delNode;
    }

    boolean isEmpty() {
      return map.size() == 0;
    }
  }

  Bucket head = new Bucket(-1);
  Bucket tail = new Bucket(-1);
  Map<Integer, Bucket> map = new HashMap<>();
  int count = 0;
  int capacity;

  public void LFUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Bucket cur = map.get(key);
      Bucket target = null;
      if (cur.next.idx != cur.idx + 1) {
        target = new Bucket(cur.idx + 1);
        // insert(cur, target);
        target.next = cur.next;
        cur.next.prev = target;
        cur.next = target;
        target.prev = cur;
      } else {
        target = cur.next;
      }

      // get node
      Node node = cur.remove(key);
      target.put(node.key, node.val);
      map.out(key, target);
      deleteIfEmpty(cur);

      return node.val;
    } 

    return - 1;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Bucket cur = map.get(key);
      cur.put(key, value);
      get(key);
    } else {
      if (count == capacity) {
        Bucket cur = head.next;
        Node node = cur.clear();
        map.remove(node.key);
        count --;
        deleteIfEmpty(cur);
      }

      Bucket first = null;
      if (head.next.idx != 1) {
        first = new Bucket(1);
        // insert(first)
        first.next = head.next;
        head.next.prev = first;
        first.prev = head;
        head.next = first;
      } else {
        first = head.next;
      }

      first.put(key, value);
      map.put(key, first);
      count ++;
    }
  }

  void deleteIfEmpty(Bucket bucket) {
    if (bucket.isEmpty()) {
      bucket.prev.next = bucket.next;
      bucket.next.prev = bucket.prev;
      bucket = null;
    }
  }
}