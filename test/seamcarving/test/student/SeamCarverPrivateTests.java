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
 
public class SeamCarverPrivateTests extends TestCase {
    private SeamCarver prog;
 
    @Override
    @Before
    public void setUp() {
        prog = new SeamCarverImpl();
    }
 
        //--------------------------
        // Exceptions Tests
        //--------------------------
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testEnergyFunctionSanityPublic1() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        try {
            prog.evaluateEnergyFunction(image, 0, 3);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testEnergyFunctionSanityPublic2() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        try {
            prog.evaluateEnergyFunction(image, 3, 0);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testEnergyFunctionSanityPublic3() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        try {
            prog.evaluateEnergyFunction(image, -1, 0);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testEnergyFunctionSanityPublic4() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        try {
            prog.evaluateEnergyFunction(image, 0, -1);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testEnergyFunctionSanityPublicImageNull() {
        BufferedImage image = null;
        try {
            prog.evaluateEnergyFunction(image, 0, 0);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputePixelEnergyImageNull() {
        BufferedImage image = null;
        try {
            prog.computePixelEnergy(image, 10, 10, null, true, true);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
     
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputePixelEnergy1() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-left
        try {
            prog.computePixelEnergy(image, -1, 1, null, true, true);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputePixelEnergy2() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-left
        try {
            prog.computePixelEnergy(image, 1, -1, null, true, true);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputePixelEnergy3() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-left
        try {
            prog.computePixelEnergy(image, image.getWidth(), 1, null, true, true);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputePixelEnergy4() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-left
        try {
            prog.computePixelEnergy(image, 1, image.getHeight(), null, true, true);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputePixelEnergyEnergyNullAndLocalFalse() {
        BufferedImage image = TestUtil.createImage(3, 3, 0);
        image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1)); // middle-left
        try {
            prog.computePixelEnergy(image, 1, 1, null, true, false);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputeImageEnergyImageNull() {
        BufferedImage image = null;
        try {
            prog.computeImageEnergy(image, true, true);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputeHorizontalSeamImageNull() {
        BufferedImage image = null;
        try {
            prog.computeHorizontalSeam(image);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputeHorizontalSeamImageRow() {
        BufferedImage image = TestUtil.createImage(3, 1, 0);
        try {
            prog.computeHorizontalSeam(image);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputeVerticalSeamImageNull() {
        BufferedImage image = null;
        try {
            prog.computeVerticalSeam(image);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputeVerticalSeamColumns() {
        BufferedImage image = TestUtil.createImage(1, 3, 0);
        try {
            prog.computeVerticalSeam(image);
        } catch (IllegalArgumentException e) {
            // expect this
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
        }
        fail("Expected IllegalArgumentException to be thrown for bad coordinate!\n");
    }
         
         
        //--------------------------
        // Matrix Tests
        //--------------------------
         
    @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputeSeamVertical() {
        BufferedImage image = TestUtil.createImage(4, 4, 0);
//      image.setRGB(0, 0, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(1, 0, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(2, 0, SeamCarverUtils.createRGBInt(0, 3, 0));
//                image.setRGB(3, 0, SeamCarverUtils.createRGBInt(0, 0, 1));
                 
//                image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 1));
//                image.setRGB(2, 1, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(3, 1, SeamCarverUtils.createRGBInt(4, 0, 1));
                 
                image.setRGB(0, 2, SeamCarverUtils.createRGBInt(0, 0, 1));
//                image.setRGB(1, 2, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(2, 2, SeamCarverUtils.createRGBInt(0, 0, 5));
//                image.setRGB(3, 2, SeamCarverUtils.createRGBInt(0, 0, 1));
                 
                image.setRGB(0, 3, SeamCarverUtils.createRGBInt(6, 0, 1));
                image.setRGB(1, 3, SeamCarverUtils.createRGBInt(0, 0, 1));
//                image.setRGB(2, 3, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(3, 3, SeamCarverUtils.createRGBInt(0, 0, 1));
                 
                 
        int[] refSeam = new int[4];
        // Leftmost (entirely white) column is the first seam to be removed.
        refSeam[0] = 0;
        refSeam[1] = 0;
        refSeam[2] = 1;
                refSeam[3] = 2;
        int[] testSeam = prog.computeSeam(image, true);
        assertTrue("First computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
                TestUtil.compareSeamArrays(refSeam, testSeam));
 
        // Remove the column.
        image = SeamCarverImage.removeSeam(image, testSeam, true);
 
        // Rightmost (entirely white) column is the second seam to be removed.
        refSeam[0] = 0;
        refSeam[1] = 0;
        refSeam[2] = 0;
                refSeam[3] = 0;
        testSeam = prog.computeSeam(image, true);
        assertTrue("Second computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
                TestUtil.compareSeamArrays(refSeam, testSeam));
    }
 
        @Test(timeout = DEFAULT_TIMEOUT)
    public void testComputeSeamHorizontal() {
        BufferedImage image = TestUtil.createImage(4, 4, 0);
//      image.setRGB(0, 0, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(1, 0, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(2, 0, SeamCarverUtils.createRGBInt(0, 3, 0));
//                image.setRGB(3, 0, SeamCarverUtils.createRGBInt(0, 0, 1));
                 
//                image.setRGB(0, 1, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(1, 1, SeamCarverUtils.createRGBInt(0, 0, 1));
//                image.setRGB(2, 1, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(3, 1, SeamCarverUtils.createRGBInt(4, 0, 1));
                 
                image.setRGB(0, 2, SeamCarverUtils.createRGBInt(0, 0, 1));
//                image.setRGB(1, 2, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(2, 2, SeamCarverUtils.createRGBInt(0, 0, 5));
//                image.setRGB(3, 2, SeamCarverUtils.createRGBInt(0, 0, 1));
                 
                image.setRGB(0, 3, SeamCarverUtils.createRGBInt(6, 0, 1));
                image.setRGB(1, 3, SeamCarverUtils.createRGBInt(0, 0, 1));
//                image.setRGB(2, 3, SeamCarverUtils.createRGBInt(0, 0, 1));
                image.setRGB(3, 3, SeamCarverUtils.createRGBInt(0, 0, 1));
                 
                 
        int[] refSeam = new int[4];
        // Leftmost (entirely white) column is the first seam to be removed.
        refSeam[0] = 0;
        refSeam[1] = 0;
        refSeam[2] = 0;
                refSeam[3] = 0;
        int[] testSeam = prog.computeSeam(image, false);
        assertTrue("First computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
                TestUtil.compareSeamArrays(refSeam, testSeam));
 
        // Remove the column.
        image = SeamCarverImage.removeSeam(image, testSeam, false);
 
        // Rightmost (entirely white) column is the second seam to be removed.
        refSeam[0] = 0;
        refSeam[1] = 0;
        refSeam[2] = 0;
                refSeam[3] = 0;
        testSeam = prog.computeSeam(image, false);
        assertTrue("Second computed seam (" + SeamCarverUtils.printSeamArray(testSeam) + ") did not match expected seam (" + SeamCarverUtils.printSeamArray(refSeam) + ").\n",
                TestUtil.compareSeamArrays(refSeam, testSeam));
    }
 
 
}