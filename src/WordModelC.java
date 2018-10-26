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
    private Words arr;

    private class Words {
        private String[] splitInp;
        Words(String input) {
            this.splitInp = input.split(" ");
        }
    }

    public WordModelC(String input, int degree) {
        this.input = input;
        this.degree = degree;
        this.output = "";
        this.arr = new Words(input);
        this.map = generateMap(input, degree);
    }

    private Map generateMap(String input, int degree) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < arr.splitInp.length - degree; ++i) {
            StringBuilder key = new StringBuilder(arr.splitInp[i]);
            for (int j = i + 1; j < i + degree; ++j) {
                key.append(" ").append(arr.splitInp[j]);
            }

            String value = (i + degree < arr.splitInp.length) ? arr.splitInp[i + degree] : "";
            List<String> valueList;
            if (!map.containsKey(key.toString())) {
                valueList = new ArrayList<String>();
            } else {
                valueList = map.get(key.toString());
            }
            valueList.add(value);
            if (i == arr.splitInp.length - degree)      valueList.add(Main.SENTINEL.toString());
            map.put(key.toString(), valueList);
        }
        return map;
    }

    public String generateOutput() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < degree; i++) {
            if (i == 0)         key.append(arr.splitInp[i]);
            else                key.append(" ").append(arr.splitInp[i]);
        }
        output += key.toString();

        while (map.containsKey(key.toString())) {
            final int LIMIT = (int) Math.round(arr.splitInp.length * 1.5);
            List<String> valueList = map.get(key.toString());
            int randIndex = (int) Math.floor(Math.random() * valueList.size());
            String randWord = valueList.get(randIndex);
            if (randWord.equals(Main.SENTINEL.toString()))      break;
            output += String.format(" %s", randWord);

            key = new StringBuilder();
            String temp = output;
            String[] tempWords = temp.split(" ");
            for (int j = tempWords.length - degree; j < tempWords.length; j++) {
                if (j == tempWords.length - degree)     key.append(tempWords[j]);
                else                                    key.append(" ").append(tempWords[j]);
            }
        }
        return output;
    }

    public String toString() { return String.format("%s", output); }

    public void showMap() {
        System.out.format("%n");
        Object[] keyArray = map.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            System.out.format("%s: %s%n", keyArray[i], map.get(keyArray[i]));
        }
    }

    public static void main(String[] args) {
        String inputText = "THIS IS REALLY AMAZING. I WANT TO THANK CONGRESSMAN ALAN WEST. HE IS AN AMAZING GUY. I HAVE BEEN A SUPPORTER OF HIS. HE IS TOUGH, SMART, AND A REAL PATRIOT. ALSO, RICK SCOTT. HE IS DOING A GOOD JOB. IT IS NOT EASY. HE IS DOING A HELLUVA JOB. MY SECOND HOME IS RIGHT DOWN THE ROAD IN YOUR COMPETITIVE COMMUNITY KNOWN AS PALM BEACH. I LOVE FLORIDA. I WOULD LIKE TO THANK THE SOUTH FLORIDA TEA PARTY FOR THE OPPORTUNITY TO ADDRESS THIS GROUP OF HARD-WORKING, INCREDIBLE PEOPLE. IT IS MY GREAT HONOR, BELIEVE ME. [APPLAUSE] OVER THE LAST SIX MONTHS SINCE I STARTED THINKING ABOUT THIS, I HAVE BEEN ASKED SO MUCH ABOUT THE TEA PARTY BY REPORTERS AND A LOT OF DIFFERENT FOLKS. I HAVE COME UP WITH A TRUTHFUL BUT STANDARD ANSWER. THEY ARE GREAT BECAUSE THEY MADE WASHINGTON START THINKING, BOTH DEMOCRATS AND REPUBLICANS. THEY MADE WASHINGTON START THINKING. I WANT TO THANK YOU ALL. IT IS FANTASTIC. WHEN I WAS ASKED TO DO THIS SPEECH TODAY BY A FRIEND OF MINE, HE SAID IT WOULD BE IN AN AUDITORIUM WITH 250 PEOPLE. WHAT HAPPENED? [CHEERS AND APPLAUSE] WITH ALL OF THE WIND, AT LEAST YOU KNOW IT IS MY REAL HAIR. THE UNITED STATES HAS BECOME THE LAUGHING STOCK AND A WHIPPING POST FOR THE REST OF THE WORLD WHETHER WE LIKE IT OR NOT. WE DO NOT LIKE IT.";
        Model model = new WordModelC(inputText, 3);
        model.showMap();
        System.out.format("%n%s%n%n", model.generateOutput());
    }

}
