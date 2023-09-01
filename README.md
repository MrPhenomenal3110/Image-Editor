# Image-Editor
A simple Image-Editor written in JAVA.

# Structure:

- This image-editor has a command line interface. 
There is one class - ImageEditor , which contains a main function and 5 functions , namely
    - `mirror`
    - `to-grayscale`
    - `to-monochrome`
    - `rotate`
    - `brightness-control`

# Command-Line-Interface : 

- `Checking for valid commands and arguments` :

    - The structure of the command is \<command\> \<argument1\> \<argument2\>

    - In order to check for valid commands, an array of commands has been created.

    - Since, the commands are being taken as an array of strings, the first part of the given arguments contains the command. This commmand is compared with each element of the array. If the command does not match with any of the elements of the command array, an error statement is printed, explaining the syntax of writing a command and a list of possible commands.

    -  The type of argument1 has to b compulsorily `.jpg` and argument2 is the name of the output file (which is `.jpg` file by default)

    - If there is any error in the program, it prints the exception in the terminal.

- `Taking in the input to process the input` :

    - Used the `System.java.io` module that helps in taking the input form user and gives output.


# Functions

    Original Image

![image](./example.jpg)

- `mirror` :

    ![image](./mirrored_image.jpg)

    - It laterally inverts the image by fetching the rgb value of each pixel, and switches the ith pixel of each row with the (n-i)th pixel of that row.

    - Used `java.awt.image.BufferedImage` module to create an object of Buffered Image and process it and return the processed Buffered Image as the output.

- `to-grayscale` :

    ![image](./grayscaled-image.jpg)

    - It uses the `gray-color-space` instead of the `3-byte-color-space`. This leads to the creation of a grayscaled image.

    - Used `java.awt.image.BufferedImage` module to create an object of Buffered Image and process it and return the processed Buffered Image as the output.

- `rotate`

    ![image](./rotate.jpg)

    - It rotates the image `clockwise`. The function exchanges every row with the column i.e row becomes column and column becomes row.

    - Used `java.awt.image.BufferedImage` module to create an object of Buffered Image and process it and return the processed Buffered Image as the output.

- `to-monochrome` :

    ![image](./monochrome-image.jpg)

    - It traverses every pixel of the image, collects the rgb values for each pixel and sets all the three values r, g and b to the average of r,g and b values.

    - Used `java.awt.image.BufferedImage` module to create an object of Buffered Image and process it and return the processed Buffered Image as the output.

- `brightness-control` :

    ![image](./brightness_increased_by_50percent.jpg)

    - This function takes an input as an integer, that gives the increase or decrease percentage of brightness and it increases the r, g, b values of every pixel of the image, by the percentage entered as input. This leads to an increase in the effective brightness. (In order to avoid error which might occur if r,g or b values surpass their threshold value : 255, there is a check done using the if block, ensuring that no value goes above 255, and gets caped at 255).

    - Used `java.awt.image.BufferedImage` module to create an object of Buffered Image and process it and return the processed Buffered Image as the output.