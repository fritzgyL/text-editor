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
import javax.swing.event.MenuKeyEvent;

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
	private char charToInsert;

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
			}
		});
		initMenu();
		initFrame();
		setVisible(true);
		this.insertCommand = insertCommand;
		charToInsert = '\0';
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
		char character = e.getKeyChar();
		if (isAValidChar(character)) {
			insertChar(character);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			System.out.println("Backspace");
			break;
		case KeyEvent.VK_DELETE:
			System.out.println("Delete");
			break;
		case KeyEvent.VK_SPACE:
			insertChar(' ');
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * @param character the character to insert in the buffer insert character in
	 *                  the buffer
	 */
	public void insertChar(char character) {
		charToInsert = character;
		insertCommand.execute();
	}

	/**
	 * @return the character to insert in the buffer
	 */
	public char getCharToInsert() {
		return charToInsert;
	}

	/**
	 * @param character the paramater we want to check if it's valid
	 * @return if character is a valid alphanumeric character or a ponctuation
	 */
	public boolean isAValidChar(char character) {
		String letter = Character.toString(character);
		return letter.matches("^(?!\\d+$)(?:[a-zA-Z0-9][a-zA-Z0-9 @&$]*)?");
	}

}
