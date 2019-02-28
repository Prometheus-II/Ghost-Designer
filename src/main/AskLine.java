package main;


import javax.swing.JLabel;
import javax.swing.JTextField;

import com.google.gson.JsonObject;


public class AskLine extends BehaviorLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtbehavior;
	
	public AskLine() {
		super();
		JLabel lblBehavior = new JLabel("Behavior");
		add(lblBehavior);
		
		txtbehavior = new JTextField();
		txtbehavior.setToolTipText("[...]Behavior");
		add(txtbehavior);
		txtbehavior.setColumns(10);
	}
		public String getBehavior()
		{
			return txtbehavior.getText();
		}
		
		public JsonObject getVals()
		{
			JsonObject returner = new JsonObject();
			returner.addProperty("line", getText());
			returner.addProperty("behave", getBehavior());
			return returner;
		}
}
