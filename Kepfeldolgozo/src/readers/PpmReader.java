package readers;
import java.awt.Color;
import java.io.*;
import tmpimage.TmpImage;

/**
 * PPM Reader
 * 
 * Converts PPP images to a TmpImage for further processing.
 * @see http://netpbm.sourceforge.net/doc/ppm.html
 * 
 * Part of this code is taken from this link:
 * @see http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml 
 * 
 * @author dzdeni
 *
 */
public class PpmReader implements ImageReader {

	/** 
	 * Image identifier [magic number] P1,P2..P5,P6 which should be P3 for now.
	 * 
	 * There's a complete code for P6 here:
	 * @see http://rosettacode.org/wiki/Bitmap/Read_a_PPM_file#J
	 */
	private String identifier;
	
	/** 
	 * The with of the image.
	 */
	private int width;
	
	/**
	 * The height of the image.
	 */
	private int height;
	
	/**
	 * The maximum value of the colors. | 0 < maxColVal < 65536
	 */
	private int maxColVal;
	
	/**
	 * For later use..
	 */
	private TmpImage tmp;	
	private int[] tmpColors;

	public PpmReader(String pathToImage) {
		this.getDatasFromFile(pathToImage);
    }

	/**
	 * @param pathToImage The path of the input file.
	 */
	private void getDatasFromFile(String pathToImage) {
		try {
			/** 
			 * Open the file that is the first
			 * command line parameter
			 */
			FileInputStream fstream = new FileInputStream(pathToImage);

			/**
			 * Get the object of DataInputStream
			 */
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			/**
			 * Needed variables
			 */
			int i = 0;
			int k = 0;
			
			/**
			 * Read File Line By Line
			 */
			while ((strLine = br.readLine()) != null)   {
				// Print the content on the console
				int commentFrom = strLine.indexOf("#");
				if (commentFrom >= 0) {			  
					strLine = strLine.substring(0, commentFrom);
				}
				if (strLine.equals("")) continue;

				/**
				 * Split to substrings
				 */
				String data[] = strLine.split("\\s+");
		  
				/**
				 * The first number is the magic number. It has to be P3.
				 */
				if (i == 0) {
					if (!data[0].equals("P3")) {
						System.out.println("Format error: P3 needed (in "+ pathToImage +")");
						System.exit(1);
					}
					else identifier = data[0];
				}

				/**
				 * The first three numbers are the width/height/maximum color value combo.
				 */
				if (maxColVal == 0) 
				for (int j = 0; j < data.length; j++) {
					if ((this.width == 0) && (i != 0) && (!data[j].equals(""))) this.width = Integer.parseInt(data[j]);
					else if ((this.height == 0) && (i != 0) && (!data[j].equals(""))) this.height = Integer.parseInt(data[j]);
					else if ((this.maxColVal == 0) && (i != 0) && (!data[j].equals(""))) {
						maxColVal = Integer.parseInt(data[j]);
						/**
						 * Create an array of the colors which size is height*width[*3] -> three tints (red, green, blue) makes the color of one pixel.
						 */
						this.tmpColors = new int[this.height*this.width*3];
					}
					else break;				
				}
				else {
					for (int j = 0; j < data.length; j++) {
						this.tmpColors[k] = Integer.parseInt(data[j]);
						k++;
					}
				}
				if (i == 0) i = 1;
			}	 
			
			/**
			 * Close the input stream
			 */
			in.close();
	    	}
		
		/**
		 * Catch exception if any
		 */
		catch (Exception e) { 			
			System.err.println("Error: " + e.getMessage());
		}		
	}

	public TmpImage convertToTmpImage() {
		this.tmp = new TmpImage(this.height, this.width);

		int i = 0;
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				this.tmp.setColor(row, col, new Color(this.tmpColors[i+0],
						  this.tmpColors[i+1],
						  this.tmpColors[i+2]
						 ));
				i += 3;
				// System.out.println("row: "+row+", col: "+col+" "+this.tmp.getColor(row, col));
				}
			}

		return tmp;
	}

	public String toString() {
		return 
			"Identifiers: " + "\t\t" + this.identifier + "\n" +
			"Width: " + "\t\t\t" + this.width  + "\n" +
			"Height: " + "\t\t" + this.height + "\n" +
			"Maximum Color Value: " + "\t" + this.maxColVal + "\n"
			;
	}

}