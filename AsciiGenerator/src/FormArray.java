//imports for image handeling
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.*;


public class FormArray {
	BufferedImage image;  //main variable
	String path;
	String name;

	public FormArray(String path) {//constructor for images
	this.path = path; //finds where the image is 
	this.name = path;
	this.name = this.getFileName(); //removes the path except for the filename of the image
	try {
        this.image = ImageIO.read(new File(path));//defines image as the image we want (as a bufferedImage)
      } catch (IOException e) {//if theres nothing there it throws an exception
      }
	
	}
	
	public BufferedImage getImage() {
	return image; //to get the image file

	}
	
	public int getImageWidth() {
		return image.getWidth(); //to get the width
	}
	public int getImageHeight() {
		return image.getHeight(); //to get the height
	}
	public int getGrayscale(int x, int y) { //takes in a point and tells the grayscale value on the image
		Color RGB = new Color(image.getRGB(x, y)); //gets the color of the pixel
		int red = RGB.getRed(); //finds the red
		int green = RGB.getGreen();//finds the green
		int blue = RGB.getBlue();//finds the blue
		int gray = (red+blue+green)/3;//averages the RGB to get the grayscale value
		return gray;//return the grayscale value
	}
	
	public int[][] makeArray() {//makes a new array based on an image that holds the grayscale values
		int[][] array = new int[image.getWidth()][image.getHeight()]; //makes an array equal to the image
			for(int y = 0; y<image.getHeight();y++) { //loops through the array
				for(int x = 0; x<image.getWidth();x++) {
				array[x][y] = getGrayscale(x,y);//gets the grayscale value of the pixel and adds it to the same spot of the new array
			}
			
		}
		return array; //return the grayscale array
	}
	public String getFileName() { //gets just the name of the file
		String filename = this.name; //final name variable
		if (filename.indexOf('\\')>0){ //finds an instance of a \
			this.name = filename.substring(filename.indexOf('\\')+1); 
			//changes filename so it's everything after the \
			filename = this.getFileName();
			//runs the method again until it finds a string with no \
		}
		
		return filename;//returns the final string that works 
		
	}
		 
public static void main(String[] args) {
FormArray array = new FormArray("src\\MY BOY.jpg");
System.out.println(array.getImageWidth());
System.out.println(array.getGrayscale(10,10));
}		
	}

	
