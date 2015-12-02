package project6;
import com.sun.org.apache.xpath.internal.SourceTree;
import project6.Pegs.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MasterMindTextInterface {
	
	public static void HumanvsComputerlaunch(){
		boolean correctInput = true;
		ArrayList<Peg> userInput = null;
		System.out.println("\nYou're in the Text-based console!\n");
		for(int guessCount=0; guessCount<Params.amountOfGuesses; guessCount++){
			
		
			System.out.println("You have "+ (Params.amountOfGuesses-guessCount) +" guesses left.");
			do{
		    correctInput = true;
			System.out.println("What is your next guess?");
			System.out.println("Type in the characters for your guess and press enter.");
			System.out.print("Enter your guess: ");
			Scanner s = new Scanner(System.in);
			String input = s.nextLine();
			userInput=MasterMindTextInterface.creatingUserInput(input);
			if(userInput == null){
				System.out.println("->INVALID INPUT");
				correctInput = false;
			}
			
			}while(correctInput == false);
			ArrayList<Peg> pegAnswer=MasterMindConsole.inputCheck(userInput);
			
			if(checkFeedbackForConsole(pegAnswer)){
				Params.gameWin=true;
				break;
			}
		}
		if(Params.gameWin==false){
			System.out.println("Ha. Ha. Ha. You Lose.");
		}
		else{
			System.out.println("Congratulations Human. You win this time.");
		}
	}
	private static boolean checkFeedbackForConsole(ArrayList<Peg> pegAnswer){
		int numberBlackPegs =0;
		int numberWhitePegs=0;
		for(int i=0; i<pegAnswer.size(); i++){
			if(pegAnswer.get(i)==null){
				//continue;
			}
			else if(pegAnswer.get(i).equals(new BlackPeg())){
				numberBlackPegs++;
			}
			else if(pegAnswer.get(i).equals(new WhitePeg())){
				numberWhitePegs++;
			}
		}
		if(numberBlackPegs==Params.pegNumbertoGuess){
			return true;
		}
		else{
			System.out.println("White Pegs: " + numberWhitePegs + " Black Pegs: " + numberBlackPegs+"\n");
			return false;
		}
	}
	private static ArrayList<Peg>  creatingUserInput(String input){
		input.split("");
		ArrayList<Peg> userInput=new ArrayList<>(Params.pegNumbertoGuess);
		String[] inputArray = input.split(""); //haven't coded exception handling when they put the wrong letter
		for(int x=0; x<inputArray.length; x++){
			Peg covertedPeg = textToPeg(inputArray[x]);
			if(covertedPeg == null){
				return null;
			}
			userInput.add(covertedPeg);
		}
		return userInput;
	}
	private static Peg textToPeg(String s){
		switch (s) {
			case "B":
				return new BluePeg();
			case "G":
				return new GreenPeg();
			case "O":
				return new OrangePeg();
			case "P":
				return new PurplePeg();
			case "R":
				return new RedPeg();
			case "Y":
				return new YellowPeg();
			default:
				return null; //for error handling
		}
	}

	//-------AI MASTERMIND------
	public static void aiVsHuman() {
		printAIGuess(AIMastermind.initialguess());
		int guess=0;
		Scanner s = new Scanner(System.in);
		for (int guessCount = 0; guessCount < Params.amountOfGuesses; guessCount++) {
			System.out.println("How many White Pegs are there?:");
			int whitePegsCount= s.nextInt();
			System.out.println("How many Black Pegs are there?:");
			int blackPegsCount= s.nextInt();
			if(blackPegsCount == 4){
				System.out.println("AI Wins! Computers are better than Humans");
				return;
			}
			ArrayList<Peg> nextGuess = AIMastermind.aiGuessBasedOnFeedback(blackPegsCount,whitePegsCount);
			printAIGuess(nextGuess);
			guess++;
			System.out.println("\nI guessed "+guess+" times");
		}
	}

	private static void printAIGuess(ArrayList<Peg> aiGuess){
		int guess=0;
		for (int i = 0; i < aiGuess.size(); i++) {
			System.out.print(aiGuess.get(i).getPegText());
		}
		System.out.println("");
	}
}

