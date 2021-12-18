package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineMemento;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command to cut text
 * 
 * @author Fritzgy Lubin
 * @author Abdou Ibouraima
 *
 * 
 */
public class CutTextCommand extends BaseCommand {

	public CutTextCommand(Engine engine, Recorder recorder, Stack<EngineMemento> engineStates) {
		super(engine, recorder, engineStates);
	}

	@Override
	public Text execute() {
		setSelection();
		saveEngineState();
		engine.cutSelectedText();
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
