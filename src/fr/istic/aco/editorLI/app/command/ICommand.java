package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Text;

/**
 * Interace for the command pattern
 * 
 * @author Fritzgy Lubin
 *
 *
 */
public interface ICommand {

	/**
	 * execute the command
	 * 
	 * @return
	 * @throws Exception
	 */
	Text execute() throws Exception;

}
