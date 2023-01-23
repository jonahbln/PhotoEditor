package model;

/**
 * represents an image processor model.
 * The model has various methods that can be used to load images from the system into this model
 * manipulate their data, and store the current image.
 */
public class GUIModelImpl implements IGUIModel {
  private Image currentImage;

  /**
   * constructs a new model and initializes the currentImage.
   */
  public GUIModelImpl() {
    currentImage = null;
  }

  @Override
  public void loadFile(String fileName) throws IllegalArgumentException {
    currentImage = new Image(fileName);
  }

  @Override
  public void greyscale(String type) throws IllegalArgumentException {
    Image temp = new Image(
            currentImage.getWidth(), currentImage.getHeight(), currentImage.getMax());
    int val;
    for (int i = 0; i < currentImage.getWidth(); i++) {
      for (int j = 0; j < currentImage.getHeight(); j++) {
        Pixel toAdd;
        switch (type) {
          case "value":
            val = Math.max(Math.max(currentImage.getPixelAt(i, j).getRed(),
                            currentImage.getPixelAt(i, j).getGreen()),
                    currentImage.getPixelAt(i, j).getBlue());

            break;
          case "intensity":
            val = (currentImage.getPixelAt(i, j).getRed() +
                    currentImage.getPixelAt(i, j).getGreen()
                    + currentImage.getPixelAt(i, j).getBlue()) / 3;
            break;
          case "luma":
            val = (int) ((0.2126 * currentImage.getPixelAt(i, j).getRed() +
                    (0.7152 * currentImage.getPixelAt(i, j).getGreen() +
                            (0.0722 * currentImage.getPixelAt(i, j).getBlue()))) + .5);
            break;
          default:
            throw new IllegalArgumentException("Try again with a valid type " +
                    "('value', 'intensity', or 'luma')");
        }
        toAdd = new Pixel(val, val, val);
        temp.setPixelAt(i, j, toAdd);
      }
    }
    currentImage = temp;
  }

