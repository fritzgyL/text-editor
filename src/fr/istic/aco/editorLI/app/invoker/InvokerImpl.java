package fr.istic.aco.editorLI.app.invoker;

import fr.istic.aco.editorLI.app.GUI.TextEditor;
import fr.istic.aco.editorLI.app.command.ICommand;
import fr.istic.aco.editorLI.app.command.InsertCommand;
import fr.istic.aco.editorLI.app.utils.Text;

public class InvokerImpl implements Invoker {

	private TextEditor editor;

	@Override
	public void insert(ICommand insertCommand) throws Exception {
		((InsertCommand) insertCommand).setText(editor.getCharToInsert());
		Text text = insertCommand.execute();
		editor.setInsertCommandState(((InsertCommand) insertCommand).save());
		updateText(text);
		addNewUndoCommand(true, insertCommand);
		editor.updateSelection();
	}

	@Override
	public void cut(ICommand cutCommand) throws Exception {
		Text text = cutCommand.execute();
		updateText(text);
		addNewUndoCommand(true, cutCommand);
	}

	@Override
	public void copy(ICommand copyCommand) throws Exception {
		Text text = copyCommand.execute();
		updateText(text);
	}

	@Override
	public void paste(ICommand pasteCommand) throws Exception {
		Text text = pasteCommand.execute();
		updateText(text);
		addNewUndoCommand(true, pasteCommand);
	}

	@Override
	public void delete(ICommand deleteCommand) throws Exception {
		Text text = deleteCommand.execute();
		updateText(text);
		addNewUndoCommand(true, deleteCommand);

	}

	@Override
	public void replay(ICommand replayCommand) throws Exception {
		Text text = replayCommand.execute();
		updateText(text);
	}

	@Override
	public void undo(ICommand undoCommand) {
		try {
			Text text = undoCommand.undo();
			updateText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		editor.addNewRedoCommand(undoCommand);
	}

	@Override
	public void redo(ICommand redoCommand) {
		try {
			if (redoCommand instanceof InsertCommand) {
				((InsertCommand) redoCommand).restore(editor.getInsertCommandState());
			}
			Text text = redoCommand.execute();
			updateText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addNewUndoCommand(false, redoCommand);
	}

	/**
	 * Set the text editor
	 * 
	 * @param textEditor
	 */
	public void setEditor(TextEditor textEditor) {
		this.editor = textEditor;
	}

	/**
	 * Update the text of the text editor
	 * 
	 * @param text the new text
	 */
	private void updateText(Text text) {
		editor.setText(text.getContent());
		editor.setCaretPosition(text.getCaret()[0], text.getCaret()[1]);
	}

	/**
	 * Add a new undo command to the stack
	 * 
	 * @param clear   tells if redo stack should be cleared
	 * @param command the new command
	 */
	private void addNewUndoCommand(boolean clear, ICommand command) {
		if (clear) {
			editor.clearRedoStack();
		}
		editor.addNewUndoCommand(command);
	}

}
