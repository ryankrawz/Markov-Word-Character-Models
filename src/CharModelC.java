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
    for (int i = 0; i + degree <= input.length(); i++) {
      String substr = input.substring(i, i + degree);
      List<Character> list;
      if (!map.containsKey(substr)) list = new ArrayList<Character>();
      else list = map.get(substr);
      if (i + degree == input.length()) list.add(Main.SENTINEL); // if we've hit the end of the input, append Main.SENTINEL to the key's value list instead.
      else list.add(input.charAt(i + degree));
      map.put(substr, list);
    }
    return map;
  }

  public String generateOutput() {
    // String[] keys = textMap.keySet().toArray(new String[textMap.size()]);
    // int randKeyIndex = (int) Math.floor(Math.random() * keys.length);
    // output = keys[randKeyIndex];
    output = input.substring(0, degree);
    String substr = output;
    final int LIMIT = (int) Math.round(input.length() * 1.25);
    while (map.containsKey(substr) /* && output.length() <= LIMIT */) {
      List<Character> charList = map.get(substr);
      int randIndex = (int) Math.floor(Math.random() * charList.size());
      if (charList.get(randIndex) == Main.SENTINEL) break;
      output += charList.get(randIndex).toString();
      substr = output.substring(output.length() - degree, output.length());
    }
    return output;
  }

  public String toString() { return String.format("%s", output); }

  public static void main(String[] args) {
    String inputText = "THIS IS REALLY AMAZING. I WANT TO THANK CONGRESSMAN ALAN WEST. HE IS AN AMAZING GUY. I HAVE BEEN A SUPPORTER OF HIS. HE IS TOUGH, SMART, AND A REAL PATRIOT. ALSO, RICK SCOTT. HE IS DOING A GOOD JOB. IT IS NOT EASY. HE IS DOING A HELLUVA JOB. MY SECOND HOME IS RIGHT DOWN THE ROAD IN YOUR COMPETITIVE COMMUNITY KNOWN AS PALM BEACH. I LOVE FLORIDA. I WOULD LIKE TO THANK THE SOUTH FLORIDA TEA PARTY FOR THE OPPORTUNITY TO ADDRESS THIS GROUP OF HARD-WORKING, INCREDIBLE PEOPLE. IT IS MY GREAT HONOR, BELIEVE ME. [APPLAUSE] OVER THE LAST SIX MONTHS SINCE I STARTED THINKING ABOUT THIS, I HAVE BEEN ASKED SO MUCH ABOUT THE TEA PARTY BY REPORTERS AND A LOT OF DIFFERENT FOLKS. I HAVE COME UP WITH A TRUTHFUL BUT STANDARD ANSWER. THEY ARE GREAT BECAUSE THEY MADE WASHINGTON START THINKING, BOTH DEMOCRATS AND REPUBLICANS.  THEY MADE WASHINGTON START THINKING. I WANT TO THANK YOU ALL. IT IS FANTASTIC. WHEN I WAS ASKED TO DO THIS SPEECH TODAY BY A FRIEND OF MINE, HE SAID IT WOULD BE IN AN AUDITORIUM WITH 250 PEOPLE. WHAT HAPPENED? [CHEERS AND APPLAUSE] WITH ALL OF THE WIND, AT LEAST YOU KNOW IT IS MY REAL HAIR. THE UNITED STATES HAS BECOME THE LAUGHING STOCK AND A WHIPPING POST FOR THE REST OF THE WORLD WHETHER WE LIKE IT OR NOT. WE DO NOT LIKE IT.";

    Model model = new CharModelC(inputText, 4);
    System.out.format("%s%n", model.generateOutput());
  }
}
