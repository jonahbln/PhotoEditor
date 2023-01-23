import org.junit.Before;
import org.junit.Test;

import controller.GUIControllerImpl;
import controller.IGUIController;
import model.GUIModelImpl;
import model.IGUIModel;
import model.Image;
import view.IGUIView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * examples and tests written for the Model class and it's methods. Each constructor, command, and
 * public methods is tested.
 */
public class GUIModelImplTest {
  IGUIModel model1;
  IGUIController controller1;
  IGUIView view1;

  @Before
  public void init() {
    this.model1 = new GUIModelImpl();
    this.view1 = new ViewMock();
    this.controller1 = new GUIControllerImpl(model1, view1);

    this.model1.loadFile("res/colors.ppm");
  }

  @Test
  public void loadFileFail() {
    try {
      // try to load a file that does not exist
      this.model1.loadFile("res/fakePhotoName.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("File res/fakePhotoName.ppm not found!", e.getMessage());
    }
  }

  @Test
  public void loadFileSuccessPPM() {
    // now, load a file into the model
    this.model1.loadFile("res/colors.ppm");

    // now we can retrieve the image colorpic from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // lets load from a different file now
    this.model1.loadFile("res/something.ppm");

    // now we can retrieve the new image from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "150\n" +
                    "100\n" +
                    "150\n" +
                    "150\n" +
                    "250\n" +
                    "150\n" +
                    "60\n" +
                    "100\n" +
                    "60\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void loadFileSuccessPNG() {
    // now, load a file into the model with the name colorpic
    this.model1.loadFile("res/colors.png");

    // now we can retrieve the image colorpic from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // lets load from a different file now
    this.model1.loadFile("res/something.png");

    // now we can retrieve the new image from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "150\n" +
                    "100\n" +
                    "150\n" +
                    "150\n" +
                    "250\n" +
                    "150\n" +
                    "60\n" +
                    "100\n" +
                    "60\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void loadFileSuccessBMP() {
    // now, load a file into the model with the name colorpic
    this.model1.loadFile("res/colors.bmp");

    // now we can retrieve the image colorpic from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // lets load from a different file now
    this.model1.loadFile("res/something.bmp");

    // now we can retrieve the new image from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "150\n" +
                    "100\n" +
                    "150\n" +
                    "150\n" +
                    "250\n" +
                    "150\n" +
                    "60\n" +
                    "100\n" +
                    "60\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void loadFileSuccessJPG() {
    // now, load a file into the model with the name colorpic
    this.model1.loadFile("res/colors.jpg");

    // now we can retrieve the image colorpic from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n" +
                    "0\n" +
                    "0\n" +
                    "0\n" +
                    "255\n" +
                    "0\n" +
                    "0\n" +
                    "0\n" +
                    "255\n" +
                    "100\n" +
                    "50\n" +
                    "100\n" +
                    "100\n" +
                    "200\n" +
                    "100\n" +
                    "10\n" +
                    "50\n" +
                    "10\n",
            this.model1.getImage().toPPM());

    // lets load from a different file now
    this.model1.loadFile("res/something.jpg");

    // now we can retrieve the new image from the model, proving that it was loaded and stored
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "50\n" +
                    "50\n" +
                    "50\n" +
                    "255\n" +
                    "150\n" +
                    "100\n" +
                    "150\n" +
                    "150\n" +
                    "250\n" +
                    "150\n" +
                    "60\n" +
                    "100\n" +
                    "60\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void greyScaleFail() {
    try {
      // use a fake greyscale type with an image that does exist
      this.model1.greyscale("fakeType");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Try again with a valid type " +
              "('value', 'intensity', or 'luma')", e.getMessage());
    }
  }

  @Test
  public void greyscaleIntensity() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // greyscale the image using the intensity component
    this.model1.greyscale("intensity");

    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "85\n85\n85\n" +
                    "85\n85\n85\n" +
                    "85\n85\n85\n" +
                    "83\n83\n83\n" +
                    "133\n133\n133\n" +
                    "23\n23\n23\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void greyscaleValue() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // greyscale the image using the value component
    this.model1.greyscale("value");

    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n255\n255\n" +
                    "255\n255\n255\n" +
                    "255\n255\n255\n" +
                    "100\n100\n100\n" +
                    "200\n200\n200\n" +
                    "50\n50\n50\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void greyscaleLuma() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // greyscale the image using the luma component
    this.model1.greyscale("luma");

    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "54\n" +
                    "54\n" +
                    "54\n" +
                    "182\n" +
                    "182\n" +
                    "182\n" +
                    "18\n" +
                    "18\n" +
                    "18\n" +
                    "64\n" +
                    "64\n" +
                    "64\n" +
                    "172\n" +
                    "172\n" +
                    "172\n" +
                    "39\n" +
                    "39\n" +
                    "39\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void greyscaleColorFail() {
    try {
      // use a fake greyscale type with an image that does exist
      this.model1.greyscaleColor("fakeColor");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Try again with a valid color " +
              "('red', 'green', or 'blue')", e.getMessage());
    }
  }

  @Test
  public void greyscaleRed() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // greyscale the image using the red component
    this.model1.greyscaleColor("red");

    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n255\n255\n" +
                    "0\n0\n0\n" +
                    "0\n0\n0\n" +
                    "100\n100\n100\n" +
                    "100\n100\n100\n" +
                    "10\n10\n10\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void greyscaleGreen() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // greyscale the image using the green component
    this.model1.greyscaleColor("green");

    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "0\n0\n0\n" +
                    "255\n255\n255\n" +
                    "0\n0\n0\n" +
                    "50\n50\n50\n" +
                    "200\n200\n200\n" +
                    "50\n50\n50\n",
            this.model1.getImage().toPPM());

  }

  @Test
  public void greyscaleBlue() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // greyscale the image using the blue component
    this.model1.greyscaleColor("blue");

    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "0\n0\n0\n" +
                    "0\n0\n0\n" +
                    "255\n255\n255\n" +
                    "100\n100\n100\n" +
                    "100\n100\n100\n" +
                    "10\n10\n10\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void changeBrightnessSuccessPositive() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // change brightness of the image using a positive value
    this.model1.changeBrightness(50);

    // show that the values have been brightened by the given amount
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n50\n50\n" +
                    "50\n255\n50\n" +
                    "50\n50\n255\n" +
                    "150\n100\n150\n" +
                    "150\n250\n150\n" +
                    "60\n100\n60\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void changeBrightnessSuccessNegative() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" +
                    "0\n255\n0\n" +
                    "0\n0\n255\n" +
                    "100\n50\n100\n" +
                    "100\n200\n100\n" +
                    "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // change brightness of the image using a negative value
    this.model1.changeBrightness(-50);

    // show that the values have been darkened by the given amount
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "205\n0\n0\n" +
                    "0\n205\n0\n" +
                    "0\n0\n205\n" +
                    "50\n0\n50\n" +
                    "50\n150\n50\n" +
                    "0\n0\n0\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void flipFail() {
    try {
      // use an image name that does not exist
      this.model1.flip("fakeDirection");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("try again with a valid direction('horizontal', 'vertical')"
              , e.getMessage());
    }
  }

  @Test
  public void flipSuccessVertical() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" + "0\n255\n0\n" + "0\n0\n255\n" +
                    "100\n50\n100\n" + "100\n200\n100\n" + "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // flip the image vertically
    this.model1.flip("vertical");

    // show that the image has been flipped vertically
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "100\n50\n100\n" + "100\n200\n100\n" + "10\n50\n10\n" +
                    "255\n0\n0\n" + "0\n255\n0\n" + "0\n0\n255\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void flipSuccessHorizontal() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" + "0\n255\n0\n" + "0\n0\n255\n" +
                    "100\n50\n100\n" + "100\n200\n100\n" + "10\n50\n10\n",
            this.model1.getImage().toPPM());

    // flip the image horizontally
    this.model1.flip("horizontal");

    // show that the image has been flipped horizontally
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "0\n0\n255\n" + "0\n255\n0\n" + "255\n0\n0\n" +
                    "10\n50\n10\n" + "100\n200\n100\n" + "100\n50\n100\n",
            this.model1.getImage().toPPM());
  }

  @Test
  public void testBlur() {
    this.model1.filter("blur");
    assertEquals("P3\n" +
            "3\n" +
            "2\n" +
            "255\n" +
            "83\n" +
            "51\n" +
            "19\n" +
            "51\n" +
            "95\n" +
            "51\n" +
            "8\n" +
            "51\n" +
            "71\n" +
            "69\n" +
            "53\n" +
            "38\n" +
            "55\n" +
            "94\n" +
            "55\n" +
            "15\n" +
            "53\n" +
            "47\n", this.model1.getImage().toPPM());
  }

  @Test
  public void testSharpen() {
    this.model1.filter("sharpen");
    assertEquals("P3\n" +
            "3\n" +
            "2\n" +
            "255\n" +
            "255\n" +
            "120\n" +
            "17\n" +
            "116\n" +
            "255\n" +
            "116\n" +
            "0\n" +
            "120\n" +
            "255\n" +
            "188\n" +
            "158\n" +
            "92\n" +
            "191\n" +
            "255\n" +
            "191\n" +
            "0\n" +
            "158\n" +
            "86\n", this.model1.getImage().toPPM());
  }

  @Test
  public void testGreyscale() {
    this.model1.transformation("greyscale");
    assertEquals("P3\n" +
            "3\n" +
            "2\n" +
            "255\n" +
            "54\n" +
            "54\n" +
            "54\n" +
            "182\n" +
            "182\n" +
            "182\n" +
            "18\n" +
            "18\n" +
            "18\n" +
            "64\n" +
            "64\n" +
            "64\n" +
            "172\n" +
            "172\n" +
            "172\n" +
            "39\n" +
            "39\n" +
            "39\n", this.model1.getImage().toPPM());
  }

  @Test
  public void testSepia() {
    this.model1.transformation("sepia");
    assertEquals("P3\n" +
            "3\n" +
            "2\n" +
            "255\n" +
            "100\n" +
            "89\n" +
            "69\n" +
            "196\n" +
            "175\n" +
            "136\n" +
            "48\n" +
            "43\n" +
            "33\n" +
            "97\n" +
            "86\n" +
            "67\n" +
            "212\n" +
            "189\n" +
            "147\n" +
            "44\n" +
            "39\n" +
            "31\n", this.model1.getImage().toPPM());
  }

  // fake view that doesn't show frame/panel when test is run
  protected static class ViewMock implements IGUIView {
    @Override
    public void placeImage(Image image) {
      return;
    }

    @Override
    public void renderMessage(String s) {
      return;
    }

    @Override
    public void renderListOfCommands() {
      return;
    }
  }
}