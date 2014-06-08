package seamcarving.test.student;

import static seamcarving.test.student.TestUtil.DEFAULT_TIMEOUT;
import static org.junit.Assert.assertArrayEquals;

import java.awt.image.BufferedImage;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import seamcarving.SeamCarver;
import seamcarving.SeamCarverImage;
import seamcarving.SeamCarverImpl;
import seamcarving.SeamCarverUtils;
public class Test_Matrix extends TestCase{
	private SeamCarver prog;

	@Override
	@Before
	public void setUp() {
		prog = new SeamCarverImpl();
	}
	
	@Test(timeout = DEFAULT_TIMEOUT)
	public void test_EMatrix_mitte_mitte() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-center
		int[][]testE	= prog.computeImageEnergy(image, true, true);
		int[][]E = new int[3][3];
		
		E[0][0] = 0;
		E[1][0] = 0;
		E[2][0] = 0;
		E[0][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(0, 0));
		E[1][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(1, 1)) + SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(1, 0));
		E[2][1] = SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(2, 1)) + SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 0));
		E[0][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(0, 1));
		E[1][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(1, 2)) + SeamCarverUtils.getDelta(image.getRGB(1, 2), image.getRGB(1, 1));
		E[2][2] = SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 2)) + SeamCarverUtils.getDelta(image.getRGB(2, 2), image.getRGB(1, 2));
		assertArrayEquals("Fehler bei Energiematrix", E, testE);
		
	}
	
	public void test_EMatrix_links_mitte_rechts_mitte() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 255)); // middle-center
		image.setRGB(2, 1, SeamCarverUtils.createRGBInt(0, 0, 255));
		int[][]testE	= prog.computeImageEnergy(image, true, true);
		int[][]E = new int[3][3];
		E[0][0] = 0;
		E[1][0] = 0;
		E[2][0] = 0;
		E[0][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(0, 0));
		E[1][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(1, 1)) + SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(1, 0));
		E[2][1] = SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(2, 1)) + SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 0));
		E[0][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(0, 1));
		E[1][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(1, 2)) + SeamCarverUtils.getDelta(image.getRGB(1, 2), image.getRGB(1, 1));
		E[2][2] = SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 2)) + SeamCarverUtils.getDelta(image.getRGB(2, 2), image.getRGB(1, 2));
		
		assertArrayEquals("Fehler bei Energiematrix", E, testE);
		
	}
	public void test_EMatrix_ecke_links_oben() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(0, 0, SeamCarverUtils.createRGBInt(0, 0, 255)); // top_left
		int[][]testE	= prog.computeImageEnergy(image, true, true);
		int[][]E = new int[3][3];
		E[0][0] = 0;
		E[1][0] = SeamCarverUtils.getDelta(image.getRGB(0, 0), image.getRGB(1, 0));
		E[2][0] = SeamCarverUtils.getDelta(image.getRGB(1, 0), image.getRGB(2, 0));
		E[0][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(0, 0));
		E[1][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(1, 1)) + SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(1, 0));
		E[2][1] = SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(2, 1)) + SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 0));
		E[0][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(0, 1));
		E[1][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(1, 2)) + SeamCarverUtils.getDelta(image.getRGB(1, 2), image.getRGB(1, 1));
		E[2][2] = SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 2)) + SeamCarverUtils.getDelta(image.getRGB(2, 2), image.getRGB(1, 2));
		
		assertArrayEquals("Fehler bei Energiematrix", E, testE);
		
	}
	/**
	public void test_local_Matrix(){
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 255)); // top_left
		image.setRGB(1, 0, SeamCarverUtils.createRGBInt(0, 0, 1));
		int[][]testE	= prog.
		int[][]E = new int[3][3];
	}
	**/
	
	public void test_EMatrix_ecke_rechts_oben() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(2, 0, SeamCarverUtils.createRGBInt(0, 0, 1)); // top_left
		int[][]testE	= prog.computeImageEnergy(image, true, true);
		int[][]E = new int[3][3];
		E[0][0] = 0;
		E[1][0] = SeamCarverUtils.getDelta(image.getRGB(0, 0), image.getRGB(1, 0));
		E[2][0] = SeamCarverUtils.getDelta(image.getRGB(1, 0), image.getRGB(2, 0));
		E[0][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(0, 0));
		E[1][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(1, 1)) + SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(1, 0));
		E[2][1] = SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(2, 1)) + SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 0));
		E[0][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(0, 1));
		E[1][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(1, 2)) + SeamCarverUtils.getDelta(image.getRGB(1, 2), image.getRGB(1, 1));
		E[2][2] = SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 2)) + SeamCarverUtils.getDelta(image.getRGB(2, 2), image.getRGB(1, 2));
		
		assertArrayEquals("Fehler bei Energiematrix", E, testE);
		
	}
	
	public void test_EMatrix_ecke_links_unten() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(0, 2, SeamCarverUtils.createRGBInt(0, 0, 1)); // top_left
		int[][]testE	= prog.computeImageEnergy(image, true, true);
		int[][]E = new int[3][3];
		E[0][0] = 0;
		E[1][0] = SeamCarverUtils.getDelta(image.getRGB(0, 0), image.getRGB(1, 0));
		E[2][0] = SeamCarverUtils.getDelta(image.getRGB(1, 0), image.getRGB(2, 0));
		E[0][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(0, 0));
		E[1][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(1, 1)) + SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(1, 0));
		E[2][1] = SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(2, 1)) + SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 0));
		E[0][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(0, 1));
		E[1][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(1, 2)) + SeamCarverUtils.getDelta(image.getRGB(1, 2), image.getRGB(1, 1));
		E[2][2] = SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 2)) + SeamCarverUtils.getDelta(image.getRGB(2, 2), image.getRGB(1, 2));
		
		assertArrayEquals("Fehler bei Energiematrix", E, testE);
		
	}
	
	public void test_EMatrix_ecke_rechts_unten() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(2, 2, SeamCarverUtils.createRGBInt(0, 0, 1)); // top_left
		int[][]testE	= prog.computeImageEnergy(image, true, true);
		int[][]E = new int[3][3];
		E[0][0] = 0;
		E[1][0] = SeamCarverUtils.getDelta(image.getRGB(0, 0), image.getRGB(1, 0));
		E[2][0] = SeamCarverUtils.getDelta(image.getRGB(1, 0), image.getRGB(2, 0));
		E[0][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(0, 0));
		E[1][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(1, 1)) + SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(1, 0));
		E[2][1] = SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(2, 1)) + SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 0));
		E[0][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(0, 1));
		E[1][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(1, 2)) + SeamCarverUtils.getDelta(image.getRGB(1, 2), image.getRGB(1, 1));
		E[2][2] = SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 2)) + SeamCarverUtils.getDelta(image.getRGB(2, 2), image.getRGB(1, 2));
		
		assertArrayEquals("Fehler bei Energiematrix", E, testE);
		
	}
	
	public void test_EMatrix_not_quadratic() {
		BufferedImage image = TestUtil.createImage(2, 3, 0);
		image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-center
		int[][]testE	= prog.computeImageEnergy(image, true, true);
		int[][]E = new int[2][3];
		
		E[0][0] = 0;
		E[1][0] = 0;
		E[0][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(0, 0));
		E[1][1] = SeamCarverUtils.getDelta(image.getRGB(0, 1), image.getRGB(1, 1)) + SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(1, 0));
		//E[2][1] = SeamCarverUtils.getDelta(image.getRGB(1, 1), image.getRGB(2, 1)) + SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 0));
		E[0][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(0, 1));
		E[1][2] = SeamCarverUtils.getDelta(image.getRGB(0, 2), image.getRGB(1, 2)) + SeamCarverUtils.getDelta(image.getRGB(1, 2), image.getRGB(1, 1));
		//E[2][2] = SeamCarverUtils.getDelta(image.getRGB(2, 1), image.getRGB(2, 2)) + SeamCarverUtils.getDelta(image.getRGB(2, 2), image.getRGB(1, 2));
		assertArrayEquals("Fehler bei Energiematrix", E, testE);
		
	}
	
	public void testSeamHorizontal() {
		BufferedImage image = TestUtil.createImage(3, 3, 0);
		image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-center
		int[] refSeam = new int[3];
		// Leftmost (entirely white) column is the first seam to be removed.
		refSeam[0] = 0;
		refSeam[1] = 0;
		refSeam[2] = 0;
		int[] testSeam = prog.computeSeam(image, false);
		assertTrue("First computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));

		// Remove the column.
		image = SeamCarverImage.removeSeam(image, testSeam, false);

		// Rightmost (entirely white) column is the second seam to be removed.
		refSeam[0] = 1;
		refSeam[1] = 1;
		refSeam[2] = 1;
		testSeam = prog.computeSeam(image, false);
		assertTrue("Second computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
				TestUtil.compareSeamArrays(refSeam, testSeam));
	}
	
}
