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
		engine.insert(Character.toString(editor.getTextToInsert()));
		//System.out.println(engine.getSelection().getBeginIndex());
		System.out.println(engine.getBufferContents());
	}

}