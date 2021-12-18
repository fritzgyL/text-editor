package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.GUI.TextEditor;
import fr.istic.aco.editorLI.app.memento.EngineMemento;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Abstract class helping in code reuse
 * 
 * @author Fritzgy Lubin
 * 
 */
public abstract class BaseCommand implements ICommand {
	protected Engine engine;
	protected TextEditor editor;
	protected Recorder recorder;
	protected Stack<EngineMemento> engineStates;

	public BaseCommand(Engine engine, Recorder recorder, Stack<EngineMemento> engineStates) {
		this.engine = engine;
		this.editor = null;
		this.recorder = recorder;
		this.engineStates = engineStates;
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
	 * save engine state before modifying buffer content
	 */
	public void saveEngineState() {
		EngineMemento es = (((EngineImpl) engine).save());
		engineStates.push(es);
	}

	@Override
	public abstract Text execute() throws Exception;

	@Override
	public Text undo() {
		EngineMemento es;
		if (!engineStates.isEmpty()) {
			es = engineStates.pop();
		} else {
			es = new EngineMemento("", 0, 0);
		}
		((EngineImpl) engine).restore(es);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
