package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Engine;

public class PasteTextCommand extends BaseICommand{

	public PasteTextCommand(Engine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		setSelection();
		engine.pasteClipboard();
		System.out.println(engine.getBufferContents());
		System.out.println(engine.getClipboardContents());
	}
	
}
