package fr.istic.aco.editorLI.app.receiver;

public class SelectionImpl implements Selection {

	private int beginIndex;
	private int endIndex;
	public static final int BUFFER_BEGIN_INDEX = 0;

	private StringBuffer my_buffer;

	/*
	 * TODO add constructor method parse buffer in constructor(add parameter buffer)
	 * init beginIndex and endIndex
	 */

	/**
	 * Fournit l'indice du premier caract�re d�sign� par la s�lection.
	 * 
	 * @return
	 */
	@Override
	public int getBeginIndex() {
		// TODO Auto-generated method stub
		return beginIndex;
	}

	/**
	 * Fournit l'index du premier caract�re apr�s le dernier caract�re d�sign� par
	 * la s�lection.
	 * 
	 * @return l'indice de fin
	 */
	@Override
	public int getEndIndex() {
		// TODO Auto-generated method stub
		return endIndex;
	}

	/**
	 * Fournit l'index du premier caract�re dans le buffer
	 *
	 * Retourne l'indice de d�but du buffer.
	 */
	@Override
	public int getBufferBeginIndex() {
		// TODO Auto-generated method stub
		return BUFFER_BEGIN_INDEX;
	}

	/**
	 * Fournit l'index du premier caract�re "virtuel" apr�s la fin du buffer.
	 *
	 * @return the post end buffer index
	 */
	@Override
	public int getBufferEndIndex() {
		// TODO Auto-generated method stub
		return my_buffer.toString().length()-1;
	}

	/**
	 * Change la valeur de l'indice de d�but de la s�lection
	 *
	 * @param beginIndex, doit �tre dans la plage d'index du buffer
	 * @throws IndexOutOfBoundsException si beginIndex est hors limites.
	 */
	@Override
	public void setBeginIndex(int beginIndex) {
		// TODO Auto-generated method stub
		if (beginIndex >= getBufferBeginIndex() & beginIndex <= getBufferEndIndex()) {
			try {
				this.beginIndex = beginIndex;
			} catch (IndexOutOfBoundsException e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * Change la valeur de l'index de fin de la s�lection
	 *
	 * @param endIndex, doit �tre dans la plage d'index du tampon
	 * @throws IndexOutOfBoundsException si le beginIndex est hors limites
	 */
	@Override
	public void setEndIndex(int endIndex) {
		// TODO Auto-generated method stub
		if (endIndex >= getBufferBeginIndex() & endIndex <= getBufferEndIndex()) {
			try {
				this.endIndex = endIndex;
			} catch (IndexOutOfBoundsException e) {
				// TODO: handle exception
			}
		}
	}

}
