package model;

/**
 * represents an image processor model.
 * The model has various methods that can be used to load images from the system into this model
 * and manipulate their data,
 * with the ability to save the new images to the system at any point.
 */
public interface IModel {
  /**
   * loads the file located at the given file name and
   * saves it to this model to be referred to the given save name from now on.
   * An image must be loaded before any methods can be run on it.
   *
   * @param fileName the user-inputted file location containing a supported image file
   * @param saveName the user-inputted save name which will now be associated with the loaded image
   */
  void loadFile(String fileName, String saveName);

  /**
   * Saves a locally stored image in this model to the system at a user-inputted file path.
   *
   * @param fileName the file path where the image will be saved
   * @param oldName  the name of the image within this model that the user wants to save
   * @throws IllegalArgumentException if the given oldName does not point to
   *                                  a valid existing loaded image
   */
  void saveFile(String oldName, String fileName) throws IllegalArgumentException;

  /**
   * turns a given loaded image into it's greyscale equivalent based on the inputted component
   * here, 'value', 'luma' and 'intensity' are accepted greyscale components which will change the
   * calculations made when greyscaling the image.
   * The resulting image will be saved using a new name and stored in this model.
   *
   * @param type    the desire component to be used in the greyscale process
   * @param oldName the name of the existing loaded image in this model
   * @param newName the desired new name of the resulting greyscale
   * @throws IllegalArgumentException if the given oldName does not point to
   *                                  a valid existing loaded image
   */
  void greyscale(String type, String oldName, String newName) throws IllegalArgumentException;

  /**
   * turns a given loaded image into it's greyscale equivalent based on the inputted component
   * here, 'red', 'green' and 'blue' are accepted greyscale components which will change the
   * calculations made when greyscaling the image.
   * The resulting image will be saved using a new name and stored in this model.
   *
   * @param color   the desired color component to be used in the greyscale process
   * @param oldName the name of the existing loaded image in this model
   * @param newName the desired new name of the resulting greyscale
   * @throws IllegalArgumentException if the given oldName does not point to
   *                                  a valid existing loaded image
   */
  void greyscaleColor(String color, String oldName, String newName)
          throws IllegalArgumentException;

  /**
   * flips a given loaded image in the given direction, either 'vertical' or 'horizontal',
   * and stores the resulting image with a new name in this model.
   *
   * @param direction the desired flip direction
   * @param oldName   the name of the existing image to be flipped
   * @param newName   the desired new name of the resulting flipped image
   * @throws IllegalArgumentException if the given oldName does not point to
   *                                  a valid existing loaded image
   */
  void flip(String direction, String oldName, String newName) throws IllegalArgumentException;

  /**
   * changes the brightness of a given loaded image by a given integer value.
   * A positive value will brighten each pixel by that amount.
   * A negative value will darken each pixel by the absolute value of that amount.
   * stores the resulting image with a new name in this model.
   *
   * @param value   the user-inputted value which the image's brightness will be changed by
   * @param oldName the name of the existing image
   * @param newName the desired new name of the resulting image
   * @throws IllegalArgumentException if the given oldName does not point to
   *                                  a valid existing loaded image
   */
  void changeBrightness(int value, String oldName, String newName) throws IllegalArgumentException;

  /**
   * returns the image stored in this model that is associated with the given name.
   *
   * @param name the name of the image requested to be returned
   * @return the loaded image associated with name
   */
  Image getImage(String name);

  /**
   * Color transformation of a picture based on matrix values. Transforms each pixel based on it's
   * own values
   *
   * @param type    of color transformation
   * @param oldName the name of the existing image
   * @param newName the desired new name of the resulting image
   */
  void transformation(String type, String oldName, String newName);

  /**
   * Gaussian blur by applying a filter to every channel of every pixel to produce the output image.
   *
   * @param oldName the name of the existing image
   * @param newName the desired new name of the resulting image
   */
  void filter(String type, String oldName, String newName);
}
