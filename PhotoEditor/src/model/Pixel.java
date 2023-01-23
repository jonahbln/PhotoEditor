package model;

/**
 * represents a single pixel contained in an image object.
 * Pixels are made up of a red, green, and blue component.
 * component values cannot be negative and must be less than or equal to 255.
 * This class does not require testing because all constructors and methods are protected.
 */
public class Pixel {
  // we chose to make these values private so that the user cannot mutate the pixel's data
  // the data can still be accessed in the image and model classes using the protected getters
  private final int red;
  private final int green;
  private final int blue;

  // constructs a new pixel using the given red, green, and blue values.
  // throws IllegalArgumentException if any of the values are less than 0 or greater than 255
  protected Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (Math.min(Math.min(red, green), blue) < 0 || Math.max(Math.max(red, green), blue) > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  // constructs a new pixel using a given formatted argb value
  // the argb value format is a four byte integer
  // bits 0-7 representing blue,
  // 8-15 represent green,
  // and 16-23 represents red
  // bits 23-31 would represent alpha values however for this implementation they are being ignored
  protected Pixel(int argb) {
    red = (argb >> 16) & 0xFF;
    green = (argb >> 8) & 0xFF;
    blue = (argb) & 0xFF;
  }

  // returns the red component of this pixel.
  protected int getRed() {
    return red;
  }

  // returns the green component of this pixel.
  protected int getGreen() {
    return green;
  }

  // returns the blue component of this pixel.
  protected int getBlue() {
    return blue;
  }

  // returns a formatted 3 byte rgb integer value of this pixel's color
  protected int getRGB() {
    return (red << 16) + (green << 8) + (blue);
  }
}