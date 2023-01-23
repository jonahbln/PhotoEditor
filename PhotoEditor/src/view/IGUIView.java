package view;

/**
 * This interface represents a GUI view for a program to process images. User input is interpreted
 * through the controller and model. The resulting calculation/image is displayed through the
 * view.
 */
public interface IGUIView {
  /**
   * Place histogram in GUI. Automatically updated in the GUI everytime the Image is manipulated.
   *
   * @param image of histogram
   */
  void placeImage(model.Image image);

  /**
   * Display a message to the view in the GUI. The message is updated with every user action.
   *
   * @param s the message to be rendered.
   */
  void renderMessage(String s);

  /**
   * Render the list of available commands in the GUI. Displayed when user presses a specific
   * JButton.
   */
  void renderListOfCommands();

}
