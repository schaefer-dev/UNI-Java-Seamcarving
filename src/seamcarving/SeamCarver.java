package seamcarving;

import java.awt.image.BufferedImage;

/**
 * This is the interface you need to implement.
 */
public interface SeamCarver {

	//------------------------------------------------------------------------//
	// Seam calculation (to be implemented by students)
	//------------------------------------------------------------------------//

	/**
	 * Compute the current best seam of the image, either vertical or horizontal.
	 * This function only serves as a wrapper. It calls either computeVerticalSeam()
	 * or computeHorizontalSeam(), depending on the value of vertical.
	 *
	 * @param image
	 * 				The image to calculate the best seam for.
	 * @param vertical
	 *				Specifies whether to compute the best vertical or horizontal seam.
	 *
	 * @return The best vertical or horizontal seam.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 *				If the image does not have more than one column left and vertical is true.
	 *				If the image does not have more than one row left and vertical is false.
	 */
	public int[] computeSeam(final BufferedImage image, final boolean vertical);

	/**
	 * Compute the best vertical seam of the image.
	 * The seam is represented as an array of indices that specify which pixel of 
	 * each row belongs to the seam.
	 * 
	 * @param image
	 * 				The image to calculate the best vertical seam for.
	 * 
	 * @return The best vertical seam.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 *				If the image does not have more than one column left.
	 */
	public int[] computeVerticalSeam(final BufferedImage image);
	
	/**
	 * Compute the best horizontal seam of the image.
	 * The seam is represented as an array of indices that specify which pixel of 
	 * each column belongs to the seam.
	 * 
	 * @param image
	 * 				The image to calculate the best horizontal seam for.
	 * 
	 * @return The best horizontal seam.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 *				If the image does not have more than one row left.
	 */
	public int[] computeHorizontalSeam(final BufferedImage image);
	
	
	//------------------------------------------------------------------------//
	// Energy function implementation (to be implemented by students)
	//------------------------------------------------------------------------//
	
	/**
	 * Compute the energy function of the image.
	 * The energy function is represented as a two-dimensional array which holds
	 * one value per pixel at the corresponding indices.
	 * This function simply invokes computePixelEnergy() for every pixel.
	 * 
	 * @param image
	 * 				The image to calculate the energy for.
	 * @param vertical
	 *				Specifies whether to compute the vertical or horizontal energy.
	 * @param local
	 *				Specifies whether to only compute the local energy of each pixel.
	 * 
	 * @return The array that contains the energy of each pixel.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 */
	public int[][] computeImageEnergy(final BufferedImage image, final boolean vertical, final boolean local);

	/**
	 * Compute the energy of one pixel of the image.
	 * If local is true, the energy of a pixel is simply the local energy of
	 * the pixel alone and the vertical flag does not have any impact.
	 * Otherwise, the energy of a pixel is the sum of its local energy (given by
	 * evaluateEnergyFunction()) and the minimum energy of its neighboring
	 * pixels above (vertical == true) or left (vertical == false).
	 * This function should assume that the energy-array is already filled with
	 * the local energy of each pixel. It should not write to the array but only
	 * access values that have already been computed.
	 * It is not necessary to use recursion.
	 *
	 * @param image
	 * 				The image that holds the requested pixel.
	 * @param x
	 *				The x-coordinate of the pixel.
	 * @param y
	 *				The y-coordinate of the pixel.
	 * @param energy
	 *				The array that holds the energy of all previously calculated pixels
	 * @param vertical
	 *				Specifies whether to compute the vertical or horizontal energy.
	 * @param local
	 *				Specifies whether to only compute the local energy of each pixel.
	 * 
	 * @return The energy of the pixel at coordinate x/y.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 *				If x is no valid x-coordinate of the image.
	 *				If y is no valid y-coordinate of the image.
	 *				If energy is null and local is false.
	 */
	public int computePixelEnergy(final BufferedImage image, final int x, final int y, final int[][] energy, final boolean vertical, final boolean local);

	/**
	 * Evaluate the energy function for one pixel of the image.
	 * This function implements the calculation of the local energy of a single pixel.
	 *
	 * @param image
	 * 				The image that holds the requested pixel.
	 * @param x
	 *				The x-coordinate of the pixel.
	 * @param y
	 *				The y-coordinate of the pixel.
	 *
	 * @return The local energy of the pixel at coordinate x/y.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 *				If x is no valid x-coordinate of the image.
	 *				If y is no valid y-coordinate of the image.
	 */
	public int evaluateEnergyFunction(final BufferedImage image, final int x, final int y);

}
