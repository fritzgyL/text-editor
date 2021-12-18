package fr.istic.aco.editorLI.app.memento;

import fr.istic.aco.editorLI.app.utils.Text;

public class EngineState implements Memento {

	private final String content;
	private final int charetStartPosition, charetEndPosition;

	public EngineState(String content, int charetStartPosition, int charetEndPosition) {
		this.content = content;
		this.charetStartPosition = charetStartPosition;
		this.charetEndPosition = charetEndPosition;
	}

	@Override
	public Text getText() {
		// TODO Auto-generated method stub
		return new Text(content, new int[] { charetStartPosition, charetEndPosition });
	}


}
