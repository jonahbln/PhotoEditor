package controller;

/**
 * Represents a controller for a GUI image processor program.
 * The controller is in charge of running the program itself by interpreting user input,
 * accessing model methods,
 * and utilizing the view to display the resulting image.
 */
public interface IGUIController {

  /**
   * Changes the brightness of the photo by a given value. If the type is 'brighten', value is
   * positive. If type is 'darken', value is negative.
   *
   * @param type either 'brighten' or 'darken'
   * @param val  value to change current photo by
   */
  void callChangeBrightness(String type, int val);

  /**
   * Calls the appropriate command in the switch statement and renders a message to the view
   * when it has been successfully completed.
   *
   * @param s command from the user
   */
  void callAction(String s);

  /**
   * Loads an image with the given file path. The loaded image is set as the current image and
   * can be manipulated.
   *
   * @param name file path of the image
   */
  void callLoad(String name);

  /**
   * Saves the current image in the given file path using FileIO.
   *
   * @param name file path of the image.
   */
  void callSave(String name);
}
