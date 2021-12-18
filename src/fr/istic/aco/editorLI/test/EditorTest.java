package fr.istic.aco.editorLI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
 * Test class for integration testing
 * 
 * @author Fritzgy Lubin
 *
 */
public class EditorTest {
	Selection selection;
	Engine engine;
	TextEditor textEditor;

	@BeforeEach
	void setUp() {
		StringBuilder buffer = new StringBuilder();
		selection = new SelectionImpl(buffer);
		engine = new EngineImpl(buffer, selection);
		Recorder recorder = new RecorderImpl();
		Stack<EngineMemento> engineStates = new Stack<EngineMemento>();
		BaseCommand insertCommand = new InsertCommand(engine, recorder, engineStates);
		BaseCommand deleteCommand = new DeleteCommand(engine, recorder, engineStates);
		BaseCommand cutCommand = new CutTextCommand(engine, recorder, engineStates);
		BaseCommand pasteCommand = new PasteTextCommand(engine, recorder, engineStates);
		BaseCommand copyCommand = new CopyTextCommand(engine, recorder, engineStates);
		BaseCommand replayCommand = new ReplayCommand(engine, recorder, engineStates);
		Invoker invoker = new InvokerImpl();
		textEditor = new TextEditor.TextEditorBuilder(insertCommand, deleteCommand, cutCommand, copyCommand,
				pasteCommand, replayCommand, invoker).build();
		((InvokerImpl) invoker).setEditor(textEditor);
		insertCommand.setEditor(textEditor);
		deleteCommand.setEditor(textEditor);
		cutCommand.setEditor(textEditor);
		pasteCommand.setEditor(textEditor);
		copyCommand.setEditor(textEditor);
		replayCommand.setEditor(textEditor);
	}

	@Test
	public void testSelection() throws Exception {
		textEditor.setCharToInsert('C');
		textEditor.insert();
		assertEquals("C", textEditor.getText());
		textEditor.setCaretPosition(0, 1);
		assertEquals(0, textEditor.getSelectionStartIndex());
		assertEquals(1, textEditor.getSelectionEndIndex());
	}

	@Test
	public void testInsert() throws Exception {
		textEditor.setCharToInsert('C');
		textEditor.insert();
		assertEquals("C", textEditor.getText());
		textEditor.setCharToInsert('C');
		textEditor.insert();
		textEditor.insert();
		assertEquals("CCC", textEditor.getText());
	}

	@Test
	public void testInsertOnSelection() throws Exception {
		textEditor.setCharToInsert('C');
		textEditor.insert();
		assertEquals("C", textEditor.getText());
		textEditor.setCharToInsert('C');
		textEditor.insert();
		textEditor.insert();
		assertEquals("CCC", textEditor.getText());
		textEditor.setCaretPosition(0, 3);
		textEditor.insert();
		assertEquals("C", textEditor.getText());
		textEditor.setCaretPosition(1, 1);
		textEditor.insert();
		assertEquals("CC", textEditor.getText());
	}

	@Test
	public void testEditorCharetAfterInsert() throws Exception {
		assertEquals(0, textEditor.getSelectionStartIndex());
		assertEquals(0, textEditor.getSelectionEndIndex());
		textEditor.setCharToInsert('C');
		textEditor.insert();
		assertEquals("C", textEditor.getText());
		assertEquals(1, textEditor.getSelectionStartIndex());
		assertEquals(1, textEditor.getSelectionEndIndex());
	}

	@Test
	public void testEditorCharetAfteDelete() throws Exception {
		textEditor.setCharToInsert('C');
		textEditor.insert();
		textEditor.delete();
		assertEquals(0, textEditor.getSelectionStartIndex());
		assertEquals(0, textEditor.getSelectionEndIndex());
	}

	@Test
	public void testInsertNumber() throws Exception {
		textEditor.setCharToInsert('1');
		textEditor.insert();
		assertEquals("1", textEditor.getText());
	}

	@Test
	public void testDelete() throws Exception {
		textEditor.setCharToInsert('1');
		textEditor.insert();
		textEditor.insert();
		textEditor.delete();
		assertEquals("1", textEditor.getText());
		textEditor.delete();
		assertEquals("", textEditor.getText());
		textEditor.insert();
		textEditor.delete();
		assertEquals("", textEditor.getText());
	}

	@Test
	public void testDeleteEmptyTextArea() throws Exception {
		textEditor.delete();
		assertEquals("", textEditor.getText());
	}

	@Test
	public void testDeleteSpace() throws Exception {
		textEditor.setCharToInsert(' ');
		textEditor.insert();
		textEditor.delete();
		assertEquals("", textEditor.getText());
		textEditor.setCharToInsert('b');
		textEditor.insert();
		textEditor.setCharToInsert(' ');
		textEditor.insert();
		textEditor.delete();
		assertEquals("b", textEditor.getText());
	}