  @Override
  public void greyscaleColor(String color) {
    Image temp = new Image(
            currentImage.getWidth(), currentImage.getHeight(), currentImage.getMax());
    int toSet;
    Pixel toAdd;
    for (int i = 0; i < currentImage.getWidth(); i++) {
      for (int j = 0; j < currentImage.getHeight(); j++) {
        toSet = greyscaleColorHelper(currentImage.getPixelAt(i, j), color);
        toAdd = new Pixel(toSet, toSet, toSet);
        temp.setPixelAt(i, j, toAdd);
      }
    }
    currentImage = temp;
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
  public void changeBrightness(int value) {
    Image temp = new Image(
            currentImage.getWidth(), currentImage.getHeight(), currentImage.getMax());
    for (int i = 0; i < currentImage.getWidth(); i++) {
      for (int j = 0; j < currentImage.getHeight(); j++) {
        Pixel toAdd = new Pixel(
                Math.min(Math.max(currentImage.getPixelAt(i, j).getRed() + value, 0),
                        255),
                Math.min(Math.max(currentImage.getPixelAt(i, j).getGreen() + value, 0),
                        255),
                Math.min(Math.max(currentImage.getPixelAt(i, j).getBlue() + value, 0),
                        255));
        temp.setPixelAt(i, j, toAdd);
      }
    }
    currentImage = temp;
  }


  @Override
  public void flip(String direction)
          throws IllegalArgumentException {
    Image temp = new Image(
            currentImage.getWidth(), currentImage.getHeight(), currentImage.getMax());
    for (int j = 0; j < currentImage.getHeight(); j++) {
      for (int i = 0; i < currentImage.getWidth(); i++) {
        switch (direction) {
          case "vertical":
            temp.setPixelAt(i, j, currentImage.
                    getPixelAt(i, currentImage.getHeight() - 1 - j));

            break;
          case "horizontal":
            temp.setPixelAt(i, j, currentImage.
                    getPixelAt(currentImage.getWidth() - 1 - i, j));
            break;
          default:
            throw new IllegalArgumentException("try again with a valid direction" +
                    "('horizontal', 'vertical')");
        }
      }
    }
    currentImage = temp;
  }

  @Override
  public void transformation(String type) {
    Image temp = new Image(
            currentImage.getWidth(), currentImage.getHeight(), currentImage.getMax());
    Double[][] transform;
    double red;
    double green;
    double blue;
    Pixel toAdd;

    for (int i = 0; i < currentImage.getWidth(); i++) {
      for (int j = 0; j < currentImage.getHeight(); j++) {
        if (type.equalsIgnoreCase("greyscale")) {
          transform = setGreyscale();
        } else {
          transform = setSepia();
        }
        red = getColorCombo(type, currentImage.getPixelAt(i, j), "red", transform);
        green = getColorCombo(type, currentImage.getPixelAt(i, j), "green", transform);
        blue = getColorCombo(type, currentImage.getPixelAt(i, j), "blue", transform);

        toAdd = new Pixel(rgb(red), rgb(green), rgb(blue));
        temp.setPixelAt(i, j, toAdd);
      }
    }
    currentImage = temp;
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
  public void filter(String type) {
    Image temp = new Image(
            currentImage.getWidth(), currentImage.getHeight(), currentImage.getMax());
    double red = 0;
    double green = 0;
    double blue = 0;

    Double[][] filter;
    // create first to cut down runtime
    if (type.equalsIgnoreCase("blur")) {
      filter = setBlur();
    } else {
      filter = setSharpen();
    }

    for (int i = 0; i < currentImage.getWidth(); i++) {
      for (int j = 0; j < currentImage.getHeight(); j++) {
        if (type.equalsIgnoreCase("blur")) {
          red = surroundingBlurPixels(i, j, "red", filter);
          green = surroundingBlurPixels(i, j, "green", filter);
          blue = surroundingBlurPixels(i, j, "blue", filter);
        } else if (type.equalsIgnoreCase("sharpen")) {
          red = surroundingSharpenImage(i, j, "red", filter);
          green = surroundingSharpenImage(i, j, "green", filter);
          blue = surroundingSharpenImage(i, j, "blue", filter);
        }

        Pixel toAdd = new Pixel(rgb(red), rgb(green), rgb(blue));
        temp.setPixelAt(i, j, toAdd);

        red = 0;
        green = 0;
        blue = 0;
      }
    }
    currentImage = temp;
  }

  private double surroundingBlurPixels(int i, int j, String color, Double[][] filter) {
    int kernelRow = 0;
    int kernelCol = 0;
    double sum = 0;

    for (int a = i - 1; a <= i + 1; a++) {
      for (int b = j - 1; b <= j + 1; b++) {
        sum += getAdd(a, b, kernelRow, kernelCol, color, "blur", filter);
        kernelRow += 1;
      }
      kernelCol += 1;
      kernelRow = 0;
    }
    return sum;
  }

  private double surroundingSharpenImage(int i, int j, String color, Double[][] filter) {
    int kernelRow = 0;
    int kernelCol = 0;
    double sum = 0;

    for (int a = i - 2; a <= i + 2; a++) {
      for (int b = j - 2; b <= j + 2; b++) {
        sum += getAdd(a, b, kernelRow, kernelCol, color, "sharpen", filter);
        kernelRow += 1;
      }
      kernelCol += 1;
      kernelRow = 0;
    }
    return sum;
  }

  // multiple RBG values by kernel values
  private double getAdd(int row, int col, int typeRow, int typeCol,
                        String color, String type, Double[][] filter) {
    switch (color.toLowerCase()) {
      case "red":
        return currentImage.getValue(row, col, "red") *
                filter[typeRow][typeCol];
      case "green":
        return currentImage.getValue(row, col, "green") *
                filter[typeRow][typeCol];
      case "blue":
        return currentImage.getValue(row, col, "blue") *
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
  public Image getImage() {
    return currentImage;
  }
}
