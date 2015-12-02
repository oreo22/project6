package project6;

import java.util.ArrayList;
import java.util.HashSet;

import project6.Pegs.*;

/**
 * Created by Oriana_W on 20/11/2015.
 */

public class AIMastermind {
	static HashSet<ArrayList<Peg>> possibleCombinations = new HashSet<ArrayList<Peg>>();
	
	
	private static void possibleCombinationsConstructorRecur(ArrayList<Peg> guess, int pos){
		if(pos==-1){
			return;
		}
		for(int x=0; x<MasterMindConsole.availableColors.size(); x++){
			guess.set(pos, MasterMindConsole.availableColors.get(x));
			possibleCombinations.add(guess);
			possibleCombinationsConstructorRecur(guess, pos-1);
		}
		
	}
	
	public static void possibleCombinationsConstructor(){
		ArrayList<Peg> guess = new ArrayList<Peg>(Params.boardWidth);
    	for(int x=0; x<guess.size(); x++){
    		guess.set(x, MasterMindConsole.availableColors.get(0));
    	}
    	possibleCombinationsConstructorRecur(guess, guess.size()-1);
	}
	
	
	
    public static void initialguess(){
    	possibleCombinationsConstructor();
    	System.out.println("yooo");
        
        //if feedback is negative with no white or black pegs,
        //Guess yellow and red (also works if they give back 3 white pegs, we either guess a yellow or red)
        //if none of these colors exist in the human's arraylist, HUMAN IS CHEATING
    }
    public static void checkFeedback(ArrayList<Peg> humanFeedback){
        for(int i=0; i<humanFeedback.size(); i++){
            //if it's a black peg, we keep the stuff we have and change one?
            //if it's a white peg, we switch the order of the things we have?
        }
    }
    /*Method I use to play:
    * 1. Guess all possible colors
    * 2. Depending on feedback, I replace x-(WBpegNum) number of colors to see if I get the same feedback or better
    * 3. If feedback is better (# of pegs is better than the last guess), i change very minimal
    * 4. Goal is to get all the white pegs first
    * 5. Then I mess around with the order of pegs*/
}
