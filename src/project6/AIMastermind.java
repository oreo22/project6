package project6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import project6.Pegs.*;

/**
 * Created by Oriana_W on 20/11/2015.
 */

public class AIMastermind {

	public static ArrayList<Peg> aiGuess;
	public static HashSet<ArrayList<Peg>> possibleCombinations = new HashSet<ArrayList<Peg>>();


	private static void removeNonIntersections(int PegsCount){ //remove the colors combinations that dont have the same amount of colors as aiGuess
		int correctColor = 0;
		Iterator<ArrayList<Peg>> itr = possibleCombinations.iterator();
		while(itr.hasNext()){
			correctColor = 0;
			ArrayList <Peg> victim=itr.next();
			ArrayList <Peg> victimTemp = new ArrayList<Peg>(victim);
			for(int i=0; i<aiGuess.size(); i++){
				if(victimTemp.contains(aiGuess.get(i))){
					victimTemp.remove(aiGuess.get(i));
					correctColor+=1;
				}
			}
			if(correctColor<PegsCount){
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
					break;
				}
			}
		}
	}

	private static void removeIncorrectPositionCombinations(int blackPegCount){
		int correctPosition = 0;
		Iterator<ArrayList<Peg>> itr2 = possibleCombinations.iterator();
		while(itr2.hasNext()){
			correctPosition = 0;
			ArrayList <Peg> victim=itr2.next();

			for(int x=0; x<victim.size(); x++){
				ArrayList<Integer> indexList = new ArrayList<Integer>();
				for(int y=0; y<victim.size(); y++){
					if(victim.get(y).equals(aiGuess.get(x))){indexList.add(y);}
				}
				if(indexList.contains(x)){
					correctPosition++;
				}
			}
			if(correctPosition < blackPegCount){
				itr2.remove();
			}
			
		}
	}
	
	
	private static void possibleCombinationsConstructorRecur(ArrayList<Peg> guess, int pos){
		if(pos==-1){
			return;
		}
		for(int x=0; x<PegCreator.availableColors.size(); x++){
			guess.set(pos, PegCreator.availableColors.get(x));
			possibleCombinations.add(new ArrayList<Peg>(guess));
			possibleCombinationsConstructorRecur(guess, pos-1);
		}
		
	}
	
	private static void possibleCombinationsConstructor(){
		ArrayList<Peg> guess = new ArrayList<Peg>();
    	for(int x=0; x<Params.boardWidth; x++){
    		guess.add(PegCreator.availableColors.get(0));
    	}
    	possibleCombinationsConstructorRecur(guess, guess.size()-1);
	}

	//-----First AI guess-----
    public static ArrayList<Peg> initialguess(){
    	possibleCombinations.clear();
    	possibleCombinationsConstructor();
    	ArrayList<Peg> initialguess=new ArrayList(Params.pegNumbertoGuess);
    	for(int x=0; x<(Params.pegNumbertoGuess/2); x++){
    		initialguess.add(PegCreator.availableColors.get(0));
    	}
    	for(int x=(Params.pegNumbertoGuess/2); x< Params.pegNumbertoGuess; x++){
    		initialguess.add(PegCreator.availableColors.get(1));
    	}
    	aiGuess = initialguess;
    	return aiGuess;
    }
    
    public static ArrayList<Peg> aiGuessBasedOnFeedback(int blackPegCount, int whitePegCount){
		if((whitePegCount+blackPegCount) == 4){
			removeNonIntersections(whitePegCount+blackPegCount);
		}
		if(blackPegCount >= 1){
			removeIncorrectPositionCombinations(blackPegCount);
		}
    	if(whitePegCount >= 1){
    		removeNonIntersections(whitePegCount);
    	}

    	else if((blackPegCount+whitePegCount) == 0){
    		removeCurrentColors();
    	}
		possibleCombinations.remove(aiGuess);
	   	if(possibleCombinations.size() == 0){ //User Was Cheating
    		return null;
    	}
    	aiGuess = possibleCombinations.iterator().next();
		return aiGuess;
    	
    }
}
