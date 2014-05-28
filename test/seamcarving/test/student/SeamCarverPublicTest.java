package seamcarving.test.student;

import static seamcarving.test.student.TestUtil.DEFAULT_TIMEOUT;

import java.awt.image.BufferedImage;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import seamcarving.SeamCarver;
import seamcarving.SeamCarverImage;
import seamcarving.SeamCarverImpl;
import seamcarving.SeamCarverUtils;

public class SeamCarverPublicTest extends TestCase {
	private SeamCarver prog;

	@Override
	@Before
	public void setUp() {
		prog = new SeamCarverImpl();
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testEnergyFunctionSanityPublic() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		try {
			prog.evaluateEnergyFunction(image, 0, 10);
		} catch (IllegalArgumentException e) {
			// Expecting this
			return;
		} catch (Exception e) {
			fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
		}
		fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
	}
	
	// this test checks whether the function correctly computes the sum of the
	// deltas of the upper and left neighbor-pixels
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testEnergyFunctionPublic1() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-center
		assertEquals("Result of the energy function did not match expected result for pixel (0, 0).\n",
				0, prog.evaluateEnergyFunction(image, 0, 0));
		assertEquals("Result of the energy function did not match expected result for pixel (1, 0).\n",
				0, prog.evaluateEnergyFunction(image, 1, 0));
		assertEquals("Result of the energy function did not match expected result for pixel (2, 0).\n",
				0, prog.evaluateEnergyFunction(image, 2, 0));
		assertEquals("Result of the energy function did not match expected result for pixel (0, 1).\n",
				0, prog.evaluateEnergyFunction(image, 0, 1));
		assertEquals("Result of the energy function did not match expected result for pixel (1, 1).\n",
				2, prog.evaluateEnergyFunction(image, 1, 1));
		assertEquals("Result of the energy function did not match expected result for pixel (2, 1).\n",
				1, prog.evaluateEnergyFunction(image, 2, 1));
		assertEquals("Result of the energy function did not match expected result for pixel (0, 2).\n",
				0, prog.evaluateEnergyFunction(image, 0, 2));
		assertEquals("Result of the energy function did not match expected result for pixel (1, 2).\n",
				1, prog.evaluateEnergyFunction(image, 1, 2));
		assertEquals("Result of the energy function did not match expected result for pixel (2, 2).\n",
				0, prog.evaluateEnergyFunction(image, 2, 2));
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testEnergyFunctionPublic2() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 255)); // middle-center
		image.setRGB(2, 1, SeamCarverUtils.createRGBInt(0, 0, 255)); // middle-right
		assertEquals("Result of the energy function did not match expected result for pixel (0, 0).\n",
				0, prog.evaluateEnergyFunction(image, 0, 0));
		assertEquals("Result of the energy function did not match expected result for pixel (1, 0).\n",
				0, prog.evaluateEnergyFunction(image, 1, 0));
		assertEquals("Result of the energy function did not match expected result for pixel (2, 0).\n",
				0, prog.evaluateEnergyFunction(image, 2, 0));
		assertEquals("Result of the energy function did not match expected result for pixel (0, 1).\n",
				0, prog.evaluateEnergyFunction(image, 0, 1));
		assertEquals("Result of the energy function did not match expected result for pixel (1, 1).\n",
				130050, prog.evaluateEnergyFunction(image, 1, 1));
		assertEquals("Result of the energy function did not match expected result for pixel (2, 1).\n",
				65025, prog.evaluateEnergyFunction(image, 2, 1));
		assertEquals("Result of the energy function did not match expected result for pixel (0, 2).\n",
				0, prog.evaluateEnergyFunction(image, 0, 2));
		assertEquals("Result of the energy function did not match expected result for pixel (1, 2).\n",
				65025, prog.evaluateEnergyFunction(image, 1, 2));
		assertEquals("Result of the energy function did not match expected result for pixel (2, 2).\n",
				65025, prog.evaluateEnergyFunction(image, 2, 2));
	}
	
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testComputeSeamSanityPublic() {
		try {
			prog.computeSeam(null, true);
		} catch (IllegalArgumentException e) {
			// Expecting this
			return;
		} catch (Exception e) {
			fail("Expected IllegalArgumentException to be thrown for image == null!\n");
		}
		fail("Expected IllegalArgumentException to be thrown for image == null!\n");
	}
	
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testComputeSeamPublic1() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-center
		int[] refSeam = new int[3];
		// Leftmost (entirely white) column is the first seam to be removed.
		refSeam[0] = 0;
		refSeam[1] = 0;
		refSeam[2] = 0;
		int[] testSeam = prog.computeSeam(image, true);
		assertTrue("First computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));

		// Remove the column.
		image = SeamCarverImage.removeSeam(image, testSeam, true);

		// Rightmost (entirely white) column is the second seam to be removed.
		refSeam[0] = 1;
		refSeam[1] = 1;
		refSeam[2] = 1;
		testSeam = prog.computeSeam(image, true);
		assertTrue("Second computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testComputeSeamPublic2() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 255)); // middle-left
		image.setRGB(2, 1, SeamCarverUtils.createRGBInt(0, 0, 255)); // middle-right
		int[] refSeam = new int[3];
		// The first seam to be removed is top-center-> middle-center -> bottom-center.
		refSeam[0] = 1;
		refSeam[1] = 1;
		refSeam[2] = 1;
		int[] testSeam = prog.computeSeam(image, true);
		assertTrue("First computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));

		// Remove the column.
		image = SeamCarverImage.removeSeam(image, testSeam, true);

		// The second seam to be removed is top-left -> middle-left -> bottom-left.
		refSeam[0] = 0;
		refSeam[1] = 0;
		refSeam[2] = 0;
		testSeam = prog.computeSeam(image, true);
		assertTrue("Second computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testComputeSeamPublic3() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(2, 0, SeamCarverUtils.createRGBInt(0, 0, 1)); // top-right
		image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-left
		image.setRGB(2, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-right
		image.setRGB(1, 2, SeamCarverUtils.createRGBInt(0, 0, 1)); // bottom-center
		int[] refSeam = new int[3];
		// The first seam to be removed is top-left -> middle-left -> bottom-left.
		refSeam[0] = 0;
		refSeam[1] = 0;
		refSeam[2] = 0;
		int[] testSeam = prog.computeSeam(image, true);
		assertTrue("First computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));

		// Remove the column.
		image = SeamCarverImage.removeSeam(image, testSeam, true);

		// The second seam to be removed is top-left -> middle-left -> bottom-left.
		refSeam[0] = 0;
		refSeam[1] = 0;
		refSeam[2] = 0;
		testSeam = prog.computeSeam(image, true);
		assertTrue("Second computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));
	}

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testComputeSeamPublic4() {
		System.out.println("Running testComputeSeamNightly5.");
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-left
		int[] refSeam = new int[3];
		// The first seam to be removed is top-right -> middle-right -> bottom-center.
		refSeam[0] = 2;
		refSeam[1] = 2;
		refSeam[2] = 1;
		int[] testSeam = prog.computeSeam(image, true);
		assertTrue("First computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));

		// Remove the column.
		image = SeamCarverImage.removeSeam(image, testSeam, true);

		// The second seam to be removed is top-right -> middle-right -> bottom-right.
		refSeam[0] = 1;
		refSeam[1] = 1;
		refSeam[2] = 1;
		testSeam = prog.computeSeam(image, true);
		assertTrue("Second computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));
	}

}
