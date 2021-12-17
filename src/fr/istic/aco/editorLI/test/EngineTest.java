package fr.istic.aco.editorLI.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Selection;
import fr.istic.aco.editorLI.app.receiver.SelectionImpl;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

	private Engine engine;
	private StringBuilder buffer;
	private Selection selection;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		buffer = new StringBuilder();
		selection = new SelectionImpl(buffer);
		engine = new EngineImpl(buffer, selection);
	}

	@DisplayName("Buffer must be empty after initialisation")
	@Test
	void getSelection() {
		Selection selection = engine.getSelection();
		assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
		assertEquals("", engine.getBufferContents());
	}

	@Test
	void testInsert() {
		engine.insert("h");
		assertEquals("h", engine.getBufferContents());
		engine.insert("0");
		assertEquals("h0", engine.getBufferContents());
		engine.insert(" ");
		assertEquals("h0 ", engine.getBufferContents());
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(2);
		engine.insert("a");
		assertEquals("a ", engine.getBufferContents());
		engine.insert("a");
		assertEquals("aa ", engine.getBufferContents());
	}

	@Test
	void getBufferContents() {
		assertEquals("", engine.getBufferContents());
		engine.insert("h");
		assertEquals("h", engine.getBufferContents());
		engine.insert("e");
		assertEquals("he", engine.getBufferContents());
		engine.delete();
		assertEquals("h", engine.getBufferContents());
	}

	@Test
	void testDelete() {
		engine.insert("hey");
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(3);
		engine.delete();
		assertEquals("", engine.getBufferContents());
		engine.insert("h");
		engine.delete();
		assertEquals("", engine.getBufferContents());
		engine.delete();
		assertEquals("", engine.getBufferContents());
	}

	@Test
	void getClipboardContents() {
		assertEquals("", engine.getClipboardContents());
		engine.insert("hey");
		assertEquals("", engine.getClipboardContents());
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(3);
		engine.cutSelectedText();
		assertEquals("hey", engine.getClipboardContents());
	}

	@Test
	void cutSelectedText() {
		engine.insert("hey");
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(1);
		engine.cutSelectedText();
		assertEquals("h", engine.getClipboardContents());
		assertEquals("ey", engine.getBufferContents());
		engine.cutSelectedText();
		assertEquals("h", engine.getClipboardContents());
		assertEquals("ey", engine.getBufferContents());
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(2);
		engine.cutSelectedText();
		assertEquals("ey", engine.getClipboardContents());
		assertEquals("", engine.getBufferContents());
	}

	@Test
	void copySelectedText() {
		engine.insert("hey");
		engine.copySelectedText();
		assertEquals("", engine.getClipboardContents());
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(2);
		engine.copySelectedText();
		assertEquals("he", engine.getClipboardContents());
		assertEquals("hey", engine.getBufferContents());
	}

	@Test
	void pasteClipboard() {
		engine.insert("hey");
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(1);
		engine.copySelectedText();
		assertEquals("h", engine.getClipboardContents());
		engine.getSelection().setBeginIndex(3);
		engine.getSelection().setEndIndex(3);
		engine.pasteClipboard();
		assertEquals("heyh", engine.getBufferContents());
		engine.pasteClipboard();
		assertEquals("heyhh", engine.getBufferContents());
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(1);
		engine.copySelectedText();
		engine.pasteClipboard();
		assertEquals("heyhh", engine.getBufferContents());
		assertEquals("h", engine.getClipboardContents());
		engine.pasteClipboard();
		assertEquals("hheyhh", engine.getBufferContents());
		assertEquals("h", engine.getClipboardContents());
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(3);
		engine.copySelectedText();
		engine.getSelection().setBeginIndex(5);
		engine.getSelection().setEndIndex(6);
		engine.pasteClipboard();
		assertEquals("hheyhhhe", engine.getBufferContents());
		assertEquals("hhe", engine.getClipboardContents());
	}

	@Test
	void pasteEmptyClipboard() {
		assertEquals("", engine.getClipboardContents());
		engine.pasteClipboard();
		assertEquals("", engine.getBufferContents());
	}
}
