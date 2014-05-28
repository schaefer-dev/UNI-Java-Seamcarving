package seamcarving;

import java.awt.image.BufferedImage;

/**
 * Image creation and modification code.
 */
public final class SeamCarverImage {

	/**
	 * Remove a given seam from the image.
	 * Depending on the parameter vertical, the seam is interpreted either as a
	 * vertical or a horizontal seam.
	 *
	 * @param image
	 * 				The image to be resized.
	 * @param vertical
	 *				Specifies whether to remove a vertical or horizontal seam.
	 *
	 * @return The resized image.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 *				If the given seam is null.
	 *				If the image does not have more than one column left and vertical is true.
	 *				If the image does not have more than one row left and vertical is false.
	 */
	public static BufferedImage removeSeam(final BufferedImage image, final int[] seam, final boolean vertical) {
		if (seam == null) return image;

		final int width = image.getWidth();
		final int height = image.getHeight();

		// erase the seam from the image
		// = create new image with width/height reduced by one

		final BufferedImage newImage;
		if (vertical) {
			newImage = new BufferedImage(width-1, height, image.getType());

			for (int y = 0; y < height; y++) {
				final int k = seam[y];
				// copy all values in front of the deleted pixel
				for (int x = 0; x < k; x++) {
					newImage.setRGB(x, y, image.getRGB(x, y));
				}
				// copy all values behind the deleted pixel
				for (int x = k; x < width-1; x++) {
					newImage.setRGB(x, y, image.getRGB(x+1, y));
				}
			}
		} else {
			newImage = new BufferedImage(width, height-1, image.getType());

			for (int x = 0; x < width; x++) {
				final int k = seam[x];
				// copy all values in front of the deleted pixel
				for (int y = 0; y < k; y++) {
					newImage.setRGB(x, y, image.getRGB(x, y));
				}
				// copy all values behind the deleted pixel
				for (int y = k; y < height-1; y++) {
					newImage.setRGB(x, y, image.getRGB(x, y+1));
				}
			}
		}

		return newImage;
	}

	/**
	 * Create an image that has the seam highlighted in red.
	 * Depending on the parameter vertical, the seam is interpreted either as a
	 * vertical or a horizontal seam.
	 *
	 * @param image
	 * 				The image where the seam should be highlighted.
	 * @param vertical
	 *				Specifies whether to show a vertical or horizontal seam.
	 *
	 * @return The modified image.
	 * @throws IllegalArgumentException
	 * 				If the given image is null.
	 *				If the given seam is null.
	 */
	public static BufferedImage createImageWithHighlightedSeam(final BufferedImage image, final int[] seam, final boolean vertical) {
		if (seam == null) return image;

		final int width = image.getWidth();
		final int height = image.getHeight();

		// color each pixel of the seam
		if (vertical) {
			for (int y = 0; y < height; y++) {
				final int x = seam[y];
				image.setRGB(x, y, SeamCarverUtils.createRGBInt(255, 0, 0));
			}
		} else {
			for (int x = 0; x < width; x++) {
				final int y = seam[x];
				image.setRGB(x, y, SeamCarverUtils.createRGBInt(255, 0, 0));
			}
		}

		return image;
	}

	/**
	 * Create an image from a given energy function.
	 *
	 * @param energy
	 * 				The energy-function that should be visualized.
	 *
	 * @return The new energy-image.
	 * @throws IllegalArgumentException
	 * 				If the given energy is null.
	 */
	public static BufferedImage createEnergyImage(final int[][] energy, final int imageType) {
		if (energy == null) throw new IllegalArgumentException("ERROR: energy is null!");

		final int width = energy.length;
		final int height = energy[0].length;

		final BufferedImage energyImage = new BufferedImage(width, height, imageType);

		int maxEnergy = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				final int curEnergy = energy[x][y];
				if (curEnergy > maxEnergy) {
					maxEnergy = curEnergy;
				}
			}
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				energyImage.setRGB(x, y, (int)(energy[x][y]*255L / maxEnergy) * 0x010101);
			}
		}

		return energyImage;
	}

}
