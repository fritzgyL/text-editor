package fr.istic.aco.editorLI.app.app;

import fr.istic.aco.editorLI.app.command.InsertTextCommand;
import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Selection;
import fr.istic.aco.editorLI.app.receiver.MySelectionImpl;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder buffer = new StringBuilder();
		Selection selection = new MySelectionImpl(buffer);
		Engine engine = new EngineImpl(buffer, selection);
		InsertTextCommand insertCommand = new InsertTextCommand(engine);
		TextEditor textEditor = new TextEditor(insertCommand);
		insertCommand.setEditor(textEditor);
	}
}
