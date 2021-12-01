package fr.istic.aco.editorLI.app.command;

import fr.istic.aco.editorLI.app.receiver.Text;

public interface ICommand {

	/**
	 * execute the command
	 * 
	 * @return
	 * @throws Exception
	 */
	Text execute() throws Exception;

}
