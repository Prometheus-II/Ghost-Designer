package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import javax.swing.JFormattedTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.BevelBorder;

public class App {

	File ghostRoot;	
	String imageUsed;
	
	private JFrame frame;
	
	private JTextField txtbehavior;
	private JFormattedTextField MinTimeField;
	private JFormattedTextField MaxTimeField;
	private JCheckBox IsTimed;
	private JCheckBox ImplementsIRemember;
	private JComboBox<String> BehaviorCB;
	private JButton ImageSelect;
	private JLabel selectedImg;
	private JButton NewLongTalk;
	private JButton NewNormal;
	private JButton NewAskOption;
	private JFormattedTextField TimeField;
	private JLabel ActTimeLbl;
	private BehaviorLineContainer cont;
	private JButton btnDone;
	private JButton btnCancel;
	private JTextField GiveUptxtbx;
	private JPanel AskGiveUpPanel;
	private JButton NewRandom;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Ghost Designer");
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel selectedName = new JLabel("(No folder selected)");
		selectedName.setBounds(131, 4, 113, 16);
		frame.getContentPane().add(selectedName);
		
		JButton FolderSelect = new JButton("Select a folder...");
		FolderSelect.setBounds(0, 0, 127, 25);
		FolderSelect.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent arg0)
        	{
        		JFileChooser choose = new JFileChooser();
        		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        		choose.setAcceptAllFileFilterUsed(false);
        		int returnVal = choose.showOpenDialog(frame);
        	    if(returnVal == JFileChooser.APPROVE_OPTION) {
        	       ghostRoot = choose.getSelectedFile();
        	       selectedName.setText(ghostRoot.getAbsolutePath());
        	    }
        	}
        });
		frame.getContentPane().add(FolderSelect);
		
		JLabel lblSelectTheFolder = new JLabel("<html><body style='width: 375px'>Select the folder where the ghost you're working with is placed. (If you downloaded it as a .zip, be sure to unzip it into its own folder first!)</html>");
		lblSelectTheFolder.setBounds(258, 4, 492, 37);
		frame.getContentPane().add(lblSelectTheFolder);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 40, 250, 650);
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblBehaviorName = new JLabel("Behavior Name (format as [...]Behavior)");
		GridBagConstraints gbc_lblBehaviorName = new GridBagConstraints();
		gbc_lblBehaviorName.anchor = GridBagConstraints.WEST;
		gbc_lblBehaviorName.insets = new Insets(0, 0, 5, 0);
		gbc_lblBehaviorName.gridx = 0;
		gbc_lblBehaviorName.gridy = 0;
		panel.add(lblBehaviorName, gbc_lblBehaviorName);
		
		txtbehavior = new JTextField();
		GridBagConstraints gbc_txtbehavior = new GridBagConstraints();
		gbc_txtbehavior.insets = new Insets(0, 0, 5, 0);
		gbc_txtbehavior.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtbehavior.gridx = 0;
		gbc_txtbehavior.gridy = 1;
		panel.add(txtbehavior, gbc_txtbehavior);
		txtbehavior.setColumns(10);
		
		JLabel lblBehaviorType = new JLabel("Behavior Type");
		GridBagConstraints gbc_lblBehaviorType = new GridBagConstraints();
		gbc_lblBehaviorType.anchor = GridBagConstraints.WEST;
		gbc_lblBehaviorType.insets = new Insets(0, 0, 5, 0);
		gbc_lblBehaviorType.gridx = 0;
		gbc_lblBehaviorType.gridy = 3;
		panel.add(lblBehaviorType, gbc_lblBehaviorType);
		
		BehaviorCB = new JComboBox<String>();
		GridBagConstraints gbc_BehaviorCB = new GridBagConstraints();
		gbc_BehaviorCB.anchor = GridBagConstraints.WEST;
		gbc_BehaviorCB.insets = new Insets(0, 0, 5, 0);
		gbc_BehaviorCB.gridx = 0;
		gbc_BehaviorCB.gridy = 4;
		panel.add(BehaviorCB, gbc_BehaviorCB);
		BehaviorCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BehaviorCB.getSelectedItem() == "Normal")
				{
					NewNormal.setEnabled(true);
					NewLongTalk.setEnabled(false);
					NewAskOption.setEnabled(false);
					NewRandom.setEnabled(false);
					
					TimeField.setEnabled(true);
					ActTimeLbl.setEnabled(true);
					
					ImageSelect.setEnabled(true);
					selectedImg.setEnabled(true);
					AskGiveUpPanel.setVisible(false);
				}
				if(BehaviorCB.getSelectedItem() == "Ask")
				{
					NewNormal.setEnabled(true);
					NewLongTalk.setEnabled(false);
					NewAskOption.setEnabled(true);
					NewRandom.setEnabled(false);
					
					TimeField.setEnabled(true);
					ActTimeLbl.setEnabled(true);
					
					ImageSelect.setEnabled(true);
					selectedImg.setEnabled(true);
					AskGiveUpPanel.setVisible(true);
				}
				if(BehaviorCB.getSelectedItem() == "LongTalk")
				{
					NewLongTalk.setEnabled(true);
					NewNormal.setEnabled(false);
					NewAskOption.setEnabled(false);
					NewRandom.setEnabled(false);
					
					TimeField.setEnabled(false);
					ActTimeLbl.setEnabled(false);
					ImageSelect.setEnabled(false);
					selectedImg.setEnabled(false);
					AskGiveUpPanel.setVisible(false);
				}
				if(BehaviorCB.getSelectedItem() == "Randomizer")
				{
					NewLongTalk.setEnabled(false);
					NewNormal.setEnabled(false);
					NewAskOption.setEnabled(false);
					NewRandom.setEnabled(true);
					
					TimeField.setEnabled(false);
					ActTimeLbl.setEnabled(false);
					ImageSelect.setEnabled(false);
					selectedImg.setEnabled(false);
					AskGiveUpPanel.setVisible(false);
				}
				cont.Clear();
			}
		});
		BehaviorCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Select...", "Normal", "Ask", "LongTalk", "Randomizer"}));
		
		ImageSelect = new JButton("Select an image");
		GridBagConstraints gbc_ImageSelect = new GridBagConstraints();
		gbc_ImageSelect.anchor = GridBagConstraints.WEST;
		gbc_ImageSelect.insets = new Insets(0, 0, 5, 0);
		gbc_ImageSelect.gridx = 0;
		gbc_ImageSelect.gridy = 6;
		panel.add(ImageSelect, gbc_ImageSelect);
		
		selectedImg = new JLabel("(none)");
		GridBagConstraints gbc_selectedImg = new GridBagConstraints();
		gbc_selectedImg.anchor = GridBagConstraints.WEST;
		gbc_selectedImg.insets = new Insets(0, 0, 5, 0);
		gbc_selectedImg.gridx = 0;
		gbc_selectedImg.gridy = 7;
		panel.add(selectedImg, gbc_selectedImg);
		
		ActTimeLbl = new JLabel("Act Time");
		GridBagConstraints gbc_ActTimeLbl = new GridBagConstraints();
		gbc_ActTimeLbl.insets = new Insets(0, 0, 5, 0);
		gbc_ActTimeLbl.anchor = GridBagConstraints.WEST;
		gbc_ActTimeLbl.gridx = 0;
		gbc_ActTimeLbl.gridy = 9;
		panel.add(ActTimeLbl, gbc_ActTimeLbl);
		
		TimeField = new JFormattedTextField(NumberFormat.getNumberInstance());
		GridBagConstraints gbc_TimeField = new GridBagConstraints();
		gbc_TimeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_TimeField.insets = new Insets(0, 0, 5, 0);
		gbc_TimeField.gridx = 0;
		gbc_TimeField.gridy = 10;
		panel.add(TimeField, gbc_TimeField);
		
		ImplementsIRemember = new JCheckBox("Remembers information?");
		GridBagConstraints gbc_ImplementsIRemember = new GridBagConstraints();
		gbc_ImplementsIRemember.anchor = GridBagConstraints.WEST;
		gbc_ImplementsIRemember.insets = new Insets(0, 0, 5, 0);
		gbc_ImplementsIRemember.gridx = 0;
		gbc_ImplementsIRemember.gridy = 12;
		panel.add(ImplementsIRemember, gbc_ImplementsIRemember);
		
		JLabel RememberWarning = new JLabel("<html><body style='width: 200px'>(Warning: to make use of remembered information, you'll need to edit the code yourself.)</html>");
		GridBagConstraints gbc_RememberWarning = new GridBagConstraints();
		gbc_RememberWarning.insets = new Insets(0, 0, 5, 0);
		gbc_RememberWarning.gridx = 0;
		gbc_RememberWarning.gridy = 13;
		panel.add(RememberWarning, gbc_RememberWarning);
		
		IsTimed = new JCheckBox("Activated on a timer?");
		GridBagConstraints gbc_IsTimed = new GridBagConstraints();
		gbc_IsTimed.anchor = GridBagConstraints.WEST;
		gbc_IsTimed.insets = new Insets(0, 0, 5, 0);
		gbc_IsTimed.gridx = 0;
		gbc_IsTimed.gridy = 15;
		panel.add(IsTimed, gbc_IsTimed);
		
		JLabel lblwarningThisWill = new JLabel("<html><body style='width: 200px'>(Warning: this will create a second new behavior, named \"Timer_[your name],\" that will contain the behavior you're making now.)</html>");
		GridBagConstraints gbc_lblwarningThisWill = new GridBagConstraints();
		gbc_lblwarningThisWill.anchor = GridBagConstraints.WEST;
		gbc_lblwarningThisWill.insets = new Insets(0, 0, 5, 0);
		gbc_lblwarningThisWill.gridx = 0;
		gbc_lblwarningThisWill.gridy = 16;
		panel.add(lblwarningThisWill, gbc_lblwarningThisWill);
		
		JPanel TimedInputPanel = new JPanel();
		GridBagConstraints gbc_TimedInputPanel = new GridBagConstraints();
		gbc_TimedInputPanel.insets = new Insets(0, 0, 5, 0);
		gbc_TimedInputPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_TimedInputPanel.gridx = 0;
		gbc_TimedInputPanel.gridy = 17;
		panel.add(TimedInputPanel, gbc_TimedInputPanel);
		GridBagLayout gbl_TimedInputPanel = new GridBagLayout();
		gbl_TimedInputPanel.columnWidths = new int[]{0, 0, 0};
		gbl_TimedInputPanel.rowHeights = new int[]{0, 0, 0};
		gbl_TimedInputPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_TimedInputPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		TimedInputPanel.setLayout(gbl_TimedInputPanel);
		
		JLabel lblMinSinceLast = new JLabel("Min. Since Last");
		GridBagConstraints gbc_lblMinSinceLast = new GridBagConstraints();
		gbc_lblMinSinceLast.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinSinceLast.gridx = 0;
		gbc_lblMinSinceLast.gridy = 0;
		TimedInputPanel.add(lblMinSinceLast, gbc_lblMinSinceLast);
		
		MinTimeField = new JFormattedTextField(NumberFormat.getNumberInstance());
		GridBagConstraints gbc_MinTimeField = new GridBagConstraints();
		gbc_MinTimeField.insets = new Insets(0, 0, 5, 0);
		gbc_MinTimeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_MinTimeField.gridx = 1;
		gbc_MinTimeField.gridy = 0;
		TimedInputPanel.add(MinTimeField, gbc_MinTimeField);
		
		JLabel lblMaxSinceLast = new JLabel("Max. Since Last");
		GridBagConstraints gbc_lblMaxSinceLast = new GridBagConstraints();
		gbc_lblMaxSinceLast.anchor = GridBagConstraints.EAST;
		gbc_lblMaxSinceLast.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaxSinceLast.gridx = 0;
		gbc_lblMaxSinceLast.gridy = 1;
		TimedInputPanel.add(lblMaxSinceLast, gbc_lblMaxSinceLast);
		
		MaxTimeField = new JFormattedTextField(NumberFormat.getNumberInstance());
		GridBagConstraints gbc_MaxTimeField = new GridBagConstraints();
		gbc_MaxTimeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_MaxTimeField.gridx = 1;
		gbc_MaxTimeField.gridy = 1;
		TimedInputPanel.add(MaxTimeField, gbc_MaxTimeField);
		
		AskGiveUpPanel = new JPanel();
		GridBagConstraints gbc_AskGiveUpPanel = new GridBagConstraints();
		gbc_AskGiveUpPanel.insets = new Insets(0, 0, 5, 0);
		gbc_AskGiveUpPanel.fill = GridBagConstraints.BOTH;
		gbc_AskGiveUpPanel.gridx = 0;
		gbc_AskGiveUpPanel.gridy = 18;
		panel.add(AskGiveUpPanel, gbc_AskGiveUpPanel);
		
		JLabel lblGiveUpBehavior = new JLabel("Give Up Behavior");
		AskGiveUpPanel.add(lblGiveUpBehavior);
		
		GiveUptxtbx = new JTextField();
		AskGiveUpPanel.add(GiveUptxtbx);
		GiveUptxtbx.setColumns(17);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 19;
		panel.add(panel_1, gbc_panel_1);
		
		NewNormal = new JButton("New Statement");
		NewNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BehaviorLine l = new BehaviorLine();
				cont.AddLine(l);
				cont.revalidate();
			}
		});
		panel_1.add(NewNormal);
		
		NewAskOption = new JButton("New Ask Option");
		NewAskOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AskLine l = new AskLine();
				cont.AddLine(l);
				cont.revalidate();
			}
		});
		panel_1.add(NewAskOption);
		
		NewLongTalk = new JButton("New Statement Group");
		NewLongTalk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LongTalkLineContainer l = new LongTalkLineContainer(ghostRoot);
				cont.AddLine(l);
				cont.revalidate();
			}
		});
		panel_1.add(NewLongTalk);
		
		NewRandom = new JButton("New Random");
		NewRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RandomizerLine l = new RandomizerLine();
				cont.AddLine(l);
				cont.revalidate();
			}
		});
		panel_1.add(NewRandom);
		
		cont = new BehaviorLineContainer();
		cont.setBounds(270, 40, 500, 600);
		frame.getContentPane().add(cont);
		
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WriteEverything();
			}
		});
		btnDone.setBounds(10, 701, 89, 23);
		frame.getContentPane().add(btnDone);
		
		btnCancel = new JButton("Quit");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(100, 701, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont.Clear();
				frame.revalidate();
			}
		});
		btnClear.setBounds(191, 701, 89, 23);
		frame.getContentPane().add(btnClear);
		
		AskGiveUpPanel.setVisible(false);
		
		TimedInputPanel.setVisible(false);
		IsTimed.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				TimedInputPanel.setVisible(!TimedInputPanel.isVisible());
			}
		});
		ImageSelect.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent arg0)
        	{
				if(ghostRoot == null)
				{
					//System.out.println("Choose a root for the ghost first, so you can get the picture from resources");
					JOptionPane.showMessageDialog(frame, "Choose a root for the ghost first, so you can get the picture from the resources folder.");
					return;
				}
        		JFileChooser chooser = new JFileChooser();
        		FileNameExtensionFilter filter = new FileNameExtensionFilter(
        		        "JPG & PNG Images", "jpg", "png");
        		chooser.setFileFilter(filter);
        		chooser.setCurrentDirectory(ghostRoot);
        		int returnVal = chooser.showOpenDialog(frame);
        	    if(returnVal == JFileChooser.APPROVE_OPTION) {
        	    	imageUsed = chooser.getSelectedFile().getName();
        	       selectedImg.setText(imageUsed);
        	    }
        	}
        });
	}
	
	public String writeClass()
	{
		String str = "";
		
		if(BehaviorCB.getSelectedItem() == "Normal")
		{
			str = "package ghost_behaviors;\r\n" + 
					"\r\n" + 
					"import ghostpet.GhostBase;\r\n" + 
					"\r\n" + 
					"public class Foobar extends Behavior ";
			if(ImplementsIRemember.isSelected())
			{
				str = str+"implements IRememberBehavior ";
			}
			str = str+
					"{\r\n" +
					"\r\n" + 
					"	public Foobar(GhostBase frame) {\r\n" + 
					"		super(frame);\r\n" + 
					"		\r\n" + 
					"	}\r\n" + 
					"\r\n" + 
					"	public Foobar() {\r\n" + 
					"\r\n" + 
					"	}\r\n" + 
					"\r\n";
		}
		else if(BehaviorCB.getSelectedItem() == "Ask")
		{
			str = "package ghost_behaviors;\r\n" + 
					"\r\n" + 
					"import ghostpet.GhostBase;\r\n" + 
					"\r\n" + 
					"public class Foobar extends Behavior_Ask ";
			if(ImplementsIRemember.isSelected())
			{
				str = str+"implements IRememberBehavior ";
			}
			str = str+
					"{\r\n"+
					"\r\n" + 
					"	public Foobar(GhostBase frame) {\r\n" + 
					"		super(frame);\r\n" + 
					"	}\r\n" + 
					"\r\n" + 
					"	public Foobar() {\r\n" + 
					"	}\r\n" + 
					"\r\n";
		}
		else if(BehaviorCB.getSelectedItem() == "LongTalk")
		{
			str = "package ghost_behaviors;\r\n" + 
					"\r\n" + 
					"import ghostpet.GhostBase;\r\n" + 
					"\r\n" + 
					"public class Foobar extends Behavior_LongTalk ";
			if(ImplementsIRemember.isSelected())
			{
				str = str+"implements IRememberBehavior ";
			}
			str = str+
					"{\r\n"+
					"\r\n" + 
					"	public Foobar(GhostBase frame) {\r\n" + 
					"		super(frame);\r\n" + 
					"	}\r\n" + 
					"\r\n" + 
					"	public Foobar() {\r\n" + 
					"	}\r\n" + 
					"\r\n";
		}
		else if(BehaviorCB.getSelectedItem() == "Randomizer")
		{
			str = "package ghost_behaviors;\r\n" + 
					"\r\n" + 
					"import ghostpet.GhostBase;\r\n" + 
					"\r\n" + 
					"public class Foobar extends Behavior_Randomizer ";
			if(ImplementsIRemember.isSelected())
			{
				str = str+"implements IRememberBehavior ";
			}
			str = str+
					"{\r\n"+
					"\r\n" + 
					"	public Foobar(GhostBase frame) {\r\n" + 
					"		super(frame);\r\n" + 
					"	}\r\n" + 
					"\r\n" + 
					"	public Foobar() {\r\n" + 
					"	}\r\n" + 
					"\r\n";
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "Select a behavior type for the ghost.");
			return "";
		}
		
		if(ImplementsIRemember.isSelected())
		{
			str = str+ 
					"	@Override\r\n" + 
					"	public void calcValues() {\r\n" + 
					"\r\n" + 
					"	}";
		}
		
		str = str + 
				"\r\n" + 
				"\r\n" + 
				"}";
		
		str = str.replaceAll(Pattern.quote("Foobar"), txtbehavior.getText());
		
		return str;
	}
	
	public void WriteGson(String filetarget)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
		String filename = filetarget + "\\resources\\"+txtbehavior.getText()+".json";
		JsonObject obj = new JsonObject();
		if(BehaviorCB.getSelectedItem() == "Normal")
		{
			JsonArray statements = new JsonArray();
			for(int i = 0; i < cont.list.size(); i++)
			{
				statements.add(((BehaviorLine) cont.list.get(i)).getText());
			}
			obj.add("statements", statements);
			obj.addProperty("imageUsed", imageUsed);
			obj.addProperty("actTime", Float.parseFloat(TimeField.getText()));
		}
		else if(BehaviorCB.getSelectedItem() == "Ask")
		{
			JsonArray statements = new JsonArray();
			JsonArray opts = new JsonArray();
			
			for(int i = 0; i < cont.list.size(); i++)
			{
				if(cont.list.get(i) instanceof AskLine)
					opts.add(((AskLine) cont.list.get(i)).getVals());
				else
					statements.add(((BehaviorLine) cont.list.get(i)).getText());
			}
			obj.add("statements", statements);
			obj.add("opts", opts);
			obj.addProperty("imageUsed", imageUsed);
			obj.addProperty("actTime", Float.parseFloat(TimeField.getText()));
			if(GiveUptxtbx.getText() == "")
				obj.addProperty("giveUp", (String) null);
			else
				obj.addProperty("giveUp", GiveUptxtbx.getText());
		}
		else if(BehaviorCB.getSelectedItem() == "LongTalk")
		{
			ChainGroup[] list = new ChainGroup[cont.list.size()];
			for(int i = 0; i < cont.list.size(); i++)
			{
				list[i] = ((LongTalkLineContainer) cont.list.get(i)).getValues();
			}
			obj.add("chains", gson.toJsonTree(list));
			obj.addProperty("actTime", -1);
			obj.addProperty("hasSetValue", false);
		}
		else if(BehaviorCB.getSelectedItem() == "Randomizer")
		{
			JsonArray behaves = new JsonArray();
			for(int i = 0; i < cont.list.size(); i++)
			{
				behaves.add(((RandomizerLine) cont.list.get(i)).getBehavior());
			}
			obj.add("behaves", behaves);
			obj.addProperty("actTime",-1);
		}
		try {
			FileWriter fr = new FileWriter(filename);
			JsonWriter js = gson.newJsonWriter(fr);
			gson.toJson(obj, js);
			js.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String WriteTimedClass()
	{
		String str = "";
		str = str+"package ghost_behaviors;\r\n" + 
				"\r\n" + 
				"import ghostpet.GhostBase;\r\n" + 
				"\r\n" + 
				"public class Foobar extends Behavior_Timed {\r\n" + 
				"\r\n" + 
				"	public Foobar(GhostBase frame) {\r\n" + 
				"		super(frame);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public Foobar() {\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
		str = str.replaceAll(Pattern.quote("Foobar"), ("Timer_"+txtbehavior.getText()));
		return str;
	}
	
	public void WriteTimedJson(String filetarget)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
		String filename = filetarget + "\\resources\\Timer_"+txtbehavior.getText()+".json";
		JsonObject obj = new JsonObject();
		obj.addProperty("minSinceLast", Integer.parseInt(MinTimeField.getText()));
		obj.addProperty("maxSinceLast", Integer.parseInt(MaxTimeField.getText()));
		obj.addProperty("BehaviorExecuted", txtbehavior.getText());
		
		try {
			FileWriter fr = new FileWriter(filename);
			JsonWriter js = gson.newJsonWriter(fr);
			gson.toJson(obj, js);
			js.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void WriteEverything()
	{
		Runtime runtime = Runtime.getRuntime();
		if((BehaviorCB.getSelectedItem() == "Select...") || (ghostRoot == null) || ((TimeField.isEnabled() && (TimeField.getText() == ""))) || (ImageSelect.isEnabled() && imageUsed == "") || (IsTimed.isSelected() && (MinTimeField.getText() == "" || MaxTimeField.getText() == "")))
		{
			JOptionPane.showMessageDialog(frame, "Information missing. Cannot create the behavior. Make sure everything's in place and try again.");
			return;
		}
		WriteGson(ghostRoot.getPath());
		String classfilename = ghostRoot.getPath() + "\\src\\ghost_behaviors\\"+txtbehavior.getText()+".java";
		try {
			FileWriter fr = new FileWriter(classfilename);
			fr.write(writeClass());
			fr.close();
		
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
			File[] files = {new File(ghostRoot.getAbsolutePath()+"\\src\\ghost_behaviors\\"+txtbehavior.getText()+".java")};
			Iterable<? extends JavaFileObject> compilationUnits =
			           fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files));
			List<String> options = new ArrayList<String>();
			options.addAll(Arrays.asList("-classpath",ghostRoot.getAbsolutePath()+"\\ghostpet.jar;"+ghostRoot.getAbsolutePath()+"\\libs\\gson-2.8.5.jar","-d",ghostRoot.getAbsolutePath()+"\\bin"));
			compiler.getTask(null, fileManager, null, options, null, compilationUnits).call();
			String jarupdate = "jar uf "+ghostRoot.getAbsolutePath()+"\\ghostpet.jar -C "+ghostRoot.getAbsolutePath()+"\\bin ghost_behaviors\\"+txtbehavior.getText()+".class";
			
			if(IsTimed.isSelected())
			{
				WriteTimedJson(ghostRoot.getPath());
				classfilename = ghostRoot.getPath() + "\\src\\ghost_behaviors\\Timer_"+txtbehavior.getText()+".java";
				FileWriter fj = new FileWriter(classfilename);
				fj.write(WriteTimedClass());
				fj.close();
				
				File[] file2 = {new File(ghostRoot.getAbsolutePath()+"\\src\\ghost_behaviors\\"+classfilename+".java")};
				compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file2));
				compiler.getTask(null, fileManager, null, options, null, compilationUnits).call();
				jarupdate = jarupdate + " ghost_behaviors\\Timer_"+txtbehavior.getText()+".class";
			}
			
			runtime.exec(jarupdate);
			fileManager.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
