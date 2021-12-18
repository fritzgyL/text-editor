package fr.istic.aco.editorLI.app.memento;

/**
 * Memento of the insert command Hold the current text
 * 
 * @author Fritzgy Lubin
 */
public class InsertMemento {

	private final String text;

	public InsertMemento(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
