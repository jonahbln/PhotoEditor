import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingController;
import model.IModel;
import model.ImageProcessingModel;
import view.IView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * examples and tests written for the ImageProcessingControllerImpl class and it's methods. Each
 * action is tested along with invalid and valid constructor initializations. All exceptions are
 * also tested.
 */
public class ImageProcessingControllerTest {
  ImageProcessingController controller;
  IModel model;
  IView view;
  Readable in;
  Appendable out;

  private void testOutput(String input) {
    this.in = new StringReader(input);
    this.out = new StringBuilder();
    this.view = new ImageProcessingView(this.out);
    this.model = new ImageProcessingModel();
    this.controller = new ImageProcessingController(model, view, in);
    this.controller.run();
  }


  @Test
  public void testValidInitialization() {
    this.view = new ImageProcessingView();
    this.controller = new ImageProcessingController(new ImageProcessingModel(), this.view,
            new StringReader(""));
    testOutput("quit");
    assertEquals("Welcome to the image processor!\n" +
                    "\n" +
                    "At any time enter 'menu' to see a list of valid commands\n" +
                    "or enter 'quit' to exit the program.\n" +
                    "\n" +
                    "program quit!\n",
            this.out.toString());
  }

  @Test
  public void testInvalidInitialization() {
    try {
      this.controller = new ImageProcessingController(
              null, new ImageProcessingView(), new StringReader(""));
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("one or more provided parameters are null", e.getMessage());
    }
    try {
      this.controller = new ImageProcessingController(
              new ImageProcessingModel(), null, new StringReader(""));
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("one or more provided parameters are null", e.getMessage());
    }
    try {
      this.controller = new ImageProcessingController(
              null, new ImageProcessingView(), null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("one or more provided parameters are null", e.getMessage());
    }
  }


  @Test
  public void testController() {
    // test menu
    testOutput("menu");
    assertEquals("Welcome to the image processor!\n" +
                    "\n" +
                    "At any time enter 'menu' to see a list of valid commands\n" +
                    "or enter 'quit' to exit the program.\n" +
                    "\n" +
                    "The following is a list of all supported commands that you can use\n" +
                    "load ~file location~ ~image name~ :" +
                    " loads the image located at the given file " +
                    "location which will be stored in" +
                    " this program and referred to by the given " +
                    "image name\n" +
                    "\n" +
                    "save ~image name~ ~file location~ :" +
                    " saves all changes to the given locally stored " +
                    "image into the given file location on the user's system\n" +
                    "\n" +
                    "brighten ~image name~ ~new name~ ~value~" +
                    " brightens the given image by the given " +
                    "integer value and renames the image to the given new name\n" +
                    "\n" +
                    "darken ~image name~ ~new name~ ~value~" +
                    " darkens the given image by the given " +
                    "integer value and renames the image to the given new name\n" +
                    "\n" +
                    "flip ~image name~ ~new name~ ~direction~ flips the given image " +
                    "('vertically' or 'horizontally')" +
                    " and renames the image to the given new name\n" +
                    "\n" +
                    "greyscale ~image name~ ~new name~ ~type~" +
                    " creates a greyscale image based on the " +
                    "component ('value', 'intensity', or 'luma')" +
                    " of the given image and names it the " +
                    "given new name\n" +
                    "\n" +
                    "greyscale-color ~image name~ ~new name~ ~color~ creates a greyscale image " +
                    "based on the given color component ('red', 'green' or 'blue')" +
                    " of the given image" +
                    " and names it the given new name\n" +
                    "\n" +
                    "filter ~image name~ ~new name~ ~type~ creates a filtered image" +
                    " ('blur' or 'sharpen') "
                    +
                    "of the given image and names it the given new name\n" +
                    "\n" +
                    "transformation ~image name~ ~new name~ ~type~" +
                    " creates a transformed image " +
                    "('greyscale' or 'sepia')" +
                    " of the given image and names it the given new name\n" +
                    "\n" +
                    "enter 'quit at any time to quit the program," +
                    " all unsaved changes will be lost.\n" +
                    "\n" +
                    "enter 'help' at any time to see this list of commands again.\n\n",
            this.out.toString());

    // test load
    testOutput("load res/colors.ppm colorpic");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n", this.out.toString());

    // test greyscale
    testOutput("load res/colors.ppm colorpic greyscale colorpic greycolorpic luma " +
            "greyscale greycolorpic color2 intensity greyscale color2 color3 value quit");
    assertEquals(
            "Welcome to the image processor!\n" +
                    "\n" +
                    "At any time enter 'menu' to see a list of valid commands\n" +
                    "or enter 'quit' to exit the program.\n" +
                    "\n" +
                    "colorpic successfully loaded.\n" +
                    "greycolorpic successfully greyscaled.\n" +
                    "color2 successfully greyscaled.\n" +
                    "color3 successfully greyscaled.\n" +
                    "program quit!\n", this.out.toString());

    // test brighten
    testOutput("load res/colors.ppm colorpic brighten colorpic colorbrightened 25 quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colorbrightened successfully brightened by 25.\n" +
            "program quit!\n", this.out.toString());

    // test darken
    testOutput("load res/colors.ppm colorpic darken colorpic colordarken 19 quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colordarken successfully darkened by 19.\n" +
            "program quit!\n", this.out.toString());

    // test flip
    testOutput("load res/colors.ppm colorpic flip colorpic colorflip horizontal " +
            "flip colorflip colordoubleflip vertical quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colorflip successfully flipped.\n" +
            "colordoubleflip successfully flipped.\n" +
            "program quit!\n", this.out.toString());

    // test greyscale-color
    testOutput("load res/colors.ppm colorpic greyscale-color colorpic colorred red " +
            "greyscale-color colorred colorredgreen green greyscale-color colorredgreen " +
            "colorredgreenblue blue quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colorred successfully greyscaled by color.\n" +
            "colorredgreen successfully greyscaled by color.\n" +
            "colorredgreenblue successfully greyscaled by color.\n" +
            "program quit!\n", this.out.toString());

    // test filter
    testOutput("load res/colors.ppm colorpic filter colorpic colorblur blur " +
            "filter colorblur colorfiltered sharpen quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colorblur successfully filtered by blur.\n" +
            "colorfiltered successfully filtered by sharpen.\n" +
            "program quit!\n", this.out.toString());

    // test color transformation
    testOutput(
            "load res/colors.ppm colorpic transformation colorpic colorgreyscale greyscale " +
                    "transformation colorgreyscale colortransformed sepia quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colorgreyscale successfully transformed by greyscale.\n" +
            "colortransformed successfully transformed by sepia.\n" +
            "program quit!\n", this.out.toString());

    // test save
    testOutput("load res/colors.ppm colorpic greyscale-color colorpic colorgreen green " +
            "save colorgreen res/savedcolor.ppm quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colorgreen successfully greyscaled by color.\n" +
            "res/savedcolor.ppm successfully saved.\n" +
            "program quit!\n", this.out.toString());

    // test menu followed by another action
    testOutput("menu load res/colors.ppm colorspic greyscale colorspic " +
            "colorsgreyscale intensity ");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "The following is a list of all supported commands that you can use\n" +
            "load ~file location~ ~image name~ : loads the image located at the given file" +
            " location which will be stored in this program and referred" +
            " to by the given image name\n" +
            "\n" +
            "save ~image name~ ~file location~ : saves all changes" +
            " to the given locally stored image into the given file" +
            " location on the user's system\n" +
            "\n" +
            "brighten ~image name~ ~new name~ ~value~ brightens the given" +
            " image by the given integer value and renames the image to the given new name\n" +
            "\n" +
            "darken ~image name~ ~new name~ ~value~ darkens the given image by the given integer" +
            " value and renames the image to the given new name\n" +
            "\n" +
            "flip ~image name~ ~new name~ ~direction~ flips the given image" +
            " ('vertically' or 'horizontally') and renames the image to the given new name\n" +
            "\n" +
            "greyscale ~image name~ ~new name~ ~type~ creates a greyscale image based on the" +
            " component ('value', 'intensity', or 'luma')" +
            " of the given image and names it the given new name\n" +
            "\n" +
            "greyscale-color ~image name~ ~new name~ ~color~ creates a greyscale image" +
            " based on the given color component ('red', 'green' or 'blue') of the given" +
            " image and names it the given new name\n" +
            "\n" +
            "filter ~image name~ ~new name~ ~type~ creates a filtered image " +
            "('blur' or 'sharpen') of the given image and names it the given new name\n" +
            "\n" +
            "transformation ~image name~ ~new name~ ~type~ creates a transformed image " +
            "('greyscale' or 'sepia') of the given image and names it the given new name\n" +
            "\n" +
            "enter 'quit at any time to quit the program, all unsaved changes will be lost.\n" +
            "\n" +
            "enter 'help' at any time to see this list of commands again.\n" +
            "\n" +
            "colorspic successfully loaded.\n" +
            "colorsgreyscale successfully greyscaled.\n", this.out.toString());

    // test multiple commands
    testOutput("load res/colors.ppm colorpic greyscale colorpic colorluma luma " +
            "flip colorluma colorflip vertical darken colorflip colordarken 10 " +
            "save colordarken res/colorfinal.ppm quit");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "colorluma successfully greyscaled.\n" +
            "colorflip successfully flipped.\n" +
            "colordarken successfully darkened by 10.\n" +
            "res/colorfinal.ppm successfully saved.\n" +
            "program quit!\n", this.out.toString());

    // test default
    testOutput("load res/colors.ppm colorpic enlarge colorpic colorenlarged");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "Invalid action, try again.\n" +
            "Enter 'menu' for a list of valid commands.\n\n", this.out.toString());

    // test invalid old file name followed by valid file name, error handling
    testOutput("load res/colors.ppm colorpic darken invalidOld colorpic colordarkened 10");
    assertEquals("Welcome to the image processor!\n" +
            "\n" +
            "At any time enter 'menu' to see a list of valid commands\n" +
            "or enter 'quit' to exit the program.\n" +
            "\n" +
            "colorpic successfully loaded.\n" +
            "Please enter a valid file name\n" +
            "colordarkened successfully darkened by 10.\n", this.out.toString());
  }

  @Test
  public void testNoInput() {
    try {
      testOutput("");
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Please enter a valid command.", e.getMessage());
    }
  }
}