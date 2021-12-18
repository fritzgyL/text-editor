package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineState;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command for pasting the clipboard content
 * 
 * @author Ibouraima/Lubin
 *
 */
public class PasteTextCommand extends BaseCommand {

	public PasteTextCommand(Engine engine, Recorder recorder, Stack<EngineState> engineStates) {
		super(engine, recorder, engineStates);
	}

	@Override
	public Text execute() {
		resetCounter();
		setSelection();
		saveEngineState();
		engine.pasteClipboard();
		// save command into the recorder
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
