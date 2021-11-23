package fr.istic.aco.editorLI.app.receiver;

import fr.istic.aco.editorLI.app.command.ICommand;

public interface Recorder {

	public void save(ICommand command);
	public void replay();
	public void start();
	public void stop();


}
