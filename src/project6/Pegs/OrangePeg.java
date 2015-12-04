package project6.Pegs;

import javafx.scene.paint.Color;

/**
 * Created by Oriana_W on 20/11/2015.
 */
public class OrangePeg extends Peg {
   public  OrangePeg(){ //is this a good idea??? for constructor to be declared public?
        color= Color.ORANGE;
    }
    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Orange";
    }

    @Override
    public char getPegText() {
        return 'O';
    }

    @Override
    public Peg copy() {
        return new OrangePeg();
    }
}
