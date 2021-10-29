package fr.istic.aco.editorLI.app.invoker;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import fr.istic.aco.editorLI.app.command.InsertTextCommand;

public class TextEditor extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private TextArea textArea;
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenuItem close = new JMenuItem("Close");
	private JMenuItem cancel = new JMenuItem("Cancel");
	private JMenuItem cut = new JMenuItem("Cut");
	private JMenuItem copy = new JMenuItem("Copy");
	private JMenuItem paste = new JMenuItem("Paste");
	private JMenuItem delete = new JMenuItem("Delete");
	private InsertTextCommand insertCommand;

	public TextEditor(InsertTextCommand insertCommand) {
		super("Text Editor");
		textArea = new TextArea();
		textArea.addKeyListener(this);
		initMenu();
		initFrame();
		setVisible(true);
		this.insertCommand = insertCommand;
	}

	public void initFrame() {
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		add(textArea);
	}

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

	public String getText() {
		return "";
	}

	@Override
	public void keyTyped(KeyEvent e) {
		insertCommand.setTextToInsert(e.getKeyChar());
		insertCommand.execute();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
