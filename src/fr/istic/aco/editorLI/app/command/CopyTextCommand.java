package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineMemento;
import fr.istic.aco.editorLI.app.receiver.*;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command for copy
 * 
 * @author Fritzgy Lubin
 * @author Abdou Ibouraima
 * 
 * 
 */
public class CopyTextCommand extends BaseCommand {

	public CopyTextCommand(Engine engine, Recorder recorder, Stack<EngineMemento> engineStates) {
		super(engine, recorder, engineStates);
	}

	@Override
	public Text execute() {
		setSelection();
		engine.copySelectedText();
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
