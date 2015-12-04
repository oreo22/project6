package project6.Pegs;

import javafx.scene.paint.Color;

/**
 * Created by Oriana_W on 20/11/2015.
 */
public class GreenPeg extends Peg {
    public GreenPeg(){
        color= Color.GREEN;
    }
    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Green";
    }

    @Override
    public char getPegText() {
        return 'G';
    }

    @Override
    public Peg copy() {
        return new GreenPeg();
    }
}
