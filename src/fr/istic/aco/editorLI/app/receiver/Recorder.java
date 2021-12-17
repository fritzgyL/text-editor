package fr.istic.aco.editorLI.app.receiver;

import fr.istic.aco.editorLI.app.command.ICommand;
import fr.istic.aco.editorLI.app.utils.Text;

/**
 * @author Fritzgy Lubin
 * Manage saving and replay user actions
 *
 */
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
	
	/**
	 * @return the buffer content after last command has been replayed
	 * @throws Exception if no commands have been saved
	 */
	public Text undo() throws Exception;

}
