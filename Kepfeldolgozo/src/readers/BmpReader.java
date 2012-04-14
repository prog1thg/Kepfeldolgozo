package readers;

import java.awt.Color;

import tmpimage.TmpImage;


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
	private TmpImage tmp;
	
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
	 * 0x28	40	windows
	 * 0x0c	12	os/2.1.x
	 * 0xfo	240	os/2.2.x
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
	private int[][] palette;
	
	/**
	 * Depending on the compression specifications, 
	 * this field contains all the bitmap data bytes
	 *  which represent indices in the color palette.
	 */
	private int[][] bitmap;
	
	public BmpReader(String pathToImage) {
		this.byteReader = new ByteReader(pathToImage);
	}
	
	public static void main(String args[]) {
		BmpReader img = new BmpReader("res/bmp/test2_24.bmp");
		img.convertToTmpImage();
	}
	
	@Override
	/**
	 * The only public method.
	 * 
	 * @return TmpImage The converted
	 */
	public TmpImage convertToTmpImage() {
		this.readHeader();
		
		this.tmp = new TmpImage(this.height, this.width);
		
		this.palette = this.readPalette();
		this.bitmap = this.readBitmap();
		
		System.out.println(this.tmp);
		
		return tmp;
	}
	
	private void readHeader() {
		this.identifier = this.byteReader.readWord(0);
		
		this.file_size = this.byteReader.readDWord(2);
		this.reserved = this.byteReader.readDWord(6);
		this.data_offset = this.byteReader.readDWord(10);
		this.header_size = this.byteReader.readDWord(14);
		
		this.width = this.byteReader.readDWord(18);
		this.height = this.byteReader.readDWord(22);
		
		this.planes = this.byteReader.readByte(26);
		this.bits_per_pixel = this.byteReader.readWord(28);
		this.compression = this.byteReader.readDWord(30);
		
		this.data_size = this.byteReader.readDWord(34);
		// As of the specification rounding to the next 4 byte boundary, seems quite unnecessary for now ...
//		if(this.data_size % 4 != 0) {
//			this.data_size += 4 - this.data_size % 4;
//		}
			
		this.h_resolution = this.byteReader.readDWord(38);
		this.v_resolution = this.byteReader.readDWord(42);
		
		this.colors = this.byteReader.readDWord(46);
		this.important_colors = this.byteReader.readDWord(50);
	}
	
	private int[][] readPalette() {
		return null;
	}
	
	private int[][] readBitmap() {
		int row, col, offset;
		offset = this.data_offset;
		
		// BMP stores rows form down to up
		for(row = this.height - 1; row >= 0; row--) {
			for(col = 0; col < this.width; col++) {
				this.tmp.setColor(
					row, col,
					new Color(
							this.byteReader.readByte(offset + 2)  & 0x000000ff,
							this.byteReader.readByte(offset + 1)  & 0x000000ff,
							this.byteReader.readByte(offset)  & 0x000000ff
					)
				);
				System.out.println(
						"Offset: " + offset + "   " +
						" R: " + Integer.toHexString(this.byteReader.readByte(offset + 2) & 0x000000ff) +
						" G: " + Integer.toHexString(this.byteReader.readByte(offset + 1) & 0x000000ff) +
						" B: " + Integer.toHexString(this.byteReader.readByte(offset) & 0x000000ff)
				);
				offset += 3;
			}
		}
		
		return null;
	}
	
	public String toString() {
		return 
			"Identifier: " + "\t\t" + this.identifier  + "\n" +
			"File size: " + "\t\t" + ((double)this.file_size / 1024) + " KB" + "\n" +
			"Reserved: " + "\t\t" + this.reserved  + "\n" +
			"Data Offset: " + "\t\t" + this.data_offset + "\n" +
			"Header Size: " + "\t\t" + this.header_size  + "\n" +
			"Width " + "\t\t\t" + this.width  + " pixel" + "\n" +
			"Height: " + "\t\t" + this.height  + " pixel" + "\n" +
			"Planes: " + "\t\t" + this.planes  + "\n" +
			"Bits per Pixel: " + "\t" + this.bits_per_pixel  + "\n" +
			"Compression: " + "\t\t" + this.compression  + "\n" +
			"Data Size: " + "\t\t" + this.data_size  + "\n" +
			"H res: " + "\t\t\t" + this.h_resolution  + "\n" +
			"V res: " + "\t\t\t" + this.v_resolution  + "\n" +
			"Colors: " + "\t\t" + this.colors  + "\n" +
			"Important colors: " + "\t" + this.important_colors  + "\n"
		;
	}
	
}
