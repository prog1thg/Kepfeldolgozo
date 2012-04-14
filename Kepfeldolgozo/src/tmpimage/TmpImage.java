package tmpimage;
import java.awt.Color;

/**
 * Temp Image
 * 
 * A common format to help manipulate any kind of image files.
 * 
 * @author Zoo_Lee
 *
 */
public class TmpImage { 
	
	private int width;
	private int height;
	private Color[][] map;

	public TmpImage(int height, int width){
		this.width = width;
		this.height = height;
		this.map = new Color[this.height][this.width];
	}

	public void setColor(int row, int col, Color color){
		this.map[row][col] = color;
	}

	public Color getColor(int row, int col){
		return map[row][col];
	}
	
	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public String toString() {
		String out = "";

		for(int row = 0; row < this.height; row++) {
			for(int col = 0; col < this.width; col++) {
				Color current = map[row][col];
				out += "(" + current.getRed() + "," + current.getGreen() + "," + current.getBlue() + ")" + "\t";
			}
			out += "\n";
		}

		return out;
	}

}