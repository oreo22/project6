package project6.Pegs;

import javafx.scene.paint.Color;

/**
 * Created by Oriana_W on 20/11/2015.
 */
public class OrangePeg extends Peg {
   public  OrangePeg(){ //is this a good idea??? for constructor to be declared public?
        color= Color.ORANGE;
        pegText='O';
        pegName="Orange";
    }
    public Peg copy() {
        OrangePeg copy=new OrangePeg();
        return copy;
    }
}
