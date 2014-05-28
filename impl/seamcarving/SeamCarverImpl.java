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
		throw new UnsupportedOperationException("This method has not yet been implemented.");
	}
	@Override
	public final int[] computeHorizontalSeam(final BufferedImage image) {
		// TODO Implement this method
		throw new UnsupportedOperationException("This method has not yet been implemented.");
	}
	
	//------------------------------------------------------------------------//
	// Energy function implementation (to be implemented by students)
	//------------------------------------------------------------------------//
	
	@Override
	public final int[][] computeImageEnergy(final BufferedImage image, final boolean vertical, final boolean local) {
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
