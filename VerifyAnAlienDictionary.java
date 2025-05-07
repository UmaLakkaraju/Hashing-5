import java.util.HashMap;

//TC-O(N*L) SC-O(26) constant
public class VerifyAnAlienDictionary {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            char c = order.charAt(i);
            map.put(c, i+1);
        }

        for(int i = 0; i < words.length - 1; i++){
            if(compare(words[i], words[i+1])){
                return false;
            }

        }
        return true;
    }

    private boolean compare(String s1, String s2){
        int m = s1.length(); int n = s2.length();
        for(int i = 0; i < m && i < n; i++){
            char c1 = s1.charAt(i); char c2 = s2.charAt(i);
            if(c1 != c2){
                return map.get(c1) > map.get(c2);
            }
        }
        return m > n;
    }
}
