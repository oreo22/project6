package project6.Pegs;

import javafx.scene.paint.Color;

/**
 * Created by Oriana_W on 20/11/2015.
 */
public class GreenPeg extends Peg {
    public GreenPeg(){
        color= Color.GREEN;
        pegText='G';
        pegName="Green";
    }
    public Peg copy() {
        GreenPeg copy=new GreenPeg();
        return copy;
    }
}
