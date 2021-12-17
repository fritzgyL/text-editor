package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.memento.State;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.Text;

/**
 * Concrete command for inserting text and also originator in the momento design
 * pattern
 * 
 * @author Fritzgy Lubin
 *
 */
public class InsertCommand extends BaseICommand {

	private String text;

	public InsertCommand(Engine engine, Recorder recorder) {
		super(engine, recorder);
	}

	/**
	 * @return the newly created memento object
	 */
	public State save() {
		return new State(text);
	}

	/**
	 * consumes state to restore the previous state.
	 * 
	 * @param state the memento object
	 */
	public void restore(State state) {
		text = state.getText();
	}

	@Override
	public Text execute() {
		setSelection();
		text = Character.toString(editor.getCharToInsert());
		engine.insert(text);
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
