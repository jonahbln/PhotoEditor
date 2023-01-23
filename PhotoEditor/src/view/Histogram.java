package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Histogram class for an image that creates an array of pixel frequencies.
 */
public class Histogram extends JPanel {
  private int[][] frequencies;

  /**
   * Public constructor that initializes the frequencies.
   */
  public Histogram() {
    this.frequencies = null;
  }

  public void run(int[][] frequencies) {
    this.frequencies = frequencies;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (frequencies == null) {
      return;
    }
    super.paintComponent(g);
    Color[] colors = new Color[]{new Color(255, 0, 0, 60),
        new Color(0, 255, 0, 60),
        new Color(0, 0, 255, 60),
        new Color(65, 65, 65, 50)};
    Color current;
    int x = 5;
    int i = 3;
    int max = 0;
    int width = (int) (((getWidth()) / 255.0));

    for (int[] ints : frequencies) {
      for (int j : ints) {
        if (j > max) {
          max = j;
        }
      }
      current = colors[i];
      g.setColor(current);

      for (int j : ints) {
        int height = (int) ((((double) j / ((double) max) * (getHeight() - 10)) + .5));
        g.fillRect(x, getHeight() - 5 - height, width, height);

        x += width;
      }

      i--;
      x = 5;
      max = 0;
    }
  }
}