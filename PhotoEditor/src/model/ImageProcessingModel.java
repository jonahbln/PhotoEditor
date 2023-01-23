package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * represents an image processor model.
 * The model has various methods that can be used to load images from the system into this model
 * and manipulate their data,
 * with the ability to save the new images to the system at any point.
 * A hash map is used to locally store images and associate them with user-inputted names.
 */

public class ImageProcessingModel implements IModel {
  private Map<String, Image> imageMap;

  /**
   * constructs a new model with a new empty default view object.
   * the map of images is initialized as an empty hashmap.
   */

  public ImageProcessingModel() {
    imageMap = new HashMap<String, Image>();
  }

  @Override
  public void loadFile(String fileName, String saveName) {
    imageMap.put(saveName, new Image(fileName));
  }

  // save file of ppm, jpg, bmp, and jpeg format
  @Override
  public void saveFile(String oldName, String fileLocation)
          throws IllegalArgumentException {
    String type = fileLocation.substring(fileLocation.length() - 4);
    if (type.equalsIgnoreCase(".ppm")) {
      savePPM(oldName, fileLocation);
    } else if ((type.equalsIgnoreCase(".jpg")
            || type.equalsIgnoreCase(".png")
            || type.equalsIgnoreCase(".bmp")
            || type.equalsIgnoreCase("jpeg"))) {
      saveStandard(oldName, fileLocation);
    } else {
      throw new IllegalArgumentException("invalid image file format provided " +
              "(use either ppm, png, bmp, jpeg, or jpg)");
    }
  }

  private void saveStandard(String oldName, String fileLocation)
          throws IllegalArgumentException {
    try {
      ImageIO.write(getImage(oldName).toBuffered(), "png", new File(fileLocation));
    } catch (IOException e) {
      throw new IllegalArgumentException("File location '" + fileLocation + "' does not exist!");
    }
  }

  private void savePPM(String oldName, String fileLocation) throws IllegalArgumentException {
    try {
      //append is set to false so that if a file already exists
      // at the file location it will be overwritten with the new image being saved
      FileWriter file = new FileWriter(new File(fileLocation), false);
      try {
        file.write(this.imageMap.get(oldName).toPPM());
      } catch (NullPointerException ex) {
        throw new IllegalArgumentException("Image '" + oldName + "' does not exist!");
      }
      file.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("File location '" + fileLocation + "' does not exist!");
    }
  }

  @Override
  public void greyscale(String type, String oldName, String newName)
          throws IllegalArgumentException {
    initNewName(oldName, newName);

    type = type.toLowerCase();
    int val;
    for (int i = 0; i < imageMap.get(oldName).getWidth(); i++) {
      for (int j = 0; j < imageMap.get(oldName).getHeight(); j++) {
        Pixel toAdd;
        switch (type) {
          case "value":
            val = Math.max(Math.max(imageMap.get(oldName).getPixelAt(i, j).getRed(),
                            imageMap.get(oldName).getPixelAt(i, j).getGreen()),
                    imageMap.get(oldName).getPixelAt(i, j).getBlue());

            break;
          case "intensity":
            val = (imageMap.get(oldName).getPixelAt(i, j).getRed() +
                    imageMap.get(oldName).getPixelAt(i, j).getGreen()
                    + imageMap.get(oldName).getPixelAt(i, j).getBlue()) / 3;
            break;
          case "luma":
            val = (int) ((0.2126 * imageMap.get(oldName).getPixelAt(i, j).getRed() +
                    (0.7152 * imageMap.get(oldName).getPixelAt(i, j).getGreen() +
                            (0.0722 * imageMap.get(oldName).getPixelAt(i, j).getBlue()))) + .5);
            break;
          default:
            throw new IllegalArgumentException("Try again with a valid type " +
                    "('value', 'intensity', or 'luma')");
        }
        toAdd = new Pixel(val, val, val);
        imageMap.get(newName).setPixelAt(i, j, toAdd);
      }
    }
  }

  @Override
  public void greyscaleColor(String color, String oldName, String newName)
          throws IllegalArgumentException {
    initNewName(oldName, newName);

    color = color.toLowerCase();
    int toSet;
    Pixel toAdd;
    for (int i = 0; i < imageMap.get(oldName).getWidth(); i++) {
      for (int j = 0; j < imageMap.get(oldName).getHeight(); j++) {
        toSet = greyscaleColorHelper(imageMap.get(oldName).getPixelAt(i, j), color);
        toAdd = new Pixel(toSet, toSet, toSet);
        imageMap.get(newName).setPixelAt(i, j, toAdd);
      }
    }
  }

  // returns the correct color value
  private int greyscaleColorHelper(Pixel p, String color) {
    switch (color) {
      case "red":
        return p.getRed();
      case "blue":
        return p.getBlue();
      case "green":
        return p.getGreen();
      default:
        throw new IllegalArgumentException("Try again with a valid color " +
                "('red', 'green', or 'blue')");
    }
  }

