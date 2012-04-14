package transformations;

import java.awt.Color;
import tmpimage.TmpImage;

/**
 * 
 * Horizontal Flip is used to flip the source image from left to right.
 * 
 * @author Tomi
 *
 */

public abstract class HorizontalFlip implements Transformation {

	public static void transformOnTmpImage(TmpImage image) {
		
		Color temp;
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth()/2; j++) {
				temp= image.getColor(i,j);
				image.setColor(i, j, image.getColor(i, image.getWidth()-j-1));
				image.setColor(i, image.getWidth()-j-1, temp);
			}
		}
	}

}