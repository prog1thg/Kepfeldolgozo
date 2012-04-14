package transformations;

import java.awt.Color;
import tmpimage.TmpImage;

/**
 * 
 * Vertical Flip is used to flip the source image from top to bottom.
 * 
 * @author Tomi
 *
 */

public abstract class VerticalFlip implements Transformation {
	
	public static void transformOnTmpImage(TmpImage image) {
		
		Color temp;
		for (int i = 0; i < image.getHeight()/2; i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				temp= image.getColor(i,j);
				image.setColor(i, j, image.getColor(image.getHeight()-i-1, j));
				image.setColor(image.getHeight()-i-1, j, temp);
			}
		}
	}

}