package fr.istic.aco.editorLI.app.command;


import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.Text;

public class InsertCommand extends BaseICommand {

	private String text;

	public InsertCommand(Engine engine, Recorder recorder) {
		super(engine,recorder);
	}

	@Override
	public Text execute() {
		setSelection();
		text = Character.toString(editor.getCharToInsert());
		engine.insert(text);
		return new Text(engine.getBufferContents(), new int[]{engine.getSelection().getBeginIndex(),engine.getSelection().getEndIndex()});
	}

}
