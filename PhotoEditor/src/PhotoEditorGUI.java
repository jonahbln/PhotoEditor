import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.IController;
import controller.ImageProcessingController;
import model.IModel;
import model.ImageProcessingModel;
import view.IView;
import view.ImageProcessingGuiView;
import view.ImageProcessingView;

/**
 * main class provided to the user to run an instance of the photo processor program.
 * calls to this class should be made ot the main method using command line inputs as described
 * in the provided list of commands.
 */
public final class PhotoEditorGUI {
  /**
   * main method that accepts command-line inputs from the user
   * and translates them into photo manipulation.
   * A list of all supported inputs is provided in view class, or made available by entering
   * 'menu' as an argument at any time.
   * The program will accept any number of inputs and will continue to parse user inputs until
   * 'quit' is entered at which point the program will quit and all unsaved changes will be lost.
   *
   * @param args input parameters given by user.
   */
  public static void main(String[] args) {
    StringBuilder s = new StringBuilder();

    if (args.length == 0) {
      ImageProcessingGuiView.setDefaultLookAndFeelDecorated(false);
      ImageProcessingGuiView frame = new ImageProcessingGuiView();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
               IllegalAccessException e) {
        System.out.println("exception thrown!");
      }
    } else if (args[0].equalsIgnoreCase("-file")) {
      try {
        s.append(readFile(args[1]));
      } catch (IllegalArgumentException e) {
        System.out.println("File provided not found!");
      }
      Readable in = new StringReader(s.toString());
      Appendable out = System.out;
      IView view = new ImageProcessingView(out);
      IModel model = new ImageProcessingModel();
      IController controller = new ImageProcessingController(model, view, in);
      controller.run();
    } else if (args[0].equals("-text")) {
      Readable in = new InputStreamReader(System.in);
      Appendable out = System.out;
      IView view = new ImageProcessingView(out);
      IModel model = new ImageProcessingModel();
      IController controller = new ImageProcessingController(model, view, in);
      controller.run();
    } else {
      Appendable out = System.out;
      IView view = new ImageProcessingView(out);
      try {
        view.renderMessage(
                "You must enter one of the three following as command inputs:\n" +
                        "No input - runs the program as an interactive GUI\n" +
                        "'-text' - runs the program by taking inputs from the system console\n" +
                        "'-file  path-of-script-file.txt' " +
                        "- runs the program using commands in a given script file");
      } catch (IOException e) {
        throw new RuntimeException("I/O exception occurred!");
      }
    }
  }

  // takes in a .txt filename and reads it, returning a string containing
  // all of the information stored in the file
  private static String readFile(String filename) throws IllegalArgumentException {
    Scanner scan;

    try {
      scan = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (scan.hasNextLine()) {
      String s = scan.nextLine();
      if (s.length() == 0 || s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    return builder.toString();
  }
}
