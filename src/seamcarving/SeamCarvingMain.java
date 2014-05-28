package seamcarving;

import seamcarving.gui.SeamCarvingFrame;
import java.awt.image.BufferedImage;

public final class SeamCarvingMain {
	
        /**
        * Instantiate this class and open the GUI.
        * 
        * @param args
        * 				The command line arguments
        */
        public static void main(String[] args) {
    	// attempt to load a default image to start with :)
		//final java.io.File file = new java.io.File("test_tower.jpg");

		//SeamCarvingMain handler = new SeamCarvingMain(file);
		SeamCarvingMain handler = new SeamCarvingMain(null);

		handler.showGUI();

		return;
        }

	private SeamCarvingFrame gui = null;
	private BufferedImage image = null;
	private Thread seamCarvingThread = null;
	
	/**
	 * Constructor.
	 * 
	 * @param file
	 * 				The default image file to load (can be null).
	 */
	public SeamCarvingMain(final java.io.File file) {
		gui = new SeamCarvingFrame(this);
		gui.setDefaultCloseOperation( javax.swing.JFrame.EXIT_ON_CLOSE );

		try{
			loadImage(file);
		} catch (IllegalArgumentException iae) {
			// We do not want to throw an exception here...
			System.err.println("ERROR: default image could not be loaded!");
		}
		assert (image != null);
	}
	
	/**
	 * Open the GUI.
	 */
	public final void showGUI() {
		gui.setVisible(true);
	}

	/**
	 * Close the GUI and shut down the application.
	 */
	public final void shutdown() {
		gui.setVisible(false);
		System.exit(0);
	}

	/**
	 * Load an image from a file.
	 * 
	 * @param file
	 * 				The source file of the image.
	 * 
	 * @throws IllegalArgumentException
	 * 				If the image file could not be read
	 */
	public final void loadImage(final java.io.File file) throws IllegalArgumentException {
		if (file == null) return;

		try {
			image = javax.imageio.ImageIO.read(file);
		} catch (java.io.IOException ex) {
			throw new IllegalArgumentException("ERROR: loadImage(): Could not read image file: " + ex);
		}

		assert (image != null);
		gui.updateImage(image);
	}

	/**
	 * Save image to a file.
	 *
	 * @param image
	 * 				The image to be saved.
	 * @param file
	 * 				The target file for the image.
	 *
	 * @throws IllegalArgumentException
	 * 				If the image is null.
	 * 				If the requested file format can not be written.
	 * 				If the image file could not be written.
	 */
	public final void storeImage(final BufferedImage image, final String fileFormat, final java.io.File file) throws IllegalArgumentException {
		if (file == null) return;
		if (image == null) throw new IllegalArgumentException("ERROR: storeImage(): image is null!");

		try {
			final boolean success = javax.imageio.ImageIO.write(image, fileFormat, file);
			if (!success) throw new IllegalArgumentException("ERROR: storeImage(): Could not write image file format!");
		} catch (java.io.IOException ex) {
			throw new IllegalArgumentException("ERROR: storeImage(): Could not write image file: " + ex);
		}
	}

	
	/**
	 * Create a new thread that performs seam carving.
	 * 
	 * @param deleteVertical
	 * 				True if vertical seams should be removed.
	 * 				False if vertical seams should be inserted.
	 * @param deleteHorizontal
	 * 				True if horizontal seams should be removed.
	 * 				False if horizontal seams should be inserted.
	 * @param vertical
	 * 				Amount of vertical seams that should be removed/inserted.
	 * @param horizontal
	 * 				Amount of vertical seams that should be removed/inserted.
	 */
	public final synchronized void performSeamCarving(final boolean deleteVertical, final boolean deleteHorizontal, final int vertical, final int horizontal) throws IllegalStateException {
		if (!deleteVertical || !deleteHorizontal) return; // not implemented

		// Spawn new thread that performs the carving operations.
		// This is required in order to let the GUI update properly if seam
		// carving is invoked via GUI controls.

		// If the thread is still active, we have to wait until the last carving action has finished.
		while (seamCarvingThread != null && seamCarvingThread.isAlive()) {
			seamCarvingThread.interrupt();
			try {
				wait(50); // not sure how much we need, but this seems to work well
			} catch (InterruptedException ex) {
				// we don't care :p
			}
		}
		// Create a new thread with the appropriate Runnable
		seamCarvingThread = new Thread( new SeamCarvingRunnable(deleteVertical, deleteHorizontal, vertical, horizontal) );
		seamCarvingThread.start();
	}

