import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class ShowPicture {
	
	public static void showPic(String pic) {
		JFrame frame = new JFrame();
		//ImageIcon icon = new ImageIcon("pics/caution.jpg");
		ImageIcon icon = new ImageIcon(pic);
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon("pics/caution.jpg");
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}