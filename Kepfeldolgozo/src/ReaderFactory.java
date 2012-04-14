import readers.BmpReader;
import readers.ImageReader;
import readers.PpmReader;


public class ReaderFactory {
	
	public static ImageReader getReader(String reader, String pathToFile) {
		reader = reader.toLowerCase();
		
		if(reader.equals("ppm")) return new PpmReader( pathToFile );
		if(reader.equals("bmp")) return new BmpReader( pathToFile );
		
		System.out.println("No such reader.");
		System.exit(1);
		
		return null;
	}
	
}
