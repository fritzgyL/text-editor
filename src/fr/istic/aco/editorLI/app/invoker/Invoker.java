package fr.istic.aco.editorLI.app.invoker;

import fr.istic.aco.editorLI.app.command.ICommand;

/**
 * Interface for all the text editor command
 * 
 * @author Fritzgy Lubin
 *
 */
public interface Invoker {

	/**
	 * insert character in the buffer
	 * 
	 * @param character the character to insert in the buffer
	 * @throws Exception
	 */
	public void insert(ICommand command) throws Exception;

	/**
	 * cut selected text
	 * 
	 * @throws Exception
	 */
	public void cut(ICommand command) throws Exception;

	/**
	 * copy selected text
	 * 
	 * @throws Exception
	 */
	public void copy(ICommand command) throws Exception;

	/**
	 * paste the clipboard content
	 * 
	 * @throws Exception
	 */
	public void paste(ICommand command) throws Exception;

	/**
	 * delete selected text
	 * 
	 * @throws Exception
	 */
	public void delete(ICommand command) throws Exception;

	/**
	 * replay last action
	 * 
	 * @throws Exception
	 */
	public void replay(ICommand command) throws Exception;

	/**
	 * Undo the last action of the user
	 */
	public void undo(ICommand redoCommand);

	/**
	 * Redo the last undone command
	 * 
	 * @param redoCommand
	 */
	public void redo(ICommand redoCommand);

}
