package controller;

/**
 * Represents a controller for an image processor program.
 * The controller is in charge of running the program itself by
 * parsing user input,
 * accessing model methods,
 * and utilizing the view to output information back to the user.
 */
public interface IController {
  /**
   * Runs the program as a whole.
   */
  public void run();
}
