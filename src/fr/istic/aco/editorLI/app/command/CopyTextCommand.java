package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.*;

/**
 * 
 * @author LI
 * 
 * cc qui fait une copy
 */
public class CopyTextCommand  implements ICommand{
	
	private Engine engine;
	private TextEditor editor;

	public CopyTextCommand(Engine engine) {
		//super();
		this.engine = engine;
	}
	
	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stubt
		engine.getSelection().setBeginIndex(editor.getStartsel());
		engine.getSelection().setEndIndex(editor.getEndsel());
		engine.copySelectedText();
		//System.out.println();
	}

}
