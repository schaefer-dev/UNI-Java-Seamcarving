package seamcarving;

import java.awt.image.BufferedImage;

/**
 * This is the implementation of the main SeamCarver interface.
 */
public final class SeamCarverImpl implements SeamCarver {

	//------------------------------------------------------------------------//
	// Seam calculation (to be implemented by students)
	//------------------------------------------------------------------------//

	@Override
	public final int[] computeSeam(final BufferedImage image, final boolean vertical) {
		return vertical ? computeVerticalSeam(image) : computeHorizontalSeam(image);
	}
	@Override
	public final int[] computeVerticalSeam(final BufferedImage image) {		
		// TODO Implement this method
		
		if (image==null){throw new IllegalArgumentException("ERROR: Picture is empty!");}
		
		
		int[][] energieBild = computeImageEnergy(image,true,false);
		
		int hight = image.getHeight()-1;
		int width = image.getWidth()-1;
		
		if (width <= 1) {throw new IllegalArgumentException("ERROR: Picture is too small!");}
		
		int counter = 0;
		
		/* kleinstes Element in unterster Reihe = (counter,hight) */
		for (int i=1;i<=width;i++){
			if (energieBild[counter][hight]>energieBild[i][hight]) {counter = i;}
		}
		
		int[] res = new int[]{};
		res[0]=counter;
		
		int counter2=0; //Merkstelle fuer kleinste Stelle
		
		hight=hight-1;
		
		/* schleife fuer alle elemente ueber letzter Reihe*/
		for (int i=1; i<=(image.getHeight()-1);i++) {
			counter2 = 0;
			/* kleinstes von links und mitte auf counter2*/
			if (counter-1>=0){
				if (energieBild[counter-1][hight]<=energieBild[counter][hight]) {
					counter2 = counter -1;
					}
				else {
					counter2=counter;
					}
			}
			if (counter +1 <= width){
				if (energieBild[counter2][hight]<=energieBild[counter+1][hight]) {/*schon auf kleinstem Element*/}
				else {
					counter2=counter+1;
				}
			}
			res[i]=counter2;
			hight=hight-1;
			counter = counter2;
		}
		
		return res;
		
	}
	@Override
	public final int[] computeHorizontalSeam(final BufferedImage image) {
		// TODO Implement this method
		
		
		/* NOT TESTED !!! YET */
		
		if (image==null){throw new IllegalArgumentException("ERROR: Picture is empty!");}
		
		
		int[][] energieBild = computeImageEnergy(image,true,false);
		
		int hight = image.getHeight()-1;
		int width = image.getWidth()-1;
		
		if (hight <= 1) {throw new IllegalArgumentException("ERROR: Picture is too small!");}
		
		int counter = 0;
		
		/* kleinstes Element in unterster Reihe = (counter,hight) */
		for (int i=1;i<=hight;i++){
			if (energieBild[width][counter]>energieBild[width][i]) {counter = i;}
		}
		
		int[] res = new int[]{};
		res[0]=counter;
		
		int counter2=0; //Merkstelle fuer kleinste Stelle
		
		width=width-1;
		
		/* schleife fuer alle elemente ueber letzter Reihe*/
		for (int i=1; i<=(image.getWidth()-1);i++) {
			counter2 = 0;
			/* kleinstes von links und mitte auf counter2*/
			if (counter-1>=0){
				if (energieBild[width][counter-1]<=energieBild[width][counter]) {
					counter2 = counter -1;
					}
				else {
					counter2=counter;
					}
			}
			if (counter +1 <= hight){
				if (energieBild[width][counter2]<=energieBild[width][counter+1]) {/*schon auf kleinstem Element*/}
				else {
					counter2=counter+1;
				}
			}
			res[i]=counter2;
			width=width-1;
			counter = counter2;
		}
		
		return res;
		
		
		
	}
	
	//------------------------------------------------------------------------//
	// Energy function implementation (to be implemented by students)
	//------------------------------------------------------------------------//
	
	@Override
	public final int[][] computeImageEnergy(final BufferedImage image, final boolean vertical, final boolean local) {
		
		// local ist nur Energie Pixel mit links + Energie Pixel mit rechts
		
		
		// TODO Implement this method
		throw new UnsupportedOperationException("This method has not yet been implemented.");
	}
	@Override
	public final int computePixelEnergy(final BufferedImage image, final int x, final int y, final int[][] energy, final boolean vertical, final boolean local) {
		// TODO Implement this method
		throw new UnsupportedOperationException("This method has not yet been implemented.");
	}
	@Override
	public final int evaluateEnergyFunction(final BufferedImage image, final int x, final int y) {
		// TODO Implement this method
		throw new UnsupportedOperationException("This method has not yet been implemented.");
	}

}
