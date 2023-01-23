import org.junit.Before;
import org.junit.Test;

import model.IModel;
import model.ImageProcessingModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * examples and tests written for the Model class and it's methods.
 */
public class ImageProcessingModelTest {
  IModel model1;

  @Before
  public void init() {
    this.model1 = new ImageProcessingModel();
    this.model1.loadFile("res/colors.ppm", "colorpic");
    this.model1.saveFile("colorpic", "res/colors.jpg");
    this.model1.saveFile("colorpic", "res/colors.png");
    this.model1.saveFile("colorpic", "res/colors.bmp");
    this.model1.saveFile("colorpic", "res/colors.jpeg");
  }

  @Test
  public void loadFileFail() {
    try {
      // try to load a file that does not exist
      this.model1.loadFile("res/fakePhotoName.ppm", "saveName");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("File res/fakePhotoName.ppm not found!", e.getMessage());
    }
  }

  @Test
  public void loadFileSuccessPPM() {
    // now, load a file into the model with the name colorpic
    this.model1.loadFile("res/colors.ppm", "test");

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
            this.model1.getImage("test").toPPM());

    // now, load the same file into the model using another name
    this.model1.loadFile("res/colors.ppm", "colorpic69");

    //now we can show that the two images are stored in this model with different names
    // but they contain identical data
    assertEquals(this.model1.getImage("test").toPPM(),
            this.model1.getImage("colorpic69").toPPM());

    // lets load from a different file now
    this.model1.loadFile("res/something.ppm", "lasan");

