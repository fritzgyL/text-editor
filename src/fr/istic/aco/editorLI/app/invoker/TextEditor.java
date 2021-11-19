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

import fr.istic.aco.editorLI.app.command.ICommand;

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
	private ICommand deleteCommand;

	private char charToInsert;
	private int selectionStartIndex;
	private int selectionEndIndex;

	public TextEditor(ICommand insertCommand, ICommand deleteCommand) {
		super("Text Editor");
		this.selectionStartIndex = 0;
		this.selectionEndIndex = 0;
		this.insertCommand = insertCommand;
		this.deleteCommand = deleteCommand;
		charToInsert = '\0';

		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		textArea.addKeyListener(this);
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				updateSelection();
			}
		});
		initMenu();
		initFrame();
		setVisible(true);
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
			insert(character);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			delete();
			break;
		case KeyEvent.VK_DELETE:
			delete();
			break;
		case KeyEvent.VK_SPACE:
			insert(' ');
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * @return the character to insert in the buffer
	 */
	public char getCharToInsert() {
		return charToInsert;
	}

	/**
	 * @return the index of the selection start
	 */
	public int getSelectionStartIndex() {
		return selectionStartIndex;
	}

	/**
	 * @return the index of the selection end
	 */
	public int getSelectionEndIndex() {
		return selectionEndIndex;
	}

	/**
	 * @param character the character to insert in the buffer insert character in
	 *                  the buffer
	 */
	public void insert(char character) {
		charToInsert = character;
		insertCommand.execute();
	}

	/**
	 * delete the actual selection in the buffer
	 */
	public void delete() {
		deleteCommand.execute();
	}

	/**
	 * @param character the paramater we want to check if it's valid
	 * @return if character is a valid alphanumeric character or a ponctuation
	 */
	public boolean isAValidChar(char character) {
		String patternAlphanumeric = "^[\\p{L}0-9]*$";
		String patternSymbol = "[.;!?\\-/$\"'()@#&|\\{}=*-+=%§¤£¨µ°:]";
		String letter = Character.toString(character);
		return letter.matches(patternAlphanumeric) || letter.matches(patternSymbol);
	}

	/**
	 * Automatically update selection index when user makes a selection in the GUI
	 */
	private void updateSelection() {
		selectionStartIndex = textArea.getSelectionStart();
		selectionEndIndex = textArea.getSelectionEnd();
	}

}
