package readers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Byte Reader
 * 
 * For now, this byte reader expects little endian files.
 * Part of this code is taken from Example Depot. Refer to the first link.
 * 
 * @see http://www.exampledepot.com/egs/java.io/file2bytearray.html
 *
 */
class ByteReader {
	
	private byte[] bytes;
	
	public ByteReader(String pathToFile) {
		try {
			this.bytes = this.getBytesFromFile(new File( pathToFile ));
		} catch (IOException e) {
			System.out.println("Cant read file : " + pathToFile);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public byte[] getGetBytes() {
		return this.bytes;
	}
	
	// Returns the contents of the file in a byte array.
	private byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    // Get the size of the file
	    long length = file.length();

	    // You cannot create an array using a long type.
	    // It needs to be an int type.
	    // Before converting to an int type, check
	    // to ensure that file is not larger than Integer.MAX_VALUE.
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }

	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];

	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
	
	public int readByte(int index) {
		return bytes[index];
	}
	
	public int readWord(int offset) {
		int left = bytes[offset + 1];
		int right = bytes[offset];
		
		left <<= 8;
		right |= left;
		
		return right;
	}
	
	public int readDWord(int offset) {
		int leftmost = bytes[offset + 3];
		int midleft = bytes[offset + 2];
		int midright = bytes[offset + 1];
		int rightmost = bytes[offset];
		
		leftmost <<= 24;
		midleft <<= 16;
		midright <<= 8;
		
		rightmost |= midright;
		rightmost |= midleft;
		rightmost |= leftmost;
		
		return rightmost;
	}
	
	public void printBytes() {
		int i = 0;
		
		for(i = 0; i < this.bytes.length; i++) {
			System.out.print( Integer.toHexString(this.bytes[i]) + "\t");
		}
		System.out.println();
	}
	
}
