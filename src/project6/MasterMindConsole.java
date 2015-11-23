package project6;
import java.util.*;

import project6.Pegs.*;

public class MasterMindConsole {
	private static Peg[][] gameGrid = new Peg[Params.boardWidth][Params.boardHeight];
	private static ArrayList<Peg> answer = new ArrayList<Peg>(Params.pegNumbertoGuess);
	
	public static ArrayList inputCheck(ArrayList<Peg> userInput){
		ArrayList<Peg> pegAnswer = new ArrayList<Peg>(Params.pegNumbertoGuess);
		for(int x=0; x<userInput.size(); x++){
			for(int y=0; y<userInput.size(); y++){
				if(userInput.get(x).equals(answer.get(y))){
					pegAnswer.add(new BlackPeg());
				}
			}
		}
		
		
		return pegAnswer;
		
	}
	
	

}
