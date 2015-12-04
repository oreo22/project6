package project6;

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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import project6.Pegs.Peg;

public class MastermindFX extends Application{
	private RoundButton[][] gameBoardButtons = new RoundButton[Params.boardWidth][Params.boardHeight];
	private feedbackFlowPane[][] feedbackBoard = new feedbackFlowPane[1][Params.boardHeight];
	private RoundButton[] humanFeedback = new RoundButton[Params.pegNumbertoGuess];
	
	double BUTTON_PADDING = 30;
	private static String cursorColor = "Gray";
	private static Scene s;
	private static Scene aiDecoderScene;
	private int rowIndex = Params.boardHeight-1;
	private static Text winOrLose = new Text("");
	
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
		                "-fx-max-height: "+size+"px;" + "-fx-base: "+this.pegInput.getPegName()+";"
		        );
			 
		}
		
		private void redraw(){
			this.setStyle(
	                "-fx-background-radius: 5em; " +
	                "-fx-min-width: "+size+"px; " +
	                "-fx-min-height: "+size+"px; " +
	                "-fx-max-width:"+size+"px; " +
	                "-fx-max-height: "+size+"px;" + "-fx-base: "+this.pegInput.getPegName()+";"
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
		
		feedbackFlowPane(int size){
		this.setPrefWrapLength(size*4);
		for(int x=0; x<Params.pegNumbertoGuess; x++){
			feedbackPegs[x] = new RoundButton("Gray", size);
			this.getChildren().add(feedbackPegs[x]);
		}
		}
		
		private void changeFeedback(ArrayList<Peg> consoleFeedback){
			int pos=0;
			for(int x=0; x<consoleFeedback.size(); x++){
				Peg color = consoleFeedback.get(x);
				if(color != null){
					feedbackPegs[pos] = new RoundButton(color.getPegName(), 15);
					this.getChildren().set(pos, feedbackPegs[pos]);
					pos++;
					
				}
			}
		}
		
		private boolean checkWin(){
			for(int x=0; x<Params.pegNumbertoGuess; x++){
				if(!this.feedbackPegs[x].pegInput.getPegName().equals("Black")){
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
		
		//Screen//
		BorderPane pane = new BorderPane();
		BorderPane startupPane = new BorderPane();
		
		GridPane gameCompletedPane = new GridPane();
		VBox startupGrid = new VBox();
		
		//Game Completed Screen//
		Button playAgainButton = new Button();
		
		Text answerText = new Text("The answer was:");
		answerText.setTextAlignment(TextAlignment.CENTER);
		answerText.setWrappingWidth(120);
		winOrLose.setTextAlignment(TextAlignment.CENTER);
		winOrLose.setWrappingWidth(120);
		HBox answerKey = new HBox();
		playAgainButton.setText("Play Again");
		playAgainButton.setMinWidth(120);
		gameCompletedPane.add(winOrLose, 0, 0);
		gameCompletedPane.add(answerText, 0, 1);
		gameCompletedPane.add(answerKey, 0, 2);
		gameCompletedPane.add(playAgainButton, 0, 3);
		gameCompletedPane.setAlignment(Pos.CENTER);
		gameCompletedPane.setVgap(BUTTON_PADDING);
		
		//-----------------End of Game Completed---------//
		
		//Startup Screen//
		Button aiDecoderMode = new Button();
		Button humanDecoderMode = new Button();
		Text welcome = new Text("Welcome to MasterMind!\nPlease Select an Option Below.");
		welcome.setFont(new Font("Arial", 30));
		welcome.setTextAlignment(TextAlignment.CENTER);
		welcome.setWrappingWidth(800);
		aiDecoderMode.setText("AI as Decoder");
		humanDecoderMode.setText("Human as Decoder");
		startupGrid.getChildren().add(welcome);
		startupGrid.getChildren().add(aiDecoderMode);
		startupGrid.getChildren().add(humanDecoderMode);
		startupGrid.setAlignment(Pos.CENTER);
		startupPane.setCenter(startupGrid);
		startupPane.setMinSize(screenSize.getWidth()/2, screenSize.getHeight()/2);
		//End of Startup Screen
		

		//GameBoard initialization//
		GridPane gameBoard = new GridPane();
		gameBoard.setVgap(BUTTON_PADDING);
		gameBoard.setAlignment(Pos.CENTER);
		
		//Setting up right side of the board//
		GridPane rightBoard = new GridPane();
		rightBoard.setVgap(BUTTON_PADDING);
		rightBoard.setAlignment(Pos.CENTER);
		
		
		
		//Setting up Scene//
		s = new Scene(pane);
		
		
		//Beginning Menu scene
		Scene startupScene = new Scene(startupPane);
		
		
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
		
		
		//Creating AIDecoder Mode Screen//
		
		BorderPane aiDecoderPane = new BorderPane();
		
		aiDecoderPane.setPrefSize(screenSize.getWidth()/2, screenSize.getHeight()/2);
		aiDecoderScene = new Scene(aiDecoderPane);
		
		
		
		
		
		
		
		
		//Creating Check Button//
		Button check = new Button();
		check.setText("Check");
		check.setMinWidth(90);
		
		//Creating Submit Button//
		Button submit = new Button();
		submit.setText("Submit Feedback");
		submit.setMinWidth(120);
		

		//Flowpane for the colors the user can pick//
		FlowPane colorKey = new FlowPane();
		colorKey.setPrefWrapLength(90);
		pane.setPrefSize(screenSize.getWidth()/2, screenSize.getHeight()/2);
		
		
		//Making all the different buttons the user can click to input into board//
/*		for(int x=0; x<MasterMindConsole.availableColors.size(); x++){
			RoundButton newButton = new RoundButton(MasterMindConsole.availableColors.get(x).pegName, 30);
			colorKey.getChildren().add(newButton);
		}*/
		
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
		

		//Creating FeedBackboard and inserting it into the center board
		for(int y=0; y<feedbackBoard[0].length; y++){
			feedbackBoard[0][y] = new feedbackFlowPane();
			
			gameBoard.add(feedbackBoard[0][y], Params.boardWidth, y);
		}
		

		//Creating FeedbackKey//
		HBox humanFeedbackKey = new HBox();
		for(int x=0; x< Params.pegNumbertoGuess; x++){
			humanFeedback[x] = new RoundButton("Gray", 30);
			registerFeedbackButton(humanFeedback[x]);
			humanFeedbackKey.getChildren().add(humanFeedback[x]);
		}
		HBox whiteBlackKey = new HBox();
		RoundButton whiteRoundButton = new RoundButton("White", 30);
	    RoundButton blackRoundButton = new RoundButton("Black", 30);
	    whiteBlackKey.getChildren().add(whiteRoundButton);
	    whiteBlackKey.getChildren().add(blackRoundButton);
		
		//It's Showtime!//
		
		
		primaryStage.setTitle("MasterMind");
		primaryStage.setScene(startupScene);
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
		whiteRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/whiteCursor.png");	
		    	   aiDecoderScene.setCursor(new ImageCursor(image));
		    	   cursorColor = "White";
		    	   }
		});
		blackRoundButton.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   Image image = new Image("/images/blackCursor.png");	
		    	   aiDecoderScene.setCursor(new ImageCursor(image));
		    	   cursorColor = "Black";
		    	   }
		});
		
		
		humanDecoderMode.setOnAction(new EventHandler<ActionEvent>(){
		       @Override
		       public void handle(ActionEvent e) {
		   		for(int x=0; x<MasterMindConsole.answerKey.size(); x++){
					answerKey.getChildren().add(new RoundButton(MasterMindConsole.answerKey.get(x).getPegName(), 30));
				}
				rightBoard.add(colorKey, 0, 0);
				rightBoard.add(check, 0, 1);
				rightBoard.setPadding(new Insets(0,20,0,0));
				 pane.setRight(rightBoard);
				 pane.setCenter(gameBoard);
		    	 primaryStage.setScene(s);
		    	   	}
		});
		
		aiDecoderMode.setOnAction(new EventHandler<ActionEvent>(){
		       @Override
		       public void handle(ActionEvent e) {
		    	   rightBoard.add(humanFeedbackKey, 0, 0);
		    	   rightBoard.add(whiteBlackKey, 0, 1);
		    	   rightBoard.add(submit, 0, 2);
		    	   rightBoard.setPadding(new Insets(0,20,0,0));
		    	   aiDecoderPane.setRight(rightBoard);
		    	   aiDecoderPane.setCenter(gameBoard);
		    	   ArrayList<Peg> aiGuess = AIMastermind.initialguess();
		    	   for(int x=0; x<Params.boardWidth; x++){
		    		   gameBoardButtons[x][rowIndex].pegInput = aiGuess.get(x);
		    		   gameBoardButtons[x][rowIndex].redraw();
		    	   }
		    	   rowIndex-=1;
		    	   
		    	   
		    	   primaryStage.setScene(aiDecoderScene);
		    	   	}
		});
		
		//Check Button Handler, this is where the magic happens//
		check.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	   ArrayList<Peg> userInput = new ArrayList<Peg>();
		    	   //Check to see if any of the buttons in the same row are gray//
		    	   for(int x=0; x<Params.boardWidth; x++){
		    		   if(gameBoardButtons[x][rowIndex].pegInput.getPegName().equals("Gray")){
		    			   return;
		    		   }
		    		   userInput.add(gameBoardButtons[x][rowIndex].pegInput);
		    	   	}
		    	   //---------------------------End of Check---------------------//
		    	   
		    	   
		    	    feedbackBoard[0][rowIndex].changeFeedback(MasterMindConsole.inputCheck(userInput));
		    	    if(feedbackBoard[0][rowIndex].checkWin() == true){
		    	    	pane.setCenter(gameCompletedPane);
		    	    	pane.getChildren().remove(rightBoard);
		    	    	winOrLose.setText("You Won!");
		    	    	return;
		    	    }
		    	    
		    	   
		    	   //Check to see if the amount of guesses is over
		    	   if(rowIndex == 0){
		    	    	pane.setCenter(gameCompletedPane);
		    	    	pane.getChildren().remove(rightBoard);
		    	    	winOrLose.setText("You Lost!");
		    	    	return;
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
		
		submit.setOnAction(new EventHandler<ActionEvent>() {
		       @Override
		       public void handle(ActionEvent e) {
		    	  answerKey.getChildren().clear();
		    	  int blackPegs=0;
		    	  int whitePegs=0;
		    	  ArrayList<Peg> feedback = new ArrayList<Peg>();
		    	   //Check to see if any of the buttons in the same row are gray//
		    	   for(int x=0; x<Params.boardWidth; x++){
		    		  if(humanFeedback[x].pegInput.getPegName().equals("Black")){
		    			   blackPegs++;
		    			   feedback.add(PegCreator.pegConstructor("Black"));
		    		   }
		    		   else if(humanFeedback[x].pegInput.getPegName().equals("White")){
		    			   whitePegs++;
		    			   feedback.add(PegCreator.pegConstructor("White"));
		    		   }
		    		  answerKey.getChildren().add(new RoundButton(gameBoardButtons[x][rowIndex+1].pegInput.getPegName(), 30));
		    		  humanFeedback[x].pegInput = PegCreator.pegConstructor("Gray");
		    		  humanFeedback[x].redraw();
		    	   	}
		    	   //---------------------------End of Check---------------------//
		    	   if(blackPegs==Params.pegNumbertoGuess){
				   			
					
		    		   aiDecoderPane.setCenter(gameCompletedPane);
		    		   aiDecoderPane.getChildren().remove(rightBoard);
		    	    	winOrLose.setText("AI Won!");
		    	    	return;
		    	   }
		    	   
		    	   ArrayList<Peg> aiGuess = AIMastermind.aiGuessBasedOnFeedback(blackPegs,whitePegs);
		    	   if(aiGuess == null){
		    		   winOrLose.setText("YOU CHEATED!");
		    		   aiDecoderPane.setCenter(gameCompletedPane);
		    		   aiDecoderPane.getChildren().remove(rightBoard);
		    		   return;
		    	   }
		    	   feedbackBoard[0][rowIndex+1].changeFeedback(feedback);
		    	   
		    	   for(int x=0; x<Params.boardWidth;x++){
		    		   gameBoardButtons[x][rowIndex].pegInput = aiGuess.get(x);
		    		   gameBoardButtons[x][rowIndex].redraw();
		    		   gameBoardButtons[x][rowIndex+1].setDisable(true);
		    		   gameBoardButtons[x][rowIndex].setDisable(false);
		    	   }
		    	   rowIndex -= 1;
		       }
		    	   
		    	    
		});
		
		playAgainButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				MasterMindConsole.answerGenerator();
				gameBoard.getChildren().clear();
				rightBoard.getChildren().clear();
				answerKey.getChildren().clear();
				rowIndex = Params.boardHeight-1;
				
				//Reseting GameBoardButtons//
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
				
				//Reseting feedbackBoard buttons//
				for(int y=0; y<feedbackBoard[0].length; y++){
					feedbackBoard[0][y] = new feedbackFlowPane();
					gameBoard.add(feedbackBoard[0][y], Params.boardWidth, y);
				}
				
				//Reseting the Pane//
				primaryStage.setScene(startupScene);
			}
		});
	}
	
	
	//Event Handler for each button in the game board//
	private static void registerGameBoardButton(RoundButton button){
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if(cursorColor != "Gray" && cursorColor != "White" && cursorColor != "Black"){
					button.pegInput = PegCreator.pegConstructor(cursorColor);
					button.redraw();
					cursorColor = "Gray";
					s.setCursor(Cursor.DEFAULT);
				}
			}
		});
	}
		private static void registerFeedbackButton(RoundButton button){
			button.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event){
					if(cursorColor != "Gray"){
						button.pegInput = PegCreator.pegConstructor(cursorColor);
						button.redraw();
						cursorColor = "Gray";
						aiDecoderScene.setCursor(Cursor.DEFAULT);
					}
				}
			});
		
	}
	
}
