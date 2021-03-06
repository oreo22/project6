package project6;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.*;

import project6.Pegs.*;

public class tests {

	
	
	/*Test removing all the colors that are in the aiGuess*/
	@Test
	public void RemoveAllColorsTest() {
	       AIMastermind.possibleCombinations = new HashSet<ArrayList<Peg>>();
	       AIMastermind.initialguess();
	        
	       ArrayList<Peg> tests = new ArrayList<Peg>();
	       tests.add(new RedPeg());
	       tests.add(new BluePeg());
	       tests.add(new YellowPeg());
	       tests.add(new GreenPeg());
	       AIMastermind.aiGuess = tests;
	       AIMastermind.removeCurrentColors();
	       tests.clear();
	       tests.add(new OrangePeg());
	       tests.add(new PurplePeg());
	       tests.add(new YellowPeg());
	       tests.add(new GreenPeg());
	       AIMastermind.removeCurrentColors();
	        
	        assertEquals(0, AIMastermind.possibleCombinations.size());
	}
	@Test
	public void RemoveNonIntersectionsTest() {
	       int whitePegCount=1; int blackPegCount=0;
	       AIMastermind.aiGuess = new ArrayList<Peg>();
	       ArrayList<Peg> testArray = new ArrayList<Peg>();
	       testArray.add(new GreenPeg());
	       testArray.add(new OrangePeg());
	       testArray.add(new BluePeg());
	       testArray.add(new PurplePeg());
	       
	       AIMastermind.possibleCombinations = new HashSet<ArrayList<Peg>>();
	       AIMastermind.possibleCombinations.add(testArray);
	       
	        AIMastermind.aiGuess.add(new RedPeg());
	        AIMastermind.aiGuess.add(new YellowPeg());
	        AIMastermind.aiGuess.add(new RedPeg());
	        AIMastermind.aiGuess.add(new RedPeg());
	        AIMastermind.aiGuessBasedOnFeedback(blackPegCount,whitePegCount);
	        assertEquals(0, AIMastermind.possibleCombinations.size());
	}
	@Test
	public void RemoveIncorrectPositionCombinations() {
	       int whitePegCount=0; int blackPegCount=4;
	       AIMastermind.possibleCombinations.clear();
	       AIMastermind.possibleCombinationsConstructor();
	       AIMastermind.aiGuess = new ArrayList<Peg>();
	        AIMastermind.aiGuess.add(new RedPeg());
	        AIMastermind.aiGuess.add(new YellowPeg());
	        AIMastermind.aiGuess.add(new BluePeg());
	        AIMastermind.aiGuess.add(new PurplePeg());
	        AIMastermind.aiGuessBasedOnFeedback(blackPegCount,whitePegCount);
	        assertEquals(0, AIMastermind.possibleCombinations.size()); //This will be 0 because our method removes the aiGuess and since there will 
	        														   //only be 1 correct combination in the possibleCombinations before it is removes, 
	        														   //it will be 0
	}
	@Test
	public void possibleCombinationTest() {
		//To test this method, we changed the number of avaliable colors in PegCreator by commenting them out
		AIMastermind.initialguess();
		assertEquals((int)Math.pow(Params.numberOfPegTypes, Params.pegNumbertoGuess), AIMastermind.possibleCombinations.size()); //Should be 6^4, or 1296
	}

	@Test
	public void inputCheckTest(){
		//Changed answer to public from private
		MasterMindConsole.answer.clear();
			ArrayList<Integer> colorPositions=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions.add(0);
			MasterMindConsole.answer.put("Yellow",colorPositions );
			ArrayList<Integer> colorPositions2=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions2.add(1);
			MasterMindConsole.answer.put("Blue",colorPositions2 );
			ArrayList<Integer> colorPositions3=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions3.add(2);
			MasterMindConsole.answer.put("Green",colorPositions3 );
			ArrayList<Integer> colorPositions4=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions4.add(3);
			MasterMindConsole.answer.put("Red",colorPositions4);
		
		ArrayList<Peg> testUserInput1=new ArrayList<Peg>(){{ //ROPG returns 2 white pegs. 2 gray Pegs
			add(new RedPeg()); add(new OrangePeg()); add(new PurplePeg()); add(new GreenPeg());}};
		ArrayList<Peg> feedback1=MasterMindConsole.inputCheck(testUserInput1);
		ArrayList<Peg> correctFeedback1=new ArrayList<Peg>(){{add(new WhitePeg()); add(new WhitePeg()); }};
		int count = 0;
		for(int i=0; i<feedback1.size(); i++){
			if(correctFeedback1.contains(feedback1.get(i))){
				correctFeedback1.remove(feedback1.get(i));
				
				
			}

		}
		
		assertEquals(correctFeedback1.size(), 0); 

	}

}
