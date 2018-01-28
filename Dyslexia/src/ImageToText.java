import java.io.File;
import net.sourceforge.tess4j.*;



public class ImageToText { 
	//followed tutorial from https://dzone.com/articles/extracting-text-from-image-converting-image-text
    public static String getText(String filePath) {
        File imageFile = new File(filePath);
        ITesseract instance = new Tesseract();
        try {
            String result = instance.doOCR(imageFile); 
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "Error while reading image";
        } 
    }
    
    public static void main(String[] args) {
        System.out.println(getText("pics/table.png"));
    }
}
