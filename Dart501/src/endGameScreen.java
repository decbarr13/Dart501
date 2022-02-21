

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class endGameScreen {

	@SuppressWarnings("unchecked")
	public static void start(Player playerOne, Player playerTwo, String winner) {
		Stage window = new Stage();
		//Table Columns
		TableView<Player> statsTable = new TableView<Player>();
		TableColumn<Player, String> playerColumn = new TableColumn<>("Player");
		playerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Player, String> setsColumn = new TableColumn<>("Sets");
		setsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfSets"));
		TableColumn<Player, String> legsColumn = new TableColumn<>("Legs");
		legsColumn.setCellValueFactory(new PropertyValueFactory<>("totalCheckouts"));
		TableColumn<Player, String> first9AverageColumn = new TableColumn<>("First 9 Average");
		first9AverageColumn.setCellValueFactory(new PropertyValueFactory<>("first9Average"));
		TableColumn<Player, String> averageColumn = new TableColumn<>("Average");
		averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
		TableColumn<Player, String> checkoutColumn = new TableColumn<>("Checkout Percentage");
		checkoutColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutPercentage"));
		TableColumn<Player,String> bestLegColumn = new TableColumn<>("Best Leg");
		bestLegColumn.setCellValueFactory(new PropertyValueFactory<>("bestLeg"));
		
		//Adding columns to table
		statsTable.getColumns().addAll(playerColumn,setsColumn, legsColumn, first9AverageColumn, averageColumn, checkoutColumn, bestLegColumn);
		
		
		
		// Adding players to table
		statsTable.getItems().add(playerOne);
		statsTable.getItems().add(playerTwo);
		
		if((playerOne.getNumOfSets()==0) && (playerTwo.getNumOfSets()==0))
		{
			statsTable.getColumns().remove(1);
		}
		
		// Table layer
		VBox vBox = new VBox(statsTable);
		
		// Title/Top Layer
		Label winnerLabel = new Label(winner + " wins (Game Stats)");
		winnerLabel.setFont(new Font("Arial", 30));
		HBox topLayer = new HBox();
		topLayer.getChildren().add(winnerLabel);
		topLayer.setAlignment(Pos.CENTER);
		
		//Combining layers
		BorderPane layout = new BorderPane();
		layout.setCenter(vBox);
		layout.setTop(topLayer);
		Scene scene = new Scene(layout, 400,400);
		
        window.setScene(scene);
        window.show();
    }
}
