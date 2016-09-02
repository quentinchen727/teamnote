import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by qinche on 8/16/2016.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        /* BFS */
        Set<String> start = new HashSet<>();
        Set<String> unvisited = new HashSet<>(wordList);
        Set<String> latest = new HashSet<>();
        Set<String> tmp;

        unvisited.add(endWord);
        start.add(beginWord);

        int level = 1;
        while(!start.isEmpty()) {
            System.out.print(start);
                if (start.contains(endWord)) {
                    System.out.println("ladder level is " + level);
                    return level;
                }
            for (String word : start) {
                for (int i = 0; i < word.length(); i++) {
                    char [] chars = word.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        chars[i] = j;
                        String adj = String.valueOf(chars);
                        System.out.println("new string is " + adj);
                        if (unvisited.remove(adj)) {
                            latest.add(adj);
                        }
                    }
                }
            }
            start.clear();
            level++;
            tmp = start;
            start = latest;
            latest = tmp;
         }
         return 0;
    }

    public static void main(String[] args) {
        WordLadder w = new WordLadder();

        Set<String> wordList = new HashSet<>(Arrays.asList("hot", "dog", "dot"));

        w.ladderLength("hot","dog", wordList);

        String test = "012345";
        System.out.println("substring is" + test.substring(5,5) + "*");
        String a = "";
        System.out.println("answer is " + a.equals(""));
        String b = "aaa".substring(3,3);
        if(test.substring(5,5).isEmpty() && a == b  ) {
            System.out.println("is empty string");
            System.out.println(test.substring(5,5));
        }
    }
}