	/**
	 * Remove exactly one seam from the image.
	 * 
	 * @param vertical
	 * 				True if vertical seams should be removed.
	 * 				False if horizontal seams should be removed.
	 * 
	 * @throws IllegalStateException
	 * 				If the images width (height) is not enough to remove a vertical (horizontal) seam.
	 */
	private void removeOneSeam(final boolean vertical) throws IllegalStateException {
		if (image == null) return;

		// instantiate seam carver for this image
		SeamCarver seamCarver = new SeamCarverImpl();

		// invoke seam carving

		if (vertical && image.getWidth() <= 1) {
			throw new IllegalStateException("ERROR: removeOneSeam(): image is less than two pixels wide!");
		}
		if (!vertical && image.getHeight() <= 1) {
			throw new IllegalStateException("ERROR: removeOneSeam(): image is less than two pixels high!");
		}

		// compute next best seam
		int[] seam = seamCarver.computeSeam(image, vertical);

		// display seam
		image = SeamCarverImage.createImageWithHighlightedSeam(image, seam, vertical);
		gui.updateImage(image);

		// create new image by removing seam
		image = SeamCarverImage.removeSeam(image, seam, vertical);
		gui.updateImage(image);

	}

	/**
	 * Insert seams into the image.
	 * NOTE: NOT IMPLEMENTED!
	 * 
	 * @param vertical
	 * 				True if vertical seams should be inserted.
	 * 				False if horizontal seams should be inserted.
	 * @param numSeams
	 * 				Amount of seams that should be inserted.
	 */
	private void insertSeams(final boolean vertical, final int numSeams) {
		if (image == null) return;

		throw new IllegalStateException("ERROR: insertSeams() not implemented!");

//		// instantiate seam carver for this image
//		SeamCarver seamCarver = new SeamCarver();
//
//		// invoke inverse seam carving
//
//		// compute next 'numSeams' best seams
//		int[][] seams = seamCarver.computeSeams(image, numSeams, vertical);
//
//		// TODO: display seams on demand
//		//image = seamCarver.createImageWithHighlightedSeams(image, seams, vertical);
//		//gui.updateImage(image);
//
//		// create new image by removing seam
//		image = seamCarver.insertSeams(image, seams, vertical);
//		gui.updateImage(image);
	}

	/**
	 * Visualize the energy function in an additional window.
	 * If local is true, the color of each pixel is simply its local energy
	 * and the vertical flag does not have any impact.
	 * Otherwise, the color of each pixel is the sum of its local energy and the
	 * minimum energy of its neighboring pixels above (vertical == true) or
	 * left (vertical == false).
	 * 
	 * @param vertical
	 * 				True if the vertical energy should be displayed.
	 * 				False if the horizontal energy should be displayed.
	 * @param local
	 * 				True if only the local pixel energy should be displayed,
	 * 				false otherwise. If true, 'vertical' is ignored.
	 */
	public final void displayEnergyImage(final boolean vertical, final boolean local) {
		final String title = "Energy Function Visualization " + (local ? "(Local)" : (vertical ? "(Vertical)" : "(Horizontal)"));
		final int[][] energy = new SeamCarverImpl().computeImageEnergy(image, vertical, local);
		gui.displayExtraWindow(SeamCarverImage.createEnergyImage(energy, image.getType()), title);
	}

	/**
	 * Close the image that displays the energy function.
	 */
	public final void closeEnergyImage() {
		gui.closeExtraWindow();
	}



	private class SeamCarvingRunnable implements Runnable {
		boolean deleteVertical;
		boolean deleteHorizontal;
		int vertical;
		int horizontal;
		public SeamCarvingRunnable(final boolean deleteVertical, final boolean deleteHorizontal, final int vertical, final int horizontal) {
			this.deleteVertical = deleteVertical;
			this.deleteHorizontal = deleteHorizontal;
			this.vertical = vertical;
			this.horizontal = horizontal;
		}

		public void run() {
			if (deleteVertical) {
				for (int i=0; i<vertical; ++i) {
					removeOneSeam(true);
				}
			} else {
				insertSeams(true, Math.abs(vertical));
			}
			if (deleteHorizontal) {
				for (int i=0; i<horizontal; ++i) {
					removeOneSeam(false);
				}
			} else {
				insertSeams(false, Math.abs(horizontal));
			}
		}
	}

}
