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

  public String generateOutput() { return null; }

  public String toString() { return String.format("%s", this.output); }

}
