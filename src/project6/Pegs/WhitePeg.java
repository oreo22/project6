package project6.Pegs;

import javafx.scene.paint.Color;
/**
 * Created by Oriana_W on 20/11/2015.
 */
public class WhitePeg extends Peg{
    public WhitePeg(){
        color= Color.WHITE;
    }
    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "White";
    }

    @Override
    public char getPegText() {
        return 'W';
    }

    @Override
    public Peg copy() {
        return new WhitePeg();
    }

}
