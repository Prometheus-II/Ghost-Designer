package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LongTalkLine extends BehaviorLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LongTalkLineContainer parent;
	String imageUsed;
	private JLabel selectedName;
	private JFormattedTextField numField;
	
	public LongTalkLine() {
		super();
		JButton ImageSelect = new JButton("Select an image");
		ImageSelect.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent arg0)
        	{
				if(parent.ghostRoot == null)
				{
					System.out.println("Choose a root for the ghost first, so you can get the picture from resources");
					return;
				}
        		JFileChooser chooser = new JFileChooser();
        		FileNameExtensionFilter filter = new FileNameExtensionFilter(
        		        "JPG & PNG Images", "jpg", "png");
        		chooser.setFileFilter(filter);
        		chooser.setCurrentDirectory(parent.ghostRoot);
        		int returnVal = chooser.showOpenDialog(ImageSelect);
        	    if(returnVal == JFileChooser.APPROVE_OPTION) {
        	       imageUsed = chooser.getSelectedFile().getName();
        	       selectedName.setText(imageUsed);
        	    }
        	}
        });
		
		JLabel lblActtime = new JLabel("Speak Time");
		add(lblActtime);
		lblActtime.setLabelFor(numField);
		numField = new JFormattedTextField(NumberFormat.getNumberInstance());
		add(numField);
		numField.setColumns(2);
		add(ImageSelect);
		
		selectedName = new JLabel("(none)");
		add(selectedName);
		
	}
	public String getImg()
	{
		return imageUsed;
	}
	public float getTime()
	{
		return Float.parseFloat(numField.getText());
	}
}
