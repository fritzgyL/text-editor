package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.memento.EngineState;
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
	protected Stack<EngineState> engineStates;
	protected int counter;

	public BaseCommand(Engine engine, Recorder recorder, Stack<EngineState> engineStates) {
		this.engine = engine;
		this.editor = null;
		this.recorder = recorder;
		this.engineStates = engineStates;
		counter = 0;
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

	public void saveEngineState() {
		EngineState es = (((EngineImpl) engine).save());
		engineStates.push(es);
	}

	@Override
	public abstract Text execute() throws Exception;

	@Override
	public Text undo() {
		if (counter == 0) {
			engineStates.pop();
		}
		counter++;
		EngineState es;
		if (!engineStates.isEmpty()) {
			es = engineStates.pop();
		} else {
			es = new EngineState("", 0, 0);
		}
		((EngineImpl) engine).restore(es);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

	public void resetCounter() {
		counter = 0;
	}

}
