import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame {
	
	public MainFrame(){
		initLookAndFeel();
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
//		container.add(new MainPanel(this), BorderLayout.CENTER);
		container.add(new EditorPanel(this), BorderLayout.CENTER);
		
		
		pack();
//		setSize(300, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initLookAndFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
