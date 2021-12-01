package fr.istic.aco.editorLI.app.receiver;

import fr.istic.aco.editorLI.app.command.ICommand;

public interface Recorder {

	/**
	 * @param command save command
	 */
	public void save(ICommand command);

	/**
	 * @return the buffer content after last command has been replayed
	 * @throws Exception if no commands have been saved
	 */
	public Text replay() throws Exception;

}
