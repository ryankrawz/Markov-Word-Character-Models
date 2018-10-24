/* file: WordModelC.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is an implementation of the Model ADT, a part of an
   implementation of C. Shannon's word-based algorithm for
   modeling English text. This model is built using words
   instead of individual characters.
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
            String key = wordArray[i];
            List<String> valueList;
            if (!map.containsKey(key))              valueList = new ArrayList<String>();
            else                                    valueList = map.get(key);
            valueList.add(wordArray[i + 1]);
            if (i == wordArray.length - 2)          valueList.add(Main.SENTINEL.toString());
            map.put(key, valueList);
        }
        return map;
    }

    public String generateOutput() {
        String key = input.split(" ")[0];
        output += key;
        int i = -1;
        while (map.containsKey(key)) {
            List<String> valueList = map.get(key);
            int randIndex = (int) Math.floor(Math.random() * valueList.size());
            String randWord = valueList.get(randIndex);
            if (randWord.equals(Main.SENTINEL.toString())) break;
            output += " " + randWord;
            i++;
            key = output.split(" ")[i];
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
        String inputText = "THIS IS REALLY AMAZING. I WANT TO THANK CONGRESSMAN ALAN WEST. HE IS AN AMAZING GUY. I HAVE BEEN A SUPPORTER OF HIS. HE IS TOUGH, SMART, AND A REAL PATRIOT. ALSO, RICK SCOTT. HE IS DOING A GOOD JOB. IT IS NOT EASY. HE IS DOING A HELLUVA JOB. MY SECOND HOME IS RIGHT DOWN THE ROAD IN YOUR COMPETITIVE COMMUNITY KNOWN AS PALM BEACH. I LOVE FLORIDA. I WOULD LIKE TO THANK THE SOUTH FLORIDA TEA PARTY FOR THE OPPORTUNITY TO ADDRESS THIS GROUP OF HARD-WORKING, INCREDIBLE PEOPLE. IT IS MY GREAT HONOR, BELIEVE ME. [APPLAUSE] OVER THE LAST SIX MONTHS SINCE I STARTED THINKING ABOUT THIS, I HAVE BEEN ASKED SO MUCH ABOUT THE TEA PARTY BY REPORTERS AND A LOT OF DIFFERENT FOLKS. I HAVE COME UP WITH A TRUTHFUL BUT STANDARD ANSWER. THEY ARE GREAT BECAUSE THEY MADE WASHINGTON START THINKING, BOTH DEMOCRATS AND REPUBLICANS.  THEY MADE WASHINGTON START THINKING. I WANT TO THANK YOU ALL. IT IS FANTASTIC. WHEN I WAS ASKED TO DO THIS SPEECH TODAY BY A FRIEND OF MINE, HE SAID IT WOULD BE IN AN AUDITORIUM WITH 250 PEOPLE. WHAT HAPPENED? [CHEERS AND APPLAUSE] WITH ALL OF THE WIND, AT LEAST YOU KNOW IT IS MY REAL HAIR. THE UNITED STATES HAS BECOME THE LAUGHING STOCK AND A WHIPPING POST FOR THE REST OF THE WORLD WHETHER WE LIKE IT OR NOT. WE DO NOT LIKE IT.";
        Model model = new WordModelC(inputText);
        model.showMap();
        System.out.format("%s%n", model.generateOutput());
    }

}
