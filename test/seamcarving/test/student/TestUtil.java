package seamcarving.test.student;

import java.awt.image.BufferedImage;

public class TestUtil {

	/**
	 * Default timeout for the JUnit Tests
	 */
	public static final long DEFAULT_TIMEOUT = 5 * 1000;
	
	public static BufferedImage createImage(final int width, final int height, final int color) {
		if (width < 1 || height < 1) throw new IllegalArgumentException("ERROR: bad width/height!");
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		return setImageColor(image, color);
	}
	public static BufferedImage setImageColor(final BufferedImage image, final int color) {
		if (image == null) throw new IllegalArgumentException("ERROR: image == null!");
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				image.setRGB(x, y, color);
			}
		}
		return image;
	}

	public static boolean compareSeamArrays(final int[] seam1, final int[] seam2) {
		if (seam1.length != seam2.length) return false;
		for (int i = 0; i < seam1.length; i++) {
			if (seam1[i] != seam2[i]) return false;
		}
		return true;
	}

}
