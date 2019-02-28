package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class RandomizerLine extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4043761874902051168L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RandomizerLine() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setMaximumSize(new Dimension(500, 30));
		JLabel lblBehavior = new JLabel("Behavior");
		add(lblBehavior);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);

	}
	
	public String getBehavior()
	{
		return textField.getText();
	}
}
