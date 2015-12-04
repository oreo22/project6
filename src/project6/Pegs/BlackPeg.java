package project6.Pegs;

import javafx.scene.paint.Color;
public class BlackPeg extends Peg{
    public BlackPeg(){
        color=Color.BLACK;
    }

    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Black";
    }

    @Override
    public char getPegText() {
        return 'B';
    }

    @Override
    public Peg copy() {
        return new BlackPeg();
    }
}
