package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;

public class InsertTextCommand implements ICommand {

	private Engine engine;
	private TextEditor editor;

	public InsertTextCommand(Engine engine) {
		this.engine = engine;
		this.editor = null;
	}

	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}

	@Override
	public void execute() {
		engine.getSelection().setBeginIndex(editor.getSelectionStartIndex());
		engine.getSelection().setEndIndex(editor.getSelectionEndIndex());
		engine.insert(Character.toString(editor.getCharToInsert()));
		System.out.println(engine.getBufferContents());
	}

}
