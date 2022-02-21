


import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Label;

public class GameScoring {

	public static void averageCalculator(Player player, Label averageLabelPlayer, Label first9AveragePlayer) {
		int sumOfScoresEntered = 0;
		for (Integer num : player.scoresEntered) {
			sumOfScoresEntered += num;
		}
		double sumOfScoresEnteredDouble = sumOfScoresEntered;
		double average = sumOfScoresEnteredDouble / (player.getDartsUsed() / 3);
		average = (Math.round(average*100.0)/100.0);
		averageLabelPlayer.setText(String.valueOf(average));
		player.setAverage(average);
		first9AverageCalculator(player, first9AveragePlayer);

	}

	public static void first9AverageCalculator(Player player, Label first9AveragePlayer) {
		int sumOfScoresEntered = 0;
		for (Integer num : player.firstThreeScoresEntered) {
			sumOfScoresEntered += num;
		}
		double sumOfScoresEnteredDouble = sumOfScoresEntered;
		double first9Average = sumOfScoresEnteredDouble / player.firstThreeScoresEntered.size();
		first9Average = (Math.round(first9Average*100.0)/100.0);
		first9AveragePlayer.setText(String.valueOf(first9Average));
		player.setFirst9Average(first9Average);

	}

	public static boolean checkingValidScore(int scoreEntered, Label scorePlayer) {
		boolean valid = true;
		List<Integer> impossibleScores = Arrays.asList(179, 178, 176, 175, 173, 172, 169, 166, 163);
		if (impossibleScores.contains(scoreEntered))
			valid = false;
		else if (Integer.parseInt(scorePlayer.getText()) - scoreEntered < 0)
			valid = false;
		else if (scoreEntered > 180)
			valid = false;

		return valid;
	}

	public static void checkingLegWin(Player playerOne, Label scorePlayer1, Label legScorePlayer1,Label setScorePlayer1,Player playerTwo,
			Label scorePlayer2, Label legScorePlayer2,Label setScorePlayer2, GameStoringInfo setup, Label bestLegPlayer1,
			Label bestLegPlayer2) {
		if (playerOne.getCurrentScore() <= 0) {
			playerOne.setTotalCheckouts(playerOne.getTotalCheckouts() + 1);
			playerOne.setNumOfLegs(playerOne.getNumOfLegs() + 1);
			if (playerOne.getBestLeg() == 0) {
				playerOne.setBestLeg(playerOne.getDartsUsedInLeg());
			} else if (playerOne.getBestLeg() > playerOne.getDartsUsedInLeg()) {
				playerOne.setBestLeg(playerOne.getDartsUsedInLeg());
			}
			playerOne.setDartsUsedInLeg(0);
			playerTwo.setDartsUsedInLeg(0);
			bestLegPlayer1.setText(String.valueOf(playerOne.getBestLeg()));
			playerOne.startingLeg ^= true;
			playerTwo.startingLeg ^= true;
			playerOne.currentlyThrowing = playerOne.startingLeg;
			playerTwo.currentlyThrowing = playerTwo.startingLeg;
			if(setup.sets) 
				SetScoring.checkingSetWin(playerOne, scorePlayer1, legScorePlayer1,  setScorePlayer1, playerTwo, scorePlayer2, legScorePlayer2,setScorePlayer2, setup);
			else
				LegScoring.checkingGameWin(playerOne, scorePlayer1, legScorePlayer1, playerTwo, scorePlayer2, legScorePlayer2, setup, bestLegPlayer1, bestLegPlayer2);

		} else if (playerTwo.getCurrentScore() <= 0) {
			playerTwo.setTotalCheckouts(playerTwo.getTotalCheckouts() + 1);
			playerTwo.setNumOfLegs(playerTwo.getNumOfLegs() + 1);
			if (playerTwo.getBestLeg() == 0) {
				playerTwo.setBestLeg(playerTwo.getDartsUsedInLeg());
			} else if (playerTwo.getBestLeg() > playerTwo.getDartsUsedInLeg()) {
				playerTwo.setBestLeg(playerTwo.getDartsUsedInLeg());
			}
			playerOne.setDartsUsedInLeg(0);
			playerTwo.setDartsUsedInLeg(0);
			bestLegPlayer2.setText(String.valueOf(playerTwo.getBestLeg()));
			playerOne.startingLeg ^= true;
			playerTwo.startingLeg ^= true;
			playerOne.currentlyThrowing = playerOne.startingLeg;
			playerTwo.currentlyThrowing = playerTwo.startingLeg;
			if(setup.sets) 
				SetScoring.checkingSetWin(playerOne, scorePlayer1, legScorePlayer1,  setScorePlayer1, playerTwo, scorePlayer2, legScorePlayer2,setScorePlayer2, setup);
			else
				LegScoring.checkingGameWin(playerOne, scorePlayer1, legScorePlayer1, playerTwo, scorePlayer2, legScorePlayer2, setup, bestLegPlayer1, bestLegPlayer2);
		}
	}

	public static void checkoutPercentage(Player player) {
		
		if (player.getCurrentScore() > 50) {
			player.setDartsUsed(player.getDartsUsed()+3);
			player.setDartsUsedInLeg(player.getDartsUsedInLeg() + 3);
		}
		else {
			CheckoutPopUp.start(player);
		}

	}
}


