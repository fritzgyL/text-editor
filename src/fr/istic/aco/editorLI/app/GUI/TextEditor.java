package fr.istic.aco.editorLI.app.GUI;

import java.awt.event.*;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import fr.istic.aco.editorLI.app.command.ICommand;
import fr.istic.aco.editorLI.app.invoker.Invoker;
import fr.istic.aco.editorLI.app.memento.InsertMemento;
import fr.istic.aco.editorLI.app.utils.Utilities;

/**
 * Text editor GUI/Invoker
 * 
 * @author Fritzgy Lubin
 * @author Abdou Ibouraima
 *
 */
public class TextEditor extends JFrame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	public static long lastKeyPressTime = 0l;

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
	private JMenuItem redo = new JMenuItem("Redo");
	private JMenuItem undo = new JMenuItem("Undo");

	private ICommand insertCommand;
	private ICommand deleteCommand;
	private ICommand cutCommand;
	private ICommand pasteCommand;
	private ICommand copyCommand;
	private ICommand replayCommand;

	private String charToInsert;
	private int selectionStartIndex;
	private int selectionEndIndex;

	private Stack<ICommand> undoStack;
	private Stack<ICommand> redoStack;

	private InsertMemento insertCommandState;

	private Invoker invoker;

	public TextEditor(TextEditorBuilder textEditorBuilder) {
		super("Text Editor");
		this.selectionStartIndex = 0;
		this.selectionEndIndex = 0;
		charToInsert = "";
		initMenu();
		initFrame();
		resetCaretVisibility();
		insertCommand = textEditorBuilder.insertCommand;
		deleteCommand = textEditorBuilder.deleteCommand;
		copyCommand = textEditorBuilder.copyCommand;
		cutCommand = textEditorBuilder.cutCommand;
		pasteCommand = textEditorBuilder.pasteCommand;
		replayCommand = textEditorBuilder.replayCommand;
		invoker = textEditorBuilder.invoker;
		undoStack = new Stack<ICommand>();
		redoStack = new Stack<ICommand>();
	}

	public enum CommandType {
		INSERT, COPY, CUT, PASTE, DELETE
	}

	/**
	 * Initialize the user interface
	 */
	public void initFrame() {
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
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		add(scrollPane);
		setVisible(true);
		setAlwaysOnTop(true);
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
		edit.add(redo);
		edit.add(undo);
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
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					redo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				resetCaretVisibility();

			}

		});
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					undo();
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
		String character = Character.toString(e.getKeyChar());
		if (Utilities.isAValidChar(character)) {
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
			charToInsert = " ";
			try {
				insert();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case KeyEvent.VK_ENTER:
			charToInsert = "\n";
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

	/**
	 * Insert text
	 * 
	 * @throws Exception
	 */
	public void insert() throws Exception {
		invoker.insert(insertCommand);
	}

	/**
	 * delete selected text
	 * 
	 * @throws Exception
	 */
	public void delete() throws Exception {
		invoker.delete(deleteCommand);
	}

	/**
	 * replay user action
	 * 
	 * @throws Exception
	 */
	public void replay() throws Exception {
		invoker.replay(replayCommand);
	}

	/**
	 * cut selected text
	 * 
	 * @throws Exception
	 */
	public void cut() throws Exception {
		invoker.cut(cutCommand);
	}

	/**
	 * copy selected text
	 * 
	 * @throws Exception
	 */
	public void copy() throws Exception {
		invoker.copy(copyCommand);
	}

	/**
	 * paste clipboard content
	 * 
	 * @throws Exception
	 */
	public void paste() throws Exception {
		invoker.paste(pasteCommand);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * @return the character to insert in the buffer
	 */
	public String getCharToInsert() {
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
	 * Replace the textarea text by text
	 * 
	 * @param text the text to insert in the textarea
	 */
	public void setText(String text) {
		textArea.setText(text);
	}

	/**
	 * Automatically update selection index when user makes a selection in the GUI
	 */
	public void updateSelection() {
		selectionStartIndex = textArea.getSelectionStart();
		selectionEndIndex = textArea.getSelectionEnd();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Set the caret position to begin and end index
	 * 
	 * @param beginIndex the start position of the caret
	 * @param endIndex   the end position of the caret
	 */
	public void setCaretPosition(int beginIndex, int endIndex) {
		textArea.setCaretPosition(beginIndex);
		textArea.moveCaretPosition(endIndex);
		updateSelection();
	}

	/**
	 * make caret visible if focus has been lost
	 */
	public void resetCaretVisibility() {
		textArea.getCaret().setVisible(true);
		textArea.getCaret().setSelectionVisible(true);
	}

	/**
	 * undo user action
	 */
	public void undo() {
		if (!undoStack.isEmpty()) {
			ICommand undoCommand = undoStack.pop();
			invoker.undo(undoCommand);
		}
	}

	/**
	 * redo user action
	 */
	public void redo() {
		if (!redoStack.isEmpty()) {
			ICommand redoCommand = redoStack.pop();
			invoker.redo(redoCommand);
		}
	}

	/**
	 * update the character to insert in the editor
	 * 
	 * @param mChar a char to insert in the editor
	 */
	public void setCharToInsert(String mChar) {
		charToInsert = mChar;
	}

	/**
	 * @return the current text content of the editor
	 */
	public String getText() {
		return textArea.getText();
	}

	/**
	 * update insert command state
	 * 
	 * @param save the insert memento
	 */
	public void setInsertCommandState(InsertMemento save) {
		this.insertCommandState = save;
	}

	/**
	 * Clear the redo commands stack
	 */
	public void clearRedoStack() {
		redoStack.clear();
	}

	/**
	 * Add command to the undo stack
	 * 
	 * @param command a concrete command
	 */
	public void addNewUndoCommand(ICommand command) {
		undoStack.push(command);
	}

	/**
	 * Add command to the redo stack
	 * 
	 * @param command a concrete command
	 */
	public void addNewRedoCommand(ICommand command) {
		redoStack.push(command);
	}

	/**
	 * @return insert command memento
	 */
	public InsertMemento getInsertCommandState() {
		return insertCommandState;
	}

	/**
	 * Builder for TextEditor
	 * 
	 * @author Fritzgy Lubin
	 *
	 */
	public static class TextEditorBuilder {
		private ICommand insertCommand;
		private ICommand deleteCommand;
		private ICommand cutCommand;
		private ICommand copyCommand;
		private ICommand pasteCommand;
		private ICommand replayCommand;
		private Invoker invoker;

		public TextEditorBuilder(ICommand insertCommand, ICommand deleteCommand, ICommand cutCommand,
				ICommand copyCommand, ICommand pasteCommand, ICommand replayCommand, Invoker invoker) {
			this.insertCommand = insertCommand;
			this.deleteCommand = deleteCommand;
			this.cutCommand = cutCommand;
			this.copyCommand = copyCommand;
			this.pasteCommand = pasteCommand;
			this.replayCommand = replayCommand;
			this.invoker = invoker;
		}

		public TextEditor build() {
			return new TextEditor(this);
		}
	}

}
