package project6.Pegs;
import javafx.scene.paint.Color;

public class RedPeg extends Peg {
   public RedPeg(){
        color= Color.RED;
    }
    @Override
    public int hashCode() {
        return this.getPegName().hashCode();
    }

    @Override
    public String getPegName() {
        return "Red";
    }

    @Override
    public char getPegText() {
        return 'R';
    }

    @Override
    public Peg copy() {
        return new RedPeg();
    }
}
