package effects;
import tmpimage.TmpImage;

/**
 * Brigher and Darker
 * 
 * @author Zoo_Lee
 *
 */
public abstract class BrighterDarker implements Effect {
	public static void doTheEffect(TmpImage orig, int fokozat){
		int i=0;
		if(fokozat>0){
			for(int j=0; j<orig.getHeight(); j++){
				for(int k=0; k<orig.getWidth(); k++){
					while(i<fokozat){
						orig.setColor(j, k, (orig.getColor(j, k)).brighter());
						i++;
					}
					i=0;
				}
			}
		}
		if(fokozat<0){
			for(int j=0; j<orig.getHeight(); j++){
				for(int k=0; k<orig.getWidth(); k++){
					while(i>fokozat){
						orig.setColor(j, k, (orig.getColor(j, k)).darker());
						i--;
					}
					i=0;
				}
			}
		}
	}
}
