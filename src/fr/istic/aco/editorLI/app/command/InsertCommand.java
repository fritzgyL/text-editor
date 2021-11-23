package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.InsertMemento;
import fr.istic.aco.editorLI.app.receiver.Memento;
import fr.istic.aco.editorLI.app.receiver.Recorder;

public class InsertCommand extends BaseICommand {

	private String text;
	private Recorder recorder;

	public InsertCommand(Engine engine, Recorder recorder) {
		super(engine);
		this.recorder = recorder;
	}

	@Override
	public String execute() {
		setSelection();
		text = Character.toString(editor.getCharToInsert());
		engine.insert(text);
		recorder.save(this);
		return engine.getBufferContents();
	}

	@Override
	public String getText() {
		return super.getText();
	}

	public Memento getMemento() {
		return new InsertMemento(text);
	}

}
