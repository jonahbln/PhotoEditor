package view;

import java.io.IOException;

/**
 * represents a view object of an image processor
 * that can be used to communicate information to the user through an appendable object.
 */
public class ImageProcessingView implements IView {
  private final Appendable append;

  /**
   * constructs a view with a user-defined appendable object.
   *
   * @param append the appendable object that will be used by the render methods in this view
   * @throws IllegalArgumentException if the given paramter is null
   */
  public ImageProcessingView(Appendable append) throws IllegalArgumentException {
    if (append == null) {
      throw new IllegalArgumentException();
    }
    this.append = append;
  }

  /**
   * constructs a default view with the System.out as the appendable object for the user to view.
   */
  public ImageProcessingView() {
    this(System.out);
  }

  @Override
  public void renderMessage(String msg) throws IOException {
    this.append.append(msg);
  }

  @Override
  public void renderListOfCommands() throws IOException {
    this.renderMessage(
            "The following is a list of all supported commands that you can use\n" +
                    "load ~file location~ ~image name~ : loads the image located at the given " +
                    "file location which will be stored in this " +
                    "program and referred to by the given image name\n\n" +
                    "save ~image name~ ~file location~ : saves all changes to the given " +
                    "locally stored image " +
                    "into the given file location on the user's system\n\n" +
                    "brighten ~image name~ ~new name~ ~value~ brightens the given image by the " +
                    "given integer value and renames the image to the given new name\n\n" +
                    "darken ~image name~ ~new name~ ~value~ darkens the given image by the " +
                    "given integer value and renames the image to the given new name\n\n" +
                    "flip ~image name~ ~new name~ ~direction~ flips the given image " +
                    "('vertically' or 'horizontally')" +
                    " and renames the image to the given new name\n\n" +
                    "greyscale ~image name~ ~new name~ ~type~ creates a greyscale image based on" +
                    " the component ('value', 'intensity', or 'luma') " +
                    "of the given image and names it " +
                    "the given new name\n\n" +
                    "greyscale-color ~image name~ ~new name~ ~color~ creates a greyscale" +
                    " image based on the given color component ('red', 'green' or 'blue')" +
                    " of the given image and names it the given new name\n\n" +
                    "filter ~image name~ ~new name~ ~type~ creates a filtered" +
                    " image ('blur' or 'sharpen')" +
                    " of the given image and names it the given new name\n\n" +
                    "transformation ~image name~ ~new name~ ~type~ creates a transformed" +
                    " image ('greyscale' or 'sepia')" +
                    " of the given image and names it the given new name\n\n" +
                    "enter 'quit at any time to quit the program," +
                    " all unsaved changes will be lost.\n\n" +
                    "enter 'help' at any time to see this list of commands again.\n\n");
  }
}
