# PhotoEditorGUI

!!!!!!!!!!!!!!!!!
The lasagna photo was taken from a recipe below
https://www.hy-vee.com/recipes-ideas/recipes/classic-lasagna-from-seasons
!!!!!!!!!!!!!!!!!

For our design we created three primary interfaces:
'IGUIModel'
'IGUIView'
'IGUIController'

Each of these interfaces are implemented by a class:
'GUIModelImpl'
'ImageProcessingGuiView'
'GUIFeaturesImpl'

Additionally, a main class with a main method has been included and named 'PhotoEditorGUI'.

To run the main method, create a run configuration with one of the 3 command-line inputs.

1. No input: this will run the GUI as normal.
2. File-based input: enter '-file' 'path-of-script-file.txt'
   (ex. -file res/SCRIPT.txt)
3. Console-based input: enter '-text' followed by the list of commands.
   (ex. -text load res/colors.ppm colorpic flip colorpic colorflipped vertical
   save colorflipped res/colorflipped.jpg)

The purpose of these classes is pretty straight-forward:

The GUIModelImpl is in charge of handling image data.
It is within this class that the current Image is saved and manipulated.
The model contains all the methods for image processing methods the user can call.
The user likely will never interact with the model implementation itself.
In pt.2, we added functionality for loading and saving standard image format files,
we also added the ability to blur, sharpen, sepia, and grayscale by color transformations.
In pt.3, instead of using a hashmap, we created a private Image field that stored the current
image. Any command can be called on this image, and the resulting new image replaces the current
image.
All assignment requirements were filed AND all functionality from the previous assignment still
functions unchanged!

The model package also contains our custom Image and Pixel classes.
The pixel class simply stores a red, green, and blue value between 0-255 and has protected
getter methods setup to retrieve these values for use in other model methods.
In pt. 2, we added support for INT_RGB format in Pixels by making a second constructor that takes
in an RGB value that splits the value into the three red, green, blue values that the model uses.
Additionally, a getter method was made to turn the three values back into a single RGB int
and return the result.
In pt. 3, we did not have to change the Pixel class.

The image class is our way of representing image data in a simplified way. Here, a 2D array
of pixel objects is used to represent the image itself, while a width, height, and max value
are also included for convenience. An image can be created either with a filename or with a width,
height, and max which will make an empty array of pixels to match.
In pt.2 we added support for creating standard format images by utilizing the ImageIO java library.
This also required us to create a protected method that converts images into buffered images
for use in saving to image files.
In pt. 3, a method to create the histogram was added. It performs the mathematical calculations to
creates an array of an array of frequency values for each pixel of an Image.

The ImageProcessingGuiView class is in charge of communication information to the user.
In pt 1 and 2, the view contained an appendable object used to render messages and instructions
meant for the user. Our implementation of the view provided the user with exact and necessary
help such as a list of commands when running the program.
In pt 3, the view was completely changed to incorporate the GUI. The class now extends JFrame and
uses a series of labels, panels, and scroll panes to get up an interactive interface. The user
can click on a button to load, save, or get help. They can also click one of the radio buttons to
manipulate the picture. The actionPerformed() method allows user input to be transmitted to the
controller.

The GUIFeaturesImpl is in charge of running the program.
This is done by taking in user input, parsing the input into the proper format, and then passing it
along to the model and view as necessary.
The controller can take as much or as little input as the user provides. The controller controls
error handling.
From pt.1 to pt.2 all that was added is a few extra cases in the switch statement that access
methods within the model class.
In pt 3, we simplified the code specifically error handling, while maintaining the same
functionality.

The Histogram class extends JPanel and creates an array of RGB frequencies that can be read and
drawn by the ImageProcessingGuiView.
