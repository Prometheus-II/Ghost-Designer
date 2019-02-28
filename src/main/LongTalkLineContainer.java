package main;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import com.google.gson.JsonArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class LongTalkLineContainer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	ArrayList<LongTalkLine> list;
	JPanel panel;
	File ghostRoot;
	
	public LongTalkLineContainer(File gr) {
		ghostRoot = gr;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		setMaximumSize(new Dimension(500,300));
		
		JButton btnAddValues = new JButton("Add Values");
		btnAddValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LongTalkLine l = new LongTalkLine();
				addLine(l);
				revalidate();
			}
		});
		add(btnAddValues, BorderLayout.SOUTH);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JScrollPane scroll = new JScrollPane(panel);
		add(scroll, BorderLayout.CENTER);
		
		
		list = new ArrayList<LongTalkLine>();
	}
	
	public void addLine(LongTalkLine l)
	{
		l.setAlignmentX((float) 0.5);
		l.parent = this;
		list.add(l);
		panel.add(l);
	}
	
	public ChainGroup getValues()
	{
		ChainGroup returner = new ChainGroup(list.size());
		for(int i = 0; i < list.size(); i++)
		{
			returner.stmts[i] = list.get(i).getText();
			returner.times[i] = list.get(i).getTime();
			returner.imgStrs[i] = list.get(i).getImg();
		}
		
		return returner;
	}
	
	public JsonArray getStatements()
	{
		JsonArray returner = new JsonArray();
		for(int i = 0; i < list.size(); i++)
		{
			returner.add(list.get(i).getText());
		}
		return returner;
	}
	
	public JsonArray getTimes()
	{
		JsonArray returner = new JsonArray();
		for(int i = 0; i < list.size(); i++)
		{
			returner.add(list.get(i).getTime());
		}
		return returner;
	}
	public JsonArray getImages()
	{
		JsonArray returner = new JsonArray();
		for(int i = 0; i < list.size(); i++)
		{
			returner.add(list.get(i).getImg());
		}
		return returner;
	}
}