    // this shows that the new ppm has been loaded with
    // a different name and the data is not identical being from a different file
    assertNotEquals(this.model1.getImage("test").toPPM(),
            this.model1.getImage("lasan").toPPM());
  }

  @Test
  public void loadFileSuccessPNG() {
    // now, load a file into the model with the name colorpic
    this.model1.loadFile("res/colors.png", "test");

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
            this.model1.getImage("test").toPPM());

    // now, load the same file into the model using another name
    this.model1.loadFile("res/colors.png", "colorpic69");

    //now we can show that the two images are stored in this model with different names
    // but they contain identical data
    assertEquals(this.model1.getImage("test").toPPM(),
            this.model1.getImage("colorpic69").toPPM());

    // lets load from a different file now
    this.model1.loadFile("res/something.png", "lasan");

    // this shows that the new ppm has been loaded with
    // a different name and the data is not identical being from a different file
    assertNotEquals(this.model1.getImage("test").toPPM(),
            this.model1.getImage("lasan").toPPM());
  }

  @Test
  public void loadFileSuccessBMP() {
    // now, load a file into the model with the name colorpic
    this.model1.loadFile("res/colors.bmp", "test");

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
            this.model1.getImage("test").toPPM());

    // now, load the same file into the model using another name
    this.model1.loadFile("res/colors.bmp", "colorpic69");

    //now we can show that the two images are stored in this model with different names
    // but they contain identical data
    assertEquals(this.model1.getImage("test").toPPM(),
            this.model1.getImage("colorpic69").toPPM());

    // lets load from a different file now
    this.model1.loadFile("res/something.bmp", "lasan");

    // this shows that the new ppm has been loaded with
    // a different name and the data is not identical being from a different file
    assertNotEquals(this.model1.getImage("test").toPPM(),
            this.model1.getImage("lasan").toPPM());
  }

  @Test
  public void loadFileSuccessJPG() {
    // now, load a file into the model with the name colorpic
    this.model1.loadFile("res/colors.jpg", "test");

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
            this.model1.getImage("test").toPPM());

    // now, load the same file into the model using another name
    this.model1.loadFile("res/colors.jpg", "colorpic69");

    //now we can show that the two images are stored in this model with different names
    // but they contain identical data
    assertEquals(this.model1.getImage("test").toPPM(),
            this.model1.getImage("colorpic69").toPPM());
  }

  @Test
  public void saveFileFail() {
    try {
      // try to save an image that has not been loaded
      this.model1.saveFile("notSaved", "res/test.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'notSaved' does not exist!", e.getMessage());
    }

    try {
      // try to save a loaded image to a file location that does not exist
      this.model1.saveFile("test", "fakeDirectory/test.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("File location 'fakeDirectory/test.ppm' does not exist!",
              e.getMessage());
    }

    try {
      // try to save a loaded image to a file location that does not exist
      this.model1.saveFile("test", "res/test.gif");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("invalid image file format provided " +
                      "(use either ppm, png, bmp, jpeg, or jpg)",
              e.getMessage());
    }

  }

  @Test
  public void saveFileSuccessPPM() {
    // this test looks like a lot but it all goes to prove that a loaded image
    // can be saved as a file to the system while preserving all data

    // load a file into 'colorpic'
    this.model1.loadFile("res/colors.ppm", "colorpic");

    // show the image has been loaded
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
            this.model1.getImage("colorpic").toPPM());

    // now save the file onto the system
    this.model1.saveFile("colorpic", "res/something.ppm");

    // attempt to load the file that we just saved onto the system
    this.model1.loadFile("res/something.ppm", "test");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
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
            this.model1.getImage("test").toPPM());

    // make a change to the image
    // (it doesnt matter what this change is, we just want to show
    // that it manipulates the data contained in the image in some way)
    this.model1.changeBrightness(50, "test", "testbrightened");

    // show that the data has changed
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
            this.model1.getImage("testbrightened").toPPM());

    // now save the file onto the system at the SAME file location (it should be overwritten now)
    this.model1.saveFile("testbrightened", "res/something.ppm");

    // attempt to load the file that we just re-saved onto the system
    this.model1.loadFile("res/something.ppm", "somethingtest");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
    // after being overwritten.
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
            this.model1.getImage("somethingtest").toPPM());
  }

  @Test
  public void saveFileSuccessPNG() {
    // load a standard image format file into 'colorpic'
    this.model1.loadFile("res/colors.png", "colorpic");

    // show the image has been loaded
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
            this.model1.getImage("colorpic").toPPM());

    // now save the file onto the system
    this.model1.saveFile("colorpic", "res/something.png");

    // attempt to load the file that we just saved onto the system
    this.model1.loadFile("res/something.png", "test");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
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
            this.model1.getImage("test").toPPM());

    // make a change to the image
    // (it doesnt matter what this change is, we just want to show
    // that it manipulates the data contained in the image in some way)
    this.model1.changeBrightness(50, "test", "testbrightened");

    // show that the data has changed
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
            this.model1.getImage("testbrightened").toPPM());

    // now save the file onto the system at the SAME file location (it should be overwritten now)
    this.model1.saveFile("testbrightened", "res/something.png");

    // attempt to load the file that we just re-saved onto the system
    this.model1.loadFile("res/something.png", "somethingtest");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
    // after being overwritten.
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
            this.model1.getImage("somethingtest").toPPM());
  }

  @Test
  public void saveFileSuccessBMP() {
    // load a standard image format file into 'colorpic'
    this.model1.loadFile("res/colors.bmp", "colorpic");

    // show the image has been loaded
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
            this.model1.getImage("colorpic").toPPM());

    // now save the file onto the system
    this.model1.saveFile("colorpic", "res/something.bmp");

    // attempt to load the file that we just saved onto the system
    this.model1.loadFile("res/something.bmp", "test");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
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
            this.model1.getImage("test").toPPM());

    // make a change to the image
    // (it doesnt matter what this change is, we just want to show
    // that it manipulates the data contained in the image in some way)
    this.model1.changeBrightness(50, "test", "testbrightened");

    // show that the data has changed
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
            this.model1.getImage("testbrightened").toPPM());

    // now save the file onto the system at the SAME file location (it should be overwritten now)
    this.model1.saveFile("testbrightened", "res/something.bmp");

    // attempt to load the file that we just re-saved onto the system
    this.model1.loadFile("res/something.bmp", "somethingtest");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
    // after being overwritten.
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
            this.model1.getImage("somethingtest").toPPM());
  }

  @Test
  public void saveFileSuccessJPG() {
    // load a standard image format file into 'colorpic'
    this.model1.loadFile("res/colors.jpg", "colorpic");
    // show the image has been loaded
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
            this.model1.getImage("colorpic").toPPM());

    // now save the file onto the system
    this.model1.saveFile("colorpic", "res/something.jpg");

    // attempt to load the file that we just saved onto the system
    this.model1.loadFile("res/something.jpg", "test");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
    // show the image has been loaded
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
            this.model1.getImage("test").toPPM());

    // make a change to the image
    // (it doesnt matter what this change is, we just want to show
    // that it manipulates the data contained in the image in some way)
    this.model1.changeBrightness(50, "test", "testbrightened");

    // show that the data has changed
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
            this.model1.getImage("testbrightened").toPPM());

    // now save the file onto the system at the SAME file location (it should be overwritten now)
    this.model1.saveFile("testbrightened", "res/something.jpg");

    // attempt to load the file that we just re-saved onto the system
    this.model1.loadFile("res/something.jpg", "somethingtest");

    // show that the saved file can be retrieved and contains all changes made to the original pic
    // this proved that the locally stored image was saved to the correct location on the system
    // the data manipulation can be seen in the file after saving and it can be read by the program
    // after being overwritten.
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
            this.model1.getImage("somethingtest").toPPM());
  }

  @Test
  public void testLoadPPMSaveStandard() {
    // testing that you can load a ppm file and save
    // it as a standard image without messing with the data
    this.model1.loadFile("res/colors.ppm", "test");
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
            "10\n", this.model1.getImage("test").toPPM());
    this.model1.saveFile("test", "res/test.png");
    this.model1.loadFile("res/test.png", "test2");
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
            "10\n", this.model1.getImage("test2").toPPM());
  }

  @Test
  public void testLoadStandardSavePPM() {
    // testing that you can load a standard image format file and save
    // it as a PPM without messing with the data
    this.model1.loadFile("res/colors.png", "test");
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
            "10\n", this.model1.getImage("test").toPPM());
    this.model1.saveFile("test", "res/test.ppm");
    this.model1.loadFile("res/test.ppm", "test2");
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
            "10\n", this.model1.getImage("test2").toPPM());
  }

  @Test
  public void testLoadPNGSaveJPGBMP() {
    // testing that you can load a PNG file and save it as a JPG or BMP
    this.model1.loadFile("res/colors.png", "test");
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
            "10\n", this.model1.getImage("test").toPPM());
    this.model1.saveFile("test", "res/test.jpg");
    this.model1.loadFile("res/test.jpg", "test2");
    this.model1.saveFile("test", "res/test.bmp");
    this.model1.loadFile("res/test.bmp", "test3");
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
            "10\n", this.model1.getImage("test2").toPPM());
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
            "10\n", this.model1.getImage("test3").toPPM());
  }

  @Test
  public void testLoadJPGSavePNGBMP() {
    // testing that you can load a JPG file and save it as a PNG or BMP
    this.model1.loadFile("res/colors.jpg", "test");
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
            "10\n", this.model1.getImage("test").toPPM());
    this.model1.saveFile("test", "res/test.png");
    this.model1.loadFile("res/test.png", "test2");
    this.model1.saveFile("test", "res/test.bmp");
    this.model1.loadFile("res/test.bmp", "test3");
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
            "10\n", this.model1.getImage("test2").toPPM());
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
            "10\n", this.model1.getImage("test3").toPPM());
  }

  @Test
  public void testLoadBMPSaveJPGPNG() {
    // testing that you can load a PNG file and save it as a JPG or BMP
    this.model1.loadFile("res/colors.bmp", "test");
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
            "10\n", this.model1.getImage("test").toPPM());
    this.model1.saveFile("test", "res/test.jpg");
    this.model1.loadFile("res/test.jpg", "test2");
    this.model1.saveFile("test", "res/test.png");
    this.model1.loadFile("res/test.png", "test3");
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
            "10\n", this.model1.getImage("test2").toPPM());
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
            "10\n", this.model1.getImage("test3").toPPM());
  }

  @Test
  public void greyScaleFail() {
    try {
      // use an image name that does not exist (i did it for all three greyscale types)
      this.model1.greyscale("luma", "fakeFileName", "greyFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }

    try {
      this.model1.greyscale("value", "fakeFileName", "greyFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }

    try {
      this.model1.greyscale("intensity", "fakeFileName", "greyFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }

    try {
      // use a fake greyscale type with an image that does exist
      this.model1.greyscale("fakeType", "colorpic", "greyFail");
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
            this.model1.getImage("colorpic").toPPM());

    // greyscale the image using the intensity component
    this.model1.greyscale("intensity", "colorpic", "colorpicintensity");

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
            this.model1.getImage("colorpicintensity").toPPM());
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
            this.model1.getImage("colorpic").toPPM());

    // greyscale the image using the value component
    this.model1.greyscale("value", "colorpic", "colorpicvalue");

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
            this.model1.getImage("colorpicvalue").toPPM());
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
            this.model1.getImage("colorpic").toPPM());

    // greyscale the image using the luma component
    this.model1.greyscale("luma", "colorpic", "colorpicluma");

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
            this.model1.getImage("colorpicluma").toPPM());
    this.model1.saveFile("colorpicluma", "res/colorpicluma.png");
  }

  @Test
  public void greyscaleColorFail() {
    try {
      // use an image name that does not exist (i did it for all three greyscale types)
      this.model1.greyscaleColor("red", "fakeFileName", "greyFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }

    try {
      this.model1.greyscaleColor("green", "fakeFileName", "greyFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }

    try {
      this.model1.greyscaleColor("blue", "fakeFileName", "greyFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }

    try {
      // use a fake greyscale type with an image that does exist
      this.model1.greyscaleColor("fakeColor", "colorpic", "greyFail");
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
            this.model1.getImage("colorpic").toPPM());

    // greyscale the image using the red component
    this.model1.greyscaleColor("red", "colorpic", "colorpicred");

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
            this.model1.getImage("colorpicred").toPPM());
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
            this.model1.getImage("colorpic").toPPM());

    // greyscale the image using the green component
    this.model1.greyscaleColor("green", "colorpic", "colorpicgreen");

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
            this.model1.getImage("colorpicgreen").toPPM());

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
            this.model1.getImage("colorpic").toPPM());

    // greyscale the image using the blue component
    this.model1.greyscaleColor("blue", "colorpic", "colorpicblue");

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
            this.model1.getImage("colorpicblue").toPPM());
  }

  @Test
  public void changeBrightnessFail() {
    try {
      // use an image name that does not exist
      this.model1.changeBrightness(50, "fakeFileName", "brightnessFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }
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
            this.model1.getImage("colorpic").toPPM());

    // change brightness of the image using a positive value
    this.model1.changeBrightness(50, "colorpic", "colorpicbrightened");

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
            this.model1.getImage("colorpicbrightened").toPPM());
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
            this.model1.getImage("colorpic").toPPM());

    // change brightness of the image using a negative value
    this.model1.changeBrightness(-50, "colorpic", "colorpicbrightened");

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
            this.model1.getImage("colorpicbrightened").toPPM());
  }

  @Test
  public void flipFail() {
    try {
      // use an image name that does not exist
      this.model1.flip("vertical", "fakeFileName", "flipFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }
    try {
      // use an image name that does not exist
      this.model1.flip("horizontal", "fakeFileName", "flipFail");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image 'fakeFileName' does not exist!", e.getMessage());
    }
    try {
      // use an image name that does not exist
      this.model1.flip("fakeDirection", "colorpic", "flipFail");
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
            this.model1.getImage("colorpic").toPPM());

    // flip the image vertically
    this.model1.flip("vertical", "colorpic", "colorpicVertical");

    // show that the image has been flipped vertically
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "100\n50\n100\n" + "100\n200\n100\n" + "10\n50\n10\n" +
                    "255\n0\n0\n" + "0\n255\n0\n" + "0\n0\n255\n",
            this.model1.getImage("colorpicVertical").toPPM());
  }

  @Test
  public void flipSuccessHorizontal() {
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "255\n0\n0\n" + "0\n255\n0\n" + "0\n0\n255\n" +
                    "100\n50\n100\n" + "100\n200\n100\n" + "10\n50\n10\n",
            this.model1.getImage("colorpic").toPPM());

    // flip the image horizontally
    this.model1.flip("horizontal", "colorpic", "colorpicHorizontal");

    // show that the image has been flipped horizontally
    assertEquals("P3\n" +
                    "3\n" +
                    "2\n" +
                    "255\n" +
                    "0\n0\n255\n" + "0\n255\n0\n" + "255\n0\n0\n" +
                    "10\n50\n10\n" + "100\n200\n100\n" + "100\n50\n100\n",
            this.model1.getImage("colorpicHorizontal").toPPM());
  }

  @Test
  public void testBlur() {
    this.model1.filter("blur", "colorpic", "colorblur");
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
            "47\n", this.model1.getImage("colorblur").toPPM());
    this.model1.saveFile("colorblur", "res/colorblur.png");
  }

  @Test
  public void testSharpen() {
    this.model1.filter("sharpen", "colorpic", "colorsharpened");
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
            "86\n", this.model1.getImage("colorsharpened").toPPM());
    this.model1.saveFile("colorsharpened", "res/colorsharpened.png");
  }

  @Test
  public void testGreyscale() {
    this.model1.transformation("greyscale", "colorpic", "colortransform");
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
            "39\n", this.model1.getImage("colortransform").toPPM());
    this.model1.saveFile("colortransform", "res/colortransform.png");

    this.model1.greyscale("luma", "colorpic", "colorluma");

    // checks whether color transformation and luma work the same way
    assertEquals(this.model1.getImage("colorluma").toPPM(),
            this.model1.getImage("colortransform").toPPM());
  }

  @Test
  public void testSepia() {
    this.model1.transformation("sepia", "colorpic", "colorsepia");
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
            "31\n", this.model1.getImage("colorsepia").toPPM());
    this.model1.saveFile("colorsepia", "res/colorsepia.png");
  }
}