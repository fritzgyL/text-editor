package fr.istic.aco.editorLI.app.invoker;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import fr.istic.aco.editorLI.app.command.CutTextCommand;
import fr.istic.aco.editorLI.app.command.ICommand;
import fr.istic.aco.editorLI.app.command.InsertTextCommand;

public class TextEditor extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenuItem close = new JMenuItem("Close");
	private JMenuItem cancel = new JMenuItem("Cancel");
	private JMenuItem cut = new JMenuItem("Cut");
	private JMenuItem copy = new JMenuItem("Copy");
	private JMenuItem paste = new JMenuItem("Paste");
	private JMenuItem delete = new JMenuItem("Delete");

	private ICommand insertCommand;
	private char textToInsert;
	
	// k ajout
	private char textTocut; 
	private CutTextCommand cutCommand;
	
	private int startsel;
	private int endsel;
	


	public TextEditor(ICommand insertCommand) {
		super("Text Editor");
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		textArea.addKeyListener(this);
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				System.out.println("start: " + textArea.getSelectionStart());
				System.out.println("end: " + textArea.getSelectionEnd());
				startsel = textArea.getSelectionStart();
				endsel = textArea.getSelectionEnd();
			}
		});
		initMenu();
		initFrame();
		setVisible(true);
		this.insertCommand = insertCommand;
		textToInsert = '\0';
	}

	/**
	 * Initialize the user interface
	 */
	public void initFrame() {
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		add(scrollPane);
	}

	/**
	 * Initialize the menu of the GUI
	 */
	public void initMenu() {
		menuBar = new JMenuBar();
		file.add(close);
		edit.add(cancel);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(file);
		menuBar.add(edit);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		textToInsert = e.getKeyChar();
		insertCommand.execute();
		
		// k Ajout
		textTocut = e.getKeyChar();
		cutCommand.execute();
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public char getTextToInsert() {
		return textToInsert;
	}

	
	public int getStartsel() {
		return startsel;
	}

	public void setStartsel(int startsel) {
		this.startsel = startsel;
	}

	public int getEndsel() {
		return endsel;
	}

	public void setEndsel(int endsel) {
		this.endsel = endsel;
	}
	
}
