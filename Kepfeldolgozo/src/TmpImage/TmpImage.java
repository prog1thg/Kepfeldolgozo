package TmpImage;
import java.awt.Color;

public class TmpImage { 
	private int width;
	private int height;
	private Color[] map = new Color[height*width];
	
	public void setColor(int i, int j, Color szin){
		this.map[i*j]=szin;
	}
	
	public Color getColor(int i, int j){
		return map[i*j];
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public TmpImage(int magas, int szeles){
		this.width=szeles;
		this.height=magas;
	}
}