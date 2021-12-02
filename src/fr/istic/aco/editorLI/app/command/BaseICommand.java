package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.Text;

/**
 * Abstract class helping in code reuse
 * 
 * @author Fritzgy Lubin
 * 
 */
public abstract class BaseICommand implements ICommand {
	protected Engine engine;
	protected TextEditor editor;
	protected Recorder recorder;

	public BaseICommand(Engine engine, Recorder recorder) {
		this.engine = engine;
		this.editor = null;
		this.recorder = recorder;
	}

	/**
	 * @param editor the text editor that invokes the commands injects editor
	 */
	public void setEditor(TextEditor editor) {
		this.editor = editor;
	}

	/**
	 * set the selection of the engine buffer
	 */
	public void setSelection() {
		engine.getSelection().setBeginIndex(editor.getSelectionStartIndex());
		engine.getSelection().setEndIndex(editor.getSelectionEndIndex());
	}

	/**
	 * @return buffer content
	 */
	public String getText() {
		return engine.getBufferContents();
	}

	@Override
	public abstract Text execute() throws Exception;

}
