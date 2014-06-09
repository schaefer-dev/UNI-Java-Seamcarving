package seamcarving;

import java.awt.image.BufferedImage;
//test2
/**
 * This is the implementation of the main SeamCarver interface.
 */

public final class SeamCarverImpl implements SeamCarver {

	// ------------------------------------------------------------------------//
	// Seam calculation (to be implemented by students)
	// ------------------------------------------------------------------------//

	@Override
	public final int[] computeSeam(final BufferedImage image,
			final boolean vertical) {
		return vertical ? computeVerticalSeam(image)
				: computeHorizontalSeam(image);
	}

	@Override
	public final int[] computeVerticalSeam(final BufferedImage image) {
		// TODO Implement this method

		if (image == null) {
			throw new IllegalArgumentException("ERROR: Picture is empty!");
		}

		int[][] energieBild = computeImageEnergy(image, true, false);

		int hight = image.getHeight() - 1;
		int width = image.getWidth() - 1;

		if (width + 1 <= 1) {
			throw new IllegalArgumentException("ERROR: Picture is too small!");
		}

		int counter = 0;

		/* kleinstes Element in unterster Reihe = (counter,hight) */
		for (int i = 1; i <= width; i++) {
			if (energieBild[counter][hight] > energieBild[i][hight]) {
				counter = i;
			}
		}

		int[] res = new int[hight + 1];
		res[0] = counter;

		int counter2 = 0; // Merkstelle fuer kleinste Stelle

		hight = hight - 1;

		/* schleife fuer alle elemente ueber letzter Reihe */
		for (int i = 1; i <= (image.getHeight() - 1); i++) {
			counter2 = 0;
			/*
			 * kleinstes von links-oben und mitte-oben ermitteln. Counter2
			 * enthaellt dessen x Koordinate
			 */
			if (counter - 1 >= 0) {
				if (energieBild[counter - 1][hight] < energieBild[counter][hight]) {
					// !!!!! Da mitte priorisiert wird nur < und nicht <= !!!!
					counter2 = counter - 1;
				} else {
					counter2 = counter;
				}
			}
			if ((counter + 1) <= width) {
				if (energieBild[counter2][hight] <= energieBild[counter + 1][hight]) {
					/*
					 * counter 2 schon auf kleinstem Element
					 */
				} else {
					counter2 = counter + 1;
				}
			}
			res[i] = counter2;
			hight = hight - 1;
			counter = counter2;
		}

		return res;

	}

	@Override
	public final int[] computeHorizontalSeam(final BufferedImage image) {
		// TODO Implement this method

		if (image == null) {
			throw new IllegalArgumentException("ERROR: Picture is empty!");
		}

		int[][] energieBild = computeImageEnergy(image, false, false);

		int hight = image.getHeight() - 1;
		int width = image.getWidth() - 1;

		if (hight + 1 <= 1) {
			throw new IllegalArgumentException("ERROR: Picture is too small!");
		}

		int counter = 0;

		/* kleinstes Element in ganz rechter Reihe = (width, counter) */
		for (int i = 1; i <= hight; i++) {
			if (energieBild[width][counter] > energieBild[width][i]) {
				counter = i;
			}
		}

		int[] res = new int[width + 1];
		res[0] = counter;

		int counter2 = 0; // Merkstelle fuer kleinste Stelle

		width = width - 1;

		/* schleife fuer alle elemente ueber letzter Reihe */
		for (int i = 1; i <= (image.getWidth() - 1); i++) {
			counter2 = 0;
			/* kleinstes von links und mitte auf counter2 */
			if (counter - 1 >= 0) {
				if (energieBild[width][counter - 1] < energieBild[width][counter]) {
					// Mitte priorisiert !!!!
					counter2 = counter - 1;
				} else {
					counter2 = counter;
				}
			}
			if (counter + 1 <= hight) {
				if (energieBild[width][counter2] <= energieBild[width][counter + 1]) {
					/* schon auf kleinstem Element */
				} else {
					counter2 = counter + 1;
				}
			}
			res[i] = counter2;
			width = width - 1;
			counter = counter2;
		}

		return res;

	}

	// ------------------------------------------------------------------------//
	// Energy function implementation (to be implemented by students)
	// ------------------------------------------------------------------------//

	@Override
	public final int[][] computeImageEnergy(final BufferedImage image,
			final boolean vertical, final boolean local) {
		// TODO Implement this method

		if (image == null) {
			throw new IllegalArgumentException("ERROR: Picture is empty!");
		}

		int hight = image.getHeight() - 1;
		int width = image.getWidth() - 1;

		int[][] energyArray = new int[width + 1][hight + 1];

		if (local == true) {
			/* lokale Energie berechnen */
			for (int c = 0; c <= hight; c++) {
				for (int i = 0; i <= width; i++) {
					energyArray[i][c] = computePixelEnergy(image, i, c,
							energyArray, true, true);
				}
			}
		} else {
			/* nicht lokale Energie berechnen */
			if (vertical == true) {
				/* vertikal aufrufen */
				for (int c = 0; c <= hight; c++) {
					for (int i = 0; i <= width; i++) {
						energyArray[i][c] = computePixelEnergy(image, i, c,
								energyArray, true, false);
					}
				}
			} else {
				/* horizontal aufrufen */
				for (int c = 0; c <= width; c++) {
					for (int i = 0; i <= hight; i++) {
						energyArray[c][i] = computePixelEnergy(image, c, i,
								energyArray, false, false);
					}
				}
			}
		}
		return energyArray;

	}

