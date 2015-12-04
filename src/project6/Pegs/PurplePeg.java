package project6.Pegs;

import javafx.scene.paint.*;

/**
 * Created by Oriana_W on 20/11/2015.
 */
public class PurplePeg extends Peg {
    public PurplePeg(){
        color= Color.PURPLE;
    }
    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Purple";
    }

    @Override
    public char getPegText() {
        return 'P';
    }

    @Override
    public Peg copy() {
        return new PurplePeg();
    }
}
