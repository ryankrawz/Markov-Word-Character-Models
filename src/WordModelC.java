/* file: WordModelC.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is an implementation of the Model ADT, a part of an
   implementation of C. Shannon's word-based algorithm for
   modeling English text.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.lang.Math;

@SuppressWarnings("unchecked")

public class WordModelC implements Model {

    private String input, output;
    private Map<String, List<String>> map;

    public WordModelC(String input) {
        this.input = input;
        this.output = "";
        this.map = generateMap(input);
    }

    private static Map generateMap(String input) {
        String[] wordArray = input.split(" ");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < wordArray.length - 1; i++) {
            List<String> list;
            if (!map.containsKey(wordArray[i])) { list = new ArrayList<String>(); }
            else { list = map.get(wordArray[i]); }
            if (  )
        }
    }

    public String generateOutput() {

    }

    public String toString() { return String.format("%s", output); }

}
