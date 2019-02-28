package main;

import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class BehaviorLine extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public BehaviorLine() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setMaximumSize(new Dimension(500, 30));
		
		JLabel lblText = new JLabel("Text");
		add(lblText);
		
		textField = new JTextField();
		lblText.setLabelFor(textField);
		add(textField);
		textField.setColumns(10);
	}
	
	public String getText()
	{
		return textField.getText();
	}
}
