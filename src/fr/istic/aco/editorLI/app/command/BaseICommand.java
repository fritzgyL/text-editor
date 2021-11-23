package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;

public abstract class BaseICommand implements ICommand {
	protected Engine engine;
	protected TextEditor editor;

	public BaseICommand(Engine engine) {
		this.engine = engine;
		this.editor = null;
	}

	/**
	 * @param editor the text editor that invokes the commands injects editor
	 */
	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}

	/**
	 * set the selection of the engine buffer
	 */
	public void setSelection() {
		engine.getSelection().setBeginIndex(editor.getSelectionStartIndex());
		engine.getSelection().setEndIndex(editor.getSelectionEndIndex());
	}
	
	public String getText() {
		return engine.getBufferContents();
	}

	@Override
	public abstract String execute();

}
