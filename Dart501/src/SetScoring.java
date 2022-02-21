


import javafx.scene.control.Label;

public class SetScoring {

	public static void checkingSetWin(Player playerOne, Label scorePlayer1, Label legScorePlayer1,
			Label setScorePlayer1, Player playerTwo, Label scorePlayer2, Label legScorePlayer2, Label setScorePlayer2,
			GameStoringInfo setup) {

		if (playerOne.getNumOfLegs() <= 2) {
			scorePlayer1.setText("501");
			scorePlayer2.setText("501");
			playerOne.setCurrentScore(501);
			playerTwo.setCurrentScore(501);
			playerOne.scoresRemaining.clear();
			playerTwo.scoresRemaining.clear();
			playerOne.scoresRemaining.add(501);
			playerTwo.scoresRemaining.add(501);
			legScorePlayer1.setText(String.valueOf(playerOne.getNumOfLegs()));
		} else {
			playerOne.setNumOfSets(playerOne.getNumOfSets() + 1);
			checkingGameWin(playerOne, scorePlayer1, legScorePlayer1,setScorePlayer1, playerTwo, scorePlayer2,
					legScorePlayer2, setScorePlayer2, setup);
		}

		if (playerTwo.getNumOfLegs() <= 2) {
			scorePlayer2.setText("501");
			scorePlayer1.setText("501");
			playerOne.setCurrentScore(501);
			playerTwo.setCurrentScore(501);
			playerOne.scoresRemaining.clear();
			playerTwo.scoresRemaining.clear();
			playerOne.scoresRemaining.add(501);
			playerTwo.scoresRemaining.add(501);
			legScorePlayer2.setText(String.valueOf(playerTwo.getNumOfLegs()));
		} else {
			playerTwo.setNumOfSets(playerTwo.getNumOfSets() + 1);
			checkingGameWin(playerOne, scorePlayer1, legScorePlayer1,setScorePlayer1, playerTwo, scorePlayer2, legScorePlayer2,
					setScorePlayer2, setup);
		}
	}

	private static void checkingGameWin(Player playerOne, Label scorePlayer1, Label legScorePlayer1,
			Label setScorePlayer1, Player playerTwo, Label scorePlayer2, Label legScorePlayer2, Label setScorePlayer2, GameStoringInfo setup) {
		if (playerTwo.getNumOfSets() == setup.endingGameScore) {
			String winner = playerTwo.getName();
			playerTwo.setCheckoutPercentage(
					100 * (double) playerTwo.getTotalCheckouts() / ((double) playerTwo.getDartsAtDouble()));
			playerOne.setCheckoutPercentage(
					100 * (double) playerOne.getTotalCheckouts() / ((double) playerOne.getDartsAtDouble()));
			endGameScreen.start(playerOne, playerTwo, winner);}
			
		 if (playerOne.getNumOfSets() == setup.endingGameScore) {
			String winner = playerOne.getName();
			playerOne.setCheckoutPercentage(
					100 * (double) playerOne.getTotalCheckouts() / ((double) playerOne.getDartsAtDouble()));
			playerTwo.setCheckoutPercentage(
					100 * (double) playerTwo.getTotalCheckouts() / ((double) playerTwo.getDartsAtDouble()));
			endGameScreen.start(playerOne, playerTwo, winner);
		}		 
		playerOne.setCurrentScore(501);
		playerTwo.setCurrentScore(501);
		playerOne.scoresRemaining.clear();
		playerOne.scoresRemaining.add(playerOne.getCurrentScore());
		playerTwo.scoresRemaining.clear();
		playerTwo.scoresRemaining.add(playerTwo.getCurrentScore());
		playerOne.setNumOfLegs(0);
		playerTwo.setNumOfLegs(0);
		playerOne.startingSet ^= true;
		playerTwo.startingSet ^= true;
		playerOne.startingLeg = playerOne.startingSet;
		playerTwo.startingLeg = playerTwo.startingSet;
		playerOne.currentlyThrowing = playerOne.startingLeg;
		playerTwo.currentlyThrowing = playerTwo.startingLeg;
		setScorePlayer2.setText(String.valueOf(playerTwo.getNumOfSets()));
		legScorePlayer2.setText(String.valueOf(playerTwo.getNumOfLegs()));
		legScorePlayer1.setText(String.valueOf(playerOne.getNumOfLegs()));
		setScorePlayer1.setText(String.valueOf(playerOne.getNumOfSets()));
		scorePlayer1.setText(String.valueOf(playerOne.getCurrentScore()));
		scorePlayer2.setText(String.valueOf(playerTwo.getCurrentScore()));
	}

}
