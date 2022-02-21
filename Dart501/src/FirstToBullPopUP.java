

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FirstToBullPopUP {

	static int dartsUsed = 0;
	public static void start(GameStoringInfo setup) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Closest to Bull");
		window.setMinWidth(250);

		Label label = new Label();
		label.setText("Who was closest to Bull?");
		RadioButton one = new RadioButton(setup.playerOneName);
		RadioButton two = new RadioButton(setup.playerTwoName);
		ToggleGroup closestToBull = new ToggleGroup();
		one.setToggleGroup(closestToBull);
		two.setToggleGroup(closestToBull);
		Button closeButton = new Button("Enter");
		closeButton.setOnAction(e -> {
			if (one.isSelected()) 
				setup.playerStart = 1;
			else if(two.isSelected())
				setup.playerStart = 2;
			window.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, one, two, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		// Used alongside initmodality above
		window.showAndWait();

	}

}