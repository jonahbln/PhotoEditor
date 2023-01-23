package model;

/**
 * represents an image processor model.
 * The model has various methods that can be used to load images from the system into this model
 * and manipulate their data, and get the current image at any point.
 */
public interface IGUIModel {
  /**
   * loads the file located at the given file name
   * An image must be loaded before any methods can be run on it.
   *
   * @param fileName the user-inputted file location containing a supported image file
   */
  void loadFile(String fileName) throws IllegalArgumentException;

  /**
   * turns the current image into it's greyscale equivalent based on the inputted component
   * here, 'value', 'luma' and 'intensity' are accepted greyscale components which will change the
   * calculations made when greyscaling the image.
   * The resulting image will be stored in this model.
   *
   * @param type the desire component to be used in the greyscale process
   * @throws IllegalArgumentException if the type is not 'value', 'luma' or 'intensity'
   */
  void greyscale(String type) throws IllegalArgumentException;

  /**
   * turns the current image into it's greyscale equivalent based on the inputted component
   * here, 'red', 'green' and 'blue' are accepted greyscale components which will change the
   * calculations made when greyscaling the image. The resulting image will be stored as
   * the current image.
   *
   * @param color the desired color component to be used in the greyscale process
   */
  void greyscaleColor(String color);

  /**
   * flips the current image in the given direction, either 'vertical' or 'horizontal',
   * and stores the resulting image with a new name in this model.
   *
   * @param direction the desired flip direction
   * @throws IllegalArgumentException if direction is not either 'vertical' or 'horizontal'
   */
  void flip(String direction) throws IllegalArgumentException;

  /**
   * changes the brightness of the current image by a given integer value.
   * A positive value will brighten each pixel by that amount.
   * A negative value will darken each pixel by the absolute value of that amount.
   * stores the resulting image in the model
   *
   * @param value the user-inputted value which the image's brightness will be changed by
   */
  void changeBrightness(int value);

  /**
   * returns the current image stored in the model.
   *
   * @return the loaded image
   */
  Image getImage();

  /**
   * Color transformation of a picture based on matrix values. Transforms each pixel based on it's
   * own values
   *
   * @param type of color transformation
   */
  void transformation(String type);

  /**
   * Gaussian blur by applying a filter to every channel of every pixel to produce the output image.
   *
   * @param type of the filter application
   */
  void filter(String type);
}
