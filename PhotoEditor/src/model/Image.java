package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * represents a single image object. fields store the image's width and height,
 * along with the maximum rgb value contained in the image (usually 255)
 * pixels are arranged in a 2D array to represent the image itself
 */
public class Image {
  // we decided to make these three fields private, opting to use protected getter methods
  // so that the user cannot mutate the fields but the model methods can still retrieve the data
  private int height;
  private int width;
  private int max;
  private Pixel[][] img;

  // constructs an image pulled from the given file location
  // fileName is a user-inputted file path containing a supported image type
  protected Image(String fileName) throws IllegalArgumentException {
    String type = fileName.substring(fileName.length() - 4);
    if (type.equalsIgnoreCase(".ppm")) {
      createPPM(fileName);
    } else if (type.equalsIgnoreCase(".jpg")
            || type.equalsIgnoreCase(".png")
            || type.equalsIgnoreCase(".bmp")
            || type.equalsIgnoreCase("jpeg")) {
      createStandard(fileName);
    } else {
      throw new IllegalArgumentException("unsupported file format provided!");
    }
  }

  // constructs a blank image containing the given width, height, and maximum RGB value.
  // this is used in the model class when manipulating locally loaded images without
  // having to access files on the system
  protected Image(int width, int height, int max) {
    this.width = width;
    this.height = height;
    this.max = max;
    this.img = new Pixel[height][width];
  }

  private void createStandard(String filename) throws IllegalArgumentException {
    BufferedImage buffered;

    try {
      buffered = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    this.height = buffered.getHeight();
    this.width = buffered.getWidth();
    this.max = 255;

    this.img = new Pixel[height][width];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        img[j][i] = new Pixel(buffered.getRGB(i, j));
      }
    }
  }


  // uses the given image file on this system and converts its data into a 2D array
  // of pixel objects.
  private void createPPM(String filename) throws IllegalArgumentException {
    Scanner scan;

    try {
      scan = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (scan.hasNextLine()) {
      String s = scan.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    scan = new Scanner(builder.toString());

    String token;

    token = scan.next();

    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    this.width = scan.nextInt();
    this.height = scan.nextInt();
    this.max = scan.nextInt();

    this.img = new Pixel[height][width];

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();
        img[j][i] = new Pixel(r, g, b);
      }
    }
  }

  // returns the pixel at the given coordinates
  protected Pixel getPixelAt(int x, int y) {
    return img[y][x];
  }

  // sets the pixel at the given coordinates to a copy of the given pixel
  // creates a copy of the pixel so that the images are not pointing towards
  // the same pixel object but still contain identical data.
  protected void setPixelAt(int x, int y, Pixel p) {
    img[y][x] = new Pixel(p.getRed(), p.getGreen(), p.getBlue());
  }

  // returns the height of this image
  protected int getHeight() {
    return this.height;
  }

  // returns the width of this image
  protected int getWidth() {
    return this.width;
  }

  // returns the maximum RGB of this image
  protected int getMax() {
    return this.max;
  }

  /**
   * returns the number of pixels contained in this image.
   *
   * @return the number of pixels
   */
  public int getNumPixels() {
    return this.width * this.height;
  }

  /**
   * converts this image into the ASCII PPM format.
   * The ASCII PPM format is a simple way to represent images in a text-based format.
   * The file begins with 'P3', followed by the width and height of the image, and the maximum
   * RGB value of the image (usually 255). After this, a series of sets of three integers denote
   * each individual pixel and their RGB values in this image.
   *
   * @return the formatted PPM as a string
   */
  public String toPPM() {
    StringBuilder builder = new StringBuilder();
    builder.append(
            "P3\n"
                    + this.width + "\n"
                    + this.height + "\n"
                    + this.max + "\n");

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        builder.append(img[j][i].getRed() + "\n");
        builder.append(img[j][i].getGreen() + "\n");
        builder.append(img[j][i].getBlue() + "\n");
      }
    }
    return builder.toString();
  }

  /**
   * Return the RGB value of a given pixel.
   *
   * @param row   of pixel
   * @param col   of pixel
   * @param color RGB value
   * @return color value of given pixel
   */
  public double getValue(int col, int row, String color) {
    if (outOfBounds(row, col)) {
      return 0;
    }
    switch (color) {
      case "red":
        return img[row][col].getRed();
      case "green":
        return img[row][col].getGreen();
      case "blue":
        return img[row][col].getBlue();
      default:
        return 0;
    }
  }


  private boolean outOfBounds(int row, int col) {
    return (row < 0 || row >= height || col < 0 || col >= width);
  }

  /**
   * Converts this image object into a buffered image.
   *
   * @return a buffered image using the Java.awt library
   */
  public BufferedImage toBuffered() {
    BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        buffered.setRGB(i, j, img[j][i].getRGB());
      }
    }

    return buffered;
  }

  /**
   * Creates an array of an array of frequency values for each pixel of an Image.
   *
   * @return a 2D array of integers
   */
  public int[][] createHistogram() {
    int[] red = new int[256];
    int[] green = new int[256];
    int[] blue = new int[256];
    int[] avg = new int[256];
    int r;
    int g;
    int b;
    int a;
    Pixel current;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        current = img[i][j];
        r = current.getRed();
        g = current.getGreen();
        b = current.getBlue();
        a = (int) ((r + g + b) / 3 + .5);

        red[r]++;
        green[g]++;
        blue[b]++;
        avg[a]++;
      }
    }
    return new int[][]{avg, blue, green, red};
  }
}