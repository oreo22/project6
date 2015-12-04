package project6.Pegs;

import javafx.scene.paint.Color;

public class GrayPeg extends Peg{
	public GrayPeg(){
        color= Color.GRAY;
    }

    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Gray";
    }

    @Override
    public char getPegText() {
        return 'G';
    }

    @Override
    public Peg copy() {
        return new GrayPeg();
    }
}
