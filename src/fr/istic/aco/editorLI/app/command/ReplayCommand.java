package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.Text;

public class ReplayCommand extends BaseICommand {

	public ReplayCommand(Engine engine, Recorder recorder) {
		super(engine, recorder);
	}

	@Override
	public Text execute() throws Exception {
		return recorder.replay();
	}

}
