package project6;
import javafx.scene.paint.Color;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MastermindFX extends Application{
	private class RoundButton extends Button{
		RoundButton(String color){
			 this.setStyle(
		                "-fx-background-radius: 5em; " +
		                "-fx-min-width: 30px; " +
		                "-fx-min-height: 30px; " +
		                "-fx-max-width:30px; " +
		                "-fx-max-height: 30px;" + "-fx-base: "+color+";"
		        );
			
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		BorderPane pane = new BorderPane();
		//GridPane colorPane = new GridPane();
		FlowPane colorKey = new FlowPane();
		colorKey.setPrefWrapLength(90);
		pane.setPrefSize(screenSize.getWidth()/2, screenSize.getHeight()/2);
		Scene s = new Scene(pane);
		
		
		  //All the different buttons the user can click//
	      RoundButton blueRoundButton = new RoundButton("blue");
	      RoundButton redRoundButton = new RoundButton("red");
	      RoundButton yellowRoundButton = new RoundButton("yellow");
	      RoundButton greenRoundButton = new RoundButton("green");
	      RoundButton orangeRoundButton = new RoundButton("orange");
	      RoundButton purpleRoundButton = new RoundButton("purple");
	      colorKey.getChildren().add(blueRoundButton);
	      colorKey.getChildren().add(redRoundButton);
	      colorKey.getChildren().add(yellowRoundButton);
	      colorKey.getChildren().add(greenRoundButton);
	      colorKey.getChildren().add(orangeRoundButton);
	      colorKey.getChildren().add(purpleRoundButton);
	      //--------------------------------------------//
		
		
		
		
		//Components of MasterMind Game//
		VBox rules = new VBox();
		Canvas gameBoard = new Canvas(Params.boardWidth*35, Params.boardHeight*50);
		GraphicsContext gc = gameBoard.getGraphicsContext2D();
		
		gc.setFill(Color.GRAY);
		for(int x=0; x<Params.boardWidth; x++){
			for(int y=0; y<Params.boardHeight; y++){
				gc.fillOval(x*35, y*50, 30, 30);
			}
		}
		
		pane.setLeft(rules);
		pane.setCenter(gameBoard);
		pane.setRight(colorKey);
		
		//colorPane.getChildren().add(colorKey);
		//colorPane.setAlignment(Pos.CENTER);
		//pane.setRight(colorPane);
		
	
		
		primaryStage.setScene(s);
		primaryStage.show();
		blueRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/bluecursor.jpg");	
		    	   s.setCursor(new ImageCursor(image));
		    	   }
		});
		redRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/redcursor.jpg");	
		    	   s.setCursor(new ImageCursor(image));
		    	   }
		});
		yellowRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/yellowcursor.jpg");	
		    	   s.setCursor(new ImageCursor(image));
		    	   }
		});
		greenRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/greencursor.jpg");	
		    	   s.setCursor(new ImageCursor(image));
		    	   }
		});
		orangeRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/orangecursor.jpg");	
		    	   s.setCursor(new ImageCursor(image));
		    	   }
		});
		purpleRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/purplecursor.jpg");	
		    	   s.setCursor(new ImageCursor(image));
		    	   }
		});
		
	}
	
}
