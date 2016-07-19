package com.capgemini.bowlingver2;

public class Frame {

	private static final int LAST_FRAME = 9;
	private static final int MAX_NUMBER_OF_PINS = 10;
	private int frameNumber;
	private int numberOfPointsInFirstRoll;
	private int numberOfPointsInSecondRoll;
	private int numberOfPointsInThirdRoll;
	private int rollNumber;
	private boolean isFinished;
	private Bonus bonus;

	public int getNumberOfPointsInFirsRoll() {
		return numberOfPointsInFirstRoll;
	}

	public Bonus getBonus() {
		return bonus;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public Frame(int frameNumber) {
		this.frameNumber = frameNumber;
		rollNumber = 1;
	}

	public boolean isStrike() {
		return numberOfPointsInFirstRoll == MAX_NUMBER_OF_PINS;
	}

	public boolean isSpare() {
		return numberOfPointsInFirstRoll + numberOfPointsInSecondRoll == MAX_NUMBER_OF_PINS;
	}

	public int getScore() {
		return numberOfPointsInFirstRoll + numberOfPointsInSecondRoll + numberOfPointsInThirdRoll;
	}

	public void addPoints(int numberOfPins) {
		if (rollNumber == 1) {
			firstRollUpdate(numberOfPins);
		} else if (rollNumber == 2) {
			secondRollUpdate(numberOfPins);
		} else if (rollNumber == 3) {
			numberOfPointsInThirdRoll = numberOfPins;
			closeThisFrame();
		}
	}

	private void firstRollUpdate(int numberOfPins) {
		numberOfPointsInFirstRoll = numberOfPins;
		incrementRollNumber();
		checkBonus();
	}

	private void secondRollUpdate(int numberOfPins) {
		numberOfPointsInSecondRoll = numberOfPins;
		if (frameNumber != LAST_FRAME) {
			checkBonus();
			incrementRollNumber();
			closeThisFrame();
		} else if (frameNumber == LAST_FRAME) {
			checkBonus();
			if (bonus != Bonus.NONE) {
				incrementRollNumber();
			} else {
				closeThisFrame();
			}
		}
	}

	private void closeThisFrame() {
		isFinished = true;
	}

	private void checkBonus() {
		if (isStrike()) {
			bonus = Bonus.STRIKE;
			if (frameNumber != LAST_FRAME) 
				closeThisFrame();
		} else if (isSpare()) {
			bonus = Bonus.SPARE;
		} else
			bonus = Bonus.NONE;
	}

	public void incrementRollNumber() {
		rollNumber++;
	}

	public int getRollsQuantity() {
		return rollNumber;
	}

	public void setRollsQuantity(int rollsQuantity) {
		this.rollNumber = rollsQuantity;
	}

}
