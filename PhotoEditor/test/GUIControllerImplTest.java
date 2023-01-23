import org.junit.Test;

import controller.GUIControllerImpl;
import controller.IGUIController;
import model.GUIModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the GUI controller test. Since this method does not interact
 * with the listeners call, there is limited testing. We choose not to use the features design,
 * so only an invalid constructor and the 4 public-facing method inputs can be tested.
 */
public class GUIControllerImplTest {
  private IGUIController controller;

  @Test
  public void testBrightness() {
    StringBuilder log = new StringBuilder();
    controller = new ControllerMock(log);
    controller.callChangeBrightness("darken", 10);
    assertEquals("Type: darken Val: 10", log.toString());

    log = new StringBuilder();
    controller = new ControllerMock(log);
    controller.callChangeBrightness("brighten", 21);
    assertEquals("Type: brighten Val: 21", log.toString());
  }

  @Test
  public void testAction() {
    StringBuilder log = new StringBuilder();
    controller = new ControllerMock(log);
    controller.callAction("flip");
    assertEquals("flip", log.toString());
  }

  @Test
  public void testLoad() {
    StringBuilder log = new StringBuilder();
    controller = new ControllerMock(log);
    controller.callLoad("res/colors.ppm");
    assertEquals("res/colors.ppm", log.toString());
  }

  @Test
  public void testSave() {
    StringBuilder log = new StringBuilder();
    controller = new ControllerMock(log);
    controller.callSave("res/colors.ppm");
    assertEquals("res/colors.ppm", log.toString());
  }

  @Test
  public void testInvalidInitialization() {
    try {
      this.controller = new GUIControllerImpl(null, new GUIModelImplTest.ViewMock());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("one or more provided parameters are null", e.getMessage());
    }
    try {
      this.controller = new GUIControllerImpl(new GUIModelImpl(), null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("one or more provided parameters are null", e.getMessage());
    }
  }

  // Mock class to test correct the correct method is called and that it is receiving the
  // correct input.
  private class ControllerMock implements IGUIController {
    private StringBuilder log;

    public ControllerMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void callChangeBrightness(String type, int val) {
      log.append("Type: " + type + " Val: " + val);
    }

    @Override
    public void callAction(String s) {
      log.append(s);
    }

    @Override
    public void callLoad(String name) {
      log.append(name);
    }

    @Override
    public void callSave(String name) {
      log.append(name);
    }
  }
}
