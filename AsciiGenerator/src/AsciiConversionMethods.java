//imports to make sure we can write to files
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class AsciiConversionMethods {

	public static void binary(int[][] array,int threshold,String path) throws IOException {
			FileWriter fileWriter = new FileWriter(path+".txt"); //config to make sure we can output to a txt file. Txt file is made based on the given path and adds .txt to the end
			PrintWriter printWriter = new PrintWriter(fileWriter);	
			String AsciiLine = "";//this is the string that each line will be stored in before being sent
			//loop through the array
			for (int y = 0; y<array[0].length;y++) {
				for (int x = 0; x<array.length;x++) {
				if (array[x][y]>threshold) { //compares each gray value against the threshold
					AsciiLine = (AsciiLine+" "); //appends a blank if it is greater than
				} 
				else {

					AsciiLine = (AsciiLine+"+");//appends a + sign if it is less than
				}
			}
		
				
				printWriter.println(AsciiLine);//writes to the text file
				
				System.out.println(AsciiLine);//outputs the line in console
				AsciiLine = ""; //resets AsciiLine

		}
			printWriter.close(); //closes the txt doc
	}
	
	public static int[][]chunkify(int[][]prechunk,int scalefactor){ //this method scales an array down into chunks by scalefactor
	//	int scalefactor = 10; //the amount of pixels you want to condense by. SF^2 get put into one pixel of the final arary
		int avGray = 0; //variable for the average amount of gray of a chunk
		int xdist = 0,ydist = scalefactor,fydist = 0;//X and Y dist are switched

		int[][] chunked = new int[(int) (Math.ceil(prechunk.length/(double)scalefactor))][(int) (Math.ceil(prechunk[0].length/(double)scalefactor))]; //array that new chunks will be stored in
		
		
		for (int x = 0; x<prechunk.length;x+=scalefactor) { 		//runs through the old array in chunks until it reaches an array that would be bigger than chunks	
			for (int y = 0; y<prechunk[0].length;y+=scalefactor) {
				avGray = 0; //reset the average
				for (int a = x;a<x+scalefactor&&a<prechunk.length;a++) { //runs through the chunks
					fydist = 0;
					for (int b = y;b<y+scalefactor&&b<prechunk[0].length;b++) {
				avGray+=prechunk[a][b]; //add up all the gray values of the chunk
				fydist++;
	
					}
					if (fydist<scalefactor) //the outward scales need to be divided by the amount of pixels
						ydist = fydist;
					xdist++;
			
			}
				chunked[x/scalefactor][y/scalefactor] = avGray/(ydist*xdist); //add the chunk average to the chunk array
				xdist = 0;
				ydist = scalefactor; //resets variables
			}

		}
		
		return chunked; //return the array
	}
	public static void dispArray(int[][]array) { //displaying an array
		for (int y = 0; y<array[0].length;y++) {
			for (int x = 0; x<array.length;x++) {
				System.out.print(array[x][y]);
		}
			System.out.println();
	}
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//FormArray Sexy = new FormArray("src\\MY BOY.jpg");
		//int[][]SexyArray = new int[Sexy.getImageWidth()][Sexy.getImageHeight()];
		//SexyArray = Sexy.makeArray();
		//binary(SexyArray,100);
		FormArray width = new FormArray("src\\MY BOY.jpg");
		
		//System.out.print(width.getFileName());
		//binary(width.makeArray(),120,width.path);
		//dispArray(chunkify(width.makeArray()));
		binary(chunkify(width.makeArray(),10),120,width.path);
		//chunkify(width.makeArray());
		System.out.print("\n"+width.getImageHeight()+", "+width.getImageWidth());
	}

}
