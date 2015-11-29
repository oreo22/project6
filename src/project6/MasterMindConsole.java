package project6;
import java.util.*;

import project6.Pegs.*;

public class MasterMindConsole {
	private static Peg[][] gameGrid = new Peg[Params.boardWidth][Params.boardHeight];
	private static HashMap<String, ArrayList<Integer>> answer=new HashMap<>(Params.pegNumbertoGuess);
	//-----Creating random code for user to guess--------
	public static void answerGenerator(){ //it's public because Main is using it
		for(int x=0; x<Params.pegNumbertoGuess; x++){
			int temp=randomIntGenerator();
			Peg answerPeg=PegCreator.pegConstructor(PegCreator.numberToPegColor(temp));
			if(answer.get(answerPeg.pegName)==null){
				ArrayList<Integer> colorPositions=new ArrayList<>(Params.pegNumbertoGuess);
				colorPositions.add(x);
				answer.put(answerPeg.pegName, colorPositions);
			}
			else {
				answer.get(answerPeg.pegName).add(x);
			}
		}
	}
	private static int randomIntGenerator(){
		Random rand = new Random();
		int value = rand.nextInt(5);
		return value;
	}
	//------Did the user guess the correct code?---- From graphics or console, just send in an arraylist of Pegs, and this method will check for you
	public static ArrayList inputCheck(ArrayList<Peg> userInput){
		//Tad PROBLEM is that this method uses the pegname or the peg color's name as the Hashmap's key
		//If we have time, I would like to fix this so that it uses Peg value as the key
		ArrayList<Peg> pegAnswer = new ArrayList<Peg>(Params.pegNumbertoGuess);
		ArrayList <Integer> checker=new ArrayList<>(Params.pegNumbertoGuess);
		HashMap<String, ArrayList<Integer>> answercopy=answercopier();
	//	ArrayList<Integer> countOfEachPeg=new ArrayList<>(Params.pegNumbertoGuess);
		for(int x=0; x<Params.pegNumbertoGuess; x++){
			Peg test1=userInput.get(x);
			checker=answercopy.get(test1.pegName);
			if(checker==null || checker.size()<=0){ //if color doesn't exist in the answer
				pegAnswer.add(x,null);
			}
			else{
				boolean checkedFlag=false;
				for(int i=0; i<checker.size(); i++){
					if(((Integer)x).equals(checker.get(i))){ //same color and position
						pegAnswer.add(x,new BlackPeg());
						checkedFlag=true;
						answercopy.get(test1.pegName).remove(i); // remove the index already found
						break;
						//
					}
				}
				if(checkedFlag==false){
					pegAnswer.add(x,new WhitePeg());
				}
			}
		}
		for(int x=0; x<pegAnswer.size(); x++){
			if(pegAnswer.get(x) instanceof WhitePeg){
				if((answercopy.get(userInput.get(x).pegName).size()<=0)){
					pegAnswer.set(x,null);
				}
			}
		}
		return pegAnswer;
	}
	//----Creates a temp copy of the answer the player is supposed to guess------
	private static HashMap<String, ArrayList<Integer>> answercopier (){
		HashMap<String, ArrayList<Integer>> answerCopy= new HashMap<>(Params.pegNumbertoGuess);
		for (HashMap.Entry<String, ArrayList<Integer>> entry : answer.entrySet())
			answerCopy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
		return answerCopy;
	}
	//----Create a temp copy of the number of pegs in each arraylist in the hashmap----
	private static ArrayList<Integer> numOfEachPeg(HashMap<String, ArrayList<Integer>> anwercopier){
		ArrayList<Integer> countOfEachPeg=new ArrayList<>(Params.pegNumbertoGuess);
		return countOfEachPeg;
	}
	//private static ArrayList<Peg> answer = new ArrayList<Peg>(Params.pegNumbertoGuess);
	
/*	public static ArrayList inputCheck(ArrayList<Peg> userInput){
		ArrayList<Peg> pegAnswer = new ArrayList<Peg>(Params.pegNumbertoGuess);
		for(int x=0; x<userInput.size(); x++){
			for(int y=0; y<userInput.size(); y++){
				if(userInput.get(x).equals(answer.get(y))){
					pegAnswer.add(new BlackPeg());
				}
			}
		}
		
		
		return pegAnswer;
		
	}*/
	
	

}
