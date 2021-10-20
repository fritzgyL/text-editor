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

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    	Selection selection = new SelectionImpl();
        engine = new EngineImpl(selection);
    }

    private void todo() {
        fail("Unimplemented test");
    }
    
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        todo();
    }

    @Test
    void getClipboardContents() {
        todo();
    }

    @Test
    void cutSelectedText() {
        todo();
    }

    @Test
    void copySelectedText() {
        todo();
    }

    @Test
    void pasteClipboard() {
        todo();
    }
}
