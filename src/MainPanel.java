import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class MainPanel extends JPanel {
	
	public MainPanel(MainFrame frame){
		SpringLayout layout = new SpringLayout();
		setLayout(new GridLayout(1, 1));
		
		JTextArea area =new JTextArea();
		JScrollPane pane = new JScrollPane(area);
		add(pane);
		
	}

}
