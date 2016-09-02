/**
 * Created by qinche on 8/4/2016.
 */
public class RBT {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node {
        private Comparable key;
        private Object value;
        private Node left, right;
        private boolean color;

        Node(Comparable key, Object value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private boolean isRed(Node n) {
        if (n == null) return false;
        return n.color == RED;
    }

    private Node leftRotate(Node x) {
        assert isRed(x.right);
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rightRotate(Node x) {
        assert isRed(x.left);

        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private void flipColor(Node x) {
        assert !isRed(x);
        assert isRed(x.left);
        assert isRed(x.right);

        x.left.color = BLACK;
        x.right.color = BLACK;
        x.color = RED;
    }

    public Node insert(Node x, Comparable key, Object value) {
        if (x == null) return new Node(key, value, RED);

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = insert(x.left, key, value);
        }
        else if (cmp > 0) {
            x.right = insert(x.right, key, value);
        } else {
            x.value = value;
        }

        if (isRed(x.right) && !isRed(x.left)) x = leftRotate(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rightRotate(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);
        return x;
    }

}
