package fr.istic.aco.editorLI.controller;

public interface Controller {
	
	//////////////////////////write//////////////////////////////////////////
	/**
	 * @param s the string to insert
	 * replace the selection by the new string
	 */
	void insert(String s);
	
	/**
	 * @param start the index where the selection start
	 * @param end the index where the selection ends
	 * make a new selection
	 */
	void select(int start, int end);
	
	/**
	 * @param start the index where the selection start
	 * @param end the index where the selection ends
	 * add to the clipboard the selection
	 */
	void copy(int start, int end);
	
	/**
	 * @param start the index where the selection start
	 * @param end the index where the selection ends
	 * remove the selection from the text and add it to the clipboard
	 */
	void cut(int start, int end);
	
	/**
	 * paste the clipboard content to the selection
	 */
	void paste();
	
	//////////////////////////////read/////////////////////////////////
	/**
	 * @return the current text
	 */
	String getText();
		
	
	
	
}
