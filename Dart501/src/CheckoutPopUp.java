
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckoutPopUp {
	static int minimumDartsCheckout = 0;
	static int dartsUsed = 0;
	static int maximumDartsAtDouble = 0;
	static List<Integer> threeDartCheckoutScores = Arrays.asList(99, 102, 103, 105, 106, 108, 109);
	static List<Integer> twoDartCheckoutScores = Arrays.asList(3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31,
			33, 35, 37, 39, 41, 42, 43, 44, 45, 46, 47, 48, 49);

	public static void start(Player player) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Checkout Percentage");
		window.setMinWidth(250);
		if ((player.scoresRemaining.get(player.scoresRemaining.size() - 2) % 2) != 0)
			minimumDartsCheckout = 1;
		if (threeDartCheckoutScores.contains(player.scoresRemaining.get(player.scoresRemaining.size() - 2))
				|| player.scoresRemaining.get(player.scoresRemaining.size() - 2) > 110)
			maximumDartsAtDouble = 1;
		else if ((player.scoresRemaining.get(player.scoresRemaining.size() - 2) > 50)
				|| twoDartCheckoutScores.contains(player.scoresRemaining.get(player.scoresRemaining.size() - 2)))
			maximumDartsAtDouble = 2;
		else
			maximumDartsAtDouble = 3;
		System.out.println(maximumDartsAtDouble);

		Label label = new Label();
		label.setText("How Many darts were used on double");
		RadioButton zero = new RadioButton("0");
		RadioButton one = new RadioButton("1");
		RadioButton two = new RadioButton("2");
		RadioButton three = new RadioButton("3");
		ToggleGroup numOfDartsUsed = new ToggleGroup();
		zero.setToggleGroup(numOfDartsUsed);
		one.setToggleGroup(numOfDartsUsed);
		two.setToggleGroup(numOfDartsUsed);
		three.setToggleGroup(numOfDartsUsed);
		Button closeButton = new Button("Enter");
		closeButton.setOnAction(e -> {
			if(numOfDartsUsed.getSelectedToggle() != null) {
				if (one.isSelected()) {
					player.setDartsAtDouble(player.getDartsAtDouble() + 1);
					minimumDartsCheckout += 1;
				} else if (two.isSelected()) {
					player.setDartsAtDouble(player.getDartsAtDouble() + 2);
					minimumDartsCheckout += 2;
				} else if (three.isSelected()) {
					player.setDartsAtDouble(player.getDartsAtDouble() + 3);
					minimumDartsCheckout = 3;
				}
				if (player.getCurrentScore() == 0)
					dartsAtCheckout(player);
				else {
					dartsUsed = 3;
					player.setDartsUsed(player.getDartsUsed() + dartsUsed);
					player.setDartsUsedInLeg(player.getDartsUsedInLeg() + dartsUsed);
				}
				window.close();
			}
			});

		VBox layout = new VBox(10);
		if (maximumDartsAtDouble == 1)
			layout.getChildren().addAll(label, zero, one, closeButton);
		else if(maximumDartsAtDouble == 2) 
			layout.getChildren().addAll(label,zero,one,two, closeButton);
		else
			layout.getChildren().addAll(label, zero, one, two, three, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		// Used alongside initmodality above
		window.showAndWait();

	}

	private static void dartsAtCheckout(Player player) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Checkout Percentage");
		window.setMinWidth(250);
		VBox layout = new VBox(10);
		ToggleGroup numOfDartsUsed = new ToggleGroup();

		Label label = new Label();
		label.setText("How Many darts were used on checkout");
		RadioButton one = new RadioButton("1");
		RadioButton two = new RadioButton("2");
		RadioButton three = new RadioButton("3");
		one.setToggleGroup(numOfDartsUsed);
		two.setToggleGroup(numOfDartsUsed);
		three.setToggleGroup(numOfDartsUsed);
		layout.getChildren().add(label);
		if (minimumDartsCheckout == 2)
			layout.getChildren().addAll(two, three);
		else if (minimumDartsCheckout == 3)
			layout.getChildren().add(three);
		else
			layout.getChildren().addAll(one, two, three);
		Button closeButton = new Button("Enter");
		closeButton.setOnAction(e -> {
			if(numOfDartsUsed.getSelectedToggle() != null) {
				if (one.isSelected())
					dartsUsed = 1;
				else if (two.isSelected())
					dartsUsed = 2;
				else
					dartsUsed = 3;
				player.setDartsUsed(player.getDartsUsed() + dartsUsed);
				player.setDartsUsedInLeg(player.getDartsUsedInLeg() + dartsUsed);
				window.close();
			}
		});
		layout.getChildren().add(closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		// Used alongside initmodality above
		window.showAndWait();
	}

}
