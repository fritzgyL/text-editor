package fr.istic.aco.editorLI.app.receiver;

public final class Text {
	
	private final String content;
	private final int[] caret;
	
	public Text(String content, int[] caret) {
		this.content = content;
		this.caret = caret;
	}

	public String getContent() {
		return content;
	}

	public int[] getCaret() {
		return caret;
	}
	
	
}
