import java.util.*;

/**
 * Created by qinche on 8/5/2016.
 */
/* one of the top 10 algorithms in 20th century */
/* shuffle to ensure randomness; move the pointers forwards even if
    the values are equal to avoid insidious quadratic time*/
public class Quick {
    public static void sort3Way(Comparable[] a) {
        int lo = 0, hi = a.length-1;
        sort3Way(a, lo, hi);
    }

    private static void sort3Way(Comparable[] a, int lo, int hi) {
        int lt = lo, ht = hi, i = lo; // lt is the upper boundary of lower keys, while ht is the lower boundary of high keys
        Comparable v = a[lo];

        while(i <= ht) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                swap(a, i++, lt++);
            }
            else if (cmp > 0) {
                swap(a, i, ht--);// only decrement ht; do not increment i; as we are not sure the original a[ht] is bigger than pivot, but the
                                // the new element a[ht] is bigger than pivot.
            }
            else {
                i++;
            }
        }
        sort3Way(a, lo, lt-1);
        sort3Way(a, ht+1 , hi);
    }
    public static void sort(Comparable[] a) {
        //Collections.shuffle(Arrays.asList(a));
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void shuffle(Comparable[] a) {
        Random ran = new Random();

        for (int i = a.length-1; i>0; i--) {
            int sub = ran.nextInt(i+1);

            swap(a, sub, i);
        }
    }

    public static void sort(Comparable[] a, int head, int tail) {
        if (head <= tail) return;

        int j = partition(a, head, tail);
        sort(a, head, j-1);
        sort(a, j+1, tail);
    }

    private static int partition(Comparable[] a, int head, int tail) {
        Comparable pivot = a[head];
        int i = head, j = tail + 1;
        while (true) {
            while (a[++i].compareTo(pivot) < 0) {
                if (i == tail) break;
            }

            while (a[--j].compareTo(pivot) > 0) {
                if (j == head) break;
            }

            if (i >= j) break;
            swap(a, i, j);
        }

        swap(a, head, j);
        return j;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /*quick select the kth smallest element in the list. The index counts from 0. */
    public static Comparable select(Comparable[] a, int k) {
        int lo = 0, hi = a.length-1;

        while(lo < hi) {
            int j = partition(a, lo, hi);
            if (k < j) {
                hi = j - 1;
            }
            else if ( k > j) {
                lo = j + 1;
            }
            else {
                return a[j];
            }
        }
        return a[k];
    }

    public static void main(String[] args) {
        int[] a = { 3,2,5,7,5,1};
        System.out.println("Before shuffling: " + Arrays.toString(a));
        List b = Arrays.asList(1, 5,-1,4,2);
        // Arrays.asList(T...): the parameter is not an array reference, but an array of object.
        System.out.println("class is " + Arrays.asList(a).getClass().getName());
        System.out.println(a.length);
        System.out.println(b.size());
        Collections.shuffle(b);
        System.out.println("After shuffling: " + Arrays.toString(b.toArray()));
        //Collections.shuffle(Arrays.asList(a));
        //System.out.println("After shuffling: " + Arrays.toString(a));
    }
}
