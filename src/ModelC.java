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
    Map<String, List<Character>> textMap = new HashMap<String, List<Character>>();
    return textMap;
  }

  public String generateOutput() {
    Map textMap = generateMap();
    String[] keys = (String[]) textMap.keySet().toArray();
    output = (String) textMap.get(keys[(int) Math.floor(Math.random() * keys.length)]);
    int i = degree;
    while (textMap.containsKey(output.substring(i - degree, i))) {
      List<Character> charList = (List<Character>) textMap.get(output.substring(i - degree, i));
      output += charList.get((int) Math.floor(Math.random() * charList.size())).toString();
      i++;
    }
    return output;
  }

  public String toString() { return String.format("%s", output); }

}
