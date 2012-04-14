import parser.CommandLineParser;
import readers.BmpReader;
import readers.ImageReader;
import readers.PpmReader;
import tmpimage.TmpImage;
import writers.ImageWriter;
import writers.PpmWriter;


/**
 * Teszt Elek
 */

/**
 * Image Manipulation Software (C) 2012
 * 
 * @author dzdeni
 * @author Tomi
 * @author uvegla
 * @author Zoo_Lee
 *
 */

public class Main {

	private static ImageReader reader; 
	private static ImageWriter writer;
	private static CommandLineParser cmd;
	private static TmpImage tmp;	
	
	public static void main(String args[]) {
		cmd = new parser.CommandLineParser(args);
		
		reader = ReaderFactory.getReader(cmd.ext, cmd.filein);
		
		tmp = reader.convertToTmpImage();

		/** Effects && Transformation should go here */
		
		writer = WriterFactory.getWriter(cmd.ext, tmp, cmd.fileout);

		writer.writeToFile();


		System.out.println(cmd.filein +" converted to "+ cmd.fileout +".\n\nIt was so easy!\n");

	}

}