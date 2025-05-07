import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//we can decide which char comes before which char by comparing the words.
public class AlienDictionary {
    int[] indegrees;
    HashMap<Character, HashSet<Character>> map;

    public String alienOrder(String[] words) {

        map = new HashMap<>();
        indegrees = new int[26];

        //first build the dependency graph
        buildGraph(words);

        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();

        for (char c : map.keySet()) {
            if (indegrees[c - 'a'] == 0) {
                sb.append(c);
                q.add(c);
            }
        }

        while (!q.isEmpty()) {
            char curr = q.poll();
            if (map.get(curr) == null) continue;

            for (char neighbor : map.get(curr)) {
                indegrees[neighbor - 'a']--;
                if (indegrees[neighbor - 'a'] == 0) {
                    q.add(neighbor);
                    sb.append(neighbor);
                }
            }
        }

        if (sb.length() < map.size()) {
            return "";
        }
        return sb.toString();
    }

    private void buildGraph(String[] words) {
        for (String word : words) {
            if(map.size()==26) break;
            for (char c : word.toCharArray()) {
                map.putIfAbsent(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int l1 = first.length();
            int l2 = second.length();

            if (l1 > l2 && first.startsWith(second)) {
                // Invalid order: prefix issue
                map.clear();
                return;
            }

            for (int j = 0; j < l1 && j < l2; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if (out != in) {
                    if (!map.get(out).contains(in)) {
                        map.get(out).add(in);
                        indegrees[in - 'a']++;
                    }
                    break;
                }
            }

        }

        System.out.println(map);
    }
}
