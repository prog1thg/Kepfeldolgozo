import tmpimage.TmpImage;
import writers.ImageWriter;
import writers.PpmWriter;


public class WriterFactory {
	
	public static ImageWriter getWriter(String writer, TmpImage tmp, String pathToFile) {
		writer = writer.toLowerCase();
		
		if(writer.equals("ppm")) return new PpmWriter(tmp, pathToFile);
		
		System.out.println("No such writer.");
		System.exit(1);
		
		return null;
	}
	
}
