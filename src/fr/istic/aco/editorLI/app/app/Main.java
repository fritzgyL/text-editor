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
 * application main entry point
 * 
 * @author Fritzgy Lubin
 *
 */
public class Main {

	private StringBuilder buffer;
	private Selection selection;
	private Engine engine;
	private Recorder recorder;
	private Stack<EngineMemento> engineStates;
	private BaseCommand insertCommand;
	private BaseCommand deleteCommand;
	private BaseCommand cutCommand;
	private BaseCommand pasteCommand;
	private BaseCommand copyCommand;
	private BaseCommand replayCommand;
	private Invoker invoker;
	private TextEditor textEditor;

	public Main() {
		buffer = new StringBuilder();
		selection = new SelectionImpl(buffer);
		engine = new EngineImpl(buffer, selection);
		recorder = new RecorderImpl();
		engineStates = new Stack<EngineMemento>();
		invoker = new InvokerImpl();
		initCommand();
		initTextEditor();
		setUp();
	}

	/**
	 * initialize the commands of the text editor
	 */
	private void initCommand() {
		insertCommand = new InsertCommand(engine, recorder, engineStates);
		deleteCommand = new DeleteCommand(engine, recorder, engineStates);
		cutCommand = new CutTextCommand(engine, recorder, engineStates);
		pasteCommand = new PasteTextCommand(engine, recorder, engineStates);
		copyCommand = new CopyTextCommand(engine, recorder, engineStates);
		replayCommand = new ReplayCommand(engine, recorder, engineStates);
	}

	/**
	 * build the text editor
	 */
	private void initTextEditor() {
		textEditor = new TextEditor.TextEditorBuilder(insertCommand, deleteCommand, cutCommand, copyCommand,
				pasteCommand, replayCommand, invoker).build();
	}

	/**
	 * inject text editor dependencies
	 */
	private void setUp() {
		((InvokerImpl) invoker).setEditor(textEditor);
		insertCommand.setEditor(textEditor);
		deleteCommand.setEditor(textEditor);
		cutCommand.setEditor(textEditor);
		pasteCommand.setEditor(textEditor);
		copyCommand.setEditor(textEditor);
		replayCommand.setEditor(textEditor);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main main = new Main();
	}
}
