import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
Time Complexity: O(NlogN+ K^2) N is the length of the given string and K is the length of Options array list
Space Complexity: O(K^2) K is the size of options array list
Run on Leetcode: Yes
Any difficulties: No

Approach:
1. Attempted once discussed in the class, **well commented code**
 */
public class BraceExpansion {
        public static String[] expandString(String s){

            List<List<Character>> allOptions = new ArrayList<>();
            List<Character> list = null;

            /*
            Dividing the string into the nested list and forming all the options
            after iterating over the string
             */
            for(char c: s.toCharArray()){
                if(c == '{'){
                    list = new ArrayList<>();
                    allOptions.add(list);
                }else if(c == '}'){
                    Collections.sort(list);
                    list = null;
                }else if(c == ','){
                    continue;
                }else{
                    if(list == null){
                        List<Character> option = new ArrayList<>();
                        option.add(c);
                        allOptions.add(option);
                    }else{
                        list.add(c);
                    }
                }
            }

            System.out.println(allOptions);
            /*
            Options would look like,-> [[a, b], [c], [d, e], [f]]
             */

            List<String> result = new ArrayList<>();

            /*
            Recursively making all the possible result using the options I have
             */
            recursionToExpand("", 0, result, allOptions);

            String[] res = new String[result.size()];

            for(int i = 0; i<res.length; i++){
                res[i] = result.get(i);
            }

            return res;
        }

        public static void recursionToExpand(String expand, int index, List<String> result, List<List<Character>> options){
            if(index == options.size()){
                result.add(expand);
                return;
            }

            for(int i = 0; i<options.get(index).size(); i++){
                recursionToExpand(expand+options.get(index).get(i), index+1, result, options);
            }
        }

        public static void main(String[] args){
            System.out.println("Brace Expansion: "+ Arrays.toString(expandString("{a,b}c{d,e}f")));
        }
}
