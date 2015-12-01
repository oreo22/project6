package project6;
import javafx.scene.paint.Color;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project6.Pegs.Peg;

public class MastermindFX extends Application{
	RoundButton[][] gameBoardButtons = new RoundButton[Params.boardWidth][Params.boardHeight];
	feedbackFlowPane[][] feedbackBoard = new feedbackFlowPane[1][Params.boardHeight];
	
	double BUTTON_PADDING = 30;
	private static String cursorColor = "gray";
	private static Scene s;
	private int rowIndex = Params.boardHeight-1;
	
	//New Type of Button that has a color component to it and is also round//
	private class RoundButton extends Button{
		Peg pegInput;
		int size;
		RoundButton(String color, int size){
			this.pegInput = PegCreator.pegConstructor(color);
			this.size = size;
			 this.setStyle(
		                "-fx-background-radius: 5em; " +
		                "-fx-min-width: "+size+"px; " +
		                "-fx-min-height: "+size+"px; " +
		                "-fx-max-width: "+size+"px; " +
		                "-fx-max-height: "+size+"px;" + "-fx-base: "+this.pegInput.pegName+";"
		        );
			 
		}
		
		private void redraw(){
			this.setStyle(
	                "-fx-background-radius: 5em; " +
	                "-fx-min-width: "+size+"px; " +
	                "-fx-min-height: "+size+"px; " +
	                "-fx-max-width:"+size+"px; " +
	                "-fx-max-height: "+size+"px;" + "-fx-base: "+this.pegInput.pegName+";"
	        );
		}
		
	}
	
	
	//Made a flowPane for the feedback tiles to make it easier to change//
	private class feedbackFlowPane extends FlowPane{
		RoundButton[] feedbackPegs = new RoundButton[Params.pegNumbertoGuess];
		feedbackFlowPane(){
		this.setPrefWrapLength(30);
		for(int x=0; x<Params.pegNumbertoGuess; x++){
			feedbackPegs[x] = new RoundButton("Gray",15);
			this.getChildren().add(feedbackPegs[x]);
		}
		}
		
		private void changeFeedback(ArrayList<Peg> consoleFeedback){
			int pos=0;
			for(int x=0; x<consoleFeedback.size(); x++){
				Peg color = consoleFeedback.get(x);
				if(color != null){
					feedbackPegs[pos] = new RoundButton(color.pegName, 15);
					this.getChildren().set(pos, feedbackPegs[pos]);
					pos++;
					
				}
			}
		}
		
		private boolean checkWin(){
			for(int x=0; x<Params.pegNumbertoGuess; x++){
				if(this.feedbackPegs[x].pegInput.pegName.equals("White")){
					return false;
				}
			}
			return true;
		}
	}
	
	
	//---------------------------End of New Classes-----------------------//
	
	
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
				RoundButton button = new RoundButton("Gray", 30);
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
		
		
		Text rules = new Text();
		

		//Creating FeedBackboard and inserting it into the center board
		for(int y=0; y<feedbackBoard[0].length; y++){
			feedbackBoard[0][y] = new feedbackFlowPane();
			gameBoard.add(feedbackBoard[0][y], Params.boardWidth, y);
		}
		
		
		  //Making all the different buttons the user can click to input into board//
	      RoundButton blueRoundButton = new RoundButton("Blue", 30);
	      RoundButton redRoundButton = new RoundButton("Red", 30);
	      RoundButton yellowRoundButton = new RoundButton("Yellow", 30);
	      RoundButton greenRoundButton = new RoundButton("Green", 30);
	      RoundButton orangeRoundButton = new RoundButton("Orange", 30);
	      RoundButton purpleRoundButton = new RoundButton("Purple", 30);
	      colorKey.getChildren().add(blueRoundButton);
	      colorKey.getChildren().add(redRoundButton);
	      colorKey.getChildren().add(yellowRoundButton);
	      colorKey.getChildren().add(greenRoundButton);
	      colorKey.getChildren().add(orangeRoundButton);
	      colorKey.getChildren().add(purpleRoundButton);
	      //--------------------------------------------//
		
		
		
		
		//Components of MasterMind Game//
		
		
		
		//Adding to the right side of the screen//
		rightBoard.add(colorKey, 0, 0);
		rightBoard.add(check, 0, 1);
		rightBoard.setPadding(new Insets(0,20,0,0));
		
		
		
		
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
		    	   cursorColor = "Blue";
		    	   }
		});
		redRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/redCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "Red";
		    	   }
		});
		yellowRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/yellowCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "Yellow";
		    	   }
		});
		greenRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/greenCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "Green";
		    	   }
		});
		orangeRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/orangeCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "Orange";
		    	   }
		});
		purpleRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/purpleCursor.png");	
		    	   s.setCursor(new ImageCursor(image));
		    	   cursorColor = "Purple";
		    	   }
		});
		
		
		
		
		
		//Check Button Handler, this is where the magic happens//
		check.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   ArrayList<Peg> userInput = new ArrayList<Peg>();
		    	   //Check to see if any of the buttons in the same row are gray//
		    	   for(int x=0; x<Params.boardWidth; x++){
		    		   if(gameBoardButtons[x][rowIndex].pegInput.pegName.equals("Gray")){
		    			   return;
		    		   }
		    		   userInput.add(gameBoardButtons[x][rowIndex].pegInput);
		    	   	}
		    	   //---------------------------End of Check---------------------//
		    	   
		    	   
		    	    feedbackBoard[0][rowIndex].changeFeedback(MasterMindConsole.inputCheck(userInput));
		    	    if(feedbackBoard[0][rowIndex].checkWin() == true){
		    	    	pane.setDisable(true);
		    	    	return;
		    	    }
		    	    
		    	   
		    	   //Check to see if the amount of guesses is over
		    	   if(rowIndex == 0){
		    		   System.out.println("You Lost");
		    	   }
		    	 //Disabling previous row//
		    	   else{
			    	   for(int x=0; x<Params.boardWidth; x++){
			    		   gameBoardButtons[x][rowIndex].setDisable(true);
			    		   gameBoardButtons[x][rowIndex-1].setDisable(false);
			    	   	}
			    	   rowIndex -= 1;
			    	}
		       }
		});
		
	}
	
	
	//Event Handler for each button in the game board//
	private static void registerGameBoardButton(RoundButton button){
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(cursorColor != "Gray"){
					button.pegInput = PegCreator.pegConstructor(cursorColor);
					button.redraw();
					cursorColor = "Gray";
					s.setCursor(Cursor.DEFAULT);
				}
			}
		});
		
	}
	
}
