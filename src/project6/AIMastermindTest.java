package project6;
import junit.framework.TestCase;
import junit.framework.TestResult;
import project6.AIMastermind;
import project6.Params;
import project6.Pegs.*;

import java.lang.Override;

import org.junit.Test;

/**
 * Created by Oriana_W on 05/12/2015.
 */
public class AIMastermindTest extends TestCase {

	@Test
	public void aiGuessBasedOnFeedback() throws Exception {
        int whitePegCount=0; int blackPegCount=0;
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


        /*Removes color combinations that don’t have the same n amount of colors from aiGuess.
         Input is n amount of pegs, the result should be removing the combinations from the possibleCombinatations.*/
    }

/*    @Test
    void myTest() {
        return super.createResult();
    }*/
}