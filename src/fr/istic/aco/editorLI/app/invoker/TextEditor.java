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
import fr.istic.aco.editorLI.app.receiver.Text;

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
	private JMenuItem replay = new JMenuItem("Replay");

	private ICommand insertCommand;
	private ICommand deleteCommand;
	private ICommand cutCommand;
	private ICommand pasteCommand;
	private ICommand copyCommand;
	private ICommand replayCommand;

	private char charToInsert;
	private int selectionStartIndex;
	private int selectionEndIndex;

	public TextEditor(ICommand insertCommand, ICommand deleteCommand, ICommand cutCommand, ICommand pasteCommand,
			ICommand copyCommand, ICommand replayCommand) {
		super("Text Editor");
		this.selectionStartIndex = 0;
		this.selectionEndIndex = 0;
		this.insertCommand = insertCommand;
		this.deleteCommand = deleteCommand;
		this.cutCommand = cutCommand;
		this.pasteCommand = pasteCommand;
		this.copyCommand = copyCommand;
		this.replayCommand = replayCommand;
		charToInsert = '\0';
		textArea = new JTextArea();
		resetCaretVisibility();

		textArea.setEditable(false);

		// textArea.setEditable(false);
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
		edit.add(replay);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delete();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				resetCaretVisibility();

			}
		});
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				try {
					paste();
				} catch (Exception e) {
					e.printStackTrace();
				}
				resetCaretVisibility();

			}
		});

		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				try {
					copy();
				} catch (Exception e) {
					e.printStackTrace();
				}
				resetCaretVisibility();

			}
		});

		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				try {
					cut();
				} catch (Exception e) {
					e.printStackTrace();
				}
				resetCaretVisibility();

			}
		});

		close.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});

		replay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					replay();
				} catch (Exception e) {
					e.printStackTrace();
				}
				resetCaretVisibility();

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
			try {
				insert();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			try {
				delete();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			break;
		case KeyEvent.VK_DELETE:
			try {
				delete();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			break;
		case KeyEvent.VK_SPACE:
			charToInsert = ' ';
			try {
				insert();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
	 * @throws Exception
	 */
	public void insert() throws Exception {
		Text text = insertCommand.execute();
		int[] caret = text.getCaret();
		setText(text.getContent());
		setCaretPosition(caret[0], caret[1]);
	}

	/**
	 * @param text the text to insert in the textarea Replace the textarea text by
	 *             text
	 */
	public void setText(String text) {
		textArea.setText(text);
	}

	/**
	 * delete selected text
	 * 
	 * @throws Exception
	 */
	public void delete() throws Exception {
		Text text = deleteCommand.execute();
		int[] caret = text.getCaret();
		setText(text.getContent());
		setCaretPosition(caret[0], caret[1]);
	}

	/**
	 * cut selected text
	 * 
	 * @throws Exception
	 */
	public void cut() throws Exception {
		Text text = cutCommand.execute();
		int[] caret = text.getCaret();
		setText(text.getContent());
		setCaretPosition(caret[0], caret[1]);
	}

	/**
	 * paste the clipboard content
	 * 
	 * @throws Exception
	 */
	public void paste() throws Exception {
		Text text = pasteCommand.execute();
		int[] caret = text.getCaret();
		setText(text.getContent());
		setCaretPosition(caret[0], caret[1]);
	}

	/**
	 * copy selected text
	 * 
	 * @throws Exception
	 */
	public void copy() throws Exception {
		Text text = copyCommand.execute();
		int[] caret = text.getCaret();
		setText(text.getContent());
		setCaretPosition(caret[0], caret[1]);
	}

	/**
	 * replay last action
	 * 
	 * @throws Exception
	 */
	public void replay() throws Exception {
		Text text = replayCommand.execute();
		int[] caret = text.getCaret();
		setText(text.getContent());
		setCaretPosition(caret[0], caret[1]);
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

	public void setCaretPosition(int beginIndex, int endIndex) {
		textArea.setCaretPosition(beginIndex);
		textArea.moveCaretPosition(endIndex);
	}

	public void resetCaretVisibility() {
		textArea.getCaret().setVisible(true);
		textArea.getCaret().setSelectionVisible(true);
	}
}
