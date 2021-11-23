package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.*;

/**
 * 
 * @author LI
 * 
 * cc qui fait une copy
 */
public class CopyTextCommand extends BaseICommand{

	public CopyTextCommand(Engine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		setSelection(); 
		engine.copySelectedText();
		System.out.println(engine.getBufferContents());
		System.out.println(engine.getClipboardContents());
		
	}
	


}
