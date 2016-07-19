package com.capgemini.bowlingver2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private static final int NUMBER_OF_FRAMES = 10;
	private Game game;

	@Before
	public void createObjects() {
		game = new Game();
	}

	@Test
	public void shouldCreateFrames() {
		// when
		Game game = new Game();
		// then
		Assert.assertEquals(NUMBER_OF_FRAMES, game.getFrames().size());
	}

	@Test
	public void shouldAddPointsAfterSingleRoll() {
		// given
		int numberOfPins = 6;
		int startingFrame = 1;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPins);
		int score = game.getFrames().get(startingFrame).getScore();
		// then
		Assert.assertEquals(numberOfPins, score);
	}

	@Test
	public void shouldAddPointsAfterDoubleRolls() {
		// given
		int numberOfPinsFirstRoll = 5;
		int numberOfPinsSecondRoll = 2;
		int startingFrame = 2;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPinsFirstRoll);
		game.roll(numberOfPinsSecondRoll);
		int score = game.getFrames().get(startingFrame).getScore();
		// then
		Assert.assertEquals(numberOfPinsFirstRoll + numberOfPinsSecondRoll, score);
	}

	@Test
	public void shoudUpdateActualScoreNormalCase() {
		// given
		int startingFrame = 2;
		int numberOfPinsFirstRoll = 4;
		int numberOfPinsSecondRoll = 3;
		int numberOfPinsThirdRoll = 5;
		int sumOfPoints = numberOfPinsFirstRoll + numberOfPinsSecondRoll + numberOfPinsThirdRoll;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPinsFirstRoll);
		game.roll(numberOfPinsSecondRoll);
		game.roll(numberOfPinsThirdRoll);
		int score = game.score();
		// then
		Assert.assertEquals(sumOfPoints, score);
	}

	@Test
	public void shouldAddBonusPointsAfterStrike() {
		// given
		int startingFrame = 2;
		int firstRoll = 10;
		int secondroll = 4;
		int thirdRoll = 3;
		int expectedResult = 24;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(firstRoll);
		game.roll(secondroll);
		game.roll(thirdRoll);
		int score = game.score();
		// then
		Assert.assertEquals(expectedResult, score);
	}

	@Test
	public void shouldEndTheGame() {
		// given
		int startingFrame = 9;
		int firstRoll = 4;
		int secondRoll = 3;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(firstRoll);
		game.roll(secondRoll);
		boolean isGameFinished = game.isFinished();
		// then
		Assert.assertTrue(isGameFinished);
	}

	@Test
	public void shouldMakeThirdRollInTenthFrameWithStrike() {
		// given
		int startingFrame = 8;
		int firstRoll = 5;
		int secondRoll = 3;
		int thirdRoll = 10;
		int fourthRoll = 10;
		int fifthRoll = 10;
		int expectedResult = 38;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(firstRoll);
		game.roll(secondRoll);
		game.roll(thirdRoll);
		game.roll(fourthRoll);
		game.roll(fifthRoll);
		int score = game.score();
		// then
		Assert.assertEquals(expectedResult, score);
	}

	@Test(expected = NoMoreFramesException.class)
	public void shouldNotAllowToRollAfterTenFrame() throws NoMoreFramesException {
		// given
		int firstRoll = 3;
		int secondRoll = 3;
		int thirdRoll = 3;
		int startingFrame = 9;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(firstRoll);
		game.roll(secondRoll);
		game.roll(thirdRoll);
	}

	@Test
	public void shouldMakeThirdRollInTenthFrameWithSpare() {
		// given
		int startingFrame = 8;
		int firstRoll = 2;
		int secondRoll = 1;
		int thirdRoll = 9;
		int fourthRoll = 1;
		int fifthRoll = 5;
		int expectedResult = 18;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(firstRoll);
		game.roll(secondRoll);
		game.roll(thirdRoll);
		game.roll(fourthRoll);
		game.roll(fifthRoll);
		int score = game.score();
		// then
		Assert.assertEquals(expectedResult, score);
	}

	@Test
	public void shoudUpdateActualScoreMixedCase() {
		// given
		int startingFrame = 1;
		int numberOfPins1Roll = 6;
		int numberOfPins2Roll = 2;
		int numberOfPins3Roll = 10;
		int numberOfPins4Roll = 5;
		int numberOfPins5Roll = 3;
		int numberOfPins6Roll = 4;
		int numberOfPins7Roll = 6;
		int numberOfPins8Roll = 3;
		int numberOfPins9Roll = 2;
		int expectedResult = 52;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPins1Roll);
		game.roll(numberOfPins2Roll);
		game.roll(numberOfPins3Roll);
		game.roll(numberOfPins4Roll);
		game.roll(numberOfPins5Roll);
		game.roll(numberOfPins6Roll);
		game.roll(numberOfPins7Roll);
		game.roll(numberOfPins8Roll);
		game.roll(numberOfPins9Roll);
		int score = game.score();
		// then
		Assert.assertEquals(expectedResult, score);
	}

	@Test
	public void shouldSetSpare() {
		// given
		int numberOfPinsFirstRoll = 9;
		int numberOfPinsSecondRoll = 1;
		int startingFrame = 6;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPinsFirstRoll);
		game.roll(numberOfPinsSecondRoll);
		boolean isSpare = game.getFrames().get(startingFrame).isSpare();
		// then
		Assert.assertTrue(isSpare);
	}

	@Test
	public void shouldSetStrike() {
		// given
		int numberOfPins = 10;
		int startingFrame = 3;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPins);
		boolean isStrike = game.getFrames().get(startingFrame).isStrike();
		// then
		Assert.assertTrue(isStrike);
	}

	@Test
	public void shoudGoToNextFrameWithoutBonus() {
		// give
		int startingFrame = 2;
		int numberOfPinsFirstRoll = 1;
		int numberOfPinsSecondRoll = 5;
		int numberOfPinsThirdRoll = 4;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPinsFirstRoll);
		game.roll(numberOfPinsSecondRoll);
		game.roll(numberOfPinsThirdRoll);
		// then
		Assert.assertEquals(startingFrame + 1, game.getCurrentFrame());
	}

	@Test
	public void shoudGoToNextFrameAfterStrike() {
		// give
		int startingFrame = 1;
		int pointsInPreviousFrame = 10;
		int numberOfPinsFirstRoll = pointsInPreviousFrame;
		int pointsInCurrentFrame = 5;
		int numberOfPinsSecondRoll = pointsInCurrentFrame;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPinsFirstRoll);
		game.roll(numberOfPinsSecondRoll);
		// then
		Assert.assertEquals(startingFrame + 1, game.getCurrentFrame());
		Assert.assertEquals(pointsInCurrentFrame, game.getFrames().get(game.getCurrentFrame()).getScore());
		Assert.assertEquals(pointsInPreviousFrame, game.getFrames().get(game.getCurrentFrame() - 1).getScore());
	}

	// TODO fill below
	@Test
	public void shoudGoToNextFrameAfterSpare() {
		// give
		int startingFrame = 1;
		int numberOfPinsFirstRoll = 4;
		int numberOfPinsSecondRoll = 5;
		int numberOfPinsThirdRoll = 2;
		// when
		game.setCurrentFrame(startingFrame);
		game.roll(numberOfPinsFirstRoll);
		game.roll(numberOfPinsSecondRoll);
		game.roll(numberOfPinsThirdRoll);
		// then
		Assert.assertEquals(startingFrame + 1, game.getCurrentFrame());
		Assert.assertEquals(2, game.getFrames().get(game.getCurrentFrame()).getScore());
	}
}
