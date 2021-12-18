package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineMemento;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command for replaying command
 * 
 * @author Fritzgy Lubin
 * 
 *
 */
public class ReplayCommand extends BaseCommand {

	public ReplayCommand(Engine engine, Recorder recorder, Stack<EngineMemento> engineStates) {
		super(engine, recorder, engineStates);
	}

	@Override
	public Text execute() throws Exception {
		return recorder.replay();
	}

}
