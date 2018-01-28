import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextToPdf {
	
	
	
    public static String remove(String test) { //replaces string with valid text
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            if (WinAnsiEncoding.INSTANCE.contains(test.charAt(i))) {
                b.append(test.charAt(i));
            }
            else {
            	b.append(" "); //replace invalid new line characters with spaces
            }
        }
        return b.toString();
    }


	public static void pdfFromText(String text, String fileName) throws IOException {
		String filename = fileName + ".pdf";
		String message = remove(text); //remove illegible characters (including \n , which we replace with a space)
		File file = new File("fonts/OpenDyslexic3-Bold.ttf");

		PDDocument doc = new PDDocument();
		try {
			PDPage page = new PDPage();
			doc.addPage(page);
			PDPageContentStream contents = new PDPageContentStream(doc, page);
			contents.setNonStrokingColor(Color.getHSBColor(60, 10, 96)); //background color
			contents.addRect(0, 0, page.getMediaBox().getWidth(), page.getMediaBox().getHeight()); 
			contents.fill();
			contents.setNonStrokingColor(Color.BLACK); //text color
			//contents.setCharacterSpacing(5);
			PDFont font = PDType0Font.load(doc, file);

			float fontSize = 25;
			float leading = 1.5f * fontSize;

			PDRectangle mediabox = page.getMediaBox();
			float margin = 72 / 2;
			float width = mediabox.getWidth() - 2 * margin;
			float startX = mediabox.getLowerLeftX() + margin;
			float startY = mediabox.getUpperRightY() - margin;

			ArrayList<String> lines = new ArrayList<String>();
			int lastSpace = -1;
			while (message.length() > 0) { //code for wrapping text around in PDFBOX
				int spaceIndex = message.indexOf(' ', lastSpace + 1);
				if (spaceIndex < 0)
					spaceIndex = message.length();
				String subString = message.substring(0, spaceIndex);
				float size = fontSize * font.getStringWidth(subString) / 1000;
				if (size > width) {
					if (lastSpace < 0)
						lastSpace = spaceIndex;
					subString = message.substring(0, lastSpace);
					lines.add(subString);
					message = message.substring(lastSpace).trim();
					lastSpace = -1;
				} else if (spaceIndex == message.length()) {
					lines.add(message);
					message = "";
				} else {
					lastSpace = spaceIndex;
				}
			}

			contents.beginText();
			contents.setFont(font, fontSize);
			contents.newLineAtOffset(startX, startY);
			for (String line : lines) {
				contents.showText(line);
				contents.newLineAtOffset(0, -leading);
			}
			contents.endText();
			contents.close();

			doc.save(filename);
		} finally {
			doc.close();
			
			if (Desktop.isDesktopSupported()) { //open the pdf
			    try {
			        File myFile = new File(filename);
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        // no application registered for PDFs
			    	
			    }
			}
		}
		
		

	}


}