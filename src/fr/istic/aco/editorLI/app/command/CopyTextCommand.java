package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineState;
import fr.istic.aco.editorLI.app.receiver.*;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command for copy
 * 
 * @author Ibouraima/Lubin
 * 
 * 
 */
public class CopyTextCommand extends BaseCommand {

	public CopyTextCommand(Engine engine, Recorder recorder, Stack<EngineState> engineStates) {
		super(engine, recorder, engineStates);
	}

	@Override
	public Text execute() {
		setSelection();
		engine.copySelectedText();
		// save command into the recorder
		recorder.save(this);
		// saveEngineState();
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
