/* file: ModelC.java
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

public class ModelC implements Model {

  private String input, output;
  private int degree;

  public ModelC(String input, int degree) {
    this.input = input;
    this.degree = degree;
    this.output = "";
  }

  private Map generateMap() {
    Map<String, List<Character>> map = new HashMap<String, List<Character>>();
    for (int i = 0; i + degree < input.length(); i++) {
      String substr = input.substring(i, i + degree);
      List<Character> list;
      if (!map.containsKey(substr)) list = new ArrayList<Character>();
      else list = map.get(substr);
      list.add(input.charAt(i + degree));
      map.put(substr, list);
    }
    return map;
  }

  public String generateOutput() {
    Map<String, List<Character>> textMap = generateMap();
    String[] keys = (String[]) textMap.keySet().toArray();
    int randKeyIndex = (int) Math.floor(Math.random() * keys.length);
    output = keys[randKeyIndex];
    final int LIMIT = (int) Math.round(input.length() * 1.25);
    while (textMap.containsKey(substr) && output.length() <= LIMIT) {
      String substr = output.substring(output.length() - degree, output.length());
      List<Character> charList = textMap.get(substr);
      int randIndex = (int) Math.floor(Math.random() * charList.size());
      output += charList.get(randIndex).toString();
    }
    return output;
  }

  public String toString() { return String.format("%s", output); }

}
