package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.IModel;
import view.IView;

import static java.lang.Integer.parseInt;

/**
 * Implementation of the image processing controller.
 * Contains fields that represent an image processor model, and an image processor view,
 * along with a readable object.
 * The controller is meant to run the program using user provided inputs in the readable object
 * that manipulate images contained in the model and report out information to the view.
 */
public class ImageProcessingController implements IController {
  private final IModel model;
  private final IView view;
  private final Readable in;

  /**
   * constructs a controller using user-provided model, view, and readable containing instructions.
   *
   * @param model an image processor model that may be used to load, manipulate, and save images
   * @param view  an image processor view that may be used to communicate information to the user
   * @param in    a readable object that can be parsed to take in user input
   * @throws IllegalArgumentException if any of the three parameters provided are null
   */
  public ImageProcessingController(IModel model,
                                   IView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("one or more provided parameters are null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  /**
   * constructs a controller with user-provided model and view but defaults to System.in as the
   * readable object. This is useful for testing the program within the IDE.
   *
   * @param model an image processor model that may be used to load, manipulate, and save images
   * @param view  an image processor view that may be used to communicate information to the user
   * @throws IllegalArgumentException if either of the two parameters provided are null
   */
  public ImageProcessingController(IModel model, IView view) throws IllegalArgumentException {
    this(model, view, new InputStreamReader(System.in));
  }

  /**
   * executes the program using the fields stored in this controller.
   * First, welcomes the user with brief info and instructions about the program
   * written into the view object.
   * Then, parses through user inputs, executing commands as entered and guiding the user in the
   * right direction when necessary.
   *
   * @throws IllegalStateException if the readable object runs out of
   *                               information before the program is quit
   */
  public void run() throws IllegalStateException {
    Scanner scan = new Scanner(this.in);

    render("Welcome to the image processor!\n\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n");

    if (!scan.hasNext()) {
      throw new IllegalStateException("Please enter a valid command.");
    }

    while (scan.hasNext()) {
      String action = scan.next();
      String oldName;
      if (action.equalsIgnoreCase("menu")) {
        try {
          this.view.renderListOfCommands();
        } catch (IOException e) {
          throw new RuntimeException("I/O exception occurred!");
        }
        if (scan.hasNext()) {
          action = scan.next();
        } else {
          return;
        }
      }

      if (action.equalsIgnoreCase("quit")) {
        render("program quit!");
        return;
      }

      if (!action.equalsIgnoreCase("load")) {
        oldName = getOldFile(scan, "file name");
        while (oldName.equals("")) {
          oldName = getNext(scan, "file name");
        }
      } else {
        oldName = getNext(scan, "file name");
      }

      String newName = getNext(scan, "new file name");
      while (newName.equals("")) {
        oldName = getNext(scan, "new file name");
      }

      switch (action) {
        case "load":
          this.model.loadFile(oldName, newName);
          render(newName + " successfully loaded.");
          break;
        case "brighten":
          int add = parseInt(getNext(scan, "brighten value"));
          this.model.changeBrightness(add, oldName, newName);
          render(newName + " successfully brightened by " + add + ".");
          break;
        case "darken":
          int sub = parseInt(getNext(scan, "darken value"));
          this.model.changeBrightness(-sub, oldName, newName);
          render(newName + " successfully darkened by " + sub + ".");
          break;
        case "flip":
          String direction = getNext(scan, "direction");
          this.model.flip(direction, oldName, newName);
          render(newName + " successfully flipped.");
          break;
        case "greyscale":
          String type = getNext(scan, "type");
          this.model.greyscale(type, oldName, newName);
          render(newName + " successfully greyscaled.");
          break;
        case "greyscale-color":
          String color = getNext(scan, "color");
          this.model.greyscaleColor(color, oldName, newName);
          render(newName + " successfully greyscaled by color.");
          break;
        case "filter":
          type = getNext(scan, "type");
          this.model.filter(type, oldName, newName);
          render(newName + " successfully filtered by " + type + ".");
          break;
        case "transformation":
          type = getNext(scan, "type");
          this.model.transformation(type, oldName, newName);
          render(newName + " successfully transformed by " + type + ".");
          break;
        case "save":
          this.model.saveFile(oldName, newName);
          render(newName + " successfully saved.");
          break;
        default:
          render("Invalid action, try again.\nEnter 'menu' for a list of valid commands.\n");
      }
    }
  }

  // ensures scanner does not call getNext() unless there is a next element.
  // if scanner does not have a next element, an error message is returned.
  private String getNext(Scanner sc, String message) {
    String newName = "";
    if (sc.hasNext()) {
      newName = sc.next();
    } else {
      try {
        this.view.renderMessage("Please enter a valid " + message + "\n");
      } catch (IOException ex) {
        throw new IllegalStateException("I/O exception occurred!");
      }
    }
    return newName;
  }

  // when the user is expected to enter the original filename, methods checks to see that they
  // entered some input. If they have, method checks that the file is valid file previously stored
  // in the hashmap. If both of these pass, only then is the old file name accepted.
  private String getOldFile(Scanner sc, String message) {
    String file = getNext(sc, message);
    if (this.model.getImage(file) == null) {
      file = "";
      try {
        this.view.renderMessage("Please enter a valid " + message + "\n");
      } catch (IOException ex) {
        throw new IllegalStateException("I/O exception occurred!");
      }
    }
    return file;
  }

  // tries to render the given message to the view,
  // throwing an illegal state exception if an I/O exception is caught
  private void render(String msg) {
    try {
      this.view.renderMessage(msg + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("I/O exception occurred!");
    }
  }
}