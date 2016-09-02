import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by qinche on 8/8/2016.
 */
public class Merge {
    public static void sort(Comparable[] a) {
        //Comparable[] aux = new Comparable[a.length];
        Comparable[] aux = Arrays.copyOf(a, a.length);

        sort(a, aux, 0, a.length - 1);
    }

    private static void merge(Comparable[]a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        /* eliminate the copy to the auxiliary array. save time but not space.*/
        /*
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        */

        /*
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j])<0) a[k] = aux[i++];
            else a[k]  = aux[j++];
        }
        */
        System.out.println("lo is " + lo + ", hi is " + hi);

        for (int k = lo; k <= hi; k++) {
            System.out.println("i is " + i + "; a[i] is" + a[i]);

            if (i > mid) aux[k] = a[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (a[i].compareTo(a[j])<0) aux[k] = a[i++];
            else aux[k]  = a[j++];

            System.out.println("a[k] is " + aux[k] + ", k is " + k);
        }
        System.out.println("end----one merge");
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        /*
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid,  hi);
        */

        sort(aux, a, lo, mid);
        sort(aux, a, mid+1, hi);
        //if (aux[mid].compareTo(aux[mid+1])<=0) return;
        merge(aux, a, lo, mid,  hi);
    }

    public static void sortIterative(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];

        for (int size = 1; size < n; size *= size) {
            for (int lo = 0; lo < n - size; lo += 2*size) {
                merge(a, aux, lo, lo+size-1, Math.min(lo+2*size-1, n-1));
            }
        }
    }

    public static void main(String[] args) {
        //Comparable[] a = {5,2,6,7,2,4};
        Comparable[] a = {5,2, 19, 12, 89, 13};
        sort(a);
        for (Comparable b : a) {
            System.out.print( (int)b + " ");
        }

    }
}
