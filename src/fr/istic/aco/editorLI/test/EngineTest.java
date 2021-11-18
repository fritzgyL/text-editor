package fr.istic.aco.editorLI.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editorLI.app.receiver.Engine;
import fr.istic.aco.editorLI.app.receiver.EngineImpl;
import fr.istic.aco.editorLI.app.receiver.Selection;
import fr.istic.aco.editorLI.app.receiver.MySelectionImpl;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

	private Engine engine;
	private StringBuilder buffer;
	private Selection selection;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		buffer = new StringBuilder();
		selection = new MySelectionImpl(buffer);
		engine = new EngineImpl(buffer, selection);
	}

//    private void todo() {
//        fail("Unimplemented test");
//    }

	@DisplayName("Buffer must be empty after initialisation")
	@Test
	void getSelection() {
		Selection selection = engine.getSelection();
		assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
		assertEquals("", engine.getBufferContents());
	}
	
	@Test
	void testInsert() {
		assertEquals("", engine.getBufferContents());
		engine.insert("hey");
		assertEquals("hey", engine.getBufferContents());
		engine.getSelection().setBeginIndex(3);
		engine.getSelection().setEndIndex(3);
		engine.insert("a");
		assertEquals("heya", engine.getBufferContents());
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(1);
		engine.insert("a");
		assertEquals("aeya", engine.getBufferContents());

	}

	@Test
	void getBufferContents() {
		// check if at first buffer content is empty
		assertEquals("", engine.getBufferContents());
		engine.insert("hey");
		assertEquals("hey", engine.getBufferContents());
		engine.insert("hey");
		assertEquals("heyhey", engine.getBufferContents());
		engine.delete();
		assertEquals("heyhe", engine.getBufferContents());
	}

	@Test
	void testDelete() {
		engine.insert("hey");
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(3);
		engine.delete();
		assertEquals("", engine.getBufferContents());
		engine.insert("hey");
		System.out.println(engine.getSelection().getBeginIndex());
		engine.delete();
		assertEquals("he", engine.getBufferContents());
		engine.delete();
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

	}
	
	@Test
	void pasteEmptyClipboard() {
		assertEquals("", engine.getClipboardContents());
		engine.pasteClipboard();
		assertEquals("", engine.getBufferContents());
	}
}