  @Override
  public void changeBrightness(int value, String oldName, String newName)
          throws IllegalArgumentException {
    initNewName(oldName, newName);

    for (int i = 0; i < imageMap.get(oldName).getWidth(); i++) {
      for (int j = 0; j < imageMap.get(oldName).getHeight(); j++) {
        Pixel toAdd = new Pixel(
                Math.min(Math.max(imageMap.get(oldName).getPixelAt(i, j).getRed() + value, 0),
                        255),
                Math.min(Math.max(imageMap.get(oldName).getPixelAt(i, j).getGreen() + value, 0),
                        255),
                Math.min(Math.max(imageMap.get(oldName).getPixelAt(i, j).getBlue() + value, 0),
                        255));
        imageMap.get(newName).setPixelAt(i, j, toAdd);
      }
    }
  }

  @Override
  public void flip(String direction, String oldName, String newName)
          throws IllegalArgumentException {
    initNewName(oldName, newName);

    direction = direction.toLowerCase();
    imageMap.put(newName, new Image(imageMap.get(oldName).getWidth(),
            imageMap.get(oldName).getHeight(), imageMap.get(oldName).getMax()));

    for (int j = 0; j < imageMap.get(oldName).getHeight(); j++) {
      for (int i = 0; i < imageMap.get(oldName).getWidth(); i++) {
        switch (direction) {
          case "vertical":
            imageMap.get(newName).setPixelAt(i, j, imageMap.get(oldName).
                    getPixelAt(i, imageMap.get(oldName).getHeight() - 1 - j));
            break;
          case "horizontal":
            imageMap.get(newName).setPixelAt(i, j, imageMap.get(oldName).
                    getPixelAt(imageMap.get(oldName).getWidth() - 1 - i, j));
            break;
          default:
            throw new IllegalArgumentException("try again with a valid direction" +
                    "('horizontal', 'vertical')");
        }
      }
    }
  }

  @Override
  public void transformation(String type, String oldName, String newName) {
    Double[][] transform;
    double red;
    double green;
    double blue;
    Pixel toAdd;

    imageMap.put(newName, new Image(imageMap.get(oldName).getWidth(),
            imageMap.get(oldName).getHeight(), imageMap.get(oldName).getMax()));

    for (int i = 0; i < imageMap.get(oldName).getWidth(); i++) {
      for (int j = 0; j < imageMap.get(oldName).getHeight(); j++) {
        if (type.equalsIgnoreCase("greyscale")) {
          transform = setGreyscale();
        } else {
          transform = setSepia();
        }
        red = getColorCombo(type, imageMap.get(oldName).getPixelAt(i, j), "red", transform);
        green = getColorCombo(type, imageMap.get(oldName).getPixelAt(i, j), "green", transform);
        blue = getColorCombo(type, imageMap.get(oldName).getPixelAt(i, j), "blue", transform);

        toAdd = new Pixel(rgb(red), rgb(green), rgb(blue));
        imageMap.get(newName).setPixelAt(i, j, toAdd);
      }
    }
  }

