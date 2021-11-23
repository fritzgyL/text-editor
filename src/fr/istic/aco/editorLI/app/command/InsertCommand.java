package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;

public class InsertCommand extends BaseICommand {

	private String text;
	// private Recorder recorder;

	public InsertCommand(Engine engine) {
		super(engine);
		// this.recorder = recorder;
	}

	@Override
	public String execute() {
		setSelection();
		text = Character.toString(editor.getCharToInsert());
		engine.insert(text);
		// recorder.save(this);
		return engine.getBufferContents();
	}

}
