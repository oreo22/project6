package project6.Pegs;

import javafx.scene.paint.*;

/**
 * Created by Oriana_W on 20/11/2015.
 */
public class PurplePeg extends Peg {
    public PurplePeg(){
        color= Color.PURPLE;
        pegText='P';
        pegName="Purple";
    }
    public Peg copy() {
        PurplePeg copy=new PurplePeg();
        return copy;
    }
}
