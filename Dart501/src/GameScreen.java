import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameScreen {
	private static boolean validScore = true;
	static int computerScore = 0;

	public static void start(GameStoringInfo setup) {

		Stage window = new Stage();
		window.setTitle("Game On");

		Player playerOne = new Player();
		playerOne.setName(setup.playerOneName);
		Player playerTwo = new Player();
		playerTwo.setName(setup.playerTwoName);

		if (setup.playerStart == 1) {
			playerOne.startingSet = true;
			playerOne.startingLeg = true;
			playerOne.currentlyThrowing = true;
			playerTwo.startingSet = false;
			playerTwo.startingLeg = false;
			playerTwo.currentlyThrowing = false;
		} else {
			playerOne.startingSet = false;
			playerOne.startingLeg = false;
			playerOne.currentlyThrowing = false;
			playerTwo.startingSet = true;
			playerTwo.startingLeg = true;
			playerTwo.currentlyThrowing = true;
		}
		// Top Layer
		Label gameOn = new Label("Game on");
		gameOn.setFont(new Font("Arial", 30));
		HBox topLayer = new HBox();
		topLayer.setAlignment(Pos.CENTER);
		topLayer.getChildren().addAll(gameOn);

		// Left Layer
		VBox leftLayer = new VBox();
		Label player1Label = new Label(playerOne.getName());
		Label scorePlayer1 = new Label(String.valueOf(playerOne.getCurrentScore()));
		leftLayer.getChildren().addAll(player1Label, scorePlayer1);
		Label setScorePlayer1;
		if (setup.sets) {
			setScorePlayer1 = new Label(String.valueOf(playerOne.getNumOfSets()));
			leftLayer.getChildren().add(setScorePlayer1);
		} else {
			setScorePlayer1 = null;
		}
		Label legScorePlayer1 = new Label(String.valueOf(playerOne.getNumOfLegs()));
		Label first9AveragePlayer1 = new Label(String.valueOf(playerOne.getFirst9Average()));
		Label averageLabelPlayer1 = new Label(String.valueOf(playerOne.getAverage()));
		Label bestLegPlayer1 = new Label(String.valueOf(playerOne.getBestLeg()));
		leftLayer.getChildren().addAll(legScorePlayer1, first9AveragePlayer1, averageLabelPlayer1, bestLegPlayer1);

		// Centre Layer
		VBox centreLayer = new VBox();
		Label playerLabel = new Label("Player");
		Label scoreLabel = new Label("Score");
		centreLayer.getChildren().addAll(playerLabel, scoreLabel);
		if (setup.sets) {
			Label setLabel = new Label("Sets");
			centreLayer.getChildren().add(setLabel);
		}
		Label legLabel = new Label("Legs");
		Label first9AverageLabel = new Label("First 9 Average");
		Label averageLabel = new Label("Average");
		Label bestLegLabel = new Label("Best Leg");
		centreLayer.getChildren().addAll(legLabel, first9AverageLabel, averageLabel, bestLegLabel);
		centreLayer.setAlignment(Pos.TOP_CENTER);

		// Right Layer

		VBox rightLayer = new VBox();
		Label player2Label = new Label(playerTwo.getName());
		Label scorePlayer2 = new Label(String.valueOf(playerTwo.getCurrentScore()));
		rightLayer.getChildren().addAll(player2Label, scorePlayer2);
		Label setScorePlayer2;
		if (setup.sets) {
			setScorePlayer2 = new Label(String.valueOf(playerTwo.getNumOfSets()));
			rightLayer.getChildren().add(setScorePlayer2);
		} else {
			setScorePlayer2 = null;
		}
		Label legScorePlayer2 = new Label(String.valueOf(playerTwo.getNumOfLegs()));
		Label first9AveragePlayer2 = new Label(String.valueOf(playerTwo.getFirst9Average()));
		Label averageLabelPlayer2 = new Label(String.valueOf(playerTwo.getAverage()));
		Label bestLegPlayer2 = new Label(String.valueOf(playerTwo.getBestLeg()));
		rightLayer.getChildren().addAll(legScorePlayer2, first9AveragePlayer2, averageLabelPlayer2, bestLegPlayer2);

		// Bottom Layer
		HBox bottomLayer = new HBox();
		TextField scoreEntry = new TextField();
		Button enterScore = new Button("enter");
		Button undoScore = new Button("Undo");
		scoreEntry.setPromptText("Enter Score");

		undoScore.setOnAction(e -> {
			if (playerTwo.currentlyThrowing) {
				playerOne.undoScore(scorePlayer1);
				GameScoring.averageCalculator(playerOne, averageLabelPlayer1, first9AveragePlayer1);
				playerOne.currentlyThrowing ^= true;
				playerTwo.currentlyThrowing ^= true;
			} else {
				playerTwo.undoScore(scorePlayer2);
				GameScoring.averageCalculator(playerTwo, averageLabelPlayer2, first9AveragePlayer2);
				playerOne.currentlyThrowing ^= true;
				playerTwo.currentlyThrowing ^= true;
			}
		});
		enterScore.setOnAction(e -> {
			enterScoring(playerOne, scorePlayer1, averageLabelPlayer1, first9AveragePlayer1, legScorePlayer1,
					setScorePlayer1, bestLegPlayer1, playerTwo, scorePlayer2, averageLabelPlayer2, first9AveragePlayer2,
					legScorePlayer2, setScorePlayer2, bestLegPlayer2, scoreEntry, setup);

		});

		scoreEntry.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				enterScoring(playerOne, scorePlayer1, averageLabelPlayer1, first9AveragePlayer1, legScorePlayer1,
						setScorePlayer1, bestLegPlayer1, playerTwo, scorePlayer2, averageLabelPlayer2,
						first9AveragePlayer2, legScorePlayer2, setScorePlayer2, bestLegPlayer2, scoreEntry, setup);
			}
		});

		bottomLayer.getChildren().addAll(scoreEntry, enterScore, undoScore);
		bottomLayer.setAlignment(Pos.CENTER);

		// Combining Layers
		BorderPane layout = new BorderPane();
		layout.setTop(topLayer);
		layout.setLeft(leftLayer);
		layout.setCenter(centreLayer);
		layout.setRight(rightLayer);
		layout.setBottom(bottomLayer);
		Scene scene = new Scene(layout, 400, 400);
		window.setScene(scene);
		window.show();

	}

	private static void enterScoring(Player playerOne, Label scorePlayer1, Label averageLabelPlayer,
			Label first9AveragePlayer1, Label legScorePlayer1, Label setScorePlayer1, Label bestLegPlayer1,
			Player playerTwo, Label scorePlayer2, Label averageLabelPlayer2, Label first9AveragePlayer2,
			Label legScorePlayer2, Label setScorePlayer2, Label bestLegPlayer2, TextField scoreEntry,
			GameStoringInfo setup) {
		if (setup.computerPlaying) {
			if (playerOne.currentlyThrowing) {
				int scoreEntered = Integer.parseInt(scoreEntry.getText());
				validScore = playerOne.enterScore(scoreEntered, scorePlayer1);
				if (validScore) {
					GameScoring.averageCalculator(playerOne, averageLabelPlayer, first9AveragePlayer1);
					GameScoring.checkingLegWin(playerOne, scorePlayer1, legScorePlayer1, setScorePlayer1, playerTwo,
							scorePlayer2, legScorePlayer2, setScorePlayer2, setup, bestLegPlayer1, bestLegPlayer2);
					if(playerOne.getCurrentScore() != 501) {
						computerScore = playerTwo.enterScoreComputer(setup);
						playerTwo.computerScoreCheck(computerScore, scorePlayer2);
						GameScoring.averageCalculator(playerTwo, averageLabelPlayer2, first9AveragePlayer2);
						GameScoring.checkingLegWin(playerOne, scorePlayer1, legScorePlayer1, setScorePlayer1, playerTwo,
								scorePlayer2, legScorePlayer2, setScorePlayer2, setup, bestLegPlayer1, bestLegPlayer2);
					}
				}
			}
			else {
				computerScore = playerTwo.enterScoreComputer(setup);
				playerTwo.computerScoreCheck(computerScore, scorePlayer2);
				GameScoring.averageCalculator(playerTwo, averageLabelPlayer2, first9AveragePlayer2);
				GameScoring.checkingLegWin(playerOne, scorePlayer1, legScorePlayer1, setScorePlayer1, playerTwo,
								scorePlayer2, legScorePlayer2, setScorePlayer2, setup, bestLegPlayer1, bestLegPlayer2);
					if (playerTwo.getCurrentScore() != 501) {
						playerOne.currentlyThrowing ^= true;
						playerTwo.currentlyThrowing ^= true;
					}

				
			}
		} else
			{
				if (playerOne.currentlyThrowing) {
					int scoreEntered = Integer.parseInt(scoreEntry.getText());
					validScore = playerOne.enterScore(scoreEntered, scorePlayer1);
					if (validScore) {
						GameScoring.averageCalculator(playerOne, averageLabelPlayer, first9AveragePlayer1);
						GameScoring.checkingLegWin(playerOne, scorePlayer1, legScorePlayer1, setScorePlayer1, playerTwo,
								scorePlayer2, legScorePlayer2, setScorePlayer2, setup, bestLegPlayer1, bestLegPlayer2);
						if (playerOne.getCurrentScore() != 501) {
							playerOne.currentlyThrowing ^= true;
							playerTwo.currentlyThrowing ^= true;
						}
					}
				} else {
					int scoreEntered = Integer.parseInt(scoreEntry.getText());
					validScore = playerTwo.enterScore(scoreEntered, scorePlayer2);
					if (validScore) {
						GameScoring.averageCalculator(playerTwo, averageLabelPlayer2, first9AveragePlayer2);
						GameScoring.checkingLegWin(playerOne, scorePlayer1, legScorePlayer1, setScorePlayer1, playerTwo,
								scorePlayer2, legScorePlayer2, setScorePlayer2, setup, bestLegPlayer1, bestLegPlayer2);
						if (playerTwo.getCurrentScore() != 501) {
							playerOne.currentlyThrowing ^= true;
							playerTwo.currentlyThrowing ^= true;
						}
					}

				}
			}
			scoreEntry.clear();
		
	}
}