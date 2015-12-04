package project6.Pegs;

import javafx.scene.paint.Color;
import project6.Pegs.Peg;



/**
 * Created by Oriana_W on 20/11/2015.
 */
public class BluePeg extends Peg {
    public BluePeg(){
        color= Color.BLUE;
    }


    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Blue";
    }

    @Override
    public char getPegText() {
        return 'B';
    }

    @Override
    public Peg copy() {
        return new BluePeg();
    }
}
