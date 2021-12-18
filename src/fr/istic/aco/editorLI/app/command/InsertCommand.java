package fr.istic.aco.editorLI.app.command;

import java.util.Stack;

import fr.istic.aco.editorLI.app.memento.EngineMemento;
import fr.istic.aco.editorLI.app.memento.InsertMemento;
import fr.istic.aco.editorLI.app.receiver.Engine;
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

	public InsertCommand(Engine engine, Recorder recorder, Stack<EngineMemento> engineStates) {
		super(engine, recorder, engineStates);
	}

	/**
	 * @return the newly created memento object
	 */
	public InsertMemento save() {
		return new InsertMemento(text);
	}

	/**
	 * @param state the memento object consumes state to restore the previous state.
	 */
	public void restore(InsertMemento state) {
		text = state.getText();
	}

	/**
	 * set the text to insert
	 * 
	 * @param text the text to insert
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Text execute() {
		setSelection();
		saveEngineState();
		engine.insert(text);
		recorder.save(this);
		return new Text(engine.getBufferContents(),
				new int[] { engine.getSelection().getBeginIndex(), engine.getSelection().getEndIndex() });
	}

}