  private Double[][] setGreyscale() {
    Double[][] greyscaleKernel = new Double[3][3];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          greyscaleKernel[j][i] = 0.2126;
        } else if (j == 1) {
          greyscaleKernel[j][i] = 0.7152;
        } else {
          greyscaleKernel[j][i] = 0.0722;
        }
      }
    }
    return greyscaleKernel;
  }

  private Double[][] setSepia() {
    Double[][] sepiaKernel = new Double[3][3];

    sepiaKernel[0][0] = 0.393;
    sepiaKernel[0][1] = 0.349;
    sepiaKernel[0][2] = 0.272;
    sepiaKernel[1][0] = 0.769;
    sepiaKernel[1][1] = 0.686;
    sepiaKernel[1][2] = 0.534;
    sepiaKernel[2][0] = 0.189;
    sepiaKernel[2][1] = 0.168;
    sepiaKernel[2][2] = 0.131;

    return sepiaKernel;
  }

  private double getColorCombo(String type, Pixel p, String color, Double[][] transform) {
    switch (color.toLowerCase()) {
      case ("red"):
        return transform[0][0] * p.getRed()
                + transform[1][0] * p.getGreen()
                + transform[2][0] * p.getBlue();

      case ("green"):
        return transform[0][1] * p.getRed()
                + transform[1][1] * p.getGreen()
                + transform[2][1] * p.getBlue();
      case ("blue"):
        return transform[0][2] * p.getRed()
                + transform[1][2] * p.getGreen()
                + transform[2][2] * p.getBlue();
      default:
        return -1;
    }
  }

  @Override
  public void filter(String type, String oldName, String newName) {
    imageMap.put(newName, new Image(imageMap.get(oldName).getWidth(),
            imageMap.get(oldName).getHeight(), imageMap.get(oldName).getMax()));

    double red = 0;
    double green = 0;
    double blue = 0;

    // create filter first to speed up runtime
    Double[][] filter;
    if (type.equalsIgnoreCase("blur")) {
      filter = setBlur();
    } else {
      filter = setSharpen();
    }

    for (int i = 0; i < imageMap.get(oldName).getWidth(); i++) {
      for (int j = 0; j < imageMap.get(oldName).getHeight(); j++) {
        if (type.equalsIgnoreCase("blur")) {
          red = surroundingBlurPixels(oldName, i, j, "red", filter);
          green = surroundingBlurPixels(oldName, i, j, "green", filter);
          blue = surroundingBlurPixels(oldName, i, j, "blue", filter);
        } else if (type.equalsIgnoreCase("sharpen")) {
          red = surroundingSharpenImage(oldName, i, j, "red", filter);
          green = surroundingSharpenImage(oldName, i, j, "green", filter);
          blue = surroundingSharpenImage(oldName, i, j, "blue", filter);
        }

        Pixel toAdd = new Pixel(rgb(red), rgb(green), rgb(blue));
        imageMap.get(newName).setPixelAt(i, j, toAdd);

        red = 0;
        green = 0;
        blue = 0;
      }
    }
  }

  private double surroundingBlurPixels(String oldName, int i, int j, String color,
                                       Double[][] filter) {
    int kernelRow = 0;
    int kernelCol = 0;
    double sum = 0;

    for (int a = i - 1; a <= i + 1; a++) {
      for (int b = j - 1; b <= j + 1; b++) {
        sum += getAdd(oldName, a, b, kernelRow, kernelCol, color, "blur", filter);
        kernelRow += 1;
      }
      kernelCol += 1;
      kernelRow = 0;
    }
    return sum;
  }

  private double surroundingSharpenImage(String oldName, int i, int j, String color,
                                         Double[][] filter) {
    int kernelRow = 0;
    int kernelCol = 0;
    double sum = 0;

    for (int a = i - 2; a <= i + 2; a++) {
      for (int b = j - 2; b <= j + 2; b++) {
        sum += getAdd(oldName, a, b, kernelRow, kernelCol, color, "sharpen", filter);
        kernelRow += 1;
      }
      kernelCol += 1;
      kernelRow = 0;
    }
    return sum;
  }

  // multiple RBG values by kernel values
  private double getAdd(String oldName, int row, int col, int typeRow, int typeCol,
                        String color, String type, Double[][] filter) {
    switch (color.toLowerCase()) {
      case "red":
        return imageMap.get(oldName).getValue(row, col, "red") *
                filter[typeRow][typeCol];
      case "green":
        return imageMap.get(oldName).getValue(row, col, "green") *
                filter[typeRow][typeCol];
      case "blue":
        return imageMap.get(oldName).getValue(row, col, "blue") *
                filter[typeRow][typeCol];
      default:
        return -1;
    }
  }


  // create blur kernel
  private Double[][] setBlur() {
    Double[][] blurKernel = new Double[3][3];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i == 0 || i == 2) && (j == 0 || j == 2)) {
          blurKernel[j][i] = 1.0 / 16;
        } else if (i == 1 && j == 1) {
          blurKernel[j][i] = 1.0 / 4;
        } else {
          blurKernel[j][i] = 1.0 / 8;
        }
      }
    }
    return blurKernel;
  }

  // create sharpen kernel
  private Double[][] setSharpen() {
    Double[][] sharpenKernel = new Double[5][5];

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (i == 0 || j == 0 || i == 4 || j == 4) {
          sharpenKernel[j][i] = -1.0 / 8;
        } else if (i == 2 && j == 2) {
          sharpenKernel[j][i] = 1.0;
        } else {
          sharpenKernel[j][i] = 1.0 / 4;
        }
      }
    }
    return sharpenKernel;
  }

  private int rgb(double val) {
    return (int) (Math.max(Math.min(val, 255), 0) + 0.5);
  }

  @Override
  public Image getImage(String key) {
    return imageMap.get(key);
  }

  // attempts to initialize a new image in the hashmap using the given new name
  // If oldName is the name of a loaded image, then the newName image will be created
  // with the same height, width, and max value as the old one.
  // If oldName is not the name of an existing loaded image, illegal argument exception is thrown
  private void initNewName(String oldName, String newName) throws IllegalArgumentException {
    try {
      imageMap.put(newName, new Image(imageMap.get(oldName).getWidth(),
              imageMap.get(oldName).getHeight(), imageMap.get(oldName).getMax()));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Image '" + oldName + "' does not exist!");
    }
  }
}