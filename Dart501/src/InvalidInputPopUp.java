

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InvalidInputPopUp {

	public void createPopUp() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Error invalid input");
		window.setMinWidth(250);

		Label label = new Label();
		label.setText("You have inputted an invalid input please try again");

		Button closeButton = new Button("Close Window");
		closeButton.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		// Used alongside initmodality above
		window.showAndWait();
	}
}
