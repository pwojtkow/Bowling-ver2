package com.capgemini.bowlingver2;

import java.util.ArrayList;
import java.util.List;

public class Game implements BowlingGameResultCalculator {

	private static final int LAST_FRAME = 9;
	private static final int NUMBER_OF_FRAMES = 10;
	private List<Frame> frames;
	private int actualScore;
	private int mainCurrentFrame;

	public Game() {
		setFrames(new ArrayList<Frame>());
		for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
			getFrames().add(new Frame(i));
		}
	}

	public void roll(int numberOfPins) throws NoMoreFramesException {
		if (mainCurrentFrame == LAST_FRAME
				&& frames.get(mainCurrentFrame).isFinished() == true) {
			throw new NoMoreFramesException();
		}
		checkIsNextFrame();
		addPoints(numberOfPins);
		addBonusPoints();
	}

	private void addPoints(int numberOfPins) {
		frames.get(mainCurrentFrame).addPoints(numberOfPins);
	}

	private void addBonusPoints() {
		Frame previousFrame = frames.get(mainCurrentFrame - 1);
		Frame actualFrame = frames.get(mainCurrentFrame);
		if (previousFrame.getBonus() == Bonus.STRIKE
				&& actualFrame.isFinished() == true) {
			actualScore += actualFrame.getScore();
		} else if ((previousFrame.getBonus() == Bonus.SPARE)
				&& (actualFrame.getRollsQuantity() == 2)) {
			actualScore += actualFrame.getNumberOfPointsInFirsRoll();
		}
	}

	private void checkIsNextFrame() {
		if (frames.get(mainCurrentFrame).isFinished()) {
			mainCurrentFrame++;
		}
	}

	public int score() {
		for (int i = 0; i < frames.size(); i++) {
			actualScore += frames.get(i).getScore();
		}
		return actualScore;
	}

	public boolean isFinished() {
		if (mainCurrentFrame == LAST_FRAME
				&& frames.get(mainCurrentFrame).isFinished()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	public int getCurrentFrame() {
		return mainCurrentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.mainCurrentFrame = currentFrame;
	}
}
