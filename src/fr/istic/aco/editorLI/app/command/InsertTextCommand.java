package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;

public class InsertTextCommand implements ICommand {

	private Engine engine;
	private char textToInsert;

	public InsertTextCommand(Engine engine) {
		this.engine = engine;
		textToInsert = '\0';
	}

	public void setTextToInsert(char c) {
		this.textToInsert = c;
	}

	@Override
	public void execute() {
		engine.insert(String.valueOf(textToInsert));
		System.out.println(engine.getSelection().getBeginIndex());
		System.out.println(engine.getBufferContents());
	}

}