	@Override
	public final int computePixelEnergy(final BufferedImage image, final int x,
			final int y, final int[][] energy, final boolean vertical,
			final boolean local) {
		// TODO Implement this method

		if ((energy == null) && (local == false)) {
			throw new IllegalArgumentException(
					"ERROR: Wrong call of computePixelEnergy");
		}

		/* Bild leer abfangen */
		if (image == null) {
			throw new IllegalArgumentException("ERROR: Picture is empty!");
		}

		int hight = image.getHeight() - 1;
		int width = image.getWidth() - 1;
		int res = 0;

		/* koordinaten ausserhalb abfangen */
		if (x > width) {
			throw new IllegalArgumentException("x Coordinate is outside");
		}
		if (x < 0) {
			throw new IllegalArgumentException("x Coordinate is outside");
		}
		if (y > hight) {
			throw new IllegalArgumentException("y Coordinate is outside");
		}
		if (y < 0) {
			throw new IllegalArgumentException("y Coordinate is outside");
		}

		/* local == true */
		if (local == true) {
			res = this.evaluateEnergyFunction(image, x, y);
		} else {
			/* local == false */
			if (vertical == true) {
				/* vertical == true */
				if (y - 1 < 0) {
					/* wenn am oberen Rand ist es nurnoch lokale Energie */
					return (this.evaluateEnergyFunction(image, x, y));
				} else {
					/* berechnen wenn Reihe Obendrueber existiert */
					if (x - 1 < 0) {
						if (x + 1 > width) {
							/* Fall nur mit x */
							return (this.evaluateEnergyFunction(image, x, y) + energy[x][y - 1]);
						} else {
							/* Fall mit x und mit x+1 */
							return (this.evaluateEnergyFunction(image, x, y) + Math
									.min(energy[x][y - 1], energy[x + 1][y - 1]));
						}
					} else {
						if (x + 1 > width) {
							/* Fall mit x-1 und x */
							return (this.evaluateEnergyFunction(image, x, y) + Math
									.min(energy[x][y - 1], energy[x - 1][y - 1]));
						} else {
							/* Fall mit x-1, x und x+1 */
							return (this.evaluateEnergyFunction(image, x, y) + Math
									.min(energy[x + 1][y - 1], Math.min(
											energy[x][y - 1],
											energy[x - 1][y - 1])));
						}
					}
				}

			} else {
				/* vertical == false */
				if (x - 1 < 0) {
					/* nur lokale Energie da am Rand */
					return (this.evaluateEnergyFunction(image, x, y));
				} else {
					/* berechnen da Reihe links Existiert */
					if (y - 1 < 0) {
						if (y + 1 > hight) {
							/* fall nur mit y */
							return (this.evaluateEnergyFunction(image, x, y) + energy[x - 1][y]);
						} else {
							/* fall mit y und y+1 */
							return (this.evaluateEnergyFunction(image, x, y) + Math
									.min(energy[x - 1][y], energy[x - 1][y + 1]));
						}
					} else {
						if (y + 1 > hight) {
							/* Fall mit y-1 und y */
							return (this.evaluateEnergyFunction(image, x, y) + Math
									.min(energy[x - 1][y], energy[x - 1][y - 1]));
						} else {
							/* Fall mit y-1 und y und y+1 */
							return (this.evaluateEnergyFunction(image, x, y) + Math
									.min(energy[x - 1][y + 1], Math.min(
											energy[x - 1][y],
											energy[x - 1][y - 1])));
						}
					}
				}
			}

		}

		return res;
	}

	@Override
	public final int evaluateEnergyFunction(final BufferedImage image,
			final int x, final int y) {
		// TODO Implement this method

		/* Bild leer abfangen */
		if (image == null) {
			throw new IllegalArgumentException("ERROR: Picture is empty!");
		}

		int hight = image.getHeight() - 1;
		int width = image.getWidth() - 1;
		int res;

		/* koordinaten ausserhalb abfangen */
		if (x > width) {
			throw new IllegalArgumentException("x Coordinate is outside");
		}
		if (x < 0) {
			throw new IllegalArgumentException("x Coordinate is outside");
		}
		if (y > hight) {
			throw new IllegalArgumentException("y Coordinate is outside");
		}
		if (y < 0) {
			throw new IllegalArgumentException("y Coordinate is outside");
		}

		/* Abfangen von Randfaellen (da hier Energie = 0 sein kann) */
		if (x == 0) {
			if (y == 0) {
				/* x und y = 0 */
				res = 0;
			} else {
				/* nur x = 0 */
				res = SeamCarverUtils.getDelta(image.getRGB(x, y),
						image.getRGB(x, y - 1));
			}
		} else {
			if (y == 0) {
				/* nur y = 0 */
				res = SeamCarverUtils.getDelta(image.getRGB(x, y),
						image.getRGB(x - 1, y));
			} else {
				/* beides != 0 */
				res = SeamCarverUtils.getDelta(image.getRGB(x, y),
						image.getRGB(x - 1, y))
						+ SeamCarverUtils.getDelta(image.getRGB(x, y),
								image.getRGB(x, y - 1));
			}
		}

		return res;
	}

}
