package project6;
import javafx.scene.paint.Color;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
	RoundButton[][] gameBoardButtons = new RoundButton[Params.boardWidth][Params.boardHeight];
	double BUTTON_PADDING = 30;
	private static String cursorColor = "gray";
	private static Scene s;
	private int rowIndex = Params.boardHeight-1;
	
	//New Type of Button that has a color component to it and is also round//
	private class RoundButton extends Button{
		String color;
		RoundButton(String color){
			 this.setStyle(
		                "-fx-background-radius: 5em; " +
		                "-fx-min-width: 30px; " +
		                "-fx-min-height: 30px; " +
		                "-fx-max-width:30px; " +
		                "-fx-max-height: 30px;" + "-fx-base: "+color+";"
		        );
			this.color = color;
		}
		
		private void changeColor(String color){
			this.setStyle(
	                "-fx-background-radius: 5em; " +
	                "-fx-min-width: 30px; " +
	                "-fx-min-height: 30px; " +
	                "-fx-max-width:30px; " +
	                "-fx-max-height: 30px;" + "-fx-base: "+color+";"
	        );
			this.color = color;
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		BorderPane pane = new BorderPane();
		
		
		//GameBoard initialization//
		GridPane gameBoard = new GridPane();
		gameBoard.setVgap(BUTTON_PADDING);
		gameBoard.setVgap(BUTTON_PADDING);
		gameBoard.setAlignment(Pos.CENTER);
		
		
		GridPane rightBoard = new GridPane();
		rightBoard.setVgap(BUTTON_PADDING);
		rightBoard.setAlignment(Pos.CENTER);
		
		s = new Scene(pane);
		
		//Creating the gameboard of buttons//
		for(int x=0; x<gameBoardButtons.length; x++){
			for(int y=0; y<gameBoardButtons[x].length; y++){
				RoundButton button = new RoundButton("gray");
				gameBoardButtons[x][y] = button;
				gameBoard.add(button, x, y);
				registerGameBoardButton(button); //this adds the handler to each button
				if(y<gameBoardButtons[x].length-1){ //Only the last row will be available
					button.setDisable(true);
				}
			}
		}
		
		
		//Creating Check Button//
		Button check = new Button();
		check.setText("Check");
		check.setMinWidth(90);
		

		//Flowpane for the colors the user can pick//
		FlowPane colorKey = new FlowPane();
		colorKey.setPrefWrapLength(90);
		pane.setPrefSize(screenSize.getWidth()/2, screenSize.getHeight()/2);
		
		
		
		  //Making all the different buttons the user can click to input into board//
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
		
		
		//Adding to the right side of the screen//
		rightBoard.add(colorKey, 0, 0);
		rightBoard.add(check, 0, 1);
		
		
		
		
		//It's Showtime!//
		pane.setLeft(rules);
		pane.setCenter(gameBoard);
		pane.setRight(rightBoard);
		
		primaryStage.setScene(s);
		primaryStage.show();
		
		
		
		//Handlers for each available color//
		blueRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/blueCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "blue";
		    	   }
		});
		redRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/redCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "red";
		    	   }
		});
		yellowRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/yellowCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "yellow";
		    	   }
		});
		greenRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/greenCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "green";
		    	   }
		});
		orangeRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/orangeCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "orange";
		    	   }
		});
		purpleRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/purpleCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "purple";
		    	   }
		});
		
		
		
		
		
		//Check Button Handler, this is where the magic happens//
		check.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   
		    	   //Check to see if any of the buttons in the same row are gray//
		    	   for(int x=0; x<Params.boardWidth; x++){
		    		   if(gameBoardButtons[x][rowIndex].color.equals("gray")){
		    			   return;
		    		   }
		    	   	}
		    	   //---------------------------End of Check---------------------//
		    	   
		    	   
		    	   
		    	   //Disabling previous row//
		    	   for(int x=0; x<Params.boardWidth; x++){
		    		   gameBoardButtons[x][rowIndex].setDisable(true);
		    		   gameBoardButtons[x][rowIndex-1].setDisable(false);
		    	   	}
		    	   rowIndex -= 1;
		    	}
		});
		
	}
	
	
	//Event Handler for each button in the game board//
	private static void registerGameBoardButton(RoundButton button){
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				button.changeColor(cursorColor);
				cursorColor = "gray";
				s.setCursor(Cursor.DEFAULT);
			}
		});
		
	}
	
}
