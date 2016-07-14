package com.capgemini.bowlingver2;

public class NoMoreFramesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoMoreFramesException() {
		super("The game is now finished");
	}
}
