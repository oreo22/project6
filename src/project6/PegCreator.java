package project6;
import project6.Pegs.*;

import java.util.ArrayList;

/**
 * Created by Oriana_W on 23/11/2015.
 */
public class PegCreator {
    public static String numberToPegColor(int randNum){
        String pegColor;
        switch (randNum) {
            case 0:  pegColor = "Blue";
                break;
            case 1:  pegColor = "Green";
                break;
            case 2:  pegColor = "Orange";
                break;
            case 3:  pegColor = "Purple";
                break;
            case 4:  pegColor = "Red";
                break;
            default: pegColor = "Yellow"; //5
                break;
        }
        return pegColor;
    }
    public static Peg pegConstructor(String pegName){
        if(pegName.equals("Blue") || pegName.equals("B")){
            return new BluePeg();
        }
        else if(pegName.equals("Green") || pegName.equals("G")){
            return new GreenPeg();
        }
        else if(pegName.equals("Orange") || pegName.equals("O")){
            return new OrangePeg();
        }
        else if(pegName.equals("Purple") || pegName.equals("P")){
            return new PurplePeg();
        }
        else if(pegName.equals("Red") || pegName.equals("R")){
            return new RedPeg();
        }
        else if(pegName.equals("Yellow") || pegName.equals("Y")){
            return new YellowPeg();
        }
        else if(pegName .equals("Gray")){
        	return new GrayPeg();
        }
        else if(pegName.equals("White")){
        	return new WhitePeg();
        }
        else if(pegName.equals("Black")){
        	return new BlackPeg();
        }
        else {
            return null;
        }
    }
    public final static ArrayList<Peg> availableColors = new ArrayList<Peg>(){{
        add(new RedPeg());
        add(new YellowPeg());
        add(new OrangePeg());
        add(new BluePeg());
        add(new PurplePeg());
        add(new GreenPeg());

    }};
}

