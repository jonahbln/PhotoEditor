# PhotoEditorGUI

How to use our photo editor application:

There are two main ways to interact with our program...

1: Create a run configuration in IntelliJ

This will allow users to enter script commands directly into the command prompt. There are 3
possible command line arguments.
To run the main method, create a run configuration with one of the 3 command-line inputs.

1. No input: this will run the GUI as normal.
2. File-based input: enter '-file' 'path-of-script-file.txt'
   The program will parse through all script commands in the file and run them.
   (ex. -file res/SCRIPT.txt)
3. Console-based input: enter '-text' followed by the list of commands.
   The program will accept interaction form the user using System.in and System.out from the
   console. At any time, enter 'menu' to see a detailed list of all supported script commands.
   (ex. -text load res/colors.ppm colorpic flip colorpic colorflipped vertical
   save colorflipped res/colorflipped.jpg)

2: Run the provided PhotoEditor.jar application
Click on the JAR to run it. You might need to right-click then press run.
The JAR will open up the GUI.
The jar can also be run with command line inputs that will act as described above.

How to use the GUI:

- Start by pressing 'open a file' and navigating to the image you want to process
- Now, the image will load on the right, with it's histogram beneath it
- You can select from any of the radio buttons the action to perform to the loaded image
- Press enter to apply the action
- When selecting brighten or darken, you must also use the spinner to the right of the enter button
  to enter a value which will be applied
- When you are done processing the image, press 'save a file' and navigate to the location where
  you want to save the image. You must name the image and include the file format or else it will
  not be saved properly (.jpg, .png, .bmp, .ppm)
- At any time you can press 'help' to see instructions within the GUI itself.

More detailed instructions for using console-based commands:

When typing a command, enter the command, then the original file name, new file name, and finally
the type of the command (if the command requires you to specify). For example,
"load res/colors.ppm colorpic" is a valid command. Once the photo is loaded, you can perform
any command on the loaded photo. For example, "flip colorpic colorpicflipped vertical".
To view the image, you have changed you must save it. For example, "save colorpicflipped
res/colorpicflipped.png". At any time, you can reaccess a previous image by calling a command
on the previous image's name.

The following is a list of all supported commands that you can use

load ~file location~ ~image name~ :
loads the image located at the given file location which will be
stored in this program and referred to by the given image name
'directory/imagename.format'

save ~image name~ ~file location~ :
saves all changes to the given locally stored
image into the given file location on the user's system
'directory/imagename.format'

brighten ~image name~ ~new name~ ~value~ :
brightens the given image by the given integer value and renames the image to the given new name

darken ~image name~ ~new name~ ~value~ :
darkens the given image by the given integer value and renames the image to the given new name

flip ~image name~ ~new name~ ~direction~ :
flips the given image ('vertically' or 'horizontally') and renames the image to the given new name

greyscale ~image name~ ~new name~ ~type~ :
creates a greyscale image based on the component
('value', 'intensity', or 'luma') of the given image and names it the given new name

greyscale-color ~image name~ ~new name~ ~color~ :
creates a greyscale image based on the given color component
('red', 'green' or 'blue') of the given image and names it the given new name

filter ~image name~ ~new name~ ~type~ :
creates a filtered image ('blur' or 'sharpen') of the given image and names it the given new name

transformation ~image name~ ~new name~ ~type~ :
creates a transformed image ('greyscale' or 'sepia')
of the given image and names it the given new name

enter 'quit at any time to quit the program, all unsaved changes will be lost.

enter 'help' at any time to see this list of commands again.