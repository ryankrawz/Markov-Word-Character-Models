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
      list.add(input.charAt(i + degree + 1));
      map.put(substr, list);
    }
    return map;
  }

  public String generateOutput() {
    Map textMap = generateMap();
    String[] keys = (String[]) textMap.keySet().toArray();
    output = (String) textMap.get(keys[(int) Math.floor(Math.random() * keys.length)]);
    final int LIMIT = Math.round(input.length() * 1.25);

    int i = degree;
    while (textMap.containsKey(output.substring(i - degree, i))) {
      List<Character> charList = (List<Character>) textMap.get(output.substring(i - degree, i));
      output += charList.get((int) Math.floor(Math.random() * charList.size())).toString();
      if (i == LIMIT) { break; }
      i++;
    }
    return output;
  }

  public String toString() { return String.format("%s", output); }

}
