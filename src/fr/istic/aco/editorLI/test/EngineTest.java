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
    	engine = new EngineImpl(buffer,selection);
    }

    private void todo() {
        fail("Unimplemented test");
    }
    

    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
    	//check if at first buffer content is empty
        assertEquals("",engine.getBufferContents());
        engine.insert("hey");
        assertEquals("hey",engine.getBufferContents());
        engine.insert("hey");
        assertEquals("heyhey",engine.getBufferContents());
    }
    
    @Test
    void testDelete() {
    	engine.insert("hey");
    	engine.getSelection().setBeginIndex(0);
    	engine.getSelection().setEndIndex(2);
        engine.delete();
        assertEquals("y",engine.getBufferContents());
    	engine.getSelection().setEndIndex(1);
    	engine.delete();
        assertEquals("",engine.getBufferContents());
    }
    
    @Test
    void getClipboardContents() {
        assertEquals("",engine.getClipboardContents());
        engine.insert("hey");
        assertEquals("",engine.getClipboardContents());
    	engine.getSelection().setBeginIndex(0);
    	engine.getSelection().setEndIndex(2);
    	engine.cutSelectedText();
        assertEquals("he",engine.getClipboardContents());
    }

    @Test
    void cutSelectedText() {
        assertEquals("",engine.getClipboardContents());
    	engine.insert("hey");
    	engine.getSelection().setBeginIndex(0);
    	engine.getSelection().setEndIndex(2);
    	engine.cutSelectedText();
        assertEquals("he",engine.getClipboardContents());
        assertEquals("y",engine.getBufferContents());
    }

    @Test
    void copySelectedText() {
        assertEquals("",engine.getClipboardContents());
    	engine.insert("hey");
    	engine.getSelection().setBeginIndex(0);
    	engine.getSelection().setEndIndex(2);
    	engine.copySelectedText();
        assertEquals("he",engine.getClipboardContents());
        assertEquals("hey",engine.getBufferContents());
    }

    @Test
    void pasteClipboard() {
        assertEquals("",engine.getClipboardContents());
    	engine.insert("hey");
    	engine.getSelection().setBeginIndex(0);
    	engine.getSelection().setEndIndex(2);
    	engine.pasteClipboard();
        assertEquals("",engine.getClipboardContents());
        assertEquals("hey",engine.getBufferContents());
    }
}
