package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.Text;

public class DeleteCommand extends BaseICommand implements ICommand {

	public DeleteCommand(Engine engine, Recorder recorder) {
		super(engine,recorder);
	}

	@Override
	public Text execute() {
		setSelection();
		engine.delete();
		//save command into the recorder
		recorder.save(this);
		return new Text(engine.getBufferContents(),new int[]{engine.getSelection().getBeginIndex(),engine.getSelection().getEndIndex()});
	}

}
