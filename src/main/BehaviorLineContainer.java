package main;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;

public class BehaviorLineContainer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<JPanel> list;
	
	public BehaviorLineContainer() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		list = new ArrayList<JPanel>();
	}
	
	public void AddLine(JPanel l)
	{
		list.add(l);
		l.setAlignmentX((float) 0.5);
		add(l);
	}
	
	public void Clear()
	{
		list = new ArrayList<JPanel>();
		removeAll();
		revalidate();
		this.getParent().repaint();
	}
}
