package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;

/**
 * 
 * @author Ibouraima/Lubin
 *
 *cc to cut text
 *
 */
public class CutTextCommand implements ICommand {
	
	private Engine engine;
	private TextEditor editor;
	
	public CutTextCommand(Engine engine) {
		//super();
		this.engine = engine;
	}
	
	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		engine.cutSelectedText();
	}

}
