package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineState;
import fr.istic.aco.editorLI.app.memento.State;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Concrete command for inserting text and also originator in the momento design
 * pattern
 * 
 * @author Fritzgy Lubin
 *
 */
public class InsertCommand extends BaseCommand {

	private String text;

	public InsertCommand(Engine engine, Recorder recorder, Stack<EngineState> engineStates) {
		super(engine, recorder, engineStates);
	}

	/**
	 * @return the newly created memento object
	 */
	public State save() {
		return new State(text);
	}

	/**
	 * @param state the memento object consumes state to restore the previous state.
	 */
	public void restore(State state) {
		text = state.getText();
	}

	@Override
	public Text execute() {
		resetCounter();
		setSelection();
		text = Character.toString(editor.getCharToInsert());
		engine.insert(text);
		recorder.save(this);
		saveEngineState();
		System.out.println(engineStates.size());

		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
