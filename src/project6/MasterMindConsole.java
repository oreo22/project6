package project6;
import java.util.*;

import project6.Pegs.*;

public class MasterMindConsole {
	private static Peg[][] gameGrid = new Peg[Params.boardWidth][Params.boardHeight];
	private static HashMap<String, ArrayList<Integer>> answer=new HashMap<>(Params.pegNumbertoGuess);

	//-----Creating random code for user to guess--------
	public static void answerGenerator(){
		for(int x=0; x<Params.pegNumbertoGuess; x++){
			int temp=x;//randomIntGenerator();
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
	public static ArrayList inputCheck(ArrayList<Peg> userInput){
		ArrayList<Peg> pegAnswer = new ArrayList<Peg>(Params.pegNumbertoGuess);
		ArrayList <Integer> checker=new ArrayList<>(Params.pegNumbertoGuess);
		HashMap<String, ArrayList<Integer>> answercopy=(HashMap) answer.clone();
		for(int x=0; x<Params.pegNumbertoGuess; x++){
			Peg test1=userInput.get(x);
			checker=answercopy.get(test1.pegName);
			if(checker==null){ //if color doesn't exist in the answer
				System.out.println("Wrong");
			}
			else if(checker.size()<=0){
				System.out.println("Wrong");
			}
			else{
				boolean checkedFlag=false;
				for(int i=0; i<checker.size(); i++){
					if(((Integer)x).equals(checker.get(i))){ //same color and position
						System.out.println("Black");
						checkedFlag=true;
						answercopy.get(test1.pegName).remove(i); // remove the index already found
						break;
						//pegAnswer.add(new BlackPeg());
					}
				}
				if(checkedFlag==false){ //index doesn't match any of the ones stored,
						System.out.println("White"); // it means
						//pegAnswer.add(new WhitePeg()); //same color, wrong position
				}
			}


		}
		return pegAnswer;
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
