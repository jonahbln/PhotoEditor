package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GUIControllerImpl;
import controller.IGUIController;
import model.GUIModelImpl;

/**
 * ImageProcessingGuiView class that extends JFrame. Creates a GUI using JLabels, JPanels, radio
 * buttons, and other JFrame built-in features. The class renders an interactive UI and is
 * updated with every action. A message is rendered to the user with every action.
 */
public class ImageProcessingGuiView extends JFrame implements ActionListener, IGUIView {
  private final IGUIController controller;
  private JLabel label;
  private final JPanel loadSaveIntroPanel;
  private ButtonGroup menu;
  private JLabel imageLabel;
  private JSpinner spinner;
  private Histogram histogram;
  private final JPanel menuPanel;

  /**
   * Creates the GUI that allows user interaction. All available commands are displayed.
   */
  public ImageProcessingGuiView() {
    super("Image Processor");
    this.controller = new GUIControllerImpl(new GUIModelImpl(), this);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout());

    label = new JLabel();
    imageLabel = new JLabel();
    loadSaveIntroPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(0, 40, 150, 100));
    histogram = new Histogram();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    menuPanel = new JPanel();
    imageScrollPane.setPreferredSize(new Dimension(780, 775));
    imageScrollPane.setMinimumSize(new Dimension(300, 350));
    imageScrollPane.setMaximumSize(new Dimension(780, 775));
    mainPanel.setLayout(new GridLayout(0, 1));
    mainScrollPane.setPreferredSize(new Dimension(780, 800));
    mainScrollPane.setMinimumSize(new Dimension(300, 350));
    mainScrollPane.setMaximumSize(new Dimension(780, 800));
    mainPanel.add(loadSaveIntroPanel);
    mainPanel.add(menuPanel);
    mainPanel.add(histogram);
    this.add(mainScrollPane);
    this.add(imageScrollPane);
    setUp();

    pack();

    this.setVisible(true);
  }

  private void setUp() {
    renderIntro();
    buttonsSetUp();
    commandButtonSetup();
    pack();
  }

  public void placeImage(model.Image image) {
    imageLabel.setIcon(new ImageIcon(image.toBuffered()));
    histogram.run(image.createHistogram());
  }

  private void commandButtonSetup() {
    JRadioButton[] buttons = new JRadioButton[14];
    menu = new ButtonGroup();
    buttons[0] = new JRadioButton("brighten", false);
    buttons[0].setActionCommand("brighten");
    buttons[1] = new JRadioButton("darken", false);
    buttons[1].setActionCommand("darken");
    buttons[2] = new JRadioButton("flip-horizontal", false);
    buttons[2].setActionCommand("flip-horizontal");
    buttons[3] = new JRadioButton("flip-vertical", false);
    buttons[3].setActionCommand("flip-vertical");
    buttons[4] = new JRadioButton("greyscale-value", false);
    buttons[4].setActionCommand("greyscale-value");
    buttons[5] = new JRadioButton("greyscale-luma", false);
    buttons[5].setActionCommand("greyscale-luma");
    buttons[6] = new JRadioButton("greyscale-intensity", false);
    buttons[6].setActionCommand("greyscale-intensity");
    buttons[7] = new JRadioButton("greyscale-red", false);
    buttons[7].setActionCommand("greyscale-red");
    buttons[8] = new JRadioButton("greyscale-green", false);
    buttons[8].setActionCommand("greyscale-green");
    buttons[9] = new JRadioButton("greyscale-blue", false);
    buttons[9].setActionCommand("greyscale-blue");
    buttons[10] = new JRadioButton("blur", false);
    buttons[10].setActionCommand("filter-blur");
    buttons[11] = new JRadioButton("sharpen", false);
    buttons[11].setActionCommand("filter-sharpen");
    buttons[12] = new JRadioButton("greyscale", false);
    buttons[12].setActionCommand("transformation-greyscale");
    buttons[13] = new JRadioButton("sepia", false);
    buttons[13].setActionCommand("transformation-sepia");
    JButton enter = new JButton("enter");
    spinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 5));
    menuPanel.setLayout(new GridLayout(0, 2));
    for (JRadioButton b : buttons) {
      menu.add(b);
      menuPanel.add(b);
    }
    enter.addActionListener(this);
    menuPanel.add(enter);
    menuPanel.add(spinner);
  }

  private void renderIntro() {
    label = new JLabel("<html>Welcome to the Image Processor!<br/>" +
            "If this is your first time, press 'help' for instructions!<br/>" +
            "Scroll to the left/right to see all commands and the whole picture.<html>");
    loadSaveIntroPanel.add(label);
  }

  private void buttonsSetUp() {
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);

    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);

    JButton menuButton = new JButton("help");
    menuButton.setActionCommand("menu");
    menuButton.addActionListener(this);

    loadSaveIntroPanel.add(fileOpenButton);
    loadSaveIntroPanel.add(fileSaveButton);
    loadSaveIntroPanel.add(menuButton);
  }

  /**
   * Method from ActionListener that is called every time an actionCommand is set. Transmits data
   * to the controller to be manipulated by the view.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file":
        final JFileChooser fchooser = new JFileChooser("res");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(this);
        File f = null;
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          f = fchooser.getSelectedFile();
          //   fileOpenDisplay.setText(f.getAbsolutePath());
        }
        try {
          this.controller.callLoad(f.getPath());
        } catch (NullPointerException ex) {
          renderMessage("no file selected");
        }
        break;
      case "Save file":
        final JFileChooser fsaver = new JFileChooser("res");
        retvalue = fsaver.showSaveDialog(this);
        f = null;
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          f = fsaver.getSelectedFile();
        }
        try {
          this.controller.callSave(f.getPath());
        } catch (NullPointerException ex) {
          renderMessage("no file selected");
        }
        break;
      case "menu":
        renderListOfCommands();
        break;
      case "enter":
        if (menu.getSelection().getActionCommand().equalsIgnoreCase("brighten")) {
          int val = (int) spinner.getValue();
          this.controller.callChangeBrightness("brighten", val);
          if (val == 0) {
            renderMessage("Enter a positive value to brighten by in the spinner");
          }
          spinner.setValue(0);
        } else if (menu.getSelection().getActionCommand().equalsIgnoreCase("darken")) {
          int val = (int) spinner.getValue();
          this.controller.callChangeBrightness("darken", val);
          if (val == 0) {
            renderMessage("Enter a positive value to darken by in the spinner");
          }
          spinner.setValue(0);
        } else {
          String action = menu.getSelection().getActionCommand();
          this.controller.callAction(action);
        }
        break;
      default:
        renderMessage("No action selected");
    }
  }

  @Override
  public void renderMessage(String s) {
    loadSaveIntroPanel.remove(0);
    label.removeAll();
    label = new JLabel(s);
    label.setForeground(Color.red);
    loadSaveIntroPanel.add(label, 0);
    label.updateUI();
    loadSaveIntroPanel.repaint();
  }

  @Override
  public void renderListOfCommands() {
    this.renderMessage(
            "<html>To use this program, start by pressing<br/>" +
                    " 'open a file' and selecting an image from your computer.<br/>" +
                    "The image will be loaded and displayed on your screen.<br/>" +
                    "Then, use the buttons below to select an action to perform.<br/>" +
                    "NOTE - When choosing 'brighten' or darken',<br/>" +
                    "You must enter an int between 0-255 which determines the value<br/>" +
                    "that the image's brightness will be changed by.<br/>" +
                    "All changes made to the loaded image will be reflected<br/>" +
                    "on your screen, and changes will be compounded.<br/>" +
                    "At any time you can press 'save a file' and navigate<br/>" +
                    "to the location on your computer you would like<br/>" +
                    "to save the current image to.<br/>" +
                    "NOTE - You must include the file type when saving<br/>" +
                    "an image (ex. '.jpg' or '.png').<br/>" +
                    "You may press 'help' at any time to return to this menu.<br/></html>");
  }
}