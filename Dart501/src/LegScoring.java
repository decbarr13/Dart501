


import javafx.scene.control.Label;

public class LegScoring {

	public static void checkingGameWin(Player playerOne, Label scorePlayer1, Label legScorePlayer1, Player playerTwo,
			Label scorePlayer2, Label legScorePlayer2, GameStoringInfo setup, Label bestLegPlayer1,
			Label bestLegPlayer2) {
			if (playerOne.getNumOfLegs() < setup.endingGameScore) {
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
				playerTwo.setCheckoutPercentage(
						100 * (double) playerTwo.getTotalCheckouts() / ((double) playerTwo.getDartsAtDouble()));
				playerOne.setCheckoutPercentage(
						100 * (double) playerOne.getTotalCheckouts() / ((double) playerOne.getDartsAtDouble()));
				endGameScreen.start(playerOne, playerTwo, playerOne.getName());
			}
			if (playerTwo.getNumOfLegs() < setup.endingGameScore) {
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
				playerTwo.setCheckoutPercentage(
						100 * (double) playerTwo.getTotalCheckouts() / ((double) playerTwo.getDartsAtDouble()));
				playerOne.setCheckoutPercentage(
						100 * (double) playerOne.getTotalCheckouts() / ((double) playerOne.getDartsAtDouble()));
				endGameScreen.start(playerOne, playerTwo, playerTwo.getName());
			}
		}

	
}
