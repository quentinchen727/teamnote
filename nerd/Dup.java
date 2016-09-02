import java.util.*;

/**
 * Created by qinche on 8/11/2016.
 */
public class Dup {
    public static void main(String[] args) {
        Set<Integer> rep = new HashSet<Integer>();
        rep.add(1);
        rep.add(1);
        rep.add(20000);
        rep.add(20000);
        System.out.println(rep);
        Integer a = new Integer(10);
        Integer b = new Integer(10);
        System.out.println(a==b);

        LinkedList<Integer> order = new LinkedList<>();
        order.push(1);
        order.push(2);
        order.push(3);
        order.push(4);
        order.push(5);

        /* Linked List can generate stack-wise iterable, in LIFO manner*/
        for (int i : order) {
            System.out.println(i);
        }

        List<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);

        /* Array List's iterable is in FIFO manner */
        for (int i : array) {
            System.out.println(i);
        }
    }
}
