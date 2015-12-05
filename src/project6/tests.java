package project6;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.*;

import project6.Pegs.*;

public class tests {

	@Test
	public void RemoveAllColorsTest() {
	       int whitePegCount=0; int blackPegCount=0;
	       AIMastermind.aiGuess = new ArrayList<Peg>();
	        AIMastermind.aiGuess.add(new RedPeg());
	        AIMastermind.aiGuess.add(new YellowPeg());
	        AIMastermind.aiGuess.add(new BluePeg());
	        AIMastermind.aiGuess.add(new PurplePeg());
	        AIMastermind.aiGuessBasedOnFeedback(blackPegCount,whitePegCount);
	        AIMastermind.aiGuess.add(new GreenPeg());
	        AIMastermind.aiGuess.add(new OrangePeg());
	        AIMastermind.aiGuess.add(new BluePeg());
	        AIMastermind.aiGuess.add(new PurplePeg());
	        AIMastermind.aiGuessBasedOnFeedback(blackPegCount,whitePegCount);
	        assert(AIMastermind.possibleCombinations.size()==0);
	}
	
	public void RemoveNonIntersectionsTest() {
	       int whitePegCount=0; int blackPegCount=0;
	       AIMastermind.aiGuess = new ArrayList<Peg>();
	        AIMastermind.aiGuess.add(new RedPeg());
	        AIMastermind.aiGuess.add(new YellowPeg());
	        AIMastermind.aiGuess.add(new BluePeg());
	        AIMastermind.aiGuess.add(new PurplePeg());
	        AIMastermind.aiGuessBasedOnFeedback(blackPegCount,whitePegCount);
	        AIMastermind.aiGuess.add(new GreenPeg());
	        AIMastermind.aiGuess.add(new OrangePeg());
	        AIMastermind.aiGuess.add(new BluePeg());
	        AIMastermind.aiGuess.add(new PurplePeg());
	        AIMastermind.aiGuessBasedOnFeedback(blackPegCount,whitePegCount);
	        assert(AIMastermind.possibleCombinations.size()==0);
	}
	@Test
	public void possibleCombinationTest() {
		//To test this method, we changed the number of avaliable colors in PegCreator by commenting them out
		AIMastermind.initialguess();
		assert(AIMastermind.possibleCombinations.size()==(PegCreator.availableColors.size()^(Params.pegNumbertoGuess)));
	}
	@ Test
	public void answerGeneratorTest(){
		for(int i=0; i<Params.pegNumbertoGuess; i++){
			assert(PegCreator.availableColors.contains(MasterMindConsole.answerKey.get(i)));
		}
	}
	@Test
	public void inputCheckTest(){
		//Changed answer to public from private
			ArrayList<Integer> colorPositions=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions.add(0);
			MasterMindConsole.answer.put("Yellow",colorPositions );
			ArrayList<Integer> colorPositions2=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions.add(1);
			MasterMindConsole.answer.put("Blue",colorPositions2 );
			ArrayList<Integer> colorPositions3=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions.add(2);
			MasterMindConsole.answer.put("Green",colorPositions3 );
			ArrayList<Integer> colorPositions4=new ArrayList<>(Params.pegNumbertoGuess);
			colorPositions.add(3);
			MasterMindConsole.answer.put("Red",colorPositions4);
		;
		ArrayList<Peg> testUserInput1=new ArrayList<Peg>(){{ //ROPG returns 2 white pegs
			add(new RedPeg()); add(new OrangePeg()); add(new PurplePeg()); add(new GreenPeg());}};
		ArrayList<Peg> feedback1=MasterMindConsole.inputCheck(testUserInput1);
		ArrayList<Peg> correctFeedback1=new ArrayList<Peg>(){{add(new WhitePeg()); add(new WhitePeg()); }};
		for(int i=0; i<feedback1.size(); i++){
			if(feedback1.contains(correctFeedback1.get(i))){
				feedback1.remove(i);
				correctFeedback1.remove(i);
			}

		}
		assert(feedback1.size()==0 && correctFeedback1.size()==0);

	}

}
