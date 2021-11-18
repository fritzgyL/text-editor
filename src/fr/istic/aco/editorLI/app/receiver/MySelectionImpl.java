package fr.istic.aco.editorLI.app.receiver;

public class MySelectionImpl implements Selection {

	private StringBuilder buffer;
	private int beginIndex, endIndex;
	private int bufferBeginIndex;

	public MySelectionImpl(StringBuilder buffer) {
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
		return buffer.length()-1;
	}

	@Override
	public void setBeginIndex(int beginIndex) {
		if (beginIndex >= getBufferBeginIndex() && beginIndex <= getBufferEndIndex()+1) {
			this.beginIndex = beginIndex;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setEndIndex(int endIndex) {
		if (endIndex >= getBufferBeginIndex() && endIndex <= getBufferEndIndex()+1) {
			this.endIndex = endIndex;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

}
