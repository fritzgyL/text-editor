package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.Text;

/**
 * Concrete command for pasting the clipboard content
 * 
 * @author Ibouraima/Lubin
 *
 */
public class PasteTextCommand extends BaseICommand {

	public PasteTextCommand(Engine engine, Recorder recorder) {
		super(engine, recorder);
	}

	@Override
	public Text execute() {
		setSelection();
		engine.pasteClipboard();
		// save command into the recorder
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