	@Test
	public void testDeleteSelection() throws Exception {
		textEditor.setCharToInsert('b');
		textEditor.insert();
		textEditor.setCharToInsert(' ');
		textEditor.insert();
		textEditor.setCaretPosition(0, 2);
		textEditor.delete();
		assertEquals("", textEditor.getText());
		textEditor.setCharToInsert('b');
		textEditor.insert();
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.delete();
		assertEquals("b", textEditor.getText());
	}

	@Test
	public void testCopyEmptyText() throws Exception {
		textEditor.copy();
		assertEquals("", engine.getClipboardContents());
	}

	@Test
	public void testCopySelection() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCharToInsert('b');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.copy();
		assertEquals("a", engine.getClipboardContents());
	}

	@Test
	public void testCopySpace() throws Exception {
		textEditor.setCharToInsert(' ');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.copy();
		assertEquals(" ", engine.getClipboardContents());
	}

	@Test
	public void testPasteEmptyClipboard() throws Exception {
		textEditor.paste();
		assertEquals("", engine.getClipboardContents());
		assertEquals("", textEditor.getText());
	}

	@Test
	public void testPasteClipboardContentOnSelection() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.copy();
		textEditor.paste();
		assertEquals("a", engine.getClipboardContents());
		assertEquals("a", textEditor.getText());
	}

	@Test
	public void testPasteClipboardContent() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.copy();
		textEditor.setCaretPosition(1, 1);
		textEditor.paste();
		assertEquals("a", engine.getClipboardContents());
		assertEquals("aa", textEditor.getText());
	}

	@Test
	public void testCutEmptyText() throws Exception {
		textEditor.cut();
		assertEquals("", engine.getClipboardContents());
		assertEquals("", textEditor.getText());
	}

	@Test
	public void testCutSelection() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.cut();
		assertEquals("a", engine.getClipboardContents());
	}

	@Test
	public void testPasteAfterCuttingSelection() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.cut();
		assertEquals("a", engine.getClipboardContents());
		textEditor.paste();
		assertEquals("a", textEditor.getText());
	}

	@Test
	public void testEmptyUndo() throws Exception {
		textEditor.undo();
		assertEquals("", textEditor.getText());
	}

	@Test
	public void testEmptyRedo() throws Exception {
		textEditor.redo();
		assertEquals("", textEditor.getText());
		textEditor.setCharToInsert('x');
		textEditor.insert();
		textEditor.redo();
		assertEquals("x", textEditor.getText());
	}

	@Test
	public void testUndoAfterInserting1() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.insert();
		textEditor.undo();
		assertEquals("a", textEditor.getText());
	}

	@Test
	public void testUndoAfterInserting2() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCharToInsert('b');
		textEditor.insert();
		textEditor.undo();
		assertEquals("a", textEditor.getText());
	}

	@Test
	public void testUndoAfterInserting3() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCharToInsert('b');
		textEditor.insert();
		textEditor.undo();
		textEditor.undo();
		assertEquals("", textEditor.getText());
	}

	@Test
	public void testUndoAfterCopy() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.copy();
		textEditor.setCaretPosition(1, 1);
		textEditor.paste();
		textEditor.undo();
		assertEquals("a", textEditor.getText());
	}

	@Test
	public void testLastUndo() throws Exception {
		textEditor.undo();
		assertEquals("", textEditor.getText());
	}

	@Test
	public void testRedoAfterUndo() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.insert();
		textEditor.undo();
		textEditor.redo();
		assertEquals("aa", textEditor.getText());
	}

	@Test
	public void testRedoAfterPasting() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.copy();
		textEditor.setCaretPosition(1, 1);
		textEditor.paste();
		textEditor.undo();
		textEditor.redo();
		assertEquals("aa", textEditor.getText());
	}

	@Test
	public void testReplayForInsertion() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.replay();
		assertEquals("aa", textEditor.getText());
	}

	@Test
	public void testReplayForCuting() throws Exception {
		textEditor.setCharToInsert('a');
		textEditor.insert();
		textEditor.setCaretPosition(0, 1);
		textEditor.cut();
		textEditor.paste();
		textEditor.setCaretPosition(0, 1);
		textEditor.replay();
		assertEquals("a", engine.getClipboardContents());
		assertEquals("a", textEditor.getText());
	}

	@Test
	public void testReplayIfEmptyCommand() throws Exception {
		Throwable exception = assertThrows(Exception.class, () -> textEditor.replay());
		assertEquals("no commands saved", exception.getMessage());
	}

}
