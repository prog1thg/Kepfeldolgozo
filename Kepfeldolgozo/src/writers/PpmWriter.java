package writers;
import java.io.*;
import tmpimage.TmpImage;
/**
 * PPM Reader
 * 
 * Converts TmpImage to PpmImage and write it to a file.
 * @see http://netpbm.sourceforge.net/doc/ppm.html
 * 
 * Part of this code is taken from this link:
 * @see http://www.roseindia.net/java/beginners/java-write-to-file.shtml
 * 
 * @author dzdeni
 * @return 
 *
 */
public class PpmWriter implements ImageWriter {
		
	private TmpImage tmp;
	private String output;

	public PpmWriter(TmpImage tmp, String output) {
		this.tmp = tmp;
		this.output = output;
		this.writeToFile();
	}
	
	public void writeToFile() {
		try {
			/**
			 * Create file 
			 */
			FileWriter fstream = new FileWriter(this.output);
			BufferedWriter out = new BufferedWriter(fstream);
			/**
			 * First line: the identifier
			 */
			out.write("P3\n");
			/**
			 * Second line: the width and height
			 */
			out.write(this.tmp.getWidth() +" "+ this.tmp.getHeight() +" ");
			/**
			 * Third line: the maximum value of the colors
			 */
			out.write("255");
			int k = 0;
			/**
			 * Other lines: the pixels which based on rgb colors [the maximum length of a line is 70character, so it needs a split sometime]
			 */
			for (int i = 0; i < this.tmp.getHeight(); i++) {
				for (int j = 0; j < this.tmp.getWidth(); j++) {
					if (k % 6 == 0) out.write(System.getProperty("line.separator"));
					out.write(this.tmp.getColor(i, j).getRed() + " ");
					out.write(this.tmp.getColor(i, j).getGreen() + " ");
					out.write(this.tmp.getColor(i, j).getBlue() + " ");
					k++;
				}
			}

			/**
			 * Close the output stream
			 */
			out.close();
		}
		catch (Exception e) {
			/**
			 * Catch exception if any
			 */
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public String toString() {
		return "_output_";
	}

}