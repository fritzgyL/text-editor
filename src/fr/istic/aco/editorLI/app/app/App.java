package fr.istic.aco.editorLI.app.app;

import java.util.Stack;

import fr.istic.aco.editorLI.app.GUI.TextEditor;
import fr.istic.aco.editorLI.app.command.BaseCommand;
import fr.istic.aco.editorLI.app.command.CopyTextCommand;
import fr.istic.aco.editorLI.app.command.CutTextCommand;
import fr.istic.aco.editorLI.app.command.DeleteCommand;
import fr.istic.aco.editorLI.app.command.InsertCommand;
import fr.istic.aco.editorLI.app.command.PasteTextCommand;
import fr.istic.aco.editorLI.app.command.ReplayCommand;
import fr.istic.aco.editorLI.app.invoker.Invoker;
import fr.istic.aco.editorLI.app.invoker.InvokerImpl;
import fr.istic.aco.editorLI.app.memento.EngineMemento;
import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Recorder;
import fr.istic.aco.editorLI.app.receiver.RecorderImpl;
import fr.istic.aco.editorLI.app.receiver.Selection;
import fr.istic.aco.editorLI.app.receiver.SelectionImpl;

/**
 * @author Fritzgy Lubin Main application entry point
 *
 */
public class App {

	public static void main(String[] args) {
		StringBuilder buffer = new StringBuilder();
		Selection selection = new SelectionImpl(buffer);
		Engine engine = new EngineImpl(buffer, selection);
		Recorder recorder = new RecorderImpl();
		Stack<EngineMemento> engineStates = new Stack<EngineMemento>();
		BaseCommand insertCommand = new InsertCommand(engine, recorder, engineStates);
		BaseCommand deleteCommand = new DeleteCommand(engine, recorder, engineStates);
		BaseCommand cutCommand = new CutTextCommand(engine, recorder, engineStates);
		BaseCommand pasteCommand = new PasteTextCommand(engine, recorder, engineStates);
		BaseCommand copyCommand = new CopyTextCommand(engine, recorder, engineStates);
		BaseCommand replayCommand = new ReplayCommand(engine, recorder, engineStates);
		Invoker invoker = new InvokerImpl();
		TextEditor textEditor = new TextEditor.TextEditorBuilder(insertCommand, deleteCommand, cutCommand, copyCommand,
				pasteCommand, replayCommand, invoker).build();
		((InvokerImpl) invoker).setEditor(textEditor);
		insertCommand.setEditor(textEditor);
		deleteCommand.setEditor(textEditor);
		cutCommand.setEditor(textEditor);
		pasteCommand.setEditor(textEditor);
		copyCommand.setEditor(textEditor);
		replayCommand.setEditor(textEditor);
	}
}
