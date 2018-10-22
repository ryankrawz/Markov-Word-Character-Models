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
    Map<String, List<Character>> outputMap = new HashMap<String, List<Character>>();
  }

  public String generateOutput() {
    Map outputMap = generateMap();
    
  }

  public String toString() { return String.format("%s", this.output); }

}
