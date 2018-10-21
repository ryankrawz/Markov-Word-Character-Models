/* file: Shannon.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is an implementation of C. Shannon's n-gram algorithm
   for English text.
*/
import javafx.scene.Node;

public class Shannon {

  private Node input, output;
  private Integer degree;

  public Shannon(Node input, Integer degree, Node output) {
    this.input = input;
    this.output = output;
    this.degree = degree;
  }

  private String getInputText() {
    return Main.getText(this.input);
  }

  private void setOutputText(String text) {
    Main.setText(this.output, text);
  }

  public void process() { // YOUR CODE HERE
    String inputText = getInputText();

    Model myModel = ModelC(

    // Dummy code that simply sets the output
    // to what was read from the input. Replace
    // the next line with your code.
    //
    setOutputText();
  }
}
