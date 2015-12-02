package project6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import project6.Pegs.*;

/**
 * Created by Oriana_W on 20/11/2015.
 */

public class AIMastermind {
	
	private static ArrayList<Peg> aiGuess;
	private static HashSet<ArrayList<Peg>> possibleCombinations = new HashSet<ArrayList<Peg>>();
	
	
	private static void removeNonIntersections(){ //remove the colors that were in aiGuess
/*		for(int x=0; possibleCombinations.size(); x++){
			possibleCombinations.iterator().hasNext()
		}*/
		Iterator<ArrayList<Peg>> itr = possibleCombinations.iterator();
		while(itr.hasNext()){
			boolean noContains=true;
			
			ArrayList <Peg> victim=itr.next();
			for(int i=0; i<aiGuess.size(); i++){
				if(victim.contains(aiGuess.get(i))){
					noContains=false;
				}
			}
			if(noContains==true){
				itr.remove();
			}
		}
	}
	
	private static void removeCurrentColors(){ //remove the combinations that don't have aiInput's colors
		Iterator<ArrayList<Peg>> itr = possibleCombinations.iterator();
		while(itr.hasNext()){
			ArrayList <Peg> victim=itr.next();
			for(int i=0; i<aiGuess.size(); i++){
				if(victim.contains(aiGuess.get(i))){
					itr.remove();
				}
			}
		}
	}
	
	private static void removeIncorrectPositionCombinations(){
		Iterator<ArrayList<Peg>> itr2 = possibleCombinations.iterator();
		while(itr2.hasNext()){
			boolean noContains=true;
			
			ArrayList <Peg> victim=itr2.next();
			
			for(int x=0; x<victim.size(); x++){
				ArrayList<Integer> indexList = new ArrayList<Integer>();
				for(int y=0; y<victim.size(); y++){
					if(victim.get(y).equals(aiGuess.get(x))){indexList.add(y);}
				}
				if(indexList.contains(x)){
					noContains = false;
					break;
				}
			}
			if(noContains == true){
				itr2.remove();
			}
		}
	}
	
	
	private static void possibleCombinationsConstructorRecur(ArrayList<Peg> guess, int pos){
		if(pos==-1){
			return;
		}
		for(int x=0; x<MasterMindConsole.availableColors.size(); x++){
			guess.set(pos, MasterMindConsole.availableColors.get(x));
			possibleCombinations.add(new ArrayList<Peg>(guess));
			possibleCombinationsConstructorRecur(guess, pos-1);
		}
		
	}
	
	private static void possibleCombinationsConstructor(){
		ArrayList<Peg> guess = new ArrayList<Peg>();
    	for(int x=0; x<Params.boardWidth; x++){
    		guess.add(MasterMindConsole.availableColors.get(0));
    	}
    	possibleCombinationsConstructorRecur(guess, guess.size()-1);
	}
	
	
	
	
    private static ArrayList<Peg> initialguess(){
    	possibleCombinationsConstructor();
    	ArrayList<Peg> initialguess=new ArrayList(Params.pegNumbertoGuess);
    	for(int x=0; x<(Params.pegNumbertoGuess/2); x++){
    		initialguess.add(MasterMindConsole.availableColors.get(0));
    	}
    	for(int x=(Params.pegNumbertoGuess/2); x< Params.pegNumbertoGuess; x++){
    		initialguess.add(MasterMindConsole.availableColors.get(1));
    	}
    	return initialguess;
        
        //if feedback is negative with no white or black pegs,
        //Guess yellow and red (also works if they give back 3 white pegs, we either guess a yellow or red)
        //if none of these colors exist in the human's arraylist, HUMAN IS CHEATING
    }
    
    public static void start(){
    	aiGuess=initialguess();
    	
    }
    
    public static ArrayList<Peg> aiGuessBasedOnFeedback(ArrayList<Peg> feedback){
    	int blackPegCount=0; int whitePegCount=0;
    	for(int i=0; i<feedback.size(); i++){
    		if(feedback.get(i) instanceof WhitePeg){
    			whitePegCount++;
    		}
    		else if(feedback.get(i) instanceof BlackPeg){
    			blackPegCount++;
    		}
    	}
    	if((blackPegCount+whitePegCount) >=1){
    		removeNonIntersections();
    	}
    	else if((blackPegCount+whitePegCount) == 0){
    		removeCurrentColors();
    	}
    	else if(blackPegCount >=1){
    		removeIncorrectPositionCombinations();
    	}
    	aiGuess = possibleCombinations.iterator().next();
		return aiGuess;
    	
    }
}
