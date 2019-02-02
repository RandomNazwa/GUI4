/**
 *
 *  @author Trembowska Katarzyna S18233
 *
 */

package zad3;

import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;

import javax.swing.*;


class ColorWidget implements Icon {
	
	private Color color;
	
	public ColorWidget(Color color) {
		this.color = color;
	}
	
	public int getIconHeight() { return 10; }

	public int getIconWidth() { return 10; }

	public void paintIcon(Component cmp, Graphics gph, int x, int y) {
		gph.setColor(color);
		gph.fillOval(0, 0, 15, 15);
	}
}



public class Main extends JFrame{
	
	private JTextArea editor = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(editor);
	private FileDialog loadFile = new FileDialog(this, "Wczytaj", FileDialog.LOAD);
	private FileDialog saveFile = new FileDialog(this, "Zapisz", FileDialog.SAVE);
	private String editedFile = null;
	private static Map<String, String> map = new HashMap<>();

	
	static {
		map.put("Praca", "adres pracy");
		map.put("Szkoła", "adres szkoły");
		map.put("Dom", "adres domu");
	}
	
public static void main(String[] args) { new Main(); }
	public Main() {
		
		this.setSize(350, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		editor.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		loadFile.setDirectory(".");

		
//Pierwsza zakładka - File
		JMenu file = new JMenu("File");
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(e -> clickOpen());
		open.setMnemonic('O');
		open.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_MASK));
		file.add(open);
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(e -> clickSave());
		save.setMnemonic('S');
		save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
		file.add(save);
		
		JMenuItem saveAs = new JMenuItem("Save as ...");
		saveAs.addActionListener(e -> clickSaveAs());
		saveAs.setMnemonic('A');
		saveAs.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_MASK));
		file.add(saveAs);
		
		JSeparator line = new JSeparator();
		line.setBackground(Color.red);
		file.add(line);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(e -> this.dispose());
		exit.setMnemonic('X');
		exit.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_MASK));
		file.add(exit);
		
//Zakładka - Edit
		JMenu edit = new JMenu("Edit");
		JMenu adresy = new JMenu("Adresy");
			for(String s : map.keySet()) {
				JMenuItem mi = new JMenuItem(s);
				mi.setMnemonic(s.charAt(0));
				mi.setAccelerator(KeyStroke.getKeyStroke(s.charAt(0), KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
				mi.addActionListener(e -> editor.insert(map.get(s), editor.getCaretPosition()));
				adresy.add(mi);
			}
		edit.add(adresy);
		
//Zakładka - Options
		JMenu option = new JMenu("Options");
		
		JMenu foreground = new JMenu("Foreground");	
			for(int i = 0; i < colors.length; i++) {
				int j = i;
				JMenuItem mi = new JMenuItem(names[i], new ColorWidget(colors[i]));
				mi.addActionListener(e -> editor.setForeground(colors[j]));
				foreground.add(mi);
			}
			option.add(foreground);

		
		JMenu background = new JMenu("Background");			
			for(int i = 0; i < colors.length; i++) {
				int j = i;
				JMenuItem mi = new JMenuItem(names[i], new ColorWidget(colors[i]));
				mi.addActionListener(e -> editor.setBackground(colors[j]));
				background.add(mi);
			}
			option.add(background);
		

		
		JMenu font = new JMenu("Font");			
			for(int i = 8; i <= 24; i = i+2) {
				int j = i;
				JMenuItem mi = new JMenuItem(i + " pts");
				mi.addActionListener(e -> editor.setFont(new Font(Font.DIALOG, Font.PLAIN, j)));
				font.add(mi);
			}
			option.add(font);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(option);
		
		this.setJMenuBar(menuBar);
		this.add(scrollPane);
		this.setVisible(true);
	}
		
	private Color[] colors = {Color.BLUE, Color.YELLOW, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.GREEN};
	private String[] names = {"blue", "yellow", "orange", "red", "white", "black", "green"};
	


	
	public void clickOpen() {
		loadFile.setVisible(true);	
		if(loadFile.getFile() != null) {
			this.setTitle("Prosty Edytor - " + loadFile.getDirectory() + loadFile.getFile());
			editedFile = loadFile.getDirectory() + loadFile.getFile();
			try {
				FileReader fileReader = new FileReader(editedFile);
				editor.read(fileReader, "Content of File");
				fileReader.close();
			} catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	public void clickSave() {
		if(editedFile != null) {
			try {
				PrintWriter printWriter =new PrintWriter(editedFile);
				 editor.write(printWriter);
				 printWriter.close();
			} catch(Exception e) { e.printStackTrace(); }
		}
		else clickSaveAs(); 
		}
	
	public void clickSaveAs() {
		saveFile.setVisible(true);
		
		try {
			PrintWriter printWriter = new PrintWriter(saveFile.getDirectory() + saveFile.getFile());
			 editor.write(printWriter);
			 printWriter.close();
		} catch(Exception e) { e.printStackTrace(); }	
	}
	
}
