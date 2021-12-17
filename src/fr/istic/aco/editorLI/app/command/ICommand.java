package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.utils.Text;

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
	 * @return engine content after execution
	 * @throws Exception
	 */
	Text execute() throws Exception;
	
	/**
	 * undo the command
	 * @return engine content after undoing changes
	 */
	Text undo(); 
}
