package project6;
import project6.Pegs.*;

import java.util.ArrayList;

public class MasterMindTextInterface {
	
	public static void launch(){
		System.out.println("You're in the console!");
	}
	public static void creatingUserInput(String input){
		input.split("");
		ArrayList<Peg> userInput=new ArrayList<>(Params.pegNumbertoGuess);
		String[] inputArray = input.split(""); //haven't coded exception handling when they put the wrong letter
		for(int x=0; x<inputArray.length; x++){
			userInput.add(textToPeg(inputArray[x]));
		}
		MasterMindConsole.inputCheck(userInput);
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
			default:
				return new YellowPeg();
		}
	}
}
