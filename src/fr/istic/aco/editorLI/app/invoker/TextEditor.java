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

public class TextEditor extends JFrame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenuItem close = new JMenuItem("Close");
	private JMenuItem cut = new JMenuItem("Cut");
	private JMenuItem copy = new JMenuItem("Copy");
	private JMenuItem paste = new JMenuItem("Paste");
	private JMenuItem delete = new JMenuItem("Delete");

	private ICommand insertCommand;
	private ICommand deleteCommand;
	private ICommand cutCommand;
	private ICommand pasteCommand;
	private ICommand copyCommand;

	private char charToInsert;
	private int selectionStartIndex;
	private int selectionEndIndex;
	private String textContent;

	public TextEditor(ICommand insertCommand, ICommand deleteCommand, ICommand cutCommand, ICommand pasteCommand,
			ICommand copyCommand) {
		super("Text Editor");
		this.selectionStartIndex = 0;
		this.selectionEndIndex = 0;
		this.insertCommand = insertCommand;
		this.deleteCommand = deleteCommand;
		this.cutCommand = cutCommand;
		this.pasteCommand = pasteCommand;
		this.copyCommand = copyCommand;
		charToInsert = '\0';
		textContent = "";
		textArea = new JTextArea();
		textArea.setEditable(false);
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

	public enum CommandType {
		INSERT, COPY, CUT, PASTE, DELETE
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
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				pasteAction();
			}
		});

		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				copyAction();
			}
		});

		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				cutAction();
			}
		});

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
			charToInsert = character;
			insert();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			delete();
			break;
		case KeyEvent.VK_CUT:
			cutAction();
			break;
		case KeyEvent.VK_COPY:
			copyAction();
			break;
		case KeyEvent.VK_PASTE:
			pasteAction();
			break;
		case KeyEvent.VK_DELETE:
			delete();
			break;
		case KeyEvent.VK_SPACE:
			charToInsert = ' ';
			insert();
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
	public void insert() {
		setText(insertCommand.execute());
	}

	/**
	 * @param text the text to insert in the textarea Replace the textarea text by
	 *             text
	 */
	public void setText(String text) {
		textContent = text;
		textArea.setText(textContent);
	}

	/**
	 * delete selected text
	 */
	public void delete() {
		setText(deleteCommand.execute());
	}

	/**
	 * cut selected text
	 */
	public void cutAction() {
		setText(cutCommand.execute());
	}

	/**
	 * paste the clipboard content
	 */
	public void pasteAction() {
		setText(pasteCommand.execute());
	}

	/**
	 * copy selected text
	 */
	public void copyAction() {
		setText(copyCommand.execute());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
