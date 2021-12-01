package fr.istic.aco.editorLI.app.receiver;

import java.util.Stack;

import fr.istic.aco.editorLI.app.command.ICommand;

public class RecorderImpl implements Recorder {
	private Stack<ICommand> commands;

	public RecorderImpl() {
		commands = new Stack<ICommand>();
	}

	@Override
	public void save(ICommand command) {
		commands.push(command);
	}

	@Override
	public Text replay() throws Exception {
		if (commands.empty()) {
			throw new Exception("no commands saved");
		} else {
			return commands.pop().execute();
		}
	}

}
