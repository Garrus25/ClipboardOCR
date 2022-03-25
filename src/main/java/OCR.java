import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class OCR {
    private String text = "";

    public BufferedImage processedImage;

    public String performOCR(BufferedImage rawImage) {
        processedImage = rawImage;
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setLanguage("pol");
            tesseract.setTessVariable("user_defined_dpi", "300");
            tesseract.setDatapath("C:\\Users\\mati1\\IdeaProjects\\Tess4J\\tessdata");

            processedImage = resizeImage(processedImage);
            processedImage = sharpenImage(processedImage);
            text = tesseract.doOCR(processedImage);

        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return text;
    }

    private BufferedImage resizeImage(BufferedImage bufferedImage) {
        return Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                1280, 500, Scalr.OP_ANTIALIAS);
    }

    private BufferedImage sharpenImage(BufferedImage bufferedImage) {
        Kernel kernel = new Kernel(3, 3, new float[]
                {-1, -1, -1,
                        -1, 9, -1,
                        -1, -1, -1});
        BufferedImageOp op = new ConvolveOp(kernel);
        bufferedImage = op.filter(bufferedImage, null);
        return bufferedImage;
    }
}
