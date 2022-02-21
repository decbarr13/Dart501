
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class startingScreen extends Application {
	// Class Variables
	final double MAX_FONT_SIZE = 30.0;
	private int computerAverage;
	TextField player1Name;
	TextField player2Name;
	
	// Launching Application in main
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Creating Window
		Stage window = primaryStage;
		window.setTitle("Dart501");
		
		// Top Region
		Label welcomeLabel = new Label("Welcome to Dart 501");
		welcomeLabel.setFont(new Font("Arial", MAX_FONT_SIZE));
		HBox title = new HBox();
		title.getChildren().add(welcomeLabel);
		title.setAlignment(Pos.CENTER);

		// Left Region
			// Layout
		GridPane leftGrid = new GridPane();
		leftGrid.setPadding(new Insets(10, 10, 10, 10));
		leftGrid.setVgap(8);
		leftGrid.setHgap(10);
		leftGrid.setMinWidth(200);
			// Title
		Label opponentTitle = new Label("Opponent");
		opponentTitle.setFont(new Font("Arial", 20));
		opponentTitle.setAlignment(Pos.CENTER);
		leftGrid.add(opponentTitle, 0, 0);
			// Computer or Player Select
		ToggleGroup playerToggle = new ToggleGroup();
		RadioButton computerBox = new RadioButton("Computer");
		computerBox.setSelected(true);
		computerBox.setToggleGroup(playerToggle);
		leftGrid.add(computerBox, 0, 1);
		RadioButton playerBox = new RadioButton("Player");
		playerBox.setSelected(false);
		playerBox.setToggleGroup(playerToggle);
		leftGrid.add(playerBox, 1, 1);
		

		// Centre Region
			// Layout
		VBox centreLayout = new VBox(10);
		centreLayout.setMinWidth(200);
			// Changing GUI depending on opponent
		computerBox.setOnAction(e -> changeOpponentToComputer(centreLayout));
		playerBox.setOnAction(e -> changeOpponentToPlayer(centreLayout));
			// Original setup against computer
		Label playerVsComputer = new Label("Player vs Computer");
		player1Name = new TextField();
		player1Name.setPromptText("Player Name");
		Label computerLevel = new Label("Computer Average");
		computerLevel.setFont(new Font("Arial", 20));
		Label computerAverageLabel = new Label();
		computerAverageLabel.setFont(new Font("Arial", 40));
		computerAverageLabel.setAlignment(Pos.CENTER);
			// Slider of computer average
		Slider slider = new Slider(0, 120, 5);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1);
		slider.setBlockIncrement(1);
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				computerAverage = (int) slider.getValue();
				computerAverageLabel.setText(Integer.toString(computerAverage));
			}
		});
		centreLayout.getChildren().addAll(playerVsComputer, player1Name, computerLevel, slider, computerAverageLabel);

		// Right Region
			// Layout
		GridPane rightGrid = new GridPane();
		rightGrid.setPadding(new Insets(10, 10, 10, 10));
		rightGrid.setVgap(8);
		rightGrid.setHgap(10);
		rightGrid.setMinWidth(200);
			// Title
		Label gameTypeTitle = new Label("Game Type");
		gameTypeTitle.setFont(new Font("Arial", 20));
		rightGrid.add(gameTypeTitle, 0, 0);
			// Sets or Legs Select
		ToggleGroup setsOrLegsToggle = new ToggleGroup();
		RadioButton setsBox = new RadioButton("Sets");
		setsBox.setSelected(true);
		setsBox.setToggleGroup(setsOrLegsToggle);
		rightGrid.add(setsBox, 0, 1);
		RadioButton legsBox = new RadioButton("Legs");
		legsBox.setSelected(false);
		legsBox.setToggleGroup(setsOrLegsToggle);
		rightGrid.add(legsBox, 1, 1);
			// First to or Best of Select
		ToggleGroup firstToOrBestOfToggle = new ToggleGroup();
		RadioButton firstToBox = new RadioButton("First to");
		firstToBox.setSelected(true);
		firstToBox.setToggleGroup(firstToOrBestOfToggle);
		rightGrid.add(firstToBox, 0, 2);
		RadioButton bestOfBox = new RadioButton("Best of");
		bestOfBox.setSelected(false);
		bestOfBox.setToggleGroup(firstToOrBestOfToggle);
		rightGrid.add(bestOfBox, 1, 2);
		TextField numberOfGames = new TextField();
		numberOfGames.setPromptText("Enter the number of Legs/Sets you would like to play");
		rightGrid.add(numberOfGames, 0, 3);
		
		// Bottom Region
		
		HBox bottomLayout = new HBox();
		Button startGame = new Button("Start Game");
		startGame.setMinSize(300, 200);
		startGame.setOnAction(e-> startingGame(computerBox, setsBox, bestOfBox, numberOfGames, computerAverageLabel));
		bottomLayout.getChildren().addAll(startGame);
		bottomLayout.minHeight(300);
		bottomLayout.setAlignment(Pos.CENTER);

		
		// BorderPane Layout
		BorderPane layout = new BorderPane();
		layout.setTop(title);
		layout.setLeft(leftGrid);
		layout.setCenter(centreLayout);
		layout.setRight(rightGrid);
		layout.setBottom(bottomLayout);

		Scene scene = new Scene(layout, 700, 400);
		
		window.setScene(scene);
		window.show();

	}
	// Initialising start up game variables
	private void startingGame(RadioButton computerBox, RadioButton setsBox, RadioButton bestOfBox, TextField numberOfGames, Label computerAverageLabel) {
		GameStoringInfo setup = new GameStoringInfo();
		if(bestOfBox.isSelected()) {
			setup.bestOf = true;
			setup.firstTo = false;
		}
		if(setsBox.isSelected()) {
			setup.sets = true;
			setup.legs = false;
			setup.numberOfGames = Integer.parseInt(numberOfGames.getText());
			setup.endingGameScoring();
		}else {
			setup.legs = true;
			setup.sets = false;
			setup.numberOfGames = Integer.parseInt(numberOfGames.getText());
			setup.endingGameScoring();
		}
		if (computerBox.isSelected()) {
			setup.playerOneName = player1Name.getText();
			setup.playerTwoName = "DartBot";
			setup.computerAverage = computerAverage;;
		}
		else {
			setup.computerPlaying = false;
			setup.playerOneName = player1Name.getText();
			setup.playerTwoName = player2Name.getText();
		}
		FirstToBullPopUP.start(setup);
		GameScreen.start(setup);

	}
	// Method to change the centre layer contents based on game selected
	private TextField changeOpponentToPlayer(VBox centreLayout) {
		centreLayout.getChildren().clear();
		Label playerVsPlayer = new Label("Player vs Player");
		playerVsPlayer.setFont(new Font("Arial", 20));
		player1Name = new TextField();
		player1Name.setPromptText("Player 1 Name");
		player2Name = new TextField();
		player2Name.setPromptText("Player 2 Name");
		centreLayout.getChildren().addAll(playerVsPlayer, player1Name, player2Name);
		
		return player2Name;
	}

	// Method to change the centre layer contents based on game selected
	private void changeOpponentToComputer(VBox centreLayout) {
		centreLayout.getChildren().clear();
		Label playerVsComputer = new Label("Player vs Computer");
		player1Name = new TextField();
		player1Name.setPromptText("Player Name");
		Label computerLevel = new Label("Computer Average");
		computerLevel.setFont(new Font("Arial", 20));
		Label computerAverageLabel = new Label();
		computerAverageLabel.setFont(new Font("Arial", 40));
		// Slider of computer average
		Slider slider = new Slider(0, 100, 0);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1);
		slider.setBlockIncrement(1);
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				computerAverage = (int) slider.getValue();
				 computerAverageLabel.setText(Integer.toString(computerAverage));
			}
		});
		centreLayout.getChildren().addAll(playerVsComputer, player1Name, computerLevel, slider, computerAverageLabel);

	}
}
