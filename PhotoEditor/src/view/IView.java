package view;

import java.io.IOException;

/**
 * represents a view object of an image processor
 * that can be used to communicate information to the user through any form of output.
 */
public interface IView {
  /**
   * renders a given message to be read by the user.
   *
   * @param msg the message to be rendered.
   * @throws IOException if there is an error in attempting to write to the output object
   */
  void renderMessage(String msg) throws IOException;


  /**
   * renders a list of all supported commands to the user.
   *
   * @throws IOException if there is an error in attempting to write to the output object
   */
  void renderListOfCommands() throws IOException;
}
