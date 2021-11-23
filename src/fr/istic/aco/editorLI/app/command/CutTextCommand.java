package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;

/**
 * 
 * @author Ibouraima/Lubin
 *
 *         cc to cut text
 *
 */
public class CutTextCommand extends BaseICommand {

	public CutTextCommand(Engine engine) {
		super(engine);
	}

	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}

	@Override
	public String execute() {
		setSelection();
		engine.cutSelectedText();
		return engine.getBufferContents();
	}

}
