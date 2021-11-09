package fr.istic.aco.editorLI.app;

public class SelectionImpl implements Selection {

	private StringBuilder buffer;
	private int beginIndex, endIndex;
	private int bufferBeginIndex;

	public SelectionImpl(StringBuilder buffer) {
		this.buffer = buffer;
		this.beginIndex = buffer.length();
		this.endIndex = buffer.length();
		this.bufferBeginIndex = 0;
	}

	@Override
	public int getBeginIndex() {
		return beginIndex;
	}

	@Override
	public int getEndIndex() {
		return endIndex;
	}

	@Override
	public int getBufferBeginIndex() {
		return bufferBeginIndex;
	}

	@Override
	public int getBufferEndIndex() {
		return buffer.length();
	}

	@Override
	public void setBeginIndex(int beginIndex) {
		if (beginIndex >= 0 && beginIndex <= getBufferEndIndex()) {
			this.beginIndex = beginIndex;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setEndIndex(int endIndex) {
		if (endIndex <= buffer.length() && endIndex >= 0) {
			this.endIndex = endIndex;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public StringBuilder getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuilder buffer) {
		this.buffer = buffer;
	}

}
