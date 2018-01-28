import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Demo extends JFrame {

	public Demo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(255,243,0));//background color
	    setSize(500,350); //window size
	    setTitle("DELEXIA");
		setFont(new Font("Arial", Font.BOLD, 30));
		final JLabel label = new JLabel("Select an image");
		label.setFont(new Font("Arial", Font.BOLD, 30)); //change font for comment
		

		String[] data = { "BookTitle.png","BookParagraph.jpg", "Spanish.png", "CautionSign.jpg","CautionSign2.png", "EmergencySign.jpg", "RoadSign.png" };
		final JList dataList = new JList(data);
		dataList.setFont(new Font("Arial", Font.BOLD, 30)); //change font for user selection
		dataList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					label.setText(dataList.getSelectedValue().toString());
					
					String userChoice = "pics/" + dataList.getSelectedValue().toString(); //add directory location to selected image
					
					
					//ShowPicture.showPic(userChoice);
					if (Desktop.isDesktopSupported()) { //open the picture selected
					    try {
					        File myFile = new File(userChoice);
					        Desktop.getDesktop().open(myFile);
					    } catch (IOException ex) {
					        // no application registered for PDFs
					    	
					    }
					}
					
					try {
						DyslexicView.getPdfFromUserChoice(userChoice);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		});
		add(dataList);
		add(label);

		setVisible(true);

	}

	public static void main(String args[]) {
		new Demo();
	}

}