package fr.istic.aco.editorLI.app.app;

import fr.istic.aco.editorLI.app.command.BaseICommand;
import fr.istic.aco.editorLI.app.command.DeleteCommand;
import fr.istic.aco.editorLI.app.command.InsertCommand;
import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Selection;
import fr.istic.aco.editorLI.app.receiver.MySelectionImpl;

public class App {

	public static void main(String[] args) {
		StringBuilder buffer = new StringBuilder();
		Selection selection = new MySelectionImpl(buffer);
		Engine engine = new EngineImpl(buffer, selection);
		BaseICommand insertCommand = new InsertCommand(engine);
		BaseICommand deleteCommand = new DeleteCommand(engine);
		TextEditor textEditor = new TextEditor(insertCommand, deleteCommand);
		insertCommand.setEditor(textEditor);
		deleteCommand.setEditor(textEditor);

	}
}
