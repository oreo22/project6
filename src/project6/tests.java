package project6;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import project6.Pegs.BluePeg;
import project6.Pegs.GreenPeg;
import project6.Pegs.OrangePeg;
import project6.Pegs.Peg;
import project6.Pegs.PurplePeg;
import project6.Pegs.RedPeg;
import project6.Pegs.YellowPeg;

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

}
