import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

public class DyslexicView {

	
	public static void getPdfFromUserChoice(String fileName) throws IOException {
		String text = ImageToText.getText(fileName);
	       System.out.println(text);
	       fileName =  FilenameUtils.removeExtension(fileName); //remove file extension for new pdf name
	       fileName = fileName.substring(5); // remove first 4 letters of the directory of the original image. Here, we remove /pics . 
	       TextToPdf.pdfFromText(text,fileName);
		
	}
	
    public static void main(String[] args) throws IOException {
    String fileName = "pics/table.png";
       String text = ImageToText.getText(fileName);
       System.out.println(text);
       fileName =  FilenameUtils.removeExtension(fileName); //remove file extension for new pdf name
       TextToPdf.pdfFromText(text, fileName);
    }
    
    
    
}
 