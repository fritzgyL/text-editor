package fr.istic.aco.editorLI.app.app;

import fr.istic.aco.editorLI.app.command.BaseICommand;
import fr.istic.aco.editorLI.app.command.CopyTextCommand;
import fr.istic.aco.editorLI.app.command.CutTextCommand;
import fr.istic.aco.editorLI.app.command.DeleteCommand;
import fr.istic.aco.editorLI.app.command.InsertCommand;
import fr.istic.aco.editorLI.app.command.PasteTextCommand;
import fr.istic.aco.editorLI.app.command.ReplayCommand;
import fr.istic.aco.editorLI.app.invoker.TextEditor;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.RecorderImpl;
import fr.istic.aco.editorLI.app.receiver.Selection;
import fr.istic.aco.editorLI.app.receiver.SelectionImpl;

/**
 * @author Fritzgy Lubin
 * Main application entry point
 *
 */
public class App {

	public static void main(String[] args) {
		StringBuilder buffer = new StringBuilder();
		Selection selection = new SelectionImpl(buffer);
		Engine engine = new EngineImpl(buffer, selection);
		Recorder recorder = new RecorderImpl();
		BaseICommand insertCommand = new InsertCommand(engine, recorder);
		BaseICommand deleteCommand = new DeleteCommand(engine, recorder);
		BaseICommand cutCommand = new CutTextCommand(engine, recorder);
		BaseICommand pasteCmmand = new PasteTextCommand(engine, recorder);
		BaseICommand copyCommand = new CopyTextCommand(engine, recorder);
		BaseICommand replayCommand = new ReplayCommand(engine, recorder);
		TextEditor textEditor = new TextEditor(insertCommand, deleteCommand, cutCommand, pasteCmmand, copyCommand,
				replayCommand);
		insertCommand.setEditor(textEditor);
		deleteCommand.setEditor(textEditor);
		cutCommand.setEditor(textEditor);
		pasteCmmand.setEditor(textEditor);
		copyCommand.setEditor(textEditor);
		replayCommand.setEditor(textEditor);
	}
}
