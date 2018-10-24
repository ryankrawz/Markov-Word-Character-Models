/* file: WordModelC.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is an implementation of the Model ADT, a part of an
   implementation of C. Shannon's word-based algorithm for
   modeling English text.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.lang.Math;

@SuppressWarnings("unchecked")

public class WordModelC implements Model {

    private String input, output;
    private int degree;
    private Map<String, List<String>> map;

    public WordModelC(String input, int degree) {
        this.input = input;
        this.degree = degree;
        this.output = "";
        this.map = generateMap(input, degree);
    }

    private static String[] subArray(String[] arr, int start, int end) {
        return Arrays.asList(arr).subList(start, end).toArray(new String[0]);
    }

    private static Map generateMap(String input, int degree) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        String[] wordArray = input.split(" ");
        for (int i = 0; i < wordArray.length - degree; ++i) {
            StringBuilder key = new StringBuilder(wordArray[i]);
            for (int j = i + 1; j < i + degree; ++j) {
                key.append(" ").append(words[j]);
            }
            String value = (i + degree < wordArray.length) ? wordArray[i + degree] : "";
            List<String> valueList;
            if (!map.containsKey(key.toString())) {
                valueList = new List<String>();
            } else {
                valueList = map.get(key.toString());
            }
            valueList.add(value);
            map.put(key.toString(), valueList);
        }
        return map;

        /* for (int i = 0; i < wordArray.length; i++) {
            String[] key = new String[1];
            if (i == 0)                    key[0] = "";
            else if (i < degree)           key = subArray(wordArray, 0, i);
            else                           key = subArray(wordArray, i - degree, i);
            List<String> valueList;
            if (!map.containsKey(key))     valueList = new ArrayList<String>();
            else                           valueList = map.get(key);
            if (i == input.length())       valueList.add(Main.SENTINEL.toString());
            else                           valueList.add(wordArray[i]);
            map.put(key, valueList);
        }
        return map; */
    }

    public String generateOutput() {
        String[] key = new String[1];
        if (output.equals(""))             key[0] = "";
        else                               key = output.split(" ");
        while (map.containsKey(key)) {
            List<String> valueList = map.get(key);
            int randIndex = (int) Math.floor(Math.random() * valueList.size());
            String randWord = valueList.get(randIndex);
            if (randWord.equals(Main.SENTINEL.toString())) break;
            output += " " + randWord;
            String[] outputArray = output.split(" ");
            if (outputArray.length - degree < 0)
                key = subArray(outputArray, 0, outputArray.length);
            else
                key = subArray(outputArray, outputArray.length - degree, outputArray.length);
        }
        return output;
    }

    public String toString() { return String.format("%s", output); }

    public void showMap() {
        Object[] keyArray = map.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            System.out.format("%s: %s%n", keyArray[i], map.get(keyArray[i]));
        }
    }

    public static void main(String[] args) {
        String inputText = "Hello my name is Ryan and I like computer science.";
        Model model = new WordModelC(inputText, 1);
        model.showMap();
        System.out.format("%s%n", model.generateOutput());
    }

}
