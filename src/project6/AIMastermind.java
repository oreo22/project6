package project6;

import java.util.ArrayList;

import project6.Pegs.*;

/**
 * Created by Oriana_W on 20/11/2015.
 */

public class AIMastermind {

    public static void initialguess(){
        ArrayList<Peg> initialguess=new ArrayList<Peg>(Params.pegNumbertoGuess);
        initialguess.add(new BluePeg());
        initialguess.add(new GreenPeg());
        initialguess.add(new OrangePeg());
        initialguess.add(new PurplePeg());
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
