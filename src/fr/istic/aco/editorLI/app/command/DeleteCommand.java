package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineState;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command for deleting text
 * 
 * @author Fritzgy Lubin
 *
 */
public class DeleteCommand extends BaseCommand implements ICommand {

	public DeleteCommand(Engine engine, Recorder recorder, Stack<EngineState> engineStates) {
		super(engine, recorder, engineStates);
	}

	@Override
	public Text execute() {
		resetCounter();
		setSelection();
		saveEngineState();
		engine.delete();
		// save command into the recorder
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
