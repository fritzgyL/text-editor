package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.*;

/**
 * 
 * @author LI
 * 
 *         cc qui fait une copy
 */
public class CopyTextCommand extends BaseICommand {

	public CopyTextCommand(Engine engine, Recorder recorder) {
		super(engine, recorder);
	}

	@Override
	public Text execute() {
		setSelection();
		engine.copySelectedText();
		// save command into the recorder
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
