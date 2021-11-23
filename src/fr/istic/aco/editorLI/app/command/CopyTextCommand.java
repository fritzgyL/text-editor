package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.*;

/**
 * 
 * @author LI
 * 
 *         cc qui fait une copy
 */
public class CopyTextCommand extends BaseICommand {

	public CopyTextCommand(Engine engine) {
		super(engine);
	}

	@Override
	public String execute() {
		setSelection();
		engine.copySelectedText();
		return engine.getBufferContents();
	}

}
