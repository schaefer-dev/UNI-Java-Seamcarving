package seamcarving;

/**
 * This class provides some utility functions for image handling.
 */
public final class SeamCarverUtils {
	
	//------------------------------------------------------------------------//
	// helper functions
	//------------------------------------------------------------------------//

	public static int getDelta(final int colorRGB1, final int colorRGB2) {
		final int deltaR = Math.abs(getR(colorRGB1) - getR(colorRGB2));
		final int deltaG = Math.abs(getG(colorRGB1) - getG(colorRGB2));
		final int deltaB = Math.abs(getB(colorRGB1) - getB(colorRGB2));
		return deltaR*deltaR + deltaG*deltaG + deltaB*deltaB;
	}
	public static int getAverage(final int colorRGB1, final int colorRGB2) {
		final int avgR = (getR(colorRGB1) + getR(colorRGB2)) / 2;
		final int avgG = (getG(colorRGB1) + getG(colorRGB2)) / 2;
		final int avgB = (getB(colorRGB1) + getB(colorRGB2)) / 2;
		return createRGBInt(avgR, avgG, avgB);
	}

	public static int getR(final int colorRGB) { return (colorRGB >> 16) & 0xFF; }
	public static int getG(final int colorRGB) { return (colorRGB >> 8) & 0xFF; }
	public static int getB(final int colorRGB) { return (colorRGB) & 0xFF; }

	public static int createRGBInt(final int valR, final int valG, final int valB) {
		if (valR > 255) throw new IllegalArgumentException("ERROR: red value is too large!");
		if (valG > 255) throw new IllegalArgumentException("ERROR: green value is too large!");
		if (valB > 255) throw new IllegalArgumentException("ERROR: blue value is too large!");
		if (valR < 0) throw new IllegalArgumentException("ERROR: red value must not be negative!");
		if (valG < 0) throw new IllegalArgumentException("ERROR: green value must not be negative!");
		if (valB < 0) throw new IllegalArgumentException("ERROR: blue value must not be negative!");
		return (valR << 16) | (valG << 8) | (valB);
	}


	public static String printImage(final java.awt.image.BufferedImage image) {
		if (image == null) throw new IllegalArgumentException("ERROR: image == null!");

		String s = "";
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				s += " " + image.getRGB(x, y);
			}
			s += "\n";
		}
		return s;
	}
	public static String printEnergy(final int[][] energy) {
		String s = "";
		for (int y = 0; y < energy.length; y++) {
			for (int x = 0; x < energy.length; x++) {
				s += " " + energy[x][y];
			}
			s += "\n";
		}
		return s;
	}
	public static String printSeamArray(final int[] seam) {
		String s = "";
		for (int i = 0; i < seam.length; i++) {
			s += " " + seam[i];
		}
		return s + " ";
	}
	
}
