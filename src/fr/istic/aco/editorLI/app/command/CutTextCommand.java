package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.Text;

/**
 * Concrete command to cut text
 * 
 * @author Ibouraima/Lubin
 
 *
 */
public class CutTextCommand extends BaseICommand {

	public CutTextCommand(Engine engine, Recorder recorder) {
		super(engine, recorder);
	}

	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}

	@Override
	public Text execute() {
		setSelection();
		engine.cutSelectedText();
		// save command into the recorder
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });

	}

}
