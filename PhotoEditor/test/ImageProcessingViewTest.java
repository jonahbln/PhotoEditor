import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.IView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * examples and tests written for the View class and it's methods.
 */
public class ImageProcessingViewTest {
  IView view1;
  Appendable append1;

  @Before
  public void init() {
    append1 = new StringBuilder();
    view1 = new ImageProcessingView(append1);
  }

  @Test
  public void renderMessageFail() {
    view1 = new ImageProcessingView(new FaultyAppendable());

    try {
      this.view1.renderMessage("test message");
      fail();
    } catch (IOException e) {
      assertEquals("testing purpose exception", e.getMessage());
    }
  }

  @Test
  public void renderMessageSuccess() {
    assertEquals("", this.append1.toString());

    try {
      this.view1.renderMessage("test message");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals("test message", this.append1.toString());

    try {
      this.view1.renderMessage(" another test message");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals("test message another test message", this.append1.toString());
  }

  @Test
  public void testRenderListOfCommandsFail() {
    view1 = new ImageProcessingView(new FaultyAppendable());

    try {
      this.view1.renderListOfCommands();
      fail();
    } catch (IOException e) {
      assertEquals("testing purpose exception", e.getMessage());
    }
  }

  @Test
  public void testRenderListOfCommandsSucess() {
    assertEquals("", this.append1.toString());

    try {
      this.view1.renderListOfCommands();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals(
            "The following is a list of all supported commands that you can use\n" +
                    "load ~file location~ ~image name~ : loads the image located at the given " +
                    "file location which will be stored in this program and referred to by the " +
                    "given image name\n" +
                    "\n" +
                    "save ~image name~ ~file location~ : saves all changes to the given locally " +
                    "stored image into the given file location on the user's system\n" +
                    "\n" +
                    "brighten ~image name~ ~new name~ ~value~ brightens the given image by the " +
                    "given integer value and renames the image to the given new name\n" +
                    "\n" +
                    "darken ~image name~ ~new name~ ~value~ darkens the given image by the given " +
                    "integer value and renames the image to the given new name\n" +
                    "\n" +
                    "flip ~image name~ ~new name~ ~direction~ flips the given image " +
                    "('vertically' or 'horizontally') and renames the image to the given new " +
                    "name\n" +
                    "\n" +
                    "greyscale ~image name~ ~new name~ ~type~ creates a greyscale image based " +
                    "on the component ('value', 'intensity', or 'luma') of the given image and " +
                    "names it the given new name\n" +
                    "\n" +
                    "greyscale-color ~image name~ ~new name~ ~color~ creates a greyscale image " +
                    "based on the given color component ('red', 'green' or 'blue') of the given " +
                    "image and names it the given new name\n" +
                    "\n" +
                    "filter ~image name~ ~new name~ ~type~ creates a filtered image " +
                    "('blur' or 'sharpen') of the given image and names it the given new name\n" +
                    "\n" +
                    "transformation ~image name~ ~new name~ ~type~ creates a transformed image " +
                    "" +
                    "('greyscale' or 'sepia') of the given image and names it the given new " +
                    "name\n\n" +
                    "enter 'quit at any time to quit the program, all unsaved changes will be" +
                    " lost.\n" +
                    "\n" +
                    "enter 'help' at any time to see this list of commands again.\n\n",
            this.append1.toString());
  }

  // this class is used for the view to force an I/O exception
  private class FaultyAppendable implements Appendable {
    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException("testing purpose exception");
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException("testing purpose exception");
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException("testing purpose exception");
    }
  }
}