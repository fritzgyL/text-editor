package fr.istic.aco.editorLI.app.receiver;

import fr.istic.aco.editorLI.app.memento.EngineState;
import fr.istic.aco.editorLI.app.memento.State;
import fr.istic.aco.editorLI.app.utils.Text;

public class EngineImpl implements Engine {

	private StringBuilder buffer;
	private String clipboard;
	private final Selection selection;
	private int startIndex;
	private int endIndex;

	public EngineImpl(StringBuilder buffer, Selection selection) {
		this.selection = selection;
		this.buffer = buffer;
		this.clipboard = "";
	}

	/**
	 * @param start the start index of the selection
	 * @param end   the end index of the selection update buffer selection start and
	 *              end index
	 */
	public void setUpSelectionIndex(int start, int end) {
		startIndex = start;
		endIndex = end;
	}

	/**
	 * Provides access to the selection control object
	 *
	 * @return the selection object
	 */
	@Override
	public Selection getSelection() {
		return this.selection;
	}

	/**
	 * Provides the whole contents of the buffer, as a string
	 *
	 * @return a copy of the buffer's contents
	 */
	@Override
	public String getBufferContents() {
		return this.buffer.toString();
	}

	/**
	 * Provides the clipboard contents
	 *
	 * @return a copy of the clipboard's contents
	 */
	@Override
	public String getClipboardContents() {
		return this.clipboard;
	}

	/**
	 * Removes the text within the interval specified by the selection control
	 * object, from the buffer.
	 */
	@Override
	public void cutSelectedText() {
		setUpSelectionIndex(selection.getBeginIndex(), selection.getEndIndex());
		String newText = buffer.substring(startIndex, endIndex);
		if (newText != "") {
			buffer.delete(startIndex, endIndex);
			clipboard = newText;
			selection.setBeginIndex(startIndex);
			selection.setEndIndex(startIndex);
		}
	}

	/**
	 * Copies the text within the interval specified by the selection control object
	 * into the clipboard.
	 */
	@Override
	public void copySelectedText() {
		setUpSelectionIndex(selection.getBeginIndex(), selection.getEndIndex());
		String newText = buffer.substring(startIndex, endIndex);
		if (newText != "") {
			this.clipboard = buffer.substring(startIndex, endIndex);
		}
	}

	/**
	 * Replaces the text within the interval specified by the selection object with
	 * the contents of the clipboard.
	 */
	@Override
	public void pasteClipboard() {
		setUpSelectionIndex(selection.getBeginIndex(), selection.getEndIndex());
		if (!clipboard.isEmpty()) {
			buffer.replace(startIndex, endIndex, clipboard);
			selection.setBeginIndex(startIndex + clipboard.length());
			selection.setEndIndex(startIndex + clipboard.length());
		}
	}

	/**
	 * Inserts a string in the buffer, which replaces the contents of the selection
	 *
	 * @param s the text to insert
	 */
	@Override
	public void insert(String s) {
		setUpSelectionIndex(selection.getBeginIndex(), selection.getEndIndex());
		buffer.replace(startIndex, endIndex, s);
		selection.setBeginIndex(startIndex + 1);
		selection.setEndIndex(startIndex + 1);
	}

	/**
	 * Removes the contents of the selection in the buffer
	 */
	@Override
	public void delete() {
		setUpSelectionIndex(selection.getBeginIndex(), selection.getEndIndex());
		if (buffer.length() > 0) {
			if (endIndex != startIndex) {
				buffer.delete(startIndex, endIndex);
				selection.setEndIndex(startIndex);
			} else {
				buffer.delete(startIndex - 1, endIndex);
				selection.setBeginIndex(endIndex - 1);
				selection.setEndIndex(endIndex - 1);
			}
		}

	}

	public EngineState save() {
		return new EngineState(buffer.toString(), selection.getBeginIndex(), selection.getEndIndex());
	}

	public void restore(EngineState state) {
		Text text = state.getText();
		buffer.replace(0, buffer.length(),text.getContent());
		//buffer = new StringBuilder(text.getContent());
		System.out.println(buffer.toString());
		selection.setBeginIndex(text.getCaret()[0]);
		selection.setEndIndex(text.getCaret()[1]);
	}

}