
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Label;

public class Player {

	// Variables
	private String name = "Dec";
	private int currentScore = 501;
	private int numOfLegs = 0;
	private int numOfSets = 0;
	private double first9Average = 0;
	private double average = 0;
	private int dartsAtDouble = 0;
	private int dartsUsed = 0;
	double dartsUsedDouble = dartsUsed;
	private double checkoutPercentage = 0;
	private int bestLeg = 0;
	private int totalCheckouts = 0;
	private int dartsUsedInLeg = 0;
	boolean startingSet;
	boolean startingLeg;
	boolean currentlyThrowing;
	ArrayList<Integer> scoresEntered = new ArrayList<>();
	ArrayList<Integer> scoresRemaining = new ArrayList<>();
	ArrayList<Integer> firstThreeScoresEntered = new ArrayList<>();

	private int numberOfThrows = 0;

	public Player() {
		scoresRemaining.add(501);
		currentScore = 501;
		checkoutPercentage = 0;
		startingLeg = false;
		startingSet = false;
		currentlyThrowing = false;
	}

	// Methods
	public boolean enterScore(int scoreEntered, Label scorePlayer) {
		boolean validScore = true;
		validScore = GameScoring.checkingValidScore(scoreEntered, scorePlayer);
		if (validScore) {
			List<Integer> impossibleCheckouts = Arrays.asList(169, 168, 166, 165, 163, 162, 159);
			if ((currentScore > 170) || (impossibleCheckouts.contains(currentScore))) {
				if ((currentScore - scoreEntered) == 1) {
					scoreEntered = 0;
				}
				scoresEntered.add(scoreEntered);
				currentScore = currentScore - scoreEntered;
				scoresRemaining.add(currentScore);
				dartsUsed += 3;
				setDartsUsedInLeg(getDartsUsedInLeg() + 3);

			} else {
				scoresEntered.add(scoreEntered);
				currentScore = currentScore - scoreEntered;
				scoresRemaining.add(currentScore);
				GameScoring.checkoutPercentage(this);
				if (currentScore == 1) {
					scoresEntered.remove(scoresEntered.size() - 1);
					scoresRemaining.remove(scoresRemaining.size() - 1);
					scoresEntered.add(0);
					currentScore = scoresRemaining.get(scoresRemaining.size() - 1);

				}
			}
			if (scoresRemaining.size() <= 4) {
				firstThreeScoresEntered.add(scoreEntered);
			}
			scorePlayer.setText(String.valueOf(currentScore));
			return true;
		} else {
			InvalidInputPopUp popUp = new InvalidInputPopUp();
			popUp.createPopUp();
			return false;
		}
	}

	public void undoScore(Label scorePlayer) {
		scoresEntered.remove(scoresEntered.size() - 1);
		scoresRemaining.remove(scoresRemaining.size() - 1);
		dartsUsed -= 3;

		scorePlayer.setText(String.valueOf(scoresRemaining.get(scoresRemaining.size() - 1)));
	}

	public int enterScoreComputer(GameStoringInfo setup) {
		boolean noScore = true;
		int computerScore = 0;
		while (noScore) {
			List<Integer> impossibleScores = Arrays.asList(179, 178, 176, 175, 173, 172, 169, 166, 163);
			Random rand = new Random(); // instance of random class
			int upperbound = setup.computerAverage + 10;
			int lowerbound = setup.computerAverage - 10;
			computerScore = rand.nextInt((upperbound - lowerbound) + 1) + lowerbound;
			if (impossibleScores.contains(computerScore))
				break;
			else
				noScore = false;
		}
		return computerScore;
	}
	
	public void computerScoreCheck(int scoreEntered, Label scorePlayer) {
		currentScore = currentScore - scoreEntered;
		if(currentScore< 0) {
			scoreEntered = scoreEntered + currentScore;
		}
			scoresEntered.add(scoreEntered);
			scoresRemaining.add(currentScore);
			dartsUsed += 3;
			setDartsUsedInLeg(getDartsUsedInLeg() + 3);
			
			if(currentScore <= 0) {
				currentScore = 0;
		}
		if (scoresRemaining.size() <= 4) {
			firstThreeScoresEntered.add(scoreEntered);
		}
		scorePlayer.setText(String.valueOf(currentScore));
	} 

		
	

	// Getters and Setters
	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getNumOfLegs() {
		return numOfLegs;
	}

	public void setNumOfLegs(int numOfLegs) {
		this.numOfLegs = numOfLegs;
	}

	public int getNumOfSets() {
		return numOfSets;
	}

	public void setNumOfSets(int numOfSets) {
		this.numOfSets = numOfSets;
	}

	public double getFirst9Average() {
		return first9Average;
	}

	public void setFirst9Average(double first9Average) {
		this.first9Average = first9Average;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getCheckoutPercentage() {
		return checkoutPercentage;
	}

	public void setCheckoutPercentage(double checkoutPercentage) {
		this.checkoutPercentage = checkoutPercentage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBestLeg() {
		return bestLeg;
	}

	public void setBestLeg(int bestLeg) {
		this.bestLeg = bestLeg;
	}

	public int getNumberOfThrows() {
		return numberOfThrows;
	}

	public void setNumberOfThrows(int numberOfThrows) {
		this.numberOfThrows = numberOfThrows;
	}

	public int getDartsAtDouble() {
		return dartsAtDouble;
	}

	public void setDartsAtDouble(int dartsAtDouble) {
		this.dartsAtDouble = dartsAtDouble;
	}

	public int getDartsUsed() {
		return dartsUsed;
	}

	public void setDartsUsed(int dartsUsed) {
		this.dartsUsed = dartsUsed;
	}

	public int getTotalCheckouts() {
		return totalCheckouts;
	}

	public void setTotalCheckouts(int totalCheckouts) {
		this.totalCheckouts = totalCheckouts;
	}

	public int getDartsUsedInLeg() {
		return dartsUsedInLeg;
	}

	public void setDartsUsedInLeg(int dartsUsedInLeg) {
		this.dartsUsedInLeg = dartsUsedInLeg;
	}

}
