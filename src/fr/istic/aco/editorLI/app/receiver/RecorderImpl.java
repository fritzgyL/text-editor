package fr.istic.aco.editorLI.app.receiver;

import java.util.Stack;

import fr.istic.aco.editorLI.app.command.ICommand;

public class RecorderImpl implements Recorder {

	private ICommand command;
	private Memento memento;

	public RecorderImpl() {

	}

	@Override
	public void save(ICommand command) {
		this.command = command;
	}

	@Override
	public void replay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
