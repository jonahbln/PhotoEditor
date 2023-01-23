import org.junit.Test;

import model.IModel;
import model.ImageProcessingModel;

import static org.junit.Assert.assertEquals;

/**
 * examples and tests written for the Image class and it's methods. The only public method needed
 * to be tested in the Image class is toPPM(). This class does not have any edge cases because
 * it is converting a valid array into a string.
 */
public class ImageTest {
  // tests only provided for public methods

  @Test
  public void testToPPM() {
    // test with newly initialized image
    IModel model = new ImageProcessingModel();

    model.loadFile("res/colors.jpg", "idc");

    model.loadFile("res/colors.ppm", "colorpic");
    assertEquals("P3\n" +
            "3\n2\n255\n255\n0\n0\n0\n255\n0\n0\n0\n255\n100\n50\n100\n" +
            "100\n200\n100\n10\n50\n10\n", model.getImage("colorpic").toPPM());

    // test for toPPM() after the image has been changed
    model.changeBrightness(10, "colorpic", "colorbrightened");
    assertEquals("P3\n" +
            "3\n" +
            "2\n" +
            "255\n" +
            "255\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "255\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "255\n" +
            "110\n" +
            "60\n" +
            "110\n" +
            "110\n" +
            "210\n" +
            "110\n" +
            "20\n" +
            "60\n" +
            "20\n", model.getImage("colorbrightened").toPPM());

    model.greyscaleColor("green", "colorbrightened", "colorgreyscalegreen");
    assertEquals("P3\n" +
            "3\n" +
            "2\n" +
            "255\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "60\n" +
            "60\n" +
            "60\n" +
            "210\n" +
            "210\n" +
            "210\n" +
            "60\n" +
            "60\n" +
            "60\n", model.getImage("colorgreyscalegreen").toPPM());
  }
}