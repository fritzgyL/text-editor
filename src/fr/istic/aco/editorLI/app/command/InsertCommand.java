package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;

public class InsertCommand extends BaseICommand {

	public InsertCommand(Engine engine) {
		super(engine);
	}

	@Override
	public void execute() {
		setSelection();
		engine.insert(Character.toString(editor.getCharToInsert()));
		System.out.println(engine.getBufferContents());
	}

}
