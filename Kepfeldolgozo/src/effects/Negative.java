package effects;


import java.awt.Color;
import tmpimage.TmpImage;

/**
 * 
 * Negative effect is used to invert the colors.
 * 
 * @author Tomi
 *
 */

public abstract class Negative implements Effect {

	public void doTheEffect(TmpImage image) {
		
		Color temp;
		for (int i=0;i<image.getHeight();i++) {
			for (int j=0;j<image.getWidth();j++) {
				temp = image.getColor(i,j);
				image.setColor(i, j, new Color(255-temp.getRed(),255-temp.getGreen(),255-temp.getBlue()));
			}
		}
	}

}
