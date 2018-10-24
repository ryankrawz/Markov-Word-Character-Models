/* file: CharModelC.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is an implementation of the Model ADT, a part of an
   implementation of C. Shannon's n-gram algorithm for
   modeling English text.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.lang.Math;

@SuppressWarnings("unchecked")

public class CharModelC implements Model {

  private String input, output;
  private int degree;
  private Map<String, List<Character>> map;

  public CharModelC(String input, int degree) {
    this.input = input;
    this.degree = degree;
    this.output = "";
    this.map = generateMap(input, degree);
  }

  private static Map generateMap(String input, int degree) {
    Map<String, List<Character>> map = new HashMap<String, List<Character>>();
    for (int i = 0; i <= input.length(); i++) {
      String key;
      if (i == 0)                    key = "";
      else if (i < degree)           key = input.substring(0, i);
      else                           key = input.substring(i - degree, i);
      List<Character> valueList;
      if (!map.containsKey(key))     valueList = new ArrayList<Character>();
      else                           valueList = map.get(key);
      if (i == input.length())       valueList.add(Main.SENTINEL);
      else                           valueList.add(input.charAt(i));
      map.put(key, valueList);
    }
    return map;
  }

  public String generateOutput() {
    String key = output;
    while (map.containsKey(key)) {
      List<Character> valueList = map.get(key);
      int randIndex = (int) Math.floor(Math.random() * valueList.size());
      Character randChar = valueList.get(randIndex);
      if (randChar.equals(Main.SENTINEL)) break;
      output += randChar.toString();
      if (output.length() - degree < 0)
        key = output.substring(0, output.length());
      else
        key = output.substring(output.length() - degree, output.length());
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
    String inputText = "Hi my name is Andy. I like to write code. This program is written in java.";
    Model model = new CharModelC(inputText, 3);
    model.showMap();
    System.out.format("%s%n", model.generateOutput());
  }

}
