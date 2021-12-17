package fr.istic.aco.editorLI.app.utils;

/**
 * Data structure to store buffer current content
 * 
 * @author Fritzgy Lubin
 *
 */
public final class Text {

	private final String content;
	private final int[] caret;

	public Text(String content, int[] caret) {
		this.content = content;
		this.caret = caret;
	}

	/**
	 * @return the text content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return carret start and end position
	 */
	public int[] getCaret() {
		return caret;
	}

}
