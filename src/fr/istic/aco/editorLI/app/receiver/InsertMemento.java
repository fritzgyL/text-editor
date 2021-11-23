package fr.istic.aco.editorLI.app.receiver;

public class InsertMemento implements Memento {

	private String text;

	public InsertMemento(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
