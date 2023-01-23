package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.IGUIModel;
import view.IGUIView;

/**
 * Implementation of the GUI image processing controller.
 * Contains fields that represent an image processor model and an image processor view.
 * The controller is meant to run the program using user provided inputs from the GUI.
 * The is then manipulated in the model and displayed through the view.
 */
public class GUIControllerImpl implements IGUIController {
  private IGUIModel model;
  private IGUIView view;

  /**
   * GUI constructor that initializes the model and view.
   *
   * @param model object that the controller has access to
   * @param view  object that the controller has access to
   * @throws IllegalArgumentException thrown if any parameters are null
   */
  public GUIControllerImpl(IGUIModel model, IGUIView view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("one or more provided parameters are null");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void callLoad(String name) {
    try {
      this.model.loadFile(name);
    } catch (IllegalArgumentException e) {
      this.view.renderMessage(e.getMessage());
      return;
    }
    this.view.renderMessage("Image successfully loaded.");
    this.view.placeImage(this.model.getImage());
  }

  @Override
  public void callSave(String fileLocation) {
    try {
      saveFile(fileLocation);
    } catch (IllegalArgumentException e) {
      this.view.renderMessage(e.getMessage());
      return;
    }
    this.view.renderMessage("Image successfully saved.");
    this.view.placeImage(this.model.getImage());
  }

  // save file of ppm, jpg, bmp, and jpeg format
  private void saveFile(String fileLocation)
          throws IllegalArgumentException {
    String type = fileLocation.substring(fileLocation.length() - 4);
    if (this.model.getImage() == null) {
      throw new IllegalArgumentException("no image loaded");
    }
    if (type.equalsIgnoreCase(".ppm")) {
      savePPM(fileLocation);
    } else if ((type.equalsIgnoreCase(".jpg")
            || type.equalsIgnoreCase(".png")
            || type.equalsIgnoreCase(".bmp")
            || type.equalsIgnoreCase("jpeg"))) {
      saveStandard(fileLocation);
    } else {
      throw new IllegalArgumentException("invalid image file format provided " +
              "(use either ppm, png, bmp, jpeg, or jpg)");
    }
  }

  // method is in controller because it uses ImageIO
  private void saveStandard(String fileLocation)
          throws IllegalArgumentException {
    try {
      ImageIO.write(this.model.getImage().toBuffered(), "png", new File(fileLocation));
    } catch (IOException e) {
      throw new IllegalArgumentException("File location '" + fileLocation + "' does not exist!");
    }
  }

  // saves the current image as a ppm to the given file location
  private void savePPM(String fileLocation) throws IllegalArgumentException {
    try {
      //append is set to false so that if a file already exists
      // at the file location it will be overwritten with the new image being saved
      FileWriter file = new FileWriter(new File(fileLocation), false);
      try {
        file.write(this.model.getImage().toPPM());
      } catch (NullPointerException ex) {
        throw new IllegalArgumentException("Image does not exist!");
      }
      file.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("File location '" + fileLocation + "' does not exist!");
    }
  }

  @Override
  public void callChangeBrightness(String type, int val) {
    if (this.model.getImage() == null) {
      this.view.renderMessage("No image loaded!");
      return;
    }
    if (type.equals("brighten")) {
      this.model.changeBrightness(val);
      this.view.renderMessage("Image successfully brightened by " + val + ".");
    } else if (type.equals("darken")) {
      this.model.changeBrightness(-val);
      this.view.renderMessage("Image successfully darkened by " + val + ".");
    }
    this.view.placeImage(this.model.getImage());
  }

  @Override
  public void callAction(String s) {
    String action = s.toLowerCase();
    if (action.equals("menu")) {
      this.view.renderListOfCommands();
    } else if (this.model.getImage() == null) {
      this.view.renderMessage("No image loaded!");
      return;
    }

    switch (action) {
      case "flip-vertical":
        this.model.flip("vertical");
        this.view.renderMessage("Image successfully flipped.");
        break;
      case "flip-horizontal":
        this.model.flip("horizontal");
        this.view.renderMessage("Image successfully flipped.");
        break;
      case "greyscale-value":
        this.model.greyscale("value");
        this.view.renderMessage("Image successfully greyscaled using value component.");
        break;
      case "greyscale-luma":
        this.model.greyscale("luma");
        this.view.renderMessage("Image successfully greyscaled using luma component.");
        break;
      case "greyscale-intensity":
        this.model.greyscale("intensity");
        this.view.renderMessage("Image successfully greyscaled using intensity component.");
        break;
      case "greyscale-red":
        this.model.greyscaleColor("red");
        this.view.renderMessage("Image successfully greyscaled using red component.");
        break;
      case "greyscale-green":
        this.model.greyscaleColor("green");
        this.view.renderMessage("Image successfully greyscaled using green component.");
        break;
      case "greyscale-blue":
        this.model.greyscaleColor("blue");
        this.view.renderMessage("Image successfully greyscaled using blue component.");
        break;
      case "filter-blur":
        this.model.filter("blur");
        this.view.renderMessage("Image successfully filtered by blur.");
        break;
      case "filter-sharpen":
        this.model.filter("sharpen");
        this.view.renderMessage("Image successfully filtered by sharpen.");
        break;
      case "transformation-greyscale":
        this.model.transformation("greyscale");
        this.view.renderMessage("Image successfully transformed by greyscale.");
        break;
      case "transformation-sepia":
        this.model.transformation("sepia");
        this.view.renderMessage("Image successfully transformed by sepia.");
        break;
      default:
        this.view.renderMessage("no action selected");
    }
    this.view.placeImage(this.model.getImage());
  }
}
