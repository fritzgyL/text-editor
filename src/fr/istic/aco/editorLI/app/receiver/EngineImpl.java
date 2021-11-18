package fr.istic.aco.editorLI.app.receiver;

public class EngineImpl implements Engine {

	private StringBuilder buffer;
	private String clipboard;
	private final Selection selection;

	public EngineImpl(StringBuilder buffer, Selection selection) {
		this.selection = selection;
		this.buffer = buffer;
		this.clipboard = "";
	}

	/**
	 * Provides access to the selection control object
	 *
	 * @return the selection object
	 */
	@Override
	public Selection getSelection() {
		// TODO
		return this.selection;
	}

	/**
	 * Provides the whole contents of the buffer, as a string
	 *
	 * @return a copy of the buffer's contents
	 */
	@Override
	public String getBufferContents() {
		// TODO
		return this.buffer.toString();
	}

	/**
	 * Provides the clipboard contents
	 *
	 * @return a copy of the clipboard's contents
	 */
	@Override
	public String getClipboardContents() {
		// TODO
		return this.clipboard;
	}

	/**
	 * Removes the text within the interval specified by the selection control
	 * object, from the buffer.
	 */
	@Override
	public void cutSelectedText() {
		int startIndex = selection.getBeginIndex();
		int endIndex = selection.getEndIndex();
		String newText = buffer.substring(startIndex, endIndex);
		buffer.replace(startIndex, endIndex, "");
		clipboard = newText;
	}

	/**
	 * Copies the text within the interval specified by the selection control object
	 * into the clipboard.
	 */
	@Override
	public void copySelectedText() {
		int startIndex = selection.getBeginIndex();
		int endIndex = selection.getEndIndex();
		this.clipboard = buffer.substring(startIndex, endIndex);
	}

	/**
	 * Replaces the text within the interval specified by the selection object with
	 * the contents of the clipboard.
	 */
	@Override
	public void pasteClipboard() {
		int startIndex = selection.getBeginIndex();
		int endIndex = selection.getEndIndex();
		if (!clipboard.isEmpty()) {
			buffer.replace(startIndex, endIndex, clipboard);
			selection.setBeginIndex(startIndex+1);
			selection.setEndIndex(startIndex+1);
			
		}
	}

	/**
	 * Inserts a string in the buffer, which replaces the contents of the selection
	 *
	 * @param s the text to insert
	 */
	@Override
	public void insert(String s) {
		int startIndex = selection.getBeginIndex();
		int endIndex = selection.getEndIndex();
		buffer.replace(startIndex, endIndex, s);
		if (endIndex != startIndex) {
			selection.setEndIndex(startIndex);
		} else {
			selection.setBeginIndex(buffer.length());
			selection.setEndIndex(buffer.length());
		}

	}

	/**
	 * Removes the contents of the selection in the buffer
	 */
	@Override
	public void delete() {
		int startIndex = selection.getBeginIndex();
		int endIndex = selection.getEndIndex();
		if (buffer.length() > 0) {
			if (endIndex > startIndex) {
				buffer.delete(startIndex, endIndex);
				selection.setEndIndex(startIndex);
			} else if (endIndex == startIndex) {
				buffer.delete(startIndex - 1, endIndex);
				selection.setBeginIndex(endIndex - 1);
				selection.setEndIndex(endIndex - 1);
			}
		}

	}

}
