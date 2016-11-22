import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class EditorPanel extends JPanel {
	public EditorPanel(MainFrame frame) {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		JTextArea area = new JTextArea(30, 25);

		JLabel packageLabel = new JLabel("Package");
		JLabel classNameLabel = new JLabel("Class name");
		JLabel parentClassLabel = new JLabel("Parent class");

		JTextField packageField = new JTextField("com.example", 22);
		JTextField classNameField = new JTextField("Example", 22);
		JTextField parentClassField = new JTextField("", 22);

		JTextField outputFolderField = new JTextField(System.getProperty("user.dir"));
		outputFolderField.setEditable(false);

		JCheckBox realmBox = new JCheckBox("Realm");
		JCheckBox serializableBox = new JCheckBox("Serializable");

		JButton outputFolderButton = new JButton("...");
		JButton generateButton = new JButton("Generate");
		JButton openButton = new JButton("Open Folder");

		realmBox.setSelected(true);
		serializableBox.setSelected(true);

		add(area);

		add(packageLabel);
		add(classNameLabel);
//		add(parentClassLabel);

		add(packageField);
		add(classNameField);
//		add(parentClassField);

		add(outputFolderField);

		add(realmBox);
		add(serializableBox);

		add(outputFolderButton);
		add(generateButton);
		add(openButton);

		layout.putConstraint(SpringLayout.WEST, area, 15, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, area, 10, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, packageLabel, 15, SpringLayout.EAST, area);
		layout.putConstraint(SpringLayout.NORTH, packageLabel, 10, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, packageField, 5, SpringLayout.EAST, packageLabel);
		layout.putConstraint(SpringLayout.NORTH, packageField, 5, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, classNameLabel, 15, SpringLayout.EAST, area);
		layout.putConstraint(SpringLayout.NORTH, classNameLabel, 10, SpringLayout.SOUTH, packageLabel);

		layout.putConstraint(SpringLayout.WEST, classNameField, 5, SpringLayout.EAST, classNameLabel);
		layout.putConstraint(SpringLayout.NORTH, classNameField, 0, SpringLayout.SOUTH, packageField);

		layout.putConstraint(SpringLayout.WEST, parentClassLabel, 15, SpringLayout.EAST, area);
		layout.putConstraint(SpringLayout.NORTH, parentClassLabel, 10, SpringLayout.SOUTH, classNameLabel);

		layout.putConstraint(SpringLayout.WEST, parentClassField, 5, SpringLayout.EAST, parentClassLabel);
		layout.putConstraint(SpringLayout.NORTH, parentClassField, 0, SpringLayout.SOUTH, classNameField);

		layout.putConstraint(SpringLayout.WEST, realmBox, 15, SpringLayout.EAST, area);
		layout.putConstraint(SpringLayout.NORTH, realmBox, 20, SpringLayout.SOUTH, parentClassLabel);

		layout.putConstraint(SpringLayout.WEST, serializableBox, 15, SpringLayout.EAST, area);
		layout.putConstraint(SpringLayout.NORTH, serializableBox, 0, SpringLayout.SOUTH, realmBox);

		layout.putConstraint(SpringLayout.WEST, generateButton, 15, SpringLayout.EAST, area);
		layout.putConstraint(SpringLayout.SOUTH, generateButton, 0, SpringLayout.SOUTH, area);

		layout.putConstraint(SpringLayout.WEST, openButton, 0, SpringLayout.EAST, generateButton);
		layout.putConstraint(SpringLayout.SOUTH, openButton, 0, SpringLayout.SOUTH, area);

		layout.putConstraint(SpringLayout.EAST, outputFolderButton, 0, SpringLayout.EAST, parentClassField);
		layout.putConstraint(SpringLayout.NORTH, outputFolderButton, 0, SpringLayout.NORTH, outputFolderField);

		layout.putConstraint(SpringLayout.WEST, outputFolderField, 15, SpringLayout.EAST, area);
		layout.putConstraint(SpringLayout.EAST, outputFolderField, 0, SpringLayout.WEST, outputFolderButton);
		layout.putConstraint(SpringLayout.SOUTH, outputFolderField, 0, SpringLayout.NORTH, generateButton);

		layout.putConstraint(SpringLayout.EAST, packageField, 0, SpringLayout.EAST, parentClassField);
		layout.putConstraint(SpringLayout.EAST, classNameField, 0, SpringLayout.EAST, parentClassField);

		layout.putConstraint(SpringLayout.EAST, this, 15, SpringLayout.EAST, parentClassField);

		layout.putConstraint(SpringLayout.SOUTH, this, 15, SpringLayout.SOUTH, area);

		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Converter(outputFolderField.getText(), packageField.getText(), classNameField.getText(),
						area.getText(), realmBox.isSelected(), serializableBox.isSelected());
			}
		});

		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				try {
					Desktop.getDesktop().open(new File(outputFolderField.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectFolder(outputFolderField);
			}
		};
		
		outputFolderButton.addActionListener(listener);
		outputFolderField.addActionListener(listener);
	}

	protected void selectFolder(JTextField field) {
		JFileChooser fileChooser = new JFileChooser(field.getText());
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = fileChooser.getSelectedFile();
			field.setText(selectedFolder.getAbsolutePath());
		}
	}
}
