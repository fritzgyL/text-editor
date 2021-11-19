package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;

public class DeleteCommand extends BaseICommand implements ICommand {

	public DeleteCommand(Engine engine) {
		super(engine);
	}

	@Override
	public void execute() {
		setSelection();
		engine.delete();
		System.out.println(engine.getBufferContents());
	}

}
