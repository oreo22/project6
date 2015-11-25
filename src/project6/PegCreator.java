package project6;
import project6.Pegs.*;
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
        if(pegName=="Blue"){
            return new BluePeg();
        }
        else if(pegName=="Green"){
            return new GreenPeg();
        }
        else if(pegName=="Orange"){
            return new OrangePeg();
        }
        else if(pegName=="Purple"){
            return new PurplePeg();
        }
        else if(pegName=="Red"){
            return new RedPeg();
        }
        else if(pegName=="Yellow"){
            return new YellowPeg();
        }
        else {
            return null;
        }
    }
}

