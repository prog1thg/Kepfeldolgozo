package readers;

import TmpImage.TmpImage;

/**
 * BMP Reader
 * 
 * Converts BMP images to a TmpImage for further processing.
 * 
 * @see http://atlc.sourceforge.net/bmp.html#_toc381201084
 * @author uvegla
 *
 */
public class BmpReader implements ImageReader {
	
	private ByteReader byteReader;
	
	/**
	 * The characters identifying the bitmap.
	 */
	private int identifier;
	
	/**
	 * Complete file size in bytes.
	 */
	private int file_size;
	
	/**
	 * Reserved for later use.
	 */
	private int reserved;
	
	/**
	 * Offset from beginning of file to the beginning of the bitmap data.
	 */
	private int data_offset;
	
	/**
	 * Length of the bitmap info header used to describe the bitmap colors, compression etc.
	 */
	private int header_size;
	
	/**
	 * Horizontal width of bitmap in pixels.
	 */
	private int width;
	
	/**
	 * Vertical height of bitmap in pixels.
	 */
	private int height;
	
	/**
	 * Number of planes in this bitmap.
	 */
	private int planes;
	
	/**
	 * Bits per pixel used to store palette entry information.
	 * This also identifies in an indirect way the number of possible colors.
	 * { 1, 4, 8, 16, 24, 32 }
	 */
	private int bits_per_pixel;
	
	// TODO Look these things up.
	/**
	 * Compression specifications.
	 * 0	none
	 * 1	bi_rle4			rle 8-bit
	 * 2	bi_rle8			rle 4-bit
	 * 3	bi_bitfields	bitfields
	 */
	private int compression;
	
	/**
	 * Size of the bitmap data in bytes. 
	 * This number must be rounded to the next 4 byte boundary
	 */
	private int data_size;
	
	/**
	 * Horizontal resolution expressed in pixel per meter.
	 */
	private int h_resolution;
	
	/**
	 * Vertical resolution expressed in pixels per meter.
	 */
	private int v_resolution;
	
	/**
	 * Number of colors used by this bitmap. 
	 * For a 8-bit / pixel bitmap this will be 100h or 256.
	 */
	private int colors;
	
	/**
	 * Number of important colors. 
	 * This number will be equal to the number of colors when every color is important.
	 */
	private int important_colors;
	
	/**
	 * The palette specification. 
	 * For every entry in the palette four bytes are used to 
	 * describe the rgb values of the color in the following way:
	 * 1 byte BLUE
	 * 1 byte GREEN
	 * 1 byte RED
	 * 1 byte SET TO ZERO
	 */
	private int palette;
	
	/**
	 * Depending on the compression specifications, 
	 * this field contains all the bitmap data bytes
	 *  which represent indices in the color palette.
	 */
	private int bitmap;
	
	public BmpReader(String pathToImage) {
		this.byteReader = new ByteReader(pathToImage);
	}
	
	public static void main(String args[]) {
		BmpReader bmp = new BmpReader("res/bmp/test2_24.bmp");
		bmp.convertToTmpImage();
	}
	
	@Override
	/**
	 * The only public method.
	 * 
	 * @return TmpImage The converted
	 */
	public TmpImage convertToTmpImage() {
		this.readHeader();
		return null;
	}
	
	private void readHeader() {
		this.identifier = this.byteReader.readWord(0);
		
		this.file_size = this.byteReader.readDWord(2);
		this.reserved = this.byteReader.readDWord(6);
		this.data_offset = this.byteReader.readDWord(10);
		this.header_size = this.byteReader.readDWord(14);
		
		this.width = this.byteReader.readDWord(18);
		this.height = this.byteReader.readDWord(22);
		
		System.out.println("ID: " + this.identifier);
		System.out.println("ID: " + Integer.toHexString(this.identifier));		
		
		System.out.println("W: " + this.width + " H: " + this.height);
		System.out.println("W: " + Integer.toHexString(this.width) + " H: " + Integer.toHexString(this.height));
	}

}
