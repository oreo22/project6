package project6.Pegs;

import javafx.scene.paint.Color;
public class YellowPeg extends Peg {
    public YellowPeg(){
        color= Color.YELLOW;
    }
    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Yellow";
    }

    @Override
    public char getPegText() {
        return 'Y';
    }

    @Override
    public Peg copy() {
        return new YellowPeg();
    }
}
