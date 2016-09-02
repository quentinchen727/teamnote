import java.util.*;

/**
 * Created by qinche on 8/3/2016.
 */
/*
    Time complexity refers to character access
   R-way Tries
    space complexity: (R+1)N
    time complexity: search hit: L; search miss: logrN; insert: L
   Tenary Search Tries:
    space complexity: 4N;
    time complexity: search hit: L + lnN; search miss: lnN; insert: L + lnN

    Hash (linear probing):
    space complexity: 4N to 16 N;
    time complexity: search hit: L; search miss: L, insert: L;

    Red Black Tree:
    space complexity: 4N;
    time complexity: search hit: L + clg2N; search miss: clg2N; insert: clg2N
 */
class Tries {
    protected static final int R = 256;
    protected TriesNode root;

    protected static class TriesNode {
        protected Object value;
        protected TriesNode[] next = new TriesNode[R];
    }

    public void put(String key, Object value) {
        put(root, key, value, 0);
    }

    private TriesNode put(TriesNode x, String key, Object value, int pos) {
        if(x == null) {
            x = new TriesNode();
        }

        if(pos == key.length()) {
            x.value = value;
            return x;
        }

        char c = key.charAt(pos);
        x.next[c] = put(x.next[c], key, value, pos + 1);
        return x;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Object get(String key) {
        TriesNode result = get(root, key, 0);

        if(result == null) {
            return null;
        }

        return result.value;
    }

    protected TriesNode get(TriesNode x, String key, int d) {
        if(x == null) {
            return null;
        }

        if(d == key.length()) {
            return x;
        }

        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }
}


/* TST tenary search tree */
class TST {
    private Node root;

    private class Node {
        char c;
        Object value;
        Node left, right, middle;
    }

    public void put(String key, Object value) {
        put(root, key, value, 0);
    }

    private Node put(Node x, String key, Object value, int d) {
        if (x == null) {
            x = new Node();
        }

        char c = key.charAt(d);
        if (c < x.c) {
            x.left = put(x.left, key, value, d);
        }
        else if (c > x.c) {
            x.right = put(x.right, key, value, d);
        }
        else if (d < key.length() - 1) {
            x.middle = put(x.middle, key, value, d+1);
        }
        else {
            x.value = value;
        }
        return x;
    }

    public boolean contains(String key) {
        return get(key) == null;
    }

    public Object get(String key) {
        Node res = get(root, key, 0);
        if (res == null) {
            return null;
        }
        return res.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        }
        else if (c > x.c) {
            return get(x.right, key, d);
        }
        else if (d < key.length() - 1) {
            return get(x.middle, key, d+1);
        }
        else {
            return x;
        }
    }
}

class ST extends Tries{

    /* return sorted keys*/
    public Iterable<String> keys() {
        Queue<String> queue = new LinkedList<String>();
        collect(root, "", queue);
        return queue;
    }

    public void collect(TriesNode x, String prefix, Queue<String> queue) {
        if (x == null) {
            return;
        }

        if (x.value != null) {
            queue.offer(prefix);
        }

        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, queue);
        }
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedList<String>();
        TriesNode x = get(root, prefix, 0);
        collect(x, prefix, queue);
        return queue;
    }

    public String longestPrefix(String prefix) {
        int length = search(root, prefix, 0, 0);
        return prefix.substring(0, length);
    }

    private int search(TriesNode x, String query, int d, int length) {
        if (x == null) {
            return length;
        }

        if (x.value != null) {
            length = d;
        }

        if (d == query.length()) {
            return length;
        }

        char c = query.charAt(d);
        return search(x.next[c], query, d+1, length);
    }

}
