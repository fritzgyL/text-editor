package fr.istic.aco.editorLI.app.receiver;

import java.util.Stack;

import fr.istic.aco.editorLI.app.command.ICommand;
import fr.istic.aco.editorLI.app.command.InsertCommand;
import fr.istic.aco.editorLI.app.memento.InsertMemento;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * Receiver in command pattern and Caretaker in memento pattern
 * 
 * @author Fritzgy Lubin
 *
 */
public class RecorderImpl implements Recorder {
	// Store only the memento of insert
	private InsertMemento insertCommandState;
	// stack data structure for storing commands
	private Stack<ICommand> commands;

	public RecorderImpl() {
		commands = new Stack<ICommand>();
	}

	@Override
	public void save(ICommand command) {
		// storing insert memento if command is only an instance of InsertCommand
		if (command instanceof InsertCommand) {
			insertCommandState = ((InsertCommand) command).save();
		}
		commands.push(command);
	}

	@Override
	public Text replay() throws Exception {
		// check if stack is not empty
		if (commands.empty()) {
			throw new Exception("no commands saved");
		} else {
			// check if the mast saved command is an instance of InsertCommand, if so, we
			// restoring the saved state(memento)
			ICommand command = commands.pop();
			if (command instanceof InsertCommand) {
				if (insertCommandState != null) {
					((InsertCommand) command).restore(insertCommandState);
				}
			}
			return command.execute();
		}
	}

}
