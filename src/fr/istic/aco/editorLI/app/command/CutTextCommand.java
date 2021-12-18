package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.memento.EngineState;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command to cut text
 * 
 * @author Ibouraima/Lubin
 *
 * 
 */
public class CutTextCommand extends BaseCommand {

	public CutTextCommand(Engine engine, Recorder recorder, Stack<EngineState> engineStates) {
		super(engine, recorder, engineStates);
	}

	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}

	@Override
	public Text execute() {
		resetCounter();
		setSelection();
		engine.cutSelectedText();
		// save command into the recorder
		recorder.save(this);
		saveEngineState();
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });

	}

}
